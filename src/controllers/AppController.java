package controllers;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
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
	private SimulationController fSimulationController;

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
		
	}
	
	public void onPlayButtonPressed()
	{
		// display all buttons as active
		//ie fAppScene.displayButtonsAsActive();
		
		// play the simulation
		// fSimulationController.play();
	}
	public void onStartButtonPressed() {
		///
	}
	public void onPauseButtonPressed() {
		
	}
	public void onResetButtonPressed() {
	
	}
	public void onStepButtonPressed() {
	
	}
	public void onSetSimulationButtonPressed() {
		FileChooser filexmlChooser=new FileChooser();
		//filexmlChooser.setTitle(value);
		File file =filexmlChooser.showOpenDialog(fStage);
		if (file!=null){
			fAppScene.Display();
			initializeGrid(file.getPath()); // a private method inside AppController
		}
	}
	private void initializeGrid(String filePath)
	{
		// pull data from XML
		SimulationFileSettingsFactory simulationFileSettingsFactory = new SimulationFileSettingsFactory(filePath);
		SimulationFileSettings filePaths = simulationFileSettingsFactory.createFileSettings();

		CellDataFactory cellDataFactory = new CellDataFactory(filePaths.getStateFile());
		CellSettings cellSettings = cellDataFactory.createCellSettings();
		CellStyleGuide cellStyleGuid = cellDataFactory.createStyleGuide();
		
		GridSettingsFactory gridSettingsFactory = new GridSettingsFactory(filePaths.getGridFile());
		GridSettings gridSettings = gridSettingsFactory.createGridSettings();

		SimulationSettingsFactory simulationSettingsFactory = new SimulationSettingsFactory(filePaths.getSimulationFile());
		SimulationSettings simulationSettings = simulationSettingsFactory.createSimulationSettings();

		// build the grid model
		GridModel gridModel = new GridModel(gridSettings, cellSettings);
		// add the grid to the display
//		fAppScene.initializeGrid(gridModel.get)
	}
	public void onParameterDrag() {
		
	}
	public void onSpeedDrag() {

	}
}
