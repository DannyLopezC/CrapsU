package craps;

import java.util.Random;

public class Dice {
	private int visibleFace;
	
	private void setVisibleFace() {
		Random random = new Random();
		visibleFace = random.nextInt(6) + 1;
	}
	
	public int getVisibleFace() {
		setVisibleFace();
		return visibleFace;
	}
}
