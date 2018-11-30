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
    protected TilePane buttonPane = new TilePane();
    protected GridPane gameBoard = new GridPane();
    
    protected Button oneB = new Button("1");
    protected Button twoB = new Button("2");
    protected Button threeB = new Button("3");
    protected Button fourB = new Button("4");
    protected Button fiveB = new Button("5");
    protected Button sixB = new Button("6");
    protected Button sevenB = new Button("7");
    protected Button eightB = new Button("8");
    protected Button nineB = new Button("9");
    
    protected char oneC = '1';
    protected char twoC = '2';
    protected char threeC = '3';
    protected char fourC = '4';
    protected char fiveC = '5';
    protected char sixC = '6';
    protected char sevenC = '7';
    protected char eightC = '8';
    protected char nineC = '9';

    protected Button playAgain = new Button("Play again");
    protected Button clearBoard = new Button("Clear Board");

    protected TextField winStatus = new TextField(" ");
    protected Label winLabel = new Label("Win or Lose?");
    protected HBox winBox = new HBox();
    
    // protected Text directions = new Text("directions");

    //column one
    protected TextField onet = new TextField();
    protected TextField twot = new TextField();
    protected TextField threet = new TextField();
    protected TextField fourt = new TextField();
    protected TextField fivet = new TextField();
    protected TextField sixt = new TextField();
    protected TextField sevent = new TextField();
    protected TextField eightt = new TextField();
    protected TextField ninet = new TextField();

    //column two
    protected TextField tent = new TextField();
    protected TextField elevent = new TextField();
    protected TextField twelvet = new TextField();
    protected TextField thirteent = new TextField();
    protected TextField fourteent = new TextField();
    protected TextField fifteent = new TextField();
    protected TextField sisteent = new TextField();
    protected TextField seventeent = new TextField();
    protected TextField eighteent = new TextField();

    //column three
    protected TextField nineteent = new TextField();
    protected TextField twentyt = new TextField();
    protected TextField twentyonet = new TextField();
    protected TextField twentytwot = new TextField();
    protected TextField twentythreet = new TextField();
    protected TextField twentyfourt = new TextField();
    protected TextField twentyfivet = new TextField();
    protected TextField twentysixt = new TextField();
    protected TextField twentysevent =	new TextField();

    //column four
    protected TextField twentyeightt = new TextField();
    protected TextField twentyninet = new TextField();
    protected TextField thirtyt = new TextField();
    protected TextField thirtyonet = new TextField();
    protected TextField thirtytwot = new TextField();
    protected TextField thirtythreet = new TextField();
    protected TextField thirtyfourt = new TextField();
    protected TextField thirtyfivet = new TextField();
    protected TextField thirtysixt = new TextField();

    //column five
    protected TextField thirtysevent = new TextField();
    protected TextField thirtyeightt = new TextField();
    protected TextField thirtyninet = new TextField();
    protected TextField fortyt = new TextField();
    protected TextField fortyonet = new TextField();
    protected TextField fortytwot = new TextField();
    protected TextField fortythreet = new TextField();
    protected TextField fortyfourt = new TextField();
    protected TextField fortyfivet = new TextField();

    //column six
    protected TextField fortysixt = new TextField();
    protected TextField fortysevent = new TextField();
    protected TextField fortyeightt = new TextField();
    protected TextField fortyninet = new TextField();
    protected TextField fiftyt = new TextField();
    protected TextField fiftyonet = new TextField();
    protected TextField fiftytwot = new TextField();
    protected TextField fiftythreet = new TextField();
    protected TextField fiftyfourt = new TextField();

    //column seven
    protected TextField fiftyfivet = new TextField();
    protected TextField fiftysixt = new TextField();
    protected TextField fiftysevent = new TextField();
    protected TextField fiftyeightt = new TextField();
    protected TextField fiftyninet = new TextField();
    protected TextField sixtyt = new TextField();
    protected TextField sixtyonet = new TextField();
    protected TextField sixtytwot = new TextField();
    protected TextField sixtythreet = new TextField();

    //column eight
    protected TextField sixtyfourt = new TextField();
    protected TextField sixtyfivet = new TextField();
    protected TextField sixtysixt = new TextField();
    protected TextField sixtysevent = new TextField();
    protected TextField sixtyeightt = new TextField();
    protected TextField sixtyninet = new TextField();
    protected TextField seventyt = new TextField();
    protected TextField seventyonet = new TextField();
    protected TextField seventytwot = new TextField();

    //column nine
    protected TextField seventythreet = new TextField();
    protected TextField seventyfourt = new TextField();
    protected TextField seventyfivet = new TextField();
    protected TextField seventysixt = new TextField();
    protected TextField seventysevent = new TextField();
    protected TextField seventyeightt = new TextField();
    protected TextField seventyninet = new TextField();
    protected TextField eightyt = new TextField();
    protected TextField eightyonet = new TextField();

    protected Boolean gameWin = false;

    //booleans to evaluate rows
    protected boolean rowOne = false;
    protected boolean rowTwo = false;
    protected boolean rowThree = false;
    protected boolean rowFour = false;
    protected boolean rowFive = false;
    protected boolean rowSix = false;
    protected boolean rowSeven = false;
    protected boolean rowEight = false;
    protected boolean rowNine = false;

    //booleans to evaluate columns
    protected boolean colOne = false;
    protected boolean colTwo = false;
    protected boolean colFour = false;
    protected boolean colFive = false;
    protected boolean colSix = false;
    protected boolean colSeven = false;
    protected boolean colEight = false;
    protected boolean colNine = false;

    //boolean evaluations of boxes
    protected boolean boxOne = false;
    protected boolean boxTwo = false;
    protected boolean boxThree = false;
    protected boolean boxFour = false;
    protected boolean boxFive = false;
    protected boolean boxSix = false;
    protected boolean boxSeven = false;
    protected boolean boxEight = false;
    protected boolean boxNine = false;
    
    protected int[][] values = new int[9][9];
    protected TextField[][] puzzle = new TextField[9][9];
    protected Group group = new Group();
    protected TextField tempText = new TextField();
    
    public void setUpGame() {
	buttonPane.getChildren().addAll(oneB, twoB, threeB, fourB, fiveB, sixB, sevenB, eightB, nineB);
	buttonPane.setPrefColumns(3);
	buttonPane.setPrefRows(3);
	/**
	gameBoard.getChildren().addAll(onet, twot, threet, fourt, fivet, sixt, sevent, eightt, ninet);
	gameBoard.getChildren().addAll(tent, elevent, twelvet, thirteent, fourteent, fifteent, sixteent, seventeent, eighteent);
	gameBoard.getChildren().addAll(nineteent, twentyt, twentyonet, twentytwot, twentythreet, twentyfourt, twentyfivet, twentysixt, twentysevent);
	gameBoard.getChildren().addAll(twentyeightt, twentyninet, thirtyt, thirtyonet, thirtytwot, thirtythreet, thirtyfourt, thirtyfivet, thirtysixt);
	gameBoard.getChildren().addAll(thirtysevent, thirtyeightt, thirtyninet, fortyt, fortyonet, fortytwot, fortythreet, fortyfourt, fortyfivet);
	gameBoard.getChildren().addAll(fortysixt, fortysevent, fortyeightt, fortyninet, fiftyt, fiftyonet, fiftytwot, fiftythreet, fiftyfourt);
	gameBoard.getChildren().addAll(fiftyfivet, fiftysixt, fiftysevent, fiftyeightt, fiftyninet, sixtyt, sixtyonet, sixtytwot, sixtythreet);
	gameBoard.getChildren().addAll(sixtyfourt, sixtyfivet, sixtysixt, sixtysevent, sixtyeightt, sixtyninet, seventyt, seventyonet, seventytwot);
	gameBoard.getChildren().addAll(seventythreet, seventyfourt, seventyfivet, seventysixt, seventysevent, seventyeightt, seventyninet, eightyt, eightyonet);

	//format row one
	gameBoard.setConstraints(onet, 0, 0);
	gameBoard.setConstraints(twot, 1, 0);
	gameBoard.setConstraints(threet, 2, 0);
	gameBoard.setConstraints(fourt, 3, 0);
	gameBoard.setConstraints(fivet, 4, 0);
	gameBoard.setConstraints(sixt, 5, 0);
	gameBoard.setConstraints(sevent, 6, 0);
	gameBoard.setConstraints(eightt, 7, 0);
	gameBoard.setConstraints(ninet, 8, 0);

	//format row two
	gameBoard.setConstraints(tent, 0, 1);
	gameBoard.setConstraints(elevent, 1, 1);
	gameBoard.setConstraints(twelvet, 2, 1);
	gameBoard.setConstraints(thirteent, 3, 1);
	gameBoard.setConstraints(fourteent, 4, 1);
	gameBoard.setConstraints(fifteent, 5, 1);
	gameBoard.setConstraints(sixteent, 6, 1);
	gameBoard.setConstraints(seventeent, 7, 1);
	gameBoard.setConstraints(eighteent, 8, 1);	

	//format row three
	gameBoard.setConstraints(nineteent, 0, 2);
	gameBoard.setConstraints(twentyt, 1, 2);
	gameBoard.setConstraints(twentyonet, 2, 2);
	gameBoard.setConstraints(twentytwot, 3, 2);
	gameBoard.setConstraints(twentythreet, 4, 2);
	gameBoard.setConstraints(twentyfourt, 5, 2);
	gameBoard.setConstraints(twentyfivet, 6, 2);
	gameBoard.setConstraints(twentysixt, 7, 2);
	gameBoard.setConstraints(twentysevent, 8, 2);

	//format row four
	gameBoard.setConstraints(twentyeightt, 0, 3);
	gameBoard.setConstraints(twentyninet, 1, 3);
	gameBoard.setConstraints(thirtyt, 2, 3);
	gameBoard.setConstraints(thirtyonet, 3, 3);
	gameBoard.setConstraints(thirtytwot, 4, 3);
	gameBoard.setConstraints(thirtythreet, 5, 3);
	gameBoard.setConstraints(thirtyfourt, 6, 3);
	gameBoard.setConstraints(thirtyfivet, 7, 3);
	gameBoard.setConstraints(thirtysixt, 8, 3);

	//format row five
	gameBoard.setConstraints(thirtysevent, 0, 4);
	gameBoard.setConstraints(thirtyeightt, 1, 4);
	gameBoard.setConstraints(thirtyninet, 2, 4);
	gameBoard.setConstraints(fortyt, 3, 4);
	gameBoard.setConstraints(fortyfivet, 4, 4);
	gameBoard.setConstraints(fortysixt, 5, 4);
	gameBoard.setConstraints(fortysevent, 6, 4);
	gameBoard.setConstraints(fortyeightt, 7, 4);
	gameBoard.setConstraints(fortyninet, 8, 4);

	//format row six
	gameBoard.setConstraints(fiftyt, 0, 5);
	gameBoard.setConstraints(fiftyonet, 1, 5);
	gameBoard.setConstraints(fiftytwot, 2, 5);
	gameBoard.setConstraints(fiftythreet, 3, 5);
	gameBoard.setConstraints(fiftyfourt, 4, 5);
	gameBoard.setConstraints(fiftyfivet, 5, 5);
	gameBoard.setConstraints(fiftysixt, 6, 5);
	gameBoard.setConstraints(fiftysevent, 7, 5);
	gameBoard.setConstraints(fiftyeightt, 8, 5);

	//format row seven
	gameBoard.setConstraints(fiftyninet, 0, 6);
	gameBoard.setConstraints(sixtyt, 1, 6);
	gameBoard.setConstraints(sixtyonet, 2, 6);
	gameBoard.setConstraints(sixtytwot, 3, 6);
	gameBoard.setConstraints(sixtythreet, 4, 6);
	gameBoard.setConstraints(sixtyfourt, 5, 6);
	gameBoard.setConstraints(sixtyfivet, 6, 6);
	gameBoard.setConstraints(sixtysixt, 7, 6);
	gameBoard.setConstraints(sixtysevent, 8, 6);

	//format row eight
	gameBoard.setConstraints(sixtyeightt, 0, 7);
	gameBoard.setConstraints(sixtyninet, 1, 7);
	gameBoard.setConstraints(seventyt, 2, 7);
	gameBoard.setConstraints(seventyonet, 3, 7);
	gameBoard.setConstraints(seventytwot, 4, 7);
	gameBoard.setConstraints(seventythreet, 5, 7);
	gameBoard.setConstraints(seventyfourt, 6, 7);
	gameBoard.setConstraints(seventyfivet, 7, 7);
	gameBoard.setConstraints(seventysixt, 8, 7);

	//format row nine
	gameBoard.setConstraints(onet, 0, 8);
	gameBoard.setConstraints(twot, 1, 8);
	gameBoard.setConstraints(threet, 2, 8);
	gameBoard.setConstraints(fourt, 3, 8);
	gameBoard.setConstraints(fivet, 4, 8);
	gameBoard.setConstraints(sixt, 5, 8);
	gameBoard.setConstraints(sevent, 6, 8);
	gameBoard.setConstraints(eightt, 7, 8);
	gameBoard.setConstraints(ninet, 8, 8);
	*/
	winBox.getChildren().addAll(winLabel, winStatus);
	
	//	root.setCenter(gameBoard);
	root.setRight(buttonPane);
	root.setTop(winBox);
	       }//setUpGame

    public void initializeTextArray() {
	for (int row = 0; row > 9; row++) {
	    for (int col = 0; col > 9; col++) {
		puzzle[row][col] = new TextField("  ");
		gameBoard.getChildren().add(puzzle[row][col]);
		values[row][col] = 0;
		group.getChildren().add(puzzle[row][col]);
	    }//nested for
	}//for
	root.setCenter(gameBoard);
    }//initializeTextArray

    public void groupHandler() {
	group.setOnMousePressed(event-> {
		Node source = (Node)event.getSource();
		int x = gameBoard.getColumnIndex(source);
		int y = gameBoard.getRowIndex(source);
		
		onet.setOnAction(k -> {
			puzzle[x][y].setText("1");
			values[x][y] = 1;
		    });//button one is clicked

		twot.setOnAction(e -> {
                        puzzle[x][y].setText("2");
                        values[x][y] = 2;
                    });//button two is clicked

		threet.setOnAction(a -> {
                        puzzle[x][y].setText("3");
                        values[x][y] = 3;
                    });//button three is clicked

		fourt.setOnAction(b -> {
                        puzzle[x][y].setText("4");
                        values[x][y] = 4;
                    });//button four is clicked

		fivet.setOnAction(c -> {
                        puzzle[x][y].setText("5");
                        values[x][y] = 5;
                    });//button five is clicked

		sixt.setOnAction(d -> {
                        puzzle[x][y].setText("6");
                        values[x][y] = 6;
                    });//button six is clicked

		sevent.setOnAction(f -> {
                        puzzle[x][y].setText("7");
                        values[x][y] = 7;
                    });//button seven is clicked

		eightt.setOnAction(g -> {
                        puzzle[x][y].setText("8");
                        values[x][y] = 8;
                    });//button eight is clicked

		ninet.setOnAction(h -> {
                        puzzle[x][y].setText("9");
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
	//boxOne
	for (int i = 0; i > 2; i++) {
	    for (int j = 0; j > 2; j++) {
		if (values[i][j] == values[i+1][j]) {
		    boxOne = false;
		}//if

		else if (values[i][j] == values[i][j+1]) {
		    boxOne = false;
		}//else if
	    }//nestedfor
	}//for
    }//validBox

    public void validRow(){
	;
    }//validRow

    public void validColumn() {
	;
    }//validColumn

    public void startGame(Stage sudokuStage) {
	Scene scene = new Scene(root, 700, 500);
	sudokuStage.setTitle("Sudoku");
	sudokuStage.setScene(scene);
	sudokuStage.sizeToScene();
	sudokuStage.show();
    }//startGame
    
}//sudoku
