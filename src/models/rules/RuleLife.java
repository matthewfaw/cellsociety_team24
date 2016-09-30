package models.rules;

import models.grid.Cell;
import models.grid.CellState;
import models.grid.GridModel;

public class RuleLife extends Rule {
	private static final int liveID = 1;
	private static final int deadID = 0;
	

	@Override
	public void calculateAndSetNextStates(GridModel grid) {
		
		for (Cell c: grid){
				
			Cell[] neighbors = grid.getNeighbors(c);
			int livingNeighbors = 0;
			
			for (Cell neighbor: neighbors){
				if (neighbor != null)
					livingNeighbors += neighbor.getStateID();
			}
			
			if (c.getStateID() == liveID){
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
	
	private CellState newLive(){
		return new CellState(liveID, null);
	}
	
	private CellState newDead(){
		return new CellState(deadID, null);
	}

}
