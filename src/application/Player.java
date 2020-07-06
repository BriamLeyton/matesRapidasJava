package application;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String id;
    private int points = 0;
    private List<PlayerSelection> playerSelection;

    private Player(String id) {
        this.id = id;
        playerSelection = new ArrayList<PlayerSelection>();
    }

    public String getId() {
        return this.id;
    }

    public int getPoints() {
        return this.points;
    }

    public void gainPoint() {
        this.points++;
    }

    static Player getInstance(String id){
        return new Player(id);
    }

    public void addPlayerSelection(PlayerSelection playerSelectionItem) {
        this.playerSelection.add(playerSelectionItem);
    }

    public List<PlayerSelection> getPlayerSelection() {
        return playerSelection;
    }

    public void setPlayerSelection(List<PlayerSelection> playerSelection) {
        this.playerSelection = playerSelection;
    }
}
