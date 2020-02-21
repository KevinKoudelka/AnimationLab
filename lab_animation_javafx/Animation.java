//T Harvey
// Loosely based on https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/Example3.java 

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.io.File;

// Animation of Orc walking
public class Animation extends Application {
    final int canvasCount = 10;
    int picInd = 0;
    double xloc = 0;
    double yloc = 0;
    final double xIncr = 8;
    final double yIncr = 2;
    final static int canvasWidth = 500;
    final static int canvasHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
	 boolean hflag = true;
	 boolean vflag = true;

    // TODO: Change the code so that at least eight orc animation pngs are loaded
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Orc");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image orc_img = createImage("/orc_animation/orc_forward_southeast.png");
        Image orc_northwest = createImage("/orc_animation/orc_forward_northwest.png");
        Image orc_northeast = createImage("/orc_animation/orc_forward_northeast.png");
        Image orc_southwest = createImage("/orc_animation/orc_forward_southwest.png");
        Image orc_north = createImage("/orc_animation/orc_forward_north.png");
        Image orc_south = createImage("/orc_animation/orc_forward_south.png");
        Image orc_west = createImage("/orc_animation/orc_forward_west.png");
        Image orc_east = createImage("/orc_animation/orc_forward_east.png");
        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1e9; 
                
                direction();
                if (hflag == true) {
                	xloc += xIncr;
                }
                else{
                	xloc -= xIncr;
                }
                if (vflag == true) {
                	yloc += yIncr;
                }
                else{
                	yloc -= yIncr;
                }
                
                // Clear the canvas
                gc.clearRect(0, 0, canvasWidth, canvasHeight);

                // draw the subimage from the original png to animate the orc's motion
                if (hflag ==true && vflag == true) {
                gc.drawImage(orc_img, imgWidth*picInd, 0, imgWidth, imgHeight,
                                    xloc, yloc, imgWidth, imgHeight);
                }
                else if (hflag == false && vflag == true) {
                gc.drawImage(orc_southwest, imgWidth*picInd, 0, imgWidth, imgHeight,
                        xloc, yloc, imgWidth, imgHeight);
                }
                else if (hflag == false && vflag == false) {
                gc.drawImage(orc_northwest, imgWidth*picInd, 0, imgWidth, imgHeight,
                       xloc, yloc, imgWidth, imgHeight);
                }
                else if (hflag == true && vflag == false) {
                    gc.drawImage(orc_northeast, imgWidth*picInd, 0, imgWidth, imgHeight,
                           xloc, yloc, imgWidth, imgHeight);
                }
                picInd = (picInd + 1) % canvasCount;
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                // TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
                //Be sure that animation picture direction matches what is happening on screen.
            }
        }.start();
        theStage.show();
    }

    //Read image from file and return
    private Image createImage(String x) {
        Image img = new Image(x);
        return img;   	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
 public void direction() {
	 
	 if (xloc >= 400) {
		 hflag = false;
	 }
	 else if (xloc <= -50) {
		 hflag = true;
	 }
	 else if (yloc <= -50) {
		 vflag = true;
	 }
	 else if (yloc >= 200) {
		 vflag = false;
	 }
 }
}


