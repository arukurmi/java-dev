package games.snakeandladder;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Game {
    private final Dice dice;
    private final Queue<Player> playerQueue;
    private final List<SnakeAndLadderJumper> snakes;
    private final List<SnakeAndLadderJumper> ladders;
    private final Map<Player, Integer> currentPositions;
    int boardSize;

    public Game(Dice dice, Queue<Player> playerQueue, List<SnakeAndLadderJumper> snakes, List<SnakeAndLadderJumper> ladders, Map<Player, Integer> currentPositions, int boardSize){
        this.dice = dice;
        this.playerQueue = playerQueue;
        this.snakes = snakes;
        this.ladders = ladders;
        this.currentPositions = currentPositions;
        this.boardSize = boardSize;
    }

    public void playGame(){
        while(playerQueue.size() > 1){
            Player currentPlayer = playerQueue.poll();
            int currentPosition = currentPositions.get(currentPlayer);
            System.out.println(currentPlayer.getName() + " Rolling!");
            int steps = dice.rollDice();
            int finalPos = currentPosition + steps;
            if(finalPos > boardSize){
                playerQueue.add(currentPlayer);
            } else if(finalPos == boardSize){
                System.out.println(currentPlayer.getName() + " won the game!\n");
            } else{
                int[] currentPos = new int[1];
                currentPos[0] = finalPos;
                boolean snakeBite = false;

                for (SnakeAndLadderJumper snake : snakes) {
                    if (snake.startPoint == finalPos) {
                        finalPos = snake.endPoint;
                        snakeBite = true;
                        break;
                    }
                }
                for (SnakeAndLadderJumper ladder : ladders) {
                    if (ladder.startPoint == finalPos) {
                        finalPos = ladder.endPoint;
                        break;
                    }
                }
                if(currentPos[0] != finalPos) {
                    if(snakeBite) System.out.println(currentPlayer.getName().toUpperCase() + " Bitten by snake! now at position: " + finalPos);
                    else System.out.println(currentPlayer.getName().toUpperCase() + " Climbed the ladder! now at position: " + finalPos);
                }
                if(currentPos[0] == boardSize){
                    System.out.println(currentPlayer.getName().toUpperCase() + " won the game!\n");
                } else {
                    currentPositions.put(currentPlayer, finalPos);
                    System.out.println(currentPlayer.getName().toUpperCase() + " is at position " + finalPos);
                    playerQueue.offer(currentPlayer);
                }
            }
        }
        System.out.println("\n\nGAME OVER!\n\n");
    }
}
