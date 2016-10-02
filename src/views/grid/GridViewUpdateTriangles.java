package views.grid;

import java.awt.Dimension;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import models.grid.Cell;
import resources.AppResources;
import views.styles.CellStyleGuide;

/**
 * A class used to build the Grid View with Triangular cells
 * @author Guhan Muruganandam
 *
 */

public class GridViewUpdateTriangles extends GridViewUpdate {

	public GridViewUpdateTriangles(int width,int height,Dimension dimensions,Group root,CellStyleGuide csg,Collection<Cell> cells) {
		super(width,height,dimensions,root,csg,cells);
	}

	@Override
	public void addCell(Cell currcell) {
		double triangleCellWidth=myCellWidth*2;
		getCellLocation(currcell);
		Polygon celltriangle=new Polygon();
		if(((myCellx+myCelly)%2)==0){
			celltriangle.getPoints().addAll(new Double[]{
                xOffset+(myCellx*triangleCellWidth*AppResources.HALF), yOffset+(myCellHeight*myCelly)+myCellHeight*AppResources.HALF,
                xOffset+(myCellx*triangleCellWidth*AppResources.HALF)-triangleCellWidth*AppResources.HALF, yOffset+(myCellHeight*myCelly)-myCellHeight*AppResources.HALF,
                xOffset+(myCellx*triangleCellWidth*AppResources.HALF)+triangleCellWidth*AppResources.HALF, yOffset+(myCellHeight*myCelly)-myCellHeight*AppResources.HALF	
			});
		}
		else{
			celltriangle.getPoints().addAll(new Double[]{
				xOffset+(myCellx*triangleCellWidth*AppResources.HALF)-triangleCellWidth*AppResources.HALF, yOffset+(myCellHeight*myCelly)+myCellHeight*AppResources.HALF,
				xOffset+(myCellx*triangleCellWidth*AppResources.HALF)+triangleCellWidth*AppResources.HALF, yOffset+(myCellHeight*myCelly)+myCellHeight*AppResources.HALF,
				xOffset+(myCellx*triangleCellWidth*AppResources.HALF), yOffset+(myCellHeight*myCelly)-myCellHeight*AppResources.HALF	
			});

		}
		cellSetup(currcell,celltriangle);
	}

}