package game;

import java.util.ArrayList;

import javafx.scene.control.ScrollBar;

public class UIScrollBars {

	private ArrayList<ScrollBar> ScrollBarList=new ArrayList<>();
	
	public UIScrollBars(ArrayList<ScrollBar> myScrollBarList) {
		//ScrollBarList=new ArrayList<>();
	}

	public ArrayList<ScrollBar> BuildScrollBars() {
		addParameter();
		addSize();
		addSpeed();
		return ScrollBarList;
	}

	private void addSpeed() {
		ScrollBar SpeedScrollBar= new ScrollBar();
		SpeedScrollBar.setLayoutX(500);
		SpeedScrollBar.setLayoutY(700);	
		ScrollBarList.add(SpeedScrollBar);
	}

	private void addSize() {
		ScrollBar SizeScrollBar= new ScrollBar();
		SizeScrollBar.setLayoutX(500);
		SizeScrollBar.setLayoutY(600);	
		ScrollBarList.add(SizeScrollBar);
	}

	private void addParameter() {
		ScrollBar ParameterScrollBar= new ScrollBar();
		ParameterScrollBar.setLayoutX(500);
		ParameterScrollBar.setLayoutY(500);	
		ScrollBarList.add(ParameterScrollBar);
		
	}
	
}
