import java.util.ArrayList;
import java.util.Random;

public class Professors_research extends Trainer implements TrainerAction
{

    //This method imitate te ability of Professor's research
    //This would discard your hand and draw 7 new cards from your deck
    public Professors_research(){
        super("Professor's Research");
    }

    
    //I'm running out of time so I can only do the necessary parts
    @Override
    public void playable(Player gameState){
        ArrayList<Card> deck = gameState.getDeck();
        ArrayList<Card> hand = gameState.getHand();
        ArrayList<Card> discards = gameState.getDiscard();

        for(Card ele : hand){
            discards.add(ele);
        }

        hand.clear();

        for(int i = 0; i < 7; i++){
            Random rng = new Random();
            int num = rng.nextInt(deck.size());
            hand.add(deck.get(num));
            deck.remove(num);
        }
    }

}
