package warp.handgame.util;

import junit.framework.TestCase;
import warp.handgame.shapes.Shapes;
import warp.handgame.shapes.UnknownShapesException;

public class RandomShapesHelperTest extends TestCase {

	public void testGetShapes() {
		try {
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
		} catch (UnknownShapesException e) {
			fail("Should not have gotten an exception");
		}
	}

}
