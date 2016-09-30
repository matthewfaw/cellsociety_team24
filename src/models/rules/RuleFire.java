package models.rules;

import java.util.Random;

import models.grid.Cell;
import models.grid.CellState;
import models.grid.GridModel;

public class RuleFire extends Rule {
	Random myRandom;
	double myProbCatch;
	GridModel myGrid;

	public RuleFire(double probCatch) {
		myProbCatch = probCatch;
		myRandom = new Random();
	}

	@Override
	public void calculateAndSetNextStates(GridModel grid) {
		myGrid = grid;
		
		for (Cell c: grid){
			c.setNextState(nextState(c));
		}
	}

	private CellState nextState(Cell cellToUpdate) {
		Cell[] neighbors = myGrid.getNeighbors(cellToUpdate);
		int burningNeighbors = 0;
		
		for (Cell neighbor: neighbors){
			if (neighbor != null && neighbor.getStateID() == 2)
				burningNeighbors++;
		}
		

		if (cellToUpdate.getStateID() == 1){
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
