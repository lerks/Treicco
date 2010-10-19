package treicco.client;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(detachable = "true")
public class Directory implements IsSerializable {
	public Directory() {
	}

	public Directory(String path, String name) {
		// Escape the name
		name = name.replaceAll("\\W", "_");
		this.id = path + name + "/";
	}

	public String getPath() {
		return id.substring(0, id.lastIndexOf('/', id.length() - 2) + 1);
	}

	public String getName() {
		return id.substring(id.lastIndexOf('/', id.length() - 2) + 1, id.length() - 1);
	}

	public String getId() {
		return id;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getLongName() {
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
