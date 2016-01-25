package warp.handgame.util;

import java.util.List;

import junit.framework.TestCase;
import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;
import warp.handgame.util.GameResultDbConnector;
import warp.handgame.util.GameResultDbConnector.GameResult;

public class GameResultDbConnectorTest extends TestCase {

	GameResultDbConnector conn = new GameResultDbConnector("org.sqlite.JDBC", "jdbc:sqlite:handgame_db.db",
			"human_game_test");

	public void testCreateTable() {
		boolean res = conn.openConnection();
		assertTrue(res);
		
		res = conn.createTableIfNotExist();
		assertTrue(res);
		
		conn.close();
	}

	public void testInsertAndQuery() {
		boolean res = conn.openConnection();
		assertTrue(res);

		res = conn.addResult(1, Shapes.ROCK, GameState.WIN);
		assertTrue(res);
		
		List<GameResult> l = conn.getResults();
		assertEquals(1, l.size());

		GameResult gameResult = l.get(0);
		assertEquals(1, gameResult.getRounds());
		assertEquals(Shapes.ROCK, gameResult.getShape());
		assertEquals(GameState.WIN, gameResult.getState());
		
		conn.close();
	}

	public void testDeleteAll() {
		boolean res = conn.openConnection();
		assertTrue(res);
		
		res = conn.clearResult();
		assertTrue(res);
		List<GameResult> l = conn.getResults();
		assertEquals(l.size(), 0);
	}
	
	public void testDropTable(){
		boolean res = conn.openConnection();
		assertTrue(res);
		
		res = conn.dropTable();
		assertTrue(res);
		
		conn.close();
	}
	
}
