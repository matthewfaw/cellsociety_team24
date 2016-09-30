package models.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import models.Point;
import models.rules.Rule;

public class GridModel implements Iterable<Cell>{
	private static final Random fRandomNumberGenerator = new Random();
	
	private Cell[][] myGrid;
	private Rule myRules;
	private int myCellSides;
	
	//NOTE: there is no public/private/protected qualifier in front of this constructor, because
	// this constructor should only constructed from GridFactory
	GridModel(Collection<Cell> aCellCollection, Dimension aGridDimensions, Rule aRule, int aNumberOfCellSides)
	{
		myGrid = constructGrid(aCellCollection, aGridDimensions);
		myRules = aRule;
		myCellSides = aNumberOfCellSides;
	}
	
	private Cell[][] constructGrid(Iterable<Cell> aCellCollection, Dimension aGridDimensions)
	{
		myGrid = new Cell[(int) aGridDimensions.getWidth()][(int) aGridDimensions.getHeight()];
		
		for (Cell cell: aCellCollection) {
			int row = cell.getLocation().getX();
			int col = cell.getLocation().getY();
			myGrid[row][col] = cell;
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
	}

	/**
	 * Calculate a next state for each cell using the given rules
	 */
	private void calculateAllNextStates() {
		myRules.calculateAndSetNextStates(this);
	}
	
    public Collection<Cell> getAllCells(){
        ArrayList<Cell> result = new ArrayList<Cell>(); 
        
        for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				result.add(myGrid[i][j]);
			}
		}
        return result;
    }
	
	public int getCellSides(){
		return myCellSides;
	}

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
	
	
	public Cell[] getNeighbors(Cell c){
		switch (myCellSides){
			case 3:
				return triangleNeighbors(c);
			case 4:
				return squareNeighbors(c);
			case 6:
				return hexagonalNeighbors(c);
			default:
				return squareNeighbors(c);
		}
	}
	
	public Cell[] getAdjAndDiagNeighbors(Cell c){
		switch (myCellSides){
			case 3:
				return triangleAllNeighbors(c);
			case 4:
				return squareAllNeighbors(c);
			case 6:
				return hexagonalNeighbors(c);
			default:
				return squareAllNeighbors(c);
		}
	}
	
	private Cell[] hexagonalNeighbors(Cell c) {
		Point p = c.getLocation();
		int x = p.getX();
		int y = p.getY();

		return new Cell[] {
				getCell(x + 2, y),
				getCell(x + 1, y),
				getCell(x - 1, y),
				getCell(x - 2, y),
				getCell(x - 1, y - 1),
				getCell(x + 1, y - 1),
			
		};
	}


	private Cell[] squareNeighbors(Cell c) {
		Point p = c.getLocation();
		int x = p.getX();
		int y = p.getY();

		return new Cell[] {
				getCell(x, y + 1),
				getCell(x + 1, y),
				getCell(x, y - 1),
				getCell(x - 1, y),
		};
	}


	private Cell[] triangleNeighbors(Cell c) {
		Point p = c.getLocation();
		int x = p.getX();
		int y = p.getY();

		if (x % 2 == 0){
			//Triangle pointing up
			return new Cell[] {
					getCell(x, y + 1),
					getCell(x, y - 1),
					getCell(x + 1, y + 1),
			};
		} else {
			//Triangle pointing down
			return new Cell[] {
					getCell(x , y + 1),
					getCell(x + 1, y - 1),
					getCell(x , y - 1),
			};
		}
	}
	
	private Cell[] squareAllNeighbors(Cell c) {
		Point p = c.getLocation();
		int x = p.getX();
		int y = p.getY();

		return new Cell[] {
				getCell(x, y + 1),
				getCell(x + 1, y + 1),
				getCell(x + 1, y),
				getCell(x + 1, y -1),
				getCell(x, y - 1),
				getCell(x - 1, y - 1),
				getCell(x - 1, y),
				getCell(x - 1, y + 1),
		};
	}
	
	private Cell[] triangleAllNeighbors(Cell c) {
		Point p = c.getLocation();
		int x = p.getX();
		int y = p.getY();

		if (x % 2 == 0){
			//Triangle pointing up
			return new Cell[] {
					getCell(x, y + 3),
					getCell(x, y + 1),
					getCell(x + 1, y),
					getCell(x, y - 1),
					getCell(x - 1, y),
					getCell(x - 1, y + 1),

			};
		} else {
			//Triangle pointing down
			return new Cell[] {
					getCell(x, y + 1),
					getCell(x + 1, y + 1),
					getCell(x + 1, y - 1),
					getCell(x, y - 3),
					getCell(x - 1, y - 1),
					getCell(x - 1, y),
			};
		}
	}
	
	public boolean inGrid(Point p){
		return (
				p.getX() >= 0 &&
				p.getY() >= 0 &&
				p.getX() < myGrid.length &&
				p.getY() < myGrid[0].length
				);			
	}
	public Cell getCell(Point p){
		if (inGrid(p)){
			return myGrid[p.getX()][p.getY()];
		} else {
			return null;
		}
	}
	
	public Point getDimensions(){
		return new Point(myGrid.length, myGrid[0].length);
	}
	
	public Cell getCell(int x, int y){
		return getCell(new Point(x, y));
	}

}
