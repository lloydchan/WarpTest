package warp.common;

public interface ILifeCycleContainer extends ILifeCycle {
	public void add(ILifeCycle lifecycle);
	public void remove(ILifeCycle lifecycle);
}
