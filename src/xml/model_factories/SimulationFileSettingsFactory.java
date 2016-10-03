package xml.model_factories;

import org.w3c.dom.Element;

import models.settings.SimulationFileSettings;
import resources.ResourceBundleHandler;
import xml.XMLFactory;

public class SimulationFileSettingsFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/Filepaths";

	private String fXmlFolderPath; 

	private ResourceBundleHandler fResourceBundleHandler;

	public SimulationFileSettingsFactory(String aXmlFolderPath, String aXmlFileName) {
		super(aXmlFolderPath + aXmlFileName);
		fXmlFolderPath = aXmlFolderPath;
		fResourceBundleHandler = new ResourceBundleHandler(RESOURCE_PATH);
	}
	
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
