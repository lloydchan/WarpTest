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

	public static Shapes fromInt(int i) throws UnknownShapesException {
		if (i > Shapes.size()) {
			throw new UnknownShapesException();
		}
		return Shapes.values()[i];
	}
}