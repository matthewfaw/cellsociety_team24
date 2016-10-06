package xml.model_factories;

import org.w3c.dom.Element;

import models.settings.SimulationFileSettings;
import resources.ResourceBundleHandler;
import xml.XMLFactory;

/**
 * The purpose of this class is to manage construction of the Simulation file settings object
 * by extracting all of the file names out of the relevant XML file.
 * 
 * This class will fail if there is no resources/Filepaths.properties file.  It will also fail
 * if one of the expected XML elements is not present.
 * 
 * This class depends on the XML reader object.
 * 
 * To create a SimulationFileSettingsFactory:
 * SimulationFileSettingsFactory sfsf = new SimulationFileSettingsFactory(/path/to/xml/folder/simulation_name.xml);
 * To create the file settings object:
 * sfsf.createFileSettings();
 * 
 * @author matthewfaw
 *
 */
public class SimulationFileSettingsFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/Filepaths";

	private String fXmlFolderPath; 

	private ResourceBundleHandler fResourceBundleHandler;

	public SimulationFileSettingsFactory(String aXmlFolderPath, String aXmlFileName) {
		super(aXmlFolderPath + aXmlFileName);
		fXmlFolderPath = aXmlFolderPath;
		fResourceBundleHandler = new ResourceBundleHandler(RESOURCE_PATH);
	}
	
	/**
	 * Creates SimulationFileSettings object
	 * Errors if XML element cannot be found
	 * @return
	 */
	public SimulationFileSettings createFileSettings()
	{
		Element gridElement = super.getXmlReader().findFirstChildElement("grid");
		Element simulationElement = super.getXmlReader().findFirstChildElement("simulation_info");
		Element stateElement = super.getXmlReader().findFirstChildElement("state_info");
		
		SimulationFileSettings simulationFileSettings = new SimulationFileSettings();
		simulationFileSettings.setGridFile(fXmlFolderPath + fResourceBundleHandler.getResource("GridFiles") + gridElement.getTextContent());
		simulationFileSettings.setSimulationFile(fXmlFolderPath + fResourceBundleHandler.getResource("SimulationInfoFiles") + simulationElement.getTextContent());
		simulationFileSettings.setStateFile(fXmlFolderPath + fResourceBundleHandler.getResource("StateFiles") + stateElement.getTextContent());
		
		return simulationFileSettings;
	}

//	@Override
//	protected String getResource(String aResourceToRetrieve) {
//		return fFilePathsRB.getString(aResourceToRetrieve);
//	}

}
