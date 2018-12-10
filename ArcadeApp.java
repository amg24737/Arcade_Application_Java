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
import javafx.scene.control.Toggle;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;

public class ArcadeApp extends Application {

    protected BorderPane root = new BorderPane();
    Random rng = new Random();
    protected HBox gameChoice = new HBox();
    protected Button sudokuButton = new Button("Sudoku: Play now!");
    protected Button snakeButton = new Button("Snake: Play now!");
    protected TextArea welcome = new TextArea();

    protected VBox text1 = new VBox();
    protected TextField tf1 = new TextField("Welcome to out arcade!");
    protected TextField tf2 = new TextField("We have two games for you to play: ");
    protected TextField tf3 = new TextField("Snake and Sudoku.");
    protected TextField tf4 = new TextField("Click one of the game buttons to begin.");
    protected TextField tf5 = new TextField("Happy gaming!");
    
    public void snake () {
	snakeButton.setOnAction(evant-> {
		Snake sn = new Snake();
		Stage snakeStage = new Stage();
		//		sn.startGame(snakeStage);
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
	text1.getChildren().addAll(tf1, tf2, tf3, tf4, tf5);
	root.setTop(text1);
	
	gameChoice.getChildren().addAll(snakeButton, sudokuButton);

	root.setCenter(gameChoice);
    }//set up
    
    @Override
    public void start(Stage stage) {
	
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("cs1302-arcade!");
	setUp();
	sudoku();
	snake();
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();

	// the group must request input focus to receive key events
	// @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
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

