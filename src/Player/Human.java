package Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Card.Card;
import Card.Deck;
import Tools.CsvTool;
import Tools.NumericTool;

public class Human extends Bank{
	private int credit;
	public boolean isLastPartyWins;
	public int lastMise;
	public boolean canModifyAs=true;

	public Human(String name) {
		CsvTool histo = null;
		deckPlayer = new ArrayList<Card>();
		login=name;

			try {
				histo = new CsvTool();
			} catch (IOException e) {
				System.out.println("ERREUR : Ouverture de l'historique des parties.");
			}
			String line = histo.getHistoriqueByUser(name);
			if (line == null) {
				login = name;
				credit = 100;
				isLastPartyWins = true;
				lastMise = 0;
				System.out.println("\nBienvenue " + login + ", Tu es crédité de 100€ pour ta première venue.\n");
			} else {
				credit = Integer.parseInt(line.split("\t")[1]);
				lastMise = Integer.parseInt(line.split("\t")[2]);
				isLastPartyWins = Boolean.getBoolean(line.split("\t")[3]);
				System.out.println("\nWelcome back "+login+", t'as "+credit+"€, ta dernière mise était de "+lastMise+"€.\n");

			}
	}
	
	public Human() {
		deckPlayer=new ArrayList<Card>();
	}
	
	public void setCredit(int mise) {
		credit+=mise;
	}
	public int getCredit() {
		return credit;
	}
	
	@Override
	public void modifyAsValue() {
		int nbAs = 0;
		for (Card card : deckPlayer) {
			if (card.isAs()) {
				nbAs++;
				card.numero.setValue(setValueAs(nbAs));
			}
		}
		canModifyAs=false;
	}
	
	public void rechargerCredit() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Mail Paypal : ");
		String email = sc.nextLine();
		System.out.print("Mot de passe ?");
		String mdp = sc.nextLine();
		credit=100;
		System.out.println("Vous avez 100€ en crédit.");
		
		
	}

	private int setValueAs(int nbAs) {
		Scanner sc = new Scanner(System.in);
		String entreeUser;
		do {
			System.out.println("Quel valeur pour l'As n°" + nbAs + " ? 1 ou 11?");
			entreeUser = sc.nextLine();
		} while (!entreeUser.equals("11") && !entreeUser.equals("1"));
		return Integer.parseInt(entreeUser);
	}

	public int mise() {
		String res;
		int resInt;
		Scanner sc = new Scanner(System.in);
		do {
			res = sc.nextLine();
			if (NumericTool.isNumeric(res))
				resInt = Integer.parseInt(res);
			else
				resInt = 0;
		} while (resInt > credit || resInt < 1);
		return resInt;

	}

	public String getName() {
		return login;
	}
	
	public void save() {
		CsvTool csv = null;
		try {
			csv = new CsvTool();
		} catch (IOException e) {
			System.out.println("ERREUR : Ouverture fichier.");
		}
		try {
			csv.UpdateProfile(login, credit, lastMise, isLastPartyWins);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printHistorique() {
		System.out.println(login+", t'as "+credit+"€, ta dernière mise était de "+lastMise+"€.");
	}
}
