package warp.handgame.player;

import warp.handgame.types.GameStatus;
import warp.handgame.types.Shapes;

public class Player {
	Shapes choice;
	String name;
	int score;
	GameStatus state = GameStatus.NIL;
	
	public Player(String name) {
		choice = Shapes.UNKNOWN;
		this.name = name;
		this.score = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setChoice(Shapes choice) {
		this.choice = choice;
	}
	
	public Shapes getChoice() {
		return choice;
	}
	
	public String getScoreText() {
		return String.valueOf(score);
	}

	public void win() {
		score++;
		state = GameStatus.WIN;
	}

	public void lose() {
		state = GameStatus.LOSE;
	}

	public void tied() {
		state = GameStatus.TIED;
	}
	
	public GameStatus lastState() {
		return state;
	}
	
	public static void play(Player p1, Player p2) {
		int res = Shapes.against(p1.getChoice(), p2.getChoice());
		if (res > 0) {
			p1.win();
			p2.lose();
		}
		else if (res < 0) {
			p2.win();
			p1.lose();
		}
		else {
			p1.tied();
			p2.tied();
		}
	}
}
