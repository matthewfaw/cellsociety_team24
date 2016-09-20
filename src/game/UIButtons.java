package game;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class UIButtons {
	
	private ArrayList<Button> ButtonList=new ArrayList<>();

	public UIButtons() {
		//ButtonList=new ArrayList<>();
	}

	public ArrayList<Button> BuildButtons() {
		addStart();
		addPause();
		addStep();
		addReset();
		addSetSimulation();
		return ButtonList;
	}

	private void addSetSimulation() {
		Button SetSimulationButton= new Button("Reset");
		SetSimulationButton.setLayoutX(200);
		SetSimulationButton.setLayoutY(700);
		SetSimulationButton.setOnAction(e->SetSimulationButtonHandler());	
		ButtonList.add(SetSimulationButton);
	}

	private void SetSimulationButtonHandler() {
		//Set which simulation
	}

	private void addReset() {
		Button ResetButton= new Button("Reset");
		ResetButton.setLayoutX(200);
		ResetButton.setLayoutY(500);
		ResetButton.setOnAction(e->ResetButtonHandler());
		ButtonList.add(ResetButton);
	}

	private void ResetButtonHandler() {
		//Reset Simulation
	}

	private void addStep() {
		Button StepButton= new Button("Step Forward");
		StepButton.setLayoutX(200);
		StepButton.setLayoutY(550);
		StepButton.setOnAction(e->StepButtonHandler());
		ButtonList.add(StepButton);
	}

	private void StepButtonHandler() {
		//Step forward through Simulation
	}

	private void addPause() {
		Button PauseButton= new Button("Pause");
		PauseButton.setLayoutX(200);
		PauseButton.setLayoutY(600);
		PauseButton.setOnAction(e->PauseButtonHandler());
		ButtonList.add(PauseButton);
	}

	private void PauseButtonHandler() {
		//Pause Simulation
	}

	private void addStart() {
		Button StartButton= new Button("Start");
		StartButton.setLayoutX(200);
		StartButton.setLayoutY(650);
		ButtonList.add(StartButton);
		StartButton.setOnAction(e->StartButtonHandler());
	}

	private void StartButtonHandler() {
		//Start Simulation
	}
	

}
