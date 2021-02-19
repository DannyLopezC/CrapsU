package craps;

import java.util.Scanner;

public class ConsoleView {
	
	private CrapsControl crapsControl;
	private Scanner input;
//	private String question;
	
	public ConsoleView(){
		crapsControl = new CrapsControl();
		input = new Scanner(System.in);
	}
	
	public void startGame() {
		System.out.println("Do you want to throw the dices? y/n");
		if (input.nextLine().equals("y")) {
			//game starting
			crapsControl.setThrew();
			System.out.printf("Dice 1 = %d Dice 2 = %d Throw = %d \n",
					crapsControl.getDiceFaces(0),
					crapsControl.getDiceFaces(1),
					crapsControl.getThrew());
			
			crapsControl.setGameState();
			
			switch (crapsControl.getState()) {
				case 1:
					System.out.println("You won");
					break;
				case 2:
					System.out.println("You lost");
					break;
				case 3:
					if(crapsControl.getFirstThrew()) {
						System.out.printf("You set a point %d, you must throw again", crapsControl.getPoint());
					}
					else {
						System.out.println("Do you want to throw again? y/n");
						if(input.nextLine().equals("y")) {
							startGame();
						}
						else {
							System.out.println("You left the game :c");
							crapsControl.reset();
							break;
						}
					}
					
				default:
					break;
			}
			
			playAgain();
		}
		else {
			System.out.println("You left the game :c");
			crapsControl.reset();
			return;
		}
	}
	
	private void playAgain() {
		System.out.println("You want to play again? y/n");
		if (input.nextLine().equals("y")) {
			startGame();
		}
		else {
			System.out.println("GoodBye");
		}
	}
}
