package warp.handgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import warp.handgame.types.Shapes;

@SuppressWarnings("serial")
public class ShapesButton extends JButton implements ActionListener {
	interface Event {
		/**
		 * callback when pressed
		 */
		void onPressed(IShapesIcon s);
	}

	Logger logger = Logger.getLogger(ShapesButton.class);

	private final ShapesIcon shapeIcon;
	
//	private Shapes shape;
//	private ImageIcon icon;

	private Color hoverBackgroundColor;
//	private Color pressedBackgroundColor;

	private final ShapesButton.Event handler;

	public ShapesButton(ShapesIcon shapeIcon, ShapesButton.Event handler) {
		this.shapeIcon = shapeIcon;
		init(null, shapeIcon.getIcon());
		
		this.handler = handler;

		super.setContentAreaFilled(false);
		this.setBackground(Color.WHITE);
		this.setHoverBackgroundColor(Color.WHITE.brighter());
		this.setFocusable(false);

		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.handler.onPressed(this.shapeIcon);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(hoverBackgroundColor);
		} else if (getModel().isRollover()) {
			g.setColor(hoverBackgroundColor);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public Color getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

	@Override
	public String toString() {
		return "GameImage{" + "shapeIcon=" + shapeIcon + "}";
	}
}
