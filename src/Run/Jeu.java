package Run;

import java.util.Scanner;

import Card.Deck;
import Player.Bank;
import Player.Human;

public class Jeu {
	public static void main(String[] args) throws InterruptedException {
		// Variables
		Scanner sc = new Scanner(System.in);
		Deck deck;
		Bank bank = new Bank();
		Human joueur = null;
		boolean endGame = false;
		boolean replay = true;
		boolean logged = false;
		char cReplay;
		int win = 0;
		int mise;
		String nom = "";
		char choix;

		System.out.println(
				"                                                                                                                                                   \r\n"
						+ "                                                                                                                                                   \r\n"
						+ "BBBBBBBBBBBBBBBBB   lllllll                                      kkkkkkkk             jjjj                                      kkkkkkkk           \r\n"
						+ "B::::::::::::::::B  l:::::l                                      k::::::k            j::::j                                     k::::::k           \r\n"
						+ "B::::::BBBBBB:::::B l:::::l                                      k::::::k             jjjj                                      k::::::k           \r\n"
						+ "BB:::::B     B:::::Bl:::::l                                      k::::::k                                                       k::::::k           \r\n"
						+ "  B::::B     B:::::B l::::l   aaaaaaaaaaaaa      cccccccccccccccc k:::::k    kkkkkkkjjjjjjj  aaaaaaaaaaaaa      cccccccccccccccc k:::::k    kkkkkkk\r\n"
						+ "  B::::B     B:::::B l::::l   a::::::::::::a   cc:::::::::::::::c k:::::k   k:::::k j:::::j  a::::::::::::a   cc:::::::::::::::c k:::::k   k:::::k \r\n"
						+ "  B::::BBBBBB:::::B  l::::l   aaaaaaaaa:::::a c:::::::::::::::::c k:::::k  k:::::k   j::::j  aaaaaaaaa:::::a c:::::::::::::::::c k:::::k  k:::::k  \r\n"
						+ "  B:::::::::::::BB   l::::l            a::::ac:::::::cccccc:::::c k:::::k k:::::k    j::::j           a::::ac:::::::cccccc:::::c k:::::k k:::::k   \r\n"
						+ "  B::::BBBBBB:::::B  l::::l     aaaaaaa:::::ac::::::c     ccccccc k::::::k:::::k     j::::j    aaaaaaa:::::ac::::::c     ccccccc k::::::k:::::k    \r\n"
						+ "  B::::B     B:::::B l::::l   aa::::::::::::ac:::::c              k:::::::::::k      j::::j  aa::::::::::::ac:::::c              k:::::::::::k     \r\n"
						+ "  B::::B     B:::::B l::::l  a::::aaaa::::::ac:::::c              k:::::::::::k      j::::j a::::aaaa::::::ac:::::c              k:::::::::::k     \r\n"
						+ "  B::::B     B:::::B l::::l a::::a    a:::::ac::::::c     ccccccc k::::::k:::::k     j::::ja::::a    a:::::ac::::::c     ccccccc k::::::k:::::k    \r\n"
						+ "BB:::::BBBBBB::::::Bl::::::la::::a    a:::::ac:::::::cccccc:::::ck::::::k k:::::k    j::::ja::::a    a:::::ac:::::::cccccc:::::ck::::::k k:::::k   \r\n"
						+ "B:::::::::::::::::B l::::::la:::::aaaa::::::a c:::::::::::::::::ck::::::k  k:::::k   j::::ja:::::aaaa::::::a c:::::::::::::::::ck::::::k  k:::::k  \r\n"
						+ "B::::::::::::::::B  l::::::l a::::::::::aa:::a cc:::::::::::::::ck::::::k   k:::::k  j::::j a::::::::::aa:::a cc:::::::::::::::ck::::::k   k:::::k \r\n"
						+ "BBBBBBBBBBBBBBBBB   llllllll  aaaaaaaaaa  aaaa   cccccccccccccccckkkkkkkk    kkkkkkk j::::j  aaaaaaaaaa  aaaa   cccccccccccccccckkkkkkkk    kkkkkkk\r\n"
						+ "                                                                                     j::::j                                                        \r\n"
						+ "                                                                           jjjj      j::::j                                                        \r\n"
						+ "                                                                          j::::jj   j:::::j                                                        \r\n"
						+ "                                                                          j::::::jjj::::::j                                                        \r\n"
						+ "                                                                           jj::::::::::::j                                                         \r\n"
						+ "                                                                             jjj::::::jjj                                                          \r\n"
						+ "                                                                                jjjjjj                                                             ");

//		System.out.println(" _     _            _    _            _    \r\n"
//				+ "| |   | |          | |  (_)          | |   \r\n"
//				+ "| |__ | | __ _  ___| | ___  __ _  ___| | __\r\n"
//				+ "| '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /\r\n"
//				+ "| |_) | | (_| | (__|   <| | (_| | (__|   < \r\n"
//				+ "|_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\\r\n"
//				+ "                       _/ |                \r\n" + "                      |__/  ");

		while (replay) {

			endGame = false;

			deck = new Deck();
			deck.shuffle();
			bank.clearDeck();

			// Insert user name

			if (!logged) {
				do {
					System.out.print("Entrez votre login (2 à 20 caractères) : ");
					nom = sc.nextLine();
				} while (nom.length() < 2 || nom.length() > 20 || nom.equals("BANK"));
				joueur = new Human(nom);
			} else {
				joueur.printHistorique();
				joueur.clearDeck();
				joueur.canModifyAs = true;
			}

			if (joueur.getCredit() == 0) {
				System.out.println("Recharger le crédit de 100€.");
				joueur.rechargerCredit();
			}

			// Mise
			System.out.print("Combien misez-vous?\nmise : ");
			mise = joueur.mise();

			// First distribution
			System.out.println("\nDistribution en cours...\n");
			joueur.getCardFromDeck(deck);
			joueur.getCardFromDeck(deck);
			bank.getCardFromDeck(deck);

			bank.printCards();
			joueur.printCards();

			while (!endGame) {
				System.out.println("-----------------------------\n");
				System.out.println("Que voulez vous faire ?\n1. Tirer une carte.\n2. Check.");
				if (joueur.canModifyAs)
					System.out.println("3. Modifier valeur AS.");
				choix = sc.nextLine().charAt(0);

				switch (choix) {

				// Tirer une carte
				case '1':
					joueur.getCardFromDeck(deck);
					if (joueur.getScore() > 21) {
						endGame = true;
						win = -1;
					} else if (joueur.getScore() == 21) {
						endGame = true;
						while (bank.getScore() < 17)
							bank.getCardFromDeck(deck);
						if (bank.getScore() > 21) {
							win = 1;
						} else {
							win = joueur.compare(bank);
						}
					}
					break;

				// Check
				case '2':
					endGame = true;
					while (bank.getScore() < 17)
						bank.getCardFromDeck(deck);
					if (bank.getScore() > 21)
						win = 1;
					else
						win = joueur.compare(bank);
					break;

				// Modifier la valeur de l'AS
				case '3':
					if (joueur.canModifyAs)
						joueur.modifyAsValue();
					break;
				}

				// Display
				joueur.printCards();
				Thread.sleep(500);
				bank.printCards();
				Thread.sleep(500);

				System.out.println("\n-----------------------------");
			}

			// Check score

			if (win > 0) {
				if (joueur.getScore() == 21) {
					mise = (int) (mise * 1.5);
					System.out.println(" _     _            _    _            _    \r\n"
							+ "| |   | |          | |  (_)          | |   \r\n"
							+ "| |__ | | __ _  ___| | ___  __ _  ___| | __\r\n"
							+ "| '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /\r\n"
							+ "| |_) | | (_| | (__|   <| | (_| | (__|   < \r\n"
							+ "|_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\\r\n"
							+ "                       _/ |                \r\n"
							+ "                      |__/                 ");
				}
				System.out.println("GAGNE ! vous avec gagné " + mise + "€.");
				joueur.isLastPartyWins = true;
				joueur.lastMise = mise;
				joueur.setCredit(mise);
			} else if (win < 0) {
				System.out.println("PERDU ! vous avez perdu " + mise + "€.");
				joueur.isLastPartyWins = false;
				joueur.lastMise = mise;
				joueur.setCredit(-mise);
			} else {
				System.out.println("EGALITE ! vous recuperez votre mise de " + mise + "€.");
				joueur.lastMise = mise;
				joueur.isLastPartyWins = true;
			}

			// Update historique
			joueur.save();

			System.out.println("Rejouer en tant que " + joueur.getName() + "? (y/n) ou quitter le jeu ? (q)");
			cReplay = sc.nextLine().charAt(0);

			switch (cReplay) {
			case 'y':
				logged = true;
				break;
			case 'n':
				logged = false;
				break;
			case 'q':
				replay = false;
				System.out.println("A bientôt " + joueur.getName() + "!");
			}
		}
	}

}
