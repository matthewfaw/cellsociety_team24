package models.rules;

import models.Point;
import models.grid.Cell;
import models.grid.CellState;

public class RuleLife extends Rule {

	@Override
	public void calculateAndSetNextStates(Cell[][] grid, int gridShape) {
		
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				Cell c = grid[i][j];
				
				Point[] neighbors = getNeighbors(c.getLocation(), gridShape);
				int livingNeighbors = 0;
				
				for (Point p: neighbors){
					Cell neighbor = getCell(p, grid);
					if (neighbor != null)
						livingNeighbors += neighbor.getState(0);
				}
				
				if (c.getState(0) == 1){
					if (livingNeighbors == 2 || livingNeighbors == 3){
						c.setNextState(c.getState());
					} else {
						c.setNextState(newDead());
					}
				} else {
					if (livingNeighbors == 3){
						c.setNextState(newLive());
					} else {
						c.setNextState(c.getState());
					}
				}
			}
		}
	}
	
	private CellState newLive(){
		return new CellState(new int[] {1});
	}
	
	private CellState newDead(){
		return new CellState(new int[] {0});
	}

}
