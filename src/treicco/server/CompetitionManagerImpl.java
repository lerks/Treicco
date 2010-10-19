package treicco.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import treicco.client.CompetitionManager;
import treicco.client.Directory;
import treicco.client.Task;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CompetitionManagerImpl extends RemoteServiceServlet implements CompetitionManager {
	private static final Logger log = Logger.getLogger(CompetitionManagerImpl.class.getName());

	public Directory get(String targetPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("Requested Directory " + targetPath);

		Directory d = pm.detachCopy(pm.getObjectById(Directory.class, targetPath));

		pm.close();

		return d;
	}

	public void add(Directory d) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, d.getPath());

			ArrayList<String> parent_children = parent.getDirectories();
			parent_children.add(d.getName());
			parent.setDirectories(parent_children);

			pm.makePersistent(d);

			log.info("Succesful creation of Directory " + d.getName() + " in " + d.getPath());
		} finally {
			pm.close();
		}
	}

	public void remove(Directory d) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, d.getPath());

			ArrayList<String> parent_children = parent.getDirectories();
			parent_children.remove(d.getName());
			parent.setDirectories(parent_children);

			pm.deletePersistent(d);

			log.info("Succesful deletion of Directory " + d.getName() + " in " + d.getPath());
		} finally {
			pm.close();
		}
	}

	public void add(Task t) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, t.getPath());

			ArrayList<String> parent_children = parent.getTasks();
			parent_children.add(t.getName());
			parent.setTasks(parent_children);

			pm.makePersistent(t);

			log.info("Succesful creation of Task " + t.getName() + " in " + t.getPath());
		} finally {
			pm.close();
		}
	}

	public void remove(Task t) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Directory parent = pm.getObjectById(Directory.class, t.getPath());

			ArrayList<String> parent_children = parent.getTasks();
			parent_children.remove(t.getName());
			parent.setTasks(parent_children);

			pm.deletePersistent(t);

			log.info("Succesful deletion of Task " + t.getName() + " in " + t.getPath());
		} finally {
			pm.close();
		}
	}
}
