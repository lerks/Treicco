package treicco.client;

import treicco.client.activity.MainActivity;
import treicco.client.api.ClientFactory;
import treicco.client.place.DirectoryPlace;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

public class Treicco implements EntryPoint {

	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);

		MainActivity mainActivity = new MainActivity(clientFactory);
		mainActivity.start(new AcceptsOneWidget() {
			public void setWidget(IsWidget w) {
				RootPanel.get().clear();
				RootPanel.get().add(w);
			}
		}, clientFactory.getEventBus());

		// Start ActivityManager for the main widget with our ActivityMapper
		// ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		// ActivityManager activityManager = new ActivityManager(activityMapper,
		// eventBus);
		// activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppHistoryMapper historyMapper = new AppHistoryMapper();
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), DirectoryPlace.ROOT);

		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();
	}
}
