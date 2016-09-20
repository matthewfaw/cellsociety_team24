package cellsociety_team24;

public class CellSquare extends Cell {

	@Override
	public int getSides() {
		return 4;
	}

	@Override
	public Point[] getNeighbors() {
		int x = getState().getLocation().getX();
		int y = getState().getLocation().getY();

		return new Point[] {
				new Point(x, y + 1),
				new Point(x + 1, y),
				new Point(x, y - 1),
				new Point(x - 1, y),
		};
	}

	@Override
	public void remakePoly(Point p) {
		// TODO Auto-generated method stub
		
	}

}
