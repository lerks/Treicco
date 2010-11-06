package treicco.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import treicco.shared.CompetitionSyntax;

import com.google.appengine.api.datastore.Link;

@PersistenceCapable(detachable = "true")
public class Directory {
	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String name;

	@Persistent
	private String longName;

	@Persistent
	private ArrayList<String> directories;

	@Persistent
	private ArrayList<String> tasks;

	@Persistent
	private Date startDate;

	@Persistent
	private Date endDate;

	@Persistent
	private String location;

	@Persistent
	private Link website;

	private static final Logger log = Logger.getLogger(Directory.class.getName());

	public Directory() {

	}

	public Integer getVersion() {
		return new Integer(1);
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

	public void setFullName(String fullname) {
		this.longName = fullname;
	}

	public String getFullName() {
		return longName;
	}

	public ArrayList<String> getDirectories() {
		return directories;
	}

	public void setDirectories(ArrayList<String> directories) {
		this.directories = directories;
	}

	public ArrayList<String> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<String> tasks) {
		this.tasks = tasks;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWebsite() {
		if (website == null)
			return null;
		return website.getValue();
	}

	public void setWebsite(String website) {
		this.website = new Link(website);
	}

	public static Directory findDirectory(String targetPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("Requested Directory " + targetPath);

		// Directory d = pm.detachCopy(pm.getObjectById(Directory.class,
		// targetPath));

		Directory d1;
		Query query = pm.newQuery(Directory.class);
		query.setFilter("id == targetKey");
		query.declareParameters("String targetKey");
		List<Directory> results = (List<Directory>) query.execute(targetPath);
		d1 = results.get(0);
		Directory d = pm.detachCopy(d1);

		pm.close();

		// d.setDirectories(null);
		// d.setTasks(null);

		return d;
	}

	public static List<Directory> listChildren(String targetPath) {
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

		ArrayList<Directory> result = new ArrayList<Directory>();

		for (String child : d.getDirectories()) {
			result.add(pm.detachCopy(pm.getObjectById(Directory.class, targetPath + child + "/")));
		}

		pm.close();

		return result;
	}

	public static List<Task> listTasks(String targetPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("Requested Tasks in " + targetPath);

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
	
	public static void init () {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("Init");

		// Directory d = pm.detachCopy(pm.getObjectById(Directory.class,
		// targetPath));

		Query query = pm.newQuery(Directory.class);
		query.setFilter("id == targetKey");
		query.declareParameters("String targetKey");
		List<Directory> results = (List<Directory>) query.execute("/");
		
		if (results.size() == 0)
		{
			Directory d = new Directory ();
			d.id = "/";
			d.name = "Home Page";
			pm.makePersistent(d);
		}

		pm.close();
	}
	
	public void create(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			this.id = id;

			Directory parent = pm.getObjectById(Directory.class, CompetitionSyntax.extractParent(this.getId()));

			ArrayList<String> parent_children = parent.directories;
			parent_children.add(CompetitionSyntax.extractCodeName(this.getId()));
			parent.setDirectories(parent_children);

			pm.makePersistent(this);

			log.info("Succesful creation of Directory " + CompetitionSyntax.extractCodeName(this.getId()) + " in " + CompetitionSyntax.extractParent(this.getId()));
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
