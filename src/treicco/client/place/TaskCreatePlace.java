package treicco.client.place;

public class TaskCreatePlace extends TaskPlace {

	public TaskCreatePlace(TaskPlace copy) {
		super(copy);
	}

	public TaskCreatePlace(String id) {
		super(id);
	}

	public TaskCreatePlace(DirectoryPlace parent, String codeName) {
		super(parent, codeName);
	}

	public TaskCreatePlace(String parent, String codeName) {
		super(parent, codeName);
	}

	public boolean equals(Object target) {
		if (target instanceof TaskCreatePlace) {
			return this.isEqual((TaskPlace) target);
		}
		return false;
	}
}
