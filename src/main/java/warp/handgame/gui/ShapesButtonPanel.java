package warp.handgame.gui;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;

import warp.handgame.types.Shapes;

public class ShapesButtonPanel extends JPanel {
	public ShapesButtonPanel(ShapesButton.Event listener) {
		setLayout(new FlowLayout());
		List<ShapesIcon> l = ShapesIconFactory.getShapesIcon();
		l.stream().filter(s -> s.getShape() != Shapes.UNKNOWN).forEach(s -> add(new ShapesButton(s, listener)));
	}
}
