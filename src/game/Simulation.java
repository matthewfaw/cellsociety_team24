package game;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Simulation {
	public static final String TITLE = "Cellular Automata";
	private Scene myScene;
	private ArrayList<Button> myButtonList;
	private ArrayList<ScrollBar> myScrollBarList;

	public String getTitle() {
		return TITLE;
	}

	public Scene init(int width, int height) {
        Group root = new Group();
        myScene = new Scene(root, width, height, Color.WHITE);
        
        SetButtons(root);
        SetScrollBars(root);
        SetLabels(root);
        
        
		return myScene;
	}

	private void SetLabels(Group root) {
		Label ParameterLabel= new Label("Parameter:");
        ParameterLabel.setLayoutX(420);
        ParameterLabel.setLayoutY(500);
        root.getChildren().add(ParameterLabel);
        Label SizeLabel= new Label("Speed:");
        SizeLabel.setLayoutX(420);
        SizeLabel.setLayoutY(600);
        root.getChildren().add(SizeLabel);
        Label SpeedLabel= new Label("Speed:");
        SpeedLabel.setLayoutX(420);
        SpeedLabel.setLayoutY(700);
        root.getChildren().add(SpeedLabel);
		
	}

	private void SetScrollBars(Group root) {
		UIScrollBars PutScrollBars= new UIScrollBars(myScrollBarList);
        myScrollBarList=PutScrollBars.BuildScrollBars();
        Iterator<ScrollBar> ScrollBarIterator= myScrollBarList.iterator();
        while(ScrollBarIterator.hasNext()){
        	ScrollBar currentScrollBar=ScrollBarIterator.next();
        	root.getChildren().add(currentScrollBar);
        }
		
	}

	private void SetButtons(Group root) {
		UIButtons PutButtons= new UIButtons();
        myButtonList=PutButtons.BuildButtons();
        Iterator<Button> ButtonIterator= myButtonList.iterator();
        while(ButtonIterator.hasNext()){
        	Button currentButton=ButtonIterator.next();
        	root.getChildren().add(currentButton);
        }
		
	}

	public Object step(double secondDelay) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
