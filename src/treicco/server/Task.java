package treicco.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(detachable = "true")
public class Task {
	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String name;

	@Persistent
	private Text description;

	private static final Logger log = Logger.getLogger(Directory.class.getName());

	public Task() {

	}

	public Integer getVersion() {
		return new Integer(1);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = new Text(description);
	}

	public String getDescription() {
		return description.getValue();
	}

	public static Task findEntity(String id) {
		return read(id);
	}

	public static Task read(String targetPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("Requested Directory " + targetPath);

		// Directory d = pm.detachCopy(pm.getObjectById(Directory.class,
		// targetPath));

		Task t1;
		Query query = pm.newQuery(Task.class);
		query.setFilter("id == targetKey");
		query.declareParameters("String targetKey");
		List<Task> results = (List<Task>) query.execute(targetPath);
		t1 = results.get(0);
		Task t = pm.detachCopy(t1);

		pm.close();

		// d.setDirectories(null);
		// d.setTasks(null);

		return t;
	}

	public static List<Task> listChildren(String targetPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("Requested Directories in " + targetPath);

		// Directory d = pm.detachCopy(pm.getObjectById(Directory.class,
		// targetPath));

		Directory d;
		Query query = pm.newQuery(Directory.class);
		query.setFilter("id == targetKey");
		query.declareParameters("String targetKey");
		List<Directory> results = (List<Directory>) query.execute(targetPath);
		d = results.get(0);

		ArrayList<Task> result = new ArrayList<Task>();

		for (String child : d.getTasks()) {
			result.add(pm.detachCopy(pm.getObjectById(Task.class, targetPath + child + "/")));
		}

		pm.close();

		return result;
	}

	public void create(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			this.id = id;

			// Directory parent = pm.getObjectById(Directory.class,
			// this.getParent());
			//
			// ArrayList<String> parent_children = parent.directories;
			// parent_children.add(d.getName());
			// parent.setDirectories(parent_children);

			pm.makePersistent(this);

			// log.info("Succesful creation of Directory " + d.getName() +
			// " in " + d.getParent());
		} finally {
			pm.close();
		}
	}

	public void update() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			// DirectoryProxy parent = pm.getObjectById(DirectoryProxy.class,
			// d.getParent());
			//
			// ArrayList<String> parent_children = parent.getDirectories();
			// parent_children.remove(d.getName());
			// parent.setDirectories(parent_children);

			pm.deletePersistent(this);

			// log.info("Succesful deletion of Directory " + d.getName() +
			// " in " + d.getParent());
		} finally {
			pm.close();
		}
	}

	public void delete() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			// DirectoryProxy parent = pm.getObjectById(DirectoryProxy.class,
			// d.getParent());
			//
			// ArrayList<String> parent_children = parent.getDirectories();
			// parent_children.remove(d.getName());
			// parent.setDirectories(parent_children);

			pm.deletePersistent(this);

			// log.info("Succesful deletion of Directory " + d.getName() +
			// " in " + d.getParent());
		} finally {
			pm.close();
		}
	}
}
