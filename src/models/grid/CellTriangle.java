package models.grid;

import java.util.ArrayList;
import java.util.Map;

import models.Point;

/**
 * A Cell shaped like a triangle.
 * @author Weston
 *
 */
abstract public class CellTriangle extends Cell {
	private static final int mySides = 3;

	public CellTriangle(int aX, int aY, int aStateId, Map<String, Double> aPropertyMap) {
		super(aX, aY, aStateId, aPropertyMap);
	}
	
	/**
	 * Returns a list of points marking the locations of the cell's neighbors.
	 */
	abstract public Point[] getNeighbors();
	
	/**
	 * @param angle
	 * @return A point containing the location of the neighbor nearest to angle degrees. Vertical is 0 degrees.
	 */
	@Override
	public Point getDirectedNeighbor(double angle) {
		angle = (angle + 360) % 360;
		int angleIndex;

		if (getLocation().getX() % 2 == 0){
			//Triangle pointing up
			angleIndex = (int) ((angle + (180 / mySides)) / (360 / mySides));
		} else {
			//Triangle pointing down
			angleIndex = (int) Math.round(angle / (360 / mySides));
		}
		return edgeNeighbors()[angleIndex];
	}
	
	
	/**
	 * @param angle
	 * @param range
	 * @return An array of points that are the locations of neighboring cells that are within angle +- range degree arc. Vertical is 0 degrees.
	 */
	@Override
	public Point[] getDirectedNeighbors(double angle, double range){
		angle = (angle + 360) % 360;
		
		double lowerAngle = (angle - range) % 360;
		double upperAngle = (angle + range) % 360;
		
		ArrayList<Point> result = new ArrayList<Point>();
		
		if ((getLocation().getX() + 2) % 2 != 0){
			lowerAngle += 360 / (mySides * 2);
			upperAngle += 360 / (mySides * 2);
		}
			
		double i = 0;
		Point[] neighbors = edgeNeighbors();
		for (Point neighbor: neighbors){
			double neighborAngle = (360 / neighbors.length) * i;
			if ((lowerAngle <= neighborAngle && neighborAngle < upperAngle))
				result.add(neighbor);
			i++;
		}
		return result.toArray(new Point[result.size()]);
	}
	
	/**
	 * @return An array of Points that are the locatation's of the cells with which this cell shares an edge.
	 */
	public Point[] edgeNeighbors() {
		Point p = getLocation();
		int x = p.getX();
		int y = p.getY();

		switch (x % 4){
			case 0:
				return new Point[] {
						new Point(x, y + 1),
						new Point(x, y - 1),
						new Point(x - 1, y + 1),
				};
				
			case 1:
				return new Point[] {
						new Point(x , y + 1),
						new Point(x + 1, y - 1),
						new Point(x, y - 1),
				};
			case 2:
				return new Point[] {
						new Point(x + 1, y + 1),
						new Point(x, y - 1),
						new Point(x, y + 1),
				};
				
			case 3:
				return new Point[] {
						new Point(x, y + 1),
						new Point(x, y - 1),
						new Point(x - 1, y - 1),
				};
			default:
				throw new UnsupportedOperationException(Integer.toString(x % 4) + "Not a member of Z (mod 4) It might be negative.");
		}
	}
	
	/**
	 * @return An array of Points that are the locatation's of this cell's neighbors, as defined by the assignment.
	 */
	public Point[] strangeTriangleNeighbors() {
		Point p = getLocation();
		int x = p.getX();
		int y = p.getY();

		switch (x % 4){
			case 0:
				return new Point[] {
						new Point(x, y + 3),
						new Point(x, y + 2),
						new Point(x, y + 1),
						new Point(x, y - 2),
						new Point(x, y - 1),
						new Point(x - 1, y - 2),
						new Point(x - 1, y + 1),
						new Point(x - 1, y + 2),
				};
				
			case 1:
				return new Point[] {
						new Point(x, y + 1),
						new Point(x + 1, y + 2),
						new Point(x + 1, y - 1),
						new Point(x + 1, y - 2),
						new Point(x, y - 3),
						new Point(x, y - 2),
						new Point(x, y - 1),
						new Point(x, y + 2),
				};
			case 2:
				return new Point[] {
						new Point(x , y + 3),
						new Point(x + 1, y + 2),
						new Point(x + 1, y + 1),
						new Point(x + 1, y - 2),
						new Point(x, y - 1),
						new Point(x, y - 2),
						new Point(x, y + 1),
						new Point(x, y + 2),
				};
				
			case 3:
				return new Point[] {
						new Point(x, y + 1),
						new Point(x, y + 2),
						new Point(x, y - 1),
						new Point(x, y - 2),
						new Point(x, y - 3),
						new Point(x - 1, y - 2),
						new Point(x - 1, y - 1),
						new Point(x - 1, y + 2),
				};
			default:
				throw new UnsupportedOperationException(Integer.toString(x % 4) + "Not a member of Z (mod 4) It might be negative.");
		}
	}
	
	/**
	 * @return An array of Points that are the locatation's of the cells with which this cell shares a vertex.
	 */
	public Point[] vertexNeighbors() {
		Point p = getLocation();
		int x = p.getX();
		int y = p.getY();

		switch (x % 4){
			case 0:
				return new Point[] {
						new Point(x, y + 3),
						new Point(x, y + 2),
						new Point(x, y + 1),
						new Point(x + 1, y),
						new Point(x + 1, y - 1),
						new Point(x, y - 2),
						new Point(x, y - 1),
						new Point(x - 1, y - 2),
						new Point(x - 1, y - 1),
						new Point(x - 1, y),
						new Point(x - 1, y + 1),
						new Point(x - 1, y + 2),
				};
				
			case 1:
				return new Point[] {
						new Point(x, y + 1),
						new Point(x + 1, y + 2),
						new Point(x + 1, y + 1),
						new Point(x + 1, y),
						new Point(x + 1, y - 1),
						new Point(x + 1, y - 2),
						new Point(x, y - 3),
						new Point(x, y - 2),
						new Point(x - 1, y),
						new Point(x - 1, y + 1),
						new Point(x, y - 1),
						new Point(x, y + 2),
				};
			case 2:
				return new Point[] {
						new Point(x , y + 3),
						new Point(x + 1, y + 2),
						new Point(x + 1, y + 1),
						new Point(x + 1, y),
						new Point(x + 1, y - 1),
						new Point(x + 1, y - 2),
						new Point(x, y - 1),
						new Point(x, y - 2),
						new Point(x - 1, y - 1),
						new Point(x - 1, y),
						new Point(x, y + 1),
						new Point(x, y + 2),
				};
				
			case 3:
				return new Point[] {
						new Point(x, y + 1),
						new Point(x, y + 2),
						new Point(x + 1, y + 1),
						new Point(x + 1, y),
						new Point(x, y - 1),
						new Point(x, y - 2),
						new Point(x, y - 3),
						new Point(x - 1, y - 2),
						new Point(x - 1, y - 1),
						new Point(x - 1, y),
						new Point(x - 1, y + 1),
						new Point(x - 1, y + 2),
				};
			default:
				throw new UnsupportedOperationException(Integer.toString(x % 4) + "Not a member of Z (mod 4) It might be negative.");
		}
	}
}
