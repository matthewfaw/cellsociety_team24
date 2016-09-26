package views.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import models.grid.Cell;
import views.styles.CellStyleGuide;

public abstract class GridViewUpdate {
	
	protected double myWidth;
	protected double myHeight;
	protected Dimension mySize;
	protected Group myRoot;
	protected int offset=50;
	protected CellStyleGuide myGuide;
	protected Collection<Cell> myCells;
	protected ArrayList<Shape> myShapeCollection=new ArrayList<Shape>();
	
	public GridViewUpdate(int width,int height,Dimension size,Group root,CellStyleGuide csg,Collection<Cell> cells){
		myWidth=width;
		myHeight=height;
		mySize=size;
		myRoot=root;
		myGuide=csg;
		myCells=cells;
	}

	
	
	public void makeGrid(Group root,int width,int height,Collection<Cell> cells,CellStyleGuide csg, Dimension dimensions){
		myGuide = csg;
		myRoot = root;
		for(Cell c: cells){
			AddCell(width,height,dimensions,c,myRoot,myGuide);
		}
	}
	public void StepGrid(Collection<Cell> cells){
		Iterator<Shape> shapeIterator= myShapeCollection.iterator();
		for(Cell c: cells){
			Shape myShape=shapeIterator.next();
			myRoot.getChildren().remove(myShape);
			ColorCell(c,myShape,myGuide);
			myRoot.getChildren().add(myShape);
		}
			
	}
	protected void ColorCell(Cell cell, Shape shape,CellStyleGuide csg) {
		int currcellstate =cell.getStateID();
		shape.setFill(Color.web(csg.getColor(currcellstate)));
	}



	public abstract void AddCell(int width, int height, Dimension size, Cell currcell,Group root,CellStyleGuide csg); 
	
}
