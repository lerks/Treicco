package treicco.client.activity;

import treicco.client.api.ClientFactory;
import treicco.client.api.TaskPresenter;
import treicco.client.api.TaskProxy;
import treicco.client.api.TaskView;
import treicco.client.place.TaskDisplayPlace;
import treicco.client.place.TaskPlace;
import treicco.client.place.TaskUpdatePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TaskDisplayer extends AbstractActivity implements TaskPresenter {

	ClientFactory clientFactory;

	TaskDisplayPlace place;

	TaskView taskDisplay;

	private static TaskView.EditorDriver editorDriver = GWT.create(TaskView.EditorDriver.class);

	public TaskDisplayer(ClientFactory clientFactory, TaskDisplayPlace place) {
		this.clientFactory = clientFactory;
		this.place = place;

		taskDisplay = clientFactory.getTaskDisplay();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		taskDisplay.setPresenter(this);

		editorDriver.initialize(taskDisplay);

		Request<TaskProxy> fetchRequest = clientFactory.getRequestFactory().taskRequest().findTask(place.getId());

		fetchRequest.with(editorDriver.getPaths());

		fetchRequest.to(new Receiver<TaskProxy>() {
			@Override
			public void onSuccess(TaskProxy response) {
				editorDriver.display(response);
			}
		}).fire();

		panel.setWidget(taskDisplay);
	}

	public void commit() {
	}

	public void toDisplay() {
		clientFactory.getPlaceController().goTo(new TaskDisplayPlace(place));
	}

	public void toUpdate() {
		clientFactory.getPlaceController().goTo(new TaskUpdatePlace(place));
	}

	public TaskPlace getPlace() {
		return place;
	}

	public void requestImageUploadUrl(Receiver<String> receiver) {
		Request<String> r = clientFactory.getRequestFactory().imageRequest().createUploadURL();

		r.to(receiver).fire();
	}
}