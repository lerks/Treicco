package treicco.client.api;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface NavigationView extends IsWidget {

	void setPresenter(NavigationPresenter presenter);

	void pushParent(String id, String shortName);

	void popParent();

	void setDirectories(List<DirectoryProxy> directories);

	void clearDirectories();

	void setTasks(List<TaskProxy> tasks);

	void clearTasks();
}
