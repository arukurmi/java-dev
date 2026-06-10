package Practice.games.snakeandladder;

public class Dice {
    private final int numberOfDice;

    public Dice(int numberOfDice){
        this.numberOfDice = numberOfDice;
    }

    public int rollDice(){
        int roll = (int) (1+(this.numberOfDice)*(6)*(Math.random()));
        System.out.print("ROLLED: " + roll + "\n");
        return roll;
    }
}
