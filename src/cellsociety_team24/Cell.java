package cellsociety_team24;

import javafx.scene.shape.Polygon;

public abstract class Cell extends Polygon {
	private CellState myCurrState;
	private CellState myNextState;
	
	
	/**
	 * @return the number of sides (and possible neighbors) this cell has
	 */
	abstract public int getSides();
	
	/**
	 * Points are returned in clockwise order, staring at 12 o'clock.
	 * @return an array of locations that could contain this cell's neighbors (They might not be occupied on the grid)
	 */
	abstract public Point[] getNeighbors();
	
	/**
	 * Makes the Polygon with getSides() sides, centered (by area) on Point p
	 * @param p Point to center Polygon around
	 */
	abstract public void remakePoly(Point p);
	
	/**
	 * Moves the cell to its next state. Next state must be precalculated.
	 */
	public void tick(){
		myCurrState = myNextState;
		myNextState = null;
		
		//Update drawing based on new state
		setFill(myCurrState.getColor());
		remakePoly(myCurrState.getLocation());
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
	
	
}
	
