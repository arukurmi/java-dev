package games.snakeandladder;

import java.util.*;

public class SnakeAndLadderGame {
    public static void main(String[] args){
        Dice dice = new Dice(1);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter number of players: ");
        int players = scanner.nextInt();
        scanner.nextLine();
        Queue<Player> playerQueue = new LinkedList<>();
        Map<Player, Integer> startingPositions = new HashMap<>();
        for (int i = 0; i < players; i++) {
            System.out.println("Enter your name player" + (i + 1) + " : ");
            String playerName = scanner.nextLine();
            Player player1 = new Player(playerName);
            playerQueue.offer(player1);
            startingPositions.put(player1, 1);
        }
        List<SnakeAndLadderJumper> snakes = new ArrayList<>();
        List<SnakeAndLadderJumper> ladders = new ArrayList<>();
        SnakeAndLadderJumper snake1 = new SnakeAndLadderJumper(93, 43);
        SnakeAndLadderJumper snake2 = new SnakeAndLadderJumper(56, 3);
        SnakeAndLadderJumper snake3 = new SnakeAndLadderJumper(29, 13);
        SnakeAndLadderJumper ladder1 = new SnakeAndLadderJumper(13, 43);
        SnakeAndLadderJumper ladder2 = new SnakeAndLadderJumper(43, 88);
        SnakeAndLadderJumper ladder3 = new SnakeAndLadderJumper(9, 16);
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);
        ladders.add(ladder1);
        ladders.add(ladder2);
        ladders.add(ladder3);

        Game game = new Game(dice, playerQueue, snakes, ladders, startingPositions, 100);

        game.playGame();
        scanner.close();
    }
}
