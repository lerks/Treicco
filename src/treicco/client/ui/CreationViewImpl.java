package treicco.client.ui;

import treicco.client.api.CreationPresenter;
import treicco.client.api.CreationView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CreationViewImpl extends Composite implements CreationView {

	interface CreationViewUiBinder extends UiBinder<Widget, CreationViewImpl> {
	}

	private static CreationViewUiBinder uiBinder = GWT.create(CreationViewUiBinder.class);

	CreationPresenter presenter;

	@UiField
	Button addDirectoryButton;

	@UiField
	Button addTaskButton;

	public CreationViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(CreationPresenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("addDirectoryButton")
	void addDirectoryClick(ClickEvent e) {
		presenter.addDirectory();
	}

	@UiHandler("addTaskButton")
	void addTaskClick(ClickEvent e) {
		presenter.addTask();
	}
}
