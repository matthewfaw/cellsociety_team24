package models.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import models.grid.Cell;
import models.grid.GridModel;

public class RuleSegregation extends Rule {
//	private static final int emptyID = 0;
	
	ArrayList<Cell> myDissenters;
	ArrayList<Cell> myEmpties;
	int myGridShape;
	GridModel myGrid;
	double satisfiedPercent;
	
	/**
	 * 
	 * @param fraction of like neighbors required to be satisfied
	 */
	public RuleSegregation(double fraction, Map<String, Integer> aStateIdsMap){
		super(aStateIdsMap);
		
		satisfiedPercent = fraction;
	}

	@Override
	public void calculateAndSetNextStates(GridModel grid) {
		myGrid = grid;
		myEmpties = new ArrayList<Cell>();
		myDissenters = new ArrayList<Cell>();
		
		for (Cell c: myGrid){
			if (c.getNextStateID() != 0 && likeNeighborsPercent(c) < satisfiedPercent)
				myDissenters.add(c);
			else if (empty(c))
				myEmpties.add(c);
		}
		relocateDissenters();
	}
	
	private boolean empty(Cell c){
//		return c.getStateID() == emptyID;
		return c.getStateID() == super.getStateId("Empty");
	}
	
	private double likeNeighborsPercent(Cell c){		
		Cell[] neighbors = myGrid.getNeighbors(c);
		
		int cType = c.getStateID();
		
		double neighborCount = 0;
		double sameNeighbors = 0;
		
		for (Cell neighbor: neighbors){
			if (neighbor != null){
				neighborCount++;
				if (neighbor.getStateID() == cType)
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


