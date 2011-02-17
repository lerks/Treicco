package treicco.client.activity;

import treicco.client.api.ClientFactory;
import treicco.client.api.DirectoryPresenter;
import treicco.client.api.DirectoryProxy;
import treicco.client.api.DirectoryView;
import treicco.client.place.DirectoryDisplayPlace;
import treicco.client.place.DirectoryUpdatePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DirectoryDisplayer extends AbstractActivity implements DirectoryPresenter {

	ClientFactory clientFactory;

	DirectoryDisplayPlace place;

	DirectoryView directoryDisplay;

	private static DirectoryView.EditorDriver editorDriver = GWT.create(DirectoryView.EditorDriver.class);

	public DirectoryDisplayer(ClientFactory clientFactory, DirectoryDisplayPlace place) {
		this.clientFactory = clientFactory;
		this.place = place;

		directoryDisplay = clientFactory.getDirectoryDisplay();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		directoryDisplay.setPresenter(this);

		editorDriver.initialize(directoryDisplay);

		Request<DirectoryProxy> fetchRequest = clientFactory.getRequestFactory().directoryRequest().findDirectory(place.getId());

		fetchRequest.with(editorDriver.getPaths());

		fetchRequest.to(new Receiver<DirectoryProxy>() {
			@Override
			public void onSuccess(DirectoryProxy response) {
				editorDriver.display(response);
			}
		}).fire();

		panel.setWidget(directoryDisplay);
	}

	public void commit() {
	}

	public void toDisplay() {
		clientFactory.getPlaceController().goTo(new DirectoryDisplayPlace(place));
	}

	public void toUpdate() {
		clientFactory.getPlaceController().goTo(new DirectoryUpdatePlace(place));
	}
}
