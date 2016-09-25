package views.grid;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.grid.Cell;
import resources.AppResources;

class GridViewUpdateSquare extends GridViewUpdate {

	public GridViewUpdateSquare(int width,int height,int size,Group root) {
		super(width,height,size,root);
	}

	@Override
	public void AddCell(int width, int height,int size,Cell currcell,Group root) {
		double cellwidth=(width*0.5)/size;
		double cellheight=(height*0.5)/size;
		/*int cellx=currcell.getXval();
		int celly=currcell.getYval();
		int currcellstate =currcell.getState();
		Rectangle cellrect = new Rectangle(width*(AppResources.OFFSET)+(cellwidth*cellx),height*(AppResources.OFFSET)+(cellheight*celly),cellwidth,cellheight);
        switch(currcellstate){
        	case 0: cellrect.setFill(Color.WHITE); ///Parse.color(0)
        			break;
        	case 1: cellrect.setFill(Color.BLUE);//Parse.color(1)
        			break;
        	case 2 :cellrect.setFill(Color.RED); //Parse.color(2);
        			break;
        }
        root.getChildren().add(cellrect);
        myShapeCollection.add(cellrect); 
        */
	}

}
