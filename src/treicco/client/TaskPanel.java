package treicco.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class TaskPanel extends ResizeComposite {
	public TaskPanel () {
		LayoutPanel lp = new LayoutPanel ();
		
		DeckPanel dp = new DeckPanel ();
		
		lp.add (dp);
		lp.setWidgetLeftRight(dp, 5, Unit.PX, 0, Unit.PX);
		lp.setWidgetTopBottom(dp, 5, Unit.PX, 5, Unit.PX);
		
		lp.addStyleName("MainPanel");
		
		dp.addStyleName("TaskPanel");
		
		initWidget(lp);
	}
}
