package application;

import java.util.ArrayList;
import java.util.List;

/**
 * clase que contiene la informacion del los jugadores actuales
 */
public class Player {

    private String id;
    private int points = 0;
    private List<PlayerSelection> playerSelection;

    /**
     * @param id segun el id del jugador, guarda en la lista su informacion
     */
    private Player(String id) {
        this.id = id;
        playerSelection = new ArrayList<PlayerSelection>();
    }


    /**
     * @return retorna el id del jugador
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return retorna los puntos del jugador
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * suma puntos al jugador segun el id
     */
    public void gainPoint() {
        this.points++;
    }

    /**
     * @param id identificador del jugador
     * @return retorna la informacion almacenada del jugador
     */
    static Player getInstance(String id){
        return new Player(id);
    }

    /**
     * @param playerSelectionItem almacena la seleccion del jugador
     */
    public void addPlayerSelection(PlayerSelection playerSelectionItem) {
        this.playerSelection.add(playerSelectionItem);
    }

    /**
     * @return retorna la seleccion del jugador
     */
    public List<PlayerSelection> getPlayerSelection() {
        return playerSelection;
    }

    /**
     * @param playerSelection identificador del jugador actual
     */
    public void setPlayerSelection(List<PlayerSelection> playerSelection) {
        this.playerSelection = playerSelection;
    }
}
