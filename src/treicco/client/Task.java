package treicco.client;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(detachable = "true")
public class Task implements IsSerializable {
	public Task() {
	}

	public Task(String path, String name) {
		// this.setShortName(shortName);
		this.id = path + name;
	}

	public String getPath() {
		return id.substring(0, id.lastIndexOf('/') + 1);
	}

	public String getName() {
		return id.substring(id.lastIndexOf('/') + 1);
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getLongName() {
		return longName;
	}

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String longName;
}
