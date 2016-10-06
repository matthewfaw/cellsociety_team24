package views.styles;

import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to encapulate 
 * the information from XML relevant to the cells on the view side
 * The CellStyleGuideFactory is responsible for building the subclasses
 * of this object
 * 
 * The reason why this class is abstract is to allow subclasses to 
 * provide different implementations of the getColor method.  This
 * allows different subclasses to perform different manipulations on the
 * Hex value stored in the color map
 * 
 * An example usage, after the CellStyleGuide has been constructed:
 * cellStyleGuide.getColor(3); // gets the color associated with a given state id
 * 
 * @author matthewfaw
 *
 */
public abstract class CellStyleGuide {
	private HashMap<Integer, String> fColors;
	
	public CellStyleGuide()
	{
		fColors = new HashMap<Integer, String>();
	}

	/**
	 * Gets the hex value corresponding to a given
	 * state
	 * @param aStateIndex
	 * @return
	 */
	public abstract String getColor(int aStateIndex);
	/**
	 * Adds a color to the color map.  This method is intended
	 * to be used only by the factory
	 * @param aStateIndex
	 * @param aHexValue
	 */
	public void setColor(int aStateIndex, String aHexValue)
	{
		fColors.put(aStateIndex, aHexValue);
	}
	/**
	 * gets the color map
	 * @return
	 */
	protected Map<Integer, String> getColors()
	{
		return fColors;
	}
	
}