package warp.handgame.player;

//import static org.junit.Assert.fail;

import junit.framework.TestCase;

public class RobotTest extends TestCase {

	private Robot m_robot;

	protected void setUp() throws Exception {
		super.setUp();
		m_robot = new Robot("robot");
	}

	// @Test
	public void testNext() {
		try {
			m_robot.next();
		} catch (Exception e) {
			fail("Robot doThrow fail. Should not have gotten an exception");
		}
	}
}
