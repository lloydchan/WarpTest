package warp.handgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import warp.common.ILifeCycle;
import warp.common.ILifeCycleContainer;
import warp.common.util.IStoppable;
import warp.common.util.IStoppableController;
import warp.handgame.machinelearning.twostate.TwoStateMachine;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ILifeCycleContainer, IStoppableController, WindowListener {
	Logger logger = Logger.getLogger(GameFrame.class);
	
	private final List<IStoppable> stoppables = new ArrayList<IStoppable>();
	private ResultPanel resultPanel;
	
	private final ShapesButtonPanel shapesButtonPanel;

	private final List<ILifeCycle> lifeCycles = new ArrayList<ILifeCycle>();
	
	public GameFrame(String title, String file, TwoStateMachine machine, List<ILifeCycle> items) {
		resultPanel = new ResultPanel(machine);
		shapesButtonPanel = new ShapesButtonPanel(resultPanel);
		
		for (ILifeCycle i : items) {
			lifeCycles.add(i);
		}
		
		setTitle(title);
		getContentPane().setPreferredSize(new Dimension(800, /*300*/400));
		setBackground(Color.WHITE);
		setResizable(false);
		addWindowListener(this);
	}

	@Override
	public void register(IStoppable stoppable) {
		stoppables.add(stoppable);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// close window -> tell all to stop
		for (IStoppable stoppable : stoppables) {
			stoppable.stop();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void start() {
		for (ILifeCycle item : lifeCycles) {
			item.start();
		}
		
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(resultPanel, BorderLayout.NORTH);
		this.getContentPane().add(shapesButtonPanel, BorderLayout.SOUTH);
		
		this.setLocation(100, 100);

		
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void stop() {
		for (ILifeCycle item : lifeCycles) {
			item.stop();
		}
	}

	@Override
	public void init() {
		for (ILifeCycle item : lifeCycles) {
			item.init();
		}
	}

	@Override
	public void finit() {
		for (ILifeCycle item : lifeCycles) {
			item.finit();
		}
	}

	@Override
	public void add(ILifeCycle lifecycle) {
		// TODO unused now
	}

	@Override
	public void remove(ILifeCycle lifecycle) {
		// TODO unused now
	}
}
