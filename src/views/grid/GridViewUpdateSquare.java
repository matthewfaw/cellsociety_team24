package views.grid;

import java.awt.Dimension;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import models.grid.Cell;
import views.styles.CellStyleGuide;

/**
 * A class used to build the Grid View with rectangular or square cells
 * @author Guhan Muruganandam
 *
 */

public class GridViewUpdateSquare extends GridViewUpdate {

	public GridViewUpdateSquare(int width,int height,Dimension size,Group root,CellStyleGuide csg,Collection<Cell> cells) {
		super(width,height,size,root,csg,cells);
	}

	@Override
	public void addCell(Cell currcell) {
		getCellLocation(currcell);
		Rectangle cellrect = new Rectangle(xOffset+(myCellWidth*myCellx),yOffset+(myCellHeight*myCelly),myCellWidth,myCellHeight);
		cellSetup(currcell,cellrect);
	}

}
