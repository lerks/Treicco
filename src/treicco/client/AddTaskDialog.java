package treicco.client;

import treicco.shared.CompetitionSyntax;
import treicco.shared.TaskProxy;
import treicco.shared.TaskRequest;

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

public class AddTaskDialog {

	private static AddDirectoryDialogUiBinder uiBinder = GWT.create(AddDirectoryDialogUiBinder.class);

	interface AddDirectoryDialogUiBinder extends UiBinder<Widget, AddTaskDialog> {
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

	public AddTaskDialog() {
		dialog = (DialogBox) uiBinder.createAndBindUi(this);
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
		TaskRequest tr = Treicco.requestFactory.taskRequest();
		TaskProxy t = tr.create(TaskProxy.class);
		t.setShortName(codename.getText()); // FIXME shortname
		t.setFullName(fullname.getText());

		tr.create(listener.getPath(), codename.getText()).using(t).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void v) {
				History.newItem(CompetitionSyntax.composeTaskId(listener.getPath(), codename.getText()));
			}
		});

		dialog.hide();
	}

	@UiHandler("cancelButton")
	void cancelClick(ClickEvent e) {
		dialog.hide();
	}

	// void show() {
	// dialog.center();
	// }

	// void hide() {
	// dialog.hide();
	// }
}
