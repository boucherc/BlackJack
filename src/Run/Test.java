package Run;
import java.io.IOException;

import Card.Deck;
import Player.Player;
import Tools.CsvTool;

public class Test {
	public static void main(String[] args) {
		Deck d = new Deck();
		d.shuffle();
		System.out.println(d);
		
		Player p = new Player();
		p.getCardFromDeck(d);
		p.getCardFromDeck(d);
		System.out.println(p);
		System.out.println(p.getScore());
		p.modifyAsValue();
		System.out.println(p.getScore());
		
//		try {
//			CsvTool csv = new CsvTool();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
