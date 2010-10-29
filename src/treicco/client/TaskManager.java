package treicco.client;

import java.util.ArrayList;

import treicco.shared.Task;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * RESTful interface for LCRUD operations on Tasks
 * 
 * @author Luca Wehrstedt
 * 
 */
@RemoteServiceRelativePath("rest/task")
public interface TaskManager extends RemoteService {
	/**
	 * Get a list of Tasks from the specified path
	 * 
	 * @param targetPath the path where the list is located
	 * @return the list of Tasks
	 */
	ArrayList<Task> list(String targetPath);
	
	/**
	 * Get a Task from the specified path
	 * 
	 * @param targetPath the path where the Task is located
	 * @return the Task
	 */
	Task read(String targetPath);
	
	/**
	 * Create a Task
	 * 
	 * @param task the Task to be created
	 */
	void create(Task task);
	
	/**
	 * Update a Task
	 * 
	 * @param task the Task to be updated
	 */
	void update(Task task);

	/**
	 * Delete a Task
	 * 
	 * @param task the Task to be deleted
	 */
	void delete(Task task);

	/**
	 * Create a root Task if it doesn't exists
	 */
	void init ();
	
	/**
	 * Delete all Tasks except the root
	 */
	void reset ();
}
