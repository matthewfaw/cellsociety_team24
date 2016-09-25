package models.settings;

import java.util.HashMap;

public class CellSettings {
	private HashMap<Integer, HashMap<String, Integer>> fProperties;
	private HashMap<Integer, String> fRuleName;
	private HashMap<Integer, String> fNeighborhoodName;
	
	public CellSettings()
	{
		fProperties = new HashMap<Integer, HashMap<String, Integer>>();
		fRuleName = new HashMap<Integer, String>();
		fNeighborhoodName = new HashMap<Integer, String>();
	}
	
	public void addProperty(int aStateIndex, String aKey, int aValue)
	{
		if (!fProperties.containsKey(aStateIndex)) {
			fProperties.put(aStateIndex, new HashMap<String,Integer>());
		}
		fProperties.get(aStateIndex).put(aKey, aValue);
	}
	public HashMap<String, Integer> getProperties(int aStateIndex)
	{
		return fProperties.get(aStateIndex);
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
