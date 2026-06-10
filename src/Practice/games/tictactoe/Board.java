package Practice.games.tictactoe;

public class Board {
    public int size;
    public Cell[][] board;

    public Board(int size){
        this.size = size;
        board = new Cell[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean makeMove(int row, int col, MarkerType marker){
        if(row<0 || col<0 || col>=size || row>=size || !board[row][col].isValid()){
            return false;
        }
        board[row][col].setMarkerType(marker);
        return true;
    }

    public void printBoard(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++) {
                System.out.print(board[i][j].getMarkerType() == MarkerType.STAR ? " " : board[i][j].getMarkerToPrint());
                if(j < size - 1) System.out.print(" | ");
            }
            if(i < size - 1) {
                System.out.println("");
                System.out.println("---------");
            }
        }
    }

    public Cell[][] getBoard(){
        return board;
    }

    public int getSize(){
        return size;
    }

    public Cell[][] getBoardState(){
        return board;
    }
}
