package treicco.client.place;

public class DirectoryDisplayPlace extends DirectoryPlace {

	public DirectoryDisplayPlace(DirectoryPlace copy) {
		super(copy);
	}

	public DirectoryDisplayPlace(String id) {
		super(id);
	}

	public DirectoryDisplayPlace(DirectoryPlace parent, String codeName) {
		super(parent, codeName);
	}

	public DirectoryDisplayPlace(String parent, String codeName) {
		super(parent, codeName);
	}

	public boolean equals(Object target) {
		if (target instanceof DirectoryDisplayPlace) {
			return this.isEqual((DirectoryPlace) target);
		}
		return false;
	}
}
