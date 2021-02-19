/*
 * Author: Danny Andres Lopez - 1941453-2711
 * Case 1: CrapsGame
 */
package craps;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Dice.
 */
public class Dice {

	/** The visible face. */
	private int visibleFace;

	/**
	 * Sets the visible face.
	 */
	private void setVisibleFace() {
		Random random = new Random();
		visibleFace = random.nextInt(6) + 1;
	}

	/**
	 * Gets the visible face.
	 *
	 * @return the visible face
	 */
	public int getVisibleFace() {
		setVisibleFace();
		return visibleFace;
	}
}
