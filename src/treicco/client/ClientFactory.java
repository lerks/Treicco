package treicco.client;

import treicco.shared.CompetitionRequestFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {

	EventBus getEventBus();

	CompetitionRequestFactory getRequestFactory();

	PlaceController getPlaceController();

	MainView getMainView();

	DirectoryView getDirectoryView();

	TaskView getTaskView();
}