package cs1302.arcade;

//import statements
import java.util.*;
//import java.util.EventObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Sudoku {

    protected BorderPane root = new BorderPane();
    protected Button evaluate = new Button();
    protected TilePane gameBoard = new TilePane();
    protected TilePane buttons = new TilePane();
    
    protected Button oneB = new Button("1");
    protected Button twoB = new Button("2");
    protected Button threeB = new Button("3");
    protected Button fourB = new Button("4");
    protected Button fiveB = new Button("5");
    protected Button sixB = new Button("6");
    protected Button sevenB = new Button("7");
    protected Button eightB = new Button("8");
    protected Button nineB = new Button("9");

    protected Button playAgain = new Button("Play again");
    protected Button clearBoard = new Button("Clear Board");

    protected TextField winStatus = new TextField(" ");
    protected Label winLabel = new Label("Win or Lose?");
    protected HBox winBox = new HBox();

    protected Boolean gameWin = false;
    protected int[][] values = new int[9][9];
    protected Group group = new Group();
    protected TextField tempText = new TextField();
    
    protected VBox evaluateBox = new VBox();

    protected int x = 0;
    protected int y = 0;
    
    public void boardSetUp() {
	gameBoard.setPrefRows(9);
	gameBoard.setPrefColumns(9);
	for (int row = 0; row < 9; row++) {
	    for (int col = 0; col < 9; col++) {
		String rowString = Integer.toString(row);
		String colString = Integer.toString(col);
		String j = rowString + colString;
		Button temp = new Button(j);
		temp.setId(j);
		gameBoard.getChildren().add(temp);
	    }//for col
	}//for row
	root.setCenter(gameBoard);
    }//setUpGameBoard

    public void setUpGame() {
	buttons.getChildren().addAll(oneB, twoB, threeB, fourB, fiveB, sixB, sevenB, eightB, nineB);
	buttons.setPrefColumns(3);
	buttons.setPrefRows(3);
	root.setRight(buttons);	
	winBox.getChildren().addAll(winLabel, winStatus);
	root.setLeft(winBox);
		     
	evaluateBox.getChildren().add(evaluate);
	root.setTop(evaluateBox);
    }//setUpGame
	    
    public void groupHandler() {
	gameBoard.setOnMousePressed(event-> {
		String nodeId = ((Button)event.getSource()).getId();
		char xInd = nodeId.charAt(0);
		char yInd = nodeId.charAt(1);
		x = Character.getNumericValue(xInd);
		y = Character.getNumericValue(yInd);
		
		oneB.setOnAction(k -> {
			((Button)event.getSource()).setText("1");
			values[x][y] = 1;
		    });//button one is clicked

		twoB.setOnAction(e -> {
                        ((Button)event.getSource()).setText("2");
                        values[x][y] = 2;
                    });//button two is clicked

		threeB.setOnAction(a -> {
                        ((Button)event.getSource()).setText("3");
                        values[x][y] = 3;
                    });//button three is clicked

		fourB.setOnAction(b -> {
                        ((Button)event.getSource()).setText("4");
                        values[x][y] = 4;
                    });//button four is clicked

		fiveB.setOnAction(c -> {
                        ((Button)event.getSource()).setText("5");
                        values[x][y] = 5;
                    });//button five is clicked

		sixB.setOnAction(d -> {
                        ((Button)event.getSource()).setText("6");
                        values[x][y] = 6;
                    });//button six is clicked

		sevenB.setOnAction(f -> {
                        ((Button)event.getSource()).setText("7");
                        values[x][y] = 7;
                    });//button seven is clicked

		eightB.setOnAction(g -> {
                        ((Button)event.getSource()).setText("8");
                        values[x][y] = 8;
                    });//button eight is clicked

		nineB.setOnAction(h -> {
                        ((Button)event.getSource()).setText("9");
                        values[x][y] = 9;
                    });//button nine is clicked
		
	    });//event handler
    }//groupHandler
    
    public void evaluate() {
	evaluate.setOnAction(event-> {
		validBox();
		String winString = "YOU WIN!";
		String loseString = "Sorry, this configuration is incorrect";

		if (gameWin == true) {
		    winStatus.setText(winString);
		}//if

		else {
		    winStatus.setText(loseString);
		}//else
	    });//lambda
    }//evaluate
    
    public void validBox() {
	;
    }//validBox

    public void validRow(){
	;
    }//validRow

    public void validColumn() {
	;
    }//validColumn

    public void play(Stage sudokuStage) {
	Scene scene = new Scene(root, 600, 500);
	boardSetUp();
	setUpGame();
	sudokuStage.setTitle("Sudoku");
	sudokuStage.setScene(scene);
	sudokuStage.sizeToScene();
	sudokuStage.show();
    }//startGame
    
}//sudoku

