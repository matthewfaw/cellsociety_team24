package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * A class intended to provide essential access methods
 * for XML documents
 * @author matthewfaw
 *
 */
public class XMLReader {
	private Element fDocumentRoot;
	public XMLReader(String aXmlFilename)
	{
		XMLParser parser = new XMLParser();
		fDocumentRoot = parser.getRootElement(aXmlFilename);
	}
	
	public NodeList findElements(String aTagName)
	{
		return fDocumentRoot.getElementsByTagName(aTagName);
	}

	public NodeList findElements(Element aNode, String aTagName)
	{
		return aNode.getElementsByTagName(aTagName);
	}
	
	public String getAttribute(String aAttributeName)
	{
		return fDocumentRoot.getAttribute(aAttributeName);
	}

	public String getTextValue(Element aNode, String aTagName) 
	{
		NodeList nodeList = aNode.getElementsByTagName(aTagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		} else {
			//XXX: should this be an error?
			return "";
		}
	}

}
