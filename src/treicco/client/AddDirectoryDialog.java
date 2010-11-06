package treicco.client;

import treicco.shared.CompetitionSyntax;
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

public class AddDirectoryDialog {

	private static AddDirectoryDialogUiBinder uiBinder = GWT.create(AddDirectoryDialogUiBinder.class);

	interface AddDirectoryDialogUiBinder extends UiBinder<Widget, AddDirectoryDialog> {
	}

	private DirectoryPanel listener;

	@UiField
	TextBox codename;

	@UiField
	TextBox fullname;

	@UiField
	Button okButton;

	@UiField
	Button cancelButton;

	// @UiField
	DialogBox dialog;

	public AddDirectoryDialog() {
		dialog = (DialogBox)uiBinder.createAndBindUi(this);
		// initWidget(dialog);
	}

	void init(DirectoryPanel dl) {
		this.listener = dl;
		this.codename.setText("");
		this.fullname.setText("");
		dialog.center();
	}

	@UiHandler("okButton")
	void okClick(ClickEvent e) {
		DirectoryRequest dr = Treicco.requestFactory.directoryRequest();
		DirectoryProxy d = dr.create(DirectoryProxy.class);
		d.setName(fullname.getText());

		dr.create(CompetitionSyntax.composeDirectoryId(listener.getPath(), codename.getText())).using(d).fire (new Receiver<Void>()
		{
			@Override
			public void onSuccess(Void v)
			{
				History.newItem(CompetitionSyntax.composeDirectoryId(listener.getPath(), codename.getText()));
			}
		});

		dialog.hide();
	}

	@UiHandler("cancelButton")
	void cancelClick(ClickEvent e) {
		dialog.hide();
	}

//	void show() {
//		dialog.center();
//	}

//	void hide() {
//		dialog.hide();
//	}
}
