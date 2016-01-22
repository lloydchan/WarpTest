package warp.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lloyd Handle the life cyclye of the objects
 */
public class LifeCycleContainer implements ILifeCycleContainer {
	private final List<ILifeCycle> lifeCycles = new ArrayList<ILifeCycle>();

	public LifeCycleContainer() {
	}

	@Override
	public void start() {
		for (ILifeCycle lifecycle : lifeCycles) {
			lifecycle.start();
		}

	}

	@Override
	public void stop() {
		for (ILifeCycle lifecycle : lifeCycles) {
			lifecycle.stop();
		}
	}

	@Override
	public void init() {
		for (ILifeCycle lifecycle : lifeCycles) {
			lifecycle.init();
		}
	}

	@Override
	public void finit() {
		for (ILifeCycle lifecycle : lifeCycles) {
			lifecycle.finit();
		}
	}

	@Override
	public void add(ILifeCycle lifecycle) {
		lifeCycles.add(lifecycle);

	}

	@Override
	public void remove(ILifeCycle lifecycle) {
		lifeCycles.remove(lifecycle);
	}
}
