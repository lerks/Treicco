package treicco.client;

import java.util.List;

import treicco.client.ui.widgets.ImageList;
import treicco.client.ui.widgets.TextBox;
import treicco.shared.ImageProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class TaskEditImpl extends Composite implements TaskView {

	interface TaskEditUiBinder extends UiBinder<Widget, TaskEditImpl> {
	}

	private static TaskEditUiBinder uiBinder = GWT.create(TaskEditUiBinder.class);

	TaskPresenter presenter;

	@UiField
	TextBox shortName;

	@UiField
	TextBox fullName;

	@UiField
	TextBox timeLimit;

	@UiField
	TextBox memoryLimit;

	@UiField
	TextBox author;

	@UiField
	TextBox difficulty;

	@UiField
	TextArea description;

	@UiField
	ImageList images;

	@UiField
	TextArea input;

	@UiField
	TextArea output;

	@UiField
	TextArea constraints;

	@UiField
	TextArea notes;

	public TaskEditImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;

		if (images != null) {
			images.setPresenter(presenter);
		}
	}

	@UiHandler("commit_top")
	public void commitChangesTop(ClickEvent e) {
		presenter.commitEdit();
	}

	@UiHandler("commit_bottom")
	public void commitChangesBottom(ClickEvent e) {
		presenter.commitEdit();
	}

	@UiHandler("cancel_top")
	public void cancelChangesTop(ClickEvent e) {
		presenter.stopEdit();
	}

	@UiHandler("cancel_bottom")
	public void cancelChangesBottom(ClickEvent e) {
		presenter.stopEdit();
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

	public LeafValueEditor<String> timeLimit() {
		return timeLimit.asEditor();
	}

	public LeafValueEditor<String> memoryLimit() {
		return memoryLimit.asEditor();
	}

	public LeafValueEditor<String> difficulty() {
		return difficulty.asEditor();
	}

	public LeafValueEditor<String> description() {
		return description.asEditor();
	}

	public LeafValueEditor<List<ImageProxy>> images() {
		return images;
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
