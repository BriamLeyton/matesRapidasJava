package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameConfiguration extends Application {

    private int number = 1;
    private static Config config;
    private static Stage gameStage;

    GameConfiguration(Config configuration) {
        config = configuration;
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameStage = stage;
        Button onePlayer = createPlayerButton(stage, "One player", 1);
        Button twoPlayers = createPlayerButton(stage, "Two players", 2);

        HBox buttonPanel = new HBox(15);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.getChildren().addAll(onePlayer, twoPlayers);

        Text title = new Text("Select Players");

        VBox panelMain = new VBox(20);
        panelMain.setAlignment(Pos.CENTER);
        panelMain.setPadding(new Insets(30));
        panelMain.getChildren().addAll(title, buttonPanel);

        Scene scene = new Scene(panelMain, 400, 400);

        stage.setTitle("Players");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Button createPlayerButton(Stage stage, String label, int palyers) {
        Button onePlayer = new Button(label);
        onePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                config.setPlayers(palyers);
                stage.setScene(difficulty());
            }
        });
        return onePlayer;
    }

    private static Scene difficulty() {
        Button easyBtn = createDifficultyButton(Config.DIFFICULTY_EASY);
        Button normalBtn = createDifficultyButton(Config.DIFFICULTY_NORMAL);
        Button hardBtn = createDifficultyButton(Config.DIFFICULTY_HARD);

        HBox buttonPanel = new HBox(20);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.getChildren().addAll(easyBtn, normalBtn, hardBtn);

        Text title = new Text("Select Difficulty");

        VBox panelMain = new VBox(10);
        panelMain.setAlignment(Pos.CENTER);
        panelMain.setPadding(new Insets(30));
        panelMain.getChildren().addAll(title, buttonPanel);

        return new Scene(panelMain, 400, 400);
    }

    private static Button createDifficultyButton(String difficulty) {
        Button button = new Button(difficulty.toUpperCase());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listenDifficultyBtnSelector(difficulty);
            }
        });
        return button;
    }

    private static void listenDifficultyBtnSelector(String difficulty) {
        config.setDifficulty(difficulty);
        Application game = new Game(config);
        try {
            game.start(gameStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
