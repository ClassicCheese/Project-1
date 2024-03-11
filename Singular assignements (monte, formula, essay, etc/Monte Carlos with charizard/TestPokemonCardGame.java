
public class TestPokemonCardGame {

	public static void main(String[] args) {
		final int loop = 10000;

		System.out.println("Since all the nunmber of rare candy need to be in the prize pool to");
		System.out.println("Consider the deck is bricked. thus, the counter for it will only");
		System.out.println("count if all rate candy is in the prize pool\n");
		
		for (int m = 1; m < 5; m++) {
			int AllCandyPool = 0;
			int AtleastOneCandy = 0;
			double extraCred = 0;
			double conditional;
			
			// Did you ask for 2 things here?
			// One for the odd that your hand is bricked
			// Another is just to find the probability that atleast 1 rare candy is in your
			// prize pool?

			// What I did here is I find the odd of the deck is bricked (the prob of all
			// rare candies in your prize pool)
			System.out.println("Number of rare candy is deck: " + m);
			for (int n = 0; n < loop; n++) {
				PokemonCardGame test1 = new PokemonCardGame(m);
				test1.drawHand();

				while (!test1.checkHand()) {
					test1.reshuffle();
					test1.drawHand();
				}

				test1.drawPool();

				// For extra credit
				// Find the probability that at least one rare candy is in your prize pool
				if (test1.checkPool()) {
					AtleastOneCandy++;
				}
				// Since all the nunmber of rare candy need to be in the prize pool to
				// Consider the deck is bricked. thus, the counter for it will only
				// count if all rate candy is in the prize pool
				// m is the number of rare candy that is in the deck
				if (test1.checkPool(m)) {
					AllCandyPool++;
				}
			}
			conditional = (double) AllCandyPool / loop;
			extraCred = (double) AtleastOneCandy / loop;
			System.out.println("The probability that your deck is bricked is: " + conditional);
			System.out.println("The probability that at least 1 rare candy is in  your prize pool is: " + extraCred + "\n");
		}

	}

}