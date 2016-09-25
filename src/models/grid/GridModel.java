package models.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import models.rules.Rule;
import models.settings.CellSettings;
import models.settings.GridSettings;

public class GridModel {
	private static final Random fRandomNumberGenerator = new Random();
	
	private Cell[][] myGrid;
	private Rule myRules;
	private int myCellSides;
	
	private GridSettings fGridSettings;
	private CellSettings fCellSettings;
	private HashMap<Integer, Integer> fCurrentStateProportions;
	private ArrayList<Integer> fStateIds;
	
	public GridModel(GridSettings aGridSettings, CellSettings aCellSettings)
	{
		fGridSettings = aGridSettings;
		fCellSettings = aCellSettings;
		fStateIds = new ArrayList<Integer>(fGridSettings.getStatePercentages().keySet());
		fCurrentStateProportions = new HashMap<Integer, Integer>();
		for (int stateId: fStateIds) {
			fCurrentStateProportions.put(stateId, 0);
		}

		buildGrid();
	}
	
	private void buildGrid()
	{
		Dimension gridDimensions = fGridSettings.getDimensions();
		myGrid = new Cell[(int)gridDimensions.getWidth()][(int)gridDimensions.getHeight()];
		for (int row=0; row<myGrid.length; ++row) {
			for (int col=0; col<myGrid[row].length; ++col) {
				Cell cell = new Cell();
				int stateIndex = selectStateIndex();
				//XXX: waiting for Weston's changes
				cell.setState(stateIndex);
				cell.setProperties(fCellSettings.getProperties(stateIndex));
//				cell.setNeighborhood();
			}
		}
	}
	
	private int selectStateIndex()
	{
		int randomStateId = getRandomStateId();
		
		int currentProportion = fCurrentStateProportions.get(randomStateId);
		int maxProportion = fGridSettings.getStatePercentages().get(randomStateId);
		
		if (!allProportionsAreAtMaximum()) {
			System.out.println("ERROR: No states can be created!");
			return -1;
		} else if (currentProportion < maxProportion) {
			fCurrentStateProportions.put(randomStateId, ++currentProportion);
			return randomStateId;
		} else { // try again
			return selectStateIndex();
		}
	}
	
	private boolean allProportionsAreAtMaximum()
	{
		for (int stateId: fStateIds) {
			int currentProportion = fCurrentStateProportions.get(stateId);
			int maxProportion = fGridSettings.getStatePercentages().get(stateId);

			if (currentProportion >= maxProportion) {
				return false;
			}
		}
		return true;
	}
	
	private int getRandomStateId() 
	{
		int randomIndex = fRandomNumberGenerator.nextInt(fStateIds.size());
		return fStateIds.get(randomIndex);
	}
	
//	public GridModel(int cellSides, Rule rules, Cell[][] grid){
//		myGrid = grid;
//		myRules = rules;
//		myCellSides = cellSides;
//	}
	
	
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
