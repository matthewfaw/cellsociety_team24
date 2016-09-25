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
	public void calculateAndSetNextStates(Cell[][] grid, int gridShape) {
		myGrid = grid;
		
		for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				grid[i][j].setNextState(nextState(myGrid[i][j], gridShape));
			}
		}
	}

	private CellState nextState(Cell cell, int gridShape) {
		Point[] neighbors = getNeighbors(cell.getLocation(), gridShape);
		int burningNeighbors = 0;
		
		for (Point p: neighbors){
			Cell neighbor = getCell(p, myGrid);
			if (neighbor != null && neighbor.getStateID() == 2)
				burningNeighbors++;
		}
		

		if (cell.getStateID() == 1){
			if(burningNeighbors > 0 && myRandom.nextDouble() < myProbCatch)
				return newBurning();
			else
				return newTree();
		} else {
			return newEmpty();
		}
	}
	
	private CellState newBurning(){
		return new CellState(2, null);
	}
	
	private CellState newTree(){
		return new CellState(1, null);
	}
	
	private CellState newEmpty(){
		return new CellState(0, null);
	}

}
