/*
 * Author: Danny Andres Lopez - 1941453-2711
 * Case 1: CrapsGame
 */
package craps;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsoleView.
 */
public class ConsoleView {

	/** The craps control. */
	private CrapsControl crapsControl;

	/** The input. */
	private Scanner input;

	/** The is leaving. */
	private boolean isLeaving;

	/**
	 * Instantiates a new console view.
	 */
	public ConsoleView() {
		crapsControl = new CrapsControl();
		input = new Scanner(System.in);
	}

	/**
	 * Start game.
	 */
	public void startGame() {
		System.out.println("Do you want to throw the dices? y/n");
		if (input.nextLine().equals("y")) {
			// game starting
			crapsControl.setThrew();
			System.out.printf("Dice 1 = %d Dice 2 = %d Throw = %d \n", crapsControl.getDiceFaces(0),
					crapsControl.getDiceFaces(1), crapsControl.getThrew());

			crapsControl.setGameState();

			switch (crapsControl.getState()) {
			case 1:
				System.out.println("You won");
				break;
			case 2:
				System.out.println("You lost");
				break;
			case 3:
				if (crapsControl.getWasFirstThrew()) {
					System.out.printf("You set a point %d \n", crapsControl.getPoint());
				}

				crapsControl.nextRound();
				startGame();
			}

			if (isLeaving) {
				return;
			}

			crapsControl.reset();
			playAgain();
		} else {
			System.out.println("You left the game :c");
			crapsControl.reset();
			isLeaving = true;
			return;
		}
	}

	/**
	 * Play again.
	 */
	private void playAgain() {
		System.out.println("Do you want to play again? y/n");
		if (input.nextLine().equals("y")) {
			startGame();
		} else {
			isLeaving = true;
			System.out.println("GoodBye");
		}
	}
}
