package models.grid;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Map;

import models.Point;
import models.rules.Rule;

/**
 * A class that overrides the GridModel's getCell method, making the grid edges connected to the edge opposite.
 * @author Weston
 *
 */
public class GridModelTorus extends GridModel{

	GridModelTorus(Collection<Cell> cells, Dimension dimension, Rule rule, int cellSides, Map<Integer, Map<String, Double>> aStateInfo) {
		super(cells, dimension, rule, cellSides, aStateInfo);
	}

	/**
	 * Finds the cell at Point p, with toroidal wrapping ((-1, -1) = (0, 0))
	 * @param p
	 */
	@Override
	public Cell getCell(Point p){
		Point dim = getDimensions();
		int xLength = dim.getX() ;
		int yLength = dim.getY();
		
		int xAdj = p.getX() % xLength;
		int yAdj = p.getY() % yLength;
		
		if (xAdj < 0)
			xAdj += xLength;
		if (yAdj < 0)
			yAdj += yLength;
		
		
		return super.getCell(xAdj, yAdj);
	}
}
