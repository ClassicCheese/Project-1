
/**
 * Write a description of class Raikou here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Raikou extends Pokemon implements Attackable
{
    public Raikou(){
        super(180, "Raikou");
    }

    
    public void attack1(Pokemon target){
        //deal 60 dmg
        int dmg = 60;
        if(getEnergy() >= 1){
            System.out.println("RAIKOU, USE THUNDER  SHOCK");
            int currentHp = target.getHp();
            int resultingHp = currentHp - dmg;
            target.setHp(resultingHp);
            System.out.println("Enemy's Pokemon took " + dmg + " damge");
            minusEnergy(1);
        }
    }

    public void attack2(Pokemon target){
        //deal 60 dmg
        int dmg = 90;
        if(getEnergy() >= 2){
            System.out.println("RAIKOU, USE THUNDER FANG");
            int currentHp = target.getHp();
            int resultingHp = currentHp - dmg;
            target.setHp(resultingHp);
            System.out.println("Enemy's Pokemon took " + dmg + " damge");
            minusEnergy(2);
        }
    }
}
