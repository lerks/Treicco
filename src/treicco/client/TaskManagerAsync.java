package treicco.client;

import java.util.ArrayList;

import treicco.shared.Task;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TaskManagerAsync {

	void create(Task task, AsyncCallback<Void> callback);

	void delete(Task task, AsyncCallback<Void> callback);

	void init(AsyncCallback<Void> callback);

	void list(String targetPath, AsyncCallback<ArrayList<Task>> callback);

	void read(String targetPath, AsyncCallback<Task> callback);

	void reset(AsyncCallback<Void> callback);

	void update(Task task, AsyncCallback<Void> callback);

}
