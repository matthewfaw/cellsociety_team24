package models.grid;

import java.util.Map;

import models.Point;

abstract public class Cell {
	private Point myLocation;
	private CellState myCurrState;
	private CellState myNextState;
	
	public Cell (int x, int y, int stateId, Map<String, Double> propertyMap){
		myLocation = new Point(x, y);
		myCurrState = new CellState(stateId, propertyMap);
	}

	/**
	 * Moves the cell to its next state. Next state must be precalculated (by the grid).
	 */
	public void tick(){
		if (myNextState != null){
			myCurrState = myNextState;
			myNextState = null;
		}
	}
	
	/**
	 * @param nextState the state that the cell will switch to on the next call of tick
	 */
	public void setNextState(CellState nextState){
		myNextState = nextState;
	}
	
	/**
	 * @return the cell's current state object
	 */
	public CellState getState(){
		return myCurrState;
	}
	
	/**
	 * @param key
	 * @return the cell's current value for the attribute key
	 */
	public double getState(String key){
		return myCurrState.getStateAttrib(key);
	}
	/**
	 * 
	 * @return the cells current state ID
	 */
	public int getStateID(){
		return myCurrState.getStateID();
	}
	
	/**
	 * Sets the nextState's ID to be val.
	 * If nextState didn't previously exist, it becomes a copy of the current state first.
	 * @param val
	 */
	public void setNextStateAttrib(int val){
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.setStateID(val);
	}
	/**
	 * Sets the nextState's attribute key to be val.
	 * If nextState didn't previously exist, it becomes a copy of the current state first.
	 * @param val
	 * @param key
	 */
	public void setNextStateAttrib(int val, String key){
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.setStateAtttrib(val, key);
	}
	
	
	/**
	 * @return the cell's nextState, or current state if no nextState exists.
	 */
	public CellState getNextState(){
		if (myNextState != null)
			return myNextState;
		return myCurrState;
	}
	
	/**
	 * 
	 * @param key
	 * @return the value for the attribute key held in the cell's nextState, or in the current state if no nextState exists.
	 */
	public double getNextState(String key){
		if (myNextState != null)
			return myNextState.getStateAttrib(key);
		return myCurrState.getStateAttrib(key);
	}
	
	/**
	 * @return the stateID  held in the cell's nextState, or in the current state if no nextState exists.
	 */
	public int getNextStateID(){
		if (myNextState != null)
			return myNextState.getStateID();
		return myCurrState.getStateID();
	}

	/**
	 * Decrements the attribute key by 1.
	 * @param key
	 */
	public void decrement(String key) {
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.decrement(key);
	}
	
	/**
	 * Increments the attribute key by 1.
	 * @param key
	 */
	public void increment(String key){
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.increment(key);
	}
	
	/**
	 * Decrements the attribute key by val.
	 * @param key
	 * @param val
	 */
	public void decrement(String key, double val) {
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.decrement(key, val);
	}
	
	/**
	 * Increments the attribute key by val.
	 * @param key
	 * @param val
	 */
	public void increment(String key, double val){
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.increment(key, val);
	}
	
	/**
	 * @return a Point describing the cell's location.
	 */
	public Point getLocation(){
		return myLocation;
	}
	
	/**
	 * @return An array of points containing locations of neighbors of the cell, in clockwise order, starting at vertical.
	 */
	abstract public Point[] getNeighbors();
	
	/**
	 * @param angle
	 * @return A point containing the location of the neighbor nearest to angle degrees. Vertical is 0 degrees.
	 */
	abstract public Point getDirectedNeighbor(double angle);
	
	/**
	 * @param angleStart
	 * @param angleRange
	 * @return An array of points that are the locations of neighboring cells that are within angleStart +- angleRange degree arc. Vertical is 0 degrees.
	 */
	abstract public Point[] getDirectedNeighbors(double angleStart, double angleRange);

}
	
