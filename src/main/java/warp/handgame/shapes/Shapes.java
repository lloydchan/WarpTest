package warp.handgame.shapes;

/**
 * @author Lloyd rock-paper-scissors-lizard-spock
 */
public enum Shapes {
	ROCK("Rock"), 
	PAPER("Paper"), 
	SCISSORS("Scissors"), 
	LIZARD("Lizard"), 
	SPOCK("Spock");

	private String text;
	
	Shapes(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static int size() {
		return Shapes.values().length;
	}
	
	public static Shapes fromString(String text) {
		if (text != null) {
			for (Shapes v : Shapes.values()) {
				if (text.equalsIgnoreCase(v.text)) {
					return v;
				}
			}
		}
		return null;
	}
	
	/*
	 * Rock(r), Paper(p), Scissors(c), Lizard(l), Spock(s) or Exit(e)
	 */
	public static Shapes fromChar(char c) {
		switch (Character.toLowerCase(c)) {
		case 'r': return Shapes.ROCK;
		case 'p': return Shapes.PAPER;
		case 'c': return Shapes.SCISSORS;
		case 'l': return Shapes.LIZARD;
		case 's': return Shapes.SPOCK;
		}
		return null;
	}
	
	public static Shapes fromInt(int i) {
		if (i > Shapes.size()) {
			return null;
		}
		return Shapes.values()[i];
	}
	
	/*
	 * 1) same 0> tied
	 * > 0 means s1 win
	 * < 0 means s2 win
	 */
	public static int against(Shapes s1, Shapes s2) {
		if (s1 == s2)
			return 0;

		switch (s1) {
		case ROCK: {
			if (s2 == SCISSORS || s2 == LIZARD)
				return 1;
			return -1;
		}
		case PAPER: {
			if (s2 == SPOCK || s2 == ROCK)
				return 1;
			return -1;
		}
		case SCISSORS: {
			if (s2 == LIZARD || s2 == PAPER)
				return 1;
			return -1;
		}
		case SPOCK: {
			if (s2 == Shapes.SCISSORS || s2 == ROCK)
				return 1;
			return -1;
		}
		case LIZARD: {
			if (s2 == Shapes.SPOCK || s2 == PAPER)
				return 1;
			return -1;
		}
		}
		return 0;
	}
}