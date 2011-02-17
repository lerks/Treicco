package treicco.client.activity;

import java.util.ArrayList;
import java.util.List;

import treicco.client.api.ClientFactory;
import treicco.client.api.DirectoryProxy;
import treicco.client.api.NavigationPresenter;
import treicco.client.api.NavigationView;
import treicco.client.api.TaskProxy;
import treicco.client.place.DirectoryPlace;
import treicco.client.place.TaskPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class NavigationActivity extends AbstractActivity implements NavigationPresenter {

	ClientFactory clientFactory;

	DirectoryPlace dplace = DirectoryPlace.ROOT;
	TaskPlace tplace;

	NavigationView navigationView;

	public NavigationActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

		navigationView = clientFactory.getNavigationView();

		clientFactory.getEventBus().addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
			public void onPlaceChange(PlaceChangeEvent event) {
				Place place = event.getNewPlace();

				if (event.getNewPlace() instanceof DirectoryPlace) {
					setDirectory((DirectoryPlace) place);
				}
				if (place instanceof TaskPlace) {
					setTask((TaskPlace) place);
				}
			}
		});
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		navigationView.setPresenter(this);

		panel.setWidget(navigationView);
	}

	public void setDirectory(DirectoryPlace newPlace) {
		while (!dplace.isAncestorOrEqual(newPlace)) {
			dplace = dplace.getParent();
			navigationView.popParent();
		}

		List<String> children = new ArrayList<String>();

		while (!dplace.isEqual(newPlace)) {
			dplace = dplace.stepTowards(newPlace);
			children.add(dplace.getId());
		}

		if (!children.isEmpty()) {
			clientFactory.getRequestFactory().directoryRequest().findAllDirectories(children).fire(new Receiver<List<DirectoryProxy>>() {
				@Override
				public void onSuccess(List<DirectoryProxy> l) {
					for (DirectoryProxy d : l) {
						navigationView.pushParent(d.getId(), d.getShortName());
					}
				}
			});
		}

		showChildren();
	}

	public void setTask(TaskPlace newPlace) {
		setDirectory(newPlace.getParent());
		tplace = newPlace;
	}

	private void showChildren() {
		navigationView.clearDirectories();
		clientFactory.getRequestFactory().directoryRequest().listDirectories(dplace.getId()).fire(new Receiver<List<DirectoryProxy>>() {
			public void onSuccess(List<DirectoryProxy> response) {
				navigationView.setDirectories(response);
			};
		});

		navigationView.clearTasks();
		clientFactory.getRequestFactory().taskRequest().listTasks(dplace.getId()).fire(new Receiver<List<TaskProxy>>() {
			public void onSuccess(List<TaskProxy> response) {
				navigationView.setTasks(response);
			};
		});
	}
}
