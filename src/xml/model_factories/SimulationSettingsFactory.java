package xml.model_factories;

import models.settings.SimulationSettings;
import resources.ResourceBundleHandler;
import xml.XMLFactory;

/**
 * A class to deal with creating simulation settings from XML
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
	
	public SimulationSettings createSimulationSettings()
	{
		String name = super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationName"));
		String title = super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationTitle"));
		String author = super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationAuthor"));
		double speed = Double.parseDouble(super.getXmlReader().getTextValue(fResourceBundleHandler.getResource("SimulationSpeed")));
		
		fSimulationSettings = new SimulationSettings(name, title, author, speed);
		return fSimulationSettings;
	}

//	@Override
//	protected String getResource(String aResourceToRetrieve) {
//		return fSimulationSettingsRB.getString(aResourceToRetrieve);
//	}
	
//	public static void main(String[] args)
//	{
//		SimulationSettingsFactory f = new SimulationSettingsFactory("simulation_config/test_simulation.xml");
//		f.createSimulationSettings();
//	}
}
