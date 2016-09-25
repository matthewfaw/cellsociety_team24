package models.settings;

public class SimulationFileSettings {
	private String fGridFile;
	private String fSimulationFile;
	private String fStateFile;

	public SimulationFileSettings()
	{
	}
	
	public void setGridFile(String aGridFileName)
	{
		fGridFile = aGridFileName;
	}
	public String getGridFile()
	{
		return fGridFile;
	}
	
	public void setSimulationFile(String aSimulationFileName)
	{
		fSimulationFile = aSimulationFileName;
	}
	public String getSimulationFile()
	{
		return fSimulationFile;
	}
	public void setStateFile(String aStateFileName)
	{
		fStateFile = aStateFileName;
	}
	public String getStateFile()
	{
		return fStateFile;
	}
}
