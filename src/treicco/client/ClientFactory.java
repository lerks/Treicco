package treicco.client;

import java.util.logging.Logger;

import treicco.shared.CompetitionRequestFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {

	Logger getLogger();

	EventBus getEventBus();

	CompetitionRequestFactory getRequestFactory();

	PlaceController getPlaceController();

	MainView getMainView();

	DirectoryView getDirectoryView();

	DirectoryView getDirectoryEdit();

	TaskView getTaskView();

	TaskView getTaskEdit();
}