package warp.handgame.shapes;

public class UnknownShapesException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6550959108617846822L;
	private static final String DEFAULT_MESSAGE = "Unknown Shapes Exception"; 
	public UnknownShapesException() {
        super(DEFAULT_MESSAGE);
    }
}