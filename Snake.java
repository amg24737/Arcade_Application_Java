package cs1302.arcade;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler; 
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.animation.KeyValue;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.animation.FadeTransition;
import javafx.scene.text.Font;




/**
 * This class provides a snake game, providing
 * gridboard layout representing three levels of
 * difficulty, with key controls that moves the snake
 * @author Anna Marie Gann
 * @author Alexander Coleman
 */
public class Snake{

    //variables
    VBox vbox =new VBox();
    GridPane grid = new GridPane();
    int columns = 26;
    int rows = 26;
    Timeline timeline;
    Timeline timeline2;
    Timeline timeline3;
    boolean Right=true;
    boolean Left=false;
    boolean Up=false;
    boolean Down=false;
    int points = 676;
    int x = 2;
    int y = 5;
    Rectangle r = new Rectangle();
    Rect dot = new Rect();
    Rectangle rect1;
    Rectangle rect2; 
    Rectangle fruit = new Rectangle();
    Label lv;
    Label pt;
    Label con;
    Label title;
    Label over;
    Label winner;
    boolean one = false;
    boolean two = false;
    boolean three = false; 
    FadeTransition ft;
    HBox display = new HBox();
    
    /**
     * Styles r object in shape of head.
     * Styles fruit object, and sets
     * position in gridpane
     */
    public void draw(){
	r.setWidth(10);
	r.setHeight(10);
	r.setArcWidth(20);
	r.setArcHeight(20);

	fruit.setFill(Color.RED);
	fruit.setWidth(15);
	fruit.setHeight(15);
	grid.getChildren().add(fruit);
	grid.setConstraints(fruit, 15, 6);
    }//draw

    /**
     * Sets up key instructions
     * game over label and winner label.
     * FadeTransition settings are made.
     * Adds winner and game over label to HBox
     */
    public void instru(){
	con = new Label("USE W-Up,A-Left,S-Down,D-Right KEYS TO CONTROL SNAKE MOVEMENTS");
	con.setFont(new Font("Arial", 17));
	over = new Label("GAME OVER");
	over.setFont(new Font("Arial", 30));
	over.setTextFill(Color.RED);
	over.setVisible(false);
	winner = new Label("   WINNER!!!");
	winner.setFont(new Font("Arial", 30));
	winner.setVisible(false);
	ft = new FadeTransition(Duration.millis(3000), over);
	ft.setFromValue(1.0);
	ft.setToValue(0.3);
	ft.setCycleCount(4);
	ft.setAutoReverse(true);
	display.getChildren().addAll(over,winner);
    }//instru

    /**
     * Sets up scoreboard thats used to 
     * display score from current game
     * @return scoreB HBox for scoring purposes
     */
    public HBox scoreBoard(){
	HBox scoreB = new HBox();
	Label score = new Label("Score : ");
	lv = new Label();
	pt = new Label();
	pt.setText("0");
	scoreB.getChildren().addAll(score,pt,lv);
	return scoreB;
    }//scoreBoard

     /**
     * Sets up instructions on how
     * to choose a level
     * @return levelB HBox for level purposes
     */
    public HBox levelBoard(){
	title = new Label("        SNAKE");
	title.setFont(new Font("Arial", 23));
	Label choose = new Label("E-Easy, Q-Med, R-Hard, N-New Game");
	choose.setFont(new Font("Arial", 11));
	HBox levelB = new HBox();
	levelB.getChildren().addAll(choose,title);
	return levelB;
    }//scoreBoard

     /**
     * UpDates players score
     */
    public void yourScore(){
	pt.setText(Integer.toString(dot.getRec().size()+1));
    }//yourScore
    
    /**
     * Checks to see if shapes have overlap
     * each other
     * @param head
     * @param food
     * @return true overlapping occurred
     * @return false no overlap
     */
    public boolean overlaps(Shape head, Shape food){
	if(head.getBoundsInParent().intersects(food.getBoundsInParent())){
	    return true;
	    }
	return false;
    }

     /**
     * Checks to see if snake has collided
     * with its own body
     * @return true snake collided
     * @return false snake safe
     */
    public boolean collide(){
	for(int z = 0; z < dot.getRec().size();z++){
	    if( overlaps(r, dot.getRectangle(z))==true){
		return true;
	    }
	}//for
	return false;
    }//collide

    /**
     * Checks to see if you're the winner
     *@return true game is over
     *@return false game not over
     */
    public boolean done(){
	if(points == dot.getRec().size()+1){
	    return true;
	}
	return false;
    }//done

    /**
     * Randomizes the position of
     * fruit on gridpane
     */
    public void appear(){
	int randNum = (int)((Math.random()*25));
	int randNum2 = (int)((Math.random()*25));
	grid.setConstraints(fruit, randNum2, randNum);
    }//appear

     /**
     *This is timeline loop normal speed
     * speed for game level 1.
     * Keeps the snake moving and checks
     * for collisions and out of bounds
     */
    public void level(){
	timeline = new Timeline(new KeyFrame(Duration.millis(750), event -> {
		    Platform.runLater(() -> {
			    /* interact with scene graph */
			    if(overlaps(r,fruit)){
				grid.getChildren().add(rect2 = dot.moreDots());
				appear();
				yourScore();
			    }//if
			    if(collide()){
			       	timeline.pause();
				over.setVisible(true);
				ft.play();
				System.out.println(pt.getText());
			    }//if
			    if(done()){
				timeline.pause();
				winner.setVisible(true);
			    }//if
			});
		    
		    if(Right){
			//get positon before updating
			dot.move(x,y);
			
			y++;
			if(y == 26){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			grid.setConstraints(r, y, x);
			
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
			
		    }//if
		    if(Left){
			dot.move(x,y);
			
			y--;
			try{
			grid.setConstraints(r, y, x);
		        }
			catch(Exception out){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));			    
			}//for
		    }//if
		    if(Down){
			dot.move(x,y);
			x++;
			if(x == 26){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			grid.setConstraints(r, y, x);
			
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    if(Up){
			dot.move(x,y);
			x--;
			try{
			    grid.setConstraints(r, y, x);
		        }
			catch(Exception out){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    
	}));	
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.play();
    }//level

     /**
     *This is a second timeline loop but faster
     * speed for game level 2
     * Keeps the snake moving and checks
     * for collisions and out of bounds
     */
    public void level2(){
	//timeline2
	timeline2 = new Timeline(new KeyFrame(Duration.millis(500), event -> {
		    Platform.runLater(() -> {
			    //interact with scene graph 
			    if(overlaps(r,fruit)){
				grid.getChildren().add(rect2 = dot.moreDots());
				appear();
				yourScore();
			    }//if
			    if(collide()){
				timeline2.pause();
				over.setVisible(true);
				ft.play();
			    }//if
			    if(done()){
				timeline2.pause();
				winner.setVisible(true);
			    }//if
			});
		    if(Right){
			//get positon before updating
			dot.move(x,y);
			
			y++;
			if(y == 26){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			grid.setConstraints(r, y, x);
			
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
			
		    }//if
		    if(Left){
			dot.move(x,y);
			
			y--;
			try{
			    grid.setConstraints(r, y, x);
		        }
			catch(Exception out){
			    timeline2.pause();
			    over.setVisible(true);
			    ft.play();
			}	        
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    if(Down){
			dot.move(x,y);
			x++;
			if(x == 26){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			grid.setConstraints(r, y, x);
		        
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    if(Up){
			dot.move(x,y);
			x--;
			try{
			    grid.setConstraints(r, y, x);
		        }
			catch(Exception out){
			    timeline2.pause();
			    over.setVisible(true);
			    ft.play();
			}	        
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    
	}));
	timeline2.setCycleCount(Timeline.INDEFINITE);
	timeline2.play();
    }//level2

    /**
     *This is a third timeline loop but faster
     * speed for game level 3
     * Keeps the snake moving and checks
     * for collisions and out of bounds
     */
     public void level3(){
	//timeline3
	 timeline3 = new Timeline(new KeyFrame(Duration.millis(350), event -> {
		    Platform.runLater(() -> {
			    //interact with scene graph
			    if(overlaps(r,fruit)){
				grid.getChildren().add(rect2 = dot.moreDots());
				appear();
				yourScore();
			    }//if
			    if(collide()){
				timeline3.pause();
				over.setVisible(true);
				ft.play();
			    }//if
			    if(done()){
				timeline3.pause();
				winner.setVisible(true);
			    }//if
			});
		    if(Right){
			//get positon before updating
			dot.move(x,y);
			
			y++;
			if(y == 26){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			grid.setConstraints(r, y, x);
	        
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
			
		    }//if
		    if(Left){
			dot.move(x,y);
			
			y--;
			try{
			    grid.setConstraints(r, y, x);
		        }
			catch(Exception out){
			    timeline3.pause();
			    over.setVisible(true);
			    ft.play();
			}	        
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    if(Down){
			dot.move(x,y);
			x++;
			if(x == 26){
			    timeline.pause();
			    over.setVisible(true);
			    ft.play();
			}
			grid.setConstraints(r, y, x);
	        
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    //grid.getChildren().add(rect1);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    if(Up){
			dot.move(x,y);
			x--;
		        try{
			    grid.setConstraints(r, y, x);
		        }
			catch(Exception out){
			    timeline3.pause();
			    over.setVisible(true);
			    ft.play();
			}
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    //grid.getChildren().add(rect1);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    
	}));
	timeline3.setCycleCount(Timeline.INDEFINITE);
        timeline3.play();
    }//level3
    
    /**
     * Display gameBoard and Snake object
     * as well as instructions
     * @param stage new stage to display
     */
    public void startGame(Stage stage) {
	//make grid lines
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(20);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(20);
            grid.getRowConstraints().add(row);
	}
	
	//style grid
        grid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
	draw();
	instru();
	
	//Controls
        Button controls=new Button();
	//moves controls off screen
        controls.relocate(-100, 200);
        controls.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.D){
                Right=true;
                Left=false;
                Up=false;
                Down=false;
            }
            if(e.getCode()==KeyCode.A){
                Left=true;
                Right=false;
                Down=false;
                Up=false;
	    }
            if(e.getCode()==KeyCode.W){
                Left=false;
                Right=false;
                Up=true;
                Down=false;
            }
            if(e.getCode()==KeyCode.S){
                Right=false;
                Left=false;
                Down=true;
                Up=false;
            }
	    //sets level 1
	    if(e.getCode()==KeyCode.E){
	    	if(two == false && three == false){
		    level();
		    lv.setText("   Level 1");
		    one = true;
		}//if
	    }
	    //sets level 2
	    if(e.getCode()==KeyCode.Q){
		if(one == false && three == false){
		    level2();
		    lv.setText("   Level 2");
		    two = true;
		}//if
	    }
	    //sets level 3
	    if(e.getCode()==KeyCode.R){
		if(two == false && one == false){
		    level3();
		    lv.setText("   Level 3");
		    three = true;
		}//if
	    }
	    // set new game
	    if(e.getCode()==KeyCode.N){
		restart(stage);
	    }
	    });
		
	//sets rectangle first spot
	grid.setConstraints(r, 5, 2);
	for(int a = 0; a < dot.getRec().size();a++){
	    rect1 = dot.getRectangle(a);
	    grid.getChildren().add(rect1);
	    grid.setConstraints(rect1, dot.getY(a), dot.getX(a));
	}//for
	
	//sets up scene
	grid.getChildren().addAll(r,controls);
	vbox.getChildren().addAll(levelBoard(),grid,scoreBoard(),con,display);	
	Scene scene = new Scene(vbox,600, 700, Color.WHITE);
	//Scene scene = new Scene(vbox,(columns * 40) + 50, (rows * 40) + 50, Color.WHITE);
	stage.setTitle("cs1302-arcade!");
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();	
    } // start

    /**
     * Calls the reset method for the snake game
     */
    public void restart(Stage stage){
	reset();
    }//restart

    /**
     * Resets the snake game points and position
     */
    public void reset(){
	//resets snake
	for(int a = 0; a < dot.getRec().size();a++){
	    rect1 = dot.getRectangle(a);
	    grid.getChildren().remove(rect1);
	}//for
	//clear messages
	over.setVisible(false);
	winner.setVisible(false);
	Rect redo = new Rect();
	dot = redo;
	pt.setText("0");
	lv.setText("");
	x =2;
	y=5;
	grid.setConstraints(r, 5, 2);
	for(int a = 0; a < dot.getRec().size();a++){
	    rect1 = dot.getRectangle(a);
		grid.getChildren().add(rect1);
		grid.setConstraints(rect1, dot.getY(a), dot.getX(a));
	}//for
	//looks for which timeline to stop
	if(one){
	    timeline.stop();
	    one = false;
	}
	if(two){
	    timeline2.stop();
	    two = false;
	}
	if(three){
	    timeline3.stop();
	    three = false;
	}
    }//reset    
} // Snake
