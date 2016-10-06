package models.settings;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.ResourceBundle;

import exceptions.GridTypeException;
import exceptions.RuleTypeException;
import models.rules.RuleType;
import resources.CellShapes;
import resources.NeighborType;
import resources.ResourceBundleHandler;
import views.grid.GridType;

/**
 * The purpose of this class is to encapsulate the grid settings specified the XML
 * and organize the data in an understandable and easily-accessible way.
 * 
 * This class will throw an error if the specified grid rule or grid type is not
 * among the valid list of options. **Currently, if the specified grid neighbors is
 * invalid, it will set the grid neighbors to null. However, I will change this to 
 * throw an error**
 * 
 * This class depends on the ResourceBundleHandler to manage retrieving data from the 
 * resource bundles.  This class also depends on the GridType, RuleType, and NeighborType enums
 * in order to specify the possible valid grid, rule, and neighbor types
 * 
 * An example:
 * GridSettings gridSettings = new GridSettings(new Dimension(2,3), "hexagonal", "wator", "edges");
 * gridSettings.getGridType() // Returns the enum associated with "hexagonal"!
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
			throw new RuleTypeException(fErrorRBHandler.getResource("InvalidRule"), aGridRule);
		}
	}
	//XXX: want to switch on the strings from resource bundle, but this doesn't seem to be possible
	/**
	 * 
	 * A method used to set the GridType field based on the input aGridType string
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
				throw new GridTypeException(fErrorRBHandler.getResource("InvalidGridType"), aGridType);
		}
	}
	/**
	 * 
	 * A method used to set the NeighborType field based on the input aGridNeighbors string
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
			//XXX: Throw error here instead
			fGridType = null;
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
