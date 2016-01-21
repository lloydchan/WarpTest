package warp.handgame;

import junit.framework.TestCase;
import warp.handgame.shapes.Shapes;

/**
 * Unit test for simple App.
 */
public class GameTest extends TestCase {
	
	Game m_game = new Game();
	
	/**
	 * Rigourous Test :-)
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
}
