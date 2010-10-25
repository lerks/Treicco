package treicco.client;

import treicco.shared.Directory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddDirectoryDialog {

	private static AddDirectoryDialogUiBinder uiBinder = GWT.create(AddDirectoryDialogUiBinder.class);

	interface AddDirectoryDialogUiBinder extends UiBinder<Widget, AddDirectoryDialog> {
	}

	private final TaskManagerAsync competitionManager = GWT.create(TaskManager.class);

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
		Directory d = new Directory(listener.getPath(), codename.getText());
		d.setFullName(fullname.getText());

		competitionManager.createDirectory(d, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				// setText("ERROR_MESSAGE");
			}

			public void onSuccess(Void v) {
				listener.showChildren();
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
