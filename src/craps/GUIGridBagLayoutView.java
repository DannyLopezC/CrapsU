package craps;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import MyComponents.Titles;

public class GUIGridBagLayoutView extends JFrame {

	private JPanel gameZone, resultsZone;
	private JLabel dice1, dice2, threw, point;
	private JTextField throwValue, pointValue;
	private JButton exit, throwDices, playAgain;
	private ImageIcon images;
	private JTextArea messages;
	private Titles title, results;
	private CrapsControl crapsControl;
	private Listener listener;
	private boolean isLeaving;
	private int x, y;
	private JFrame window;

	public GUIGridBagLayoutView() {
		initGUI();

//		this.setTitle("Craps Game");
		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window = this;
	}

	public void initGUI() {
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		listener = new Listener();
		crapsControl = new CrapsControl();

		title = new Titles("Craps Game", 30, new Color(165, 165, 165));
		title.addMouseListener(listener);
		title.addMouseMotionListener(listener);
		title.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(title, constraints);

		gameZone = new JPanel();
		images = new ImageIcon("src/images/init.png");
		dice1 = new JLabel(images);
		dice2 = new JLabel(images);

		throwDices = new JButton("Throw");
		throwDices.setCursor(new Cursor(Cursor.HAND_CURSOR));
		playAgain = new JButton("Play Again");
		playAgain.setCursor(new Cursor(Cursor.HAND_CURSOR));

		throwDices.addActionListener(listener);
		playAgain.addActionListener(listener);

		gameZone.add(dice1);
		gameZone.add(dice2);
		gameZone.add(throwDices);
		gameZone.add(playAgain);
		gameZone.setPreferredSize(new Dimension(650, 400));
		gameZone.setBorder(new TitledBorder("Game Zone"));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		constraints.fill = GridBagConstraints.NONE;
		add(gameZone, constraints);

		exit = new JButton("Exit");
		exit.addActionListener(listener);
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		add(exit, constraints);

		results = new Titles("Results", 40, new Color(145, 163, 176));
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		add(results, constraints);

		resultsZone = new JPanel();
		resultsZone.setLayout(new GridLayout(2, 2));
		threw = new JLabel("Throw");
		point = new JLabel("Point");
		throwValue = new JTextField(2);
		throwValue.setEditable(false);
		pointValue = new JTextField(2);
		pointValue.setEditable(false);
		resultsZone.add(threw);
		resultsZone.add(throwValue);
		resultsZone.add(point);
		resultsZone.add(pointValue);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		add(resultsZone, constraints);

		messages = new JTextArea(10, 20);
		messages.setText("Throw the dices to start the game. \n");
		messages.setEditable(false);
		JScrollPane scroll = new JScrollPane(messages);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.anchor = GridBagConstraints.CENTER;
		add(scroll, constraints);
	}

	private void startGame() {
		crapsControl.setThrew();
		images = new ImageIcon("src/images/" + crapsControl.getDiceFaces(0) + ".png");
		dice1.setIcon(images);
		images = new ImageIcon("src/images/" + crapsControl.getDiceFaces(1) + ".png");
		dice2.setIcon(images);

		crapsControl.setGameState();
		String threwText = "Throw was " + crapsControl.getThrew() + "\n";

		throwValue.setText(String.valueOf(crapsControl.getThrew()));

		switch (crapsControl.getState()) {
		case 1:
			messages.append("You win! \n");
			isLeaving = true;
			break;
		case 2:
			messages.append("You lose \n");
			isLeaving = true;
			break;
		case 3:
			if (crapsControl.getWasFirstThrew()) {
				pointValue.setText(String.valueOf(crapsControl.getPoint()));
				String point = "You set a point in " + crapsControl.getPoint() + "\n" + "You should throw again \n";
				messages.append(point);
			} else {
				messages.append(threwText + "You shold throw again \n");
			}

			crapsControl.nextRound();
		}
	}

	private void playAgain() {
		crapsControl.reset();
		isLeaving = false;
		pointValue.setText("");
		throwValue.setText("");
		messages.setText("Throw the dices to start the game. \n");

//		images = new ImageIcon("src/images/init.png");
//		dice1 = new JLabel(images);
//		dice1.setIcon(images);
//		dice2 = new JLabel(images);
//		dice2.setIcon(images);
	}

	private class Listener implements ActionListener, MouseListener, MouseMotionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if (throwDices == event.getSource() && !isLeaving) {
				startGame();
			}

			if (playAgain == event.getSource()) {
				playAgain();
			}

			if (exit == event.getSource()) {
				System.exit(0);
			}
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO Auto-generated method stub
			x = event.getX();
			y = event.getY();

		}

		@Override
		public void mouseReleased(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			int localX = window.getLocation().x + e.getX() - x;
			int localY = window.getLocation().y + e.getY() - y;
			setLocation(localX, localY);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
