package models.grid;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holds a cell's simulation information for one moment of the simulation.
 * @author Weston
 *
 */
public class CellState {
	private int myState;
	private Map<String, Double> myAttribs;
	
	public CellState(int state, Map<String, Double> attribs) {
		myState = state;
		myAttribs = attribs;
	}
	
	/**
	 * 
	 * @return State ID
	 */
	public int getStateID(){
		return myState;
	}

	/**
	 * 
	 * @param key
	 * @return the value held for the attribute key.
	 */
	public double getStateAttrib(String key){
		return myAttribs.get(key);
	}
	/**
	 * Sets the state ID to val.
	 * @param val
	 */
	public void setStateID(int val){
		myState = val;
	}
	
	 /**
	 * Sets the value held for the attribute key to val.
	  * @param val
	  * @param key
	  */
	public void setStateAtttrib(double val, String key){
		if (myAttribs.containsKey(key))
			addStateAttrib(val, key);
	}
	
	/**
	 * Adds a new value to the CellState, with key key and value val.
	 * @param val
	 * @param key
	 */
	public void addStateAttrib(double val, String key){
			myAttribs.put(key, val);
	}
	
	/**
	 * Makes a deep copy of the CellState object.
	 */
	@Override
	public CellState clone() {
		return new CellState(myState, new TreeMap<String, Double>(myAttribs));
	}
	
	/**
	 * Increments the value held for key by 1.
	 * @param key
	 */
	public void increment(String key){
		increment(key, 1);
	}

	/**
	 * Decrements the value held for key by 1.
	 * @param key
	 */
	public void decrement(String key) {
		decrement(key, 1);
	}

	/**
	 * Increments the value held for key by val.
	 * @param key
	 * @param val
	 */
	public void increment(String key, double val){
		if (myAttribs.containsKey(key)){
			myAttribs.put(key, myAttribs.get(key) + val);
		} else
			throw new IllegalArgumentException("No such key in attribute map.");
	}
	/**
	 * Decrements the value held for key by val.
	 * @param key
	 * @param val
	 */
	public void decrement(String key, double val){
		if (myAttribs.containsKey(key)){
			myAttribs.put(key, myAttribs.get(key) - val);
		} else
			throw new IllegalArgumentException("No such key in attribute map.");
	}
	
}
