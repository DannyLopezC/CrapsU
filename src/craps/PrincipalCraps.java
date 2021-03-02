/*
 * Author: Danny Andres Lopez - 1941453-2711
 * Case 1: CrapsGame
 */
package craps;

import java.awt.EventQueue;

public class PrincipalCraps {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ConsoleView console = new ConsoleView();
//		console.startGame();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
//				GUICrapsView myWindow = new GUICrapsView();

				GUIGridBagLayoutView myView = new GUIGridBagLayoutView();
			}
		});
	}
}
