import java.awt.*; // for Point
import javax.swing.*; // for JFrame

/**
 * This is a subclass of WalkerPoint that "wraps around" the screen.
 * @author Zachary Kurmas
 *
 */
@SuppressWarnings("serial")
class WalkerPointMod extends WalkerPoint
{

	public WalkerPointMod(int pX, int pY, Color pColor)
	{
		super(pX,pY, pColor);
	}

	public WalkerPointMod(Color pColor)
	{
		super(pColor);
	}

	public WalkerPointMod()
	{
		super();
	}

    public void smile() {
        return;
    }

    @Override
	public boolean walk(JPanel window)
	{
		boolean on_screen = super.walk(window);
		
		if (!on_screen)	{
			if (x >= window.getWidth()) {
				x %= window.getWidth();
			}
			if (x < 0) 	{
				x += window.getWidth();
			}
			if (y >= window.getHeight()) {
				y %= window.getHeight();
			}
			if ( y < 0) {
				y += window.getHeight();
			}
		} // end if
		return true;
	} // end walk
}