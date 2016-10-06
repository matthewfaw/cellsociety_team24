package views.styles;

/**
 * The purpose of this class is to define the simple
 * access pattern for the cell color specified in XML.
 * 
 * @author matthewfaw
 *
 */
public class FixedColorStyleGuide extends CellStyleGuide{
	public FixedColorStyleGuide()
	{
		super();
	}

	@Override
	public String getColor(int aStateIndex)
	{
		return super.getColors().get(aStateIndex);
	}
}
