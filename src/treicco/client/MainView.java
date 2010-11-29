package treicco.client;

import java.util.List;

import treicco.shared.DirectoryProxy;
import treicco.shared.TaskProxy;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainView extends IsWidget, AcceptsOneWidget {

	void setPresenter(MainPresenter presenter);

	void pushParent(String id, String shortName);

	void popParent();

	void setDirectories(List<DirectoryProxy> directories);

	void clearDirectories();

	void setTasks(List<TaskProxy> tasks);

	void clearTasks();
}
