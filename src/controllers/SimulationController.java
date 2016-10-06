package controllers;

import javafx.animation.Timeline;
import javafx.animation.Animation.Status;

/**
 * The purpose of this class is to manage the timeline.  It provides
 * abstractions to pausing/playing/changing the speed of the timeline.
 * It's main dependency is the Timeline class.
 * This class will fail if it is constructed with a null Timeline.
 * Example:
 * SimulationController simController = new SimulationController(timeline);
 * simController.pause();
 * simController.play();
 * simController.changeSpeed(40); // Sets simulation speed to 40%
 * @author matthewfaw
 *
 */
public class SimulationController {
	private Timeline fSimulationTimeline;

	/**
	 * Initializes the object to have a timeline which it may control
	 * @param aSimulationTimeline
	 */
	public SimulationController(Timeline aSimulationTimeline)
	{
		fSimulationTimeline = aSimulationTimeline;
	}
	
	/**
	 * A method which will begin the timeline if it is not running
	 */
	public void start()
	{
		if (fSimulationTimeline.getStatus() != Status.RUNNING) {
			fSimulationTimeline.play();
		}
	}
	/**
	 * A method to pause the timeline if it is running
	 */
	public void pause()
	{
		if (fSimulationTimeline.getStatus() == Status.RUNNING) {
			fSimulationTimeline.pause();
		}
	}
//	public void reset()
//	{
//		// return game state to initial state
//	}
	/**
	 * A method to change the speed of the timeline
	 * @param aSpeed: percentage (out of 100)--the new simulation speed
	 */
	public void changeSpeed(double aSpeed)
	{
		fSimulationTimeline.setRate(/*fSimulationTimeline.getRate() + */ aSpeed/100.0);
	}
}
