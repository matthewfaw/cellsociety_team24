package xml.model_factories;

import java.awt.Dimension;
import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.settings.GridSettings;
import xml.XMLFactory;

public class GridSettingsFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/GridSettings";
	
	private ResourceBundle fGridSettingsRB;

	public GridSettingsFactory(String aXmlFileName) {
		super(aXmlFileName);
		
		fGridSettingsRB = ResourceBundle.getBundle(RESOURCE_PATH);
	}
	
	private GridSettings createGridSettings()
	{
		Element gridWidthElement = fXmlReader.findFirstChildElement(getResource("GridWidth"));
		Element gridHeightElement = fXmlReader.findFirstChildElement(getResource("GridHeight"));
		Element gridTypeElement = fXmlReader.findFirstChildElement(getResource("GridType"));
		Element gridRulesElement = fXmlReader.findFirstChildElement(getResource("GridRules"));

		int gridWidth = Integer.parseInt(gridWidthElement.getTextContent());
		int gridHeight = Integer.parseInt(gridHeightElement.getTextContent());
		String gridType = gridTypeElement.getTextContent();
		String gridRules = gridRulesElement.getTextContent();

		GridSettings gridSettings = new GridSettings(new Dimension(gridWidth, gridHeight), gridType, gridRules);
		
		Element stateProportionsElement = fXmlReader.findFirstChildElement(getResource("StateProportions"));
		NodeList stateProportions = stateProportionsElement.getChildNodes();
		for (int i=0; i<stateProportions.getLength(); ++i) {
			if (stateProportions.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element percentage = (Element) stateProportions.item(i);
				int id = Integer.parseInt(percentage.getAttribute(getResource("StateId")));
				int value = Integer.parseInt(percentage.getAttribute(getResource("PercentageValue")));
				
				gridSettings.addPercentage(id, value);
			}
		}
		Element simulationPropertiesElement = fXmlReader.findFirstChildElement(getResource("SimulationProperties"));
		NodeList properties = simulationPropertiesElement.getChildNodes();
		for (int i=0; i<properties.getLength(); ++i) {
			if (properties.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element propertyElement = (Element) properties.item(i);
				String propertyName = propertyElement.getTagName();
				double propertyVal = Double.parseDouble(propertyElement.getTextContent());
				
				gridSettings.addProperty(propertyName, propertyVal);
			}
		}
		
		return gridSettings;
	}

	@Override
	protected String getResource(String aResourceToRetrieve) {
		return fGridSettingsRB.getString(aResourceToRetrieve);
	}
//	public static void main(String[] args)
//	{
//		GridSettingsFactory f = new GridSettingsFactory("grid_config/test_grid.xml");
//		f.createGridSettings();
//	}
//
}
