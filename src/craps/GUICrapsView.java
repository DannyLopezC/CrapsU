package craps;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUICrapsView extends JFrame {

	private JLabel dice1, dice2;
	private JButton throwDices;
	private ImageIcon images;
	private Listener listener;
	private CrapsControl crapsControl;
	private boolean isLeaving;

	private long animationDelay = 100;

	public GUICrapsView() {
		// window container and layout
		Container container = this.getContentPane();
		LayoutManager layout = new FlowLayout();
		container.setLayout(layout);

		listener = new Listener();

		images = new ImageIcon("src/images/init.png");
		dice1 = new JLabel(images);
		dice2 = new JLabel(images);

		throwDices = new JButton("Throw");

		throwDices.addActionListener(listener);
		crapsControl = new CrapsControl();

		container.add(dice1);
		container.add(dice2);
		container.add(throwDices);

		this.setTitle("Craps Game");
		this.setSize(650, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void diceAnimation() {
		for (int i = 0; i < 6; i++) {
			images = new ImageIcon("src/images/" + (1 + i) + ".png");
			dice1.setIcon(images);
			images = new ImageIcon("src/images/" + (7 - i) + ".png");
			dice2.setIcon(images);

			try {
				Thread.sleep(animationDelay);
			} catch (InterruptedException e) {
				System.out.println("got interrupted!");
			}
		}

	}

	private void showDiceFaces() {

		diceAnimation();

		crapsControl.setThrew();
		images = new ImageIcon("src/images/" + crapsControl.getDiceFaces(0) + ".png");
		dice1.setIcon(images);
		images = new ImageIcon("src/images/" + crapsControl.getDiceFaces(1) + ".png");
		dice2.setIcon(images);
	}

	private void startGame() {
		showDiceFaces();

		crapsControl.setGameState();
		String threw = "Throw was " + crapsControl.getThrew() + "\n";

		Icon icon;

		switch (crapsControl.getState()) {
		case 1:
			icon = new ImageIcon("src/images/Win.png");
			JOptionPane.showMessageDialog(throwDices, threw, "Result", JOptionPane.DEFAULT_OPTION, icon);
			crapsControl.reset();
			break;
		case 2:
			icon = new ImageIcon("src/images/lose.png");
			JOptionPane.showMessageDialog(throwDices, threw, "Result", JOptionPane.DEFAULT_OPTION, icon);
			crapsControl.reset();
			break;
		case 3:
			if (crapsControl.getWasFirstThrew()) {
				String point = "You set a point in " + crapsControl.getPoint() + "\n" + "You should throw again";
				JOptionPane.showMessageDialog(throwDices, threw + point, "Result", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(throwDices, threw + "You should throw again", "Result",
						JOptionPane.DEFAULT_OPTION);
			}

			crapsControl.nextRound();
		}
	}

	private void playAgain() {
		isLeaving = true;
	}

	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if (throwDices == event.getSource()) {
				startGame();
			}
		}
	}

}
