package treicco.client;

import java.util.List;

import treicco.shared.ImageProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TaskViewImpl extends Composite implements TaskView {

	interface TaskViewUiBinder extends UiBinder<Widget, TaskViewImpl> {
	}

	private static TaskViewUiBinder uiBinder = GWT.create(TaskViewUiBinder.class);

	TaskPresenter presenter;

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
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> fullName() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> author() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> timelimit() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> memorylimit() {
		// TODO Auto-generated method stub
		return null;
	}

	public LeafValueEditor<String> difficulty() {
		// TODO Auto-generated method stub
		return null;
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
