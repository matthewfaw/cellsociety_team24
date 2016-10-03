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

public class GridSettings {
	private static final String CELL_PATH = "resources/CellSides";
	private static final String RULE_PATH = "resources/Rules";
	private static final String ERROR_PATH = "resources/ErrorMessages";

	private Dimension fDimension;
	private GridType fGridType;
	private RuleType fGridRule;
	private NeighborType fNeighborType;
	private int fCellSides;
	private HashMap<Integer, Integer> fStatePercentages;
//	private HashMap<String, Double> fSimulationProperties;
	private ResourceBundleHandler fCellSidesRBHandler;
	private ResourceBundleHandler fRuleTypeRBHandler;
	private ResourceBundleHandler fErrorRBHandler;
	
	public GridSettings(Dimension aDimension, String aGridType, String aGridRules, String aGridNeighbors)
	{
		fDimension = aDimension;
		configureGridType(aGridType);
		configureGridRules(aGridRules);
		configureGridNeighbors(aGridNeighbors);
		
		fStatePercentages = new HashMap<Integer, Integer>();
//		fSimulationProperties = new HashMap<String, Double>();
		
		fCellSidesRBHandler = new ResourceBundleHandler(CELL_PATH);
		fRuleTypeRBHandler = new ResourceBundleHandler(RULE_PATH);
		fErrorRBHandler = new ResourceBundleHandler(ERROR_PATH);
	}
	
	public GridType getGridType()
	{
		return fGridType;
	}
	public RuleType getRuleType()
	{
		return fGridRule;
	}
	public NeighborType getNeighborType()
	{
		return fNeighborType;
	}
	
	//XXX: want to switch on the strings from resource bundle, but this doesn't seem to be possible
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
	private void configureGridNeighbors(String aGridNeighbors)
	{
		switch(aGridNeighbors) {
		case "vertices":
			fNeighborType = NeighborType.Vertices;
			break;
		case "edges":
			fNeighborType = NeighborType.Edges;
			break;
		case "duvall":
			fNeighborType = NeighborType.Duvall;
			break;
		default:
			fGridType = null;
		}
	}
	
	public Dimension getDimensions()
	{
		return fDimension;
	}
	
	public int getTotalNumberOfCells()
	{
		return (int)(fDimension.getHeight() * fDimension.getWidth());
	}
	
	//TODO: Change this to an abstract method which
	// subclasses define
	public int getNumberOfCellSides()
	{
		return fCellSides;
	}
	public void addPercentage(int aStateId, int aPercentage)
	{
		fStatePercentages.put(aStateId, aPercentage);
	}
	public HashMap<Integer, Integer> getStatePercentages()
	{
		return fStatePercentages;
	}
	
//	public void addProperty(String aProperty, double aDefaultValue)
//	{
//		fSimulationProperties.put(aProperty, aDefaultValue);
//	}
//	public HashMap<String, Double> getSimulationProperties()
//	{
//		return fSimulationProperties;
//	}
}
