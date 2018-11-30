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
import javafx.scene.layout.BorderPane;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler; 
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.animation.KeyValue;






public class Snake{

    //variables
    //Random rng = new Random();
    //Graphics g2d = new Graphics();

    final Canvas canvas = new Canvas(500,500);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    double x=0;
    double y = 0;
    boolean going = true;
    
    @Override
    public void start(Stage stage) {
	Group root = new Group();

	//GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.setFill(Color.BLACK);
	gc.fillRect(250,250,250,250);


	/**
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
	*/
    //Animation
        AnimationTimer animate=new AnimationTimer(){
            public void handle(long arg0) {
                GraphicsContext c = canvas.getGraphicsContext2D();    
		c.setFill(Color.FORESTGREEN);             
		c.fillOval(x,y,20,20);
		//animate.setDelay(Duration.seconds(1));
		c.setFill(Color.BLACK);             
		c.fillOval(x-.5,y,20,20);
		//x++;
		/**
		if(Right){

		    
                }
                if(Left){
                   
                }
                if(Up){
                   
                }
                if(Down){
                    
                }
		*/
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
	/**
	KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),new KeyValue(x, 20));
	Timeline timeline = new Timeline();
	*/	
timeline.setCycleCount(Timeline.INDEFINITE);
//timeline.getKeyFrames().add(keyFrame);
	
timeline.play();
        
	root.getChildren().add(canvas);
        Scene scene = new Scene(root, 900, 720,Color.BLACK);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();
    } // start


  public void gu(){
		    for(int a = 0;a<25;a++){
			x++;
		    }
	    }//do

} //Snake
