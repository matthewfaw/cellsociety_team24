//package cellsociety_team24;
//
//import java.util.ArrayList;
//
//import javafx.scene.control.ScrollBar;
//
//public class UIScrollBars {
//
//	private ArrayList<ScrollBar> ScrollBarList=new ArrayList<>();
//	private ScrollBar SpeedScrollBar= new ScrollBar();
//	private ScrollBar ParameterScrollBar= new ScrollBar();
//	
//	public UIScrollBars(ArrayList<ScrollBar> myScrollBarList) {
//		//ScrollBarList=new ArrayList<>();
//	}
//
//	public ArrayList<ScrollBar> buildScrollBars(int scale) {
//		addParameter(scale);
//		addSpeed(scale);
//		return ScrollBarList;
//	}
//
//	private void addSpeed(int scale) {
//		SpeedScrollBar.setLayoutX(scale*SplashScreen.FIVE_EIGHTHS);
//		SpeedScrollBar.setLayoutY(scale*SplashScreen.SEVEN_EIGHTHS);	
//		ScrollBarList.add(SpeedScrollBar);
//	}
//
//	private void addParameter(int scale) {
//		ParameterScrollBar.setLayoutX(scale*SplashScreen.FIVE_EIGHTHS);
//		ParameterScrollBar.setLayoutY(scale*SplashScreen.FIVE_EIGHTHS);	
//		ScrollBarList.add(ParameterScrollBar);
//	}
//	
//	public void getSpeed(){
//		SpeedScrollBar.getValue();
//	}
//	public void getParameter(){
//		ParameterScrollBar.getValue();
//	}
//	
//}
