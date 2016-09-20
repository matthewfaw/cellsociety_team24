package cellsociety_team24;

public class CellHex extends Cell {

	@Override
	public int getSides() {
		return 6;
	}

	@Override
	public Point[] getNeighbors() {
		int x = getState().getLocation().getX();
		int y = getState().getLocation().getY();

		//Points are in clockwise order staring from 12 o'clock.
		return new Point[] {
				new Point(x + 2, y),
				new Point(x + 1, y),
				new Point(x - 1, y),
				new Point(x - 2, y),
				new Point(x - 1, y - 1),
				new Point(x + 1, y - 1),
			
		};
	}

	@Override
	public void remakePoly(Point p) {
		// TODO Auto-generated method stub
		
	}

}
