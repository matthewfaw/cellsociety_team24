// This entire file is part of my masterpiece.
// Matthew Faw
package models.settings;

import java.awt.Dimension;
import java.util.HashMap;

import exceptions.ConfigurationException;
import models.rules.RuleType;
import resources.CellShapes;
import resources.NeighborType;
import resources.ResourceBundleHandler;
import views.grid.GridType;

/**
 * The purpose of this class is to encapsulate the grid settings specified the XML
 * and organize the data in an understandable and easily-accessible way. Since it would
 * be difficult to always remember the strings specified in XML associated with grid type,
 * grid rules, and grid neighbors, this class provides the conversion of those strings in to
 * their associated enums so that the ___Types can be more strictly specified when dealing
 * with then elsewhere in the code 
 * 
 * This class will throw an error if the specified grid rule, grid type, or grid neighbors is not
 * among the valid list of options. Thus, when adding a new grid type, the coder will always know where
 * the error is occurring
 * 
 * This class depends on the ResourceBundleHandler to retrieve the proper error messages. This allows
 * the possibility of this error message's supporting multiple languages.
 * This class also depends on the GridType, RuleType, and NeighborType enums
 * in order to specify the possible valid grid, rule, and neighbor types
 * 
 * This class is instantiated using the factory:
 * GridSettingsFactory gridSettingsFactory = new GridSettingsFactory("/path/to/xml/grid_config/simName_grid.xml");
 * GridSettings gridSettings = gridSettingsFactory.createGridSettings();
 * 
 * To get the grid type specified in the file:
 * gridSettings.getGridType() // Returns the enum associated with the grid type specified in simName_grid.xml!
 * 
 * @author matthewfaw
 *
 */
public class GridSettings {
	private static final String ERROR_PATH = "resources/ErrorMessages";

	private Dimension fDimension;
	private GridType fGridType;
	private RuleType fGridRule;
	private NeighborType fNeighborType;
	private int fCellSides;
	private HashMap<Integer, Integer> fStatePercentages;
	private ResourceBundleHandler fErrorRBHandler;
	
	public GridSettings(Dimension aDimension, String aGridType, String aGridRules, String aGridNeighbors)
	{
		fDimension = aDimension;
		configureGridType(aGridType);
		configureGridRules(aGridRules);
		configureGridNeighbors(aGridNeighbors);
		
		fStatePercentages = new HashMap<Integer, Integer>();
		fErrorRBHandler = new ResourceBundleHandler(ERROR_PATH);
	}

	/**
	 * @return the GridType that was specified in XML
	 */
	public GridType getGridType()
	{
		return fGridType;
	}
	/**
	 * @return the RuleType that was specified in XML
	 */
	public RuleType getRuleType()
	{
		return fGridRule;
	}
	/**
	 * @return the NeighborType that was specified in XML
	 */
	public NeighborType getNeighborType()
	{
		return fNeighborType;
	}
	
	//XXX: want to switch on the strings from resource bundle, but this doesn't seem to be possible
	/**
	 * A method used to set the GridRule field based on the input aGridRule string
	 * Throws ConfigurationException if an invalid rule is provided
	 * @param aGridRule
	 */
	private void configureGridRules(String aGridRule)
	{
		switch(aGridRule) {
		case "fire":
			fGridRule = RuleType.Fire;
			break;
		case "wator":
			fGridRule = RuleType.Fish;
			break;
		case "life":
			fGridRule = RuleType.Life;
			break;
		case "segregation":
			fGridRule = RuleType.Segregation;
			break;
		case "slime":
			fGridRule = RuleType.SlimeMold;
			break;
		default:
			throw new ConfigurationException(fErrorRBHandler.getResource("InvalidRule"), aGridRule);
		}
	}
	//XXX: want to switch on the strings from resource bundle, but this doesn't seem to be possible
	/**
	 * 
	 * A method used to set the GridType field based on the input aGridType string
	 * Throws ConfigurationException if an invalid grid type is provided
	 * @param aGridType
	 */
	private void configureGridType(String aGridType)
	{
		switch(aGridType) {
			case "rectangular":
				fGridType = GridType.Square;
				fCellSides = CellShapes.RECTANGULAR;
				break;
			case "hexagonal":
				fGridType = GridType.Hex;
				fCellSides = CellShapes.HEXAGONAL;
				break;
			case "triangular":
				fGridType = GridType.Triangle;
				fCellSides = CellShapes.TRIANGULAR;
				break;
			default:
				throw new ConfigurationException(fErrorRBHandler.getResource("InvalidGridType"), aGridType);
		}
	}
	/**
	 * A method used to set the NeighborType field based on the input aGridNeighbors string
	 * Throws ConfigurationException if an invalid grid neighbors is provided
	 * 
	 * @param aGridNeighbors
	 */
	private void configureGridNeighbors(String aGridNeighbors)
	{
		switch(aGridNeighbors) {
		case "vertices":
			fNeighborType = NeighborType.Vertices;
			break;
		case "edges":
			fNeighborType = NeighborType.Edges;
			break;
		case "strange_triangle_neighbors":
			fNeighborType = NeighborType.StrangeTriangle;
			break;
		default:
			throw new ConfigurationException(fErrorRBHandler.getResource("InvalidNeighbors"), aGridNeighbors);
		}
	}
	
	/**
	 * Gets the number of rows of cells by number of columns of cells specified in XML
	 * @return
	 */
	public Dimension getDimensions()
	{
		return fDimension;
	}
	
	/**
	 * Gets the total number of cells in the grid (rows * cols)
	 * @return
	 */
	public int getTotalNumberOfCells()
	{
		return (int)(fDimension.getHeight() * fDimension.getWidth());
	}
	
	/**
	 * The number of cell sides of each cell in the grid
	 * @return
	 */
	public int getNumberOfCellSides()
	{
		return fCellSides;
	}
	/**
	 * add a percentage to the map
	 * @param aStateId
	 * @param aPercentage
	 */
	public void addPercentage(int aStateId, int aPercentage)
	{
		fStatePercentages.put(aStateId, aPercentage);
	}
	/**
	 * Gets the map from state ids to the requested proportion of that state id in the grid
	 * Note that this map does not check if the total percentages add up to 100
	 * @return
	 */
	public HashMap<Integer, Integer> getStatePercentages()
	{
		return fStatePercentages;
	}
}