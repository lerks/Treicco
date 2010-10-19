package treicco.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CompetitionManagerAsync {
	void get (String targetPath, AsyncCallback<Directory> callback);
	
	void add (Directory d, AsyncCallback<Void> callback);
	
	void remove (Directory d, AsyncCallback<Void> callback);
	
	void add (Task d, AsyncCallback<Void> callback);
	
	void remove (Task d, AsyncCallback<Void> callback);
}
