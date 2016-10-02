package views.styles;

import java.util.HashMap;

/**
 * The purpose of this class is to encapulate 
 * the information relevant to the cells that comes
 * from XML
 * @author matthewfaw
 *
 */
public class CellStyleGuide {
	private HashMap<Integer, String> fColors;
//	private HashMap<Integer, String> fNames;
	
	public CellStyleGuide()
	{
		fColors = new HashMap<Integer, String>();
//		fNames = new HashMap<Integer, String>();
	}

	/**
	 * Gets the hex value corresponding to a given
	 * state
	 * @param aStateIndex
	 * @return
	 */
	public String getColor(int aStateIndex)
	{
		return fColors.get(aStateIndex);
	}
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
	 * returns the name of the requested state
	 * @param aStateIndex
	 * @return
	 */
//	public String getName(int aStateIndex)
//	{
//		return fNames.get(aStateIndex);
//	}
	/**
	 * sets the name of the specified state
	 * @param aStateIndex
	 * @param aName
	 */
//	public void setName(int aStateIndex, String aName)
//	{
//		fNames.put(aStateIndex, aName);
//	}

}