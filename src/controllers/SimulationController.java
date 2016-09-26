package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.util.Duration;

public class SimulationController {
	private Timeline fSimulationTimeline;

	public SimulationController(Timeline aSimulationTimeline)
	{
		fSimulationTimeline = aSimulationTimeline;
	}
	
	public void start()
	{
		if (fSimulationTimeline.getStatus() != Status.RUNNING) {
			fSimulationTimeline.play();
		}
	}
	public void pause()
	{
		
	}
	public void reset()
	{
		// return game state to initial state
	}
}
