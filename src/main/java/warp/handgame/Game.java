package warp.handgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import warp.handgame.shapes.Shapes;
import warp.handgame.util.RandomShapesHelper;

/**
 * program to execute
 *
 */
public class Game {
	public static final String PROMPT_USER_TURN = "Your turn:";
	public static final String EXIT = "e";
	
	enum GAME_STATUS {
		PLAYER_WIN,
		ROBOT_WIN,
		TIED;
	}
	
	public void checkStatus(String playerThrow) {
		Shapes player = Shapes.fromString(playerThrow);
		Shapes robot = RandomShapesHelper.get();
		
		int result = Shapes.against(player, robot);
		if (result > 0) {
			System.out.println("Player win");
			System.exit(0);
		}
		else if (result < 0) {
			System.out.println("Robot win");
			System.exit(0);
		}
		else {
			System.out.println("Tied");
		}
	}
	
	public void start() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		System.out.println("Game start\n Input character to throw Rock(r), Paper(p), Scissors(c), Lizard(l), Spock(s) or Exit(e)");
		System.out.println(PROMPT_USER_TURN);
		try {
			while ((s = in.readLine()) != null && s.length() != 0)
				if (EXIT.equals(s)) {	// no null checking required
					System.out.println("Exit");
					break;
				}
				
				System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
