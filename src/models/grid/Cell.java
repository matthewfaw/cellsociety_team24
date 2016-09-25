package models.grid;

import models.Point;

public class Cell {
	private Point myLocation;
	private CellState myCurrState;
	private CellState myNextState;

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
	
	public CellState getState(){
		return myCurrState;
	}
	
	public int getState(int i){
		return myCurrState.getState(i);
	}
	public void setNextState(int val, int i){
		if (myNextState == null)
			myNextState = myCurrState.copy();
		myNextState.setState(val, i);

	}
	
	public Point getLocation(){
		return myLocation;
	}
	
}
	
