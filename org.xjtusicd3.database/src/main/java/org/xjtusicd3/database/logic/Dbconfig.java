package org.xjtusicd3.database.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.xjtusicd3.common.util.JsonUtil;


public class Dbconfig {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url;
	private String username;
	private String password;
	private String alias;
	private int maxConnection;
	private int minConnection;
	private int simultaneousBuildThrottle = 60;
	
	public Dbconfig(String propertyfile){
		Properties pros = new Properties();
		InputStream in = this.getClass().getResourceAsStream(propertyfile);
		try {
			pros.load(in);
			this.driver = pros.getProperty("driver");
			this.url = pros.getProperty("url");
			this.username = pros.getProperty("username");
			this.password = pros.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String toString() {
		return JsonUtil.toJsonString(this);
	}

	public int getMaxConnection() {
		return maxConnection;
	}

	public void setMaxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
	}

	public int getMinConnection() {
		return minConnection;
	}

	public void setMinConnection(int minConnection) {
		this.minConnection = minConnection;
	}

	public int getSimultaneousBuildThrottle() {
		return simultaneousBuildThrottle;
	}

	public void setSimultaneousBuildThrottle(int simultaneousBuildThrottle) {
		this.simultaneousBuildThrottle = simultaneousBuildThrottle;
	}
}
