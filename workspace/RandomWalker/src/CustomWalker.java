import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * @author everett schoenborn
 */
public class CustomWalker extends WalkerPoint {

	public static final int UPDATE_PAUSE = 10;
	public int maxDistance;
	
	int FriendWalker_x;
	int FriendWalker_y;
	
	static JFrame window;
	static JPanel wp;
	
	public CustomWalker(int x, int y, Color c, int maxDistance){
		super(x,y,c);
		this.maxDistance = maxDistance;
	}
	
	public CustomWalker(Color c){
		super(c);
		maxDistance = 10;
	}
	
	public CustomWalker(){
		super();
		maxDistance = 10;
	}
	
	public void setFriendLocation(int x, int y){
		
		FriendWalker_x = x;
		FriendWalker_y = y;
		
	}
	
	public void walk(){
		
		if(Math.abs(this.x - FriendWalker_x) > maxDistance){
			if(this.x > FriendWalker_x)
				this.x--;
			else
				this.x++;
		}
		if(Math.abs(this.y - FriendWalker_y) > maxDistance){
			if(this.y > FriendWalker_y)
				this.y--;
			else
				this.y++;
		}
	}
	
	public static void run(JFrame window){
		// Thread.sleep throws and exception when the sleep is
				// interrupted.  When this happens, we want to exit the
				// program.  To do this, we put the entire while loop inside
				// the "try" block.
				try {
					while(true) {	
						window.repaint();
						Thread.sleep(UPDATE_PAUSE);
						//Thread.sleep(0);
					}
				} // end try 
				catch (InterruptedException e) {
					// The thread has had interrupt() called on it, so exit
					// gracefully
				} // end catch
			} // end run
	
	
	public static void main(String[] args){
		
		CustomWalker walk1 = new CustomWalker(10,10,Color.green,2);
		CustomWalker walk2 = new CustomWalker(11,11,Color.blue, 5);
		
		// Put the created points in an array
        CustomWalker[] twp =  {walk1, walk2};
	     // WalkerPoint[] twp =  {wp3};

		// Create the Panel in which the points will wander
		WalkerPanel wp = new WalkerPanel(twp);
		
		walk1.setFriendLocation(walk2.x, walk2.y);
		walk2.setFriendLocation(walk1.x, walk2.y);
		JFrame window = FrameWithOnePanel.makeFrameWithOnePanel(wp, 200, 200);
		window.setTitle("Walker Point");
        window.setLocation(100,100);
		
        walk1.move(20, 20);
        walk2.move(50,50);
		
		window.setLocation(100,100);
		
		window.setVisible(true);
		run(window);
		
	}
	
	
}
