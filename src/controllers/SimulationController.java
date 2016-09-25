package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SimulationController {
	private Timeline fSimulationTimeline;

	public SimulationController(Timeline aSimulationTimeline)
	{
		fSimulationTimeline = aSimulationTimeline;
	}
	
	public void start()
	{
		// Simulation begins
	}
	public void pause()
	{
		
	}
	public void reset()
	{
		// return game state to initial state
	}
}
