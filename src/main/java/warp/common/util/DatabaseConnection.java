package warp.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class DatabaseConnection {
	Logger logger = Logger.getLogger(DatabaseConnection.class);

	private final String jdbc;
	private final String db;
	Connection c = null;
	boolean isConn = false;
	
	public DatabaseConnection(String jdbc, String db) {
		this.jdbc = jdbc;
		this.db = db;
	}
	
	public boolean connect() {
		if (isConn)
			return isConn;
		
		try {
			Class.forName(this.jdbc);
			c = DriverManager.getConnection(this.db);
			logger.info("Open database");
			isConn = true;
		} catch (Exception e) {
			logger.error(e.getClass().getName() + ": " + e.getMessage());
		}
		return isConn;
	}

	public boolean isConnect() { 
		return isConn;
	}
	
	@SuppressWarnings("rawtypes")
	abstract public List query(String sql); 
	
	public boolean execute(String sql) {
		Statement stmt = null;
		try {
			logger.debug("Execute : " + sql);
			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getClass().getName() + ": " + e.getMessage());
			try {
				isConn = false;
				c.close();
			} catch (SQLException e1) {
				logger.error(e1.getClass().getName() + ": " + e1.getMessage());
			}
			return false;
		}
		return true;
	}
	
	public void close() {
		try {
			logger.info("Close database connection");
			isConn = false;
			c.close();
		} catch (SQLException e1) {
			logger.error(e1.getClass().getName() + ": " + e1.getMessage());
		}
	}
}
