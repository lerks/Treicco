package treicco.client.activity;

import treicco.client.api.ClientFactory;
import treicco.client.api.MainPresenter;
import treicco.client.api.MainView;
import treicco.client.place.DirectoryCreatePlace;
import treicco.client.place.DirectoryDisplayPlace;
import treicco.client.place.DirectoryPlace;
import treicco.client.place.DirectoryUpdatePlace;
import treicco.client.place.TaskCreatePlace;
import treicco.client.place.TaskDisplayPlace;
import treicco.client.place.TaskUpdatePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MainActivity extends AbstractActivity implements MainPresenter {

	ClientFactory clientFactory;

	DirectoryPlace place;

	MainView mainView;

	NavigationActivity navigationActivity;

	CreationActivity creationActivity;

	ActivityManager rootManager;

	public MainActivity(ClientFactory _clientFactory) {
		this.clientFactory = _clientFactory;

		mainView = clientFactory.getMainView();

		navigationActivity = new NavigationActivity(clientFactory);

		creationActivity = new CreationActivity(clientFactory);

		rootManager = new ActivityManager(new ActivityMapper() {
			public Activity getActivity(Place place) {
				if (place instanceof DirectoryCreatePlace) {
					return new DirectoryCreator(clientFactory, (DirectoryCreatePlace) place);
				} else if (place instanceof DirectoryUpdatePlace) {
					return new DirectoryUpdater(clientFactory, (DirectoryUpdatePlace) place);
				} else if (place instanceof DirectoryDisplayPlace) {
					return new DirectoryDisplayer(clientFactory, (DirectoryDisplayPlace) place);
				} else if (place instanceof TaskCreatePlace) {
					return new TaskCreator(clientFactory, (TaskCreatePlace) place);
				} else if (place instanceof TaskUpdatePlace) {
					return new TaskUpdater(clientFactory, (TaskUpdatePlace) place);
				} else if (place instanceof TaskDisplayPlace) {
					return new TaskDisplayer(clientFactory, (TaskDisplayPlace) place);
				} else {
					return null;
				}
			}
		}, clientFactory.getEventBus());
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		mainView.setPresenter(this);

		panel.setWidget(mainView);

		navigationActivity.start(mainView.getNavigationPanel(), clientFactory.getEventBus());

		creationActivity.start(mainView.getCreationPanel(), clientFactory.getEventBus());

		rootManager.setDisplay(mainView.getRootPanel());
	}
}
