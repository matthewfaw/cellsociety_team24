package models.rules;

import java.util.ArrayList;
import java.util.Collections;

import models.Point;
import models.grid.Cell;

public class RuleSegregation extends Rule {
	ArrayList<Cell> myDissenters;
	ArrayList<Cell> myEmpties;
	int myGridShape;
	Cell[][] myGrid;
	double satisfiedPercent;
	
	/**
	 * 
	 * @param fraction of like neighbors required to be satisfied
	 */
	public RuleSegregation(double fraction){
		satisfiedPercent = fraction;
	}

	@Override
	public void calculateAndSetNextStates(Cell[][] grid, int gridShape) {
		myGrid = grid;
		myGridShape = gridShape;
		myEmpties = new ArrayList<Cell>();
		myDissenters = new ArrayList<Cell>();
		
		for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[i].length; j++){
				if (likeNeighborsPercent(myGrid[i][j]) < satisfiedPercent)
					myDissenters.add(myGrid[i][j]);
				if (empty(myGrid[i][j]))
					myEmpties.add(myGrid[i][j]);
			}
		}
		relocateDissenters();

	}
	
	private boolean empty(Cell c){
		return c.getState(0) == 0;
	}
	
	private double likeNeighborsPercent(Cell c){
		Point[] neighbors = getNeighbors(c.getLocation(), myGridShape);
		int cType = c.getState(0);
		
		double neighborCount = 0;
		double sameNeighbors = 0;
		
		for (Point p: neighbors){
			Cell neighbor = getCell(p, myGrid);
			
			if (neighbor != null){
				neighborCount++;
				if (neighbor.getState(0) == cType)
					sameNeighbors++;
			}
		}
		return sameNeighbors / neighborCount;
	}
	
	private void relocateDissenters(){
		Collections.shuffle(myEmpties);
		Collections.shuffle(myDissenters);
		
		int i = 0;
		
		while (i < myEmpties.size() && i < myDissenters.size()){
			myEmpties.get(i).setNextState(myDissenters.get(i).getState());
			myDissenters.get(i).setNextState(myEmpties.get(i).getState());
			i++;
		}
	}
}


