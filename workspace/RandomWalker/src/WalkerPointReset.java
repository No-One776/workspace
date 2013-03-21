import java.awt.*; // for Point
import javax.swing.*; // for JFrame

/**
 * This is a subclass of WalkerPoint that moves back to its original
* starting point whenever it hits the edge.  When it resets it
* chooses a new color.
*  
 * @author Zachary Kurmas
 *
 */

@SuppressWarnings("serial")
class WalkerPointReset extends WalkerPoint
{

	// This is the array of colors through which this point
	// cycles. (It chooses a new color every time it hits the edge of
	// the window.)
	public static Color[] color_array = 
	{ Color.black, Color.blue, Color.green, Color.orange, Color.cyan, 
		Color.magenta, Color.red, Color.yellow, Color.darkGray, Color.pink};


	// Keep track of the initial starting point
	protected int original_x, original_y;

	// Keep track of the index in color_array containing the color to
	// use next.
	protected int color_number;

	public WalkerPointReset(int pX, int pY, Color pColor)
	{
		super(pX,pY, pColor);
		initialize();
	}

	public WalkerPointReset(Color pColor)
	{
		super(pColor);
		initialize();
	}

	public void initialize()
	{
		// Set the original x and y
		original_x = x;
		original_y = y;

		// Search through the color array for the number of the color
		// chosen as a starting point.
		color_number = 0;
		for (int loop = 0; loop < color_array.length; loop++)
		{
			//System.out.println(color + " --- " + color_array[loop]);
			if (color.equals(color_array[loop]))
				color_number = loop;
		}
		//System.out.println("Color number:  " + color_number);
	}

	public boolean walk(JPanel window)
	{
		boolean on_screen = super.walk(window);

		if (!on_screen)
		{
			x = original_x;
			y = original_y;
			color_number = ++color_number % color_array.length;
			color = color_array[color_number];
		} // end if
		return true;
	} // end walk

    public void goToSleep() {
        return;
    }


}