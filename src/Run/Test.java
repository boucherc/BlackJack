package Run;

import Card.Deck;
import Player.Human;

public class Test {
	public static void main(String[] args) {
		Deck d = new Deck();
		d.shuffleRecursive(0);
		for (int i = 0; i < d.size; i++) {
			System.out.println(d.deck[i]);
		}
//		Human p = new Human("drsneezy");
//		System.out.println(d.pickCard());
//		p.getCardFromDeck(d);
	}
}
