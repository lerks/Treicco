package treicco.client;

import treicco.shared.DirectoryProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DirectoryActivity extends AbstractActivity implements DirectoryPresenter {

	ClientFactory clientFactory;
	DirectoryPlace place;

	public DirectoryActivity(DirectoryPlace place, ClientFactory clientFactory) {
		this.place = place;
		this.clientFactory = clientFactory;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final DirectoryView directoryView = clientFactory.getDirectoryView();
		directoryView.setPresenter(this);

		if (place.getId().equals("/"))
			return;

		clientFactory.getRequestFactory().directoryRequest().findDirectory(place.getParent().getId(), place.getCodeName()).fire(new Receiver<DirectoryProxy>() {
			public void onSuccess(DirectoryProxy response) {
				directoryView.setFullName(response.getFullName());

				if (response.getStartDate() != null && response.getEndDate() != null) {
					directoryView.setDate(response.getStartDate().toString() + " to " + response.getEndDate().toString());
				}

				if (response.getLocation() != null && !response.getLocation().equals("")) {
					directoryView.setLocation(response.getLocation());
				}

				if (response.getWebsite() != null) {
					directoryView.setWebsite(response.getWebsite());
				}
			}
		});

		panel.setWidget(directoryView.asWidget());
	}
}
