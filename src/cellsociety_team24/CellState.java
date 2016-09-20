package cellsociety_team24;

import javafx.scene.paint.Color;

public class CellState {
	private Point myLocation;
	private int myState;

	public Color getColor() {
		switch (myState){
			case 0:
				return Color.web("0000FF", 1.0);
			case 1:
				return Color.web("00FF00", 1.0);
			case 2:
				return Color.web("FF0000", 1.0);
			default:
				return Color.web("000000", 0.0);
				
		}
	}
	
	public Point getLocation(){
		return myLocation;
	}

}
