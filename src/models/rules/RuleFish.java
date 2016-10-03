package models.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import models.grid.Cell;
import models.grid.CellState;
import models.grid.GridModel;


/**
 * The class that defines the rules for the Wa-Tor world simulation
 * @author Weston
 *
 */
public class RuleFish extends Rule {
	
	private GridModel myGrid;
	private int myFishReproTime;
	private int mySharkReproTime;
	private int myFishEnergy;
	
	private final String chrononName;
	private final String ageName;
	private final String energyName;
	
	
	
	/**
	 * @param fishReproTime time for fish to reproduce
	 * @param sharkReproTime time for sharks to reproduce
	 * @param fishEnergy energy sharks gain by eating a fish
	 * @param attributeNames the names of the fish/shark attributes given in the xml [chrononName, ageName, energyName]
	 */
	public RuleFish(int fishReproTime, int sharkReproTime, int fishEnergy, String[] attributeNames, Map<String, Integer> stateIdsMap) {
		super(stateIdsMap);
		
		myFishReproTime = fishReproTime;
		mySharkReproTime = sharkReproTime;
		myFishEnergy = fishEnergy;
		
		chrononName = attributeNames[0];
		ageName = attributeNames[1];
		energyName = attributeNames[2];
	}
	
	/**
	 * Also pass a string array of the string names of each fish attribute. This method does the same thing as calling
	 * RuleFish(fishReproTime, sharkReproTime, fishEnergy, {"Chronon", "Age", "Energy"})
	 * 
	 * @param fishReproTime time for fish to reproduce
	 * @param sharkReproTime time for sharks to reproduce
	 * @param fishEnergy energy sharks gain by eating a fish
	 * @deprecated
	 */
	@Deprecated
	public RuleFish(int fishReproTime, int sharkReproTime, int fishEnergy, Map<String, Integer> aStateIdsMap) {
		super(aStateIdsMap);
		
		myFishReproTime = fishReproTime;
		mySharkReproTime = sharkReproTime;
		myFishEnergy = fishEnergy;
		
		chrononName = "Chronon";
		ageName = "Age";
		energyName = "Energy";
	}

	@Override
	public void calculateAndSetNextStates(GridModel grid) {
		myGrid = grid;
		
		int prevChron = (int) myGrid.getCell(0, 0).getState(chrononName);
		
		for (Cell c: myGrid){
			if ((int) c.getNextState(chrononName) <= prevChron){
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
			c.increment(chrononName);
		} else {
//			if (c.getNextStateID() == fishID){
			if (c.getNextStateID() == super.getStateId("Fish")){
				Cell nextMove = pickFishMove(c);
				if (nextMove != null)
					moveFish(c, nextMove);
				else {
					c.increment(chrononName);
					c.increment(ageName);
				}
				
//			} else if ( c.getNextStateID() == sharkID){
			} else if ( c.getNextStateID() == super.getStateId("Shark")){
				if (c.getNextState(energyName) <= 0){
					c.setNextState(newEmpty(c.getNextState(chrononName) + 1));
				} else {
					c.decrement(energyName);
					Cell nextMove = pickSharkMove(c);
					
					if (nextMove != null)
						moveShark(c, nextMove);
					else {
						c.increment(chrononName);
						c.increment(ageName);
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
		fish.increment(chrononName);
		fish.increment(ageName);
		
		if (fish.getStateAttrib(ageName) < myFishReproTime)
			c.setNextState(newEmpty(fish.getStateAttrib(chrononName)));
		else {
			c.setNextState(newFish(fish.getStateAttrib(chrononName)));
			fish.setStateAtttrib(0, ageName);
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
		shark.increment(chrononName);
		shark.increment(ageName);
		
		if (shark.getStateAttrib(ageName) < mySharkReproTime)
			c.setNextState(newEmpty(shark.getStateAttrib(chrononName)));
		else {
			c.setNextState(newShark(shark.getStateAttrib(chrononName), shark.getStateAttrib(energyName)));
			shark.setStateAtttrib(0, ageName);
		}
		
//		if (destination.getNextStateID() == fishID)
		if (destination.getNextStateID() == super.getStateId("Fish"))
			shark.setStateAtttrib(shark.getStateAttrib(energyName) + myFishEnergy, energyName);
		
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
			if (options[i] != null && options[i].getNextStateID() != super.getStateId("Fish"))
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
		map.put(chrononName, chronon);
		map.put(ageName, 0.0);
		map.put(energyName, 0.0);
		
		return new CellState(0, map);
	}
	
	private CellState newFish(double chronon){
		Map<String, Double> map = new TreeMap<String, Double>();
		map.put(chrononName, chronon);
		map.put(ageName, 0.0);
		map.put(energyName, 0.0);
		
		return new CellState(1, map);
	}
	
	private CellState newShark(double chronon, double energy){
		Map<String, Double> map = new TreeMap<String, Double>();
		map.put(chrononName, chronon);
		map.put(ageName, 0.0);
		map.put(energyName, energy);
		
		return new CellState(2, map);
	}
	
	private boolean occupied(Cell c){
//		return (c != null && !(c.getNextStateID() == emptyID));
		return (c != null && !(c.getNextStateID() == super.getStateId("Empty")));
	}

	@Override
	public void updateParameter(double aPercentage) {
		myFishReproTime *= aPercentage;
	}

	@Override
	public double getParameter() {
		return myFishReproTime;
	}

}
