package warp.handgame.player;

import warp.handgame.types.Shapes;
import warp.handgame.util.RandomHelper;

public class Robot extends Player {
	
	public Robot(String name) {
		super(name);
	}
	
	public Shapes next() {
		this.choice = RandomHelper.getShape();
		return this.choice;
	}
}
