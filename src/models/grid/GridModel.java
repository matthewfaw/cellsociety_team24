package models.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import models.Point;
import models.rules.Rule;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A class to contain all the cells, hold them in spatial relation to each other, and use the rules to update them as
 * the simulation requires.
 * @author Weston
 *
 */
public class GridModel implements Iterable<Cell>{	
	private Cell[][] myGrid;
	private Rule myRules;
	private int myCellSides;
	private int myTicks;
	private Map<Integer, Map<String, Double>> myStateInfo;
	
	//NOTE: there is no public/private/protected qualifier in front of this constructor, because
	// this constructor should only be called from GridFactory
	GridModel(Collection<Cell> cells, Dimension dimension, Rule rule, int cellSides, Map<Integer, Map<String, Double>> aStateInfo){
		myGrid = constructGrid(cells, dimension);
		myRules = rule;
		myCellSides = cellSides;
		myTicks = 0;
		
		myStateInfo = aStateInfo;
	}
	
	/**
	 * A method to randomly change the state of a cell
	 * @param aCell
	 */
	public void randomlyChangeCellState(Cell aCell)
	{
		int randomStateId = getRandomStateId();
		
		CellState newState = new CellState(randomStateId, myStateInfo.get(randomStateId));
		aCell.setCurrentState(newState);
	}
	
	public double getParameter()
	{
		return myRules.getParameter();
	}
	public void updateParameter(double aPercentage)
	{
		myRules.updateParameter(aPercentage);
	}
	
	private int getRandomStateId()
	{
		Random random = new Random();
		int randomIndex = random.nextInt(myStateInfo.keySet().size());
		
		ArrayList<Integer> stateIds = new ArrayList<Integer>(myStateInfo.keySet());
		return stateIds.get(randomIndex);
	}

	/**
	 * 
	 * @param cells
	 * @param dimension
	 * @return a 2D array that contains the cells, organized by their location.
	 */
	private Cell[][] constructGrid(Iterable<Cell> cells, Dimension dimension){
		myGrid = new Cell[(int) dimension.getWidth()][(int) dimension.getHeight()];
		
		for (Cell c: cells) {
			int row = c.getLocation().getX();
			int col = c.getLocation().getY();
			myGrid[row][col] = c;
		}
		
		return myGrid;
	}

	/**
	 * Calculate the next state of each cell in the grid, then update all their states.
	 * @return 
	 */
	public void nextTick(){
		calculateAllNextStates();
		
		for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				myGrid[i][j].tick();
			}
		}
		myTicks++;
	}

	/**
	 * Calculate a next state for each cell using the given rules
	 */
	private void calculateAllNextStates() {
		myRules.calculateAndSetNextStates(this);
	}
	
	/**
	 * @return A collection of all the cells contained on the grid.
	 */
    public Collection<Cell> getAllCells(){
        ArrayList<Cell> result = new ArrayList<Cell>(); 
        
        for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				result.add(myGrid[i][j]);
			}
		}
        return result;
    }
	/**
	 * Should no longer be needed
	 * @return the number of sides each cell has
	 */
	public int getCellSides(){
		return myCellSides;
	}
	
	/**
	 * @return number of ticks elapsed since the start of the simulation.
	 */
	public int getTick(){
		return myTicks;
	}
	
	/**
	 * Not yet implemented. 
	 * @return a map from cell state IDs to that state's percentage on the grid.
	 */
	public Map<Integer, Double> percentages(){
		throw new NotImplementedException();
	}

	/**
	 * An iterator so that a for each loop can be called on GridModel
	 * @author Weston
	 *
	 */
	private class GridIterator implements Iterator<Cell>{
		Cell[][] myGrid;
		
		int currX;
		int currY;
		
		int maxX;
		int maxY;
		
		public GridIterator(Cell[][] grid){
			myGrid = grid;
			
			maxX = grid.length;
			maxY = grid[0].length;
			
			currX = 0;
			currY = 0;
		}
		
		private void updateCurrent(){
			currX++;
			
			if (currX >= maxX){
				currX = 0;
				currY++;
			}
		}

		@Override
		public boolean hasNext() {
			return (currX < maxX && currY < maxY);
		}

		@Override
		public Cell next() {
			if (hasNext()){
				int x = currX;
				int y = currY;
				
				updateCurrent();
				
				return myGrid[x][y];
			}
			return null;

		}
		
	}
	
	@Override
	public Iterator<Cell> iterator() {
		return new GridIterator(myGrid);
	}
	
	/**
	 * Converts an array of Points points to an array of cells. Entries may be null if the Points are not on the grid.
	 * @param points
	 * @return array of cells
	 */
	private Cell[] pointsToCells(Point[] points){
		Cell[] result = new Cell[points.length];
		
		for (int i = 0; i < points.length; i++){
			result[i] = getCell(points[i]);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param c
	 * @return the cells that occupy the spaces Cell c says are it's neighbors
	 */
	public Cell[] getNeighbors(Cell c){
		return pointsToCells(c.getNeighbors());
	}
	
	/**
	 * 
	 * @param c
	 * @param angleStart
	 * @param angleRange
	 * @return the cells that occupy the spaces Cell c says are it's neighbors in a direction towards angleStart within an arc of size 2*angleRange.
	 */
	public Cell[] getDirectedNeighbors(Cell c, double angleStart, double angleRange){
		return pointsToCells(c.getDirectedNeighbors(angleStart, angleRange));
	}
	
	/**
	 * 
	 * @param c
	 * @param angle
	 * @return the cells that occupy the space Cell c says are is its neighbor in a direction closest to angle
	 */
	public Cell getDirectedNeighbor(Cell c, double angle){
		return getCell(c.getDirectedNeighbor(angle));
	}
	
	/**
	 * 
	 * @param p
	 * @return true iff Point p falls inside the grid
	 */
	public boolean inGrid(Point p){
		return (
				p.getX() >= 0 &&
				p.getY() >= 0 &&
				p.getX() < myGrid.length &&
				p.getY() < myGrid[0].length
				);			
	}
	
	/**
	 * @param p
	 * @return the cell at Point p if p is in the grid, or null otherwise.
	 */
	public Cell getCell(Point p){
		if (inGrid(p)){
			return myGrid[p.getX()][p.getY()];
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return the grid's dimensions
	 */
	public Point getDimensions(){
		return new Point(myGrid.length, myGrid[0].length);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return the cell at point (x, y)
	 */
	public Cell getCell(int x, int y){
		return getCell(new Point(x, y));
	}
}
