package Card;

public class Card {
	
	public CardColor couleur;
	public CardNumber numero;
	private boolean isAs=false;
	
	public Card(CardColor couleur, CardNumber numero) {
		super();
		this.couleur = couleur;
		this.numero = numero;
		if(numero.getValue()==1) isAs=true;
	}
	
	public String toString() {
		return numero+" DE " + couleur;
	}
	
	public boolean isAs() {
		return isAs;
	}
	
	
}
