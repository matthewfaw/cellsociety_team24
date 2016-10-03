package views.grid;
 
import java.awt.Dimension;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import models.grid.Cell;
import resources.AppResources;
import views.styles.CellStyleGuide;

/**
 * A class used to build the Grid View with hexagonal cells
 * @author Guhan Muruganandam
 *
 */

public class GridViewUpdateHexagon extends GridViewUpdate {
	
	private double myEdge;
	private double xOddShift;
	private double yOddShift;
	private double xPropagate;
	private double yPropagate;
	
	public GridViewUpdateHexagon(int width,int height,Dimension dimensions,Group root,CellStyleGuide csg,Collection<Cell> cells) {
		super(width,height,dimensions,root,csg,cells);
		myEdge=myCellHeight*AppResources.HALF;
		xOddShift=myCellWidth*AppResources.HALF;
		yOddShift=myEdge*AppResources.HALF+myHeight*AppResources.HALF;
	}

	@Override
	public void addCell(Cell currcell) {
		getCellLocation(currcell);
		xPropagate=myCellx*myCellWidth;
		yPropagate=myCelly*(myCellHeight+myEdge)*AppResources.HALF;	
		Polygon cellhexagon=new Polygon();
		if(((myCelly)%2)==0){
			BuildHexagon(cellhexagon);
		} else {
			BuildHexagonShifted(cellhexagon);	
		}
		cellSetup(currcell,cellhexagon); 
	}

	private void BuildHexagon(Polygon cellhexagon) {
		cellhexagon.getPoints().addAll(new Double[]{
			// Moving clockwise from the top
			xOffset+xPropagate                            ,yOffset+yPropagate-myCellHeight*AppResources.HALF,
			xOffset+xPropagate+myCellWidth*AppResources.HALF,yOffset+yPropagate-myEdge*AppResources.HALF,
			xOffset+xPropagate+myCellWidth*AppResources.HALF,yOffset+yPropagate+myEdge*AppResources.HALF,
			xOffset+xPropagate                            ,yOffset+yPropagate+myCellHeight*AppResources.HALF,
			xOffset+xPropagate-myCellWidth*AppResources.HALF,yOffset+yPropagate+myEdge*AppResources.HALF,
			xOffset+xPropagate-myCellWidth*AppResources.HALF,yOffset+yPropagate-myEdge*AppResources.HALF
			});
	}
	private void BuildHexagonShifted(Polygon cellhexagon) {
		cellhexagon.getPoints().addAll(new Double[]{
			//Moving clockwise from the top
				xOffset+xPropagate+xOddShift                            ,yOffset+yPropagate-myCellHeight*AppResources.HALF,
				xOffset+xPropagate+xOddShift+myCellWidth*AppResources.HALF,yOffset+yPropagate-myEdge*AppResources.HALF,
				xOffset+xPropagate+xOddShift+myCellWidth*AppResources.HALF,yOffset+yPropagate+myEdge*AppResources.HALF,
				xOffset+xPropagate+xOddShift                            ,yOffset+yPropagate+myCellHeight*AppResources.HALF,
				xOffset+xPropagate+xOddShift-myCellWidth*AppResources.HALF,yOffset+yPropagate+myEdge*AppResources.HALF,
				xOffset+xPropagate+xOddShift-myCellWidth*AppResources.HALF,yOffset+yPropagate-myEdge*AppResources.HALF
				});
	}
}
