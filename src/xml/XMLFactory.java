package xml;

public abstract class XMLFactory {
	private XMLReader fXmlReader;
	
	public XMLFactory(String aXmlFileName)
	{
		fXmlReader = new XMLReader(aXmlFileName);
	}
	
	protected XMLReader getXmlReader()
	{
		return fXmlReader;
	}
	
//	protected abstract String getResource(String aResourceToRetrieve);
}
