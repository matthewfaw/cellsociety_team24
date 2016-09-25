package xml;

public abstract class XMLFactory {
	protected XMLReader fXmlReader;
	
	public XMLFactory(String aXmlFileName)
	{
		fXmlReader = new XMLReader(aXmlFileName);
	}
	protected abstract String getResource(String aResourceToRetrieve);
}
