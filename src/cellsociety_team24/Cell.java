package cellsociety_team24;

public abstract class Cell {
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
	 * Gets the vertices of the polygon, if it were centered around p with sidelength sides.
	 * @param p Point to center Polygon around
	 * @param sides Length of each side
	 * @return an array of doubles (each pair representing an vertex) in clockwise order starting at 12 o'clock.
 	 */
	abstract public double[] vertices(Point p, double sides);
	
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
		return getState();
	}
	
	abstract public double getWidth();

	abstract public double getHeight();
	
	
}
	
