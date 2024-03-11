
public class Electrode extends Pokemon implements Attackable
{
    public Electrode(){
        super(110, "Electrode");
    }
    
    
    public void attack1(Pokemon target){
        //deal 30 dmg
        int dmg = 35;
        if(getEnergy() >= 1){
            System.out.println("ELCTRODE, USE LOW KICK");
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
            System.out.println("ELECTRODE, USE THUNDER PUNCH");
            int currentHp = target.getHp();
            int resultingHp = currentHp - dmg;
            target.setHp(resultingHp);
            System.out.println("Enemy's Pokemon took " + dmg + " damge");
            minusEnergy(2);
        }
    }
}
