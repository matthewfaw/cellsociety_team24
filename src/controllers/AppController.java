package controllers;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.AppResources;
import views.AppScene;

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

	public void fileButtonHandler(Stage s) 
	{
		FileChooser filexmlChooser=new FileChooser();
		File file =filexmlChooser.showOpenDialog(s);
		if (file!=null){
//			initializeGrid(file.getPath()); // a private method inside AppController
		}
	}
//	private void initializeGrid()
//	{
//		
//	}
}
