package views.grid;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.grid.Cell;
import resources.AppResources;
import views.styles.CellStyleGuide;

class GridViewUpdateSquare extends GridViewUpdate {

	public GridViewUpdateSquare(int width,int height,int size,Group root,CellStyleGuide csg) {
		super(width,height,size,root,csg);
	}

	@Override
	public void AddCell(int width, int height,int dimensions,Cell currcell,Group root,CellStyleGuide csg) {
		double cellwidth=(width*0.5)/dimensions;
		double cellheight=(height*0.5)/dimensions;
		int cellx=currcell.getLocation().getX();
		int celly=currcell.getLocation().getY();
		Rectangle cellrect = new Rectangle(width*(AppResources.OFFSET)+(cellwidth*cellx),height*(AppResources.OFFSET)+(cellheight*celly),cellwidth,cellheight);
		ColorCell(currcell,cellrect,csg);
        root.getChildren().add(cellrect);
        myShapeCollection.add(cellrect); 
	}

}
