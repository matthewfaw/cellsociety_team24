package utils;

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
	
	/**
	 * Initializes the object to contain the document root of interest
	 * @param aXmlFile
	 */
	public XMLReader(String aXmlFile)
	{
		XMLParser parser = new XMLParser();
		fDocumentRoot = parser.getRootElement(aXmlFile);
	}
	
	public NodeList getChildNodes()
	{
		return fDocumentRoot.getChildNodes();
	}
	
	public String getAttribute(String aAttributeName)
	{
		return fDocumentRoot.getAttribute(aAttributeName);
	}
	public String getTextValue (Element root, String tagName) {
        NodeList nodeList = root.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // BUGBUG: return empty string or null, is it an error to not find the text value?
            return "";
        }
    }

}
