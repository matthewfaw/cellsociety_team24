package cellsociety_team24;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UIButtons {
	
	private ArrayList<Button> ButtonList=new ArrayList<>();
	private ResourceBundle mytext=ResourceBundle.getBundle("game.Resources/textfiles");
	public UIButtons() {
		//ButtonList=new ArrayList<>();
	}

	public ArrayList<Button> buildButtons(int scale,Timeline animation,Stage s) {
		addStart(scale,animation);
		addPause(scale,animation);
		//addStep(scale);
		addReset(scale);
		addSetSimulation(scale,s);
		return ButtonList;
	}

	private void addSetSimulation(int scale,Stage s) {
		Button SetSimulationButton= makeButton("SetSimulationCommand",e->SetSimulationButtonHandler(scale,s));
		SetSimulationButton.setLayoutX(scale*(Simulation.QUARTER));
		SetSimulationButton.setLayoutY(scale*0.875);
		ButtonList.add(SetSimulationButton);
	}

	private void SetSimulationButtonHandler(int scale,Stage s) {
		SplashScreen mysplashscreen = new SplashScreen();
        Scene myScene=mysplashscreen.makeSplashScreen(scale,scale, s);
        s.setScene(myScene);
        s.show();
	}

	private void addReset(int scale) {
		Button ResetButton= new Button(mytext.getString("ResetCommand"));
		ResetButton.setLayoutX(scale*(Simulation.QUARTER));
		ResetButton.setLayoutY(scale*0.625);
		ResetButton.setOnAction(e->ResetButtonHandler());
		ButtonList.add(ResetButton);
	}

	private void ResetButtonHandler() {
		//Reset Simulation
	}

	/*(private void addStep(int scale) {
		Button StepButton= new Button(mytext.getString("StepCommand"));
		StepButton.setLayoutX(scale*0.25);
		StepButton.setLayoutY(scale*0.6875);
		StepButton.setOnAction(e->StepButtonHandler());
		ButtonList.add(StepButton);
	}

	private void StepButtonHandler() {
	//Simulation.step(SplashScreen.SECOND_DELAY);
	}*/
	
	public Button makeButton(String name,EventHandler action)
	{
		Button b = new Button(mytext.getString(name));
		b.setOnAction(action);
		return b;
	}

	private void addPause(int scale,Timeline animation) {
		Button PauseButton= makeButton("PauseCommand",e->PauseButtonHandler(animation));
		PauseButton.setLayoutX(scale*(Simulation.QUARTER));
		PauseButton.setLayoutY(scale*0.75);
		ButtonList.add(PauseButton);
	}

	private void PauseButtonHandler(Timeline animation) {
		animation.stop();
	}

	private void addStart(int scale,Timeline animation) {
		Button StartButton= makeButton("StartCommand",e->StartButtonHandler(animation));
		StartButton.setLayoutX(scale*(Simulation.QUARTER));
		StartButton.setLayoutY(scale*0.8125);
		ButtonList.add(StartButton);
	}

	private void StartButtonHandler(Timeline animation) {
		animation.play();
	}
}
