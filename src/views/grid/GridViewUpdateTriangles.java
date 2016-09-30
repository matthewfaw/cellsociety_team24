package views.grid;

import java.awt.Dimension;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import models.grid.Cell;
import resources.AppResources;
import views.styles.CellStyleGuide;

public class GridViewUpdateTriangles extends GridViewUpdate {

	public GridViewUpdateTriangles(int width,int height,Dimension size,Group root,CellStyleGuide csg,Collection<Cell> cells) {
		super(width,height,size,root,csg,cells);
	}

	@Override
	public void AddCell(int width, int height, Dimension dimensions, Cell currcell, Group root, CellStyleGuide csg) {
		double cellwidth=(width*0.5)/(dimensions.getWidth());
		double cellheight=(height*0.5)/(dimensions.getHeight());
		int cellx=currcell.getLocation().getX();
		int celly=currcell.getLocation().getY();
		Rectangle cellrect = new Rectangle(width*(AppResources.OFFSET)+(cellwidth*cellx),height*(AppResources.OFFSET)+(cellheight*celly),cellwidth,cellheight);
//		ColorCell(currcell,cellrect,csg);
        root.getChildren().add(cellrect);
        myShapeCollection.add(cellrect); 
	}

}
