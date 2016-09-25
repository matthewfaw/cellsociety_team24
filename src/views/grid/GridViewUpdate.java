package views.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import models.grid.Cell;

abstract class GridViewUpdate {
	
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

	
	
	public void makeGrid(Group root,int width,int height,Collection<Cell> cells){
		//get gridSize(i.e. 20x20,30x30) from XML Parser
		//int gridsize=Parse.getgridSize();
			for(Cell c: cells){
			//	AddCell(width,height,gridsize,c,root);
			}
		}
	public void StepGrid(Group root,Collection<Cell> cells){
		Iterator<Shape> shapeIterator= myShapeCollection.iterator();
		for(Cell c: cells){
			Shape myShape=shapeIterator.next();
			root.getChildren().remove(myShape);
			/*int currcellstate =c.getState();
			switch(currcellstate){
        	case 0: myShape.setFill(Color.WHITE); ///HEXADECIMAL??
        			break;
        	case 1: myShape.setFill(Color.BLUE);
        			break;
        	case 2 :myShape.setFill(Color.RED); 
        			break;
			}*/
			root.getChildren().add(myShape);
		}
			
	}
	public abstract void AddCell(int width, int height, int size, Cell currcell,Group root); 
	
}
