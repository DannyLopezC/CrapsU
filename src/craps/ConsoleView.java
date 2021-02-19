package craps;

import java.util.Scanner;

public class ConsoleView {

	private CrapsControl crapsControl;
	private Scanner input;
	private boolean isLeaving;

	public ConsoleView() {
		crapsControl = new CrapsControl();
		input = new Scanner(System.in);
	}

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
