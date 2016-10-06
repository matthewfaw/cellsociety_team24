package xml.model_factories;

import models.settings.SimulationSettings;
import resources.ResourceBundleHandler;
import xml.XMLFactory;

/**
 * A class to deal with creating simulation settings from XML
 * 
 * This class will fail if the resource file does not exist and if an XML element cannot be found.
 * 
 * This class depends on the ResourceBundleHandler class, and on the XML reader
 * 
 * To instantiate SimulationSettingsFactory:
 * SimulationSettingsFactory ssf = new SimulationSettingsFactory("/path/to/xml/folder/simulation_config/simulation_name_simulation.xml"); 
 * To create SimulationSettings object:
 * SimulationSettings s = ssf.createSimulationSettings();
 * 
 * @author matthewfaw
 *
 */
public class SimulationSettingsFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/SimulationSettings";

	private SimulationSettings fSimulationSettings;
	
	private ResourceBundleHandler fResourceBundleHandler;
	
	public SimulationSettingsFactory(String aXmlFileName)
	{
		super(aXmlFileName);
		
		fResourceBundleHandler = new ResourceBundleHandler(RESOURCE_PATH);
	}
	
	
	/**
	 * Creates SimulationSettings object
	 * Errors if XML element cannot be found
	 * @return
	 */
	public SimulationSettings createSimulationSettings()
	{
		String name = super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationName"));
		String title = super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationTitle"));
		String author = super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationAuthor"));
		double speed = Double.parseDouble(super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationSpeed")));
		
		fSimulationSettings = new SimulationSettings(name, title, author, speed);
		return fSimulationSettings;
	}

//	public static void main(String[] args)
//	{
//		SimulationSettingsFactory f = new SimulationSettingsFactory("simulation_config/test_simulation.xml");
//		f.createSimulationSettings();
//	}
}
