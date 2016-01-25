package warp.handgame.types;

public enum GameStatus {
	WIN("Win"), LOSE("Lose"), TIED("Tied"), NIL(""); 
	
	private String text;

	GameStatus(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return this.text;
	}
}
