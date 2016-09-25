package models.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
	

	public RuleFish(int fishReproTime, int sharkReproTime, int fishEnergy) {
		myFishReproTime = fishReproTime;
		mySharkReproTime = sharkReproTime;
		myFishEnergy = fishEnergy;
	}

	@Override
	public CellState[][] calculateNextStates(Cell[][] grid, int gridShape) {
		myGrid = grid;
		myShape = gridShape;
		int prevChron = myGrid[0][0].getState(1);
		
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				if (myGrid[i][j].getState(1) <= prevChron){
					myGrid[i][j].setState(prevChron + 1, 1);
					move(myGrid[i][j]);
				}
			}
		}
		
		CellState[][] result = new CellState[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				result[i][j] = myGrid[i][j].getState();
			}
		}

		return result;
	}
	
	private void move(Cell c){
		if (occupied(c)){
			if (c.getState(0) == 1){
				Point[] options = getNeighbors(c.getLocation(), myShape);
				for (int i = 0; i < options.length; i++){
					if (options[i] != null && occupied(options[i]))
						options[i] = null;
				}
				
				ArrayList<Point> nonNullOptions = new ArrayList<Point>(Arrays.asList(options));
				nonNullOptions.removeAll(Collections.singleton(null));
				
				if (!nonNullOptions.isEmpty()){
					Collections.shuffle(nonNullOptions);
					moveFish(c, nonNullOptions.get(0));
				}

			} else if ( c.getState(0) == 2){
				Point[] options = getNeighbors(c.getLocation(), myShape);
				
				ArrayList<Point> optionsWithFish = new ArrayList<Point>();
				ArrayList<Point> optionsWithoutFish = new ArrayList<Point>();
				
				for (Point p: options){
					if (p != null && !occupied(p))
						optionsWithoutFish.add(p);
					if (p != null && occupied(p) && getCell(p, myGrid).getState(0) == 1)
						optionsWithFish.add(p);
				}
				
				
				if (!optionsWithFish.isEmpty()){
					Collections.shuffle(optionsWithFish);
					moveShark(c, optionsWithFish.get(0));
					
				} else if (!optionsWithoutFish.isEmpty()){
					Collections.shuffle(optionsWithoutFish);
					moveShark(c, optionsWithoutFish.get(0));
				}
			}
		}
	}
	
	
	private void moveFish(Cell c, Point p){
		CellState fish = c.getState();
		fish.setState(2, fish.getState(2) + 1);
		
		if (fish.getState(3) < myFishReproTime)
			c.setState(new CellState(new int[] {0, fish.getState(1), 0, 0}));
		else {
			c.setState(new CellState(new int[] {1, fish.getState(1), 0, 0}));
			fish.setState(0, 2);
		}
		
		getCell(p, myGrid).setState(fish);
	}
	
	private void moveShark(Cell c, Point p){
		CellState shark = c.getState();
		shark.setState(2, shark.getState(2) + 1);
		
		if (shark.getState(3) < mySharkReproTime)
			c.setState(new CellState(new int[] {0, shark.getState(1), 0, 0}));
		else {
			c.setState(new CellState(new int[] {1, shark.getState(1), 0, shark.getState(3)}));
			shark.setState(0, 2);
		}
		
		Cell destination = getCell(p, myGrid);
		
		if (destination.getState(0) == 1)
			shark.setState(3, shark.getState(3) + myFishEnergy);
		
		destination.setState(shark);
		
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
		return (c != null && !(c.getState(0) == 0));
	}

}