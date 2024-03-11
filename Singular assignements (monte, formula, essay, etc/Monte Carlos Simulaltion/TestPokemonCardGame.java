
public class TestPokemonCardGame
{

    public static void main(String[] args){
        for(int m = 1; m < 61; m++){
            //PokemonCardGame test = new PokemonCardGame(m+1);
            int aPokemon = 0;
            int loop = 10000;
            double probability;
            for(int n = 0; n < loop; n++){
                PokemonCardGame test1 = new PokemonCardGame(m);

                test1.drawHand();
                if(test1.checkHand() == true){
                    aPokemon++;
                }
                test1.resuffle();
            }
            probability = (((double)aPokemon/loop)*100);
            System.out.println("The number of pokemon(s) in your deck: " + m);
            System.out.printf("The probability of getting a basic Pokemon is:%.2f%%%n ", probability);
            //System.out.println(probability);
        }

    }

}