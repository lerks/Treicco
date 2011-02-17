package treicco.client.ui;

import java.util.List;

import treicco.client.api.ImageProxy;
import treicco.client.api.TaskPresenter;
import treicco.client.api.TaskView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TaskDisplayImpl extends Composite implements TaskView {

	interface TaskDisplayUiBinder extends UiBinder<Widget, TaskDisplayImpl> {
	}

	private static TaskDisplayUiBinder uiBinder = GWT.create(TaskDisplayUiBinder.class);

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

	public TaskDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;
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

	@UiHandler("edit")
	void cancelClick(ClickEvent e) {
		presenter.toUpdate();
	}
}
