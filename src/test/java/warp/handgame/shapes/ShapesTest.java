package warp.handgame.shapes;
//import static org.junit.Assert.*;

import junit.framework.TestCase;
import warp.handgame.types.Shapes;

public class ShapesTest extends TestCase {

//	@Test
	public void testAgainst() {
		assertTrue(Shapes.against(Shapes.ROCK, Shapes.ROCK) == 0);
		assertTrue(Shapes.against(Shapes.LIZARD, Shapes.LIZARD) == 0);
		assertTrue(Shapes.against(Shapes.PAPER, Shapes.PAPER) == 0);
		assertTrue(Shapes.against(Shapes.SCISSORS, Shapes.SCISSORS) == 0);
		assertTrue(Shapes.against(Shapes.SPOCK, Shapes.SPOCK) == 0);
		
		assertTrue(Shapes.against(Shapes.ROCK, Shapes.SCISSORS) > 0);
		assertTrue(Shapes.against(Shapes.ROCK, Shapes.LIZARD) > 0);
		assertTrue(Shapes.against(Shapes.ROCK, Shapes.PAPER) < 0);
		assertTrue(Shapes.against(Shapes.ROCK, Shapes.SPOCK) < 0);

		assertTrue(Shapes.against(Shapes.PAPER, Shapes.SPOCK) > 0);
		assertTrue(Shapes.against(Shapes.PAPER, Shapes.ROCK) > 0);
		assertTrue(Shapes.against(Shapes.PAPER, Shapes.SCISSORS) < 0);
		assertTrue(Shapes.against(Shapes.PAPER, Shapes.LIZARD) < 0);
		
		assertTrue(Shapes.against(Shapes.SCISSORS, Shapes.PAPER) > 0);
		assertTrue(Shapes.against(Shapes.SCISSORS, Shapes.LIZARD) > 0);
		assertTrue(Shapes.against(Shapes.SCISSORS, Shapes.SPOCK) < 0);
		assertTrue(Shapes.against(Shapes.SCISSORS, Shapes.ROCK) < 0);

		assertTrue(Shapes.against(Shapes.SPOCK, Shapes.ROCK) > 0);
		assertTrue(Shapes.against(Shapes.SPOCK, Shapes.SCISSORS) > 0);
		assertTrue(Shapes.against(Shapes.SPOCK, Shapes.PAPER) < 0);
		assertTrue(Shapes.against(Shapes.SPOCK, Shapes.LIZARD) < 0);
		
		assertTrue(Shapes.against(Shapes.LIZARD, Shapes.SPOCK) > 0);
		assertTrue(Shapes.against(Shapes.LIZARD, Shapes.PAPER) > 0);
		assertTrue(Shapes.against(Shapes.LIZARD, Shapes.ROCK) < 0);
		assertTrue(Shapes.against(Shapes.LIZARD, Shapes.SCISSORS) < 0);
	}
	
}
