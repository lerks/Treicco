package treicco.client.ui;

import java.util.List;

import treicco.client.api.ImageProxy;
import treicco.client.api.TaskPresenter;
import treicco.client.api.TaskView;
import treicco.client.ui.widgets.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TaskCreateImpl extends Composite implements TaskView {

	interface TaskCreateImplUiBinder extends UiBinder<Widget, TaskCreateImpl> {
	}

	private static TaskCreateImplUiBinder uiBinder = GWT.create(TaskCreateImplUiBinder.class);

	TaskPresenter presenter;

	@UiField
	TextBox codeName;

	public TaskCreateImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;
	}

	public void show(AcceptsOneWidget target) {
		target.setWidget(this);

		codeName.setValue("");
	}

	public LeafValueEditor<String> codeName() {
		return codeName.asEditor();
	}

	public LeafValueEditor<String> shortName() {
		return codeName.asEditor();
	}

	public LeafValueEditor<String> fullName() {
		return codeName.asEditor();
	}

	public LeafValueEditor<String> author() {
		return null;
	}

	public LeafValueEditor<String> timeLimit() {
		return null;
	}

	public LeafValueEditor<String> memoryLimit() {
		return null;
	}

	public LeafValueEditor<String> difficulty() {
		return null;
	}

	public LeafValueEditor<String> description() {
		return null;
	}

	public LeafValueEditor<List<ImageProxy>> images() {
		return null;
	}

	public LeafValueEditor<String> input() {
		return null;
	}

	public LeafValueEditor<String> output() {
		return null;
	}

	public LeafValueEditor<String> constraints() {
		return null;
	}

	public LeafValueEditor<String> notes() {
		return null;
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
