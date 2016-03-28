package org.elsysbg.ip.todo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.openjpa.jdbc.schema.SimpleDriverDataSource;
import org.elsysbg.ip.todo.services.EntityManagerService;

/**
 * You can use jndi in shiro.ini if DataSource is defined in this way
 * dataSource = org.apache.shiro.jndi.JndiObjectFactory
 * dataSource.resourceName = java:/comp/env/jdbc/...
 */
public class TodoListDataSource extends SimpleDriverDataSource {
	
	@Override
	protected Connection getSimpleConnection(Properties props)
			throws SQLException {
		final EntityManagerService entityManagerService = TodoListServletContextListener.injector.getInstance(EntityManagerService.class);
		return entityManagerService.createEntityManager().unwrap(Connection.class);
	}

}
