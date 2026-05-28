package games.tictactoe;

import java.util.Scanner;

public class Game {
    public Player player1;
    public Player player2;
    public Player currentPlayer;
//    public String result;
    public Board board;

    public Game(Player player1, Player player2, int boardSize){
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(boardSize);
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        currentPlayer = player1;
        while(true){
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s Turn. Please enter row and column, (0 based index)\n\n ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if(!board.makeMove(row, col, currentPlayer.getPlayerMarker())){
                System.out.println("\n\n Invalid move! Try again please!\n\n");
                continue;
            }

            if(GameValidator.checkWin(board, currentPlayer.getPlayerMarker())){
                board.printBoard();
                System.out.println("\n\n" + currentPlayer.getName() + " WINS THE GAME!\n\n ");
                break;
            }
            if(GameValidator.isDraw(board)){
                board.printBoard();
                System.out.println("\n\nGame is draw");
                break;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
        scanner.close();
    }


}
