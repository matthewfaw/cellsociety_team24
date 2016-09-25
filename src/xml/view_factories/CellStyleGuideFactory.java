package xml.view_factories;

import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import views.styles.CellStyleGuide;
import xml.XMLReader;

/**
 * A factory to pull cell styling info from XML
 * @author matthewfaw
 *
 */
public class CellStyleGuideFactory {
	private static final String RESOURCE_PATH = "resources/CellStyleguide";
	private XMLReader fXmlReader;
	private NodeList fStateXMLNodes;
	private CellStyleGuide fCellStyleGuide;
	private ResourceBundle fCellStyleGuideRB;
	
	public CellStyleGuideFactory(String fXmlFilename)
	{
		fCellStyleGuideRB = ResourceBundle.getBundle(RESOURCE_PATH);

		fXmlReader = new XMLReader(fXmlFilename);
		fStateXMLNodes = fXmlReader.findElements(getResource("StateElement"));
		fCellStyleGuide = new CellStyleGuide();
	}
	
	/**
	 * Obtains the cell specifications from the requested XML file
	 * @param aXmlFile
	 */
	public CellStyleGuide createStyleGuide()
	{
		for (int i=0; i<fStateXMLNodes.getLength(); ++i) {
			Element stateNode = getStateElement(i);
			
			appendStyleGuideInfo(stateNode);
		}
		
		return fCellStyleGuide;
	}
	
	private Element getStateElement(int aIndex)
	{
		return (Element) fStateXMLNodes.item(aIndex);
	}
	
	private void appendStyleGuideInfo(Element aStateNode)
	{
		int stateIndex = Integer.parseInt(aStateNode.getAttribute(getResource("StateId")));

		Element viewElement = fXmlReader.findFirstChildElement(aStateNode, getResource("ViewInfoTag"));
		String color = fXmlReader.getTextValue(viewElement, getResource("ColorTag"));
		String name = fXmlReader.getTextValue(viewElement, getResource("NameTag"));

		fCellStyleGuide.setColor(stateIndex, color);
		fCellStyleGuide.setName(stateIndex, name);
	}
	
	private String getResource(String aResourceToRetrieve)
	{
		return fCellStyleGuideRB.getString(aResourceToRetrieve);
	}
//	public static void main(String[] args)
//	{
//		CellStyleGuideFactory f = new CellStyleGuideFactory("state_config/test_states.xml");
//		f.createStyleGuide();
//	}
}
