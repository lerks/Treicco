package treicco.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * PersistenceManagerFactory
 * 
 * Provides methods to get a global shared javax.jdo.PersistenceManagerFactory
 * object without the need to create one
 */
public final class PMF {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}

	/**
	 * Get a global shared instance of javax.jdo.PersistenceManagerFactory
	 * 
	 * Since the creation of a javax.jdo.PersistenceManagerFactory requires lot
	 * of resources this method provides a global shared instance.
	 * 
	 * @return a global shared javax.jdo.PersistenceManagerFactory object
	 * @see javax.jdo.PersistenceManagerFactory
	 */
	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
