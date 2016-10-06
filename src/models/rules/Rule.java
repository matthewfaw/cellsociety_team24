package models.rules;

import java.util.Map;
import java.util.ResourceBundle;

import models.grid.GridModel;

/**
 * An abstract class whose purpose is to define default some default behavior
 * for Rules, and to define the API to which each subclass of Rule must comply.
 * 
 * This class assumes that there is a valid resources/State.properties file,
 * and that it is constructed with a valid map from every state name to its 
 * corresponding state id number.
 * 
 * The main dependency of this class is on the ResourceBundle being valid, and
 * on the state id map
 * 
 * This class should not be constructed directly... Instead, it should be
 * constructed using the RuleFactory.
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
	/**
	 * A method used to retrieve the state id corresponding to the input state name
	 * This method assumes the input string is a valid state name
	 * @param aStateName
	 * @return state ID corresponding to aStateName
	 */
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
	public abstract void calculateAndSetNextStates(GridModel grid);
	
	/**
	 * A method used to update a simulation parameter when the parameter
	 * scrollbar is moved
	 * @param aPercentage
	 */
	public abstract void updateParameter(double aPercentage);
	
	/**
	 * A method to get the simulation parameter associated with the parameter scrollbar
	 * @return
	 */
	public abstract double getParameter();

	
	
}
