package views.grid;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.grid.Cell;

class GridViewUpdateSquare extends GridViewUpdate {

	public GridViewUpdateSquare(int width,int height,int size,Group root) {
		super(width,height,size,root);
	}

	@Override
	public void AddCell(int width, int height,int size,Cell currcell,Group root) {
		/*double cellwidth=(width*0.5)/xmlwidth;
		double cellheight=(height*0.5)/xmlheight;
		int cellx=currcell.getXval();
		int celly=currcell.getYval();
		int currcellstate =currcell.getState();
		cellrect = new Rectangle(cellwidth,cellheight, width*(SplashScreen.OFFSET)+(cellwidth*cellx), width*(SplashScreen.OFFSET)+(cellheight*celly));
        switch(currcellstate){
        	case 0: cellrect.setFill(Color.WHITE); ///HEXADECIMAL??
        			break;
        	case 1: cellrect.setFill(Color.BLUE);
        			break;
        	case 2 :cellrect.setFill(Color.RED); 
        			break;
        }
        root.getChildren().add(cellrect);
        myShapeCollection.add(cellrect);
	*/}

}
