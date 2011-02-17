package treicco.client.activity;

import java.util.Set;

import treicco.client.api.ClientFactory;
import treicco.client.api.DirectoryPresenter;
import treicco.client.api.DirectoryProxy;
import treicco.client.api.DirectoryRequest;
import treicco.client.api.DirectoryView;
import treicco.client.place.DirectoryDisplayPlace;
import treicco.client.place.DirectoryUpdatePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DirectoryUpdater extends AbstractActivity implements DirectoryPresenter {

	ClientFactory clientFactory;

	DirectoryUpdatePlace place;

	DirectoryView directoryUpdate;

	private static DirectoryView.EditorDriver editorDriver = GWT.create(DirectoryView.EditorDriver.class);

	public DirectoryUpdater(ClientFactory clientFactory, DirectoryUpdatePlace place) {
		this.clientFactory = clientFactory;
		this.place = place;

		directoryUpdate = clientFactory.getDirectoryUpdate();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		directoryUpdate.setPresenter(this);

		editorDriver.initialize(directoryUpdate);

		Request<DirectoryProxy> fetchRequest = clientFactory.getRequestFactory().directoryRequest().findDirectory(place.getId());

		fetchRequest.with(editorDriver.getPaths());

		fetchRequest.to(new Receiver<DirectoryProxy>() {
			@Override
			public void onSuccess(DirectoryProxy response) {
				DirectoryRequest context = clientFactory.getRequestFactory().directoryRequest();
				context.update().using(response);

				editorDriver.edit(response, context);
			}
		}).fire();

		panel.setWidget(directoryUpdate);
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
				clientFactory.getPlaceController().goTo(new DirectoryDisplayPlace(place));
			}

			@Override
			public void onViolation(Set<Violation> errors) {
				clientFactory.getLogger().severe("Errors detected on the server: " + errors.iterator().next().getPath());
				editorDriver.setViolations(errors);
			}
		});
	}

	public void toDisplay() {
		clientFactory.getPlaceController().goTo(new DirectoryDisplayPlace(place));
	}

	public void toUpdate() {
		clientFactory.getPlaceController().goTo(new DirectoryUpdatePlace(place));
	}
}
