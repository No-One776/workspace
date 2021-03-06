package gvprojects.Test.practicecode;

import java.awt.geom.Point2D;

/**
 * Calculates the points on one side of a Koch snowflake. Specifically, given
 * two points, this class calculates the location of
 * <ul>
 * <li>the two points that divide the line into thirds, and</li>
 * <li>the point that forms an equilateral triangle with the two intermediate
 * points.</li>
 * </ul>
 * 
 * The peak is assumed to be on the left when traveling from start to end.
 * 
 * @author Zachary Kurmas
 */

public class PeakedLine {

	private Point2D.Double start;
	private Point2D.Double end;

	private Point2D.Double firstThird, peak, secondThird = null;

	/**
	 * Return the end point labeled "start"
	 * 
	 * @return the end point labeled "start"
	 */
	public Point2D.Double getStart() {
		return start;
	}

	/**
	 * Return the end point labeled "end"
	 * 
	 * @return the end point labeled "end"
	 */
	public Point2D.Double getEnd() {
		return end;
	}

	/**
	 * Return the point 1/3 the distance from start to end.
	 * 
	 * @return the point 1/3 the distance from start to end.
	 */
	public Point2D.Double getFirstThird() {
		return firstThird;
	}

	/**
	 * Return the point that makes an equilateral triangle with the "firstThird"
	 * and "secondThird" points.
	 * 
	 * @return the point that makes an equilateral triangle with the
	 *         "firstThird" and "secondThird" points.
	 */
	public Point2D.Double getPeak() {
		return peak;
	}

	/**
	 * Return the point 2/3 the distance from start to end.
	 * 
	 * @return the point 2/3 the distance from start to end.
	 */
	public Point2D.Double getSecondThird() {
		return secondThird;
	}

	/**
	 * Constructor. The peak will be on the left when traveling from
	 * {@code start} to {@code end}
	 * 
	 * @param start
	 *            the first end point
	 * @param end
	 *            the second end point
	 */
	public PeakedLine(Point2D.Double start, Point2D.Double end) {
		this.start = start;
		this.end = end;
		calculate();
	}

	private void calculate() {
		// Find the difference between x and y values, as well as the length.
		double diffx = (end.x - start.x); // change in x coordinates
		double diffy = (end.y - start.y); // change in y coordinates
		double length = Math.sqrt((start.x - end.x) * (start.x - end.x)
				+ (start.y - end.y) * (start.y - end.y));

		// Find the location of the two intermediate points on the line that
		// form the base of the peak.
		firstThird = new Point2D.Double(start.x + (diffx / 3.0), start.y
				+ diffy / 3.0);
		secondThird = new Point2D.Double(start.x + 2.0 * (diffx / 3.0), start.y
				+ 2.0 * diffy / 3.0);

		// Find the angle of the line above the x axis.
		double current_theta = Math.asin(diffy / length);
		if (diffx < 0) {
			current_theta = Math.PI - current_theta;
		}

		// The line in the center of the peak is perpendicular to the line we're
		// drawing. Thus, it's angle above the x axis is arcsin(1/2) degrees
		// greater than current_theta. (To see why this is, you'll need a
		// picture with the lines, angles, and lengths labeled.)
		double new_theta = current_theta + Math.asin(.5);

		// Find the distance of the peak from the line.
		double peak_height = length / 3.0 * Math.sqrt(3);

		// Calculate the location of the peak
		peak = new Point2D.Double(start.x + Math.cos(new_theta) * peak_height,
				start.y + Math.sin(new_theta) * peak_height);
	}
}
