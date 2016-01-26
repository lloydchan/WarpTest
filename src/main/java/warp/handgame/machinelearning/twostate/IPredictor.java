package warp.handgame.machinelearning.twostate;

import warp.handgame.types.Shapes;

public interface IPredictor {
	public Shapes predictNext(Shapes current);
}
