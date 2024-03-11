import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Potion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cynthia extends Trainer implements TrainerAction
{
    public Cynthia(){
        super("Cynthia");
    }
    
    //This card shuffle your hand into deck and draw 6 cards
    @Override
    public void playable(Player gameState){
        ArrayList<Card> deck = gameState.getDeck();
        ArrayList<Card> hand = gameState.getHand();

        for(Card ele : hand){
            deck.add(ele);
        }
        hand.clear();
        
        for(int i = 0; i < 6; i++){
            Random rng = new Random();
            int num = rng.nextInt(deck.size());
            hand.add(deck.get(num));
            deck.remove(num);
        }
    }
}
