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

	private TaskReadPanel taskpanel = new TaskReadPanel();

	private DirectoryWritePanel directorypanel = new DirectoryWritePanel();

	public MainPanel() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				GWT.log("MainPanel's History handler called with argument " + event.getValue());

				if (event.getValue().endsWith("/")) {
					// It's a directory
					directorypanel.setPath(event.getValue());
					deckpanel.showWidget(0);
				} else {
					// It's a task
					taskpanel.setPath(event.getValue());
					deckpanel.showWidget(1);
				}
			}
		});

		LayoutPanel lp = new LayoutPanel();

		lp.add(deckpanel);
		lp.setWidgetLeftRight(deckpanel, 5, Unit.PX, 0, Unit.PX);
		lp.setWidgetTopBottom(deckpanel, 5, Unit.PX, 5, Unit.PX);

		deckpanel.add(directorypanel);
		deckpanel.add(taskpanel);
		deckpanel.showWidget(0);

		lp.addStyleName("MainPanel");

		initWidget(lp);
	}
}
