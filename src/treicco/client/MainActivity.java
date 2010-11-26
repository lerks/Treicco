package treicco.client;

import java.util.List;

import treicco.shared.DirectoryProxy;
import treicco.shared.TaskProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
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
		GWT.log("Caught a PlaceChangeEvent");
		Place place = event.getNewPlace();

		if (event.getNewPlace() instanceof DirectoryPlace) {
			setPlace((DirectoryPlace) place);
		}
		if (place instanceof TaskPlace) {
			setPlace(((TaskPlace) place).getParent());
		}
	}

	public void setPlace(final DirectoryPlace newPlace) {
		if (place.equals(newPlace)) {
			showChildren();

		} else if (place.lessThan(newPlace)) {
			place = place.stepTowards(newPlace);

			clientFactory.getRequestFactory().directoryRequest().findDirectory(place.getParent().getId(), place.getCodeName()).fire(new Receiver<DirectoryProxy>() {
				@Override
				public void onSuccess(DirectoryProxy d) {
					mainView.pushParent(place.getId(), d.getShortName());

					setPlace(newPlace);
				}
			});

		} else {
			place = place.getParent();

			mainView.popParent();

			setPlace(newPlace);

		}
	}

	private void showChildren() {
		clientFactory.getRequestFactory().directoryRequest().listDirectories(place.getId()).fire(new Receiver<List<DirectoryProxy>>() {
			public void onSuccess(List<DirectoryProxy> response) {
				mainView.setDirectories(response);
			};
		});

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

}
