package treicco.client;

import java.util.ArrayList;
import java.util.List;

import treicco.shared.DirectoryProxy;
import treicco.shared.TaskProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MainActivity extends AbstractActivity implements MainPresenter, PlaceChangeEvent.Handler {

	ClientFactory clientFactory;
	DirectoryPlace place;

	MainView mainView;

	public MainActivity(DirectoryPlace place, ClientFactory clientFactory) {
		this.place = place;
		this.clientFactory = clientFactory;
		this.mainView = clientFactory.getMainView();
		this.mainView.setPresenter(this);
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		eventBus.addHandler(PlaceChangeEvent.TYPE, this);
	}

	public void onPlaceChange(PlaceChangeEvent event) {
		Place place = event.getNewPlace();

		if (event.getNewPlace() instanceof DirectoryPlace) {
			setPlace((DirectoryPlace) place);
		}
		if (place instanceof TaskPlace) {
			setPlace(((TaskPlace) place).getParent());
		}
	}

	public void setPlace(final DirectoryPlace newPlace) {
		while (!place.isAncestorOrEqual(newPlace)) {
			place = place.getParent();
			mainView.popParent();
		}

		List<String> children = new ArrayList<String>();

		while (!place.isEqual(newPlace)) {
			place = place.stepTowards(newPlace);
			children.add(place.getId());
		}

		if (!children.isEmpty()) {
			clientFactory.getRequestFactory().directoryRequest().findAllDirectories(children).fire(new Receiver<List<DirectoryProxy>>() {
				@Override
				public void onSuccess(List<DirectoryProxy> l) {
					for (DirectoryProxy d : l) {
						mainView.pushParent(d.getId(), d.getShortName());
					}
				}
			});
		}

		showChildren();
	}

	private void showChildren() {
		mainView.clearDirectories();
		clientFactory.getRequestFactory().directoryRequest().listDirectories(place.getId()).fire(new Receiver<List<DirectoryProxy>>() {
			public void onSuccess(List<DirectoryProxy> response) {
				mainView.setDirectories(response);
			};
		});

		mainView.clearTasks();
		clientFactory.getRequestFactory().taskRequest().listTasks(place.getId()).fire(new Receiver<List<TaskProxy>>() {
			public void onSuccess(List<TaskProxy> response) {
				mainView.setTasks(response);
			};
		});
	}

	public void addDirectory() {
		DirectoryCreateImpl dir = new DirectoryCreateImpl(clientFactory);
		dir.init(place);
	}

	public void addTask() {
		TaskCreateImpl task = new TaskCreateImpl(clientFactory);
		task.init(place);
	}
}
