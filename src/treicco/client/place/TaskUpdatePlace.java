package treicco.client.place;

public class TaskUpdatePlace extends TaskPlace {

	public TaskUpdatePlace(TaskPlace copy) {
		super(copy);
	}

	public TaskUpdatePlace(String id) {
		super(id);
	}

	public TaskUpdatePlace(DirectoryPlace parent, String codeName) {
		super(parent, codeName);
	}

	public TaskUpdatePlace(String parent, String codeName) {
		super(parent, codeName);
	}

	public boolean equals(Object target) {
		if (target instanceof TaskUpdatePlace) {
			return this.isEqual((TaskPlace) target);
		}
		return false;
	}
}
