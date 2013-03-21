import javax.swing.JFrame;
import javax.swing.JPanel;


//This class contains only one static method: makeFrameWithOnePanel.
//Use this method to create a JFrame object that draws itself using
//only one JPanel.

//In Java, JFrame is the class that knows how to draw and maintain
//windows.  The JFrame class is responsible for maintaining all of
//the "standard" window features: menus, title bar, resizing, etc.
//However, it does not know how to draw the content unique to each
//window (i.e., your document in Word, web page in IE, etc.).
//Instead, there are many different classes that draw different types
//of content: Dialog boxes, scroll bars, etc.  The simplest of these
//is JPanel.  JPanel is simply a canvas on which you can paint.

//The purpose of the makeFrameWithOnePanel is to create a JFrame
//object, then set it up with a single JPanel object to control its
//content.

class FrameWithOnePanel
{

	public static JFrame makeFrameWithOnePanel(JPanel panel, int frame_width, int frame_height)
	{
		// Create a JFrame object --- the object that creates a window
		// on the screen.
		JFrame frame = new JFrame(panel.toString());

		// Set the initial size of the window 
		frame.setSize(frame_width,frame_height);

		// This tells the Java program to quit when the window is closed.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Tell the window that the JPanel p is responsible for
		// drawing on it.
		frame.setContentPane(panel);

		// Tell the window to make itself visible.
		frame.setVisible(true);
		return frame;
	}
	
	public static JFrame makeFrameWithOnePanel(JPanel panel) 
	{
		return makeFrameWithOnePanel(panel, 500, 600);
	}
	
	
}