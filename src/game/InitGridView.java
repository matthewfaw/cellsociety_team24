/*package game;


import javafx.scene.shape.Rectangle;
import cellsociety_team24.Cell;
import cellsociety_team24.CellState;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class InitGridView {
	private int offset=50;
	private int xmlwidth=20;
	private int xmlheight=20;
	
	public void makeGrid(Group root,int width,int height){
	//get grid type from XML parser
	//get gridSize(i.e. 20x20,30x30) from XML Parser	
		//if(Parse.getType(Square)){}
		double cellwidth=(width*0.5)/xmlwidth;
		double cellheight=(height*0.5)/xmlheight;
	//get array of cells from Controller
		for(int i=0;i<gridarray.size();i++){
			Cell currcell=new Cell();
			currcell=gridarray(i);
			int cellx=currcell.getXval();
			int celly=currcell.getYval();
			int currcellstate =currcell.getState();
			Rectangle cellrect = new Rectangle(cellwidth,cellheight, offset+(cellwidth*cellx), offset+(cellheight*celly));
	        switch(currcellstate){
	        	case 0: cellrect.setFill(Color.WHITE); ///HEXADECIMAL??
	        			break;
	        	case 1: cellrect.setFill(Color.BLUE);
	        			break;
	        	case 2 :cellrect.setFill(Color.RED); 
	        			break;
	        }
	        root.getChildren().add(cellrect);
	        
		}
	}
	
} */
