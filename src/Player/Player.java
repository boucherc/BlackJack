package Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Card.Card;
import Card.Deck;

public class Player {
	private String user;
	private List<Card> deckPlayer;
	private int score;

	public Player() {
		deckPlayer = new ArrayList<Card>();
		score = 0;
	}

	public void modifyAsValue() {
		int nbAs = 0;
		for (Card card : deckPlayer) {
			if (card.isAs()) {
				nbAs++;
				card.numero.setValue(getValueAsCmd(nbAs));
			}
		}
	}

	private int getValueAsCmd(int nbAs) {
		Scanner sc = new Scanner(System.in);
		String entreeUser;
		do {
			System.out.println("Quel valeur pour l'As n°" + nbAs + " ? 1 ou 11?");
			entreeUser = sc.nextLine();
		} while (!entreeUser.equals("11") && !entreeUser.equals("1"));
		return Integer.parseInt(entreeUser);
	}

	public int getScore() {
		score = 0;
		for (Card card : deckPlayer) {
			score += card.numero.getValue();
		}
		return score;
	}

	public void getCardFromDeck(Deck d) {
		Card c = d.pickCard();
		if (c != null)
			deckPlayer.add(c);

	}

	@Override
	public String toString() {
		return "Player [deckPlayer=" + deckPlayer + ", score=" + score + "]";
	}
}
