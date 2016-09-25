package models.settings;

import java.util.HashMap;

public class CellSettings {
	private HashMap<Integer, HashMap<String, Double>> fProperties;
	private HashMap<Integer, String> fRuleName;
	private HashMap<Integer, String> fNeighborhoodName;
	
	public CellSettings()
	{
		fProperties = new HashMap<Integer, HashMap<String, Double>>();
		fRuleName = new HashMap<Integer, String>();
		fNeighborhoodName = new HashMap<Integer, String>();
	}
	
	public void addProperty(int aStateIndex, String aKey, Double aValue)
	{
		if (!fProperties.containsKey(aStateIndex)) {
			fProperties.put(aStateIndex, new HashMap<String,Double>());
		}
		fProperties.get(aStateIndex).put(aKey, aValue);
	}
	
	public void setRule(int aStateIndex, String aRuleName)
	{
		fRuleName.put(aStateIndex, aRuleName);
	}
	public void setNeighborhood(int aStateIndex, String aNeighborhoodName)
	{
		fNeighborhoodName.put(aStateIndex, aNeighborhoodName);
	}

}
