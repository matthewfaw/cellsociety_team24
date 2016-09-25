package xml;

import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.settings.CellSettings;
import views.styles.CellStyleGuide;

/**
 * A factory to pull cell styling info from XML
 * @author matthewfaw
 *
 */
public class CellDataFactory extends XMLFactory {
	private static final String RESOURCE_PATH = "resources/CellData";

	private CellStyleGuide fCellStyleGuide;

	private NodeList fStateXMLNodes;
	private ResourceBundle fCellDataRB;
	
	public CellDataFactory(String fXmlFileName)
	{
		super(fXmlFileName);
		fCellDataRB = ResourceBundle.getBundle(RESOURCE_PATH);

		fXmlReader = new XMLReader(fXmlFileName);
		fStateXMLNodes = fXmlReader.findElements(getResource("StateElement"));
	}
	
	/**
	 * Obtains the cell specifications from the requested XML file
	 * @param aXmlFile
	 */
	public CellStyleGuide createStyleGuide()
	{
		fCellStyleGuide = new CellStyleGuide();
		for (int i=0; i<fStateXMLNodes.getLength(); ++i) {
			Element stateNode = getStateElement(i);
			
			appendStyleGuideInfo(stateNode);
		}
		
		return fCellStyleGuide;
	}
	
	public CellSettings createCellSettings()
	{
		CellSettings cellSettings = new CellSettings();
		//XXX: Refactor this
		for (int i=0; i<fStateXMLNodes.getLength(); ++i) {
			Element stateElement = getStateElement(i);
			int stateIndex = Integer.parseInt(stateElement.getAttribute(getResource("StateId")));

			Element modelInfoElement = fXmlReader.findFirstChildElement(stateElement, getResource("ModelInfoTag"));
			String rule = fXmlReader.getTextValue(modelInfoElement, getResource("Rule"));
			String neighborhood = fXmlReader.getTextValue(modelInfoElement, getResource("Neighborhood"));
			cellSettings.setRule(stateIndex, rule);
			cellSettings.setNeighborhood(stateIndex, neighborhood);
			
			Element propertiesElement = fXmlReader.findFirstChildElement(modelInfoElement, getResource("CellProperties"));
			NodeList properties = propertiesElement.getChildNodes();
			for (int j=0; j<properties.getLength(); ++j) {
				if (properties.item(j).getNodeType() == Node.ELEMENT_NODE) {
					Element property = (Element) properties.item(j);

					String propertyName = property.getTagName();
					double propertyVal = Double.parseDouble(property.getTextContent());
					cellSettings.addProperty(stateIndex, propertyName, propertyVal);
				}
			}
		}
		return cellSettings;
	}
	
	private Element getStateElement(int aIndex)
	{
		return (Element) fStateXMLNodes.item(aIndex);
	}
	
	private void appendStyleGuideInfo(Element aStateNode)
	{
		int stateIndex = Integer.parseInt(aStateNode.getAttribute(getResource("StateId")));

		Element viewElement = fXmlReader.findFirstChildElement(aStateNode, getResource("ViewInfoTag"));
		//XXX: Change this so that we iterate over all children of viewElement?
		String color = fXmlReader.getTextValue(viewElement, getResource("ColorTag"));
		String name = fXmlReader.getTextValue(viewElement, getResource("NameTag"));

		fCellStyleGuide.setColor(stateIndex, color);
		fCellStyleGuide.setName(stateIndex, name);
	}
	
	@Override
	protected String getResource(String aResourceToRetrieve)
	{
		return fCellDataRB.getString(aResourceToRetrieve);
	}
//	public static void main(String[] args)
//	{
//		CellDataFactory f = new CellDataFactory("state_config/test_states.xml");
//		f.createStyleGuide();
//		f.createCellSettings();
//	}
}
