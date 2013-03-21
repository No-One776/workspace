import javax.swing.*;
import java.awt.*;

//When told to paint itself, this class simply draws each of the
//points in the array points, then updates the location of each
//point.
@SuppressWarnings("serial")
class WalkerPanel extends JPanel
{

	protected WalkerPoint[] points;

	WalkerPanel(WalkerPoint[] pPoints)
	{
		super();
		points = pPoints;
	}

    @Override
	public void paintComponent(Graphics g) {

		for (WalkerPoint p : points) {
			p.draw(g);
			p.walk(this);
		}
	}

} // end WalkerPanel
