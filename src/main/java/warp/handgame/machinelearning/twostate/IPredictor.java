package warp.handgame.machinelearning.twostate;

import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;

public interface IPredictor {
	public Shapes predictNext(Shapes current);
	public void onResult(int rounds, Shapes move, GameState state);
}
