
/**
 * Write a description of class Jolteon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jolteon extends Pokemon implements Attackable
{
    public Jolteon(){
        super(140, "Jolteon");
    }

    
    public void attack1(Pokemon target){
        //deal 30 dmg
        int dmg = 40;
        if(getEnergy() >= 1){
            System.out.println("JOLTEON, USE PIN MISSILE");
            int currentHp = target.getHp();
            int resultingHp = currentHp - dmg;
            target.setHp(resultingHp);
            System.out.println("Enemy's Pokemon took " + dmg + " damge");
            minusEnergy(1);
        }
    }

    public void attack2(Pokemon target){
        //deal 60 dmg
        int dmg = 60;
        if(getEnergy() >= 2){
            System.out.println("JOLTEON, USE THUNDERBOLT");
            int currentHp = target.getHp();
            int resultingHp = currentHp - dmg;
            target.setHp(resultingHp);
            System.out.println("Enemy's Pokemon took " + dmg + " damge");
            minusEnergy(2);
        }
    }
    
}
