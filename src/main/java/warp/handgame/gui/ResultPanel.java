package warp.handgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import warp.handgame.machinelearning.twostate.IPredictor;
import warp.handgame.player.Player;
import warp.handgame.player.Robot;
import warp.handgame.player.Robot.Mode;
import warp.handgame.types.Shapes;

public class ResultPanel extends JPanel implements ShapesButton.Event {
	Logger logger = Logger.getLogger(Player.class);

	@SuppressWarnings("serial")
	class PlayerPanel extends JPanel {
		private static final String SCORE_TEXT = "Score : ";
		static final int FONT_SIZE = 20;
		private final Color COLOR_WIN = new Color(0xF88017);

		// JLabel nameLabel;
		JLabel shapesIcon;
		JLabel scoreLabel;

		Player player;

		public PlayerPanel(Player player) {
			this.player = player;
			Shapes shape = player.getChoice();
			shapesIcon = new JLabel("", ShapesIconFactory.getShapesIcon(shape).getIcon(), JLabel.CENTER);
			scoreLabel = new JLabel(SCORE_TEXT + player.getScoreText(), JLabel.CENTER);

			this.setPreferredSize(new Dimension(300, 200));

			setLayout(new BorderLayout());
			this.add(shapesIcon, BorderLayout.CENTER);
			this.add(scoreLabel, BorderLayout.SOUTH);
		}

		@Override
		protected void paintComponent(Graphics grphcs) {
			super.paintComponent(grphcs);
			shapesIcon.setIcon(ShapesIconFactory.getShapesIcon(player.getChoice()).getIcon());
			scoreLabel.setText(SCORE_TEXT + player.getScoreText() + "   " + player.lastState());
			switch (player.lastState()) {
			case WIN:
				scoreLabel.setForeground(COLOR_WIN);
				break;
			case LOSE:
				scoreLabel.setForeground(Color.BLACK);
				break;
			case TIED:
				scoreLabel.setForeground(Color.BLUE);
				break;
			default:
				break;
			}
		}
	};

	@SuppressWarnings("serial")
	class HumanPlayerPanel extends PlayerPanel {
		JLabel nameLabel;

		public HumanPlayerPanel(Player player) {
			super(player);
			nameLabel = new JLabel(player.getName(), JLabel.CENTER);
			nameLabel.setFont(new Font("", Font.PLAIN, FONT_SIZE));
			this.add(nameLabel, BorderLayout.NORTH);
		}

	}

	@SuppressWarnings("serial")
	class RobotPlayerPanel extends PlayerPanel implements ActionListener {
		private final JPanel radioPanel;
		private JLabel nameLabel;

		public RobotPlayerPanel(Player player) {
			super(player);

			nameLabel = new JLabel(player.getName(), JLabel.CENTER);
			nameLabel.setFont(new Font("", Font.PLAIN, FONT_SIZE));
			this.add(nameLabel, BorderLayout.NORTH);

			JRadioButton simpleButton = new JRadioButton(Mode.NORMAL.toString());
			simpleButton.setMnemonic(KeyEvent.VK_R);
			simpleButton.setActionCommand(Mode.NORMAL.toString());
			simpleButton.setSelected(true);

			JRadioButton smartButton = new JRadioButton(Mode.SMART.toString());
			smartButton.setMnemonic(KeyEvent.VK_S);
			smartButton.setActionCommand(Mode.SMART.toString());

			// Group the radio buttons.
			ButtonGroup group = new ButtonGroup();
			group.add(simpleButton);
			group.add(smartButton);

			// layout
			simpleButton.setHorizontalAlignment(SwingConstants.RIGHT);
			smartButton.setHorizontalAlignment(SwingConstants.RIGHT);

			// Register a listener for the radio buttons.
			simpleButton.addActionListener(this);
			smartButton.addActionListener(this);

			// Put the radio buttons in a column in a panel.
			radioPanel = new JPanel();
			GridBagLayout gridbag = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			radioPanel.setFont(new Font("SansSerif", Font.PLAIN, 14));
			radioPanel.setLayout(gridbag);
			c.gridwidth = 1; // reset to the default
			c.gridheight = 2;
			c.weighty = 1.0;
			addComponenet(nameLabel, gridbag, c, radioPanel);

			c.anchor = GridBagConstraints.WEST;
			c.weighty = 0.0; // reset to the default
			c.gridwidth = GridBagConstraints.REMAINDER; // end row
			c.gridheight = 1; // reset to the default
			addComponenet(simpleButton, gridbag, c, radioPanel);
			addComponenet(smartButton, gridbag, c, radioPanel);

			this.add(radioPanel, BorderLayout.NORTH);
		}

		protected void addComponenet(Component item, GridBagLayout gridbag, GridBagConstraints c, JPanel panel) {
			gridbag.setConstraints(item, c);
			panel.add(item);
		}

		public void actionPerformed(ActionEvent e) {
			if (this.player instanceof Robot) {
				Mode m = Mode.fromString(e.getActionCommand());
				((Robot) player).setMode(m);
			}
		}

	}

	private static final String ROUNDS_TEXT = "Rounds : ";
	
	private Player human;
	private Robot robot;
	private PlayerPanel humanPanel, robotPanel;
	private IPredictor machine;
	private int rounds = 0;
	private JLabel lblRounds = new JLabel(ROUNDS_TEXT + rounds);
	
	public ResultPanel(IPredictor machine) {
		this.human = new Player("Me");
		this.robot = new Robot("Robot", machine);
		this.humanPanel = new HumanPlayerPanel(human);
		this.robotPanel = new RobotPlayerPanel(robot);
		this.machine = machine;
		
		this.setLayout(new FlowLayout());
		// this.add(new JLabel("result Panel"), BorderLayout.NORTH);
		this.add(humanPanel);
		this.add(robotPanel);
		// this.add(humanPanel, BorderLayout.WEST);
		// this.add(robotPanel, BorderLayout.EAST);
	}

	@Override
	public void onPressed(Shapes s) {
		robot.next(s);
		human.setChoice(s);
		Player.play(human, robot); // score++ for winner
		// refresh screen
		humanPanel.repaint();
		robotPanel.repaint();
		
		rounds++;
		lblRounds.setText(ROUNDS_TEXT + rounds);
		machine.onResult(rounds, human.getChoice(), human.getState());
		// logger.debug("onPressed callback: " + s);
	}
}
