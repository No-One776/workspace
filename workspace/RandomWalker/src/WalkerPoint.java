import java.awt.*; // for Point
import java.util.*; // for Random
import javax.swing.*; // for JFrame

/********************************************************
 * Represents a point that moves itself between draws.
 * 
 * @author Zachary Kurmas
 *********************************************************/
 
@SuppressWarnings("serial")
public class WalkerPoint extends Point
{
	public static final int DEFAULT_POINT_SIZE = 25;
	public static final int DEFAULT_SPEED = 3;
	public static final Color DEFAULT_COLOR = Color.black;

	private Random rand;
	protected Color color;
	protected int pointSize; 
	protected int speed;
	
	public WalkerPoint(int pX, int pY, Color pColor, 
			int ps_in, int speed_in )
	{
		super(pX,pY);
		color = pColor;
		rand = new Random();
		pointSize = ps_in;
		speed = speed_in;
	}

	public WalkerPoint(int pX, int pY, Color pColor) 
	{
		this(pX, pY, pColor, DEFAULT_POINT_SIZE, DEFAULT_SPEED);
	}
	
	
	public WalkerPoint(Color pColor)
	{
		this(0, 0, pColor);
	}

	public WalkerPoint()
	{
		this(DEFAULT_COLOR);
	}



	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x - (pointSize /2 ), 
				y - (pointSize / 2), 
				pointSize, pointSize);
	}

	public boolean walk(JPanel window)
	{
		translate(speed*(rand.nextInt(3) - 1),
				speed*(rand.nextInt(3) - 1));

		if (x < 0 || x >= window.getWidth() ||
				y < 0 || y >= window.getHeight())
		{
			return false;
		}
		return true;
	}

	public boolean initialize(String s)
	{
		String[] parts  = s.split(",");
		if (parts.length < 3) {
			return false;
		}

		// Double.parseDouble and Integer.parseInt throw a
		// NumberFormatException if the String passed as input does
		// not contain a double.  This try-catch block catches if the
		// input string is improperly formatted and simply returns
		// "false"
		
		try {
			int red = Integer.parseInt(parts[0].trim());
			int green = Integer.parseInt(parts[1].trim());
			int blue = Integer.parseInt(parts[2].trim());
			color = new Color(red, green, blue);
		}
		catch (NumberFormatException nfe) {
			System.err.println("ERROR initializing WalkerPoint object from " +
					"string:  " + nfe.getMessage());
			return false;
		}
		return true;
	}



	public void moveToDiaognal(JPanel window, double pct)
	{
		if (pct < 0 || pct > 1.0) 
		{
			System.out.println("ERROR! pct is out of range: " + pct);
			System.exit(0);
		}
		move((int)(window.getWidth() * pct),
				(int)(window.getHeight() * pct));
	}

	public void moveToCenter(JPanel window)
	{
		moveToDiaognal(window, .5);
	}

	/**************************************************************************
	 * return the distance between the center of this point and the point
	 * {@code (ptx, pty)}
	 * 
	 * @param ptx
	 *            the x coordinate of the other point
	 * @param pty
	 *            the y coordinate of the other point
	 * 
	 * @return the distance between the center of this point and the point
	 *         {@code (ptx, pty)}
	 *************************************************************************/
	public double distanceTo(int ptx, int pty)
	{
		return Math.sqrt((ptx - x) * (ptx - x) + (pty - y) * (pty - y));
	}

	/************************************************************************
	 * Draw this point as a square instead of a circle.
	 * 
	 * @param g the {@code Graphics} object used to draw the point.
	 *************************************************************************/
	public void drawSquare(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x - (pointSize / 2), y - (pointSize / 2), pointSize,
				pointSize);
	}

}