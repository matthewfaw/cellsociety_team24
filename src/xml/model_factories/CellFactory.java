package xml.model_factories;

import models.grid.Cell;
import models.settings.CellSettings;

/**
 * A class to handle setting up all the proper info for a given cell
 * from the XML. This class should manage setting up cells to know about their neighbors
 * @author matthewfaw
 *
 */
public class CellFactory {
	private CellSettings fCellSettings;
	
	public CellFactory(CellSettings aCellSettings)
	{
		fCellSettings = aCellSettings;
	}
	
	/**
	 * Constructs a cell based upon the cell settings and the requested
	 * cell state
	 * @return
	 */
	public Cell constructCell(int aStateIndex)
	{
		return null;
	}
	
	/**
	 * Creates the neighbors according to cell setting specifications
	 * @param aCell
	 */
	private void createNeighborsForCell(Cell aCell)
	{
		
	}
}
