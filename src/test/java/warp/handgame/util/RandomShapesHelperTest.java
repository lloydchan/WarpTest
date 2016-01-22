package warp.handgame.util;

import junit.framework.TestCase;
import warp.handgame.types.Shapes;

public class RandomShapesHelperTest extends TestCase {

	public void testGetShapes() {
		Shapes s = RandomShapesHelper.get();
		switch (s) {
		case ROCK:
		case PAPER:
		case SCISSORS:
		case LIZARD:
		case SPOCK:
			break;
		default:
			fail("Unknown shapes type");
		}
	}

}
