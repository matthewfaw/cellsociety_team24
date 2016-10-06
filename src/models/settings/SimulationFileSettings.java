package models.settings;

/**
 * The purpose of this class is to encapsulate the three file names specified by the main
 * XML document -- the grid file, simulation file, and state file.
 * 
 * Since the default constructor does not add any information to the class, the methods will
 * return null if the requested data has not yet been initialized.
 * This class is to be constructed by the SimulationFileSettings Factory.
 * 
 * Once constructed, the file names may be accessed by:
 * simulationFileSettings.getGridFile();
 * 						 .getSimulationFile();
 * ect.
 * 
 * @author matthewfaw
 *
 */
public class SimulationFileSettings {
	private String fGridFile;
	private String fSimulationFile;
	private String fStateFile;

	public SimulationFileSettings()
	{
	}
	
	/**
	 * Sets the grid file name
	 * @param aGridFileName
	 */
	public void setGridFile(String aGridFileName)
	{
		fGridFile = aGridFileName;
	}
	/**
	 * gets the grid file name
	 * @return
	 */
	public String getGridFile()
	{
		return fGridFile;
	}
	
	/**
	 * Sets the simulation file name
	 * @param aSimulationFileName
	 */
	public void setSimulationFile(String aSimulationFileName)
	{
		fSimulationFile = aSimulationFileName;
	}
	/**
	 * Gets the simulation file name
	 * @return
	 */
	public String getSimulationFile()
	{
		return fSimulationFile;
	}
	/**
	 * sets the state file name
	 * @param aStateFileName
	 */
	public void setStateFile(String aStateFileName)
	{
		fStateFile = aStateFileName;
	}
	/**
	 * gets the state file name
	 * @return
	 */
	public String getStateFile()
	{
		return fStateFile;
	}
}
