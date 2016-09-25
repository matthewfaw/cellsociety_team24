package models.settings;

import java.util.HashMap;

public class GridSettings {
	private int fWidth;
	private int fHeight;
	private String fGridType;
	private String fGridRules;
	private HashMap<Integer, Double> fStatePercentages;
	
	public GridSettings(int aWidth, int aHeight, String aGridType, String aGridRules)
	{
		fWidth = aWidth;
		fHeight = aHeight;
		fGridType = aGridType;
		fGridRules = aGridRules;
		
		fStatePercentages = new HashMap<Integer, Double>();
	}
	
	public void addPercentage(int aStateId, double aPercentage)
	{
		fStatePercentages.put(aStateId, aPercentage);
	}
}
