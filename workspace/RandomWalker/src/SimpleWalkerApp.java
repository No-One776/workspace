import javax.swing.*;
import java.awt.*;


public class SimpleWalkerApp
{
	public static final int UPDATE_PAUSE = 10;

	JFrame frame;

	// This method just repeatedly repaints the WalkerPanel, pausing
	// for a few milliseconds between each repaint.
	public static void run(JFrame w) {

		// Thread.sleep throws and exception when the sleep is
		// interrupted.  When this happens, we want to exit the
		// program.  To do this, we put the entire while loop inside
		// the "try" block.
		try {
			while(true) {	
				w.repaint();
				Thread.sleep(UPDATE_PAUSE);
				//Thread.sleep(0);
			}
		} // end try 
		catch (InterruptedException e) {
			// The thread has had interrupt() called on it, so exit
			// gracefully
		} // end catch
	} // end run


	public static void main(String[] args)
	{

		// Create several walker points
		WalkerPoint wp1 = new WalkerPoint(100, 10, Color.black);

        WalkerPoint wp2 =  new OurWalkerPointMod(10, 100, Color.blue);
        WalkerPoint wp3 = new WalkerMod(50, 50, Color.green);

		// Put the created points in an array
        WalkerPoint[] twp =  {wp1, wp2, wp3};
	     // WalkerPoint[] twp =  {wp3};

		// Create the Panel in which the points will wander
		WalkerPanel wp = new WalkerPanel(twp);

		// Create the window (JFrame) that holds the Panel
		JFrame f = FrameWithOnePanel.makeFrameWithOnePanel(wp, 500, 500);
		f.setTitle("Walker Point");
        f.setLocation(100,100);

		// Set the WalkerPoints to different places in the panel.
		//wp1.move(20, 20);
        //wp2.move(50,50);

		// Call run, which repeatedly repaints the panel.
		run(f);
	} // end main

} // end SimpleWalkerApp
