package treicco.client;

import com.google.gwt.place.shared.Place;

public class DirectoryPlace extends Place {

	public static final DirectoryPlace ROOT = new DirectoryPlace("/");

	private DirectoryPlace parent;

	private String codeName;

	public DirectoryPlace(String id) {
		if (id.equals("/")) {
			this.parent = null;
			this.codeName = "";
		} else {
			this.parent = new DirectoryPlace(id.substring(0, id.lastIndexOf("/", id.length() - 2) + 1));
			this.codeName = id.substring(id.lastIndexOf("/", id.length() - 2) + 1, id.length() - 1);
		}
	}

	public DirectoryPlace(String parent, String codeName) {
		this.parent = new DirectoryPlace(parent);
		this.codeName = codeName;
	}

	public DirectoryPlace(DirectoryPlace parent, String codeName) {
		this.parent = parent;
		this.codeName = codeName;
	}

	public String getId() {
		if (parent == null)
			return codeName + "/";
		else
			return parent.getId() + codeName + "/";
	}

	public DirectoryPlace getParent() {
		return parent;
	}

	public String getCodeName() {
		return codeName;
	}

	public DirectoryPlace getChild(String codeName) {
		return new DirectoryPlace(this, codeName);
	}

	public DirectoryPlace stepTowards(DirectoryPlace target) {
		return getChild(target.getId().substring(this.getId().length(), target.getId().indexOf("/", this.getId().length())));
	}

	public boolean equals(Object target) {
		if (target instanceof DirectoryPlace) {
			return this.isEqual((DirectoryPlace) target);
		}
		return false;
	}

	public boolean isAncestor(DirectoryPlace target) {
		return target.getId().startsWith(this.getId()) && !target.equals(this);
	}

	public boolean isAncestorOrEqual(DirectoryPlace target) {
		return target.getId().startsWith(this.getId());
	}

	public boolean isEqual(DirectoryPlace target) {
		return target.getId().equals(this.getId());
	}
}
