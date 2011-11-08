package com.svm4j.rs.dao;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/**
 * Data access object for entities.
 * 
 * @author Tommer
 *
 */
public class Dao {
	private static Dao dao;
	
	private PersistenceManagerFactory persistenceManagerFactory;
	
	public Dao() {
		super();
		persistenceManagerFactory = PMF.get();
	}

	/**
	 * Persist an object to the datastore.
	 * 
	 * @param obj
	 */
	public void save(Object obj) {
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		try {
			pm.makePersistent(obj);
		} finally {
			pm.close();
		}
	}
	
	/**
	 * Get an entity from the data store.
	 * 
	 * @param clazz
	 * @param key
	 * @return
	 */
	public<T> T get(Class<T> clazz, Long key) {
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		try {
			return pm.getObjectById(clazz, key);
		} finally {
			pm.close();
		}
	
	}

	public static Dao getDao() {
		return dao;
	}

	public static void setDao(Dao dao) {
		Dao.dao = dao;
	}

}
