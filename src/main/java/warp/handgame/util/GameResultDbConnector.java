package warp.handgame.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import warp.common.ILifeCycle;
import warp.common.util.IQueryHandler;
import warp.common.util.SqliteDbWrapper;
import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;
import warp.handgame.util.GameResultDbConnector.GameResult;

public class GameResultDbConnector implements IQueryHandler, ILifeCycle {

	Logger logger = Logger.getLogger(GameResultDbConnector.class);

	public class GameResult {
		public final int rounds;
		public final Shapes shape;
		public final GameState state;

		public GameResult(int rounds, Shapes shape, GameState state) {
			this.rounds = rounds;
			this.shape = shape;
			this.state = state;
		}

		public int getRounds() {
			return rounds;
		}

		public Shapes getShape() {
			return shape;
		}

		public GameState getState() {
			return state;
		}
	}

	// private static final String KEY_ROWID = "_id";
	private static final String ROUNDS = "rounds";
	private static final String SHAPE = "shape";
	private static final String STATE = "state";
	private String TABLE_NAME = "human_game";
	private final String SQL_CREATE_CONTACTS_TABLE;
	private final String SQL_SELECT;
	private final String SQL_INSERT_PREFIX;
	private final String SQL_DROP_TABLE;
	private final String SQL_DELETE_TABLE;
//	// test if jdbc support
//	private final String SQL_VACUUM_COMMAND = "VACUUM"; 

	private SqliteDbWrapper sqliteDb;

	public GameResultDbConnector(String jdbc, String db, String table) {
		sqliteDb = new SqliteDbWrapper(jdbc, db, this);
		TABLE_NAME = table;

		SQL_CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		// + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ROUNDS + " INTEGER, " + SHAPE + " TEXT, " + STATE + " TEXT  )";
		SQL_SELECT = "SELECT * FROM " + TABLE_NAME + " ORDER BY rowid";
		SQL_INSERT_PREFIX = "INSERT INTO " + TABLE_NAME + " (ROUNDS, SHAPE, STATE) VALUES (?, ?, ?);";
		SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
		SQL_DELETE_TABLE = "DELETE FROM " + TABLE_NAME;
	}

	public boolean openConnection() {
		return sqliteDb.connect();
	}

	public boolean createTableIfNotExist() {
		return sqliteDb.createTable(SQL_CREATE_CONTACTS_TABLE);
	}

	public boolean addResult(int rounds, Shapes shape, GameState state) {
//		String sql = SQL_INSERT_PREFIX + rounds + ",'" + shape + "','" + state + "')";
		boolean res = sqliteDb.insert(SQL_INSERT_PREFIX, rounds, shape, state);
		return res;
	}

	public boolean clearResult() {
		return sqliteDb.delete(SQL_DELETE_TABLE);
	}

	/**
	 * workflow sqliteDb query -> onQueryResult (construct result list) ->
	 * return results
	 * 
	 * @return
	 */
	public List<GameResult> getResults() {
		return sqliteDb.query(SQL_SELECT);
	}

	public boolean dropTable() {
		return sqliteDb.dropTable(SQL_DROP_TABLE);
	}

	public void close() {
		sqliteDb.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see warp.common.util.IQueryHandler#onQueryResult(java.sql.ResultSet)
	 * construct query result
	 */
	@Override
	public List<GameResult> onQueryResult(ResultSet rs) {
		List<GameResult> list = new ArrayList<GameResult>();
		if (rs != null) {
			try {
				while (rs.next()) {
					// int id = rs.getInt("rowid");
					int rounds = rs.getInt(ROUNDS);
					String sShape = rs.getString(SHAPE);
					Shapes shape = Shapes.fromString(sShape);
					String sState = rs.getString(STATE);
					GameState state = GameState.fromString(sState);
					list.add(new GameResult(rounds, shape, state));
				}
			} catch (SQLException e) {
				logger.error(e.getClass().getName() + ": " + e.getMessage());
			}
		}
		return list;
	}

	@Override
	public void start() {
		boolean res = openConnection();
		if (res)
			res = createTableIfNotExist();
	}

	@Override
	public void stop() {
		close();
	}

	@Override
	public void init() {
	}

	@Override
	public void finit() {
	}
}
