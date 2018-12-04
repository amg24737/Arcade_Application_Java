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



public class Snake{

    //variables
    VBox vbox =new VBox();
    GridPane grid = new GridPane();
    int columns = 40;
    int rows = 30;
    //boolean going = true;
    
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

    public HBox scoreBoard(){
	HBox scoreB = new HBox();
	Label score = new Label("Score : ");
	Label pt = new Label(Integer.toString(points));
	scoreB.getChildren().addAll(score,pt);
	return scoreB;
    }//scoreBoard

    public boolean overlaps(Shape head, Shape food){
	if(head.getBoundsInParent().intersects(food.getBoundsInParent())){
	    return true;
	    }
	return false;
    }

    public void appear(){
	int randNum = (int)((Math.random()*29));
	int randNum2 = (int)((Math.random()*39));
	grid.setConstraints(fruit, randNum2, randNum);
    }//appear
    
    @Override
    public void startGame(Stage stage) {
	//Group root = new Group();

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
	
	//Controls
        Button controls=new Button();
        controls.relocate(-100, 200);
        controls.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.RIGHT){
                Right=true;
                Left=false;
                Up=false;
                Down=false;
            }
            if(e.getCode()==KeyCode.LEFT){
                Left=true;
                Right=false;
                Down=false;
                Up=false;
	    }
            if(e.getCode()==KeyCode.UP){
                Left=false;
                Right=false;
                Up=true;
                Down=false;
            }
            if(e.getCode()==KeyCode.DOWN){
                Right=false;
                Left=false;
                Down=true;
                Up=false;
            }
        });
	/**	
    //Animation
        AnimationTimer animate=new AnimationTimer(){
            public void handle(long arg0) {
		
		if(Right){

		    
                }
                if(Left){
                   
                }
                if(Up){
                   
                }
                if(Down){
                    
                }
	       
            }//handle
	    };//animate
        animate.start();

	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
		    if(going){
			x++;
		    }
     
	}));

	//	EventHandler<ActionEvent> handler = event ->
	    
	   
	//});
	
	//KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),new KeyValue(x, 20));
	Timeline timeline = new Timeline();
	*/
	//sets rectangle first stop
	grid.setConstraints(r, 5, 2);
	for(int a = 0; a < dot.getRec().size();a++){
	    rect1 = dot.getRectangle(a);
	    grid.getChildren().add(rect1);
	    grid.setConstraints(rect1, dot.getY(a), dot.getX(a));
	}//for


	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
		    Platform.runLater(() -> {
			    /* interact with scene graph */
			    if(overlaps(r,fruit)){
				appear();
			    }
			});
		    if(Right){
			//get positon before updating
			dot.move(x,y);
			
			y++;
			grid.setConstraints(r, y, x);
			//if(overlaps(r,fruit)){
			//appear();
			//}
			//grid.getChildren().add(rect2 = dot.moreDots());
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    //grid.getChildren().add(rect1);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
			
		    }//if
		    if(Left){
			dot.move(x,y);
			
			y--;
			grid.setConstraints(r, y, x);
			//if(overlaps(r,fruit)){
			//appear();
			    //  }
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    //grid.getChildren().add(rect1);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    if(Down){
			dot.move(x,y);
			x++;
			grid.setConstraints(r, y, x);
			//if(overlaps(r,fruit)){
			//  appear();
			//  }
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
			grid.setConstraints(r, y, x);
			//if(overlaps(r,fruit)){
			    //appear();
			    //  }
			//loop through rect array and set constraints with each one
			for(int b = 0; b < dot.getRec().size();b++){
			    rect1 = dot.getRectangle(b);
			    //grid.getChildren().add(rect1);
			    grid.setConstraints(rect1, dot.getY(b), dot.getX(b));
			}//for
		    }//if
		    
	}));	
	timeline.setCycleCount(Timeline.INDEFINITE);
	//timeline.getKeyFrames().add(keyFrame);	
	timeline.play();
	
	
	grid.getChildren().addAll(r,controls);
	vbox.getChildren().addAll(grid,scoreBoard());	
        //Scene scene = new Scene(root, 900, 720,Color.BLACK);
	Scene scene = new Scene(vbox,(columns * 40) + 100, (rows * 40) + 100, Color.WHITE);
	stage.setTitle("cs1302-arcade!");
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
    } // startGame
    
} // Snake

