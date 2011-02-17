package treicco.client.activity;

import java.util.Set;

import treicco.client.api.ClientFactory;
import treicco.client.api.TaskPresenter;
import treicco.client.api.TaskProxy;
import treicco.client.api.TaskRequest;
import treicco.client.api.TaskView;
import treicco.client.place.TaskDisplayPlace;
import treicco.client.place.TaskPlace;
import treicco.client.place.TaskUpdatePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TaskUpdater extends AbstractActivity implements TaskPresenter {

	ClientFactory clientFactory;

	TaskUpdatePlace place;

	TaskView taskUpdate;

	private static TaskView.EditorDriver editorDriver = GWT.create(TaskView.EditorDriver.class);

	public TaskUpdater(ClientFactory clientFactory, TaskUpdatePlace place) {
		this.clientFactory = clientFactory;
		this.place = place;

		taskUpdate = clientFactory.getTaskUpdate();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		taskUpdate.setPresenter(this);

		editorDriver.initialize(taskUpdate);

		Request<TaskProxy> fetchRequest = clientFactory.getRequestFactory().taskRequest().findTask(place.getId());

		fetchRequest.with(editorDriver.getPaths());

		fetchRequest.to(new Receiver<TaskProxy>() {
			@Override
			public void onSuccess(TaskProxy response) {
				TaskRequest context = clientFactory.getRequestFactory().taskRequest();
				context.update().using(response);

				editorDriver.edit(response, context);
			}
		}).fire();

		panel.setWidget(taskUpdate);
	}

	public void commit() {
		RequestContext context = editorDriver.flush();

		if (editorDriver.hasErrors()) {
			clientFactory.getLogger().severe("Errors detected locally");
			return;
		}

		context.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				clientFactory.getPlaceController().goTo(new TaskDisplayPlace(place));
			}

			@Override
			public void onViolation(Set<Violation> errors) {
				clientFactory.getLogger().severe("Errors detected on the server: " + errors.iterator().next().getPath());
				editorDriver.setViolations(errors);
			}
		});
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
