package warp.handgame.machinelearning.twostate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import warp.common.ILifeCycle;
import warp.handgame.types.GameState;
import warp.handgame.types.Shapes;
import warp.handgame.util.GameResultDbConnector;
import warp.handgame.util.GameResultDbConnector.GameResult;

/**
 * @author Lloyd description: the two state map built from historical result and
 *         find the next step by the first step with higher probability
 */
public class TwoStateMachine implements ILifeCycle, IPredictor {

	Logger logger = Logger.getLogger(TwoStateMachine.class);

	// 1) the two state map built from historical result,
	// 2) update each human move,
	// 3) using the first step state to find the next step with highest
	// probabilities
	class TwoStateMap {
		private Map<String, Integer> twoStates = null;

		public TwoStateMap() {
			twoStates = new HashMap<String, Integer>();
			for (Shapes first : Shapes.toList()) { // 1st state
				for (Shapes second : Shapes.toList()) { // 2nd state
					String key = toKey(first, second);
					twoStates.put(key, new Integer(0));
				}
			}
		}

		int size() {
			return twoStates.size();
		}

		void add(Shapes first, Shapes second) {
			String key = toKey(first, second);
			int count = twoStates.get(key);
			twoStates.put(key, new Integer(count + 1));
		}

		Shapes predictBy1stMove(Shapes first) {
			int max = Integer.MIN_VALUE;
			Shapes result = Shapes.UNKNOWN;
			for (Entry<String, Integer> e : twoStates.entrySet()) {
				String[] tokens = e.getKey().split("_");
				if (tokens[0].equals(first.toString())) {
					int count = e.getValue();
					if (count > max) {
						max = count;
						result = Shapes.fromString(tokens[1]);
					}
				}
			}
			return result;
		}

		private String toKey(Shapes s1, Shapes s2) {
			return s1.toString() + "_" + s2.toString();
		}
	}

	private final GameResultDbConnector conn;
	private TwoStateMap twoStateMap;
	private Shapes last = Shapes.UNKNOWN;
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	private static final long SHUTDOWN_IN_ONE_SECOND = 1;

	public TwoStateMachine(GameResultDbConnector conn) {
		this.conn = conn;
		twoStateMap = new TwoStateMap();
	}

	public TwoStateMap getTwoStateMap() {
		return twoStateMap;
	}

	/**
	 * build the two state map from the historical game result
	 */
	public void buildTwoStateMap(List<GameResult> l) {
		logger.info("Build two state map with historical data");
		twoStateMap = new TwoStateMap(); // reset
		Shapes first = Shapes.UNKNOWN;

		for (GameResult result : l) {
			int rounds = result.getRounds();
			if (rounds > 1) { // more than one move
				Shapes second = result.getShape();
				twoStateMap.add(first, second);
			}
			first = result.getShape();
		}
	}

	@Override
	public void start() {
		List<GameResult> l = conn.getResults();
		buildTwoStateMap(l);
	}

	@Override
	public void stop() {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(SHUTDOWN_IN_ONE_SECOND, TimeUnit.SECONDS)) {
				logger.error("Executor did not terminate in the specified time.");
				List<Runnable> droppedTasks = executor.shutdownNow();
				logger.info("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Executor await terminate interrupt");
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finit() {
		// TODO Auto-generated method stub

	}

	@Override
	public Shapes predictNext(Shapes current) {
		return twoStateMap.predictBy1stMove(current);
	}

	@Override
	public void onResult(final int rounds, final Shapes move, final GameState state) {
		if (last != Shapes.UNKNOWN && rounds > 1) {
			twoStateMap.add(last, move);
		}
		last = move;
		logger.debug("Add human move: " + rounds + "," + move + "," + state);

		// insert db
		executor.execute(new Runnable() {
			public void run() {
				conn.addResult(rounds, move, state);
			}
		});
	}
}
