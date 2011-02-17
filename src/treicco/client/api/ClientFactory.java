package treicco.client.api;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {

	Logger getLogger();

	EventBus getEventBus();

	CompetitionRequestFactory getRequestFactory();

	PlaceController getPlaceController();

	MainView getMainView();

	NavigationView getNavigationView();

	CreationView getCreationView();

	DirectoryView getDirectoryDisplay();

	DirectoryView getDirectoryUpdate();

	DirectoryView getDirectoryCreate();

	TaskView getTaskDisplay();

	TaskView getTaskUpdate();

	TaskView getTaskCreate();
}