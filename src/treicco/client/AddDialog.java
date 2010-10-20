package treicco.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AddDialog extends DialogBox {
	private TextInputListener listener;
	
	private TextBox textbox = new TextBox();
	
	public AddDialog(String title, TextInputListener l) {
		super (false);
		
		this.listener = l;
		
		setText(title);
		
		setAnimationEnabled(true);
		setGlassEnabled(true);
		
		Button ok = new Button("Add");
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide ();
				listener.gotTextInput(textbox.getText());
			}
		});
		Button cancel = new Button("Cancel");
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide ();
			}
		});
		
		LayoutPanel p = new LayoutPanel ();
		
		p.add(textbox);
		textbox.setWidth("180px");
		p.setWidgetTopHeight(textbox, 0, Unit.PX, 50, Unit.PX);
		p.setWidgetLeftRight(textbox, 0, Unit.PX, 0, Unit.PX);
		p.add(ok);
		p.setWidgetTopHeight(ok, 50, Unit.PX, 50, Unit.PX);
		p.setWidgetLeftRight(ok, 0, Unit.PX, 90, Unit.PX);
		p.add(cancel);
		p.setWidgetTopHeight(cancel, 50, Unit.PX, 50, Unit.PX);
		p.setWidgetLeftRight(cancel, 90, Unit.PX, 180, Unit.PX);
		
		p.setWidth("180px");
		p.setHeight("100px");
		
		setWidget(p);
	}
}
