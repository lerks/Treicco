package treicco.client;

import java.util.List;

import treicco.shared.ImageProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TaskEditImpl extends Composite implements TaskView {

	interface TaskEditUiBinder extends UiBinder<Widget, TaskEditImpl> {
	}

	private static TaskEditUiBinder uiBinder = GWT.create(TaskEditUiBinder.class);

	TaskPresenter presenter;

	// FIXME remove this form the View
	ClientFactory clientFactory;

	@UiField
	TextBox shortName;

	@UiField
	TextBox fullName;

	@UiField
	TextBox timelimit;

	@UiField
	TextBox memorylimit;

	@UiField
	TextBox author;

	@UiField
	TextBox difficulty;

	@UiField
	TextArea description;

	@UiField
	FormPanel image_form;

	@UiField
	TextArea input;

	@UiField
	TextArea output;

	@UiField
	TextArea constraints;

	@UiField
	TextArea notes;

	public TaskEditImpl(ClientFactory clientFactory) {
		initWidget(uiBinder.createAndBindUi(this));

		this.clientFactory = clientFactory;
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;

		Request<String> r = clientFactory.getRequestFactory().imageRequest().createUploadURL();

		r.to(new Receiver<String>() {
			@Override
			public void onSuccess(String response) {
				image_form.setAction(response);
				image_form.setEncoding(FormPanel.ENCODING_MULTIPART);
				image_form.setMethod(FormPanel.METHOD_POST);
			}
		}).fire();

		image_form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
				clientFactory.getLogger().severe(event.getResults());
			}
		});
	}

	@UiHandler("commit")
	public void commitChanges(ClickEvent e) {
		presenter.commitEdit();
	}

	@UiHandler("cancel")
	public void cancelChanges(ClickEvent e) {
		presenter.stopEdit();
	}

	@UiHandler("image_submit")
	public void submitImage(ClickEvent e) {
		image_form.submit();
	}

	public LeafValueEditor<String> shortName() {
		return shortName.asEditor();
	}

	public LeafValueEditor<String> fullName() {
		return fullName.asEditor();
	}

	public LeafValueEditor<String> author() {
		return author.asEditor();
	}

	public LeafValueEditor<String> timelimit() {
		return timelimit.asEditor();
	}

	public LeafValueEditor<String> memorylimit() {
		return memorylimit.asEditor();
	}

	public LeafValueEditor<String> difficulty() {
		return difficulty.asEditor();
	}

	public LeafValueEditor<String> description() {
		return description.asEditor();
	}

	public LeafValueEditor<List<ImageProxy>> images() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> input() {
		return input.asEditor();
	}

	public LeafValueEditor<String> output() {
		return output.asEditor();
	}

	public LeafValueEditor<String> constraints() {
		return constraints.asEditor();
	}

	public LeafValueEditor<String> notes() {
		return notes.asEditor();
	}
}
