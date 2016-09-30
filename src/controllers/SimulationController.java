package controllers;

import javafx.animation.Timeline;
import javafx.animation.Animation.Status;

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
		if (fSimulationTimeline.getStatus() == Status.RUNNING) {
			fSimulationTimeline.pause();
		}
	}
	public void reset()
	{
		// return game state to initial state
	}
	public void changeSpeed(double aSpeed)
	{
		fSimulationTimeline.setRate(/*fSimulationTimeline.getRate() + */ aSpeed/100.0);
	}
}
