package models.rules;

import java.util.Random;

import models.Point;
import models.grid.Cell;
import models.grid.CellState;

public class RuleFire extends Rule {
	Random myRandom;
	double myProbCatch;
	Cell[][] myGrid;

	public RuleFire(double probCatch) {
		myProbCatch = probCatch;
		myRandom = new Random();
	}

	@Override
	public CellState[][] calculateNextStates(Cell[][] grid, int gridShape) {
		myGrid = grid;
		CellState[][] result = new CellState[myGrid.length][myGrid[0].length];
		
		for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				result[i][j] = nextState(myGrid[i][j], gridShape);
			}
		}
		return result;
	}

	private CellState nextState(Cell cell, int gridShape) {
		Point[] neighbors = getNeighbors(cell.getLocation(), gridShape);
		int burningNeighbors = 0;
		
		for (Point p: neighbors){
			Cell neighbor = getCell(p, myGrid);
			if (neighbor.getState().getState()[0] == 2)
				burningNeighbors++;
		}
		

		if (cell.getState().getState()[0] == 1){
			if(burningNeighbors > 0 && myRandom.nextDouble() < myProbCatch)
				return new CellState(new int[] {2});
			else
				return new CellState(new int[] {1});
		} else {
			return new CellState(new int[] {0});
		}
	}

}
