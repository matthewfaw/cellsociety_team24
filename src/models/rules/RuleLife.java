package models.rules;

import java.util.Map;

import models.grid.Cell;
import models.grid.CellState;
import models.grid.GridModel;

/**
 * The class that defines the rules for the Game of Life simulation
 * @author Weston
 *
 */
public class RuleLife extends Rule {
	
	public RuleLife(Map<String, Integer> aStateIdsMap)
	{
		super(aStateIdsMap);
	}

	@Override
	public void calculateAndSetNextStates(GridModel grid) {
		
		for (Cell c: grid){
				
			Cell[] neighbors = grid.getNeighbors(c);
			int livingNeighbors = 0;
			
			for (Cell neighbor: neighbors){
				if (neighbor != null)
					livingNeighbors += neighbor.getStateID();
			}
			
//			if (c.getStateID() == liveID){
			if (c.getStateID() == super.getStateId("Live")){
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
//		return new CellState(liveID, null);
		return new CellState(super.getStateId("Live"), null);
	}
	
	private CellState newDead(){
		return new CellState(super.getStateId("Dead"), null);
	}

	@Override
	public void updateParameter(double aPercentage) {
	}

	@Override
	public double getParameter() {
		return 0;
	}

}
