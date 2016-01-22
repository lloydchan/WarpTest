package warp.handgame.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import warp.handgame.types.Shapes;

public class ShapesIcon implements IShapesIcon {
	Logger logger = Logger.getLogger(ShapesIcon.class);

	private ImageIcon icon;
	private final Shapes shape;

	public ShapesIcon(String file, Shapes shape) {
		this.shape = shape;
		try {
			BufferedImage img = ImageIO.read(new File(file));
			icon = new ImageIcon(img);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
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
	public String toString() {
		return "ShapesIcon [shape=" + shape + "]";
	}
}
