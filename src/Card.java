
public class Card {
	private String suit;
	private String rank;
	private int value;

	//constructor inspired by AP Lab elevens
	public Card(String suit, String rank, int value) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	public String getSuit() {
		return suit;
	}
	public String getRank() {
		return rank;
	}
	
	//toString inspired by AP Lab elevens
	public String toString() {
		return rank + " of " + suit;
	}
}
