package treicco.client;

import java.util.List;

import treicco.shared.DirectoryProxy;
import treicco.shared.TaskProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainViewImpl extends Composite implements MainView {
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainViewImpl> {
	}
	
	MainPresenter presenter;

	@UiField
	Button addDirectoryButton;

	@UiField
	Button addTaskButton;

	@UiField
	FlowPanel parentsPanel;

	@UiField
	FlowPanel childrenPanel;

	@UiField
	FlowPanel tasksPanel;

	@UiField
	LayoutPanel mainPanel;

	interface DirectoryStyle extends CssResource {
		String Parent();

		String LastParent();

		String Child();

		String Task();
	}

	@UiField
	DirectoryStyle style;

	public MainViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setPresenter (MainPresenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("addDirectoryButton")
	public void addDirectory(ClickEvent e) {
		// addDirectoryDialog.init(this);
	}

	@UiHandler("addTaskButton")
	public void addTask(ClickEvent e) {
		// addTaskDialog.init(this);
	}

	//
	public void popParent() {
		parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).removeStyleName(style.LastParent());
		parentsPanel.remove(parentsPanel.getWidgetCount() - 1);
		parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).addStyleName(style.LastParent());
	}

	public void pushParent(String id, String shortName) {
		Hyperlink h = new Hyperlink(shortName, id);
		h.addStyleName(style.Parent());

		parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).removeStyleName(style.LastParent());
		parentsPanel.add(h);
		parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).addStyleName(style.LastParent());

	}

	public void setDirectories(List<DirectoryProxy> directories) {
		childrenPanel.clear();
		for (DirectoryProxy d : directories) {
			Hyperlink h = new Hyperlink(d.getShortName(), d.getParent() + d.getCodeName() + "/");
			h.addStyleName(style.Child());
			childrenPanel.add(h);
		}
	}

	public void setTasks(List<TaskProxy> tasks) {
		tasksPanel.clear();
		for (TaskProxy t : tasks) {
			Hyperlink h = new Hyperlink(t.getShortName(), t.getParent() + t.getCodeName());
			h.addStyleName(style.Task());
			tasksPanel.add(h);
		}
	}

	public void setWidget(IsWidget w) {
		mainPanel.clear();
		if (w != null) {
			mainPanel.add(w.asWidget());
			mainPanel.setWidgetLeftRight(w.asWidget(), 5, Unit.PX, 5, Unit.PX);
			mainPanel.setWidgetTopBottom(w.asWidget(), 5, Unit.PX, 5, Unit.PX);
		}
	}
	
	@UiHandler("addDirectoryButton")
	void addDirectoryClick(ClickEvent e) {
		presenter.addDirectory ();
	}
}
