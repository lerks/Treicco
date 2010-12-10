package treicco.client;

import java.util.List;

import treicco.shared.DirectoryProxy;
import treicco.shared.TaskProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class MainViewImpl extends Composite implements MainView {

	interface MainViewUiBinder extends UiBinder<Widget, MainViewImpl> {
	}

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

	MainPresenter presenter;

	@UiField
	Button addDirectoryButton;

	@UiField
	Button addTaskButton;

	@UiField
	DivElement navigationPanel;

	@UiField
	FlowPanel parentsPanel;

	@UiField
	FlowPanel childrenPanel;

	@UiField
	FlowPanel tasksPanel;

	@UiField
	FlowPanel mainPanel;
	
	Widget child;

	int scroll = 0;

	interface DirectoryStyle extends CssResource {

		String Child();

		String Parent();

		String LastParent();

		String Directory();

		String Task();
	}

	@UiField
	DirectoryStyle style;

	public MainViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		mainPanel.getElement().getStyle().setPropertyPx("minHeight", Window.getClientHeight() - 110);

		Window.addResizeHandler(new ResizeHandler() {
			public void onResize(ResizeEvent event) {
				mainPanel.getElement().getStyle().setPropertyPx("minHeight", event.getHeight() - 110);
				if (child != null) {
					child.getElement().getStyle().setPropertyPx("minHeight", event.getHeight() - 110);
				}
			}
		});

		Window.addWindowScrollHandler(new ScrollHandler() {
			public void onWindowScroll(ScrollEvent event) {
				if (event.getScrollTop() > 100 && scroll <= 100) {
					navigationPanel.setAttribute("style", "position:fixed;");
				} else if (event.getScrollTop() < 100 && scroll >= 100) {
					navigationPanel.setAttribute("style", "position:absolute;");
				}
				scroll = event.getScrollTop();
			}
		});
	}

	public void setPresenter(MainPresenter presenter) {
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
		clearDirectories();
		for (DirectoryProxy d : directories) {
			Hyperlink h = new Hyperlink(d.getShortName(), d.getId());
			h.addStyleName(style.Directory());
			childrenPanel.add(h);
		}
	}

	public void clearDirectories() {
		childrenPanel.clear();
	}

	public void setTasks(List<TaskProxy> tasks) {
		clearTasks();
		for (TaskProxy t : tasks) {
			Hyperlink h = new Hyperlink(t.getShortName(), t.getId());
			h.addStyleName(style.Task());
			tasksPanel.add(h);
		}
	}

	public void clearTasks() {
		tasksPanel.clear();
	}

	public void setWidget(IsWidget w) {
		mainPanel.clear();
		if (w != null) {
			child = w.asWidget();
			mainPanel.add(child);
			child.addStyleName(style.Child());
			child.getElement().getStyle().setPropertyPx("minHeight", Window.getClientHeight() - 110);
		}
		else
		{
			child = null;
		}
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
