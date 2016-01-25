package warp.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import warp.common.ILifeCycle;
import warp.handgame.machinelearning.twostate.TwoStateDbConnector.GameResult;

public class SqliteDbWrapper extends DatabaseConnection {
	
	private final IQueryHandler callback;
	
	public SqliteDbWrapper(String jdbc, String db, IQueryHandler callback) {
		super(jdbc, db);
		this.callback = callback;
	}

	public void createTable(String sql) {
		this.execute(sql);
//		StringBuilder sb = new StringBuidler();
//		sb.append("CREATE TABLE )";
//		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + table + "("
//	            + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DESC + " TEXT, " + KEY_DATE + " TEXT, " + KEY_EVENT + " TEXT  )";	}
	}
	
	public void insert(String sql) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(arg0)
		this.execute(sql);
	}

	public void update(String sql) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(arg0)
		this.execute(sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List query(String sql) {
		Statement stmt = null;
		List<GameResult> results = null;
		
		try {
			logger.debug("Execute sql : " + sql);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			results = callback.onQueryResult(rs);	// rs is pointer , must handle before close
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.error(e.getClass().getName() + ": " + e.getMessage());
			try {
				isConn = false;
				c.close();

			} catch (SQLException e1) {
				logger.error(e1.getClass().getName() + ": " + e1.getMessage());
			}
		}
		
		return results;
	}
}
