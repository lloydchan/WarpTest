package warp.handgame.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import warp.handgame.types.Shapes;

public class ShapesIconFactory {
	Logger logger = Logger.getLogger(ShapesIconFactory.class);
	
	static final Map<Shapes, ShapesIcon> icons = new HashMap<Shapes, ShapesIcon>();
	public ShapesIconFactory() {
	}
	
	public static ShapesIcon getShapesIcon(Shapes shape) {
		return icons.get(shape);
	}

	public static List<ShapesIcon> getShapesIcon() {
		List<ShapesIcon> l = new ArrayList<ShapesIcon>();
		for (ShapesIcon i: icons.values()) {
			if (i.getShape() != Shapes.UNKNOWN)
				l.add(i);
		}
//		return new ArrayList<ShapesIcon>(icons.values());
		return l;
	}
	
	public static void init(Map<String, String> shapes2File) {
		for (Entry<String, String> e: shapes2File.entrySet()) {
			String key = e.getKey();
			String file = e.getValue();
			Shapes shape = Shapes.fromString(key);
			icons.put(shape, new ShapesIcon(file, shape));
		}
	}
}
