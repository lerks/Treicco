package treicco.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import treicco.client.DirectoryManager;
import treicco.shared.Directory;
import treicco.shared.Task;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class DirectoryProvider extends RemoteServiceServlet implements DirectoryManager {
	private static final Logger log = Logger.getLogger(DirectoryProvider.class.getName());

	public ArrayList<Directory> list(String targetPath) {
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

	public Directory read(String targetPath) {
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

	public void create(Directory d) {
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

	public void delete(Directory d) {
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

	public void update(Directory directory) {
		// TODO Auto-generated method stub
		
	}
}
