package cellsociety_team24;

public class CellTriangle extends Cell {
	//True if point up, false if point down.
	private boolean myOrientation;

	@Override
	public int getSides() {
		return 3;
	}

	@Override
	public Point[] getNeighbors() {
		int x = getState().getLocation().getX();
		int y = getState().getLocation().getY();
		//Either 1,0; -1,0; 1,1;
		//Or 	 0,1; 0,-1; 1,-1;
		
		//TODO: find orientation from pointlist?
		if (myOrientation){
			return new Point[] {
					new Point(x + 1, y + 1),
					new Point(x - 1, y),
					new Point(x + 1, y)
			};
		} else {
			return new Point[] {
					new Point(x , y + 1),
					new Point(x + 1, y - 1),
					new Point(x , y - 1)
			};
		}
		
	}

	@Override
	public void remakePoly(Point p) {
		// TODO Auto-generated method stub
		
	}
	
}
