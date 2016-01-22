package warp.common;

public interface ILifeCycle {
	public void start();
	public void stop();
	public void init();	// initialize
	public void finit();	// destruct
}
