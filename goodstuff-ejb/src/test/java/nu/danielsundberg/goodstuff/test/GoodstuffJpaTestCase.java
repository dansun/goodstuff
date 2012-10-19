package nu.danielsundberg.goodstuff.test;



import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class GoodstuffJpaTestCase extends GoodstuffTestCase {

	private static EntityManagerFactory entityManagerFactory;
	private static Connection connection;
	protected static EntityManager entityManager;
	
	@BeforeClass
	public static void setUpDataBase() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
		Properties testPersistenceProperties = new Properties();
		testPersistenceProperties.put("hibernate.connection.url", "jdbc:hsqldb:mem:unit-testing-jpa");
		testPersistenceProperties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
		testPersistenceProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		testPersistenceProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		testPersistenceProperties.put("hibernate.connection.username", "sa");
		testPersistenceProperties.put("hibernate.connection.password", "");
		testPersistenceProperties.put("hibernate.show_sql", "false");
		testPersistenceProperties.put("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
		testPersistenceProperties.put("hibernate.cache.use_second_level_cache","false");
		testPersistenceProperties.put("hibernate.cache.use_query_cache","false");
		testPersistenceProperties.put("hibernate.max_fetch_depth","3");
		testPersistenceProperties.put("hibernate.default_batch_fetch_size","8");
		testPersistenceProperties.put("hibernate.query.substitutions","true Y, false N");
		testPersistenceProperties.put("hibernate.order_inserts","true");
		testPersistenceProperties.put("hibernate.order_updates","true");
		testPersistenceProperties.put("hibernate.bytecode.use_reflection_optimizer","false");
		testPersistenceProperties.put("hibernate.use_sql_comments","true");
		entityManagerFactory = Persistence.createEntityManagerFactory("goodstuffPersistenceUnit", testPersistenceProperties);
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Before
	public void startTransaction() throws Exception {
		entityManager.getTransaction().begin();
	}

	@After
	public void endTransaction() throws Exception {
		entityManager.getTransaction().rollback();
	}


	@AfterClass
	public static void tearDownDataBase() throws Exception {
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		try {
			connection.createStatement().execute("SHUTDOWN");
		} catch (Exception ex) {
		}
	}

}