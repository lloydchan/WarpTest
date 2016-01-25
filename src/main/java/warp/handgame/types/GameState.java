package warp.handgame.types;

public enum GameState {
	WIN("Win"), LOSE("Lose"), TIED("Tied"), NIL(""); 
	
	private String text;

	GameState(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return this.text;
	}
	
	public static GameState fromInt(int i) {
		return GameState.values()[i];
	}
	
	public static GameState fromString(String text) {
		if (text != null) {
			for (GameState v : GameState.values()) {
				if (text.equalsIgnoreCase(v.text)) {
					return v;
				}
			}
		}
		return null;
	}
}
