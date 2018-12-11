package cs1302.arcade; //package statement

//import statements
import java.util.*;
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
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import java.text.SimpleDateFormat;
import javafx.animation.Animation;
import java.util.Calendar;

/**
 * This class provides a sudoku game, providing
 * six pre-set boards representing three levels of
 * difficulty, and an evaluate button for the player
 * to determine whether they have filled in the board
 * correctly according to the rules of the game.
 * @author Anna Marie Gann
 * @author Alexander Coleman
 */
public class Sudoku {

    protected BorderPane root = new BorderPane();
    protected Button evaluate = new Button("Evaluate");
    protected GridPane gameBoard = new GridPane();
    protected TextField[][] textArr = new TextField[9][9];

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

    protected Button easy1 = new Button("Easy Board One");
    protected Button easy2 = new Button("Easy Board Two");
    protected Button med1 = new Button("Medium Board Two");
    protected Button med2 = new Button("Medium Board Two");
    protected Button hard1 = new Button("Hard Board One");
    protected Button hard2 = new Button("Hard Board Two");
    protected VBox levelBox = new VBox();
    protected String nullString = " ";
    protected String directionsString =
	"Welcome to Sudoku! \n" +
	"To being, select a board option from the right \n" +
	"There are three difficulty levels to choose from \n" +
	"Easy, medium, and hard \n" +
	"To win, each row and column must have all of the \n" +
	"numbers 1-9 with no repeats, as should each 3x3 grid \n" +
	"to check if your solution if correct, click evaluate \n" +
	"A win message will be displayed if your board is valid \n" +
	"Happy gaming! \n" +
	"\n" +
	"\n";
    
    protected Label dirLabel = new Label(directionsString);
    protected HBox labelBox = new HBox();
    protected Label gameClock = new Label("");
    protected HBox clockBox = new HBox();
    protected Timeline time;
    protected Button h2Sol = new Button("Hard Two Solution");
    protected Button h1Sol = new Button("Hard One Solution");
    protected Button m2Sol = new Button("Med Two Solution");
    protected Button m1Sol = new Button("Med One Solution");
    protected Button e2Sol = new Button("Easy Two Solution");
    protected Button e1Sol = new Button("Easy One Solution");
    
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

    protected char[][] defaultBoard = {
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
	{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
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

    protected char[][] hardTwoSolution = {
	{'3', '4', '6', '2', '1', '7', '9', '5', '8'},
        {'7', '5', '8', '6', '3', '9', '1', '2', '4'},
        {'9', '1', '2', '4', '5', '8', '7', '6', '3'},
        {'5', '9', '1', '3', '7', '4', '2', '8', '6'},
        {'6', '2', '4', '8', '9', '1', '5', '3', '7'},
        {'8', '7', '3', '5', '6', '2', '4', '9', '1'},
        {'1', '3', '9', '7', '8', '5', '6', '4', '2'},
        {'4', '6', '5', '1', '2', '3', '8', '7', '9'},
        {'2', '8', '7', '9', '4', '6', '3', '1', '5'},
    };

     protected char[][] hardOneSolution = {
	{'1', '5', '7', '2', '3', '8', '4', '6', '8'},
        {'9', '8', '6', '4', '5', '7', '1', '2', '3'},
        {'2', '3', '4', '6', '1', '9', '5', '7', '8'},
        {'3', '1', '9', '5', '7', '2', '6', '8', '4'},
        {'4', '5', '6', '1', '8', '3', '2', '9', '7'},
        {'7', '2', '8', '9', '6', '4', '3', '5', '1'},
        {'5', '7', '1', '3', '9', '6', '8', '4', '2'},
        {'8', '4', '3', '7', '2', '5', '9', '1', '6'},
        {'6', '9', '2', '8', '4', '1', '7', '3', '5'},
    };

      protected char[][] medTwoSolution = {
	{'4', '8', '2', '7', '9', '3', '6', '1', '5'},
        {'7', '1', '3', '8', '6', '5', '9', '2', '4'},
        {'5', '9', '6', '1', '2', '4', '3', '8', '7'},
        {'8', '2', '5', '9', '3', '7', '1', '4', '6'},
        {'1', '4', '9', '2', '8', '6', '5', '7', '3'},
        {'3', '6', '7', '5', '4', '1', '2', '9', '8'},
        {'9', '5', '8', '3', '7', '2', '4', '6', '1'},
        {'2', '3', '4', '6', '1', '8', '7', '5', '9'},
        {'6', '7', '1', '4', '5', '9', '8', '3', '2'},
    };

      protected char[][] medOneSolution = {
	{'7', '1', '4', '8', '2', '5', '6', '3', '9'},
        {'3', '2', '8', '4', '6', '9', '7', '5', '1'},
        {'6', '9', '5', '7', '1', '3', '4', '8', '2'},
        {'4', '6', '2', '9', '3', '8', '1', '7', '5'},
        {'5', '3', '1', '2', '7', '4', '8', '9', '6'},
        {'8', '7', '9', '6', '5', '1', '2', '4', '3'},
        {'9', '8', '6', '5', '4', '2', '3', '1', '7'},
        {'1', '5', '7', '3', '8', '6', '9', '2', '4'},
        {'2', '4', '3', '1', '9', '7', '5', '6', '8'},
    };

      protected char[][] easyTwoSolution = {
	{'3', '1', '7', '5', '9', '2', '6', '8', '2'},
        {'9', '5', '6', '3', '4', '8', '7', '1', '2'},
        {'8', '2', '4', '6', '7', '1', '9', '3', '5'},
        {'7', '9', '3', '4', '5', '6', '8', '2', '1'},
        {'5', '6', '1', '2', '8', '7', '4', '9', '3'},
        {'2', '4', '8', '9', '1', '3', '5', '6', '7'},
        {'4', '3', '2', '7', '6', '9', '1', '5', '8'},
        {'1', '7', '9', '8', '3', '5', '2', '4', '6'},
        {'6', '8', '5', '1', '2', '4', '3', '7', '9'},
    };

    protected char[][] easyOneSolution = {
	{'3', '1', '7', '5', '4', '6', '9', '8', '2'},
        {'4', '5', '6', '9', '8', '2', '1', '7', '3'},
        {'8', '2', '9', '7', '3', '1', '6', '5', '4'},
        {'5', '8', '2', '1', '6', '9', '4', '3', '7'},
        {'6', '4', '1', '3', '2', '7', '8', '9', '5'},
        {'9', '7', '3', '8', '5', '4', '2', '1', '6'},
        {'2', '3', '5', '6', '9', '8', '7', '4', '1'},
        {'7', '9', '4', '2', '1', '5', '3', '6', '8'},
        {'1', '6', '8', '4', '7', '3', '5', '2', '9'},
    };
    
    /**
     * This method loads the easy one
     * char array into the game board if
     * the player picks the easy one action
     * @param none
     */
    public void easyOneAct() {
	easy1.setOnAction(event-> {
		gameBoardInit(easyOne);
		i = 0;
		time.playFromStart();
	    });//lambda
    }//easyOneAct

     /**
     * This method loads the easy two
     * char array into the game board if
     * the player picks the easy two action
     * @param none
     */
    public void easyTwoAct() {
	easy2.setOnAction(event-> {
		gameBoardInit(easyTwo);
		i = 0;
		time.playFromStart();
	    });//lambda
    }//easyTwoAct

     /**
     * This method loads the medium one
     * char array into the game board if
     * the player picks the medium one action
     * @param none
     */
    public void medOneAct() {
	med1.setOnAction(event-> {
		gameBoardInit(medOne);
		i = 0;
		time.playFromStart();
	    });//lambda
    }//medOneAct

     /**
     * This method loads the medium two
     * char array into the game board if
     * the player picks the medium two action
     * @param none
     */
    public void medTwoAct() {
	med2.setOnAction(event-> {
		gameBoardInit(medTwo);
		i = 0;
		time.playFromStart();
	    });//lambda
    }//medTwoAct

     /**
     * This method loads the hard one
     * char array into the game board if
     * the player picks the easy one action
     * @param none
     */
     public void hardOneAct() {
	hard1.setOnAction(event-> {
		gameBoardInit(hardOne);
		i = 0;
		time.playFromStart();
	    });//lambda
    }//hardOneAct

     /**
     * This method loads the hard two
     * char array into the game board if
     * the player picks the hard two action
     * @param none
     */
     public void hardTwoAct() {
	hard2.setOnAction(event-> {
		gameBoardInit(hardTwo);
		i = 0;
		time.playFromStart();
	    });//lambda
    }//hardTwoAct

    /**                                                                        
     * This method loads the solution                                          
     * to the second hard board when                                       
     * the player picks this option                                    
     * @param none                                                             
     */
     public void hardTwoSolution() {
        h2Sol.setOnAction(event-> {
                gameBoardInit(hardTwoSolution);
            });//lambda                                                        
    }//hardTwoAct 

   /**                                                                        
     * This method loads the solution                                          
     * to the first hard board when                                       
     * the player picks this option                                    
     * @param none                                                             
     */
     public void hardOneSolution() {
        h1Sol.setOnAction(event-> {
                gameBoardInit(hardOneSolution);
            });//lambda                                                        
    }//hardOneAct 

   /**                                                                        
     * This method loads the solution                                          
     * to the second med board when                                       
     * the player picks this option                                    
     * @param none                                                             
     */
     public void medTwoSolution() {
        m2Sol.setOnAction(event-> {
                gameBoardInit(medTwoSolution);
            });//lambda                                                        
    }//medTwoAct

    /**                                                                      
     * This method loads the solution                                          
     * to the first med board when                                       
     * the player picks this option                                    
     * @param none                                                             
     */
     public void medOneSolution() {
        m1Sol.setOnAction(event-> {
                gameBoardInit(medOneSolution);
            });//lambda                                                        
    }//medOneAct

     /**                                                                      
     * This method loads the solution                                          
     * to the second easy board when                                       
     * the player picks this option                                    
     * @param none                                                             
     */
     public void easyTwoSolution() {
        e2Sol.setOnAction(event-> {
                gameBoardInit(easyTwoSolution);
            });//lambda                                                        
    }//easyTwoAct

     /**                                                                      
     * This method loads the solution                                          
     * to the first easy board when                                       
     * the player picks this option                                    
     * @param none                                                             
     */
     public void easyOneSolution() {
        e1Sol.setOnAction(event-> {
                gameBoardInit(easyOneSolution);
            });//lambda                                                        
    }//easyOneAct 
    
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
		colConst.setPercentWidth(63.0/numCols);
		gameBoard.getColumnConstraints().add(colConst);
	    }//row constraint

	for (int i = 0; i < numRows; i++) {
		RowConstraints rowConst = new RowConstraints();
		rowConst.setPercentHeight(63.0/numRows);
		gameBoard.getRowConstraints().add(rowConst);
	    }//row constraint
	gameBoardInit(defaultBoard);
	root.setCenter(gameBoard);
	//center
    }//boardSetUp

    /**
     * This method takes in an array, either the
     * defalt of the one selected by the player, and
     * sets an id for each element, and sets the ability to
     * or not to edit based on the board selection. 
     * @parameter a multiminensional int array
     */
    public void gameBoardInit(char[][] arr) {
	for (int rows = 0; rows < 9; rows++) {
	    for (int cols = 0; cols < 9; cols++) {
		String rowString = Integer.toString(rows);
	       	String colString = Integer.toString(cols);
	       	String j = rowString + colString;
		String b = Character.toString(arr[rows][cols]);
		textArr[rows][cols] = new TextField();
	       	textArr[rows][cols].setEditable(true);
		if(!(b.equals(zeroString))) {
		    addArr = textArr[rows][cols];
		    addArr.setStyle("-fx-text-inner-color: red;");
		    addArr.setEditable(false);		    
		    addArr.setId(j);
		    addArr.setText(b);
		}//if
		else {
		    addArr = textArr[rows][cols];
		    addArr.setId(j);
		    addArr.setText(nullString);
		}//else
		gameBoard.getChildren().add(addArr);
		gameBoard.setConstraints(addArr, cols, rows);
	    }//for
	}//for
    }//gameBoardInit
    
	/**
	 * This method sets up the visual of the game,
	 * adding all of the buttons, text fields, and
	 * menu options that the player will see, and 
	 * formatting each one. 
	 * @param none
	 */
    public void setUpGame() {
	root.setStyle("-fx-text-background-color: pink;");
	winBox.setPrefWidth(350);
	winBox.setPrefHeight(100);
	winBox.getChildren().addAll(winLabel, winStatus, gameClock);
	root.setBottom(winBox);
	//bottom
	levelBox.setPrefWidth(150);
	easy1.setMinWidth(levelBox.getPrefWidth());
	easy2.setMinWidth(levelBox.getPrefWidth());
	med1.setMinWidth(levelBox.getPrefWidth());
	med2.setMinWidth(levelBox.getPrefWidth());
	hard1.setMinWidth(levelBox.getPrefWidth());
	hard2.setMinWidth(levelBox.getPrefWidth());
	easy1.setStyle("-fx-base: purple;");
	easy2.setStyle("-fx-base: purple;");
	med1.setStyle("-fx-base: purple;");
	med2.setStyle("-fx-base: purple;");
	hard1.setStyle("-fx-base: purple;");
	hard2.setStyle("-fx-base: purple;");
	e1Sol.setMinWidth(levelBox.getPrefWidth());
	e2Sol.setMinWidth(levelBox.getPrefWidth());
	m1Sol.setMinWidth(levelBox.getPrefWidth());
	m2Sol.setMinWidth(levelBox.getPrefWidth());
	h1Sol.setMinWidth(levelBox.getPrefWidth());
	h2Sol.setMinWidth(levelBox.getPrefWidth());
	e1Sol.setStyle("-fx-base: blue;");
	e2Sol.setStyle("-fx-base: blue;");
	m1Sol.setStyle("-fx-base: blue;");
	m2Sol.setStyle("-fx-base: blue;");
	h1Sol.setStyle("-fx-base: blue;");
	h2Sol.setStyle("-fx-base: blue;");
	levelBox.getChildren().addAll(easy1, easy2, med1, med2, hard1, hard2);
	levelBox.getChildren().addAll(e1Sol, e2Sol, m1Sol, m2Sol, h1Sol, h2Sol);
	root.setRight(levelBox);
	//right
	dirLabel.setWrapText(true);
	labelBox.getChildren().add(dirLabel);
	root.setTop(labelBox);
	//top
	evaluateBox.getChildren().add(evaluate);
	root.setLeft(evaluateBox);
	//left
    }//setUpGame

    /**
     * This method sets up action events
     * to change the game board as the 
     * player adds new values to the board.
     * @param none
     */
    /**
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

*/

    public void getValues() {
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		String tempVal = textArr[i][j].getText();
		values[i][j] = Character.getNumericValue(tempVal.charAt(0));
	    }//forj
	}//fori
    }//getvalues
    
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
		if(validRow() == true && validColumn() == true && validBox() == true){
		    winStatus.setText(winString);
		    time.stop();
			i= 0;
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
	     * @returns boolean
	     */
    public boolean validBox() {
	getValues();
	for (int i = 0; i < 9; i+=3) {
	    for (int j = 0; j < 9; j+=3) {
		for (int pos = 0; pos < 9; pos++) {
		    for (int posTwo = pos+1; posTwo <9; posTwo++) {
			if(values[i+ pos%3][j+pos/3]==values[i+posTwo%3][j+posTwo/3]){
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
	     * @returns boolean
	     */
    public boolean validRow(){
	getValues();
	for (int r = 0; r < 9; r++) {
	    for (int c = 0; c < 9; c++) {
		for (int c2 = c+1; c2 < 9; c2++) {
		    //		    System.out.println("V"+ values[r][c]+ " r:"+r + " c:"+c +" C2:" + c2 + " val2: " + values[r][c2]);
		    if(values[r][c] == values[r][c2])
			{
			    System.out.println("V"+ values[r][c]+ " r:"+r + " c:"+c +" C2:" + c2 + " val2: " + values[r][c2]);
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
	     * @returns boolean
	     */
    public boolean validColumn() {
	getValues();
	for (int c = 0; c < 9; c++) {
	    for (int r = 0; r < 9; r++) {
		for (int r2 = r+1; r2 < 9; r2++) {
		    if(values[r][c] == values[r2][c])
			{
			    return false;
			}//if
		}//for r2
	    }//for r
	}//for c
	return true;
    }//validColumn
    
    public String toTime(int a){
	int hours =  a/ 60/ 60;
	int min = (a - (hoursToSeconds(hours)))
                / 60;
	int secs = a
                - ((hoursToSeconds(hours)) + (minutesToSeconds(min)));

	return Integer.toString(hours)+":"+Integer.toString(min)+":"+Integer.toString(secs);
    }//toTime

    private int hoursToSeconds(int hours) {
        return hours * 60* 60;
    }

    private int minutesToSeconds(int minutes) {
        return minutes * 60;
    }
    	int i = 0;
    public void inI(){
	i++;
    }
    /**
     * This method provides a clock which
     * tracks how long the player takes
     * to successfullt complete the puzzle
     * @param none
     */
    public void tl() {
	time = new Timeline(new KeyFrame(Duration.seconds(1), e-> {
		    inI();
		    gameClock.setText(toTime(i));
	}), //duration
	    new KeyFrame(Duration.seconds(1))
	    ); //event handler
	time.setCycleCount(Animation.INDEFINITE);
	//repeat until called to stop
    }//tl
	    /**
	     * This method provides the actual
	     * game loop and is the only method
	     * directly called from the arcade
	     * app class. 
	     * @param a stage for the game
	     */
    public void play(Stage sudokuStage) {
	Scene scene = new Scene(root, 600, 600);
	tl();//game clock
	boardSetUp(); //sets board constraints
	setUpGame(); //loads user selection to board
	easyOneAct(); //if easy one board is selected
	easyTwoAct(); //if easy two board is selected
	medOneAct(); //if med one board is selected
	medTwoAct(); //if med two is selected
	hardOneAct(); //if hard one is selected
	hardTwoAct(); //if hard two is selected
	hardTwoSolution(); //solution to second hard board
	hardOneSolution(); //sol to first hard puzzle
	medTwoSolution(); //sol to second med puzzle
	medOneSolution(); //sol to first med board
	easyTwoSolution(); //sol to second easy board
	easyOneSolution(); //sol to first east puzzle
	evaluate(); //to evaluate the board
	sudokuStage.setTitle("Sudoku"); //title
	sudokuStage.setScene(scene);
	sudokuStage.sizeToScene(); //format
	sudokuStage.show();//output
    }//startGame

}//sudoku
