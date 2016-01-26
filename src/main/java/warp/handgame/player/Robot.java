package warp.handgame.player;

import org.apache.log4j.Logger;

import warp.handgame.machinelearning.twostate.IPredictor;
import warp.handgame.machinelearning.twostate.TwoStateMachine;
import warp.handgame.types.Shapes;
import warp.handgame.util.RandomHelper;

public class Robot extends Player {
	
	Logger logger = Logger.getLogger(Robot.class);
	
	public enum Mode {
		NORMAL("Normal"), SMART("Smart"); 
		
		private String text ;

		Mode(String text) {
			this.text = text;
		}
		
		public static Mode fromString(String text) {
			if (text != null) {
				for (Mode v : Mode.values()) {
					if (text.equalsIgnoreCase(v.text)) {
						return v;
					}
				}
			}
			return null;
		}
		
		@Override
		public String toString() {
			return this.text;
		}
	}
	
	private Mode mode = Mode.NORMAL;
	private final IPredictor predictor;
	
	public Robot(String name, IPredictor predictor) {
		super(name);
		this.predictor = predictor;
	}
	
	public void next(Shapes current) {
		if (this.mode == Mode.NORMAL)
			this.choice = RandomHelper.getShape();
		else {
			this.choice = predictor.predictNext(current);
		}
	}
	
	public void setMode(Mode m) {
		this.mode = m;
		logger.info("Change mode to " + m);
	}
}
