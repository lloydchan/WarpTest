package warp.handgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import warp.handgame.types.Shapes;
import warp.handgame.util.RandomHelper;

/**
 * program to execute
 *
 */
public class GameText {
	public static final String PROMPT_USER_TURN = "Your turn: ";
	public static final String HELP_MESSAGE = "Game instructions: Player input a character to throw or assistant command\n\t"
			+ "Throw character: r(Rock), p(Paper), c(Scissors), l(Lizard), s(Spock)\n\t"
			+ "Assist command character: e(Exit), Help(h)";
	public static final char EXIT = 'e';
	public static final char HELP = 'h';
	
	Logger logger = Logger.getLogger(GameText.class);
	
	enum GAME_STATUS {
		PLAYER_WIN,
		ROBOT_WIN,
		TIED;
	}
	
	public boolean checkGameEnd(Shapes player, Shapes robot) {
		int result = Shapes.against(player, robot);
		if (result > 0) {
			logger.info("Player win\n\tPlayer:" + player.getText() + ", Computer:" + robot.getText());
			return true;
		}
		else if (result < 0) {
			logger.info("Computer win\n\tPlayer:" + player.getText() + ", Computer:" + robot.getText());
			return true;
		}
		else {
			logger.info("Tied\n\tPlayer:" + player.getText() + ", Computer:" + robot.getText());
		}
		return false;
	}
	
	public void printHelp() {
		logger.info(HELP_MESSAGE);
	}

	public void printPrompt() {
		logger.info(PROMPT_USER_TURN);
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
			logger.info("Exit");
			return false;
		}
		if (HELP == command) {
			printHelp();
			return true;
		}

		// game command
		Shapes player = Shapes.fromChar(command);
		Shapes robot = RandomHelper.getShape();
		if (checkGameEnd(player, robot)) {
			logger.info("Game end");
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
		logger.info("\n\n=================================================");
		logger.info("Game Start");
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
					logger.error("Invalid command: " + line);
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
//		PropertyConfigurator.configure("log4j.properties");
		
		GameText game = new GameText();
		game.start();
	}
}
