package cs1302.arcade;
//package statement
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

/**
 * This class launches an arcade application
 * which provides the user with two games
 * to play, snake and sudoku, each with
 * three levels of difficulty
 *@extends Application
 *@author Alexander Coleman
 *@author Anna Marie Gann
 */
public class ArcadeApp extends Application {
    //randomizer
    Random rng = new Random();
    //fade transitions
    FadeTransition extra1;
    FadeTransition extra2;
    FadeTransition extra3;
    //storage for game choice which
    //will be set to the scene
    protected VBox gameChoice = new VBox();
    //string for the player's name
    protected String nameS = " ";
    protected String dir = "Enter initials here, then choose a game";
    protected TextField name = new TextField(dir);
    //place for user to enter their name
    //ints for high scores
    protected int snakeH = 0;
    protected int sudokuH = 0;
    String snHS = "SNAKE HIGH SCORE: "+ nameS+ " " +snakeH;
    String skHS = "SUDOKU HIGH SCORE: "+ nameS+ " " +sudokuH;
    protected Label sudHighS = new Label(skHS);
    //snake high score label
    protected Label snaHighS = new Label(snHS);
    //sudoku button
    protected Button sudokuButton = new Button("Sudoku: Play now!");
    //snake button
    protected Button snakeButton = new Button("Snake: Play now!");
    //arcade title
    protected Label gameTitle= new Label("ARCADE CORNER");
    //label for team name
    protected Label teamName= new Label("TEAM NAME");
    protected Label authorNames= new Label("Anna Marie Gann & Alexander Coleman");
    //label for author names
    
    /**
     * This method has an event handler
     * which launches the snake game if
     * the snake button is chosen
     * @param none
     */
    public void snake () {
	snakeButton.setOnAction(evant-> {
		//get player's name from text field
		nameS = name.getText();
		//create game object
		Snake sn = new Snake();
		//stage for game
		Stage snakeStage = new Stage();
		//invoke main game loop with
		//stage to display the game
		sn.startGame(snakeStage);
	    });//lambda
    }//snake

    /**
     * This method has an event
     * handler which launches the
     * sudoku game if that button
     * is pressed by the player
     *@param none
     */
    public void sudoku() {
	sudokuButton.setOnAction(event-> {
		//get player's name from text box
		nameS = name.getText();
		//create a game object
		Sudoku sud = new Sudoku();
		//stage for game
		Stage startStage = new Stage();
		//invoke main game loop with stage
		sud.play(startStage);
	    });//lambda
    }//sudoku

    /**
     * This method sets up the first visual
     * representation of the app, including
     * animations, font choices, text choices,
     * theme fills and the like.
     *@param none
     */
    public void setUp() {
	extra1 = new FadeTransition(Duration.millis(2000), gameTitle);
	extra1.setFromValue(1.0); //format fade
	extra1.setToValue(0.3);
	extra1.setCycleCount(4); //cycle count
	extra1.setAutoReverse(true);
	gameTitle.setFont(new Font("Arial", 33));
	gameTitle.setTextFill(Color.RED);
	gameTitle.setVisible(false);
	extra2 = new FadeTransition(Duration.millis(3000), teamName);
	extra2.setFromValue(1.0); //format fade
	extra2.setToValue(0.3);
	extra2.setCycleCount(10);
	extra2.setAutoReverse(true);
	teamName.setFont(new Font("Arial", 33)); //font
	teamName.setTextFill(Color.BLACK); //color
	teamName.setVisible(false); //visibility
	extra3 = new FadeTransition(Duration.millis(4000), authorNames);
	extra3.setFromValue(1.0); //fade properties
	extra3.setToValue(0.3);
	extra3.setCycleCount(7); //cycle count
	extra3.setAutoReverse(true);
	authorNames.setFont(new Font("Arial", 25));
	authorNames.setTextFill(Color.RED); //color
	authorNames.setVisible(false); //vis
	gameChoice.getChildren().addAll(gameTitle,teamName,
					authorNames,snakeButton,sudokuButton,
					name, snaHighS, sudHighS);
	gameChoice.setAlignment(Pos.CENTER);
	gameChoice.setStyle("-fx-background-color: #f0c21e ;");
    }//set up
    
    @Override
    public void start(Stage stage) {
	//title
        stage.setTitle("cs1302-arcade!");
	setUp(); //set up game
	//event handler
	gameChoice.setOnMouseClicked(event-> {
		gameTitle.setVisible(true);
		extra1.play();
		teamName.setVisible(true);
		extra2.play();
		authorNames.setVisible(true);
		extra3.play();
	    });//lambda

	sudoku(); //sudoku game
	snake(); //snake game
	//game choice container for visual
	Scene scene = new Scene(gameChoice, 640, 480,Color.BLUE);
	stage.setScene(scene);
	stage.sizeToScene(); //format
        stage.show(); //show stage

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
