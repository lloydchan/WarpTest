package warp.handgame.machinelearning.twostate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import warp.common.util.IQueryHandler;
import warp.common.util.SqliteDbWrapper;
import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;

public class TwoStateDbConnector implements IQueryHandler {

	Logger logger = Logger.getLogger(TwoStateDbConnector.class);
	
	public class GameResult {
		final int rounds;
		final Shapes shape;
		final GameState state;
		
		public GameResult(int rounds, Shapes shape, GameState state) {
			this.rounds = rounds;
			this.shape = shape;
			this.state = state;
		}
	}
	
//	private static final String KEY_ROWID = "_id";
	private static final String ROUNDS = "rounds";
	private static final String SHAPE = "shape";
	private static final String STATE = "state";
	private static final String TABLE_NAME  = "human_game";
	private static final String SQL_CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
//			+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ ROUNDS + " INTEGER, " + SHAPE + " INTEGER, " + STATE + " INTEGER  )";
	private static final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME + " ORDER BY rowid";
	private static final String SQL_INSERT_PREFIX = "INSERT INTO " + TABLE_NAME + " (ROUNDS, SHAPE, STATE) VALUES ";
	
	private SqliteDbWrapper sqliteDb;
	
	public TwoStateDbConnector(String jdbc, String db) {
		sqliteDb = new SqliteDbWrapper(jdbc, db, this);
	}

	public void createTableIfNotExist(){
		sqliteDb.createTable(SQL_CREATE_CONTACTS_TABLE);
	}

	/**
	 * workflow sqliteDb query -> onQueryResult (construct result list) -> return results 
	 * @return
	 */
	public List<GameResult> getResults() {
		return sqliteDb.query(SQL_SELECT);
	}
	
	/* (non-Javadoc)
	 * @see warp.common.util.IQueryHandler#onQueryResult(java.sql.ResultSet)
	 * construct query result
	 */
	@Override
	public List onQueryResult(ResultSet rs) {
		List<GameResult> list = new ArrayList<GameResult>();
	      try {
			while ( rs.next() ) {
//			      int id = rs.getInt("rowid");
			      int rounds = rs.getInt(ROUNDS);
			      int iShape  = rs.getInt(SHAPE);
			      Shapes shape = Shapes.fromInt(iShape);
			      int iState  = rs.getInt(STATE);
			      GameState state = GameState.fromInt(iState);
			      list.add(new GameResult(rounds, shape, state));
			   }
		} catch (SQLException e) {
			logger.error(e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}
}
