package treicco.client;

import java.util.List;

import treicco.shared.ImageProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TaskViewImpl extends Composite implements TaskView {

	interface TaskViewUiBinder extends UiBinder<Widget, TaskViewImpl> {
	}

	private static TaskViewUiBinder uiBinder = GWT.create(TaskViewUiBinder.class);

	TaskPresenter presenter;

	@UiField
	Label shortName;

	@UiField
	Label fullName;

	@UiField
	Label timeLimit;

	@UiField
	Label memoryLimit;

	@UiField
	Label author;

	@UiField
	Label difficulty;

	public TaskViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("edit")
	void cancelClick(ClickEvent e) {
		presenter.startEdit();
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
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<List<ImageProxy>> images() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> input() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> output() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> constraints() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> notes() {
		// TODO Auto-generated method stub
		return null;
	}
}
