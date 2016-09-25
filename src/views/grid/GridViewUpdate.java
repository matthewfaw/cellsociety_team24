package views.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import models.grid.Cell;
import views.styles.CellStyleGuide;

abstract class GridViewUpdate {
	
	protected double myWidth;
	protected double myHeight;
	protected int mySize;
	protected Group myRoot;
	protected int offset=50;
	protected CellStyleGuide myGuide;
	protected ArrayList<Shape> myShapeCollection=new ArrayList<Shape>();
	
	public GridViewUpdate(int width,int height,int size,Group root,CellStyleGuide csg){
		myWidth=width;
		myHeight=height;
		mySize=size;
		myRoot=root;
		myGuide=csg;
	}

	
	
	public void makeGrid(Group root,int width,int height,Collection<Cell> cells,CellStyleGuide csg, int dimensions){
			for(Cell c: cells){
				AddCell(width,height,dimensions,c,root,csg);
			}
		}
	public void StepGrid(Group root,Collection<Cell> cells,CellStyleGuide csg){
		Iterator<Shape> shapeIterator= myShapeCollection.iterator();
		for(Cell c: cells){
			Shape myShape=shapeIterator.next();
			root.getChildren().remove(myShape);
			ColorCell(c,myShape,csg);
			root.getChildren().add(myShape);
		}
			
	}
	protected void ColorCell(Cell cell, Shape shape,CellStyleGuide csg) {
		int currcellstate =cell.getStateID();
		shape.setFill(Color.web(csg.getColor(currcellstate)));
	}



	public abstract void AddCell(int width, int height, int size, Cell currcell,Group root,CellStyleGuide csg); 
	
}
