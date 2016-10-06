package models.grid;

import java.util.Map;

import resources.CellShapes;
import resources.NeighborType;

/**
 * A class to hide choosing which class to use when initializing a cell.
 * @author Weston
 *
 */
public class CellFactory {
	
	/**
	 * Initializes a new cell based on the parameters. CellSides and Neighbors determine the Cell subclass used.
	 * @param x
	 * @param y
	 * @param stateId
	 * @param propertyMap
	 * @param cellSides
	 * @param neighbors
	 * @return the new cell
	 */
	public static Cell newCell(int x, int y, int stateId, Map<String, Double> propertyMap, int cellSides, NeighborType neighbors){

		switch (cellSides){
		case CellShapes.TRIANGULAR:
			return newTriangleCell( x, y, stateId, propertyMap, neighbors);
		case CellShapes.RECTANGULAR:
			return newSquareCell( x, y, stateId, propertyMap, neighbors);
		case CellShapes.HEXAGONAL:
			return newHexagonCell( x, y, stateId, propertyMap, neighbors);
		default:
			throw new IllegalArgumentException("Cannot tile a grid with regular polygons with this many sides: " + Integer.toString(cellSides));
		}
	}
	
	private static Cell newTriangleCell(int x, int y, int stateId, Map<String, Double> propertyMap, NeighborType neighbors){
		switch (neighbors){
		case Edges:
			return new CellTriangleEdge(x, y, stateId, propertyMap);
		case StrangeTriangle:
			return new CellTriangleStrange(x, y, stateId, propertyMap);
		case Vertices:
			return new CellTriangleVertex(x, y, stateId, propertyMap);
		default:
			throw new IllegalArgumentException("Unsupported neighbor type for Triangle: " + neighbors.toString());
		}
	}
	private static Cell newSquareCell(int x, int y, int stateId, Map<String, Double> propertyMap, NeighborType neighbors){
		switch (neighbors){
		case Edges:
			return new CellSquareEdge(x, y, stateId, propertyMap);
		case Vertices:
			return new CellSquareVertex(x, y, stateId, propertyMap);
		default:
			throw new IllegalArgumentException("Unsupported neighbor type for Square: " + neighbors.toString());
		}
	}
	private static Cell newHexagonCell(int x, int y, int stateId, Map<String, Double> propertyMap, NeighborType neighbors){
		switch (neighbors){
		case Edges:
			return new CellHexagon(x, y, stateId, propertyMap);
		default:
			throw new IllegalArgumentException("Unsupported neighbor type for Hexagon: " + neighbors.toString());
		}
	}
}
