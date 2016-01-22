package warp.handgame.player;

import java.util.Random;

import warp.handgame.action.Play;
import warp.handgame.types.Shapes;
import warp.handgame.util.RandomShapesHelper;

public class Robot implements Play {
	
	public Shapes doThrow() {
		return RandomShapesHelper.get();
	}
}
