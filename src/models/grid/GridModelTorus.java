package models.grid;

import java.awt.Dimension;
import java.util.Collection;

import models.Point;
import models.rules.Rule;

public class GridModelTorus extends GridModel{

	GridModelTorus(Collection<Cell> cells, Dimension dimension, Rule rule, int cellSides) {
		super(cells, dimension, rule, cellSides);
	}

	@Override
	public Cell getCell(Point p){
		Point dim = getDimensions();
		int xLength = dim.getX();
		int yLength = dim.getY();
		return super.getCell(	(p.getX() 	+ 	xLength) 	% xLength,
								(p.getY() 	+ 	yLength) 	% yLength);
	}
}
