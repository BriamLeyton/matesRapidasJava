package application;

import java.util.Arrays;

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

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    int getCols() {
        return resolveDifficultyConfig(6, 8, 10);
    }

    int getRows() {
        return resolveDifficultyConfig(4, 5, 6);
    }

    int getMaxCardValue() {
        return resolveDifficultyConfig(20, 25, 33);
    }

    int getMinCardValue() {
        return 5;
    }

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
