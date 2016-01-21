package warp.handgame.util;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import warp.handgame.shapes.Shapes;
import warp.handgame.shapes.UnknownShapesException;

public final class RandomShapesHelper {
	private static Random m_randomGenerator = new Random(System.currentTimeMillis());

	public static Shapes get() throws UnknownShapesException {
		int n = Shapes.size();
		int i = m_randomGenerator.nextInt(n);
		return Shapes.fromInt(i);
	}

	// public static final void main(String... aArgs) {
	// log("Generating 10 random integers in range 0..99.");
	//
	// // note a single Random object is reused here
	// Random randomGenerator = new Random();
	// for (int idx = 1; idx <= 10; ++idx) {
	// int randomInt = randomGenerator.nextInt(10);
	// log("Generated : " + randomInt);
	// }
	//
	// log("Done.");
	// }
	//
	// private static void log(String aMessage) {
	// System.out.println(aMessage);
	// }
}