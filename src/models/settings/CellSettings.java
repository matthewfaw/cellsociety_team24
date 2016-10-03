package models.settings;

import java.util.HashMap;
import java.util.Map;

public class CellSettings {
	private HashMap<String, Integer> fStateIds;
	private HashMap<Integer, Map<String, Double>> fProperties;
//	private HashMap<Integer, Map<String, Double>> fDefaults;
	private HashMap<String, Double> fDefaults;
	private HashMap<Integer, String> fNeighborhoodName;
	
	public CellSettings()
	{
		fProperties = new HashMap<Integer, Map<String, Double>>();
		fDefaults = new HashMap<String, Double>();
		fStateIds = new HashMap<String, Integer>();
		fNeighborhoodName = new HashMap<Integer, String>();
	}
	
	public void addProperty(int aStateIndex, String aKey, double aValue)
	{
		addItemToMap(fProperties, aStateIndex, aKey, aValue);
	}
	public void addProperties(int aStateIndex, Map<String, Double> aPropertyMap)
	{
		fProperties.put(aStateIndex, aPropertyMap);
	}
	
	public void addDefault(String aKey, double aValue)
	{
		fDefaults.put(aKey, aValue);
	}
	public Map<String, Double> getDefaults()
	{
		return fDefaults;
	}
	
//	public void addDefault(int aStateIndex, String aKey, double aValue)
//	{
//		addItemToMap(fDefaults, aStateIndex, aKey, aValue);
//	}
//	public void addDefaults(int aStateIndex, Map<String, Double> aDefaultsMap)
//	{
//		fDefaults.put(aStateIndex, aDefaultsMap);
//	}
//	public Map<Integer, Map<String, Double>> getDefaults()
//	{
//		return fDefaults;
//	}
	
	private void addItemToMap(Map<Integer, Map<String, Double>> aMap, int aStateIndex, String aKey, double aValue)
	{
		if (!aMap.containsKey(aStateIndex)) {
			aMap.put(aStateIndex, new HashMap<String,Double>());
		}
		aMap.get(aStateIndex).put(aKey, aValue);
	}
	
	public Map<String, Double> getProperties(int aStateIndex)
	{
		return fProperties.get(aStateIndex);
	}
	public Map<Integer, Map<String, Double>> getAllProperties()
	{
		return fProperties;
	}
	
	public Map<String, Integer> getStateNames()
	{
		return fStateIds;
	}
	
	public void setStateName(String aStateName, int aStateId)
	{
		fStateIds.put(aStateName, aStateId);
	}

	public void setNeighborhood(int aStateIndex, String aNeighborhoodName)
	{
		fNeighborhoodName.put(aStateIndex, aNeighborhoodName);
	}

}
