package cs1302.arcade;

//import statements
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Pos;

public class ArcadeApp extends Application {

    Random rng = new Random();
    FadeTransition extra1;
    FadeTransition extra2;
    FadeTransition extra3;
    protected VBox gameChoice = new VBox();
    protected Button sudokuButton = new Button("Sudoku: Play now!");
    protected Button snakeButton = new Button("Snake: Play now!");
    protected Label gameTitle= new Label("ARCADE CORNER");
    protected Label teamName= new Label("TEAM NAME");
    protected Label authorNames= new Label("Anna Marie Gann & Alexander Coleman");


    public void snake () {
	snakeButton.setOnAction(evant-> {
		Snake sn = new Snake();
		Stage snakeStage = new Stage();
		sn.startGame(snakeStage);
	    });//lambda
    }//snake
    
    public void sudoku() {
	sudokuButton.setOnAction(event-> {
		Sudoku sud = new Sudoku();
		Stage startStage = new Stage();
		sud.play(startStage);
	    });//lambda
    }//sudoku

    public void setUp() {
	extra1 = new FadeTransition(Duration.millis(2000), gameTitle);
	extra1.setFromValue(1.0);
	extra1.setToValue(0.3);
	extra1.setCycleCount(4);
	extra1.setAutoReverse(true);
	gameTitle.setFont(new Font("Arial", 33));
	gameTitle.setTextFill(Color.RED);
	gameTitle.setVisible(false);
	extra2 = new FadeTransition(Duration.millis(3000), teamName);
	extra2.setFromValue(1.0);
	extra2.setToValue(0.3);
	extra2.setCycleCount(10);
	extra2.setAutoReverse(true);
	teamName.setFont(new Font("Arial", 33));
	teamName.setTextFill(Color.BLACK);
	teamName.setVisible(false);
	extra3 = new FadeTransition(Duration.millis(4000), authorNames);
	extra3.setFromValue(1.0);
	extra3.setToValue(0.3);
	extra3.setCycleCount(7);
	extra3.setAutoReverse(true);
	authorNames.setFont(new Font("Arial", 25));
	authorNames.setTextFill(Color.RED);
	authorNames.setVisible(false);
   
	gameChoice.getChildren().addAll(gameTitle,teamName,authorNames,snakeButton,sudokuButton);
	gameChoice.setAlignment(Pos.CENTER);
	gameChoice.setStyle("-fx-background-color: #f0c21e ;");
    }//set up
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("cs1302-arcade!");
	setUp();
	gameChoice.setOnMouseClicked(event-> {
		gameTitle.setVisible(true);
		extra1.play();
		teamName.setVisible(true);
		extra2.play();
		authorNames.setVisible(true);
		extra3.play();
	    });//lambda

	sudoku();
	snake();
	Scene scene = new Scene(gameChoice, 640, 480,Color.BLUE);
	stage.setScene(scene);
	stage.sizeToScene();
        stage.show();

    } // start

    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main

} // ArcadeApp
