package treicco.client.place;

public class DirectoryCreatePlace extends DirectoryPlace {

	public DirectoryCreatePlace(DirectoryPlace copy) {
		super(copy);
	}

	public DirectoryCreatePlace(String id) {
		super(id);
	}

	public DirectoryCreatePlace(DirectoryPlace parent, String codeName) {
		super(parent, codeName);
	}

	public DirectoryCreatePlace(String parent, String codeName) {
		super(parent, codeName);
	}

	public boolean equals(Object target) {
		if (target instanceof DirectoryCreatePlace) {
			return this.isEqual((DirectoryPlace) target);
		}
		return false;
	}
}
