package warp.handgame;

import junit.framework.TestCase;
import warp.handgame.types.Shapes;

/**
 * Unit test for simple App.
 */
public class GameTest extends TestCase {
	
	GameText m_game = new GameText();
	
	/**
	 * Rigorous Test :-)
	 */
	public void testCheckGameEnd() {
		
		assertFalse(m_game.checkGameEnd(Shapes.ROCK, Shapes.ROCK));
		assertFalse(m_game.checkGameEnd(Shapes.PAPER, Shapes.PAPER));
		assertFalse(m_game.checkGameEnd(Shapes.SCISSORS, Shapes.SCISSORS));
		assertFalse(m_game.checkGameEnd(Shapes.SPOCK, Shapes.SPOCK));
		assertFalse(m_game.checkGameEnd(Shapes.LIZARD, Shapes.LIZARD));
		
		assertTrue(m_game.checkGameEnd(Shapes.ROCK, Shapes.SCISSORS));
		assertTrue(m_game.checkGameEnd(Shapes.ROCK, Shapes.LIZARD));
		assertTrue(m_game.checkGameEnd(Shapes.ROCK, Shapes.PAPER));
		assertTrue(m_game.checkGameEnd(Shapes.ROCK, Shapes.SPOCK));

		assertTrue(m_game.checkGameEnd(Shapes.PAPER, Shapes.SPOCK));
		assertTrue(m_game.checkGameEnd(Shapes.PAPER, Shapes.ROCK));
		assertTrue(m_game.checkGameEnd(Shapes.PAPER, Shapes.SCISSORS));
		assertTrue(m_game.checkGameEnd(Shapes.PAPER, Shapes.LIZARD));

		assertTrue(m_game.checkGameEnd(Shapes.SCISSORS, Shapes.PAPER));
		assertTrue(m_game.checkGameEnd(Shapes.SCISSORS, Shapes.LIZARD));
		assertTrue(m_game.checkGameEnd(Shapes.SCISSORS, Shapes.SPOCK));
		assertTrue(m_game.checkGameEnd(Shapes.SCISSORS, Shapes.ROCK));

		assertTrue(m_game.checkGameEnd(Shapes.SPOCK, Shapes.ROCK));
		assertTrue(m_game.checkGameEnd(Shapes.SPOCK, Shapes.SCISSORS));
		assertTrue(m_game.checkGameEnd(Shapes.SPOCK, Shapes.PAPER));
		assertTrue(m_game.checkGameEnd(Shapes.SPOCK, Shapes.LIZARD));

		assertTrue(m_game.checkGameEnd(Shapes.LIZARD, Shapes.SPOCK));
		assertTrue(m_game.checkGameEnd(Shapes.LIZARD, Shapes.PAPER));
		assertTrue(m_game.checkGameEnd(Shapes.LIZARD, Shapes.ROCK));
		assertTrue(m_game.checkGameEnd(Shapes.LIZARD, Shapes.SCISSORS));
	}
	
	public void testReadInputCommand(){
		// parse input to convert command to lower case character 
		assertFalse(m_game.readInputCommand('e'));
//		assertFalse(m_game.readInputCommand('E'));

		assertTrue(m_game.readInputCommand('h'));
//		assertTrue(m_game.readInputCommand('H'));
	}
	
	public void testIsValidInput() {
		char command = 0;
		// invalid command
		assertEquals(command = m_game.parseInput("k"), 0);
		assertEquals(command = m_game.parseInput("ee"), 0);
		
		// valid command 
		assertEquals(command = m_game.parseInput("e"), 'e');
		assertEquals(command = m_game.parseInput("E"), 'e');
		assertEquals(command = m_game.parseInput("h"), 'h');
		assertEquals(command = m_game.parseInput("H"), 'h');
		assertEquals(command = m_game.parseInput("r"), 'r');
		assertEquals(command = m_game.parseInput("R"), 'r');
		assertEquals(command = m_game.parseInput("s"), 's');
		assertEquals(command = m_game.parseInput("S"), 's');
		assertEquals(command = m_game.parseInput("c"), 'c');
		assertEquals(command = m_game.parseInput("C"), 'c');
		assertEquals(command = m_game.parseInput("p"), 'p');
		assertEquals(command = m_game.parseInput("P"), 'p');
		assertEquals(command = m_game.parseInput("l"), 'l');
		assertEquals(command = m_game.parseInput("L"), 'l');
	}
}
