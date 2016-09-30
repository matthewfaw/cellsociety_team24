package models.grid;

import models.Point;

public class GridModelTorus extends GridModel{

	public GridModelTorus() {
		super();
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
