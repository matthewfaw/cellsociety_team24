package models.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import models.grid.Cell;
import models.grid.CellState;
import models.grid.GridModel;

/*
 * Data for fish is [empty/fish/shark, current chronon, time since creation, health]
 */

public class RuleFish extends Rule {
	private GridModel myGrid;
	private int myFishReproTime;
	private int mySharkReproTime;
	private int myFishEnergy;
	
	
	/**
	 * @param fishReproTime time for fish to reproduce
	 * @param sharkReproTime time for sharks to reproduce
	 * @param fishEnergy energy sharks gain by eating a fish
	 */
	public RuleFish(int fishReproTime, int sharkReproTime, int fishEnergy) {
		myFishReproTime = fishReproTime;
		mySharkReproTime = sharkReproTime;
		myFishEnergy = fishEnergy;
	}

	@Override
	public void calculateAndSetNextStates(GridModel grid) {
		myGrid = grid;
		
		int prevChron = (int) myGrid.getCell(0, 0).getState("Chronon");
		
		for (Cell c: myGrid){
			if ((int) c.getNextState("Chronon") <= prevChron){
				move(c);
			}
		}
	}
	
	/**
	 * Calculates the next move for the occupant of Cell c
	 * @param c starting cell
	 */
	private void move(Cell c){
		//TODO: consolidate move statements
		if (!occupied(c)){
			c.incrementState("Chronon");
		} else {
			if (c.getNextStateID() == 1){
				Cell nextMove = pickFishMove(c);
				if (nextMove != null)
					moveFish(c, nextMove);
				else {
					c.incrementState("Chronon");
					c.incrementState("Age");
				}
				
			} else if ( c.getNextStateID() == 2){
				if (c.getNextState("Energy") <= 0){
					c.setNextState(newEmpty(c.getNextState("Chronon") + 1));
				} else {
					c.decrement("Energy");
					Cell nextMove = pickSharkMove(c);
					
					if (nextMove != null)
						moveShark(c, nextMove);
					else {
						c.incrementState("Chronon");
						c.incrementState("Age");
					}
				}
			}
		}
	}
	
	/**
	 * Move the fish in cell c to the cell at point p
	 * @param c starting cell
	 * @param p destination
	 */
	private void moveFish(Cell c, Cell destination){
		CellState fish = c.getNextState();
		fish.increment("Chronon");
		fish.increment("Age");
		
		if (fish.getStateAttrib("Age") < myFishReproTime)
			c.setNextState(newEmpty(fish.getStateAttrib("Chronon")));
		else {
			c.setNextState(newFish(fish.getStateAttrib("Chronon")));
			fish.setStateAtttrib(0, "Age");
		}
		
		destination.setNextState(fish);
	}
	
	/**
	 * Move the shark i cell c to the cell at point p
	 * @param c starting cell
	 * @param p destination
	 */
	private void moveShark(Cell c, Cell destination){
		CellState shark = c.getNextState();
		shark.increment("Chronon");
		shark.increment("Age");
		
		if (shark.getStateAttrib("Age") < mySharkReproTime)
			c.setNextState(newEmpty(shark.getStateAttrib("Chronon")));
		else {
			c.setNextState(newShark(shark.getStateAttrib("Chronon"), shark.getStateAttrib("Energy")));
			shark.setStateAtttrib(0, "Age");
		}
		
		if (destination.getNextStateID() == 1)
			shark.setStateAtttrib(shark.getStateAttrib("Energy") + myFishEnergy, "Energy");
		
		destination.setNextState(shark);
		
	}
	
	/**
	 * @param c starting cell
	 * @return a random empty cell that is adj to c, or null if there is none
	 */
	private Cell pickFishMove(Cell c){
		Cell[] options = myGrid.getNeighbors(c);
		
		for (int i = 0; i < options.length; i++){
			if (options[i] != null && occupied(options[i]))
				options[i] = null;
		}
		
		ArrayList<Cell> nonNullOptions = new ArrayList<Cell>(Arrays.asList(options));
		nonNullOptions.removeAll(Collections.singleton(null));
		
		if (!nonNullOptions.isEmpty()){
			 Collections.shuffle(nonNullOptions);
			return nonNullOptions.get(0);
		} else
			return null;
	}
	
	/**
	 * @param c starting cell
	 * @return a random adj cell with a fish in it, or if none, a random adj empty cell, or if none, null
	 */
	private Cell pickSharkMove(Cell c){
		Cell[] options = myGrid.getNeighbors(c);
		
		for (int i = 0; i < options.length; i++){
			if (options[i] != null && !(options[i].getNextStateID() == 1))
				options[i] = null;
		}
		ArrayList<Cell> nonNullOptions = new ArrayList<Cell>(Arrays.asList(options));
		nonNullOptions.removeAll(Collections.singleton(null));
		
		if (!nonNullOptions.isEmpty()){
			 Collections.shuffle(nonNullOptions);
			return nonNullOptions.get(0);
		} else
			return pickFishMove(c);
		
	}

	private CellState newEmpty(double chronon){
		Map<String, Double> map = new TreeMap<String, Double>();
		map.put("Chronon", chronon);
		map.put("Age", 0.0);
		map.put("Energy", 0.0);
		
		return new CellState(0, map);
	}
	
	private CellState newFish(double chronon){
		Map<String, Double> map = new TreeMap<String, Double>();
		map.put("Chronon", chronon);
		map.put("Age", 0.0);
		map.put("Energy", 0.0);
		
		return new CellState(1, map);
	}
	
	private CellState newShark(double chronon, double energy){
		Map<String, Double> map = new TreeMap<String, Double>();
		map.put("Chronon", chronon);
		map.put("Age", 0.0);
		map.put("Energy", energy);
		
		return new CellState(2, map);
	}
	
	private boolean occupied(Cell c){
		return (c != null && !(c.getNextStateID() == 0));
	}

}
