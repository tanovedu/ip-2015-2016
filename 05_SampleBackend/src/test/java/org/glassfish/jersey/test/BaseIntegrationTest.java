package org.glassfish.jersey.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.elsysbg.ip.todo.TodoListServletContextListener;
import org.elsysbg.ip.todo.services.EntityManagerService;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class BaseIntegrationTest extends JerseyTest {

	private static Tomcat tomcat;
	// keep session between client calls
	private final Map<String, String> savedCookies = new HashMap<String, String>();

	@Override
	protected Application configure() {
//		enable(TestProperties.LOG_TRAFFIC);
//		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig();
	}
	
	public static Tomcat startTomcat(String webappDirLocation) throws Exception {
		tomcat = new Tomcat();
        final int port = Integer.parseInt(System.getProperty(TestProperties.CONTAINER_PORT), 10);
		tomcat.setPort(port);
		
		tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());

		tomcat.start();
		Thread.sleep(2000);
		return tomcat;
	}

	public static void stopTomcat() throws LifecycleException {
		tomcat.stop();
		tomcat.destroy();
		tomcat = null;
	}


	@BeforeSuite
	public static void setUpBeforeClass() throws Exception {
		tomcat = new Tomcat();
        final int port = Integer.parseInt(System.getProperty(TestProperties.CONTAINER_PORT), 10);
		tomcat.setPort(port);
		tomcat.addWebapp("", new File("src/main/webapp/").getAbsolutePath());
		tomcat.start();
		
		// wait to start:
		Thread.sleep(2000);
	}

	@AfterSuite
	public static void tearDownAfterClass() throws Exception {
		if (tomcat != null) {
			tomcat.stop();
			tomcat.destroy();
			tomcat = null;
		}
	}

	@BeforeMethod
	public void setUpTest() throws Exception {
		// integrate junit into testng:
		super.setUp();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		// integrate junit into testng:
		super.tearDown();
	}

	@BeforeMethod
	public void cleanUpDb() throws Exception {
		final EntityManagerService entityManagerService = TodoListServletContextListener.injector.getInstance(EntityManagerService.class);
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("delete from Task t").executeUpdate();
			em.createQuery("delete from Member m").executeUpdate();
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	@Override
	protected void configureClient(ClientConfig config) {
		config.register(MultiPartFeature.class);
		config.register(JacksonJaxbJsonProvider.class);
	}
	
	public Map<String, String> getSavedCookies() {
		return savedCookies;
	}

}
