package warp.handgame.machinelearning.twostate;

import java.util.List;

import junit.framework.TestCase;
import warp.handgame.machinelearning.twostate.TwoStateDbConnector.GameResult;
import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;

public class TwoStateDbConnectorTest extends TestCase {

	TwoStateDbConnector conn = new TwoStateDbConnector("org.sqlite.JDBC", "jdbc:sqlite:handgame_db.db",
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
