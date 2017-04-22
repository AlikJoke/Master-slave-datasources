package ru.project.master.slave;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Configurable;

/**
 * Класс, дающий возможность получения экземпляра EntityManager.
 * 
 * @author Alimurad A. Ramazanov
 * @since 23.04.2017
 * @version 1.0.0
 *
 */
public class Storage {

	private static final Storage.EntityManagerHolder instance;

	static {
		instance = new Storage.EntityManagerHolder();
	}

	/**
	 * Получение единого entityManager'а.
	 * <p>
	 * 
	 * @see EntityManager
	 * @return не может быть {@code null}.
	 */
	public static EntityManager getEntityManager() {
		return instance.getEntityManager();
	}

	@Configurable
	private static class EntityManagerHolder {

		@PersistenceUnit(name = "routingEMF")
		private EntityManagerFactory emf;

		private EntityManager em;

		private final EntityManager getEntityManager() {
			if (em == null)
				em = emf.createEntityManager();
			return em;
		}
	}
}
