package models.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import models.Point;
import models.rules.Rule;

public class GridModel implements Iterable<Cell>{	
	private Cell[][] myGrid;
	private Rule myRules;
	private int myCellSides;
	
	//NOTE: there is no public/private/protected qualifier in front of this constructor, because
	// this constructor should only be called from GridFactory
	GridModel(Collection<Cell> cells, Dimension dimension, Rule rule, int cellSides)
	{
		myGrid = constructGrid(cells, dimension);
		myRules = rule;
		myCellSides = cellSides;
	}
	
	private Cell[][] constructGrid(Iterable<Cell> cells, Dimension dimension)
	{
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

	public Cell getDirectedNeighbor(Cell c, double angle) {
		switch (myCellSides){
		case 3:
			return getDirectedNeighborTriangle(c, angle);
		case 4:
			return getDirectedNeighborSquare(c, angle);
		case 6:
			return getDirectedNeighborHexagon(c, angle);
		default:
			return getDirectedNeighborSquare(c, angle);
			}
	}
	
	public Cell[] getDirectedNeighbors(Cell c, double angle, double range) {
		
		angle = (angle + 360) % 360;
		range = (range + 360) % 360;
		
		double lowerAngle = (angle - range) % 360;
		double upperAngle = (angle + range) % 360;
		
		switch (myCellSides){
		case 3:
			return getAllDirectedNeighborsTriangle(c, lowerAngle, upperAngle);
		case 4:
			return getAllDirectedNeighborsSquareOrHex(c, lowerAngle, upperAngle);
		case 6:
			return getAllDirectedNeighborsSquareOrHex(c, lowerAngle, upperAngle);
		default:
			return getAllDirectedNeighborsSquareOrHex(c, lowerAngle, upperAngle);
			}
	}

	private Cell getDirectedNeighborTriangle(Cell c, double angle) {
		angle = (angle + 360) % 360;
		int angleIndex;

		if (c.getLocation().getX() % 2 == 0){
			//Triangle pointing up
			angleIndex = (int) (angle + 45 / 90);
		} else {
			//Triangle pointing down
			angleIndex = (int) Math.round(angle / 90);
		}
		return triangleNeighbors(c)[angleIndex];
	}

	private Cell getDirectedNeighborSquare(Cell c, double angle) {
		angle = (angle + 360) % 360;
		int angleIndex = (int) Math.round(angle / 90);
		
		return squareNeighbors(c)[angleIndex];
	}
	
	private Cell getDirectedNeighborHexagon(Cell c, double angle) {
		angle = (angle + 360) % 360;
		int angleIndex = (int) Math.round(angle / 60);
		
		return hexagonalNeighbors(c)[angleIndex];
	}
	
	private Cell[] getAllDirectedNeighborsSquareOrHex(Cell c, double lowerAngle, double upperAngle){
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		double i = 0;
		for (Cell neighbor: getNeighbors(c)){
			double neighborAngle = (360 / myCellSides) * i;
			if ((lowerAngle <= neighborAngle && neighborAngle < upperAngle))
				result.add(neighbor);
			i++;
		}
		return result.toArray(new Cell[result.size()]);
	}
	
	private Cell[] getAllDirectedNeighborsTriangle(Cell c, double lowerAngle, double upperAngle){	
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		if ((c.getLocation().getX() + 2) % 2 != 0){
			lowerAngle += 360 / (myCellSides * 2);
			upperAngle += 360 / (myCellSides * 2);
		}
			
		double i = 0;
		for (Cell neighbor: getNeighbors(c)){
			double neighborAngle = (360 / myCellSides) * i;
			if ((lowerAngle <= neighborAngle && neighborAngle < upperAngle))
				result.add(neighbor);
			i++;
		}
		return result.toArray(new Cell[result.size()]);
	}
	
	
	
}
