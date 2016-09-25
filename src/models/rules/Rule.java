package models.rules;

import models.Point;
import models.grid.Cell;

abstract public class Rule {
	
	/**
	 * Calculates the next state of a cell given the current state. 
	 * @param c The cell to find the next state for.
	 * @return The cell's intended next state.
	 */
	abstract public void calculateAndSetNextStates(Cell[][] grid, int gridShape);
	
	protected Point[] getNeighbors(Point p, int gridShape){
		switch (gridShape){
			case 3:
				return triangleNeighbors(p);
			case 4:
				return squareNeighbors(p);
			case 6:
				return hexagonalNeighbors(p);
			default:
				return squareNeighbors(p);
		}
	}
	
	private Point[] hexagonalNeighbors(Point p) {
		int x = p.getX();
		int y = p.getY();

		return new Point[] {
				new Point(x + 2, y),
				new Point(x + 1, y),
				new Point(x - 1, y),
				new Point(x - 2, y),
				new Point(x - 1, y - 1),
				new Point(x + 1, y - 1),
			
		};
	}


	private Point[] squareNeighbors(Point p) {
		int x = p.getX();
		int y = p.getY();

		return new Point[] {
				new Point(x, y + 1),
				new Point(x + 1, y),
				new Point(x, y - 1),
				new Point(x - 1, y),
		};
	}


	private Point[] triangleNeighbors(Point p) {
		int x = p.getX();
		int y = p.getY();

		if (x % 2 == 0){
			//Triangle pointing up
			return new Point[] {
					new Point(x + 1, y + 1),
					new Point(x - 1, y),
					new Point(x + 1, y)
			};
		} else {
			//Triangle pointing down
			return new Point[] {
					new Point(x , y + 1),
					new Point(x + 1, y - 1),
					new Point(x , y - 1)
			};
		}
	}
	
	protected boolean inGrid(Point p, Cell[][] grid){
		return (
				p.getX() >= 0 &&
				p.getY() >= 0 &&
				p.getX() < grid.length &&
				p.getY() < grid[0].length
				);			
	}
	protected Cell getCell(Point p, Cell[][] grid){
		if (inGrid(p, grid)){
			return grid[p.getX()][p.getY()];
		} else {
			return null;
		}
	}
}
