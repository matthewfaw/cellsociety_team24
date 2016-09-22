package cellsociety_team24;

import java.util.Collection;
import java.util.Vector;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GridController {
	private Cell[][] myGrid;
	private Rule myRules;
	
	public Color getColor(Cell c) {
		switch (c.getState().getState()){
			case 0:
				return Color.web("0000FF", 1.0);
			case 1:
				return Color.web("00FF00", 1.0);
			case 2:
				return Color.web("FF0000", 1.0);
			default:
				return Color.web("000000", 0.0);
				
		}
	}
	
	/**
	 * Calculate the next state of each cell in the grid, then update all their states.
	 * @return 
	 */
	public void nextTick(){
		calculateAllNextStates();
		
		for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid.length; j++){
				myGrid[i][j].tick();
			}
		}
	}
	
	public Collection<Polygon> getGridImages(int start, int end, double size){
		Vector<Polygon> result = new Vector<Polygon>();
		for (int i = 0; i < myGrid.length && i < end; i++){
			for (int j = 0; j < myGrid.length && i < end; j++){
				Polygon polyToAdd = new Polygon(myGrid[i][j].vertices(p, sides));
				polyToAdd.setFill(getColor(myGrid[i][j]));
			}
		}
		return result;
	}

	/**
	 * Calculate a next state for each cell using the given rules
	 */
	private void calculateAllNextStates() {
		for (int i = 0; i < myGrid.length; i++){
			for (int j = 0; j < myGrid.length; j++){
				CellState c = myRules.calculateNextState(myGrid[i][j]);
				myGrid[i][j].setState(c);
			}
		}
	}
}
