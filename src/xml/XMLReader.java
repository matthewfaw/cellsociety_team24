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
	
	public Element getRoot()
	{
		return fDocumentRoot;
	}
	
	/**
	 * Finds all elements under the document root with a matching tag name
	 * @param aTagName
	 * @return
	 */
	public NodeList findElements(String aTagName)
	{
		return findElements(fDocumentRoot, aTagName);
	}

	/**
	 * Finds all elements under aNode with a matching tag name
	 * @param aNode
	 * @param aTagName
	 * @return
	 */
	public NodeList findElements(Element aNode, String aTagName)
	{
		return aNode.getElementsByTagName(aTagName);
	}
	
	/**
	 * A method that finds a the first child element beneath aSourceNode matching the tag name 
	 * @param aSourceNode
	 * @param aTagNameToFind
	 * @return
	 */
	public Element findFirstChildElement(Element aSourceNode, String aTagNameToFind)
	{
		return (Element) aSourceNode.getElementsByTagName(aTagNameToFind).item(0);
	}
	public Element findFirstChildElement(String aTagNameToFind)
	{
		return findFirstChildElement(fDocumentRoot, aTagNameToFind);
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
	public String getTextValue(String aTagName) 
	{
		return getTextValue(fDocumentRoot, aTagName);
	}

}
