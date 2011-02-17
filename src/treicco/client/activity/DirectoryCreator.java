package treicco.client.activity;

import treicco.client.api.ClientFactory;
import treicco.client.api.DirectoryPresenter;
import treicco.client.api.DirectoryProxy;
import treicco.client.api.DirectoryRequest;
import treicco.client.api.DirectoryView;
import treicco.client.place.DirectoryCreatePlace;
import treicco.client.place.DirectoryDisplayPlace;
import treicco.client.place.DirectoryUpdatePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DirectoryCreator extends AbstractActivity implements DirectoryPresenter {

	ClientFactory clientFactory;

	DirectoryCreatePlace place;

	DirectoryView directoryCreate;

	// private static DirectoryView.EditorDriver editorDriver =
	// GWT.create(DirectoryView.EditorDriver.class);

	public DirectoryCreator(ClientFactory clientFactory, DirectoryCreatePlace place) {
		this.clientFactory = clientFactory;
		this.place = place;

		directoryCreate = clientFactory.getDirectoryCreate();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		directoryCreate.setPresenter(this);

		// editorDriver.initialize(directoryCreate);
		//
		// DirectoryProxy d =
		// clientFactory.getRequestFactory().directoryRequest().create(DirectoryProxy.class);
		//
		// DirectoryRequest context =
		// clientFactory.getRequestFactory().directoryRequest();
		// context.update().using(d);
		//		
		// editorDriver.edit(d, context);

		panel.setWidget(directoryCreate);
	}

	public void commit() {
		DirectoryRequest dr = clientFactory.getRequestFactory().directoryRequest();
		DirectoryProxy d = dr.create(DirectoryProxy.class);
		d.setShortName(directoryCreate.codeName().getValue());
		d.setFullName(directoryCreate.codeName().getValue());

		dr.create(place.getId(), directoryCreate.codeName().getValue()).using(d).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void v) {
				clientFactory.getPlaceController().goTo(new DirectoryDisplayPlace(place, directoryCreate.codeName().getValue()));
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
