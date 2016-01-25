package warp.handgame.machinelearning.twostate;

import java.util.List;
import java.util.Map;

import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;
import warp.handgame.util.GameResultDbConnector;
import warp.handgame.util.GameResultDbConnector.GameResult;

public class TwoStateMachine {
	private final GameResultDbConnector conn;

	private Map<Shapes, Map<Shapes, Integer>> twoStateMap = null;
	
	public TwoStateMachine(GameResultDbConnector conn) {
		this.conn = conn;
	}
}
