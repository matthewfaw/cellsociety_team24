package models.rules;

import models.Point;
import models.grid.Cell;
import models.grid.CellState;

public class RuleLife extends Rule {

	@Override
	public CellState[][] calculateNextStates(Cell[][] grid, int gridShape) {
		CellState[][] result = new CellState[grid.length][grid[0].length];
		
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				Point[] neighbors = getNeighbors(grid[i][j].getLocation(), gridShape);
				int livingNeighbors = 0;
				
				for (Point p: neighbors){
					Cell neighbor = getCell(p, grid);
					if (neighbor != null)
						livingNeighbors += neighbor.getState(0);
				}
				
				if (grid[i][j].getState(0) == 1){
					if (livingNeighbors == 2 || livingNeighbors == 3){
						result[i][j] = grid[i][j].getState();
					} else {
						result[i][j] = newDead();
					}
				} else {
					if (livingNeighbors == 3){
						result[i][j] = newLive();
					} else {
						result[i][j] = grid[i][j].getState();
					}
				}
				
			}
		}

		return result;
	}
	
	private CellState newLive(){
		return new CellState(new int[] {1});
	}
	
	private CellState newDead(){
		return new CellState(new int[] {0});
	}

}
