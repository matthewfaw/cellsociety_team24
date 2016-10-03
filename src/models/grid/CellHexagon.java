package models.grid;

import java.util.ArrayList;
import java.util.Map;

import models.Point;

public class CellHexagon extends Cell {
	private static final int mySides = 6;

	public CellHexagon(int aX, int aY, int aStateId, Map<String, Double> aPropertyMap) {
		super(aX, aY, aStateId, aPropertyMap);
	}
	
	/**
	 * Returns a list of points marking the locations of the cell's neighbors, in clockwise order, starting at vertical.
	 */
	@Override
	public Point[] getNeighbors() {
		Point p = getLocation();
		int x = p.getX();
		int y = p.getY();

		return new Point[] {
				new Point(x + 2, y),
				new Point(x + 1, y),
				new Point(x - 1, y),
				new Point(x - 2, y),
				new Point(x - 1, y - 1),
				new Point(x + 1, y - 1),	
		};
	}

	/**
	 * @param angle
	 * @return A point containing the location of the neighbor nearest to angle degrees. Vertical is 0 degrees.
	 */
	@Override
	public Point getDirectedNeighbor(double angle) {
		angle = (angle + 360) % 360;
		int angleIndex = (int) Math.round(angle / (360 / mySides));
		
		return getNeighbors()[angleIndex];
	}

	/**
	 * @param angle
	 * @param range
	 * @return An array of points that are the locations of neighboring cells that are within angle +- range degree arc. Vertical is 0 degrees.
	 */
	@Override
	public Point[] getDirectedNeighbors(double angleStart, double angleRange) {
		angleStart = (angleStart + 360) % 360;
		angleRange = (angleRange + 360) % 360;
		
		double lowerAngle = (angleStart - angleRange) % 360;
		double upperAngle = (angleStart + angleRange) % 360;
		
		ArrayList<Point> result = new ArrayList<Point>();
		
		double i = 0;
		for (Point neighbor: getNeighbors()){
			double neighborAngle = (360 / mySides) * i;
			if ((lowerAngle <= neighborAngle && neighborAngle < upperAngle))
				result.add(neighbor);
			i++;
		}
		return result.toArray(new Point[result.size()]);
	}
}
