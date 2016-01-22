package warp.handgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import warp.handgame.shapes.Shapes;

@SuppressWarnings("serial")
public class ShapesButton extends JButton {
	Logger logger = Logger.getLogger(ShapesButton.class);

	private Shapes shape;

	private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    
	public ShapesButton(String file, Shapes shape) {
		try {
			BufferedImage img = ImageIO.read(new File(file));
			// initialize
			init(null, new ImageIcon(img));
			this.shape = shape;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
        super.setContentAreaFilled(false);
        this.setBackground(Color.WHITE);
        this.setHoverBackgroundColor(Color.WHITE.brighter());
        this.setFocusable(false);
	}
	
	public Shapes getShape() {
		return this.shape;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
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
