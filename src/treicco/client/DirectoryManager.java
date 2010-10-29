package treicco.client;

import java.util.ArrayList;

import treicco.shared.Directory;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * RESTful interface for LCRUD operations on Directories
 * 
 * @author Luca Wehrstedt
 * 
 */
@RemoteServiceRelativePath("rest/directory")
public interface DirectoryManager extends RemoteService {
	/**
	 * Get a list of Directories from the specified path
	 * 
	 * @param targetPath the path where the list is located
	 * @return the list of Directories
	 */
	ArrayList<Directory> list(String targetPath);
	
	/**
	 * Get a Directory from the specified path
	 * 
	 * @param targetPath the path where the Directory is located
	 * @return the Directory
	 */
	Directory read(String targetPath);
	
	/**
	 * Create a Directory
	 * 
	 * @param directory the Directory to be created
	 */
	void create(Directory directory);
	
	/**
	 * Update a Directory
	 * 
	 * @param directory the Directory to be updated
	 */
	void update(Directory directory);

	/**
	 * Delete a Directory
	 * 
	 * @param directory the Directory to be deleted
	 */
	void delete(Directory directory);

	/**
	 * Create a root Directory if it doesn't exists
	 */
	void init ();
	
	/**
	 * Delete all Directories except the root
	 */
	void reset ();
}