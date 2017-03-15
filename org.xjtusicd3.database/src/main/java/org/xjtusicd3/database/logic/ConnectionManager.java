package org.xjtusicd3.database.logic;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

public class ConnectionManager {
	private static PooledDataSource dataSource;

	public static DataSource getProxoolDataSource(String driver, String url, String username, String password, String alias, int maxConnection, int minConnection, int simultaneousBuildThrottle) {
		if (dataSource == null) {
			dataSource = new PooledDataSource();
		}
		dataSource.setDriver(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
//		dataSource.setPoolMaximumActiveConnections(maxConnection);
//		dataSource.setPoolMaximumIdleConnections(simultaneousBuildThrottle);
		return dataSource;
	}
}
