package application;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Game extends Application {
    public int NUM_ROWS = 4;
    public int NUM_COLS = 7;
    public int LIM_NUM = 20;

    private Config config;
    private Stage gameStage;

    private Player[] players;

    private Player activePlayer;

    private int selectedBoxes = 0;

    private Boxes[][] boxes;

    Game(Config configuration) {
        config = configuration;
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameStage = stage;
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        GridPane gameGrid = new GridPane();
        gameGrid.setHgap(7);
        gameGrid.setVgap(7);

        initializerPlayers();

        boxes = new Boxes[config.getRows()][config.getCols()];
        Random random = new Random();
        IntStream intStream = random.ints(config.getCols() * config.getRows(), config.getMinCardValue(), config.getMaxCardValue());
        Iterator iterator = intStream.iterator();

        for (int i = 0; i < config.getCols(); i++) {
            for (int j = 0; j < config.getRows(); j++) {
                int value = (int) iterator.next();
                boxes[j][i] = new Boxes(value);
                Boxes box = boxes[j][i];
                gameGrid.add(box, i, j);
            }
        }

        HBox head = new HBox(20);
        Text resume = new Text("Jugadores: " + config.getPlayers() + " | Nivel: " + config.getDifficulty().toUpperCase());

        HBox playerContainer = new HBox(15);
        playerContainer.setAlignment(Pos.CENTER_RIGHT);

        for (Player player :
                players) {
            Text playerText = new Text(player.getId() + ": " + player.getPoints());
            playerContainer.getChildren().add(playerText);
        }

        head.getChildren().addAll(resume, playerContainer);

        vBox.getChildren().addAll(head, gameGrid);

        Scene display = new Scene(vBox);

        gameStage.setResizable(false);
        gameStage.setScene(display);
        gameStage.show();
    }

    private void initializerPlayers() {
        int configPlayers = config.getPlayers();
        players = new Player[configPlayers];
        for (int i = 0; i < configPlayers; i++) {
            players[i] = Player.getInstance("Player " + (i + 1));
        }
        activePlayer = players[0];
    }

    public void changeActivePlayer(){
        if (players[0] == activePlayer) {
            activePlayer = players[1];
        }else {
            activePlayer = players[0];
        }
    }

    private void showModalOperation() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(this.gameStage);

        List<PlayerSelection> playerSelection1 = activePlayer.getPlayerSelection();
        PlayerSelection playerSelection = playerSelection1.get(playerSelection1.size() - 1);

        String[] operations = config.getDifficultyOperationConfig();
        String randomOperation = (operations[new Random().nextInt(operations.length)]);
        playerSelection.setOperation(randomOperation);


        String operator = "";
        switch (playerSelection.getOperation()) {
            case Operations.SUM:
                operator = "+";
                break;
            case Operations.SUBTRACTION:
                operator = "-";
                break;
            case Operations.MULTIPLICATION:
                operator = "*";
                break;
            case Operations.DIVISION:
                operator = "/";
                break;
        }

        Text operator1 = new Text(Integer.toString(playerSelection.getSelectionOne()));
        Text operation = new Text(operator);
        Text operator2 = new Text(Integer.toString(playerSelection.getSelectionTwo()));
        Text equal = new Text("=");
        TextField result = new TextField();
        result.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    if (null == result.getText()){
                        return;
                    }
                    try {
                        playerSelection.setPlayerResult(Float.parseFloat(result.getText()));
                        playerSelection.makeOperation();
                        if (playerSelection.isValid()) {
                            activePlayer.gainPoint();
                        }
                        System.out.println(activePlayer.getId() + " - Puntos: " + activePlayer.getPoints());
                        dialog.hide();
                        selectedBoxes = 0;

                        for (int i = 0; i < config.getCols(); i++) {
                            for (int j = 0; j < config.getRows(); j++) {
                                boxes[j][i].hide();
                            }
                        }
                        changeActivePlayer();

                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });

        HBox operationContainer = new HBox(10);
        operationContainer.setAlignment(Pos.CENTER);
        operationContainer.getChildren().addAll(operator1, operation, operator2, equal, result);

        HBox dialogVbox = new HBox(20);
        dialogVbox.getChildren().add(new Text("Resuelve la Operacion"));
        dialogVbox.setAlignment(Pos.CENTER);

        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(dialogVbox, operationContainer);

        Scene dialogScene = new Scene(container, 350, 200);
        dialog.setScene(dialogScene);
        dialog.setResizable(false);
        dialog.show();
    }

    public class Boxes extends GridPane {
        //Text numText = new Text();
        Label numText = new Label();

        Boxes(int num) {
            Rectangle box = new Rectangle(70, 70, Color.BLUE);
            numText.setText(Integer.toString(num));
            numText.setFont(Font.font(40));
            numText.setTextFill(Color.BLUE);
            Boxes.setHalignment(numText, HPos.CENTER);
            getChildren().addAll(box, numText);
            setOnMouseClicked(mouseEvent -> {
                listenSelectedBox(num);
                numText.setTextFill(Color.WHITE);
            });
            hide();
        }

        private void listenSelectedBox(int num) {
            show();
            ++selectedBoxes;
            if (1 == selectedBoxes) {
                PlayerSelection playerSelection = new PlayerSelection();
                playerSelection.setSelectionOne(num);
                activePlayer.addPlayerSelection(playerSelection);
            } else if (2 == selectedBoxes) {
                List<PlayerSelection> playerSelection1 = activePlayer.getPlayerSelection();
                PlayerSelection playerSelection = playerSelection1.get(playerSelection1.size() - 1);
                playerSelection.setSelectionTwo(num);
                showModalOperation();
            }
        }

        void hide() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.2), numText);
            ft.setToValue(0);
            ft.play();
        }

        void show() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.2), numText);
            ft.setToValue(1);
            ft.play();
        }
    }
}
