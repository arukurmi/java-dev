package games.tictactoe;

public class Player {
    private String name;
    private MarkerType markerType;

    public Player(String name, MarkerType markerType){
        this.name = name;
        this.markerType = markerType;
    }
    public MarkerType getPlayerMarker(){
        return this.markerType;
    }
    public String getName(){
        return this.name;
    }
    public void setPlayerMarker(MarkerType marker){
        markerType = marker;
    }
    public void setPlayerName(String name){
        this.name = name;
    }
}
