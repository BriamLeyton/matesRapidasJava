package application;

import java.util.Random;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
	public int NUM_ROWS=4;
	public int NUM_COLS=7;
	public int LIM_NUM=20;
	
	private Parent VentanaGame() {
		GridPane ventanaJuego=new GridPane();
		ventanaJuego.setPrefSize(700, 400);
		Cajones[][] Cuadros = new Cajones[NUM_ROWS][NUM_COLS];
		for(int i =0;i<NUM_ROWS;i++) {
			for(int j=0;j<NUM_COLS;j++) {
				Random aleatorio =	new Random();
				Cuadros[i][j]= new Cajones(aleatorio.nextInt(LIM_NUM));
				Cajones carta = Cuadros[i][j];
				
				ventanaJuego.add(carta,j,i);

	
				//System.out.println(aleatorio.nextInt(LIM_NUM));
			}
		}
		
		return ventanaJuego;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane pane = new FlowPane(Orientation.VERTICAL);
		Scene primeraVentana = new Scene(pane, 700,400);
		
		Text inicio = new Text("Bienvenidos a MatesRápidas");
		inicio.setFont(new Font(50));
		Button jugar = new Button("Jugar");
		jugar.setScaleX(2);
		jugar.setScaleY(2);
		//jugar.setOnAction(e ->primaryStage.setScene(ventanaJuego));
		jugar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setScene(new Scene(VentanaGame()));
				}
			});
		
		
		Button ayuda = new Button("Ayuda");
		ayuda.setScaleX(2);
		ayuda.setScaleY(2);
		pane.setPadding(new Insets(10,10,10,10));
		pane.setVgap(50);
		pane.setAlignment(Pos.TOP_CENTER);
		pane.getChildren().addAll(inicio,jugar,ayuda);
		
		primaryStage.setTitle("MatesRápidas");
		primaryStage.setScene(primeraVentana);
		primaryStage.show();
	}

	public class Cajones extends GridPane{
	//Text numText = new Text();
	Label numText = new Label();
	public Cajones(int num) {
	Rectangle caja = new Rectangle(100,100);
	caja.setFill(null);
	caja.setStroke(Color.BLUE);
	numText.setText(Integer.toString(num));	
	numText.setFont(Font.font(50));
	Cajones.setHalignment(numText, HPos.CENTER);
	getChildren().addAll(caja, numText);
	setOnMouseClicked(e -> destapado());
	tapado();
	
	}
		public void tapado() {
			FadeTransition ft = new FadeTransition(Duration.seconds(0.2),numText);
			ft.setToValue(0);
			ft.play();
			}
		public void destapado() {
			FadeTransition ft = new FadeTransition(Duration.seconds(0.2),numText);
			ft.setToValue(1);
			//ft.setOnFinished(e -> action.run());
			ft.play();
		}
	}
	
    public static void main(String[] args) {
        launch(args);
  }

	

	
}
