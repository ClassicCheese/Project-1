import java.util.Random;

/**
 * Write a description of class Trainer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trainer extends Card
{
    private String name;
    
    public static Card getRandom(){
        Random rng = new Random();
        int num = rng.nextInt(4);
        switch(num){
            case 0: 
                nestBall nest = new nestBall();
                return nest;
            case 1:
                Professors_research Psearch = new Professors_research();
                return Psearch;
            case 2:
                Cynthia cyn = new Cynthia();
                return cyn;
            case 3:
                Potion80 potion = new Potion80();
                return potion;
            default: return null;
        }
    }
    public Trainer(String name){
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Trainer: " + name  + "|";
    }
    
    public void playable(Player play){
        
    }
}
