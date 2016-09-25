package models.settings;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.ResourceBundle;

import models.rules.RuleType;
import resources.CellShapes;

public class GridSettings {
	private static final String RESOURCE_PATH = "resources/CellSides";

	private Dimension fDimension;
	private String fGridType;
	private RuleType fGridRule;
	private HashMap<Integer, Integer> fStatePercentages;
	private HashMap<String, Double> fSimulationProperties;
	
	public GridSettings(Dimension aDimension, String aGridType, String aGridRules)
	{
		fDimension = aDimension;
		fGridType = aGridType;
		fGridRule = setGridRules(aGridRules);
		
		fStatePercentages = new HashMap<Integer, Integer>();
		fSimulationProperties = new HashMap<String, Double>();
	}
	
	public RuleType getRuleType()
	{
		return fGridRule;
	}
	
	//TODO: Perhaps refactor this
	private RuleType setGridRules(String aGridRule)
	{
		switch(aGridRule) {
		case "Fire":
			return RuleType.Fire;
		case "Fish":
			return RuleType.Fish;
		case "Life":
			return RuleType.Life;
		case "Segregation":
			return RuleType.Segregation;
		default:
			return null;
		}
	}
	
	public Dimension getDimensions()
	{
		return fDimension;
	}
	//TODO: Change this to an abstract method which
	// subclasses define
	public int getNumberOfCellSides()
	{
		switch(fGridType) {
			case "rectangular":
				return CellShapes.RECTANGULAR;
			case "hexagonal":
				return CellShapes.HEXAGONAL;
			case "triangular":
				return CellShapes.TRIANGULAR;
			default:
				return CellShapes.DEFAULT;
		}
	}
	public void addPercentage(int aStateId, int aPercentage)
	{
		fStatePercentages.put(aStateId, aPercentage);
	}
	public HashMap<Integer, Integer> getStatePercentages()
	{
		return fStatePercentages;
	}
	
	public void addProperty(String aProperty, double aDefaultValue)
	{
		fSimulationProperties.put(aProperty, aDefaultValue);
	}
	public HashMap<String, Double> getSimulationProperties()
	{
		return fSimulationProperties;
	}
}
