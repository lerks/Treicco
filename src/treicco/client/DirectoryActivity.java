package treicco.client;

import java.util.Set;

import treicco.shared.DirectoryProxy;
import treicco.shared.DirectoryRequest;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DirectoryActivity extends AbstractActivity implements DirectoryPresenter {

	ClientFactory clientFactory;
	DirectoryPlace place;
	AcceptsOneWidget panel;

	DirectoryView directoryView;
	DirectoryView directoryEdit;

	private static DirectoryView.EditorDriver editorDriver = GWT.create(DirectoryView.EditorDriver.class);

	public DirectoryActivity(DirectoryPlace place, ClientFactory clientFactory) {
		this.place = place;
		this.clientFactory = clientFactory;
		directoryView = clientFactory.getDirectoryView();
		directoryView.setPresenter(this);
		directoryEdit = clientFactory.getDirectoryEdit();
		directoryEdit.setPresenter(this);
	}

	public void start(AcceptsOneWidget _panel, EventBus eventBus) {
		this.panel = _panel;

		if (place.getId().equals("/"))
			return;

		editorDriver.initialize(directoryView);

		Request<DirectoryProxy> fetchRequest = clientFactory.getRequestFactory().directoryRequest().findDirectory(place.getId());

		// fetchRequest.with(editorDriver.getPaths());

		fetchRequest.to(new Receiver<DirectoryProxy>() {
			@Override
			public void onSuccess(DirectoryProxy response) {
				editorDriver.display(response);

				panel.setWidget(directoryView);
			}
		}).fire();
	}

	public void startEdit() {
		editorDriver.initialize(directoryEdit);

		Request<DirectoryProxy> fetchRequest = clientFactory.getRequestFactory().directoryRequest().findDirectory(place.getId());

		// fetchRequest.with(editorDriver.getPaths());

		fetchRequest.to(new Receiver<DirectoryProxy>() {
			@Override
			public void onSuccess(DirectoryProxy response) {
				DirectoryRequest context = clientFactory.getRequestFactory().directoryRequest();
				context.update().using(response);

				editorDriver.edit(response, context);

				panel.setWidget(directoryEdit);
			}
		}).fire();
	}

	public void stopEdit() {
		panel.setWidget(directoryView);
	}

	public void commitEdit() {
		RequestContext context = editorDriver.flush();

		if (editorDriver.hasErrors()) {
			clientFactory.getLogger().severe("Errors detected locally");
			return;
		}

		context.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				stopEdit();
			}

			@Override
			public void onViolation(Set<Violation> errors) {
				clientFactory.getLogger().severe("Errors detected on the server: "+errors.iterator().next().getPath());
				editorDriver.setViolations(errors);
			}
		});
	}
}
