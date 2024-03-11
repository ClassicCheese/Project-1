import java.util.Random;

public class Pokemon extends Card
{
    private int hp;
    private int energy;
    Random rng = new Random();
    private String name;

    public Pokemon(int hp, String name){
        this.hp = InitialHp(hp);
        this.name = name;
        energy = 0;
    }

    public static Card getRandom(){
        Random rng = new Random();
        int num = rng.nextInt(4);
        switch(num){
            case 0: 
                Pikachu Pika = new Pikachu();
                return Pika;
            case 1:
                Electrode Elect = new Electrode();
                return Elect;
            case 2:
                Jolteon Jolt = new Jolteon();
                return Jolt;
            case 3:
                Raikou Rai = new Raikou();
                return Rai;
            default: return null;
        }
    }

    public int getHp(){
        return hp;
    }

    public int InitialHp(int inputHP){
        hp = rng.nextInt((inputHP-20), (inputHP+20));
        return hp;
    }

    public int setHp(int num){
        return hp = num;
    }

    public void heal(int num){
        hp += num;
    }

    public int getEnergy(){
        return energy;
    }

    public void setEnergy(int n){
        energy += n;
    }

    public void increaseEnergy(){
        energy++;
    }

    public void minusEnergy(int n){
        energy -= n;
    }

    
    @Override
    public String toString() {
        return "Pokemon: " + name + ", " + getHp() + "Hp, " + getEnergy() + " energy|";
    }

    void attack1(Pokemon Target){

    }

    void attack2(Pokemon Target){

    }

}
