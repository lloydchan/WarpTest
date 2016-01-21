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
	public static final String PROMPT_USER_TURN = "Your turn: ";
	public static final String HELP_MESSAGE = "Game instructions: Player input a character to throw or assistant command\n\t"
			+ "Throw character: r(Rock), p(Paper), c(Scissors), l(Lizard), s(Spock)\n\t"
			+ "Assistant command character: e(Exit), Help(h)\n";
	public static final char EXIT = 'e';
	public static final char HELP = 'h';
	
	enum GAME_STATUS {
		PLAYER_WIN,
		ROBOT_WIN,
		TIED;
	}
	
	public boolean checkGameEnd(Shapes player, Shapes robot) {
		int result = Shapes.against(player, robot);
		if (result > 0) {
			System.out.println("Player win\n\tPlayer:" + player.getText() + ", Computer:" + robot.getText());
			return true;
		}
		else if (result < 0) {
			System.out.println("Computer win\n\tPlayer:" + player.getText() + ", Computer:" + robot.getText());
			return true;
		}
		else {
			System.out.println("Tied\n\tPlayer:" + player.getText() + ", Computer:" + robot.getText());
		}
		return false;
	}
	
	public void printHelp() {
		System.out.print(HELP_MESSAGE);
	}

	public void printPrompt() {
		System.out.print(PROMPT_USER_TURN);
	}
	
	public void readInputCommand(char command) {
		if (EXIT == command) {	// no null checking required
			System.out.println("Exit");
			System.exit(0);
		}
		if (HELP == command) {
			printHelp();
		}
		else {
			Shapes player = Shapes.fromChar(command);
			Shapes robot = RandomShapesHelper.get();
			if (checkGameEnd(player, robot)) {
				System.out.println("Game end");
				System.exit(0);
			}
		}
	}
	
	public void start() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		System.out.print("Game start\n");
		printHelp();
		printPrompt();
		
		try {
			while ((line = in.readLine()) != null && line.length() != 0) {
				char command = Character.toLowerCase(line.charAt(0));
				readInputCommand(command);
				printPrompt();
			}
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
