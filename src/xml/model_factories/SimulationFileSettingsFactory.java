package xml.model_factories;

import java.util.ResourceBundle;

import javax.xml.soap.Node;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import models.settings.SimulationFileSettings;
import xml.XMLFactory;

public class SimulationFileSettingsFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/Filepaths";

	private String fXmlFolderPath; 

	private ResourceBundle fFilePathsRB;
	public SimulationFileSettingsFactory(String aXmlFolderPath, String aXmlFileName) {
		super(aXmlFolderPath + aXmlFileName);
		fXmlFolderPath = aXmlFolderPath;
		fFilePathsRB = ResourceBundle.getBundle(RESOURCE_PATH);
	}
	
	public SimulationFileSettings createFileSettings()
	{
		Element gridElement = fXmlReader.findFirstChildElement("grid");
		Element simulationElement = fXmlReader.findFirstChildElement("simulation_info");
		Element stateElement = fXmlReader.findFirstChildElement("state_info");
		
		SimulationFileSettings simulationFileSettings = new SimulationFileSettings();
		simulationFileSettings.setGridFile(fXmlFolderPath + getResource("GridFiles") + gridElement.getTextContent());
		simulationFileSettings.setSimulationFile(fXmlFolderPath + getResource("SimulationInfoFiles") + simulationElement.getTextContent());
		simulationFileSettings.setStateFile(fXmlFolderPath + getResource("StateFiles") + stateElement.getTextContent());
		
		return simulationFileSettings;
	}

	@Override
	protected String getResource(String aResourceToRetrieve) {
		return fFilePathsRB.getString(aResourceToRetrieve);
	}

}
