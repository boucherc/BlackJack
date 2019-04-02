package Card;

public class Deck {
	public Card[] deck = new Card[52 * 6];
	public int size;

	public Deck() {
		int i = 0;
		for(int j=0;j<6;j++){
		for (CardNumber cn : CardNumber.values()) {
			for (CardColor c : CardColor.values()) {
				deck[i] = new Card(c, cn);
				i++;
			}
		}
		}
		size = 52*6;
	}

	public void shuffle() {
		int random;
		Card tmp;
		for (int i = 0; i < size; i++) {
			random = (int) (Math.random() * size);
			tmp = deck[random];
			deck[random] = deck[i];
			deck[i] = tmp;
		}
	}
	
	public void shuffleRecursive(int idx) {
		int random;
		Card tmp;
		random=(int) (Math.random() * size);
		tmp = deck[random];
		deck[random] = deck[idx];
		deck[idx] = tmp;
		idx++;
		if(idx<size) shuffleRecursive(idx);
		
	}

	public Card pickCard() {
		Card res;
		if (size - 1 < 0)
			res = null;
		else {
			res = deck[size - 1];
			deck[size - 1] = null;
			size--;
		}
		return res;
	}
	
	public String toString() {
		String res = "";
		for (int i = 0; i < deck.length; i++) {
			res+=deck[i]+"\n";
		}
		return res;
	}
}
