package treicco.client;

import treicco.shared.Directory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AddDirectoryDialog extends DialogBox implements ClickHandler {
	private final TaskManagerAsync competitionManager = GWT.create(TaskManager.class);
	
	private DirectoryPanel listener;
	
	private String parent = "/";
	
	private TextBox shortname = new TextBox();
	
	private TextBox longname = new TextBox();
	
	private Button confirm = new Button("Add");
	
	private Button cancel = new Button("Cancel");
	
	public AddDirectoryDialog() {
		super ();
		
		setText ("Add directory");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		setModal (true);
		
		confirm.addClickHandler(this);
		cancel.addClickHandler(this);
		
		VerticalPanel vp = new VerticalPanel ();
		vp.setSpacing (5);
		
		vp.add(new HTML("Codename"));
		vp.add(shortname);
		vp.add(new HTML("Name"));
		vp.add(longname);
		
		HorizontalPanel hp = new HorizontalPanel ();
		hp.setSpacing (5);
		
		hp.add(confirm);
		hp.add(cancel);
		
		vp.add(hp);
		
//		vp.setWidth("300px");
//		vp.setHeight("200px");
		
		setWidget(vp);
	}
	
	void init (DirectoryPanel dw) {
		this.listener = dw;
		this.parent = dw.getPath();
		this.shortname.setText("");
		this.longname.setText("");
	}

	public void onClick(ClickEvent event) {
		if (event.getSource() == confirm) {
			Directory d = new Directory (parent, shortname.getText());
			d.setFullName(longname.getText());
			
			competitionManager.createDirectory (d, new AsyncCallback<Void>() {
				public void onFailure(Throwable caught) {
					setText("ERROR_MESSAGE");
				}

				public void onSuccess(Void v) {
					listener.showChildren();
				}
			});
		}
		this.hide();
	}
}
