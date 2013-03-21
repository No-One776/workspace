import javax.swing.*;

public class WalkerAppWithLoader
{
	public static final int UPDATE_PAUSE = 10;

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
			}
		} // end try 
		catch (InterruptedException e) {
			// The thread has had interrupt() called on it, so exit
			// gracefully
		} // end catch
	} // end run


	public static void main(String[] args)
	{
		int width = 200;
		int height = 200;
		if (args.length < 1) {
			System.err.println("Usage:  WakerAppWithLoader fileName");
			return;
		}

		if (args.length >= 3) {
			width = Integer.parseInt(args[1]);
			height = Integer.parseInt(args[2]);
		}


		WalkerPoint[] twp = WalkerPointLoader.loadWalkerPointsFromFile(args[0]);
		if (twp == null) {return;}
		WalkerPanel wp = new WalkerPanel(twp);
		// Create the window (JFrame) that holds the Panel
		JFrame f = FrameWithOnePanel.makeFrameWithOnePanel(wp, width, height);

		int center_x = wp.getWidth() /2;
		int center_y = wp.getHeight() / 2;
		int radius = wp.getHeight() / 4;

		for (int x = 0; x < twp.length; x++) {
			double angle = (x*2*Math.PI) / twp.length;
			int this_x = (int)(radius*Math.sin(angle) + center_x);
			int this_y = (int)(radius*Math.cos(angle) + center_y);
			twp[x].move(this_x, this_y);
		}


		// Call run, which repeatedly repaints the panel.
		run(f);
	} // end main

} // end SimpleWalkerApp
