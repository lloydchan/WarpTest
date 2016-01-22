package warp.handgame.gui;

import javax.swing.ImageIcon;

import warp.handgame.shapes.Shapes;

interface IShapes {
	public Shapes getShape();
	public ImageIcon getIcon();
}