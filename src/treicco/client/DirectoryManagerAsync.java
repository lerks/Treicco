package treicco.client;

import java.util.ArrayList;

import treicco.shared.Directory;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DirectoryManagerAsync {

	void create(Directory d, AsyncCallback<Void> callback);

	void delete(Directory d, AsyncCallback<Void> callback);

	void init(AsyncCallback<Void> callback);

	void list(String targetPath, AsyncCallback<ArrayList<Directory>> callback);

	void read(String targetPath, AsyncCallback<Directory> callback);

	void reset(AsyncCallback<Void> callback);

	void update(Directory d, AsyncCallback<Void> callback);

}
