/**
 * 
 */
package gvprojects.Test.practicecode;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/**
 * @author rohrj
 * @version Mar 20, 2013
 */
public class KochSnowflake extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		drawKoch(g, this, 9, getWidth() / 3, 10, getWidth() / 3, 75);
	}

	public static void drawKoch(Graphics g, JPanel p, int depth, int x, int y,
			int width, int height) {
		if (depth == 0)
			return;
		else {
			depth--;
			width = width / 2;

			PeakedLine line = new PeakedLine((Point2D.Double)(x), (Point2D.Double)(y));

			// Get Left side of Triangle
			drawKoch(g, p, depth, line.getFirstThird(), line.getPeak(), width, height);
			g.drawLine(x, line.getFirstThird(), x + width, y + height);
			// Get right side of Triangle

			
			drawKoch(g, p, depth, x, y, width, height);
			g.drawLine(x, y, x - width, y + height);
			
			// int halfwidth = width / 2;
			// g.drawLine(x, y, x - halfwidth, y + height);
			// drawKoch(g, p, depth - 1, x + halfwidth, y + height, width,
			// height);
			//
			// g.drawLine(x, y, x + halfwidth, y + height);
			// drawKoch(g, p, depth - 1, x + halfwidth, y + height, halfwidth,
			// height);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Fractal();
	}

}
