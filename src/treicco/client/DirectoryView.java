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

	public DirectoryView(String path) {
		this.path = path;
		updateData ();
	}
	
	private TextInputListener directory_input = new TextInputListener() {
		public void gotTextInput(String input) {
			Directory d = new Directory (path, input);
			competitionManager.add(d, new AsyncCallback<Void>() {
				public void onFailure(Throwable caught) {
					setText("ERROR_MESSAGE");
				}

				public void onSuccess(Void v) {
					updateData();
				}
			});
		}
	};
	
	private TextInputListener task_input = new TextInputListener() {
		public void gotTextInput(String input) {
			Task t = new Task (path, input);
			competitionManager.add(t, new AsyncCallback<Void>() {
				public void onFailure(Throwable caught) {
					setText("ERROR_MESSAGE");
				}

				public void onSuccess(Void v) {
					updateData();
				}
			});
		}
	};

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
		
		for (String d : directory.getDirectories()){
			addItem(new DirectoryView(path+d+"/"));
		}
		
		Anchor add_d = new Anchor ("Add directory...");
		add_d.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AddDialog ad = new AddDialog ("Add directory...", directory_input);
				ad.show ();
			}
		});
		addItem (add_d);
		
		for (String t : directory.getTasks()){
			addItem(t);
		}
		
		Anchor add_t = new Anchor ("Add task...");
		add_t.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AddDialog ad = new AddDialog ("Add task...", task_input);
				ad.show ();
			}
		});
		addItem (add_t);
	}
}
