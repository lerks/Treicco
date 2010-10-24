package treicco.client;

import java.util.ArrayList;

import treicco.shared.Directory;
import treicco.shared.Task;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * LCRUD interface for Directories and Tasks
 * 
 * @author luca
 * 
 */
public interface TaskManagerAsync {
	void listDirectories(String targetPath, AsyncCallback<ArrayList<Directory>> callback);

	void listTasks(String targetPath, AsyncCallback<ArrayList<Task>> callback);

	void createDirectory(Directory d, AsyncCallback<Void> callback);

	void createTask(Task d, AsyncCallback<Void> callback);

	void readDirectory(String targetPath, AsyncCallback<Directory> callback);

	void readTask(String targetPath, AsyncCallback<Task> callback);

	void deleteDirectory(Directory d, AsyncCallback<Void> callback);

	void deleteTask(Task d, AsyncCallback<Void> callback);

	void init(AsyncCallback<Void> callback);

	void reset(AsyncCallback<Void> callback);
}
