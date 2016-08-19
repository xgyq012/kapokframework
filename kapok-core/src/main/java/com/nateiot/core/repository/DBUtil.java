package com.nateiot.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DBUtil {

	private static EntityManager entityManager;
	
	public DBUtil(EntityManagerFactory entityManagerFactory) {
		DBUtil.entityManager = entityManagerFactory.createEntityManager();
	}

	public static <T> T find(Class<T> entityClass, Object primaryKey) {
		return DBUtil.entityManager.find(entityClass, primaryKey);
	}
	
}
