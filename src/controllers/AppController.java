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

public class AppController {
	private AppScene fAppScene;
	private Stage fStage;
	private GridModel fGridModel;
	private SimulationController fSimulationController;
	private String fCurrentXmlDirectoryPath; 
	private String fCurrentFileName;

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
	
	public void onStartButtonPressed() {
		fSimulationController.start();
		
	}
	public void onPauseButtonPressed() {
		fSimulationController.pause();
		//fAppScene.BuildGraph();
	}
	public void onResetButtonPressed() {
		fSimulationController.pause();
		initializeGrid(fCurrentXmlDirectoryPath,fCurrentFileName);
	}
	public void onStepButtonPressed() {
		step(1);
	}
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
			initializeGrid(fCurrentXmlDirectoryPath , fCurrentFileName); // a private method inside AppController
		}
	}
	private void initializeGrid(String aDirectoryPath, String aFileName)
	{
		// pull data from XML
		SimulationFileSettingsFactory simulationFileSettingsFactory = new SimulationFileSettingsFactory(aDirectoryPath, aFileName);
		SimulationFileSettings filePaths = simulationFileSettingsFactory.createFileSettings();

		SimulationSettingsFactory simulationSettingsFactory = new SimulationSettingsFactory(filePaths.getSimulationFile());
		SimulationSettings simulationSettings = simulationSettingsFactory.createSimulationSettings();

		GridSettingsFactory gridSettingsFactory = new GridSettingsFactory(filePaths.getGridFile());
		GridSettings gridSettings = gridSettingsFactory.createGridSettings();

		CellDataFactory cellDataFactory = new CellDataFactory(filePaths.getStateFile());
		CellSettings cellSettings = cellDataFactory.createCellSettings();
		CellStyleGuide cellStyleGuide = cellDataFactory.createStyleGuide(gridSettings.getRuleType());
		
		// build the grid model
		GridFactory gridFactory = new GridFactory(gridSettings, cellSettings);
		fGridModel = gridFactory.createGridModel();
		
		// add the grid to the display
		fAppScene.intializeGrid(fGridModel.getAllCells(), cellStyleGuide, gridSettings.getDimensions(), gridSettings.getGridType());
		fAppScene.setSpeedScrollBarValue(simulationSettings.getSimulationSpeed());
		fSimulationController.changeSpeed(simulationSettings.getSimulationSpeed());
	}
	
	public void onParameterDrag() {
		
	}
	public void onSpeedDrag(double aScrollbarValue) {
		fSimulationController.changeSpeed(aScrollbarValue);
	}

	public void updateCellState(Cell aCell) {
		System.out.println("DERP");
		// formula to change stateid
		fGridModel.randomlyChangeCellState(aCell);
		fAppScene.updateCell(aCell);
	}
	
}