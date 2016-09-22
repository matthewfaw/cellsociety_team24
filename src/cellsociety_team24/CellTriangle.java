package cellsociety_team24;

public class CellTriangle extends Cell {

	@Override
	public int getSides() {
		return 3;
	}

	@Override
	public Point[] getNeighbors() {
		int x = getState().getLocation().getX();
		int y = getState().getLocation().getY();

		if (x % 2 == 0){
			//Triangle pointing up
			return new Point[] {
					new Point(x + 1, y + 1),
					new Point(x - 1, y),
					new Point(x + 1, y)
			};
		} else {
			//Triangle pointing down
			return new Point[] {
					new Point(x , y + 1),
					new Point(x + 1, y - 1),
					new Point(x , y - 1)
			};
		}
		
	}

	@Override
	public double[] vertices(Point p, double sides) {
		int x = getState().getLocation().getX();
		
		int x0 = p.getX();
		int y0 = p.getY();
		
		//h = distance from center to middle of edge
		double h = sides / (4 * Math.sqrt(3));

		if (x % 2 == 0){
			//Triangle pointing up
			return new double[] {
					x0, y0 + 2 * h,
					x0 + sides/2, y0 - h,
					x0 - sides/2, y0 - h
			};
		} else {
			//Triangle pointing down
			return new double[] {
					x0 + sides/2, y0 + h,
					x0, y0 - h,
					x0 - sides/2, y0 + h
			};
		}
	}
	
	@Override
	public double getWidth() {
		return 1;
	}

	@Override
	public double getHeight() {
		return Math.sqrt(3/4);
	}
}
