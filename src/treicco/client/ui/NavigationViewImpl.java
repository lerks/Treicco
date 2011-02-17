package treicco.client.ui;

import java.util.List;

import treicco.client.api.DirectoryProxy;
import treicco.client.api.NavigationPresenter;
import treicco.client.api.NavigationView;
import treicco.client.api.TaskProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class NavigationViewImpl extends Composite implements NavigationView {

	interface NavigationViewUiBinder extends UiBinder<Widget, NavigationViewImpl> {
	}

	private static NavigationViewUiBinder uiBinder = GWT.create(NavigationViewUiBinder.class);

	NavigationPresenter presenter;

	@UiField
	FlowPanel parentsPanel;

	@UiField
	FlowPanel childrenPanel;

	@UiField
	FlowPanel tasksPanel;

	interface NavigationStyle extends CssResource {

		String Parent();

		String LastParent();

		String Directory();

		String Task();
	}

	@UiField
	NavigationStyle style;

	public NavigationViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(NavigationPresenter presenter) {
		this.presenter = presenter;
	}

	public void pushParent(String id, String shortName) {
		Hyperlink h = new Hyperlink(shortName, id);
		h.addStyleName(style.Parent());

		parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).removeStyleName(style.LastParent());
		parentsPanel.add(h);
		parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).addStyleName(style.LastParent());
	}

	public void popParent() {
		parentsPanel.getWidget(parentsPanel.getWidgetCount() - 1).removeStyleName(style.LastParent());
		parentsPanel.remove(parentsPanel.getWidgetCount() - 1);
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
}
