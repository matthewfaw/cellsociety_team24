package cellsociety_team24;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class GridViewUpdate {
	
	protected double myWidth;
	protected double myHeight;
	protected int mySize;
	protected Group myRoot;
	protected int offset=50;
	protected ArrayList<Shape> myShapeCollection=new ArrayList<Shape>();
	
	public GridViewUpdate(double width,double height,int size,Group root){
		myWidth=width;
		myHeight=height;
		mySize=size;
		myRoot=root;
	}

	
	
	public void makeGrid(Group root,int width,int height,int size){
		//get grid type from XML parser
		//get gridSize(i.e. 20x20,30x30) from XML Parser	
			//if(Parse.getType(Square)){}
		//get array of cells from Controller
			for(int i=0;i<gridarray.size();i++){
				Cell currcell=new Cell();
				currcell=gridarray(i);
				AddCell(width,height,size,currcell,root);
			}
		}
	public void StepGrid(Group root){
		Iterator<Shape> shapeIterator= myShapeCollection.iterator();
		for(int i=0;i<gridarray.size();i++){
			Shape myShape=shapeIterator.next();
			//root.getChildren().remove(myShape);
			Cell currcell=new Cell();
			currcell=gridarray(i);
			int currcellstate =currcell.getState();
			switch(currcellstate){
        	case 0: myShape.setFill(Color.WHITE); ///HEXADECIMAL??
        			break;
        	case 1: myShape.setFill(Color.BLUE);
        			break;
        	case 2 :myShape.setFill(Color.RED); 
        			break;
			}
			root.getChildren().add(myShape);
		}
			
	}
	public abstract void AddCell(int width, int height, int size, Cell currcell,Group root); 
	
}
