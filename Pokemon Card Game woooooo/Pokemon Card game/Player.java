import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;

public class Player
{
    private static Player currentPlayer;
    private static Player otherPlayer;
    private String name;

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> pool;
    private ArrayList<Pokemon> bench;
    private ArrayList<Card> discards;
    private Pokemon[] active;

    private static final int deckSize = 60;
    Scanner scan = new Scanner(System.in);
    Random rng = new Random();
    //Create a deck of 20 each type
    public Player(String name){
        deck = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        pool = new ArrayList<Card>();
        bench = new ArrayList<Pokemon>();
        discards = new ArrayList<Card>();
        active = new Pokemon[1];
        this.name = name;

        //Adding random pokemon (4 currrently) to deck with limit of 20 pokemons
        for(int n = 0; n < 20; n++){
            Card randomPoke = Pokemon.getRandom();
            deck.add(randomPoke);
        }

        //Same here but just rainbow energy for now
        for(int i = 0; i < 20; i++) {
            deck.add(new Energy());    
        }

        //Same here but with 4 trainers cards
        for(int i = 0; i < 20; i++){
            Card randomTrainer = Trainer.getRandom();
            deck.add(randomTrainer);
        }
    }

    //Set some stuffs up for the Player
    public String getPlayerName(){
        return name;
    }   
    //This to switch between Player 1 and Player 2, and to use it in the playable() method.
    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public static void setOtherPlayer() {
        otherPlayer = currentPlayer;
    }

    public static void resetCurrentPlayer() {
        currentPlayer = null;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    //End of names calling

    //Draw card at random so you don't have to shuffle the deck.
    public Card drawCard(){
        int cardIndex = rng.nextInt((deck.size()));
        Card drawnCard = deck.get(cardIndex);
        deck.remove(cardIndex);
        return drawnCard;
    }

    //Draw the first hand at the beginning of the game
    public void drawHand(){
        for(int i = 0; i < 7; i++){
            hand.add(drawCard());
        }
    }

    //This method would check hand to see if have a pokemon
    public boolean checkHand(){
        boolean havePokemon = false;
        for(int i = 0; i < hand.size(); i++){
            Card currentCard = hand.get(i);
            if(currentCard instanceof Pokemon){
                return true;
            }
        }
        return false;
    }

    //This draw the prize pool
    //Apparently it's faced down until you get it.
    public void drawPool(){
        for(int i = 0; i < 1; i++){
            pool.add(drawCard());
        }
    }

    //check for energy card in hand before attach to a pokemon
    public boolean checkEnergy(){
        for(Card ele : hand){
            if(ele instanceof Energy);
            return true;
        }
        return false;
    }

    //This method show the prize pool size;
    public int PoolSize(){
        return pool.size();
    }

    //This to display the cards in deck.
    public ArrayList<Card> getDeck(){
        return deck;
    }

    //to Display the pokemon that's currently on the battlefield
    public Pokemon getActive(){
        if(active.length  > 0){
            return active[0];        
        }
        else{
            return null;
        }
    }

    //This method is to display the Pokemon that is in the bench
    public ArrayList<Pokemon> getBench(){
        return bench;
    }

    //This method return the Player's hand
    public ArrayList<Card> getHand(){
        return hand;
    }

    //This return the pool of discarded cards
    //Just in case
    public ArrayList<Card> getDiscard(){
        return discards;
    }

    //I tried to make this show the battlefield:
    //Ex:
    //                Pokemon

    
    //                Pokemon
    public void showBattle(){
        System.out.println("\n\n");
        if(otherPlayer.active[0] instanceof Pokemon == false){
            System.out.println();
        }
        else if (otherPlayer.active[0] instanceof Pokemon){
            System.out.println("Enenmy:        " + otherPlayer.getActive());
        }
        System.out.println("\n\n\n");
        if(currentPlayer.active[0] instanceof Pokemon == false){
            System.out.println();
        } 
        else if (currentPlayer.active[0] instanceof Pokemon){
            System.out.println("You:           " + currentPlayer.getActive() + "\n");
        }
    }

    //This would randomly draw a prize after the Player defeats the enemy pokemon
    public void getPrize(){
        int prizeNum = rng.nextInt(pool.size());
        hand.add(pool.get(prizeNum));
        System.out.println("You received: " + pool.get(prizeNum) + " from prize pool");
        pool.remove(prizeNum);
        System.out.println("Current prize pool is :" + pool.size());
    }

    //This method prepare the Player to use a trainer card
    //by find all trainer cards in hand
    public ArrayList<Card> getTrainer(){
        ArrayList<Card> tempCard = new ArrayList<>();
        for(Card ele : hand){
            if(ele instanceof Trainer){
                tempCard.add(ele);
            }
        }
        return tempCard;
    }

    //This method prepare the game before any Player can make a move
    public void preBattle(){
        drawHand();
        while(!checkHand()){
            reshuffle();
            drawHand();
        }
        drawPool();
    }

    //This method reshuffle your hand
    public void reshuffle(){
        for(int i = 0; i < hand.size(); i++){
            deck.add(hand.get(i));
            hand.remove(i);
        }
    }

    //This method check if the active pokemon is defeated and if so, send a Pokemon from bench to battlefield
    public void checkPokeDefeat(){
        if(otherPlayer.getActive().getHp() <= 0){
            System.out.println("Enemy defeated");
            otherPlayer.discards.add(otherPlayer.getActive());
            otherPlayer.active = new Pokemon[1];
            getPrize();
        }
    }

    //This method check the win conditions
    //When I skimmed through the win cons, I found that there are 3 simple ways to win:
    //1) when there are no prize pool left
    //2) when the oponent have no pokemon in battlefield and bench
    //3) when you run out of cards in your deck
    public void checkWin(){
        if((otherPlayer.getActive() instanceof Pokemon == false) && otherPlayer.getBench().isEmpty() || pool.isEmpty() || otherPlayer.deck.isEmpty()){
            System.out.println(this.getPlayerName() + " won!");
            System.out.println("  (\\__/)");
            System.out.println("  (o^.^)");
            System.out.println("z(_(\")(\")");
        }
    }

    //Need I say more?
    //I googled how ot do this.
    public static void playMusic(){
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=EErxstAfMUM"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Make engine for program
    //This is where the program would piece together
    public static void run(){
        playMusic();
        System.out.println("You should be hearing something right now, if not, check your other tabs ;)");
        System.out.println("     ♫  ♪♪   (\\__/)  ♪    ♬");
        System.out.println("    ♬        (o^.^)     ♬  ");
        System.out.println("       ♬   z(_(\")(\")  ♬");

        Player Player1 = new Player("Player 1");
        Player Player2 = new Player("Player 2");

        Player1.preBattle();
        Player2.preBattle();
        Player1.FirstTurn();
        while(Player1.PoolSize() > 0 && Player2.PoolSize() > 0){
            Player2.turn();
            Player2.checkWin();
            Player1.turn();
            Player1.checkWin();
        }
    }

    //This method imitates a round of pokemon card game
    public void turn(){

        Player.setCurrentPlayer(this); // Set the current Player to the current instance
        showBattle();
        Card temp = drawCard();
        hand.add(temp);
        System.out.println("You've drawn: " + temp);

        System.out.println(getPlayerName() + "'s turn");

        displayInfo();
        displayOption();

        Player.setOtherPlayer(); //Set the current Player to the other Player at the end of round
        Player.resetCurrentPlayer(); // Reset the current Player after the turn
    }

    //Since the first turn is differ from the other turns (can't draw)
    //I make a seperate turn just for it.
    public void FirstTurn(){
        System.out.println("Since you played first hand, you can not draw or attack");
        System.out.println("\n" + getPlayerName() + "'s turn:");

        Player.setCurrentPlayer(this); // Set the current Player to the current instance

        displayInfo();
        displayOption();

        Player.setOtherPlayer();
        Player.resetCurrentPlayer(); // Reset the current Player after the turn
    }

    //This method display the menu option
    public void displayOption(){
        System.out.println("\n" + this.getHand());
        System.out.println("1) Use trainer cards:");
        System.out.println("2) add Pokemon to active battleground:");
        System.out.println("3) add Pokemon to bench:");
        System.out.println("4) Attach energy to pokemon:");
        System.out.println("5) Attack");
        System.out.println("6) End turn:");
        int choice = scan.nextInt();
        while(choice < 1 || choice > 6){
            System.out.println("Invalid choice, try again");
            choice  = scan.nextInt();
        }
        process(choice);
    }

    //This method display the informations of the Player beforehand
    public void displayInfo(){
        System.out.println("Hand: " + getHand());
        System.out.println("Bench: " + getBench());
        System.out.println("Number of prize pool card left: " + pool.size());
    }

    //This was once a gigantic method
    //They hold to much power together, so I broke them apart
    //This method process the Player's choice
    //I didn't have enough time to limit player's usage of energy, set pokemon, and trainer usage
    public void process(int n){
        switch(n){
            case 1:
                useTrainer();
                break;
            case 2: 
                pokemonToActive();
                break;
            case 3:
                addPokemonToBench();
                break;
            case 4:
                attachEnergy();
                break;
            case 5:
                attack();
                break;
            default:
                break;
        }
    } 

    //This method process the trainer in use
    private void useTrainer() {
        System.out.println(this.getHand());
        System.out.println("\nPick the trainer you want to use from your hand (1-" + hand.size() + ")");

        int num = scan.nextInt();
        while(hand.get(num-1) instanceof Trainer == false){
            System.out.println("Wrong position, enter again");
            System.out.println("type any negative number to cancel");
            num = scan.nextInt();
            if(num < 0){
                break;
            }
        }
        Trainer selectedTrainer = (Trainer) hand.get(num-1);
        hand.remove(num-1);
        selectedTrainer.playable(this);
        System.out.println("You've used: " + selectedTrainer);
        displayOption();
    }

    //This method add pokemon from either your hand or bench to battleground
    private void pokemonToActive() {
        System.out.println("\nPick a pokemon to send to battle");
        System.out.println("1) From your hand");
        System.out.println("2) From your bench");
        displayInfo();

        int send = scan.nextInt();
        while(send < 1 || send > 2){
            System.out.println("Invalid choice, enter again");
            send = scan.nextInt();
        }
        if(send == 1){
            System.out.println("\nPick the Pokemon you want to send to battle (1-" + hand.size() + ")");
            System.out.println(this.getHand());

            int hand1 = scan.nextInt();
            while(hand.get(hand1-1) instanceof Pokemon == false){
                System.out.println("Wrong position, enter again");
                System.out.println("type any negative number to cancel");
                hand1 = scan.nextInt();
                if(hand1 < 0){
                    break;
                }
            }
            if(active[0] instanceof Pokemon){
                if(bench.size() >= 5){
                    System.out.println("You have reached the limit of pokemons in bench, can not add active Pokemon to bench");
                }else {
                    bench.add(active[0]);
                    active = new Pokemon[1];
                    Pokemon temp = (Pokemon)hand.get(hand1-1);
                    hand.remove(hand1-1);
                    active[0] = temp;
                    System.out.println("Successfully added: " + temp + " to the battlefield");
                }
            }
            active = new Pokemon[1];
            Pokemon temp = (Pokemon)hand.get(hand1-1);
            hand.remove(hand1-1);
            active[0] = temp;
            System.out.println("Successfully added: " + temp);
        }
        else {
            System.out.println(this.getBench());
            System.out.println("Choose a Pokemon from bench (1-" + bench.size());
            System.out.println("Press any negative number to return to previous menu");
            int bench1 = scan.nextInt();
            if(bench1 < 0){
                process(3);
            }
            while(bench1 < 1 || bench1 > bench.size()){
                System.out.println("Invalid choice, pick again");
                bench1 = scan.nextInt();
            }
            Pokemon temp = (Pokemon) bench.get(bench1 - 1);
            bench.remove(bench1 - 1);
            if(active[0] instanceof Pokemon){
                bench.add(active[0]);
            }
            active[0] = temp;
            System.out.println("Successfully added: " + temp);
        }
        displayOption();
    }

    //This method add pokemon from hand to bench
    private void addPokemonToBench() {
        System.out.println("\nPick a pokemon from your hand to add to bench (1-" + hand.size());
        System.out.println(this.getHand());
        int pokenum = scan.nextInt();
        while(hand.get(pokenum-1) instanceof Pokemon == false){
            System.out.println("Wrong position, enter again");
            System.out.println("type any negative number to cancel");
            pokenum = scan.nextInt();
            if(pokenum < 0){
                displayOption();
                break;
            }
        }
        if(pokenum > 0){
            Pokemon selectedPokemon = (Pokemon) hand.get(pokenum-1);
            bench.add(selectedPokemon);
            hand.remove(pokenum-1);
            System.out.println("Successfully added " + selectedPokemon + " to bench.");
        }
        displayOption();
    }

    //This method process the attachment of energy to a pokemon
    //Whether it's in bench or active
    private void attachEnergy() {
        if(!checkEnergy()){
            System.out.println("\nYou have no energy card in your hand");
        }else{
            System.out.println("\nSelect the pokeomon you want to attach energy to");
            System.out.println("1)Active");
            System.out.println("2)Bench");
            int in = scan.nextInt();
            while(in < 1 || in > 2){
                System.out.println("Invalid number, try again");
                in = scan.nextInt();
            }
            if(in == 1){
                for(Card ele : hand){
                    if (ele instanceof Energy){
                        hand.remove(ele);
                        break;
                    }
                }
                active[0].increaseEnergy();
                System.out.println("Successfully attached energy");
            }
            else{
                if(bench.size() == 0){
                    System.out.println("No pokemon in bench");
                }
                System.out.println("What pokemon in bench would you like to attach energy to (1-" + bench.size());
                int un = scan.nextInt();
                while(un < 0 || un > (bench.size())){
                    System.out.println("Invalid number,try again");
                    un = scan.nextInt();
                }
                for(Card ele : hand){
                    if (ele instanceof Energy){
                        hand.remove(ele);
                        break;
                    }
                }
                Pokemon tempp = (Pokemon) bench.get(un-1);
                tempp.increaseEnergy();
                System.out.println("Successfully attached energy");
            }

        }
        displayOption();
    }

    //This method deal with the process of the attack system with the attached energy
    //Aswell as check for defeated pokemon to be sent to the graveyard and ask to add a new pokemon
    //And get prize card aswell;
    private void attack() {
        System.out.println("\n1) Attack 1 - 1 energy required");
        System.out.println("2) Attack 2 - 2 energy required");
        int atk = scan.nextInt();
        while (atk < 1 || atk > 2){
            System.out.println("Invalid number, enter again");
            atk = scan.nextInt();
        }
        if(atk == 1){
            if(currentPlayer.getActive().getEnergy() >= 1){
                currentPlayer.getActive().attack1(otherPlayer.getActive());
                checkPokeDefeat();
            }
        }
        else{
            if(currentPlayer.getActive().getEnergy() >= 2){
                currentPlayer.getActive().attack2(otherPlayer.getActive());
                checkPokeDefeat();}
        }
    }

}
