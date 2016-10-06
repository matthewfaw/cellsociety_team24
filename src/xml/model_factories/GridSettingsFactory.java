package xml.model_factories;

import java.awt.Dimension;
import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.settings.GridSettings;
import resources.ResourceBundleHandler;
import xml.XMLFactory;

/**
 * The purpose of this class is to make the proper XML queries to extract the
 * grid settings information and use this information to construct the GridSettings
 * object, which encapsulates this information.  This class hides the complexities
 * of extracting this information.
 * 
 * This class will fail if one of the resources requested is not present.
 * 
 * This class depends on the XML Reader owned by the superclass
 * 
 * To instantiate the class, simply provide the file path to the main XML file:
 * GridSettingsFactory gsf = new GridSettingsFactory("/path/to/xml/folder/grid_config/simulation_grid.xml");
 * To obtain the grid settings object:
 * GridSettings gs = gsf.createGridSettings();
 * 
 * @author matthewfaw
 *
 */
public class GridSettingsFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/GridSettings";
	
	private ResourceBundleHandler fResourceBundleHandler;

	public GridSettingsFactory(String aXmlFileName) {
		super(aXmlFileName);
		
		fResourceBundleHandler = new ResourceBundleHandler(RESOURCE_PATH);
	}
	
	/**
	 * Creates a GridSettings object from the XML elements
	 * Fails if an XML element is not found  successfully
	 * @return
	 */
	public GridSettings createGridSettings()
	{
		Element gridWidthElement = super.getXmlReader().findFirstChildElement(fResourceBundleHandler.getResource("GridWidth"));
		Element gridHeightElement = super.getXmlReader().findFirstChildElement(fResourceBundleHandler.getResource("GridHeight"));
		Element gridTypeElement = super.getXmlReader().findFirstChildElement(fResourceBundleHandler.getResource("GridType"));
		Element gridRulesElement = super.getXmlReader().findFirstChildElement(fResourceBundleHandler.getResource("GridRules"));
		Element gridNeighborsElement = super.getXmlReader().findFirstChildElement(fResourceBundleHandler.getResource("GridNeighbors"));

		int gridWidth = Integer.parseInt(gridWidthElement.getTextContent());
		int gridHeight = Integer.parseInt(gridHeightElement.getTextContent());
		String gridType = gridTypeElement.getTextContent();
		String gridRules = gridRulesElement.getTextContent();
		String gridNeighbors;
		if (gridNeighborsElement != null) {
			gridNeighbors = gridNeighborsElement.getTextContent();
		} else {
			//TODO: Change this from being hard coded
			gridNeighbors = "edges";
		}
		
		GridSettings gridSettings = new GridSettings(new Dimension(gridWidth, gridHeight), gridType, gridRules, gridNeighbors);
		
		Element stateProportionsElement = super.getXmlReader().findFirstChildElement(fResourceBundleHandler.getResource("StateProportions"));
		NodeList stateProportions = stateProportionsElement.getChildNodes();
		for (int i=0; i<stateProportions.getLength(); ++i) {
			if (stateProportions.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element percentage = (Element) stateProportions.item(i);
				int id = Integer.parseInt(percentage.getAttribute(fResourceBundleHandler.getResource("StateId")));
				int value = Integer.parseInt(percentage.getAttribute(fResourceBundleHandler.getResource("PercentageValue")));
				
				gridSettings.addPercentage(id, value);
			}
		}
		return gridSettings;
	}
}
