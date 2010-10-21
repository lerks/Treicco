package treicco.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class AddDirectoryDialog extends DialogBox implements ClickHandler {
	private final CompetitionManagerAsync competitionManager = GWT.create(CompetitionManager.class);
	
	private DirectoryView listener;
	
	private String parent = "/";
	
	private TextBox shortname = new TextBox();
	
	private TextBox longname = new TextBox();
	
	private Button confirm = new Button("Add");
	
	private Button cancel = new Button("Cancel");
	
	public AddDirectoryDialog() {
		super (false);
		
		setText ("Add directory");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		
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
		
//		p.setWidth("180px");
//		p.setHeight("100px");
		
		setWidget(vp);
	}
	
	void init (DirectoryView dw) {
		this.listener = dw;
		this.parent = dw.getPath();
		this.shortname.setText("");
		this.longname.setText("");
	}

	public void onClick(ClickEvent event) {
		if (event.getSource() == confirm) {
			Directory d = new Directory (parent, shortname.getText());
			d.setLongName(longname.getText());
			
			competitionManager.add (d, new AsyncCallback<Void>() {
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
