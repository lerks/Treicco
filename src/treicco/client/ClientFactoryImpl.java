package treicco.client;

import java.util.logging.Logger;

import treicco.client.api.ClientFactory;
import treicco.client.api.CompetitionRequestFactory;
import treicco.client.api.CreationView;
import treicco.client.api.DirectoryView;
import treicco.client.api.MainView;
import treicco.client.api.NavigationView;
import treicco.client.api.TaskView;
import treicco.client.ui.CreationViewImpl;
import treicco.client.ui.DirectoryCreateImpl;
import treicco.client.ui.DirectoryDisplayImpl;
import treicco.client.ui.DirectoryUpdateImpl;
import treicco.client.ui.MainViewImpl;
import treicco.client.ui.NavigationViewImpl;
import treicco.client.ui.TaskCreateImpl;
import treicco.client.ui.TaskUpdateImpl;
import treicco.client.ui.TaskDisplayImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.logging.client.FirebugLogHandler;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory {

	private final Logger logger = Logger.getLogger("FirebugLogger");
	private final EventBus eventBus = new SimpleEventBus();
	private final CompetitionRequestFactory requestFactory = GWT.create(CompetitionRequestFactory.class);
	private final PlaceController placeController = new PlaceController(eventBus);
	private final MainView mainView = new MainViewImpl();
	private final NavigationView navigationView = new NavigationViewImpl();
	private final CreationView creationView = new CreationViewImpl();
	private final DirectoryView directoryView = new DirectoryDisplayImpl();
	private final DirectoryView directoryEdit = new DirectoryUpdateImpl();
	private final DirectoryView directoryCreate = new DirectoryCreateImpl();
	private final TaskView taskView = new TaskDisplayImpl();
	private final TaskView taskEdit = new TaskUpdateImpl();
	private final TaskView taskCreate = new TaskCreateImpl();

	public ClientFactoryImpl() {
		requestFactory.initialize(eventBus);
		logger.addHandler(new FirebugLogHandler());
	}

	public Logger getLogger() {
		return logger;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public CompetitionRequestFactory getRequestFactory() {
		return requestFactory;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}

	public MainView getMainView() {
		return mainView;
	}

	public NavigationView getNavigationView() {
		return navigationView;
	}

	public CreationView getCreationView() {
		return creationView;
	}

	public DirectoryView getDirectoryDisplay() {
		return directoryView;
	}

	public DirectoryView getDirectoryUpdate() {
		return directoryEdit;
	}

	public DirectoryView getDirectoryCreate() {
		return directoryCreate;
	}

	public TaskView getTaskDisplay() {
		return taskView;
	}

	public TaskView getTaskUpdate() {
		return taskEdit;
	}

	public TaskView getTaskCreate() {
		return taskCreate;
	}
}
