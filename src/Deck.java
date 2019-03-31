
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck extends ArrayList<Card>{

	public Deck(String[] suits, String[] ranks, int[] values) {
		for (int i = 0; i < ranks.length; i++) {
			for (String suitString : suits) {
				this.add(new Card(ranks[i], suitString, values[i]));
			}
		}
		Collections.shuffle(this);
	}

	public Card deal() {
		if(this.size() > 0) {
			return this.remove(0);
		}
		return null;
	}
}
