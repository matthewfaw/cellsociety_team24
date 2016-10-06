package models.settings;

/**
 * The purpose of this class is to encapsulate all of the simulation settings
 * specified in the XML.
 * 
 * This class is supposed to be constructed using the corresponding SimulationSettingsFactory
 * 
 * Once the SimulationSettings object is constructed, data can be accessed in the following way:
 * simulationSettings.getTitle(); // returns the title of the simulation
 * simulationSettings.getSimulationSpeed(); // returns the simulation speed requested.
 * 
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
	
	/**
	 * Gets the simulation title
	 * @return
	 */
	public String getTitle()
	{
		return fTitle;
	}
	
	/**
	 * Gets the simulation speed
	 * @return
	 */
	public double getSimulationSpeed()
	{
		return fSimulationSpeed;
	}
}
