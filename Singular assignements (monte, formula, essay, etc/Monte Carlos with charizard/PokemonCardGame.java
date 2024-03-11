import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//60 cards limit, in order to take a turn, you needed to have a basic pokemon in your hand.

//Let's write a monte carlo simulation. That means, using raw brute force, try to figure out something interesting.

//What if your deck had exactly 1 pokemon. How many times would you expect to have to
//"Mulligan" in order to have your only pokemon in your hand.

//What if your deck had 2? ect.

//Write a simulation that shows the expected mulligans at 1-60 pokemons in your deck.

public class PokemonCardGame {
	// A deck of cards.
	private ArrayList<Card> deck;// constructor job. = new Card[];
	private ArrayList<Card> hand;
	private ArrayList<Card> pool;
	private static final int deckSize = 60;

	public PokemonCardGame(int num) {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		pool = new ArrayList<Card>();

		for (int n = 0; n < 15; n++) {
			deck.add(new Pokemon());
		}

		for (int i = 0; i < 10; i++) {
			deck.add(new Energy());
		}

		for (int i = 0; i < num; i++) {
			deck.add(new Rare_Candy());
		}

		for (int i = 25 + num; i < deckSize; i++) {
			deck.add(new Trainer());
		}
	}

	public Card drawCard() {

		Random rng = new Random();
		int cardIndex = rng.nextInt(deck.size()); // Find a random card
		Card drawnCard = deck.get(cardIndex);
		deck.remove(cardIndex);
		return drawnCard;
	}

	public void drawHand() {

		for (int i = 0; i < 7; i++) {
			hand.add(drawCard());
		}
	}

	public boolean checkHand() {
		for (int i = 0; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if (currentCard instanceof Pokemon) {
				return true;
			}
		}
		return false;
	}

	public void drawPool() {
		for (int i = 0; i < 6; i++) {
			pool.add(drawCard());
		}
	}

	public boolean checkPool(int m) {
		int count = 0;
		for (Card ele : pool) {
			if (ele instanceof Rare_Candy) {
				count++;
				if (count == m) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkPool() {
		for (Card ele : pool) {
			if (ele instanceof Rare_Candy) {
				return true;
			}
		}
		return false;
	}

	// Make engine for program
	public void run() {
		drawHand();
		// System.out.println("Has Pokemon: " + checkHand());

	}

	public void reshuffle() {
		for (int i = 0; i < hand.size(); i++) {
			deck.add(hand.get(i));
		}
		hand.clear();
	}

}
