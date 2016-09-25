package models.settings;

import java.awt.Dimension;
import java.util.HashMap;

public class GridSettings {
	private Dimension fDimension;
	private String fGridType;
	private String fGridRules;
	private HashMap<Integer, Integer> fStatePercentages;
	
	public GridSettings(Dimension aDimension, String aGridType, String aGridRules)
	{
		fDimension = aDimension;
		fGridType = aGridType;
		fGridRules = aGridRules;
		
		fStatePercentages = new HashMap<Integer, Integer>();
	}
	
	public Dimension getDimensions()
	{
		return fDimension;
	}
	
	public void addPercentage(int aStateId, int aPercentage)
	{
		fStatePercentages.put(aStateId, aPercentage);
	}
	public HashMap<Integer, Integer> getStatePercentages()
	{
		return fStatePercentages;
	}
}
