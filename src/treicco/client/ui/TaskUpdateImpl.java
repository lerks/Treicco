package treicco.client.ui;

import java.util.List;

import treicco.client.api.ImageProxy;
import treicco.client.api.TaskPresenter;
import treicco.client.api.TaskView;
import treicco.client.ui.widgets.ImageList;
import treicco.client.ui.widgets.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class TaskUpdateImpl extends Composite implements TaskView {

	interface TaskUpdateUiBinder extends UiBinder<Widget, TaskUpdateImpl> {
	}

	private static TaskUpdateUiBinder uiBinder = GWT.create(TaskUpdateUiBinder.class);

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

	public TaskUpdateImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;

		if (images != null) {
			images.setPresenter(presenter);
		}
	}

	public void show(AcceptsOneWidget target) {
		target.setWidget(this);
	}

	public LeafValueEditor<String> codeName() {
		return null;
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

	@UiHandler("commit_top")
	public void commitChangesTop(ClickEvent e) {
		presenter.commit();
	}

	@UiHandler("commit_bottom")
	public void commitChangesBottom(ClickEvent e) {
		presenter.commit();
	}

	@UiHandler("cancel_top")
	public void cancelChangesTop(ClickEvent e) {
		presenter.toDisplay();
	}

	@UiHandler("cancel_bottom")
	public void cancelChangesBottom(ClickEvent e) {
		presenter.toDisplay();
	}
}
