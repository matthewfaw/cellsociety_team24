package xml;

/**
 * The purpose of this class is to enforce that every
 * class which inherits from XML Factory has an XML reader object to use
 * 
 * @author matthewfaw
 *
 */
public abstract class XMLFactory {
	private XMLReader fXmlReader;
	
	public XMLFactory(String aXmlFileName)
	{
		fXmlReader = new XMLReader(aXmlFileName);
	}
	
	/**
	 * Provides a way for subclasses to access the XML object
	 * @return the XMLReader object
	 */
	protected XMLReader getXmlReader()
	{
		return fXmlReader;
	}
}
