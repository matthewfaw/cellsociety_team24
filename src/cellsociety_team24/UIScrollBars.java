package cellsociety_team24;

import java.util.ArrayList;

import javafx.scene.control.ScrollBar;

public class UIScrollBars {

	private ArrayList<ScrollBar> ScrollBarList=new ArrayList<>();
	private ScrollBar SpeedScrollBar= new ScrollBar();
	private ScrollBar ParameterScrollBar= new ScrollBar();
	public UIScrollBars(ArrayList<ScrollBar> myScrollBarList) {
		//ScrollBarList=new ArrayList<>();
	}

	public ArrayList<ScrollBar> buildScrollBars(int scale) {
		addParameter(scale);
		//addSize(scale);
		addSpeed(scale);
		return ScrollBarList;
	}

	private void addSpeed(int scale) {
		SpeedScrollBar.setLayoutX(scale*0.625);
		SpeedScrollBar.setLayoutY(scale*0.875);	
		ScrollBarList.add(SpeedScrollBar);
	}

	/*private void addSize(int scale) {
		ScrollBar SizeScrollBar= new ScrollBar();
		SizeScrollBar.setLayoutX(scale*0.625);
		SizeScrollBar.setLayoutY(scale*0.75);	
		ScrollBarList.add(SizeScrollBar);
	}*/

	private void addParameter(int scale) {
		ParameterScrollBar.setLayoutX(scale*0.625);
		ParameterScrollBar.setLayoutY(scale*0.625);	
		ScrollBarList.add(ParameterScrollBar);
	}
	
	public void getSpeed(){
		SpeedScrollBar.getValue();
	}
	public void getParameter(){
		ParameterScrollBar.getValue();
	}
	
}
