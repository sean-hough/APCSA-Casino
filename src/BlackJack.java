import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BlackJack {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		String name = question("Welcome to the Casino, what's your name?", "\\S+", in);
		System.out.println("Welcome, " + name + ", we've given you 20 chips to start off. Don't waste them!\n\n");
		int chips = 20;
		do {
			System.out.println("Chips: " + chips);
			int ante = 0;
			do {	
				ante = Integer.parseInt(question("What's your ante? Remember, you can't bet more chips than you own", "\\d+", in));	
			} while (ante > chips || ante <= 0);
			if(playBlackJack()) {
				chips += ante;
			} else {
				chips -= ante;
			}
			System.out.println("=======================================================================================");
			if(chips == 0) {
				System.out.println("You've run out of chips! You can't continue, even if you'd want to.");
			}
		} while(chips != 0 && question("You currently have "+ chips + " chip(s). would you like to continue, or cash out?", Arrays.asList("continue", "cash out"), in).equals("continue"));
	}
	
	public static String question(String q, List<String> validAnswers, Scanner in) {
		String r = "";
		do {
			System.out.println(q);
			r = in.nextLine().toLowerCase();
		} while(validAnswers.indexOf(r) == -1);
		return r;
	}
	public static String question(String q, String regex, Scanner in) {
		String r = "";
		do {
			System.out.println(q);
			r = in.nextLine();
		} while(!r.matches(regex));
		return r;
	}
	
	public static boolean playBlackJack() {
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
						"Ten", "King", "Queen", "Jack", "Ace" };
		int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,11};
		Deck d = new Deck(suits, ranks, values);
		
		Hand dealer = new Hand();
		dealer.add(d.deal());
		dealer.add(d.deal());
		Hand player = new Hand();
		player.add(d.deal());
		player.add(d.deal());
		
		System.out.println("Starting BlackJack...\n");
		System.out.println("Dealers Hand:\n" + dealer.get(0) + "\n?????????????");
		System.out.println("\nYour Hand:\n" + player);
		
		String decision = "";
		while(player.score() != 0 && !decision.equals("stand")) {
			decision = question("\nHit or Stand? Your current hand value is " + player.score(), Arrays.asList("hit", "stand"), in);
			if (decision.equals("hit")) {
				player.add(d.deal());
				System.out.println("\nYour new hand:\n" + player);
			} 
		} 		
		if(player.score() == 0) {
			System.out.println("You busted... Better luck next time!");
			return false;
		}
		System.out.println("Your final hand value is " + player.score());
		System.out.println("\nNow it's the dealer's turn...");
		while(dealer.score() <= 17 && dealer.score() != 0) {
			dealer.add(d.deal());
		}
		System.out.println("The dealer's final hand:\n" + dealer);
		if(dealer.score() == 0) {
			System.out.println("The dealer busted! Congratulations, you've won!");
			return true;
		} if (dealer.score() == 21) {
			System.out.println("The dealer has BlackJack... Better luck next time!");
			return false;
		}
		System.out.println("The dealer's final hand value is " + dealer.score());
		int result = player.compareTo(dealer);
		if(result == 1) {
			System.out.println("Congratulations, you've won!"); 
			return true;
		} else {
			System.out.println("Better luck next time!");
			return false;
		}
	}
}