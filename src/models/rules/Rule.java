package models.rules;

import models.grid.GridModel;

abstract public class Rule {
	
	/**
	 * Calculates the next state of a cell given the current state. 
	 * @param c The cell to find the next state for.
	 * @return The cell's intended next state.
	 */
	abstract public void calculateAndSetNextStates(GridModel grid);
	
	
}
