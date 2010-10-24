package treicco.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import treicco.client.TaskManager;
import treicco.shared.Directory;
import treicco.shared.Task;

import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TaskManagerImpl extends RemoteServiceServlet implements TaskManager {
	private static final Logger log = Logger.getLogger(TaskManagerImpl.class.getName());

	public ArrayList<Directory> listDirectories(String targetPath) {
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

	public ArrayList<Task> listTasks(String targetPath) {
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
			result.add(pm.detachCopy(pm.getObjectById(Task.class, targetPath + child)));
		}

		pm.close();

		return result;
	}

	public Directory readDirectory(String targetPath) {
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

		d.setDirectories(null);
		d.setTasks(null);

		return d;
	}

	public Task readTask(String targetPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("Requested Task " + targetPath);

		// Task t = pm.detachCopy(pm.getObjectById(Task.class, targetPath));

		Task t1;
		Query query = pm.newQuery(Task.class);
		query.setFilter("id == targetKey");
		query.declareParameters("String targetKey");
		List<Task> results = (List<Task>) query.execute(targetPath);
		t1 = results.get(0);
		Task t = pm.detachCopy(t1);

		pm.close();

		return t;
	}

	public void createDirectory(Directory d) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, d.getParent());

			ArrayList<String> parent_children = parent.getDirectories();
			parent_children.add(d.getName());
			parent.setDirectories(parent_children);

			pm.makePersistent(d);

			log.info("Succesful creation of Directory " + d.getName() + " in " + d.getParent());
		} finally {
			pm.close();
		}
	}

	public void deleteDirectory(Directory d) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, d.getParent());

			ArrayList<String> parent_children = parent.getDirectories();
			parent_children.remove(d.getName());
			parent.setDirectories(parent_children);

			pm.deletePersistent(d);

			log.info("Succesful deletion of Directory " + d.getName() + " in " + d.getParent());
		} finally {
			pm.close();
		}
	}

	public void createTask(Task t) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, t.getParent());

			ArrayList<String> parent_children = parent.getTasks();
			parent_children.add(t.getName());
			parent.setTasks(parent_children);

			pm.makePersistent(t);

			log.info("Succesful creation of Task " + t.getName() + " in " + t.getParent());
		} finally {
			pm.close();
		}
	}

	public void deleteTask(Task t) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, t.getParent());

			ArrayList<String> parent_children = parent.getTasks();
			parent_children.remove(t.getName());
			parent.setTasks(parent_children);

			pm.deletePersistent(t);

			log.info("Succesful deletion of Task " + t.getName() + " in " + t.getParent());
		} finally {
			pm.close();
		}
	}

	public void init() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Query query = pm.newQuery(Directory.class);
			query.setFilter("id == targetKey");
			query.declareParameters("String targetKey");
			List<Directory> results = (List<Directory>) query.execute("/");
			
			if (results.isEmpty()){
				Directory d = new Directory("", "");
				d.setFullName("Home Page");

				pm.makePersistent(d);

				log.info("Succesful creation of Directory /");	
			}
			
			log.info("Succesful init");	
		} finally {
			pm.close();
		}
	}

	public void reset() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Query query_d = pm.newQuery(Directory.class);
			List<Directory> ds = (List<Directory>) query_d.execute();
			pm.deletePersistentAll(ds);

			Query query_t = pm.newQuery(Task.class);
			List<Task> ts = (List<Task>) query_t.execute();
			pm.deletePersistentAll(ts);
			
			log.info("Succesful reset");
		} finally {
			pm.close();
		}
		
		init();
	}
}
