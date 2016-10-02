package models.rules;

import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import models.grid.Cell;
import models.grid.CellState;
import models.grid.GridModel;

public class RuleFire extends Rule {
//	private static final int fireID = 2;
//	private static final int treeID = 1;
//	private static final int emptyID = 0;
	
	private Random myRandom;
	private double myProbCatch;
	private GridModel myGrid;

	public RuleFire(double probCatch, Map<String, Integer> aStateIdsMap) {
		super(aStateIdsMap);
		
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
//			if (neighbor != null && neighbor.getStateID() == fireID)
			if (neighbor != null && neighbor.getStateID() == super.getStateId("Fire")) {
				burningNeighbors++;
			}
		}
		

//		if (cellToUpdate.getStateID() == treeID){
		if (cellToUpdate.getStateID() == super.getStateId("Tree")) {
			if(burningNeighbors > 0 && myRandom.nextDouble() < myProbCatch)
				return newBurning();
			else
				return newTree();
		} else {
			return newEmpty();
		}
	}
	
	private CellState newBurning(){
//		return new CellState(fireID, null);
		return new CellState(super.getStateId("Fire"), null);
	}
	
	private CellState newTree(){
//		return new CellState(treeID, null);
		return new CellState(super.getStateId("Tree"), null);
	}
	
	private CellState newEmpty(){
//		return new CellState(emptyID, null);
		return new CellState(super.getStateId("Empty"), null);
	}
}
