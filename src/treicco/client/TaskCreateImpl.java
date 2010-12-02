package treicco.client;

import treicco.shared.TaskProxy;
import treicco.shared.TaskRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TaskCreateImpl {

	private static TaskCreateImplUiBinder uiBinder = GWT.create(TaskCreateImplUiBinder.class);

	interface TaskCreateImplUiBinder extends UiBinder<Widget, TaskCreateImpl> {
	}

	ClientFactory clientFactory;

	@UiField
	TextBox codename;

	@UiField
	Button okButton;

	@UiField
	Button cancelButton;

	// @UiField
	DialogBox dialog;

	public TaskCreateImpl(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		dialog = (DialogBox) uiBinder.createAndBindUi(this);
	}

	DirectoryPlace place;

	void init(DirectoryPlace place) {
		this.place = place;
		this.codename.setText("");
		dialog.center();
	}

	@UiHandler("okButton")
	void okClick(ClickEvent e) {
		TaskRequest tr = clientFactory.getRequestFactory().taskRequest();
		TaskProxy t = tr.create(TaskProxy.class);
		t.setShortName(codename.getText());
		t.setFullName(codename.getText());

		tr.create(place.getId(), codename.getText()).using(t).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void v) {
				clientFactory.getPlaceController().goTo(new TaskPlace(place, codename.getText()));
			}
		});

		dialog.hide();
	}

	@UiHandler("cancelButton")
	void cancelClick(ClickEvent e) {
		dialog.hide();
	}
}
