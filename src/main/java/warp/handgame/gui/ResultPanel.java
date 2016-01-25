package warp.handgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import org.apache.log4j.Logger;

import warp.handgame.player.Player;
import warp.handgame.player.Robot;
import warp.handgame.types.IShapes;
import warp.handgame.types.Shapes;

public class ResultPanel extends JPanel implements ShapesButton.Event {
	Logger logger = Logger.getLogger(Player.class);
	
	class PlayerPanel extends JPanel {
		private static final String SCORE_TEXT = "Score ";
		private static final int FONT_SIZE = 20;
		
		JLabel nameLabel;
		JLabel shapesIcon; 
//		ShapesIcon shapesIcon;
		JLabel scoreLabel;
		
		Player player;
		
		public PlayerPanel(Player player) {
			this.player = player;
			nameLabel = new JLabel(player.getName(), JLabel.CENTER);
			nameLabel.setFont(new Font("", Font.PLAIN, FONT_SIZE));
			Shapes shape = player.getChoice();
			shapesIcon = new JLabel("", ShapesIconFactory.getShapesIcon(shape).getIcon(), JLabel.CENTER);
			scoreLabel = new JLabel(SCORE_TEXT + player.getScoreText(), JLabel.CENTER);
			nameLabel.setFont(new Font("", Font.PLAIN, FONT_SIZE));
			
			this.setPreferredSize(new Dimension(300, 150));
			
			setLayout(new BorderLayout());
//		    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
			this.add(nameLabel, BorderLayout.NORTH);
			this.add(shapesIcon, BorderLayout.CENTER);
			this.add(scoreLabel, BorderLayout.SOUTH);
		}

//		public void refresh() {
//			robot.next();
//			human.setChoice(s);
//			Player.play(human, robot);	// score++ for winner
//	//logger.debug("onPressed callback: " + s);
//		}
	}
	
	Player human;
	Robot robot;
	PlayerPanel humanPanel, robotPanel;
	
	public ResultPanel() {
		this.human = new Player("Me");
		this.robot = new Robot("Robot");
		humanPanel = new PlayerPanel(human);
		robotPanel = new PlayerPanel(robot);
		
		this.setLayout(new FlowLayout());
//		this.add(new JLabel("result Panel"), BorderLayout.NORTH);
		this.add(humanPanel);
		this.add(robotPanel);
//		this.add(humanPanel, BorderLayout.WEST);
//		this.add(robotPanel, BorderLayout.EAST);
	}

	@Override
	public void onPressed(Shapes s) {
		robot.next();
		human.setChoice(s);
		Player.play(human, robot);	// score++ for winner
		
		// refresh screen
//logger.debug("onPressed callback: " + s);
	}
}
