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

import warp.common.ILifeCycle;
import warp.common.ILifeCycleContainer;
import warp.common.util.IStoppable;
import warp.common.util.IStoppableController;
import warp.handgame.shapes.Shapes;

public class GameFrame extends JFrame implements ILifeCycleContainer, IStoppableController, WindowListener {
	private final List<IStoppable> stoppables = new ArrayList<IStoppable>();

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
		
		ShapesButton btn1 = new ShapesButton("resources/images/rock.png", Shapes.ROCK);
		ShapesButton btn2 = new ShapesButton("resources/images/paper.png", Shapes.PAPER);
		ShapesButton btn3 = new ShapesButton("resources/images/scissors.png", Shapes.SCISSORS);
		ShapesButton btn4 = new ShapesButton("resources/images/lizard.png", Shapes.LIZARD);
		ShapesButton btn5 = new ShapesButton("resources/images/spock.png", Shapes.SPOCK);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel choose = new JPanel();
		choose.setLayout(new FlowLayout());
		choose.add(btn1);
		choose.add(btn2);
		choose.add(btn3);
		choose.add(btn4);
		choose.add(btn5);
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
}
