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
    //border pane to store enture sudoku app
    protected BorderPane root = new BorderPane();
    //button to evaluate board
    protected Button evaluate = new Button("Evaluate");
    //grid pane to hold entire sudoku boaed
    protected GridPane gameBoard = new GridPane();
    //text field array
    protected TextField[][] textArr = new TextField[9][9];
    //where the win is displayed
    protected TextField winStatus = new TextField(" ");
    protected Label winLabel = new Label("Win or Lose?");
    protected HBox winBox = new HBox();
    //storage for win text field
    protected Boolean gameWin = false;
    //array to store values
    protected int[][] values = new int[9][9];
    protected Group group = new Group();
    protected TextField tempText = new TextField();
    //storage
    protected VBox evaluateBox = new VBox();
    protected int x = 0;
    protected int y = 0;
    //for comparision
    protected String zeroString = "0";
    //to use in out inital arrays
    protected TextField addArr;
    //buttons for each game board option
    protected Button easy1 = new Button("Easy Board One");
    protected Button easy2 = new Button("Easy Board Two");
    protected Button med1 = new Button("Medium Board Two");
    protected Button med2 = new Button("Medium Board Two");
    protected Button hard1 = new Button("Hard Board One");
    protected Button hard2 = new Button("Hard Board Two");
    //storage for game board options
    protected VBox levelBox = new VBox();
    //for comparison
    protected String nullString = " ";
    //string for direction label
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
    //storage for direction label
    protected Label gameClock = new Label("");
    //game clock which runs as player plays
    protected HBox clockBox = new HBox();
    //storage for clock
    //timeline
    protected Timeline time;
    //solution buttons
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
    };//hard option one
    
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
    };//hard option two
    
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
    };//medium option one
    
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
    };//medium option two
    
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
    };//easy board option one
    
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
    };//easy board option two

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
    };//default board used before user selects one
    
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
    };//positions in grid of each TextField

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
    };//hardTwoSolution

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
     };//hardOneSolution

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
      };//medTwoSolution

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
      };//medOneSolution

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
      };//easyTwoSolution

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
    };//easyOneSolution
    
    /**
     * This method loads the easy one
     * char array into the game board if
     * the player picks the easy one action
     * @param none
     */
    public void easyOneAct() {
	easy1.setOnAction(event-> {
		gameBoardInit(easyOne);
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
	int numRows = 9; //row num
	int numCols = 9; //col num
	//formatting
	gameBoard.setGridLinesVisible(true);
	//set column constraints
	for (int i = 0; i < numCols; i++) {
		ColumnConstraints colConst = new ColumnConstraints();
		colConst.setPercentWidth(63.0/numCols);
		gameBoard.getColumnConstraints().add(colConst);
	    }//row constraint
	//set row constraints
	for (int i = 0; i < numRows; i++) {
		RowConstraints rowConst = new RowConstraints();
		rowConst.setPercentHeight(63.0/numRows);
		gameBoard.getRowConstraints().add(rowConst);
	    }//row constraint
	gameBoardInit(defaultBoard); //init to def
	root.setCenter(gameBoard); //center
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
	       	String j = rowString + colString; //position
		String b = Character.toString(arr[rows][cols]);
		textArr[rows][cols] = new TextField();
	       	textArr[rows][cols].setEditable(true);
		if(!(b.equals(zeroString))) {
		    addArr = textArr[rows][cols];
		    addArr.setStyle("-fx-text-inner-color: red;");
		    addArr.setEditable(false);//cant change		    
		    addArr.setId(j); //set id
		    addArr.setText(b); //set text
		}//if
		else {
		    addArr = textArr[rows][cols];
		    addArr.setId(j); //give an id
		    addArr.setText(nullString);
		}//else
		//put on board
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
	winBox.setPrefWidth(350); //formatting
	winBox.setPrefHeight(100); //formatting
	winBox.getChildren().addAll(winLabel, winStatus, gameClock);
	root.setBottom(winBox); //bottom
	levelBox.setPrefWidth(150);
	easy1.setMinWidth(levelBox.getPrefWidth());
	easy2.setMinWidth(levelBox.getPrefWidth());
	med1.setMinWidth(levelBox.getPrefWidth());
	med2.setMinWidth(levelBox.getPrefWidth());
	hard1.setMinWidth(levelBox.getPrefWidth());
	hard2.setMinWidth(levelBox.getPrefWidth());
	easy1.setStyle("-fx-base: purple;"); //color
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
	e1Sol.setStyle("-fx-base: blue;"); //set color
	e2Sol.setStyle("-fx-base: blue;");
	m1Sol.setStyle("-fx-base: blue;");
	m2Sol.setStyle("-fx-base: blue;");
	h1Sol.setStyle("-fx-base: blue;");
	h2Sol.setStyle("-fx-base: blue;");
	levelBox.getChildren().addAll(easy1, easy2, med1, med2, hard1, hard2);
	levelBox.getChildren().addAll(e1Sol, e2Sol, m1Sol, m2Sol, h1Sol, h2Sol);
	root.setRight(levelBox); //right
	dirLabel.setWrapText(true);
	labelBox.getChildren().add(dirLabel);
	root.setTop(labelBox); //top
	evaluateBox.getChildren().add(evaluate);
	root.setLeft(evaluateBox); ///left
    }//setUpGame

    /**
     * This method obtains the value of each
     * text box, both the predetermined ones
     * and the user input ones, and puts the
     * values into an array which is then
     * evaluated to determine if it does
     * or does not represent a valid solution
     * @param none
     */
    public void getValues() {
	//i is rows
	//j is cols
	//get text at each indice of the game board
	//store text in an int array
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		String tempV = textArr[i][j].getText();
		values[i][j] = Character.getNumericValue(tempV.charAt(0));
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
		//if the players board is valid
		if(validRow()==true&&validColumn()==true&&validBox()==true){
		    winStatus.setText(winString);
		    time.stop();
		}//if
		
		//if board was not valid
		else {
		    winStatus.setText(loseString);
		}//else
	    });//lambda
    }//evaluate

	    /**
	     * This method checks whether each
	     * three by three sub box only has one of
	     * each number one through nine, and is a valid
	     * box according to the game rules. 
	     * @param none
	     * @returns boolean
	     */
    public boolean validBox() {
	getValues();
	//initalize arr of values
	//a is rows
	//b is cols
	for (int a = 0; a < 9; a++) {
	    for (int b = 0; b < 9; b++) {
		if(values[a][b]>9 || values[a][b]<1) {
		    return false;
		}//if
	    }//for b
	}//for a
	//parse the array in three col
	//and three row intervals
	//to consider each grid separately
	for (int i = 0; i < 9; i+=3) {
	    for (int j = 0; j < 9; j+=3) {
		for (int pos = 0; pos < 9; pos++) {
		    for (int posTwo = pos+1; posTwo <9; posTwo++) {
			if(values[i+pos%3][j+pos/3]==values[i+posTwo%3][j+posTwo/3]){
			    return false;
			}//if
		    }//posTwo
		}//for pos
	    }//for j
	}//for i
	return true; //if never false
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
	//initialize array of values
	//a is rows
	//b is cols
	for (int a = 0; a < 9; a++) {
	    for (int b = 0; b < 9; b++) {
		if(values[a][b]>9 || values[a][b]<1) {
		    return false;
		}//if
	    }//for b
	}//for a

	//r is rows
	//c is cols
	//c2 is a col position to comp to
	for (int r = 0; r < 9; r++) {
	    for (int c = 0; c < 9; c++) {
		for (int c2 = c+1; c2 < 9; c2++) { 
		    if(values[r][c] == values[r][c2])
			{
			    return false;  
			}//if
		}//for c2
	    }//for c
	}//for r
	return true; //if never f
    }//validRow

	    /**
	     * This method checks to 
	     * make sure that each column of the player's
	     * board is valid accodring to game rules.
	     * @param none
	     * @returns boolean
	     */
    public boolean validColumn() {
	getValues(); //inital arr of values
	//a is row indices b is col indices
	for (int a = 0; a < 9; a++) {
	    for (int b = 0; b < 9; b++) {
		if(values[a][b]>9 || values[a][b]<1) {
		    return false;
		}//if
	    }//for b
	}//for a

	//c represents col incices
	//r represents row indices
	for (int c = 0; c < 9; c++) {
	    for (int r = 0; r < 9; r++) {
		for (int r2 = r+1; r2 < 9; r2++) {
		    if(values[r][c] == values[r2][c])
			{
			    return false; //if same
			}//if
		}//for r2
	    }//for r
	}//for c
	return true; //true if never f
    }//validColumn

    /**
     * This method converts an int 
     * representation of time to a
     * string representation of time
     *@param an int rep of time
     *@returns a string represetation of time
     */
    public String toTime(int a){
	int hours =  a/ 60/ 60;
	int min = (a - (hoursToSeconds(hours)))
                / 60;
	int secs = a
                - ((hoursToSeconds(hours)) + (minutesToSeconds(min)));

	return Integer.toString(hours)+":"+Integer.toString(min)+":"+Integer.toString(secs);
    }//toTime

    /**
     * This method converts a number
     * of hours to a number of seconds
     *@param an int representing hours
     *@returns an int num of seconds
     */
    private int hoursToSeconds(int hours) {
        return hours * 60* 60;
    }//hoursToSeconds

    /**
     * This method converts minutes
     * to seconds.
     *@param an int repping a num of minutes
     *@returns an int num of seconds
     */
    private int minutesToSeconds(int minutes) {
        return minutes * 60;
    }//minutesToSeconds

    int i = 0;

    /**
     * This method increments I
     * to be used in the timeline
     *@param none
     */
    public void inI(){
	i++;
    }//inI
    
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
