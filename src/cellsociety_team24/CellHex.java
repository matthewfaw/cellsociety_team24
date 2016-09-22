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
	public double[] vertices(Point p, double sides) {
		int x0 = p.getX();
		int y0 = p.getY();
		
		double h = sides * Math.sqrt(3) / 2;
		
		return new double[] {
				x0 + sides/2, y0 + h,
				x0 + sides, y0,
				x0 + sides/2, y0 - h,
				x0 - sides/2, y0 - h,
				x0 - sides, y0,
				x0 - sides/2, y0 + h
		};
	}

	@Override
	public double getWidth() {
		return 2;
	}

	@Override
	public double getHeight() {
		return Math.sqrt(3);
	}
	

}
