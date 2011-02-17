package treicco.client.activity;

import treicco.client.api.ClientFactory;
import treicco.client.api.TaskPresenter;
import treicco.client.api.TaskProxy;
import treicco.client.api.TaskRequest;
import treicco.client.api.TaskView;
import treicco.client.place.TaskCreatePlace;
import treicco.client.place.TaskDisplayPlace;
import treicco.client.place.TaskPlace;
import treicco.client.place.TaskUpdatePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TaskCreator extends AbstractActivity implements TaskPresenter {

	ClientFactory clientFactory;

	TaskCreatePlace place;

	TaskView taskCreate;

	// private static TaskView.EditorDriver editorDriver =
	// GWT.create(TaskView.EditorDriver.class);

	public TaskCreator(ClientFactory clientFactory, TaskCreatePlace place) {
		this.clientFactory = clientFactory;
		this.place = place;

		taskCreate = clientFactory.getTaskCreate();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		taskCreate.setPresenter(this);

		panel.setWidget(taskCreate);
	}

	public void commit() {
		TaskRequest tr = clientFactory.getRequestFactory().taskRequest();
		TaskProxy t = tr.create(TaskProxy.class);
		t.setShortName(taskCreate.codeName().getValue());
		t.setFullName(taskCreate.codeName().getValue());

		tr.create(place.getParent().getId(), taskCreate.codeName().getValue()).using(t).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void v) {
				clientFactory.getPlaceController().goTo(new TaskDisplayPlace(place.getParent(), taskCreate.codeName().getValue()));
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
		// TODO Auto-generated method stub
		return null;
	}

	public void requestImageUploadUrl(Receiver<String> receiver) {
		// TODO Auto-generated method stub

	}
}
