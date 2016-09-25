package models.grid;

import java.util.Map;
import java.util.TreeMap;

public class CellState {
	private int myState;
	private Map<String, Integer> myAttribs;
	
	public CellState(int state, Map<String, Integer> attribs) {
		myState = state;
		myAttribs = attribs;
	}
	
	public int getStateID(){
		return myState;
	}

	public int getStateAttrib(String key){
		return myAttribs.get(key);
	}
	
	public void setStateAtttrib(int val, String key){
		if (myAttribs.containsKey(key))
			addStateAttrib(val, key);
	}
	
	public void addStateAttrib(int val, String key){
			myAttribs.put(key, val);
	}
	
	public void increment(String key){
		if (myAttribs.containsKey(key)){
			myAttribs.put(key, myAttribs.get(key) + 1);
		} else
			throw new IllegalArgumentException("No such key in attribute map.");
	}

	public CellState clone() {
		return new CellState(myState, new TreeMap<String, Integer>(myAttribs));
	}
	
}
