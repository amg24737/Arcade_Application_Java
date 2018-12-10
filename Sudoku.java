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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

/**
 * This class provides a sudoku game, providing
 * six pre-set boards representing three levels of
 * difficulty, and an evaluate button for the player
 * to determine whether they have filled in the board
 * correctly according to the rules of the game.
 *@author Anna Marie Gann
 * @author Alexander Coleman
 */
public class Sudoku {

    protected BorderPane root = new BorderPane();
    protected Button evaluate = new Button("Evaluate");
    protected GridPane gameBoard = new GridPane();
    protected TextField[][] textArr = new TextField[9][9];
    
    protected Button playAgain = new Button("Play again");

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
    protected String zeroString = "0";
    protected TextField addArr;
    
    protected char[][] hardOne = {
	{'0', '0', '7', '0', '0', '0', '4', '0', '0'},
	{'9', '8', '0', '0', '0', '0', '0', '0', '3'},
	{'0', '0', '0', '6', '1', '9', '0', '0', '0'},
	{'0', '0', '0', '5', '7', '2', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '3', '0', '9', '7'},
	{'0', '0', '0', '0', '6', '0', '0', '0', '0'},
	{'0', '7', '0', '0', '0', '0', '8', '0', '0'},
	{'0', '4', '3', '0', '0', '5', '0', '0', '0'},
	{'0', '0', '2', '0', '4', '0', '7', '0', '0'},
    };
    
    protected char[][] hardTwo =  {
	{'0', '0', '0', '0', '0', '0', '9', '0', '0'},
	{'0', '5', '0', '0', '3', '0', '0', '0', '0'},
	{'0', '1', '2', '0', '0', '8', '0', '0', '0'},
	{'0', '9', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '2', '0', '0', '0', '1', '0', '3', '0'},
	{'0', '0', '0', '5', '0', '2', '0', '0', '1'},
	{'0', '0', '0', '0', '8', '0', '6', '4', '0'},
	{'0', '6', '0', '1', '2', '0', '0', '0', '0'},
	{'0', '0', '7', '9', '0', '6', '0', '0', '5'},
    };
    
    protected char[][] medOne =  {
	{'7', '0', '4', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '6', '0', '0', '0', '1'},
	{'0', '9', '5', '7', '0', '0', '0', '0', '0'},
	{'4', '6', '0', '9', '3', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '9', '0'},
	{'0', '7', '0', '0', '0', '0', '2', '0', '0'},
	{'0', '0', '0', '5', '0', '2', '3', '0', '0'},
	{'1', '0', '0', '0', '0', '0', '9', '0', '4'},
	{'0', '4', '0', '0', '0', '7', '0', '0', '0'},
    };
    
    protected char[][] medTwo =  {
	{'0', '0', '2', '0', '0', '3', '0', '1', '0'},
	{'7', '0', '0', '0', '6', '0', '0', '0', '0'},
	{'0', '9', '0', '0', '0', '4', '3', '0', '0'},
	{'0', '2', '0', '0', '0', '0', '0', '4', '6'},
	{'1', '0', '0', '0', '8', '0', '0', '7', '0'},
	{'0', '0', '0', '5', '0', '0', '2', '0', '0'},
	{'0', '0', '8', '3', '0', '0', '0', '0', '1'},
	{'0', '3', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '7', '0', '0', '0', '9', '0', '0', '0'},
    };
    
    protected char[][] easyOne =  {
	{'0', '0', '0', '0', '0', '0', '9', '0', '2'},
	{'4', '5', '0', '9', '0', '0', '1', '7', '0'},
	{'0', '0', '0', '7', '0', '1', '0', '5', '0'},
	{'0', '8', '2', '0', '0', '0', '4', '0', '7'},
	{'6', '0', '1', '3', '2', '7', '8', '0', '5'},
	{'9', '0', '3', '0', '0', '0', '2', '1', '0'},
	{'0', '3', '0', '6', '0', '8', '0', '0', '0'},
	{'0', '9', '4', '0', '0', '5', '0', '6', '8'},
	{'1', '0', '8', '0', '0', '0', '0', '0', '0'},
    };
    
    protected char[][] easyTwo = {
	{'3', '0', '0', '0', '0', '2', '6', '8', '0'},
	{'0', '0', '0', '0', '0', '8', '0', '1', '0'},
	{'0', '2', '4', '0', '0', '0', '9', '3', '0'},
	{'7', '0', '0', '4', '5', '6', '0', '0', '0'},
	{'0', '0', '1', '0', '8', '7', '0', '0', '0'},
	{'2', '0', '0', '0', '1', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '6', '0', '1', '0', '0'},
	{'0', '0', '9', '0', '0', '0', '0', '0', '6'},
	{'0', '0', '5', '0', '0', '0', '3', '0', '9'},
    };
    
    protected String[][] gameBoardId = {
	{"00", "01", "02", "03", "04", "05", "06", "07", "08"},
	{"10", "11", "12", "13", "14", "15", "16", "17", "18"},
	{"20", "21", "22", "23", "24", "25", "26", "27", "28"},
	{"30", "31", "32", "33", "34", "35", "36", "37", "38"},
	{"40", "41", "42", "43", "44", "45", "46", "47", "48"},
	{"50", "51", "52", "53", "54", "55", "56", "57", "58"},
	{"60", "61", "62", "63", "64", "65", "66", "67", "68"},
	{"70", "71", "72", "73", "74", "75", "76", "77", "78"},
	{"80", "81", "82", "83", "84", "85", "86", "87", "88"},
    };
    
    /**
     * This method initializes the game board
     * with one of the pre-set game options. 
     * It also sets an id for each node and makes
     * the predetermined nodes uneditable.
     * @param none
     */
    public void boardSetUp() {
	int numRows = 9;
	int numCols = 9;
	gameBoard.setGridLinesVisible(true);

	for (int i = 0; i < numCols; i++) {
		ColumnConstraints colConst = new ColumnConstraints();
		colConst.setPercentWidth(45.0/numCols);
		gameBoard.getColumnConstraints().add(colConst);
	    }//row constraint

	for (int i = 0; i < numRows; i++) {
		RowConstraints rowConst = new RowConstraints();
		rowConst.setPercentHeight(45.0/numRows);
		gameBoard.getRowConstraints().add(rowConst);
	    }//row constraint

	for (int rows = 0; rows < 9; rows++) {
	    for (int cols = 0; cols < 9; cols++) {
		//		String rowString = Integer.toString(rows);
		//	String colString = Integer.toString(cols);
		//	String j = rowString + colString;
		//	String b = Character.toString((easyOne[rows][cols]));
		textArr[rows][cols] = new TextField("0");
		//	textArr[rows][cols].setEditable(true);
		addArr = textArr[rows][cols];
		//	addArr.setId(j);
		//	addArr.setText(b);
		gameBoard.getChildren().add(addArr);
	    }//for cols
	}//for rows
	/**
	for (int row = 0; row < 9; row++) {
	    for (int col = 0; col < 9; col++) {
		String rowString = Integer.toString(row);
		String colString = Integer.toString(col);
		String j = rowString + colString;
	    	String b = Character.toString((easyOne[row][col]));
		textArr[row][col].setId(j);
		textArr[row][col].setText(b);
		if (!(b.equals(zeroString))) {
			textArr[row][col].setEditable(false);
		    }//if
	    }//for col
	}//for row
	*/	
	root.setCenter(gameBoard);
    }//boardSetUp
    
	/**
	 * This method sets up the visual of the game,
	 * adding all of the buttons, text fields, and
	 * menu options that the player will see, and 
	 * formatting each one. 
	 * @param none
	 */
    public void setUpGame() {
	winBox.getChildren().addAll(winLabel, winStatus);
	root.setBottom(winBox);
		     
	evaluateBox.getChildren().addAll(evaluate, playAgain);
	root.setTop(evaluateBox);
    }//setUpGame

    /**
     * This method sets up action events
     * to change the game board as the 
     * player adds new values to the board.
     * @param none
     */
    public void groupHandler() {
	gameBoard.setOnMousePressed(event-> {
		TextField ttf = ((TextField)event.getSource());
		String nodeId = ttf.getId();

		for (int i = 0; i < 9; i++) {
		    for (int j = 0; j < 9; j++) {
			if (gameBoardId[i][j].equals(nodeId)) {
				String nodeString = ttf.getText();
				char importantC = nodeString.charAt(0);
				int charInt = Character.getNumericValue(importantC);
				values[i][j] = charInt;
			    }//if
			    }//for j
		    }//for i
		}); //eventHandler
    }//groupHandler


	    /**
	     * This method is called when the 
	     * player clicks the evaluate button. 
	     * The method evaluates the win status
	     * of the game at the point and reacts accordingly.
	     * @param none
	     */
    public void evaluate() {
	evaluate.setOnAction(event-> {
		String winString = "YOU WIN!";
		String loseString = "Sorry, this configuration is incorrect";
		if((validBox() == true) && (validRow() == true) && (validColumn() == true)) {
		    gameWin = true;
		    winStatus.setText(winString);
		}//if
	      
		else {
		    winStatus.setText(loseString);
		}//else
	    });//lambda
    }//evaluate

	    /**
	     * This method checks whether easy
	     * three by three sub box only has one of
	     * each number one through nine, and is a valid
	     * box according to the game rules. 
	     * @param none
	     */
    public boolean validBox() {
	for (int i = 0; i < 9; i+=3) {
	    for (int j = 0; j < 9; j+=3) {
		for (int pos = 0; pos < 8; pos++) {
		    for (int posTwo = pos+1; posTwo <9; posTwo++) {
			if (values[i + pos%3][j + pos/3] == values[i + posTwo%3][j + posTwo/3]) {
			    return false;
			}//if
		    }//posTwo
		}//for pos
	    }//for j
	}//for i
	return true;
    }//validBox


	    /**
	     * This method checks whether or not
	     * each row of the game board is valid
	     * according to the rules of the game
	     * @param none
	     */
    public boolean validRow(){
	for (int r = 0; r < 9; r++) {
	    for (int c = 0; c < 8; c++) {
		for (int c2 = c+1; c2 <9; c2++) {
		    if(values[r][c] == values[r][c2])
			{
			    return false;
			}//if
		}//for c2
	    }//for c
	}//for r
	return true;
    }//validRow

	    /**
	     * This method checks to 
	     * make sure that each column of the player's
	     * board is valid accodring to game rules.
	     * @param none
	     */
    public boolean validColumn() {
	for (int c = 0; c < 9; c++) {
	    for (int r = 0; r < 8; r++) {
		for (int r2 = r+1; r2 < 9; r2++) {
		    if(values[r][c] == values[2][c])
			{
			    return false;
			}//if
		}//for r2
	    }//for r
	}//for c
	return true;
    }//validColumn

	    /**
	     * This method provides the actual
	     * game loop and is the only method
	     * directly called from the arcade
	     * app class. 
	     * @param a stage for the game
	     */
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

