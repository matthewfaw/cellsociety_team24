package models.grid;

import java.util.Map;

import models.Point;

public class CellSquareEdge extends CellSquare {

	public CellSquareEdge(int aX, int aY, int aStateId, Map<String, Double> aPropertyMap) {
		super(aX, aY, aStateId, aPropertyMap);
	}

	/**
	 * @return An array of points containing locations of neighbors of the cell, in clockwise order, starting at vertical.
	 */
	@Override
	public Point[] getNeighbors() {
		return edgeNeighbors();
	}

}
