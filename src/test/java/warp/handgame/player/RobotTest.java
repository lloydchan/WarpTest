package warp.handgame.player;

//import static org.junit.Assert.fail;

import junit.framework.TestCase;

public class RobotTest extends TestCase {

	private Robot m_robot;

	protected void setUp() throws Exception {
		super.setUp();
		m_robot = new Robot();
	}

	// @Test
	public void testDoThrow() {
		try {
			m_robot.doThrow();
		} catch (Exception e) {
			fail("Robot doThrow fail. Should not have gotten an exception");
		}
	}
}
