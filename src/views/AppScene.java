package views;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import controllers.AppController;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import models.grid.Cell;
import resources.AppResources;
import views.styles.CellStyleGuide;
import views.grid.GridViewUpdateSquare;

public class AppScene {

	private CellStyleGuide fCellStyleGuide;
	private Group fAppRoot;
	private int fWidth;
	private int fHeight;
	private ArrayList<Button> buttonList= new ArrayList<Button>();
	private ArrayList<ScrollBar> scrollbarList= new ArrayList<ScrollBar>();
	private AppController fAppController;
	private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	private GridViewUpdateSquare myGrid;
	private ScrollBar fSpeedScrollBar;
	
	public AppScene(int aHeight, int aWidth, AppController aAppController)
	{
		fAppRoot = new Group();
		fHeight = aHeight;
		fWidth = aWidth;
		fAppController = aAppController;

		setBackground();
        setButtons(fWidth);
        setScrollBars(fWidth);
        setLabels(fWidth);
        setGrid(fWidth,fHeight);
	}

	public Group getRoot()
	{
		return fAppRoot;
	}

	private void setBackground()
	{
		Rectangle rectangle = new Rectangle();
		rectangle.setWidth(fWidth);
		rectangle.setHeight(fHeight);
		rectangle.setFill(Color.web(mytext.getString("BACKGROUNDCOLOR")));
		
		fAppRoot.getChildren().add(rectangle);
	}
	
	private void setButtons(int width)
	{
		Button startButton= makeButton("StartCommand",AppResources.OFFSET,AppResources.THIRTEEN_SIXTEENTHS,width,true);
		startButton.setOnAction(e->fAppController.onStartButtonPressed());
		//fAppRoot.getChildren().add(startButton); ////NOT SURE IF THIS IS NEEDED HER OR I CAN PUT IN THE METHOD
		Button pauseButton= makeButton("PauseCommand",AppResources.OFFSET,AppResources.THREE_QUARTERS, width,true);
		pauseButton.setOnAction(e->fAppController.onPauseButtonPressed());
		//fAppRoot.getChildren().add(pauseButton);
		Button resetButton= makeButton("ResetCommand",AppResources.OFFSET,AppResources.FIVE_EIGHTHS, width,true);
		resetButton.setOnAction(e->fAppController.onResetButtonPressed());
		//fAppRoot.getChildren().add(resetButton);
		Button stepButton= makeButton("StepCommand",AppResources.OFFSET,AppResources.ELEVEN_SIXTEENTHS, width,true);
		stepButton.setOnAction(e->fAppController.onStepButtonPressed());
		//fAppRoot.getChildren().add(stepButton);
		Button setSimulationButton= makeButton("SetSimulationCommand",AppResources.OFFSET,AppResources.SEVEN_EIGHTHS, width,false);
		setSimulationButton.setOnAction(e->
		fAppController.onSetSimulationButtonPressed());
		//fAppRoot.getChildren().add(setSimulationButton);
	}
	
	private Button makeButton(String name, double xlayout, double ylayout,int width,Boolean disable) {
		Button b = new Button(mytext.getString(name));
		b.setLayoutX(width*xlayout);
		b.setLayoutY(width*ylayout);
		b.setPrefSize(200, 20);
		if(disable){ 
			b.setDisable(true);
		}
		fAppRoot.getChildren().add(b);
		buttonList.add(b);
		return b;
	}
	public void  Display(){
		Iterator<Button> buttonIter= buttonList.iterator();
		while(buttonIter.hasNext()){
			Button b=buttonIter.next();
			b.setDisable(false);
		}
		Iterator<ScrollBar> scrollbarIter= scrollbarList.iterator();
		while(scrollbarIter.hasNext()){
			ScrollBar s=scrollbarIter.next();
			s.setDisable(false);
		}
	} 

	private void setScrollBars(int width)
	{
		ScrollBar parameterScrollBar=makeScrollBar(AppResources.FIVE_EIGHTHS,AppResources.FIVE_EIGHTHS,width,true);
		parameterScrollBar.setOnDragDone(e->fAppController.onParameterDrag());
		fSpeedScrollBar=makeScrollBar(AppResources.FIVE_EIGHTHS,AppResources.ELEVEN_SIXTEENTHS,width,true);
		fSpeedScrollBar.valueProperty().addListener(e -> fAppController.onSpeedDrag(fSpeedScrollBar.getValue()));
	}
	
	public void setSpeedScrollBarValue(double aValue)
	{
		fSpeedScrollBar.setValue(aValue);
	}
	
	private ScrollBar makeScrollBar(double xlayout, double ylayout,int width,Boolean disable) {
		ScrollBar s=new ScrollBar();
		s.setLayoutX(width*xlayout);
		s.setLayoutY(width*ylayout);
		if(disable){ 
			s.setDisable(true);
		}
		scrollbarList.add(s);
		fAppRoot.getChildren().add(s);
		return s;
	}
	
	private void setLabels(int width)
	{
		Label parameterLabel= makeLabel("ParameterText",AppResources.HALF,AppResources.FIVE_EIGHTHS,width);
		fAppRoot.getChildren().add(parameterLabel);
		Label speedLabel= makeLabel("SpeedText",AppResources.HALF,AppResources.ELEVEN_SIXTEENTHS,width);
		fAppRoot.getChildren().add(speedLabel);
	}
	
	private Label makeLabel(String name,double xlayout,double ylayout,int width){
		Label l=new Label(mytext.getString(name));
		l.setLayoutX(width*xlayout);
		l.setLayoutY(width*ylayout);
		return l;
	}
	
	private void setGrid(int width, int height){
		Rectangle basicGrid=new Rectangle(width*AppResources.ONE_SIXTEENTH,height*AppResources.ONE_SIXTEENTH,
				width*AppResources.HALF,height*AppResources.HALF);
		basicGrid.setFill(Color.GRAY);
		fAppRoot.getChildren().add(basicGrid);
		
	}
	
	public void intializeGrid(Collection<Cell> cells,CellStyleGuide csg, Dimension dimensions){
		myGrid= new GridViewUpdateSquare(fWidth,fHeight,dimensions,fAppRoot,csg,cells);
		myGrid.makeGrid(fAppRoot,fWidth,fHeight,cells,csg,dimensions);
	}
	
	public void updateGrid(Collection<Cell> cells)
	{
		myGrid.StepGrid(cells);
	}

}
