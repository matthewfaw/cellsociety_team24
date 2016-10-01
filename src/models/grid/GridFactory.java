package models.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import models.rules.Rule;
import models.rules.RuleFactory;
import models.settings.CellSettings;
import models.settings.GridSettings;

public class GridFactory {
	private static final Random fRandomNumberGenerator = new Random();
	
	private GridSettings fGridSettings;
	private CellSettings fCellSettings;
	
	private ArrayList<Integer> fStateIds;

	public GridFactory(GridSettings aGridSettings, CellSettings aCellSettings)
	{
		fGridSettings = aGridSettings;
		fCellSettings = aCellSettings;
		fStateIds = new ArrayList<Integer>(fGridSettings.getStatePercentages().keySet());
	}

	public GridModel createGridModel()
	{
		RuleFactory ruleFactory = new RuleFactory();
		Rule rules = ruleFactory.createRule(fGridSettings.getRuleType(), fGridSettings.getSimulationProperties());
		ArrayList<Cell> grid = buildGrid();
		int numberOfSides = fGridSettings.getNumberOfCellSides();
		Dimension gridDimensions = fGridSettings.getDimensions();
		
		GridModel gridModel = new GridModel(grid, gridDimensions, rules, numberOfSides);
		
		return gridModel;
	}
	
	private ArrayList<Cell> buildGrid()
	{
		Dimension gridDimensions = fGridSettings.getDimensions();
		ArrayList<Cell> grid = new ArrayList<Cell>();
		for (int row=0; row<gridDimensions.getWidth(); ++row) {
			for (int col=0; col<gridDimensions.getHeight(); ++col) {
				int stateIndex = getRandomStateId();
				Map<String, Double> stateProperties = fCellSettings.getProperties(stateIndex);
				Cell cell = new Cell(row, col, stateIndex, stateProperties);
				grid.add(cell);
			}
		}
		return grid;
	}

	private int getRandomStateId() 
	{
		double randomPercentage = fRandomNumberGenerator.nextDouble() * 100.0;
		double previousProportion = 0;
		for (int stateId: fStateIds) {
			double newProportion = fGridSettings.getStatePercentages().get(stateId) + previousProportion;
			
			if (newProportion >= randomPercentage) {
				return stateId;
			}
			
			previousProportion = newProportion;
		}
		return fStateIds.get(fStateIds.size() - 1);
	}
}
