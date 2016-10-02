package models.rules;

import java.util.Map;
import java.util.Random;

import models.grid.Cell;
import models.grid.GridModel;

public class RuleSilme extends Rule{
	private double myDrop;
	private double myEvap;
	private double myDiff;
	private double myChemMax;
	
	private int mySniffAngle;
	private int mySniffThreshold;
	private int myWiggleAngle;
	private int myWiggleBias;
	
	private final String chemAmount;
	private final String angle;
	
	private GridModel myGrid;
	
	private static final Random myRand = new Random(); 
	

	public RuleSilme(	double evapRate,
						double diffuseRate,
						int sniffAngle,
						int wiggleAngle,
						int sniffThreshold,
						int wiggleBias,
						double dropRate,
						double chemMax,
						String[] cellAttribs,
						Map<String, Integer> stateIdsMap) {
		super(stateIdsMap);
		
		myDrop = dropRate;
		myEvap = evapRate;
		myDiff = diffuseRate;
		myChemMax = chemMax;
		
		mySniffAngle = sniffAngle;
		mySniffThreshold = sniffThreshold;
		
		myWiggleAngle = wiggleAngle;
		myWiggleBias = wiggleBias;
		
		chemAmount = cellAttribs[0];
		angle = cellAttribs[1];
	}


	@Override
	public void calculateAndSetNextStates(GridModel grid) {
		myGrid = grid;
		
		for (Cell c: myGrid){
			diffuseAndEvapChemical(c);
		}
		
		for (Cell c: myGrid){
			moveSlime(c);
		}
		
		for (Cell c: myGrid){
			dropChemical(c);
			setID(c);
		}
		
	}

	private void diffuseAndEvapChemical(Cell c) {
		c.decrement(chemAmount, Math.min(c.getState(chemAmount), myEvap));
		
		if (c.getState(chemAmount) > 0){
			double diffAmount = c.getState(chemAmount) * myDiff / 2;
			
			c.decrement(chemAmount, diffAmount);
			
			Cell[] neighbors = myGrid.getNeighbors(c);
			for (Cell neighbor: neighbors){
				neighbor.increment(chemAmount, diffAmount / neighbors.length);
			}
		}
	}
	
	private void moveSlime(Cell c){
		if (hasSlime(c)){
			Cell bestMove = findSlimeMove(c);
			
			int newAngle = (int) c.getState(angle);
			//newAngle += random number in [-myWiggleAngle, myWiggleAngle]
			newAngle += myRand.nextDouble() * 2 * myWiggleAngle + myWiggleBias - myWiggleAngle;
			newAngle = (newAngle + 360) % 360;
			
			//Move the slime to bestMove
			bestMove.setNextStateAttrib(-1);
			bestMove.setNextStateAttrib(newAngle, angle);
			
			//Clear the slime off c
			c.setNextStateAttrib(0);
			c.setNextStateAttrib(0, angle);
			
		}
	}
	
	private Cell findSlimeMove(Cell c){
		Cell[] neighbors = myGrid.getDirectedNeighbors(c, c.getState(angle), mySniffAngle);
		
		Cell bestMove = null;
		double maxChem = mySniffThreshold;
		
		for (Cell neighbor: neighbors){
			if (neighbor != null && !hasSlime(neighbor) && !nextHasSlime(neighbor) && neighbor.getNextState(chemAmount) > maxChem){
				maxChem = neighbor.getNextState(chemAmount);
				bestMove = neighbor;
			}
		}
		
		if (bestMove == null)
			bestMove = myGrid.getDirectedNeighbor(c, c.getState(angle));
		
		return bestMove;
	}
	
	private void dropChemical(Cell c){
		if (nextHasSlime(c)){
			c.increment(chemAmount, Math.min(myDrop, myChemMax - c.getNextState(chemAmount)));
		}
	}

	private void setID(Cell c){
		if (nextHasSlime(c))
			c.setNextStateAttrib(-1 * (int) c.getNextState(chemAmount));
		else
			c.setNextStateAttrib((int) c.getNextState(chemAmount));
	}
	
	private boolean hasSlime(Cell c){
		return (c.getStateID() < 0);
	}
	
	private boolean nextHasSlime(Cell c){
		return (c.getNextStateID() < 0);
	}
}
