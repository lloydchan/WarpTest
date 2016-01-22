package warp.handgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import warp.handgame.shapes.Shapes;

@SuppressWarnings("serial")
public class ShapesButton extends JButton implements IShapes, ActionListener {
	interface Event {
		/**
		 * callback when pressed
		 */
		void onPressed(IShapes s);
	}

	Logger logger = Logger.getLogger(ShapesButton.class);

	private Shapes shape;
	private ImageIcon icon;

	private Color hoverBackgroundColor;
	private Color pressedBackgroundColor;

	private final ShapesButton.Event handler;

	public ShapesButton(String file, Shapes shape, ShapesButton.Event handler) {
		try {
			BufferedImage img = ImageIO.read(new File(file));
			// initialize
			icon = new ImageIcon(img);
			init(null, icon);
			this.shape = shape;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		this.handler = handler;

		super.setContentAreaFilled(false);
		this.setBackground(Color.WHITE);
		this.setHoverBackgroundColor(Color.WHITE.brighter());
		this.setFocusable(false);

		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.handler.onPressed(this);
	}
	
	@Override
	public Shapes getShape() {
		return this.shape;
	}

	@Override
	public ImageIcon getIcon() {
		return this.icon;
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
		return "GameImage{" + "shape=" + shape + "}";
	}
}
