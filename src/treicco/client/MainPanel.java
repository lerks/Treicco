package treicco.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class MainPanel extends ResizeComposite {
	private DeckPanel deckpanel = new DeckPanel();
	
	private TaskPanel taskpanel = new TaskPanel ();
	
	
	
	public MainPanel () {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				GWT.log("MainPanel's History handler called with argument " + event.getValue());

				taskpanel.setPath(event.getValue());
			}
		});
		
		LayoutPanel lp = new LayoutPanel ();
		
		lp.add (deckpanel);
		lp.setWidgetLeftRight(deckpanel, 5, Unit.PX, 0, Unit.PX);
		lp.setWidgetTopBottom(deckpanel, 5, Unit.PX, 5, Unit.PX);
		
		deckpanel.add(taskpanel);
		deckpanel.showWidget(0);
		
		lp.addStyleName("MainPanel");
		
		initWidget(lp);
	}
}
