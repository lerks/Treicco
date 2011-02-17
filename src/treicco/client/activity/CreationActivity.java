package treicco.client.activity;

import treicco.client.api.ClientFactory;
import treicco.client.api.CreationPresenter;
import treicco.client.api.CreationView;
import treicco.client.place.DirectoryCreatePlace;
import treicco.client.place.DirectoryPlace;
import treicco.client.place.TaskCreatePlace;
import treicco.client.place.TaskPlace;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;

public class CreationActivity extends Composite implements CreationPresenter {

	ClientFactory clientFactory;

	CreationView creationView;

	public CreationActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

		creationView = clientFactory.getCreationView();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		creationView.setPresenter(this);

		panel.setWidget(creationView);
	}

	public void addDirectory() {
		Place place = clientFactory.getPlaceController().getWhere();
		if (place instanceof DirectoryPlace) {
			DirectoryPlace dplace = (DirectoryPlace) place;
			clientFactory.getPlaceController().goTo(new DirectoryCreatePlace(dplace));
		} else if (place instanceof TaskPlace) {
			TaskPlace tplace = (TaskPlace) place;
			clientFactory.getPlaceController().goTo(new DirectoryCreatePlace(tplace.getParent()));
		}
	}

	public void addTask() {
		Place place = clientFactory.getPlaceController().getWhere();
		if (place instanceof DirectoryPlace) {
			DirectoryPlace dplace = (DirectoryPlace) place;
			clientFactory.getPlaceController().goTo(new TaskCreatePlace(dplace, ""));
		} else if (place instanceof TaskPlace) {
			TaskPlace tplace = (TaskPlace) place;
			clientFactory.getPlaceController().goTo(new TaskCreatePlace(tplace.getParent(), ""));
		}
	}
}
