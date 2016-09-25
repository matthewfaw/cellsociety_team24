package models.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import models.Point;
import models.grid.Cell;
import models.grid.CellState;

/*
 * Data for fish is [empty/fish/shark, current chronon, time since creation, health]
 */

public class RuleFish extends Rule {
	private Cell[][] myGrid;
	private int myShape;
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
	public void calculateAndSetNextStates(Cell[][] grid, int gridShape) {
		myGrid = grid;
		myShape = gridShape;
		int prevChron = myGrid[0][0].getState("Chronon");
		
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				Cell c = myGrid[i][j];
				
				if (c.getNextState("Chronon") <= prevChron){
					move(c);
				}
			}
		}
	}
	
	/**
	 * Calculates the next move for the occupant of Cell c
	 * @param c starting cell
	 */
	private void move(Cell c){
		if (occupied(c)){
			
			if (c.getNextStateID() == 1){
				Point nextMove = pickFishMove(c);
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
					Point nextMove = pickSharkMove(c);
					
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
	private void moveFish(Cell c, Point p){
		CellState fish = c.getNextState();
		fish.increment("Chronon");
		fish.increment("Age");
		
		if (fish.getStateAttrib("Age") < myFishReproTime)
			c.setNextState(newEmpty(fish.getStateAttrib("Age")));
		else {
			c.setNextState(newFish(fish.getStateAttrib("Chronon")));
			fish.setStateAtttrib(0, "Age");
		}
		
		getCell(p, myGrid).setNextState(fish);
	}
	
	/**
	 * Move the shark i cell c to the cell at point p
	 * @param c starting cell
	 * @param p destination
	 */
	private void moveShark(Cell c, Point p){
		CellState shark = c.getNextState();
		shark.increment("Chronon");
		shark.increment("Age");
		
		if (shark.getStateAttrib("Age") < mySharkReproTime)
			c.setNextState(newEmpty(shark.getStateAttrib("Age")));
		else {
			c.setNextState(newShark(shark.getStateAttrib("Chronon"), shark.getStateAttrib("Energy")));
			shark.setStateAtttrib(0, "Age");
		}
		
		Cell destination = getCell(p, myGrid);
		
		if (destination.getNextStateID() == 1)
			shark.setStateAtttrib(shark.getStateAttrib("Energy") + myFishEnergy, "Energy");
		
		destination.setNextState(shark);
		
	}
	
	/**
	 * @param c starting cell
	 * @return a random empty cell that is adj to c, or null if there is none
	 */
	private Point pickFishMove(Cell c){
		Point[] options = getNeighbors(c.getLocation(), myShape);
		
		for (int i = 0; i < options.length; i++){
			if (options[i] != null && occupied(options[i]))
				options[i] = null;
		}
		
		ArrayList<Point> nonNullOptions = new ArrayList<Point>(Arrays.asList(options));
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
	private Point pickSharkMove(Cell c){
		Point[] options = getNeighbors(c.getLocation(), myShape);
		
		for (int i = 0; i < options.length; i++){
			if (options[i] != null && !(getCell(options[i], myGrid).getNextStateID() == 1))
				options[i] = null;
		}
		ArrayList<Point> nonNullOptions = new ArrayList<Point>(Arrays.asList(options));
		nonNullOptions.removeAll(Collections.singleton(null));
		
		if (!nonNullOptions.isEmpty()){
			 Collections.shuffle(nonNullOptions);
			return nonNullOptions.get(0);
		} else
			return pickFishMove(c);
		
	}

	private CellState newEmpty(int chronon){
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("Chronon", chronon);
		map.put("Age", 0);
		map.put("Energy", 0);
		
		return new CellState(0, map);
	}
	
	private CellState newFish(int chronon){
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("Chronon", chronon);
		map.put("Age", 0);
		map.put("Energy", 0);
		
		return new CellState(1, map);
	}
	
	private CellState newShark(int chronon, int energy){
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("Chronon", chronon);
		map.put("Age", 0);
		map.put("Energy", energy);
		
		return new CellState(2, map);
	}
	
	@Override
	protected Cell getCell(Point p, Cell[][] grid){
		return grid	[(p.getX() 	+ 	grid.length) 		% grid.length]
					[(p.getY() 	+ 	grid[0].length) 	% grid[0].length];
	}
	
	private boolean occupied(Point p){
		Cell c = getCell(p, myGrid);
		return occupied(c);
	}
	
	private boolean occupied(Cell c){
		return (c != null && !(c.getNextStateID() == 0));
	}

}
