package games.tictactoe;

public class GameValidator {
    public static boolean checkWin(Board board, MarkerType marker){
        int size = board.getSize();
        Cell[][] grid = board.getBoard();
        for(int i=0; i<size; i++){
            if(checkRow(grid,i,marker) || checkCol(grid,i,marker)){
                return true;
            }
        }
        return checkDiagonal(grid,marker) || checkAntiDiagonal(grid,marker);
    }

    private static boolean checkRow(Cell[][] grid, int i, MarkerType marker){
        for(int j=0;j<grid.length; j++){
            if(grid[i][j].getMarkerType() != marker){
                return false;
            }
        }
        return true;
    }
    private static boolean checkCol(Cell[][] grid, int i, MarkerType marker){
        for(int j=0;j<grid.length; j++){
            if(grid[j][i].getMarkerType() != marker){
                return false;
            }
        }
        return true;
    }
    private static boolean checkDiagonal(Cell[][] grid, MarkerType marker){
        for(int j=0;j<grid.length; j++){
            if(grid[j][j].getMarkerType() != marker){
                return false;
            }
        }
        return true;
    }
    private static boolean checkAntiDiagonal(Cell[][] grid, MarkerType marker){
        for(int j=0;j<grid.length; j++){
            if(grid[grid.length-1-j][grid.length-1-j].getMarkerType() != marker){
                return false;
            }
        }
        return true;
    }

    public static boolean isDraw(Board board){
        for(int i=0; i<board.getSize(); i++){
            for(int j=0; j<board.getSize(); j++){
                if(board.getBoardState()[i][j].isValid()){
                    return false;
                }
            }
        }
        return true;
    }
}
