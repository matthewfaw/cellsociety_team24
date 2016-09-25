package models.grid;

import models.rules.Rule;

public class GridModel {
	private Cell[][] myGrid;
	private Rule myRules;
	private int myCellSides;
	
	public GridModel(int cellSides, Rule rules, Cell[][] grid){
		myGrid = grid;
		myRules = rules;
		myCellSides = cellSides;
	}
	
	
	/**
	 * Calculate the next state of each cell in the grid, then update all their states.
	 * @return 
	 */
	public void nextTick(){
		calculateAllNextStates();
		
		for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				myGrid[i][j].tick();
			}
		}
	}
	

	/**
	 * Calculate a next state for each cell using the given rules
	 */
	private void calculateAllNextStates() {
		myRules.calculateAndSetNextStates(myGrid, myCellSides);
	}
	
    public CellState[][] getCellStates(){
        CellState[][] result = new CellState[myGrid.length][myGrid[0].length];
        
        for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				result[i][j] = myGrid[i][j].getState();
			}
		}
        return result;
    }
	
		


	public int getCellSides(){
		return myCellSides;
	}
}
