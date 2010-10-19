package treicco.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

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
//		Transaction tx = pm.currentTransaction();

		try {
//			tx.begin();

			Directory parent = this.get(d.getPath());

			pm.makePersistent(d);

			ArrayList<String> parent_children = parent.getDirectories();
			parent_children.add(d.getName());
			parent.setDirectories(parent_children);
			
			pm.makePersistent(parent);
			
//			tx.commit();

			log.info("Succesful creation of Directory " + d.getName() + " in " + d.getPath());
		} finally {
//            if (tx.isActive()) {
//                tx.rollback();
                
    			log.severe("Failed creation of Directory " + d.getName() + " in " + d.getPath());
//            }
            
			pm.close();
		}
	}

	public void remove(Directory d) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Directory parent = this.get(d.getPath());

			ArrayList<String> parent_children = parent.getDirectories();
			parent_children.remove(d.getName());
			parent.setDirectories(parent_children);

			pm.deletePersistent(d);
			
			tx.commit();

			log.info("Succesful deletion of Directory " + d.getName() + " in " + d.getPath());
		} finally {
            if (tx.isActive()) {
                tx.rollback();
                
    			log.severe("Failed deletion of Directory " + d.getName() + " in " + d.getPath());
            }
            
			pm.close();
		}
	}

	public void add(Task t) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Directory parent = this.get(t.getPath());

			pm.makePersistent(t);

			ArrayList<String> parent_children = parent.getTasks();
			parent_children.add(t.getName());
			parent.setTasks(parent_children);
			
			tx.commit();

			log.info("Succesful creation of Task " + t.getName() + " in " + t.getPath());
		} finally {
            if (tx.isActive()) {
                tx.rollback();
                
    			log.severe("Failed creation of Task " + t.getName() + " in " + t.getPath());
            }
            
			pm.close();
		}
	}

	public void remove(Task t) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Directory parent = this.get(t.getPath());

			ArrayList<String> parent_children = parent.getTasks();
			parent_children.remove(t.getName());
			parent.setTasks(parent_children);

			pm.deletePersistent(t);
			
			tx.commit();

			log.info("Succesful deletion of Directory " + t.getName() + " in " + t.getPath());
		} finally {
            if (tx.isActive()) {
                tx.rollback();
                
    			log.severe("Failed deletion of Directory " + t.getName() + " in " + t.getPath());
            }
            
			pm.close();
		}
	}
}
