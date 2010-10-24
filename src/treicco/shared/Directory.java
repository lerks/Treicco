package treicco.shared;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(detachable = "true")
public class Directory implements IsSerializable {
	public Directory() {
	}

	public Directory(String parent, String name) {
		// Escape the name
		name = name.replaceAll("\\W", "_");
		this.id = parent + name + "/";
	}

	/**
	 * Gets the path of the parent of the given Directory
	 * 
	 * @param task
	 *            A path of a Directory
	 * @return The path of the parent of the Directory
	 */
	public static String getParent(String path) {
		return path.substring(0, path.lastIndexOf('/', path.length() - 2) + 1);
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
		return path.substring(path.lastIndexOf('/', path.length() - 2) + 1, path.length() - 1);
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

	public void setDirectories(ArrayList<String> directories) {
		this.directories = directories;
	}

	public ArrayList<String> getDirectories() {
		return directories;
	}

	public void setTasks(ArrayList<String> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<String> getTasks() {
		return tasks;
	}

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String longName;

	@Persistent
	private ArrayList<String> directories;

	@Persistent
	private ArrayList<String> tasks;
}
