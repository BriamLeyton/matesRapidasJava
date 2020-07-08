package application;

import java.util.Arrays;

/**
 * la clase config contiene la configuracion de variables iniciales del juego tales como:
 * jugadores, dificultad
 * adicional los metodos para trabajar estas variables tales como:
 * getPlayers, setPlayers, getDifficulty, setDifficulty
 * ademas de los metodos para gestionar los niveles de dificultad y cantidad de jugadores
 * getcols, getrows, getMaxCardValue, getMinCardValue
 */
public class Config {

    static final String DIFFICULTY_EASY = "easy";
    static final String DIFFICULTY_NORMAL = "normal";
    static final String DIFFICULTY_HARD = "hard";

    private int players;
    private String difficulty;

    Config() {
        players = 1;
        difficulty = DIFFICULTY_NORMAL;
    }

    /**
     * @return retorna el entero de la variable players que contiene la cantidad de jugadores; default 1
     */
    public int getPlayers() {
        return players;
    }

    /**
     * @param players asigna el entero a la variable players segun sea elijido
     */
    public void setPlayers(int players) {
        this.players = players;
    }

    /**
     * @return retorna el entero de la variable difficulty; default difficulty_normal
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty asigna el string a la variable difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return
     * retorna la cantidad de columnas
     * segun sea el caso de dificultad
     * resolviendolo segun la funcion resolveDifficultyConfig
     */
    int getCols() {
        return resolveDifficultyConfig(6, 8, 10);
    }

    /**
     * @return
     * retorna la cantidad de filas
     * segun sea el caso de dificultad
     * resolviendolo segun la funcion resolveDifficultyConfig
     */
    int getRows() {
        return resolveDifficultyConfig(4, 5, 6);
    }

    /**
     * @return
     * retorna la cantidad maxima de tarjetas a mostar
     * segun sea el caso de dificultad resolviendolo desde la funcion
     * resolveDifficultyConfig
     */
    int getMaxCardValue() {
        return resolveDifficultyConfig(20, 25, 33);
    }

    /**
     * @return
     * retorna la cantidad minima de tarjetas a mostar
     * segun sea el caso de dificultad resolviendolo desde la funcion
     * resolveDifficultyConfig
     */
    int getMinCardValue() {
        return 5;
    }

    /**
     * @param easy
     * @param normal
     * @param hard
     * @return
     * selecciona y retorna los valores en un unico entero segun se
     * indique en la variable privada difficulty
     */
    private int resolveDifficultyConfig(int easy, int normal, int hard) {
        switch (this.difficulty) {
            case DIFFICULTY_EASY:
                return easy;
            case DIFFICULTY_HARD:
                return hard;
            default:
                return normal;
        }
    }

    /**
     * @return
     * retorna un array de strings de las operaciones segun el nivel de juego seleccionado
     */
    public String[] getDifficultyOperationConfig() {
        String[] operations;
        switch (this.difficulty) {
            case DIFFICULTY_EASY:
                operations = new String[2];
                operations[0] = Operations.SUM;
                operations[1] = Operations.SUBTRACTION;
                break;
            case DIFFICULTY_HARD:
                operations = new String[4];
                operations[0] = Operations.SUM;
                operations[1] = Operations.SUBTRACTION;
                operations[2] = Operations.MULTIPLICATION;
                operations[3] = Operations.DIVISION;
                break;
            default:
                operations = new String[3];
                operations[0] = Operations.SUM;
                operations[1] = Operations.SUBTRACTION;
                operations[2] = Operations.MULTIPLICATION;
        }

        return operations;
    }

}
