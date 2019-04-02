package Player;

import java.util.ArrayList;
import java.util.List;

import Card.Card;
import Card.Deck;

public class Bank {

	protected List<Card> deckPlayer;
	protected String login="la Banque";

	public Bank() {
		deckPlayer=new ArrayList<Card>();
	}
	public void getCardFromDeck(Deck d) {
		Card c = d.pickCard();
		if (c != null)
			deckPlayer.add(c);
	}
	public int getScore() {
		int score = 0;
		for (Card card : deckPlayer) {
			score += card.numero.getValue();
		}
		return score;
	}
	public void printCards() throws InterruptedException {
		System.out.println("\nCartes de "+login);
		for (Card c : deckPlayer) {
			Thread.sleep(200);
			System.out.println(c);
		}
		System.out.println("Score : "+getScore());
	}
	public int compare(Bank o2) {
		int scorePlayer = getScore();
		int scoreBank = o2.getScore();
		
		if(scorePlayer!=scoreBank) return scorePlayer-scoreBank;
		else {
			if(scoreBank==21)
				return deckPlayer.size()-o2.deckPlayer.size();
			else return 0;
		}
	}
	
	public void clearDeck() {
		deckPlayer.clear();
	}
	
	public void modifyAsValue() {
		//TODO
	}
}
