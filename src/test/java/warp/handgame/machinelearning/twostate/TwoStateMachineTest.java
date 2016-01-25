package warp.handgame.machinelearning.twostate;

import java.util.List;

import junit.framework.TestCase;
import warp.handgame.machinelearning.twostate.TwoStateMachine.TwoStateMap;
import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;
import warp.handgame.util.GameResultDbConnector;

public class TwoStateMachineTest extends TestCase {
	GameResultDbConnector conn = new GameResultDbConnector("org.sqlite.JDBC", "jdbc:sqlite:handgame_db.db",
			"human_game_test");
	TwoStateMachine twoStateMachine = new TwoStateMachine(conn);
	
	public void testInitTwoStateMap() {
		TwoStateMap twoStateMap = twoStateMachine.getTwoStateMap();
		assertEquals(25, twoStateMap.size());
	}
	
	
	public void testPredict() {
		boolean res = conn.openConnection();
		res = conn.createTableIfNotExist();
		
		res = conn.addResult(1, Shapes.ROCK, GameState.WIN);
		res = conn.addResult(2, Shapes.ROCK, GameState.WIN);
		twoStateMachine.start();
		Shapes nextMove = twoStateMachine.predict(Shapes.ROCK);
		assertEquals(Shapes.ROCK, nextMove);
		
		res = conn.addResult(1, Shapes.PAPER, GameState.WIN);
		res = conn.addResult(2, Shapes.ROCK, GameState.WIN);
		res = conn.addResult(3, Shapes.PAPER, GameState.WIN);
		res = conn.addResult(4, Shapes.ROCK, GameState.WIN);

		twoStateMachine.start();
		nextMove = twoStateMachine.predict(Shapes.ROCK);
		assertEquals(Shapes.PAPER, nextMove);

		conn.clearResult();
		conn.close();
	}
}
