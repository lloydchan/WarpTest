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
import warp.handgame.player.Player;
import warp.handgame.player.Robot;
import warp.handgame.types.IShapes;
import warp.handgame.types.Shapes;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ILifeCycleContainer, IStoppableController, WindowListener, ShapesButton.Event {
	Logger logger = Logger.getLogger(GameFrame.class);
	
	private final List<IStoppable> stoppables = new ArrayList<IStoppable>();
	private ResultPanel resultPanel;
	
	private final ShapesButtonPanel shapesButtonPanel;

	public GameFrame(String title, String file) {
		resultPanel = new ResultPanel();
		shapesButtonPanel = new ShapesButtonPanel(this);
		
		setTitle(title);
		getContentPane().setPreferredSize(new Dimension(800, 300));
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
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(resultPanel, BorderLayout.NORTH);
		this.getContentPane().add(shapesButtonPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

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
	public void add(ILifeCycle lifecycle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(ILifeCycle lifecycle) {
		// TODO Auto-generated method stub

	}

	// TODO: ignore
	@Override
	public void onPressed(Shapes s) {
		resultPanel.onPressed(s);
//logger.debug("onPressed callback: " + s);
	}
}
