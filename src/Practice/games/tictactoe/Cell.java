package Practice.games.tictactoe;

public class Cell {
    public int row;
    public int column;
    public MarkerType marker;

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.marker = MarkerType.STAR;
    }

    public MarkerType getMarkerType(){
        return this.marker;
    }

    public char getMarkerToPrint(){
        switch(this.marker){
            case MarkerType.STAR:
                return '*';
            case MarkerType.CROSS:
                return 'X';
            case MarkerType.ZERO:
                return '0';
        }
        return '*';
    }
    public void setMarkerType(MarkerType marker){
        this.marker = marker;
    }
    public boolean isValid(){
        return marker == MarkerType.STAR;
    }
}
