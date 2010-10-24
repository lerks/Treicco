package treicco.shared;

import javax.jdo.annotations.PersistenceCapable;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Gets the path of the Directory which contains the given Task
 * 
 * @param task
 *            A path of a Task
 * @return The path of the Directory containing the Task
 */

@PersistenceCapable(detachable = "true")
public class Task implements IsSerializable {
	public Task() {
	}

	public Task(String parent, String name) {
		// Escape the name
		name = name.replaceAll("\\W", "_");
		this.id = parent + name;
	}

	/**
	 * Gets the path of the parent of the given Directory
	 * 
	 * @param task
	 *            A path of a Directory
	 * @return The path of the parent of the Directory
	 */
	public static String getParent(String path) {
		return path.substring(0, path.lastIndexOf('/') + 1);
	}

	public String getParent() {
		return getParent(id);
	}

	/**
	 * Gets the name of the given Directory
	 * 
	 * @param task
	 *            A path of a Directory
	 * @return The name of the Directory
	 */
	public static String getName(String path) {
		return path.substring(path.lastIndexOf('/') + 1);
	}

	public String getName() {
		return getName(id);
	}

	public String getId() {
		return id;
	}

	public void setFullName(String longName) {
		this.longName = longName;
	}

	public String getFullName() {
		return longName;
	}

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String longName;
}
