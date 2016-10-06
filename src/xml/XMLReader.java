package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import exceptions.XMLElementNotFoundException;
import resources.ResourceBundleHandler;

/**
 * A class intended to provide essential access methods
 * for XML documents
 * 
 * This class will fail when it is given an invalid filepath or 
 * asked to find an XML element that does not exist
 * 
 * To instantiate the class:
 * XMLReader reader = new XMLReader("/path/to/xml/file/file.xml");
 * To find an element by tag name:
 * reader.findElement("aTagName");
 * 
 * @author matthewfaw
 *
 */
public class XMLReader {
	private Element fDocumentRoot;
	
	private static final String ERROR_PATH = "resources/ErrorMessages";
	private ResourceBundleHandler fErrorRBHandler;

	public XMLReader(String aXmlFilename)
	{
		XMLParser parser = new XMLParser();
		fDocumentRoot = parser.getRootElement(aXmlFilename);
		
		fErrorRBHandler = new ResourceBundleHandler(ERROR_PATH);
	}
	
	/**
	 * @return the XML document root
	 */
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
		NodeList childElements = aSourceNode.getElementsByTagName(aTagNameToFind);
		if (childElements == null) {
			throw new XMLElementNotFoundException(fErrorRBHandler.getResource("TagNameForElementNotFound"), aTagNameToFind);
		}
		Element firstChild = (Element) childElements.item(0);
		
		return firstChild;
	}
	/**
	 * Retrieves the first child element matching aTagNameToFind
	 * @param aTagNameToFind
	 * @return
	 */
	public Element findFirstChildElement(String aTagNameToFind)
	{
		return findFirstChildElement(fDocumentRoot, aTagNameToFind);
	}
	
	/**
	 * Gets the requested XML attribute string
	 * @param aAttributeName
	 * @return
	 */
	public String getAttribute(String aAttributeName)
	{
		return fDocumentRoot.getAttribute(aAttributeName);
	}

	/**
	 * Gets the text value associated with aTagName, searching from root aNode
	 * @param aNode
	 * @param aTagName
	 * @return
	 */
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
	/**
	 * Gets the text value associated with aTagName, searching from the document root
	 * @param aTagName
	 * @return
	 */
	public String getTextValue(String aTagName) 
	{
		return getTextValue(fDocumentRoot, aTagName);
	}

}
