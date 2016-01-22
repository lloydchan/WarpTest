package warp.handgame.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import warp.handgame.types.Shapes;

public class ShapesIconFactory {
	static final Map<Shapes, ShapesIcon> icons = new HashMap<Shapes, ShapesIcon>();
	
	public ShapesIconFactory() {
	}

	public static List<ShapesIcon> getShapesIcon() {
		return new ArrayList<ShapesIcon>(icons.values());
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
