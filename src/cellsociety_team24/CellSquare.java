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
	public double[] vertices(Point p, double sides) {
		int x0 = p.getX();
		int y0 = p.getY();
		
		return new double[] {
				x0 + sides/2, y0 + sides/2,
				x0 + sides/2, y0 - sides/2,
				x0 - sides/2, y0 - sides/2,
				x0 - sides/2, y0 + sides/2
		};
	}

	@Override
	public double getWidth() {
		return 1;
	}

	@Override
	public double getHeight() {
		return 1;
	}
}
