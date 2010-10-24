package treicco.client;

import java.util.ArrayList;

import treicco.shared.Directory;
import treicco.shared.Task;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * LCRUD interface for Directories and Tasks
 * 
 * @author luca
 * 
 */
@RemoteServiceRelativePath("task")
public interface TaskManager extends RemoteService {
	ArrayList<Directory> listDirectories(String targetPath);

	ArrayList<Task> listTasks(String targetPath);

	Directory readDirectory(String targetPath);

	Task readTask(String targetPath);

	void createDirectory(Directory d);

	void createTask(Task d);

	void deleteDirectory(Directory d);

	void deleteTask(Task d);
	
	void init ();
	
	void reset ();
}
