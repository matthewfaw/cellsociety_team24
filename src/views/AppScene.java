package views;

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
import resources.AppResources;
import views.styles.CellStyleGuide;

public class AppScene {

	private CellStyleGuide fCellStyleGuide;
	private Group fAppRoot;
	private int fWidth;
	private int fHeight;
	
	private AppController fAppController;
	private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	
	public AppScene(int aHeight, int aWidth, AppController aAppController)
	{
		fAppRoot = new Group();
		fHeight = aHeight;
		fWidth = aWidth;
		fAppController = aAppController;
		// Add scene setup here
		//...

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
		//XXX: change this  so it's not hard coded... perhaps resource  file
		rectangle.setFill(Color.WHITE);
		
		fAppRoot.getChildren().add(rectangle);
	}
	
	private void setButtons(int width)
	{
		Button startButton= makeButton("StartCommand",AppResources.OFFSET,AppResources.THIRTEEN_SIXTEENTHS,width);
		startButton.setOnAction(e->fAppController.onStartButtonPressed());
		fAppRoot.getChildren().add(startButton);
		Button pauseButton= makeButton("PauseCommand",AppResources.OFFSET,AppResources.THREE_QUARTERS, width);
		pauseButton.setOnAction(e->fAppController.onPauseButtonPressed());
		fAppRoot.getChildren().add(pauseButton);
		Button resetButton= makeButton("ResetCommand",AppResources.OFFSET,AppResources.FIVE_EIGHTHS, width);
		resetButton.setOnAction(e->fAppController.onResetButtonPressed());
		fAppRoot.getChildren().add(resetButton);
		Button stepButton= makeButton("StepCommand",AppResources.OFFSET,AppResources.ELEVEN_SIXTEENTHS, width);
		stepButton.setOnAction(e->fAppController.onStepButtonPressed());
		fAppRoot.getChildren().add(stepButton);
		Button setSimulationButton= makeButton("SetSimulationCommand",AppResources.OFFSET,AppResources.SEVEN_EIGHTHS, width);
		setSimulationButton.setOnAction(e->
		fAppController.onSetSimulationButtonPressed());
		fAppRoot.getChildren().add(setSimulationButton);
	}
	
	private Button makeButton(String name, double xlayout, double ylayout,int width) {
		Button b = new Button(mytext.getString(name));
		b.setLayoutX(width*xlayout);
		b.setLayoutY(width*ylayout);
		b.setPrefSize(200, 20);
		return b;
	}

	private void setScrollBars(int width)
	{
		ScrollBar parameterScrollBar=makeScrollBar(AppResources.FIVE_EIGHTHS,AppResources.FIVE_EIGHTHS,width);
		//setonAction??
		fAppRoot.getChildren().add(parameterScrollBar);
		ScrollBar speedScrollBar=makeScrollBar(AppResources.FIVE_EIGHTHS,AppResources.ELEVEN_SIXTEENTHS,width);
		//setonAction??
		fAppRoot.getChildren().add(speedScrollBar);
	}
	
	private ScrollBar makeScrollBar(double xlayout, double ylayout,int width) {
		ScrollBar s=new ScrollBar();
		s.setLayoutX(width*xlayout);
		s.setLayoutY(width*ylayout);
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
	/**
	 * This method is intended to notify the Scene of 
	 * information about the cells in the grid that comes
	 * from the XML
	 * This method should be used during grid setup
	 * @param aStyleGuide
	 */
	public void setCellStyleGuide(CellStyleGuide aStyleGuide)
	{
	}

}
