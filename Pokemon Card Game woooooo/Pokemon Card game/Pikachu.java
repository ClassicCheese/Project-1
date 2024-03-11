
public class Pikachu extends Pokemon implements Attackable
{

    public Pikachu(){
        super(120, "Pikachu");
    }

    
    public void attack1(Pokemon target){
        //deal 30 dmg
        int dmg = 30;
        if(getEnergy() >= 1){
            System.out.println("Pikachu, USE QUICK ATTACK");
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
            System.out.println("Pikachu, USE THUNDER SHOCK");
            int currentHp = target.getHp();
            int resultingHp = currentHp - dmg;
            target.setHp(resultingHp);
            System.out.println("Enemy's Pokemon took " + dmg + " damge");
            minusEnergy(2);
        }
    }

}