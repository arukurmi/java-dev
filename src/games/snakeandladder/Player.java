package games.snakeandladder;

public class Player {
    public final String name;

    public Player(String playerName){
        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        this.name = playerName;
    }

    public String getName(){
        return this.name;
    }
}
