package cellsociety_team24;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
//import javafx.scene.control.ComboBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Simulation {
	public static final String TITLE = "Cellular Automata";
	private Scene mySimScene;
	private ArrayList<Button> myButtonList;
	private ArrayList<ScrollBar> myScrollBarList;
	private ResourceBundle mytext=ResourceBundle.getBundle("game.Resources/textfiles");
	private Group myRoot = new Group();
	private GridViewUpdate myGrid;
	public static final double HALF=0.5;
	public static final double QUARTER=0.25;
	

	public String getTitle() {
		return TITLE;
	}

	public Scene init(int width, int height,Timeline animation, Stage s) {
        int myWidth=width;
        int myHeight=height;
        int myScale=width;
        mySimScene = new Scene(myRoot, myWidth, myHeight, Color.WHITE);
        
        setButtons(myRoot, myScale,animation,s);
        setScrollBars(myRoot, myScale);
        setLabels(myRoot,myScale);
        setGrid(myRoot,myWidth,myHeight);
       
		return mySimScene;
	}
	private void setGrid(Group root,int width,int height) {
		myGrid=new GridViewUpdateSquare(width,height,size,myRoot);
		myGrid.makeGrid(myRoot,width,height,size);
		//GridController.myGrid
		//root.getChildren().add();
		//Rectangle tempGrid=new Rectangle(width/16, width/16, width/2, width/2);
		//tempGrid.setFill(Color.GRAY);
		//root.getChildren().add(tempGrid);
	}

	private void setLabels(Group root,int scale) {
		Label parameterLabel= new Label(mytext.getString("ParameterText"));
        parameterLabel.setLayoutX(scale*HALF);
        parameterLabel.setLayoutY(scale*0.625);
        root.getChildren().add(parameterLabel);
        Label speedLabel= new Label(mytext.getString("SpeedText"));
        speedLabel.setLayoutX(scale*HALF);
        speedLabel.setLayoutY(scale*0.875);
        root.getChildren().add(speedLabel);
		
	}

	private void setScrollBars(Group root, int scale) {
		UIScrollBars PutScrollBars= new UIScrollBars(myScrollBarList);
        myScrollBarList=PutScrollBars.buildScrollBars(scale);
        Iterator<ScrollBar> ScrollBarIterator= myScrollBarList.iterator();
        while(ScrollBarIterator.hasNext()){
        	ScrollBar currentScrollBar=ScrollBarIterator.next();
        	root.getChildren().add(currentScrollBar);
        }
		
	}

	private void setButtons(Group root, int scale,Timeline animation,Stage s) {
		UIButtons PutButtons= new UIButtons();
		myButtonList=PutButtons.buildButtons(scale,animation,s);
		Button stepButton=new Button();
		stepButton=PutButtons.makeButton("StepCommand", e->step(SplashScreen.SECOND_DELAY,scale,scale));
		myButtonList.add(stepButton);
		Iterator<Button> ButtonIterator= myButtonList.iterator();
        while(ButtonIterator.hasNext()){
        	Button currentButton=ButtonIterator.next();
        	root.getChildren().add(currentButton);
        }
		
	}

	public void step(double secondDelay, int width, int height) {
		myGrid.StepGrid(myRoot);
	}
	
}