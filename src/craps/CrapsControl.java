/*
 * Author: Danny Andres Lopez - 1941453-2711
 * Case 1: CrapsGame
 */
package craps;

// TODO: Auto-generated Javadoc
/**
 * The Class CrapsControl.
 */
public class CrapsControl {

	/** The dice. */
	private Dice dice;

	/** The state. */
	private int threw, point, state;

	/** The was first throw. */
	private boolean isFirstThrow, wasFirstThrow;

	/** The dice faces. */
	private int[] diceFaces;

	/**
	 * Instantiates a new craps control.
	 */
	public CrapsControl() {
		dice = new Dice();
		diceFaces = new int[2];
		isFirstThrow = true;
	}

	/**
	 * Sets the threw.
	 */
	public void setThrew() {
		for (int i = 0; i < diceFaces.length; i++) {
			diceFaces[i] = dice.getVisibleFace();
			threw += diceFaces[i];
		}

		if (isFirstThrow) {
			point = threw;
		}

	}

	/**
	 * Sets the game state. state 1 = win state 2 = lost state 3 = nextRound
	 */
	public void setGameState() {
		if (isFirstThrow) {

			if (threw == 7 || threw == 11) {
				state = 1; // wining
			} else if (threw == 2 || threw == 3 || threw == 12) {
				state = 2; // losing
			} else {
				state = 3;
			}

			isFirstThrow = false;
			wasFirstThrow = true;
		} else if (state != 1 && state != 2) {
			wasFirstThrow = false;

			if (threw == point) {
				state = 1; // wining
			} else if (threw == 7) {
				state = 2; // losing
			} else {
				state = 3;
				return;
			}
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		point = state = threw = 0;
		isFirstThrow = true;
		wasFirstThrow = false;
	}

	/**
	 * Next round.
	 */
	public void nextRound() {
		threw = 0;
	}

	/**
	 * Gets the point.
	 *
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * Gets the dice faces.
	 *
	 * @param dice the dice
	 * @return the dice faces
	 */
	public int getDiceFaces(int dice) {
		return diceFaces[dice];
	}

	/**
	 * Gets the threw.
	 *
	 * @return the threw
	 */
	public int getThrew() {
		return threw;
	}

	/**
	 * Gets the was first threw.
	 *
	 * @return the was first threw
	 */
	public boolean getWasFirstThrew() {
		return wasFirstThrow;
	}
}
