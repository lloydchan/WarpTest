package warp.handgame.gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

import warp.handgame.gui.util.GameImage;
import warp.handgame.shapes.Shapes;

public class GameFrame extends JFrame{

	public GameFrame(String title) {
		//1. Create the frame.
		super(title);
	}
	
	public void display() {
		GameImage btn = new GameImage("src/main/resources/Images/rock.png" , Shapes.ROCK);
		
		//2. Optional: What happens when the frame closes?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		//...create emptyLabel...
		this.getContentPane().add(btn, BorderLayout.CENTER);

		//4. Size the frame.
		this.pack();

		//5. Show it.
		this.setVisible(true);
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameFrame gui = new GameFrame("Wrap Handgame");
		gui.display();
	}

}
