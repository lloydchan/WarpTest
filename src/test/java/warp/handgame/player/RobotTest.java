package warp.handgame.player;

//import static org.junit.Assert.fail;

import junit.framework.TestCase;
import warp.handgame.types.Shapes;

public class RobotTest extends TestCase {

	private Robot m_robot;

	protected void setUp() throws Exception {
		super.setUp();
		m_robot = new Robot("robot", null);
	}

	// @Test
	public void testNext() {
		try {
			m_robot.next(Shapes.ROCK);
		} catch (Exception e) {
			fail("Robot doThrow fail. Should not have gotten an exception");
		}
	}
}
