package games.tictactoe;

public class TicTacToeDemo {
    public static void main(String[] args){
        Player player1 = new Player("Aryansh", MarkerType.CROSS);
        Player player2 = new Player("Opponent", MarkerType.ZERO);

        Game game = new Game(player1, player2, 3);
        game.play();

    }
}
