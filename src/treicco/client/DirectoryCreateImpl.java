package treicco.client;

import treicco.shared.DirectoryProxy;
import treicco.shared.DirectoryRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class DirectoryCreateImpl {

	interface DirectoryCreateImplUiBinder extends UiBinder<Widget, DirectoryCreateImpl> {
	}

	private static DirectoryCreateImplUiBinder uiBinder = GWT.create(DirectoryCreateImplUiBinder.class);

	ClientFactory clientFactory;

	@UiField
	TextBox codename;

	@UiField
	Button okButton;

	@UiField
	Button cancelButton;

	// @UiField
	DialogBox dialog;

	public DirectoryCreateImpl(ClientFactory clientFactory) {
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
		DirectoryRequest dr = clientFactory.getRequestFactory().directoryRequest();
		DirectoryProxy d = dr.create(DirectoryProxy.class);
		d.setShortName(codename.getText());
		d.setFullName(codename.getText());

		dr.create(place.getId(), codename.getText()).using(d).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void v) {
				History.newItem(place.getChild(codename.getText()).getId());
			}
		});

		dialog.hide();
	}

	@UiHandler("cancelButton")
	void cancelClick(ClickEvent e) {
		dialog.hide();
	}
}
