package xml;

import java.util.HashMap;
import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.rules.RuleType;
import models.settings.CellSettings;
import resources.ResourceBundleHandler;
import views.styles.CellStyleGuide;
import views.styles.FixedColorStyleGuide;
import views.styles.GradientColorStyleGuide;

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
	private ResourceBundleHandler fResourceBundleHandler;
	
	public CellDataFactory(String fXmlFileName)
	{
		super(fXmlFileName);
		fResourceBundleHandler = new ResourceBundleHandler(RESOURCE_PATH);
		
		fStateXMLNodes = super.getXmlReader().findElements(fResourceBundleHandler.getResource("StateElement"));
	}
	
	/**
	 * Obtains the cell specifications from the requested XML file
	 * @param aXmlFile
	 */
	public CellStyleGuide createStyleGuide(RuleType aRuleType)
	{
		switch (aRuleType) {
			case SlimeMold:
				fCellStyleGuide = new GradientColorStyleGuide();
				break;
			default:
				fCellStyleGuide = new FixedColorStyleGuide();
				break;
		}

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
			
			NodeList properties = getModelInfo(stateElement, fResourceBundleHandler.getResource("CellProperties"));
			HashMap<String, Double> propertiesMap = createInfoMap(stateIndex, properties);
			fCellSettings.addProperties(stateIndex, propertiesMap);
			
			NodeList defaults = getModelInfo(stateElement, fResourceBundleHandler.getResource("CellDefaults"));
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
			int stateIndex = Integer.parseInt(aStateElement.getAttribute(fResourceBundleHandler.getResource("StateId")));
			String stateName = aStateElement.getAttribute(fResourceBundleHandler.getResource("StateName"));
			
			fCellSettings.setStateName(stateName,stateIndex);
			
			return stateIndex;
	}
	
	private NodeList getModelInfo(Element aStateElement, String aModelInfoListName)
	{
			Element modelInfoElement = super.getXmlReader().findFirstChildElement(aStateElement, fResourceBundleHandler.getResource("ModelInfoTag"));
			Element requestedElement = super.getXmlReader().findFirstChildElement(modelInfoElement, aModelInfoListName);
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
		int stateIndex = Integer.parseInt(aStateNode.getAttribute(fResourceBundleHandler.getResource("StateId")));

		Element viewElement = super.getXmlReader().findFirstChildElement(aStateNode, fResourceBundleHandler.getResource("ViewInfoTag"));
		//XXX: Change this so that we iterate over all children of viewElement?
		String color = super.getXmlReader().getTextValue(viewElement, fResourceBundleHandler.getResource("ColorTag"));

		fCellStyleGuide.setColor(stateIndex, color);
	}
	
//	public static void main(String[] args)
//	{
//		CellDataFactory f = new CellDataFactory("data/xml/state_config/NEW_fish_states.xml");
//		f.createStyleGuide();
//		f.createCellSettings();
//	}
}
