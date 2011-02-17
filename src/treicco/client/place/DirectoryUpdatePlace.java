package treicco.client.place;

public class DirectoryUpdatePlace extends DirectoryPlace {

	public DirectoryUpdatePlace(DirectoryPlace copy) {
		super(copy);
	}

	public DirectoryUpdatePlace(String id) {
		super(id);
	}

	public DirectoryUpdatePlace(DirectoryPlace parent, String codeName) {
		super(parent, codeName);
	}

	public DirectoryUpdatePlace(String parent, String codeName) {
		super(parent, codeName);
	}

	public boolean equals(Object target) {
		if (target instanceof DirectoryUpdatePlace) {
			return this.isEqual((DirectoryPlace) target);
		}
		return false;
	}
}
