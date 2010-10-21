package treicco.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AddTaskDialog extends DialogBox implements ClickHandler {
	private final CompetitionManagerAsync competitionManager = GWT.create(CompetitionManager.class);
	
	private DirectoryView listener;
	
	private String parent = "/";
	
	private TextBox shortname = new TextBox();
	
	private TextBox longname = new TextBox();
	
	private Button confirm = new Button("Add");
	
	private Button cancel = new Button("Cancel");
	
	public AddTaskDialog() {
		super (false);
		
		setTitle ("Add task");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		
		confirm.addClickHandler(this);
		cancel.addClickHandler(this);
		
		LayoutPanel p = new LayoutPanel ();
		
		p.add(shortname);
		shortname.setWidth("180px");
		p.setWidgetTopHeight(shortname, 0, Unit.PX, 50, Unit.PX);
		p.setWidgetLeftRight(shortname, 0, Unit.PX, 0, Unit.PX);
		p.add(confirm);
		p.setWidgetTopHeight(confirm, 50, Unit.PX, 50, Unit.PX);
		p.setWidgetLeftRight(confirm, 0, Unit.PX, 90, Unit.PX);
		p.add(cancel);
		p.setWidgetTopHeight(cancel, 50, Unit.PX, 50, Unit.PX);
		p.setWidgetLeftRight(cancel, 90, Unit.PX, 180, Unit.PX);
		
		p.setWidth("180px");
		p.setHeight("100px");
		
		setWidget(p);
	}
	
	void init (DirectoryView dw) {
		this.listener = dw;
		this.parent = dw.getPath();
		this.shortname.setText("");
		this.longname.setText("");
	}

	public void onClick(ClickEvent event) {
		if (event.getSource() == confirm) {
			Task t = new Task (parent, shortname.getText());
			t.setLongName(longname.getText());
			
			competitionManager.add (t, new AsyncCallback<Void>() {
				public void onFailure(Throwable caught) {
					setText("ERROR_MESSAGE");
				}

				public void onSuccess(Void v) {
					listener.updateData();
				}
			});
		}
		this.hide();
	}
}
