package models.settings;

import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to encapsulate the data concerning Cell settings
 * specified in XML.
 * 
 * This class should not fail, since it is essentially a wrapper around several Maps.
 * However, an implicit assumption made by the fDefaults map is that each string input
 * will be unique.  If two defaults have the same name, only one of them will be preserved
 * in the map.
 * 
 * To add a Map of properties propertyMap corresponding to a state id stateId:
 * CellSettings s = new CellSettings();
 * s.addProperties(stateId, propertyMap);
 * 
 * @author matthewfaw
 *
 */
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
	
	/**
	 * A method to add a property to the property map
	 * @param aStateIndex
	 * @param aKey
	 * @param aValue
	 */
	public void addProperty(int aStateIndex, String aKey, double aValue)
	{
		addItemToMap(fProperties, aStateIndex, aKey, aValue);
	}
	/**
	 * A method to add a map of properties to be associated with aStateIndex
	 * @param aStateIndex
	 * @param aPropertyMap
	 */
	public void addProperties(int aStateIndex, Map<String, Double> aPropertyMap)
	{
		fProperties.put(aStateIndex, aPropertyMap);
	}
	
	/**
	 * A method to add a simulation default value to the state map
	 * @param aKey: a default name
	 * @param aValue: a default value
	 */
	public void addDefault(String aKey, double aValue)
	{
		fDefaults.put(aKey, aValue);
	}
	/**
	 * A method to retrieve the map of simulation defaults
	 * @return
	 */
	public Map<String, Double> getDefaults()
	{
		return fDefaults;
	}
	
	private void addItemToMap(Map<Integer, Map<String, Double>> aMap, int aStateIndex, String aKey, double aValue)
	{
		if (!aMap.containsKey(aStateIndex)) {
			aMap.put(aStateIndex, new HashMap<String,Double>());
		}
		aMap.get(aStateIndex).put(aKey, aValue);
	}
	
	/**
	 * A method to retrieve the property map associated with a state id
	 * @param aStateIndex
	 * @return map of properties associated with aStateIndex
	 */
	public Map<String, Double> getProperties(int aStateIndex)
	{
		return fProperties.get(aStateIndex);
	}
	/**
	 * @return Retrieve the property map for all state ids
	 */
	public Map<Integer, Map<String, Double>> getAllProperties()
	{
		return fProperties;
	}
	
	/**
	 * @return map from state name to state ids
	 */
	public Map<String, Integer> getStateNames()
	{
		return fStateIds;
	}
	
	/**
	 * Add a new entry to the state names map
	 * @param aStateName
	 * @param aStateId
	 */
	public void setStateName(String aStateName, int aStateId)
	{
		fStateIds.put(aStateName, aStateId);
	}

	/**
	 * add a new entry to the neighborhood map
	 * @param aStateIndex
	 * @param aNeighborhoodName: the type of neighborhood associated with a state
	 */
	public void setNeighborhood(int aStateIndex, String aNeighborhoodName)
	{
		fNeighborhoodName.put(aStateIndex, aNeighborhoodName);
	}
}
