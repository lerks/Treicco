package treicco.client.place;

public class TaskDisplayPlace extends TaskPlace {

	public TaskDisplayPlace(TaskPlace copy) {
		super(copy);
	}

	public TaskDisplayPlace(String id) {
		super(id);
	}

	public TaskDisplayPlace(DirectoryPlace parent, String codeName) {
		super(parent, codeName);
	}

	public TaskDisplayPlace(String parent, String codeName) {
		super(parent, codeName);
	}

	public boolean equals(Object target) {
		if (target instanceof TaskDisplayPlace) {
			return this.isEqual((TaskPlace) target);
		}
		return false;
	}
}
