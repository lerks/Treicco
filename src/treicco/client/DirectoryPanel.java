package treicco.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class DirectoryPanel extends ResizeComposite {
	private final CompetitionManagerAsync competitionManager = GWT.create(CompetitionManager.class);

	private String path;

	private HTML title = new HTML ("title");
	
	private HTML add_directory = new HTML ("add D");

	private HTML add_task = new HTML ("add T");

	private AddDirectoryDialog add_directory_dialog = new AddDirectoryDialog();

	private AddTaskDialog add_task_dialog = new AddTaskDialog();

	public DirectoryPanel(String path, boolean showTitle) {
		LayoutPanel lp = new LayoutPanel ();
		
		if (showTitle) {
			lp.add(title);
			lp.setWidgetTopHeight(title, 0, Unit.PX, 24, Unit.PX);
			lp.setWidgetLeftRight(title, 0, Unit.PX, 0, Unit.PX);
		}
		
		lp.add(add_directory);
		lp.setWidgetBottomHeight(add_directory, 0, Unit.PX, 24, Unit.PX);
		lp.setWidgetLeftRight(add_directory, 0, Unit.PX, 50, Unit.PCT);
		
		lp.add(add_task);
		lp.setWidgetBottomHeight(add_task, 0, Unit.PX, 24, Unit.PX);
		lp.setWidgetLeftRight(add_task, 50, Unit.PCT, 0, Unit.PX);
		
		initWidget (lp);
		
		this.path = path;
		update();
	}

	public String getPath() {
		return path;
	}
	
	public void addDirectory () {
//		add_directory_dialog.init(this);
		add_directory_dialog.center();
	}
	
	public void addTask () {
//		add_task_dialog.init(this);
		add_task_dialog.center();
	}

	public void update() {
		competitionManager.get(path, new AsyncCallback<Directory>() {
			public void onFailure(Throwable caught) {
//				setText("ERROR_MESSAGE");
			}

			public void onSuccess(Directory result) {
				update(result);
			}
		});
	}

	public void update(Directory d) {
		title.setText(d.getId());

//		removeItems();
//
//		for (String d : directory.getDirectories()) {
//			addItem(new DirectoryView(path + d + "/"));
//		}
//
//		Anchor add_d = new Anchor("Add directory...");
//		add_d.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				addDirectory ();
//			}
//		});
//		addItem(add_d);
//
//		for (String t : directory.getTasks()) {
//			addItem(t);
//		}
//
//		Anchor add_t = new Anchor("Add task...");
//		add_t.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				addTask ();
//			}
//		});
//		addItem(add_t);
	}
}
