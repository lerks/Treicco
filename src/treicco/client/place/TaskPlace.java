package treicco.client.place;

import com.google.gwt.place.shared.Place;

public class TaskPlace extends Place {

	private DirectoryPlace parent;

	private String codeName;

	TaskPlace(TaskPlace copy) {
		this.parent = copy.getParent();
		this.codeName = copy.getCodeName();
	}

	TaskPlace(String id) {
		this.parent = new DirectoryPlace(id.substring(0, id.lastIndexOf("/", id.length() - 1) + 1));
		this.codeName = id.substring(id.lastIndexOf("/", id.length() - 1) + 1, id.length());
	}

	TaskPlace(DirectoryPlace parent, String codeName) {
		this.parent = parent;
		this.codeName = codeName;
	}

	TaskPlace(String parent, String codeName) {
		this.parent = new DirectoryPlace(parent);
		this.codeName = codeName;
	}

	public String getId() {
		if (parent == null)
			return codeName;
		else
			return parent.getId() + codeName;
	}

	public DirectoryPlace getParent() {
		return parent;
	}

	public String getCodeName() {
		return codeName;
	}

	public boolean isEqual(TaskPlace target) {
		return target.getId().equals(this.getId());
	}
}
