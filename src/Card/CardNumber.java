package Card;

public enum CardNumber {
	AS(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7), HUIT(8), NEUF(9), DIX(10), 
	VALET(10), DAME(10), ROI(10);
	
	private int value;
	
	CardNumber(int value) {
		this.value=value;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value=value;
	}
	
}
