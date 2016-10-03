package views;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import controllers.AppController;
import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import models.grid.Cell;
import resources.AppResources;
import views.styles.CellStyleGuide;
import views.grid.*;

/**
 * A class used to set up and manage the UI
 * @author Guhan Muruganandam
 *
 */
public class AppScene {

	private CellStyleGuide fCellStyleGuide;
	private Group fAppRoot;
	private int fWidth;
	private int fHeight;
	private ArrayList<Button> buttonList= new ArrayList<Button>();
	private ArrayList<ScrollBar> scrollbarList= new ArrayList<ScrollBar>();
	private AppController fAppController;
	private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	private GridViewUpdate myGrid;
	private ScrollBar fSpeedScrollBar;
	private Rectangle basicGrid;
	private final NumberAxis xAxis=new NumberAxis();
	private final NumberAxis yAxis=new NumberAxis();
	private LineChart<Number,Number>  myDataChart= new LineChart<Number,Number>(xAxis, yAxis); 
	private XYChart.Series<Number,Number> mySeriesone= new XYChart.Series<Number,Number>();
	private XYChart.Series<Number,Number> mySeriestwo= new XYChart.Series<Number,Number>();
	
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
		Button pauseButton= makeButton("PauseCommand",AppResources.OFFSET,AppResources.THREE_QUARTERS, width,true);
		pauseButton.setOnAction(e->fAppController.onPauseButtonPressed());
		Button resetButton= makeButton("ResetCommand",AppResources.OFFSET,AppResources.FIVE_EIGHTHS, width,true);
		resetButton.setOnAction(e->fAppController.onResetButtonPressed());
		Button stepButton= makeButton("StepCommand",AppResources.OFFSET,AppResources.ELEVEN_SIXTEENTHS, width,true);
		stepButton.setOnAction(e->fAppController.onStepButtonPressed());
		Button setSimulationButton= makeButton("SetSimulationCommand",AppResources.OFFSET,AppResources.SEVEN_EIGHTHS, width,false);
		setSimulationButton.setOnAction(e->
		fAppController.onSetSimulationButtonPressed());
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
		basicGrid=new Rectangle(width*AppResources.ONE_SIXTEENTH,height*AppResources.ONE_SIXTEENTH,
				width*AppResources.HALF,height*AppResources.HALF);
		basicGrid.setFill(Color.GRAY);
		fAppRoot.getChildren().add(basicGrid);
		
	}
	
	//XXX: Change the grid construction to a factory
	public void intializeGrid(Collection<Cell> cells,CellStyleGuide csg, Dimension dimensions, GridType aGridType){
		//myGrid=GridViewUpdateFactory.BuildGridView(gridtype);
		//myGrid= new GridViewUpdateSquare(fWidth,fHeight,dimensions,fAppRoot,csg,cells);
		//myGrid= new GridViewUpdateTriangles(fWidth,fHeight,dimensions,fAppRoot,csg,cells);
		GridViewUpdate newGrid;
		switch (aGridType) {
		case Square:
			newGrid= new GridViewUpdateSquare(fWidth,fHeight,dimensions,fAppRoot,csg,cells);
			break;
		case Hex:
			newGrid= new GridViewUpdateHexagon(fWidth,fHeight,dimensions,fAppRoot,csg,cells);
			break;
		case Triangle:
			newGrid= new GridViewUpdateTriangles(fWidth,fHeight,dimensions,fAppRoot,csg,cells);
			break;
		default:
			newGrid = null;
//			throw new GridNotFoundException()
		}
		fAppRoot.getChildren().remove(basicGrid);
//		myGrid.clearGrid();

		myGrid = newGrid;
		myGrid.makeGrid();
	}
	
	public void clearGrid()
	{
		if (myGrid != null) {
			myGrid.clearGrid();
		}
	}
	
	public void updateGrid(Collection<Cell> cells)
	{
		myGrid.stepGrid(cells);
	}
	//public void mouseClickGrid(){
	//	fAppScene.setOnMouseClicked(e -> GridViewUpdateSquare.handleMouseClick(e.getX(), e.getY()));
	//}

	public void buildCellListeners(Collection<Cell> cells) {
		Iterator<Shape> shapeIterator= myGrid.getShapeIterator();
		for(Cell c:cells){
			Shape s= shapeIterator.next();
			s.setOnMouseClicked(e->fAppController.updateCellState(c));
		}
	}

	public void changeCellColor(Cell c) {
		fAppRoot.getChildren().remove(myGrid.getShape(c));
		myGrid.colorCell(c);
		fAppRoot.getChildren().add(myGrid.getShape(c));
	}
	
	public void updateGraphData(int stepnumber,double datapointone, double datapointtwo){
		if(fAppRoot.getChildren().contains(myDataChart)){
			fAppRoot.getChildren().remove(myDataChart);
		}
		mySeriesone.getData().add(new XYChart.Data<Number,Number>(stepnumber,datapointone));
		mySeriestwo.getData().add(new XYChart.Data<Number,Number>(stepnumber,datapointtwo));
	}
	
	public void BuildGraph(){
		myDataChart.getData().addAll(mySeriesone,mySeriestwo);
		myDataChart.setLayoutX(fWidth*AppResources.FIVE_EIGHTHS);
		myDataChart.setLayoutY(fHeight*AppResources.QUARTER);
		fAppRoot.getChildren().add(myDataChart);
		
	}
}
