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





public class Snake{

    //variables
    VBox vbox =new VBox();
    GridPane grid = new GridPane();
    int columns = 26;
    int rows = 26;
    //boolean going = false;
    Timeline timeline;
    Timeline timeline2;
    Timeline timeline3;
    boolean Right=true;
    boolean Left=false;
    boolean Up=false;
    boolean Down=false;
    int x = 2;
    int y = 5;
    int points= 0;
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
    
    public void draw(){
	r.setWidth(10);
	r.setHeight(10);
	r.setArcWidth(20);
	r.setArcHeight(20);

	fruit.setFill(Color.RED);
	fruit.setWidth(20);
	fruit.setHeight(20);
	grid.getChildren().add(fruit);
	grid.setConstraints(fruit, 15, 6);
    }//draw
   
    public void instru(){
	con = new Label("USE W-Up,A-Left,S-Down,D-Right KEYS TO CONTROL SNAKE MOVEMENTS");
	con.setFont(new Font("Arial", 17));
	over = new Label("GAME OVER");
	over.setFont(new Font("Arial", 30));
	over.setTextFill(Color.RED);
	over.setVisible(false);
	winner = new Label("WINNER!!!");
	winner.setFont(new Font("Arial", 30));
	ft = new FadeTransition(Duration.millis(3000), over);
	ft.setFromValue(1.0);
	ft.setToValue(0.3);
	ft.setCycleCount(4);
	ft.setAutoReverse(true);
 
    }//instru

    public HBox scoreBoard(){
	HBox scoreB = new HBox();
	Label score = new Label("Score : ");
	lv = new Label();
	pt = new Label(Integer.toString(points));
	scoreB.getChildren().addAll(score,pt,lv);
	return scoreB;
    }//scoreBoard

    public HBox levelBoard(){
	title = new Label("        SNAKE");
	title.setFont(new Font("Arial", 23));
	Label choose = new Label("E-Easy, Q-Med, R-Hard, N-New Game");
	choose.setFont(new Font("Arial", 11));
	HBox levelB = new HBox();
	levelB.getChildren().addAll(choose,title);
	return levelB;
    }//scoreBoard

    public void yourScore(){
	pt.setText(Integer.toString(dot.getRec().size()+1));
    }//yourScore
    

    public boolean overlaps(Shape head, Shape food){
	if(head.getBoundsInParent().intersects(food.getBoundsInParent())){
	    return true;
	    }
	return false;
    }

    public boolean collide(){
	for(int z = 0; z < dot.getRec().size();z++){
	    if( overlaps(r, dot.getRectangle(z))==true){
		return true;
	    }
	}//for
	return false;
    }//collide

    public void appear(){
	int randNum = (int)((Math.random()*25));
	int randNum2 = (int)((Math.random()*25));
	grid.setConstraints(fruit, randNum2, randNum);
    }//appear
    
    public void level(){
	timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
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
			    }//if
			});
		    
		    if(Right){
			//get positon before updating
			dot.move(x,y);
			
			y++;
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
			});
		    if(Right){
			//get positon before updating
			dot.move(x,y);
			
			y++;
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
			});
		    if(Right){
			//get positon before updating
			dot.move(x,y);
			
			y++;
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
    
    //@Override
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
	    if(e.getCode()==KeyCode.E){
	    	if(two == false && three == false){
		    level();
		    lv.setText("   Level 1");
		    one = true;
		}//if
	    }
	    if(e.getCode()==KeyCode.Q){
		if(one == false && three == false){
		    level2();
		    lv.setText("   Level 2");
		    two = true;
		}//if
	    }
	    if(e.getCode()==KeyCode.R){
		if(two == false && one == false){
		    level3();
		    lv.setText("   Level 3");
		    three = true;
		}//if
	    }
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



	grid.getChildren().addAll(r,controls);
	vbox.getChildren().addAll(levelBoard(),grid,scoreBoard(),con,over);	
	Scene scene = new Scene(vbox,600, 700, Color.WHITE);
	//Scene scene = new Scene(vbox,(columns * 40) + 50, (rows * 40) + 50, Color.WHITE);
	stage.setTitle("cs1302-arcade!");
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();

	
    } // start

    public void restart(Stage stage){
	reset();
    }//restart
    
    public void reset(){
	for(int a = 0; a < dot.getRec().size();a++){
	    rect1 = dot.getRectangle(a);
	    grid.getChildren().remove(rect1);
	}//for
	over.setVisible(false);
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
