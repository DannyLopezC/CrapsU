package craps;

public class CrapsControl {

	private Dice dice;
	private int threw, point, state;
	private boolean isFirstThrow, wasFirstThrow;
	private int[] diceFaces;

	public CrapsControl() {
		dice = new Dice();
		diceFaces = new int[2];
		isFirstThrow = true;
	}

	public void setThrew() {
		for (int i = 0; i < diceFaces.length; i++) {
			diceFaces[i] = dice.getVisibleFace();
			threw += diceFaces[i];
		}

		if (isFirstThrow) {
			point = threw;
		}

	}

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

	public void reset() {
		point = state = threw = 0;
		isFirstThrow = true;
		wasFirstThrow = false;
	}

	public void nextRound() {
		threw = 0;
	}

	public int getPoint() {
		return point;
	}

	public int getState() {
		return state;
	}

	public int getDiceFaces(int dice) {
		return diceFaces[dice];
	}

	public int getThrew() {
		return threw;
	}

	public boolean getWasFirstThrew() {
		return wasFirstThrow;
	}
}
