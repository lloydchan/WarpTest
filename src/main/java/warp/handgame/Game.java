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
			+ "Assist command character: e(Exit), Help(h)\n";
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
	
	/**
	 * @param command
	 * @return 
	 * 	true - the game should continue
	 * 	false - the game end
	 */
	public boolean readInputCommand(char command) {
		// assist command
		if (EXIT == command) {
			System.out.println("Exit");
			return false;
		}
		if (HELP == command) {
			printHelp();
			return true;
		}

		// game command
		Shapes player = Shapes.fromChar(command);
		Shapes robot = RandomShapesHelper.get();
		if (checkGameEnd(player, robot)) {
			System.out.println("Game end");
			return false;
		}
		return true;
	}
	
	public char parseInput(String line) {
		if (line == null || line.length() > 1) {
			return 0;
		}
		char c = Character.toLowerCase(line.charAt(0));
		if (c == EXIT || c == HELP || Shapes.isShapes(c))
			return c;
		
		return 0;
	}
	
	public void start() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		char command = 0;
		System.out.print("Game start\n");
		printHelp();
		printPrompt();
		
		try {
			while ((line = in.readLine()) != null && line.length() != 0) {
				if ((command = parseInput(line)) != 0) {
					if (readInputCommand(command)) {
						printPrompt();
					}
					else {
						System.exit(0);
					}
				}
				else {
					System.out.println("Invalid command: " + line);
					printHelp();
					printPrompt();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {	// other unexpected exception to end the game
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
