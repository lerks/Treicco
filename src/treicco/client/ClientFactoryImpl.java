package treicco.client;

import java.util.logging.Logger;

import treicco.shared.CompetitionRequestFactory;

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
	private final DirectoryView directoryView = new DirectoryViewImpl();
	private final DirectoryView directoryEdit = new DirectoryEditImpl();
	private final TaskView taskView = new TaskViewImpl();
	private final TaskView taskEdit = new TaskEditImpl(this);

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

	public DirectoryView getDirectoryView() {
		return directoryView;
	}

	public DirectoryView getDirectoryEdit() {
		return directoryEdit;
	}

	public TaskView getTaskView() {
		return taskView;
	}

	public TaskView getTaskEdit() {
		return taskEdit;
	}
}
