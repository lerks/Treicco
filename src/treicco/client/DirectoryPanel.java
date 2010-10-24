package treicco.client;

import java.util.ArrayList;

import treicco.shared.Directory;
import treicco.shared.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class DirectoryPanel extends ResizeComposite {
	private final TaskManagerAsync competitionManager = GWT.create(TaskManager.class);

	private String path = "/";

	private ArrayList<Hyperlink> parent_directories = new ArrayList<Hyperlink>();
	private ArrayList<Hyperlink> child_directories = new ArrayList<Hyperlink>();
	private ArrayList<Hyperlink> child_tasks = new ArrayList<Hyperlink>();

	private FlowPanel mainpanel = new FlowPanel();
	private FlowPanel pd_panel = new FlowPanel();
	private FlowPanel cd_panel = new FlowPanel();
	private FlowPanel ct_panel = new FlowPanel();
	private LayoutPanel bottombar = new LayoutPanel();

	private Button add_directory = new Button("Add Directory");

	private Button add_task = new Button("Add Task");

	private AddDirectoryDialog add_directory_dialog = new AddDirectoryDialog();

	private AddTaskDialog add_task_dialog = new AddTaskDialog();

	public static final int BOTTOMBAR_WIDTH = 250;
	public static final int BOTTOMBAR_HEIGHT = 29;
	public static final int BOTTOMBAR_PADDINGTOP = 4;
	public static final int BOTTOMBAR_PADDINGBOTTOM = 3;
	public static final int BOTTOMBAR_PADDINGLEFT = 10;
	public static final int BOTTOMBAR_PADDINGRIGHT = 5;
	public static final int BOTTOMBAR_SPACING = 10;

	public void setPath(final String target_path) {
		GWT.log("DirectoryPanel.setPath() called with argument " + target_path);
		if (target_path.equals(path)) {
			GWT.log("The given path has already been reached: " + path);
			
			showChildren();
			
		} else if (target_path.startsWith(path)) { // go down in the directory tree
			GWT.log("The given path is below the current position: " + this.path);
			
			final String child_path = target_path.substring(0, target_path.indexOf("/", path.length()) + 1);
			
			competitionManager.readDirectory(child_path, new AsyncCallback<Directory>() {
				public void onFailure(Throwable caught) {
					// setText("ERROR_MESSAGE");
				}

				public void onSuccess(Directory d) {
					Hyperlink h = new Hyperlink(d.getFullName(), d.getId());
					h.addStyleName("DirectoryPanel-ParentDirectory");
					
					parent_directories.get(parent_directories.size() - 1).removeStyleName("DirectoryPanel-LastParentDirectory");
					parent_directories.add(h);
					pd_panel.add(h);
					parent_directories.get(parent_directories.size() - 1).addStyleName("DirectoryPanel-LastParentDirectory");
					
					path = child_path;
					setPath(target_path);
				}
			});
			
		} else { // go up in the directory tree
			GWT.log("The given path is above the current position: " + this.path);
			
			parent_directories.get(parent_directories.size() - 1).removeStyleName("DirectoryPanel-LastParentDirectory");
			parent_directories.remove(parent_directories.size() - 1);
			pd_panel.remove(pd_panel.getWidgetCount() - 1);
			parent_directories.get(parent_directories.size() - 1).addStyleName("DirectoryPanel-LastParentDirectory");
			
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
			h.addStyleName("DirectoryPanel-ChildDirectory");
			child_directories.add(h);
			cd_panel.add(h);
		}
	}

	private void showTasks(ArrayList<Task> children) {
		for (Task t : children) {
			Hyperlink h = new Hyperlink(t.getFullName(), t.getId());
			h.addStyleName("DirectoryPanel-ChildTask");
			child_directories.add(h);
			ct_panel.add(h);
		}
	}

	public DirectoryPanel() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				GWT.log("DirectoryPanel's History handler called with argument " + event.getValue());
				
				child_directories.clear();
				cd_panel.clear();
				child_tasks.clear();
				ct_panel.clear();
				
				setPath(event.getValue());
			}
		});

		LayoutPanel lp = new LayoutPanel();
		
		// BottomBar
		bottombar.addStyleName("DirectoryPanel-BottomBar");
		
		lp.add(bottombar);
		lp.setWidgetBottomHeight(bottombar, 0, Unit.PX, BOTTOMBAR_HEIGHT, Unit.PX);
		lp.setWidgetLeftRight(bottombar, 0, Unit.PX, 0, Unit.PX);

		// AddDirectory
		add_directory.addStyleName("DirectoryPanel-add_dir");
		
		bottombar.add(add_directory);
		bottombar.setWidgetTopBottom(add_directory, BOTTOMBAR_PADDINGTOP, Unit.PX, BOTTOMBAR_PADDINGBOTTOM, Unit.PX);
		bottombar.setWidgetLeftRight(add_directory, BOTTOMBAR_PADDINGLEFT, Unit.PX, (BOTTOMBAR_WIDTH - BOTTOMBAR_PADDINGLEFT - BOTTOMBAR_PADDINGRIGHT) / 2 + BOTTOMBAR_SPACING / 2 + BOTTOMBAR_PADDINGRIGHT, Unit.PX);
		add_directory.setWidth("113px");
		
		// AddTask
		add_task.addStyleName("DirectoryPanel-add_task");
		
		bottombar.add(add_task);
		bottombar.setWidgetTopBottom(add_task, BOTTOMBAR_PADDINGTOP, Unit.PX, BOTTOMBAR_PADDINGBOTTOM, Unit.PX);
		bottombar.setWidgetLeftRight(add_task, (BOTTOMBAR_WIDTH - BOTTOMBAR_PADDINGLEFT - BOTTOMBAR_PADDINGRIGHT) / 2 + BOTTOMBAR_SPACING / 2 + BOTTOMBAR_PADDINGLEFT, Unit.PX, BOTTOMBAR_PADDINGRIGHT, Unit.PX);
		add_task.setWidth("113px");

		// MainPanel
		lp.add(mainpanel);
		lp.setWidgetTopBottom(mainpanel, 0, Unit.PX, BOTTOMBAR_HEIGHT, Unit.PX);
		lp.setWidgetLeftRight(mainpanel, 0, Unit.PX, 0, Unit.PX);
		
		// ParentDirectories
		mainpanel.add(pd_panel);
		
		// ChildDirectories
		mainpanel.add(cd_panel);
		
		// ChildTasks
		mainpanel.add(ct_panel);
		
		initWidget(lp);

		Hyperlink h = new Hyperlink ("Home Page", "/");
		h.addStyleName("DirectoryPanel-ParentDirectory");
		h.addStyleName("DirectoryPanel-LastParentDirectory");
		parent_directories.add(h);
		pd_panel.add (h);

		add_task.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addTask();
			}
		});

		add_directory.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addDirectory();
			}
		});
	}

	public String getPath() {
		return path;
	}

	public void addDirectory() {
		add_directory_dialog.init(this);
		add_directory_dialog.center();
	}

	public void addTask() {
		add_task_dialog.init(this);
		add_task_dialog.center();
	}
}
