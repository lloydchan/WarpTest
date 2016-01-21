package warp.handgame.gui.util;

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

public class GameImage extends JButton {
	Logger logger = Logger.getLogger(GameImage.class);

	private String file;
	private Shapes shape;

	public GameImage(String file, Shapes shape) {
		try {
			BufferedImage img = ImageIO.read(new File(file));
			// initialize
			init(null, new ImageIcon(img));
			this.shape = shape;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		this.file = file;
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	public Shapes getShape() {
		return this.shape;
	}
	
//	public static class Builder {
//		private String file;
//		private Shapes shape;
//
//		public GameImage build(String file, Shapes shape) {
//			return new GameImage(file, shape);
//		}
//	}

	@Override
	public String toString() {
		return "GameImage{" + "file=" + file + "}";
	}
}
