package warp.common.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import warp.common.ILifeCycle;
import warp.handgame.machinelearning.twostate.TwoStateDbConnector.GameResult;
import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;

public class SqliteDbWrapper extends DatabaseConnection {
	
	private final IQueryHandler callback;
	
	public SqliteDbWrapper(String jdbc, String db, IQueryHandler callback) {
		super(jdbc, db);
		this.callback = callback;
	}

	public boolean connectDb() {
		return this.connect();
	}
	
	public boolean createTable(String sql) {
		return this.execute(sql);
//		StringBuilder sb = new StringBuidler();
//		sb.append("CREATE TABLE )";
//		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + table + "("
//	            + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DESC + " TEXT, " + KEY_DATE + " TEXT, " + KEY_EVENT + " TEXT  )";	}
	}

	public boolean dropTable(String sql) {
		return this.execute(sql);
	}
	
	public boolean insert(String sql, int rounds, Shapes shape, GameState state) {
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
		      stmt.setInt(1,rounds);
		      stmt.setString(2, shape.toString());
		      stmt.setString(3, state.toString());
		      stmt.executeUpdate();			
		} catch (SQLException e) {
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

	public boolean update(String sql) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(arg0)
		return this.execute(sql);
	}

	public boolean delete(String sql) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(arg0)
		return this.execute(sql);
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
			e.printStackTrace();
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
