package views.grid;

import java.awt.Dimension;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
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
		Polygon celltriangle=new Polygon();
		if(((cellx+celly)%2)==0){
			celltriangle.getPoints().addAll(new Double[]{
				width*(AppResources.OFFSET)+(cellx*cellwidth*AppResources.HALF), height*(AppResources.OFFSET)+(cellheight*celly)+cellheight*AppResources.HALF,
				width*(AppResources.OFFSET)+(cellx*cellwidth*AppResources.HALF)-cellwidth*AppResources.HALF, height*(AppResources.OFFSET)+(cellheight*celly)-cellheight*AppResources.HALF,
				width*(AppResources.OFFSET)+(cellx*cellwidth*AppResources.HALF)+cellwidth*AppResources.HALF, height*(AppResources.OFFSET)+(cellheight*celly)-cellheight*AppResources.HALF	
			});
		}
		else{
			celltriangle.getPoints().addAll(new Double[]{
				width*(AppResources.OFFSET)+(cellx*cellwidth*AppResources.HALF), height*(AppResources.OFFSET)+(cellheight*celly)+cellheight*AppResources.HALF,
				width*(AppResources.OFFSET)+(cellx*cellwidth*AppResources.HALF)-cellwidth*AppResources.HALF, height*(AppResources.OFFSET)+(cellheight*celly)-cellheight*AppResources.HALF,
				width*(AppResources.OFFSET)+(cellx*cellwidth*AppResources.HALF)+cellwidth*AppResources.HALF, height*(AppResources.OFFSET)+(cellheight*celly)-cellheight*AppResources.HALF	
			});

		}
		cellMap.put(currcell, celltriangle);
		colorCell(currcell,csg);
		root.getChildren().add(celltriangle);
        myShapeCollection.add(celltriangle); 
	}

}
