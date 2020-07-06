package application;

import javafx.event.ActionEvent;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private Config config;

    @Override
    public void init() throws Exception {
        config = new Config();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button playBtn = new Button("Start!!");
        playBtn.setMinSize(150, 35);

        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Application configStage = new GameConfiguration(config);
                try {
                    configStage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button helpBtn = new Button("Help!!");
        helpBtn.setMinSize(110, 30);

        VBox panelButtons = new VBox(20);
        panelButtons.setAlignment(Pos.CENTER);
        panelButtons.getChildren().addAll(playBtn, helpBtn);

        String GAME_TITLE = "Mates Rapidas Game";
        Text title = new Text(GAME_TITLE);
        title.setFont(new Font(30));

        VBox panelMain = new VBox(10);
        panelMain.setAlignment(Pos.CENTER);
        panelMain.getChildren().addAll(title, panelButtons);

        StackPane canvas = new StackPane();
        canvas.setPadding(new Insets(30));
        canvas.getChildren().add(panelMain);

        Scene principal = new Scene(canvas, 400, 400);

        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setScene(principal);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
