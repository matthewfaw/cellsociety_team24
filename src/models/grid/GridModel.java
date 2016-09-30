package models.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import models.rules.Rule;

public class GridModel {
	private Cell[][] myGrid;
	private Rule myRules;
	private int myCellSides;
	
	//NOTE: there is no public/private/protected qualifier in front of this constructor, because
	// this constructor should only constructed from GridFactory
	GridModel(Collection<Cell> aCellCollection, Dimension aGridDimensions, Rule aRule, int aNumberOfCellSides)
	{
		myGrid = constructGrid(aCellCollection, aGridDimensions);
		myRules = aRule;
		myCellSides = aNumberOfCellSides;
	}
	
	private Cell[][] constructGrid(Iterable<Cell> aCellCollection, Dimension aGridDimensions)
	{
		myGrid = new Cell[(int) aGridDimensions.getWidth()][(int) aGridDimensions.getHeight()];
		
		for (Cell cell: aCellCollection) {
			int row = cell.getLocation().getX();
			int col = cell.getLocation().getY();
			myGrid[row][col] = cell;
		}
		
		return myGrid;
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
	
    public Collection<Cell> getAllCells(){
        ArrayList<Cell> result = new ArrayList<Cell>(); 
        
        for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid[0].length; j++){
				result.add(myGrid[i][j]);
			}
		}
        return result;
    }
	
	public int getCellSides(){
		return myCellSides;
	}
}
