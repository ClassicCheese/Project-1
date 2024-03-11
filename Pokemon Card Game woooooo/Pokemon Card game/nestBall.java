import java.util.ArrayList;
import java.util.Random;

public class nestBall extends Trainer implements TrainerAction
{
    
    //This method imitate te ability of the nestBall
    //This would scan through the deck looking for all the pokemons in
    //the deck, then pick at random a pokemon to be put in the bench
    public nestBall(){
        super("Nest Ball");
    }
    @Override
    public void playable(Player gameState){
        ArrayList<Card> deck = gameState.getDeck();
        ArrayList<Pokemon> bench = gameState.getBench();

        //Find all Pokemon
        ArrayList<Card> tempPokemon = new ArrayList<>();
        //booelan done = false;
        int i =0;
        while(i < deck.size()){
            if(deck.get(i) instanceof Pokemon){
                tempPokemon.add(deck.get(i));
                deck.remove(i);
            }
            i++;
        }
        System.out.println("Pokemon found so far: " + tempPokemon);
        System.out.println("Count: " + tempPokemon.size());
        
        Random rng = new Random();
        int saveRandomNum = rng.nextInt(tempPokemon.size());
        Pokemon holder = (Pokemon)tempPokemon.get(saveRandomNum);
        bench.add(holder);
        tempPokemon.remove(saveRandomNum);
        System.out.println(holder + " has been added to bench");
        
        for(int j = 0; j < tempPokemon.size(); j++){
            deck.add(tempPokemon.get(j));
        }
        
    }
    
}
