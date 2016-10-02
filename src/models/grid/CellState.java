package models.grid;

import java.util.Map;
import java.util.TreeMap;

public class CellState {
	private int myState;
	private Map<String, Double> myAttribs;
	
	public CellState(int state, Map<String, Double> attribs) {
		myState = state;
		myAttribs = attribs;
	}
	
	public int getStateID(){
		return myState;
	}

	public double getStateAttrib(String key){
		return myAttribs.get(key);
	}
	public void setStateID(int val){
		myState = val;
	}
	
	public void setStateAtttrib(double val, String key){
		if (myAttribs.containsKey(key))
			addStateAttrib(val, key);
	}
	
	public void addStateAttrib(double val, String key){
			myAttribs.put(key, val);
	}
	
	public void increment(String key){
		increment(key, 1);
	}

	public CellState clone() {
		return new CellState(myState, new TreeMap<String, Double>(myAttribs));
	}

	public void decrement(String key) {
		decrement(key, 1);
	}

	public void increment(String key, double val){
		if (myAttribs.containsKey(key)){
			myAttribs.put(key, myAttribs.get(key) + val);
		} else
			throw new IllegalArgumentException("No such key in attribute map.");
	}
	
	public void decrement(String key, double val){
		if (myAttribs.containsKey(key)){
			myAttribs.put(key, myAttribs.get(key) - val);
		} else
			throw new IllegalArgumentException("No such key in attribute map.");
	}
	
}
