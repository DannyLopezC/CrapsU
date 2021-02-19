package craps;


public class CrapsControl {
	
	private Dice dice;
	private int threw, point, state;
	private boolean firstThrow;
	private int[] diceFaces;
	
	public CrapsControl() {
		dice = new Dice();
		diceFaces = new int[2];
		firstThrow = true;
	}
	
	public void setThrew() {
		for (int i = 0; i < diceFaces.length; i++) {
			diceFaces[i] = dice.getVisibleFace();
			threw += diceFaces[i];
		}
		
		if(firstThrow) {
			point = threw;
		}
		

	}
	
	public void setGameState() {
		if(firstThrow) {
			
			if(threw == 7 || threw == 11) {
				state = 1; //wining
			}
			else if(threw == 2 || threw == 3 || threw == 12) {
				state = 2; //losing
			}
			
			firstThrow = false;
		}
		else if(state != 1 && state != 2) {
			
			if(threw == point) {
				state = 1; //wining
			}
			else if(threw == 7) {
				state = 2; //losing
			}
			else {
				return;
			}
			
			firstThrow = true;
		}
	}
	
	public void reset() {
		point = state = threw = 0;
		firstThrow = false;
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
	
	public boolean getFirstThrew() {
		return firstThrow;
	}
}
