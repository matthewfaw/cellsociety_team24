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
		myCurrState = myNextState;
		myNextState = null;
	}
	
	/**
	 * @param nextState the state that the cell will switch to on the next call of tick
	 */
	public void setState(CellState nextState){
		myNextState = nextState;
	}
	
	public CellState getState(){
		return myCurrState;
	}
	
	public int getState(int i){
		return myCurrState.getState(i);
	}
	public void setState(int val, int i){
		myCurrState.setState(val, i);;
	}
	
	public Point getLocation(){
		return myLocation;
	}
	
}
	
