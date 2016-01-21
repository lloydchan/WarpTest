package warp.handgame.player;

import java.util.Random;

import warp.handgame.action.Play;
import warp.handgame.shapes.Shapes;
import warp.handgame.shapes.UnknownShapesException;
import warp.handgame.util.RandomShapesHelper;

public class Robot implements Play {
	
	public Shapes doThrow() {
		Shapes s = Shapes.ROCK;
		try {
			s = RandomShapesHelper.get();
		} catch (UnknownShapesException e) {
			System.out.println("Get random shape error: " + e.getMessage());
			e.printStackTrace();
		}
		return s;
	}
}
