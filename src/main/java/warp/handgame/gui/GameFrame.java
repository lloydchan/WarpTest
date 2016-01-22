package warp.handgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import warp.common.ILifeCycle;
import warp.common.ILifeCycleContainer;
import warp.common.util.IStoppable;
import warp.common.util.IStoppableController;
import warp.handgame.types.Shapes;
import warp.handgame.util.RandomShapesHelper;

public class GameFrame extends JFrame implements ILifeCycleContainer, IStoppableController, WindowListener, ShapesButton.Event {
	Logger logger = Logger.getLogger(GameFrame.class);
	
	private final List<IStoppable> stoppables = new ArrayList<IStoppable>();
	private Shapes player;
	private Shapes robot;
	
	public GameFrame(String title) {
		setTitle(title);
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
		this.getContentPane().setPreferredSize(new Dimension(800, 500));
		this.setBackground(Color.WHITE);
		
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel choose = new JPanel();
		choose.setLayout(new FlowLayout());
		for (ShapesIcon i: ShapesIconFactory.getShapesIcon()) {
			choose.add(new ShapesButton(i, this));
		}
		this.getContentPane().add(choose, BorderLayout.SOUTH);

		this.setResizable(false);
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

	@Override
	public void onPressed(IShapesIcon shape) {
		robot = RandomShapesHelper.get();
		shape.getIcon();
		shape.getShape();
logger.debug("onPressed callback: " + shape);
	}
}
