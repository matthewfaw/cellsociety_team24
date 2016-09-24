package views;

import controllers.AppController;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import views.styles.CellStyleGuide;

public class AppScene {

	private CellStyleGuide fCellStyleGuide;
	private Group fAppRoot;
	private int fWidth;
	private int fHeight;
	
	private AppController fAppController;
	
	
	public AppScene(int aHeight, int aWidth, AppController aAppController)
	{
		fAppRoot = new Group();
		fHeight = aHeight;
		fWidth = aWidth;
		fAppController = aAppController;
		// Add scene setup here
		//...

		setBackground();
        setButtons();
        setScrollBars();
        setLabels();
        setGrid();
	}

	public Group getRoot()
	{
		return fAppRoot;
	}

	private void setBackground()
	{
	}
	
	private void setButtons()
	{
		/*
		 * just an example 
		 * 
		 * Button fileExplorerButton = new Button();
		 * fileButton.setOnAction(e -> fAppController.fileButtonHandler());
		
		 * Button playButton = new Button();
		 * playButton.setOnAction(e -> fAppController.onPlayButtonPressed());
		 */
	}
	
	private void setScrollBars()
	{
	}
	private void setLabels()
	{
	}
	private void setGrid()
	{
	}
	/**
	 * This method is intended to notify the Scene of 
	 * information about the cells in the grid that comes
	 * from the XML
	 * This method should be used during grid setup
	 * @param aStyleGuide
	 */
	public void setCellStyleGuide(CellStyleGuide aStyleGuide)
	{
	}

}
