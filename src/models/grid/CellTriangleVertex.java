package models.grid;

import java.util.Map;

import models.Point;

/**
 * A triangular cell that regards all cells sharing a vertex with it as it's neighbors.
 * @author Weston
 *
 */
public class CellTriangleVertex extends CellTriangle {

	public CellTriangleVertex(int aX, int aY, int aStateId, Map<String, Double> aPropertyMap) {
		super(aX, aY, aStateId, aPropertyMap);
	}

	/**
	 * @return An array of points containing locations of neighbors of the cell, in clockwise order, starting at vertical.
	 */
	@Override
	public Point[] getNeighbors() {
		return vertexNeighbors();
	}

}
