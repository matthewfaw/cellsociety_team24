package xml.model_factories;

import java.util.ResourceBundle;

import org.w3c.dom.NodeList;

import models.settings.SimulationSettings;
import xml.XMLFactory;

/**
 * A class to deal with creating simulation settings from XML
 * @author matthewfaw
 *
 */
public class SimulationSettingsFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/SimulationSettings";

	private SimulationSettings fSimulationSettings;
	
	private ResourceBundle fSimulationSettingsRB;
	
	public SimulationSettingsFactory(String aXmlFileName)
	{
		super(aXmlFileName);
		
		fSimulationSettingsRB = ResourceBundle.getBundle(RESOURCE_PATH);
	}
	
	public SimulationSettings createSimulationSettings()
	{
		String name = fXmlReader.getTextValue(getResource("SimulationName"));
		String title = fXmlReader.getTextValue(getResource("SimulationTitle"));
		String author = fXmlReader.getTextValue(getResource("SimulationAuthor"));
		double speed = Double.parseDouble(fXmlReader.getTextValue(getResource("SimulationSpeed")));
		
		fSimulationSettings = new SimulationSettings(name, title, author, speed);
		return fSimulationSettings;
	}

	@Override
	protected String getResource(String aResourceToRetrieve) {
		return fSimulationSettingsRB.getString(aResourceToRetrieve);
	}
	
//	public static void main(String[] args)
//	{
//		SimulationSettingsFactory f = new SimulationSettingsFactory("simulation_config/test_simulation.xml");
//		f.createSimulationSettings();
//	}
}
