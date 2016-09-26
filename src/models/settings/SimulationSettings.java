package models.settings;

import java.awt.Dimension;

/**
 * A class to manage all of the parameters of the 
 * currently running simulation
 * @author matthewfaw
 *
 */
public class SimulationSettings {
	
	private String fName;
	private String fTitle;
	private String fAuthor;
	private double fSimulationSpeed;
	
	public SimulationSettings(String aName, 
							String aTitle,
							String aAuthor,
							double aSimulationSpeed)
	{
		fName = aName;
		fTitle = aTitle;
		fAuthor = aAuthor;
		fSimulationSpeed = aSimulationSpeed;
	}
	
	public String getTitle()
	{
		return fTitle;
	}
	
	public double getSimulationSpeed()
	{
		return fSimulationSpeed;
	}
}
