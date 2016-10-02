package xml;

import java.util.HashMap;
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
	private CellSettings fCellSettings;

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
		fCellSettings = new CellSettings();

		for (int i=0; i<fStateXMLNodes.getLength(); ++i) {
			Element stateElement = getStateElement(i);
			
			int stateIndex = addStateIdentification(stateElement);
			
			NodeList properties = getModelInfo(stateElement, getResource("CellProperties"));
			HashMap<String, Double> propertiesMap = createInfoMap(stateIndex, properties);
			fCellSettings.addProperties(stateIndex, propertiesMap);
			
			NodeList defaults = getModelInfo(stateElement, getResource("CellDefaults"));
			if (defaults != null) {
				HashMap<String, Double> defaultsMap = createInfoMap(stateIndex, defaults);
//				fCellSettings.addDefaults(stateIndex, defaultsMap);
				for (String defaultName: defaultsMap.keySet()) {
					double defaultValue = defaultsMap.get(defaultName);
					fCellSettings.addDefault(defaultName, defaultValue);
				}
			}
			
		}
		return fCellSettings;
	}
	
	private HashMap<String, Double> createInfoMap(int aStateIndex, NodeList aPropertyList)
	{
		HashMap<String, Double> infoMap = new HashMap<String, Double>();
		for (int j=0; j<aPropertyList.getLength(); ++j) {
			if (aPropertyList.item(j).getNodeType() == Node.ELEMENT_NODE) {
				Element property = (Element) aPropertyList.item(j);

				String propertyName = property.getTagName();
				double propertyVal = Double.parseDouble(property.getTextContent());
				infoMap.put(propertyName, propertyVal);
			}
		}
		return infoMap;
	}
	
	private int addStateIdentification(Element aStateElement)
	{
			int stateIndex = Integer.parseInt(aStateElement.getAttribute(getResource("StateId")));
			String stateName = aStateElement.getAttribute(getResource("StateName"));
			
			fCellSettings.setStateName(stateName,stateIndex);
			
			return stateIndex;
	}
	
	private NodeList getModelInfo(Element aStateElement, String aModelInfoListName)
	{
			Element modelInfoElement = fXmlReader.findFirstChildElement(aStateElement, getResource("ModelInfoTag"));
			Element requestedElement = fXmlReader.findFirstChildElement(modelInfoElement, aModelInfoListName);
			if (requestedElement == null) {
				return null;
			}
			return requestedElement.getChildNodes();
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
//		String name = fXmlReader.getTextValue(viewElement, getResource("NameTag"));

		fCellStyleGuide.setColor(stateIndex, color);
//		fCellStyleGuide.setName(stateIndex, name);
	}
	
	@Override
	protected String getResource(String aResourceToRetrieve)
	{
		return fCellDataRB.getString(aResourceToRetrieve);
	}
//	public static void main(String[] args)
//	{
//		CellDataFactory f = new CellDataFactory("data/xml/state_config/NEW_fish_states.xml");
//		f.createStyleGuide();
//		f.createCellSettings();
//	}
}
