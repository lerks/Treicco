package treicco.client;

import java.util.ArrayList;

import treicco.shared.Directory;
import treicco.shared.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class DirectoryPanel extends ResizeComposite {

	private static DirectoryPanelUiBinder uiBinder = GWT.create(DirectoryPanelUiBinder.class);

	interface DirectoryPanelUiBinder extends UiBinder<Widget, DirectoryPanel> {
	}

	private final TaskManagerAsync competitionManager = GWT.create(TaskManager.class);

	private String path = "/";

	@UiField
	Button addDirectoryButton;

	@UiField
	Button addTaskButton;

	@UiField
	FlowPanel parentsPanel;

	@UiField
	FlowPanel childrenPanel;

	@UiField
	FlowPanel tasksPanel;

	interface DirectoryStyle extends CssResource {
		String Parent();

		String LastParent();

		String Child();

		String Task();
	}

	@UiField
	DirectoryStyle style;

	private AddDirectoryDialog addDirectoryDialog = new AddDirectoryDialog();

	private AddTaskDialogo addTaskDialog = new AddTaskDialogo();

	public DirectoryPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				GWT.log("DirectoryPanel's History handler called with argument " + event.getValue());

				childrenPanel.clear();
				tasksPanel.clear();

				setPath(event.getValue());
			}
		});
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String target_path) {
		GWT.log("DirectoryPanel.setPath() called with argument " + target_path);
		if (target_path.equals(path)) {
			GWT.log("The given path has already been reached: " + path);

			showChildren();

		} else if (target_path.startsWith(path)) { // go down in the directory
			// tree
			GWT.log("The given path is below the current position: " + this.path);

			final String child_path = target_path.substring(0, target_path.indexOf("/", path.length()) + 1);

			competitionManager.readDirectory(child_path, new AsyncCallback<Directory>() {
				public void onFailure(Throwable caught) {
					// setText("ERROR_MESSAGE");
				}

				public void onSuccess(Directory d) {
					Hyperlink h = new Hyperlink(d.getFullName(), d.getId());
					h.addStyleName(style.Parent());

					parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).removeStyleName(style.LastParent());
					parentsPanel.add(h);
					parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).addStyleName(style.LastParent());

					path = child_path;
					setPath(target_path);
				}
			});

		} else { // go up in the directory tree
			GWT.log("The given path is above the current position: " + this.path);

			parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).removeStyleName(style.LastParent());
			parentsPanel.remove(parentsPanel.getWidgetCount() - 1);
			parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).addStyleName(style.LastParent());

			path = Directory.getParent(path);
			setPath(target_path);

		}
	}

	public void showChildren() {
		competitionManager.listDirectories(path, new AsyncCallback<ArrayList<Directory>>() {
			public void onFailure(Throwable caught) {
				// setText("ERROR_MESSAGE");
			}

			public void onSuccess(ArrayList<Directory> result) {
				showDirectories(result);
			}
		});
		competitionManager.listTasks(path, new AsyncCallback<ArrayList<Task>>() {
			public void onFailure(Throwable caught) {
				// setText("ERROR_MESSAGE");
			}

			public void onSuccess(ArrayList<Task> result) {
				showTasks(result);
			}
		});
	}

	private void showDirectories(ArrayList<Directory> children) {
		for (Directory d : children) {
			Hyperlink h = new Hyperlink(d.getFullName(), d.getId());
			h.addStyleName(style.Child());
			childrenPanel.add(h);
		}
	}

	private void showTasks(ArrayList<Task> children) {
		for (Task t : children) {
			Hyperlink h = new Hyperlink(t.getFullName(), t.getId());
			h.addStyleName(style.Task());
			tasksPanel.add(h);
		}
	}

	@UiHandler("addDirectoryButton")
	public void addDirectory(ClickEvent e) {
		addDirectoryDialog.init(this);
	}

	@UiHandler("addTaskButton")
	public void addTask(ClickEvent e) {
		addTaskDialog.init(this);
		addTaskDialog.center();
	}

}
