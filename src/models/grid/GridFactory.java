package models.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import models.rules.Rule;
import models.rules.RuleFactory;
import models.settings.CellSettings;
import models.settings.GridSettings;

/**
 * The purpose of this class is to manage the construction of the Grid used in the Model layer.
 * 
 * It assumes it is given a validly populated GridSettings and CellSettings object.
 * Currently, it also assumes that the grid will be constructed using the state proportions specified
 * in XML, not by a list of cell coordinates.  This could be easily changed, however, by
 * changing getRandomStateId() to be called getAStateId(), and make the method abstract.  Then, there could
 * be subclasses which define how a given cell will be constructed.
 * 
 * This class depends heavily upon GridSettings and CellSettings to get the simulation settings specified by XML.
 * This class also depends on the CellFactory class to construct a valid cell according to the XML specifications.
 * 
 * The public API is very straightforward...
 * Assume you have validly constructed GridSettings aGridSettings object and CellSettings aCellSettings objects.
 * Then this class may be used as follows:
 * GridFactory aGridFactory = new GridFactory(aGridSettings, aCellSettings);
 * GridModel gridModel = aGridFactory.createGridModel;
 * 
 * Note that constructing gridModel with a factory allows us to hide from the user which subclass of GridModel was
 * actually constructed.
 * 
 * @author matthewfaw
 *
 */
public class GridFactory {
	private static final Random fRandomNumberGenerator = new Random();
	
	private GridSettings fGridSettings;
	private CellSettings fCellSettings;
	
	private ArrayList<Integer> fStateIds;

	public GridFactory(GridSettings aGridSettings, CellSettings aCellSettings)
	{
		fGridSettings = aGridSettings;
		fCellSettings = aCellSettings;
		fStateIds = new ArrayList<Integer>(fGridSettings.getStatePercentages().keySet());
	}

	/**
	 * Creates the grid model object.
	 * 
	 * This method fails when grid settings or cell settings is not properly constructed
	 * @return the GridModel object
	 */
	public GridModel createGridModel()
	{
		RuleFactory ruleFactory = new RuleFactory();
		Rule rules = ruleFactory.createRule(fGridSettings.getRuleType(), fCellSettings.getDefaults(), fCellSettings.getStateNames());
		ArrayList<Cell> grid = buildGrid();
		int numberOfSides = fGridSettings.getNumberOfCellSides();
		Dimension gridDimensions = fGridSettings.getDimensions();
		
		GridModel gridModel = new GridModel(grid, gridDimensions, rules, numberOfSides, fCellSettings.getAllProperties());
		
		return gridModel;
	}
	
	/**
	 * This method constructs the list of cells that make up the Grid Model
	 * @return the list of Cells
	 */
	private ArrayList<Cell> buildGrid()
	{
		Dimension gridDimensions = fGridSettings.getDimensions();
		ArrayList<Cell> grid = new ArrayList<Cell>();
		for (int row=0; row<gridDimensions.getWidth(); ++row) {
			for (int col=0; col<gridDimensions.getHeight(); ++col) {
				int stateIndex = getRandomStateId();
				Map<String, Double> stateProperties = fCellSettings.getProperties(stateIndex);
				//TODO: Pass additional arguments.
				Cell cell = CellFactory.newCell(row, col, stateIndex, stateProperties, fGridSettings.getNumberOfCellSides(), fGridSettings.getNeighborType());
				grid.add(cell);
			}
		}
		return grid;
	}

	/**
	 * A method that produces a random state ID based on the distribution specified in XML.
	 * This method assumes that the total state percentages adds up to 100
	 * @return a randomly generated, valid state ID
	 */
	private int getRandomStateId() 
	{
		double randomPercentage = fRandomNumberGenerator.nextDouble() * 100.0;
		double previousProportion = 0;
		for (int stateId: fStateIds) {
			double newProportion = fGridSettings.getStatePercentages().get(stateId) + previousProportion;
			
			if (newProportion >= randomPercentage) {
				return stateId;
			}
			
			previousProportion = newProportion;
		}
		return fStateIds.get(fStateIds.size() - 1);
	}
}
