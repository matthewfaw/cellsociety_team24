package models.rules;

import java.util.Map;
import java.util.ResourceBundle;

import models.grid.GridModel;

/**
 * 
 * @author Weston and Matthew
 *
 */
abstract public class Rule {
	private Map<String, Integer> fStateIds;

	private static final String RESOURCE_PATH = "resources/State";
	private ResourceBundle fStateNameRB;
	
	public Rule(Map<String, Integer> aStateIdsMap)
	{
		fStateIds = aStateIdsMap;

		//XXX: this will be removed soon
		fStateNameRB = ResourceBundle.getBundle(RESOURCE_PATH);
		
	}
	
	//XXX: This will be changed soon-->Want to extract all the resource bundle stuff to it's own class
	protected int getStateId(String aStateName)
	{
		String aResourceName = fStateNameRB.getString(aStateName);
		return fStateIds.get(aResourceName);
	}
	
	/**
	 * Calculates the next state of a cell given the current state. 
	 * @param c The cell to find the next state for.
	 * @return The cell's intended next state.
	 */
	abstract public void calculateAndSetNextStates(GridModel grid);
	
	
}
