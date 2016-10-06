package controllers;

import java.io.File;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.grid.Cell;
import models.grid.CellState;
import models.grid.GridFactory;
import models.grid.GridModel;
import models.settings.CellSettings;
import models.settings.GridSettings;
import models.settings.SimulationFileSettings;
import models.settings.SimulationSettings;
import resources.AppResources;
import views.AppScene;
import views.styles.CellStyleGuide;
import xml.CellDataFactory;
import xml.model_factories.GridSettingsFactory;
import xml.model_factories.SimulationFileSettingsFactory;
import xml.model_factories.SimulationSettingsFactory;

/**
 * The purpose of this class is to serve as the main controller of the application.
 * This class is essentially a mediator. It manages all of the events which occur in the
 * app, and passes data along to the appropriate objects.
 * This class manages the models, views, and their interactions. It dispatches the XML parsing,
 * and passes the results to the correct places.
 * This class may fail if an invalid XML file is selected.
 * @author matthewfaw
 *
 */
public class AppController {
	private AppScene fAppScene;
	private Stage fStage;
	private GridModel fGridModel;
	private SimulationController fSimulationController;
	private String fCurrentXmlDirectoryPath; 
	private String fCurrentFileName;
	
	private GridSettings fGridSettings;

	/**
	 * This method is the gateway to starting the app.  It should be called once when the app is launched
	 * @param aStage: a stage to display
	 */
	public void init(Stage aStage)
	{
		fAppScene = new AppScene(AppResources.SIZE, AppResources.SIZE, this);
		fStage = aStage;
		
		fStage.setTitle("CELL SOCIETY");
    	fStage.setScene(new Scene(fAppScene.getRoot()));
    	fStage.show();
    	
    	KeyFrame frame = new KeyFrame(Duration.millis(AppResources.MILLISECOND_DELAY),
    								e -> step(AppResources.SECOND_DELAY));
    	Timeline simulationTimeline = new Timeline();
    	simulationTimeline.setCycleCount(Timeline.INDEFINITE);
    	simulationTimeline.getKeyFrames().add(frame);
    	
    	fSimulationController = new SimulationController(simulationTimeline);
	}
	/**
	 * A method to be called at each time step of the timeline. 
	 * The purpose is to update the grid model and to update the display accordingly.
	 * 
	 * This method will fail if the timeline is started before the grid model or app
	 * scene's grid are created
	 * @param aElapsedTime
	 */
	public void step(double aElapsedTime)
	{
		if (aElapsedTime > 0) {
			fGridModel.nextTick();
			fAppScene.updateGrid(fGridModel.getAllCells());
			//fAppScene.updateGraphData(0,1,1);
			//fAppScene.updateGraphData(1,4,2);
			//fAppScene.updateGraphData(2,5,3);
		}
	}
	
	/**
	 * This method is called when the start button is pressed.
	 * It tells the simulation controller to "start".
	 */
	public void onStartButtonPressed() {
		fSimulationController.start();
		
	}
	/**
	 * This method is called when the pause button is pressed.
	 * It tells the simulation controller to "pause".
	 */
	public void onPauseButtonPressed() {
		fSimulationController.pause();
		//fAppScene.BuildGraph();
	}
	/**
	 * This method is called when the reset button is pressed.
	 * It resets the grid to its initial state
	 */
	public void onResetButtonPressed() {
		fSimulationController.pause();
		initializeGrid(fCurrentXmlDirectoryPath,fCurrentFileName);
	}
	/**
	 * Steps the simulation ahead one time step
	 */
	public void onStepButtonPressed() {
		step(1);
	}
	/**
	 * manages the model and view setup necessary to initialize a simulation.
	 * this method will fail if an invalid file is selected
	 */
	public void onSetSimulationButtonPressed() {
		fSimulationController.pause();
		FileChooser filexmlChooser=new FileChooser();
		//filexmlChooser.setTitle(value);
		File file =filexmlChooser.showOpenDialog(fStage);
		if (file!=null){
			fAppScene.Display();
			fAppScene.clearGrid();
			fCurrentXmlDirectoryPath = file.getParent() + "/";
			fCurrentFileName = file.getName();
			initializeGrid(fCurrentXmlDirectoryPath , fCurrentFileName);
		}
	}
	/**
	 * The purpose of this method is to dispatch all of the XML parsing and object creation
	 * necessary to create the backend and frontend infrastructure necessary to run a simualtion.
	 * 
	 * This method will fail if the directory path and file name are invalid
	 * @param aDirectoryPath: path to XML folder
	 * @param aFileName: main simulation XML file
	 */
	private void initializeGrid(String aDirectoryPath, String aFileName)
	{
		// pull data from XML
		SimulationFileSettingsFactory simulationFileSettingsFactory = new SimulationFileSettingsFactory(aDirectoryPath, aFileName);
		SimulationFileSettings filePaths = simulationFileSettingsFactory.createFileSettings();

		SimulationSettingsFactory simulationSettingsFactory = new SimulationSettingsFactory(filePaths.getSimulationFile());
		SimulationSettings simulationSettings = simulationSettingsFactory.createSimulationSettings();

		GridSettingsFactory gridSettingsFactory = new GridSettingsFactory(filePaths.getGridFile());
		fGridSettings = gridSettingsFactory.createGridSettings();

		CellDataFactory cellDataFactory = new CellDataFactory(filePaths.getStateFile());
		CellSettings cellSettings = cellDataFactory.createCellSettings();
		CellStyleGuide cellStyleGuide = cellDataFactory.createStyleGuide(fGridSettings.getRuleType());
		
		// build the grid model
		GridFactory gridFactory = new GridFactory(fGridSettings, cellSettings);
		fGridModel = gridFactory.createGridModel();
		
		// add the grid to the display
		fAppScene.intializeGrid(fGridModel.getAllCells(), cellStyleGuide, fGridSettings.getDimensions(), fGridSettings.getGridType());
		fAppScene.setSpeedScrollBarValue(simulationSettings.getSimulationSpeed());
		fAppScene.setParameterScrollBarValue(fGridModel.getParameter());
		fSimulationController.changeSpeed(simulationSettings.getSimulationSpeed());
	}
	
	/**
	 * A method that is called whenever the parameter scroll bar is moved.
	 * This method updates a parameter in the simulation
	 * @param aScrollbarValue
	 */
	public void onParameterDrag(double aScrollbarValue)
	{
		fGridModel.updateParameter(aScrollbarValue);
	}
	/**
	 * A method called whenever the speed scroll bar is moved.
	 * This method changes the simulation speed to aScrollbarValue %
	 * @param aScrollbarValue: The new simulation speed (in percent)
	 */
	public void onSpeedDrag(double aScrollbarValue) 
	{
		fSimulationController.changeSpeed(aScrollbarValue/100);
	}

	/**
	 * A method called whenever a cell is selected on the screen. The purpose
	 * of this method is to change the cell state of the selected state.
	 * 
	 * We did not wire up this method to be successfully called when a cell was selected,
	 * so this method is never called
	 * @param aCell
	 */
	public void updateCellState(Cell aCell) {
		System.out.println("DERP");
		// formula to change stateid
		fGridModel.randomlyChangeCellState(aCell);
		fAppScene.updateCell(aCell);
	}
	
}