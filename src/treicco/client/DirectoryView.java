package treicco.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.TreeItem;

public class DirectoryView extends TreeItem {
	private final CompetitionManagerAsync competitionManager = GWT.create(CompetitionManager.class);

	private String path;

	private Directory directory;

	private AddDirectoryDialog add_directory_dialog = new AddDirectoryDialog();
	
	private AddTaskDialog add_task_dialog = new AddTaskDialog();

	public DirectoryView(String path) {
		this.path = path;
		updateData();
	}

	public String getPath() {
		return path;
	}
	
	public void addDirectory () {
		add_directory_dialog.init(this);
		add_directory_dialog.center();
	}
	
	public void addTask () {
		add_task_dialog.init(this);
		add_task_dialog.center();
	}

	public void updateData() {
		competitionManager.get(path, new AsyncCallback<Directory>() {
			public void onFailure(Throwable caught) {
				setText("ERROR_MESSAGE");
			}

			public void onSuccess(Directory result) {
				directory = result;
				updateView();
			}
		});
	}

	public void updateView() {
		setText(directory.getId());

		removeItems();

		for (String d : directory.getDirectories()) {
			addItem(new DirectoryView(path + d + "/"));
		}

		Anchor add_d = new Anchor("Add directory...");
		add_d.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addDirectory ();
			}
		});
		addItem(add_d);

		for (String t : directory.getTasks()) {
			addItem(t);
		}

		Anchor add_t = new Anchor("Add task...");
		add_t.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addTask ();
			}
		});
		addItem(add_t);
	}
}
