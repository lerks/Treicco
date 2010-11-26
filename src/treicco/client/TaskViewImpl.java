package treicco.client;

import treicco.client.DirectoryViewImpl.DirectoryViewUiBinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TaskViewImpl extends Composite implements TaskView {
	private static TaskViewUiBinder uiBinder = GWT.create(TaskViewUiBinder.class);

	interface TaskViewUiBinder extends UiBinder<Widget, TaskViewImpl> {
	}

	TaskPresenter presenter;

	@UiField
	Label fullName;

	@UiField
	Label date;

	@UiField
	Label location;

	@UiField
	Label website;

	public TaskViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(TaskPresenter presenter) {
		this.presenter = presenter;
	}

	public void setShortName(String shortName) {
		// Nothing...
	}

	public void setFullName(String fullName) {
		this.fullName.setText(fullName);
	}
}
