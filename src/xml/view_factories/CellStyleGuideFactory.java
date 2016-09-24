package xml.view_factories;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import views.styles.CellStyleGuide;
import xml.XMLReader;

public class CellStyleGuideFactory {
	private XMLReader fXmlReader;
	NodeList fStateXMLNodes;
	CellStyleGuide fCellStyleGuide;
	
	public CellStyleGuideFactory(String fXmlFilename)
	{
		fXmlReader = new XMLReader(fXmlFilename);
		fStateXMLNodes = fXmlReader.findElements("State");
		fCellStyleGuide = new CellStyleGuide();
	}
	
	/**
	 * Obtains the cell specifications from the requested XML file
	 * @param aXmlFile
	 */
	public CellStyleGuide createStyleGuide()
	{
		// find all style info contained in the xml, and make the hashmap
		for (int i=0; i<fStateXMLNodes.getLength(); ++i) {
			Element stateNode = (Element) fStateXMLNodes.item(i);
			int stateIndex = Integer.parseInt(stateNode.getAttribute("id"));
			
			Element viewElement = (Element) fXmlReader.findElements(stateNode, "view").item(0);
			String color = fXmlReader.getTextValue(viewElement, "color");
			String name = fXmlReader.getTextValue(viewElement, "name");
			
			fCellStyleGuide.setColor(stateIndex, color);
			fCellStyleGuide.setName(stateIndex, name);
		}
		
		return fCellStyleGuide;
	}
}
