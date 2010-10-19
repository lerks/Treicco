package treicco.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("competition")
public interface CompetitionManager extends RemoteService {
	Directory get(String targetPath);

	void add(Directory d);

	void remove(Directory d);

	void add(Task d);

	void remove(Task d);
}
