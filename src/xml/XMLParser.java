package xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * This class is devoted solely to obtaining the root element of a requested
 * XML file in an efficient manner (i.e. by only ever keeping one DocumentBuiler
 * around)
 * Note that this class is only accessible within the xml package, to encourage
 * Encapsulating the XML access methods
 * 
 * This class will fail if it cannot create a DocumentBuilder or if it cannot find the root
 * element of the requested XML file
 * 
 * This class depends on the DocumentBuilder class
 * 
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author matthewfaw
 *
 */
class XMLParser {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	
	XMLParser()
	{
	}
	
	/**
	 * Clears the DocumentBuilder, and obtains the root of the requested XML file
	 * @param aXmlFilename
	 * @return
	 */
	public Element getRootElement(String aXmlFilePath)
	{
		DOCUMENT_BUILDER.reset();
		Document xmlDocument;
		try {
			xmlDocument = DOCUMENT_BUILDER.parse(aXmlFilePath);
			return xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static DocumentBuilder getDocumentBuilder()
	{
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//XXX: change to throws
			return null;
		}
	}
}
