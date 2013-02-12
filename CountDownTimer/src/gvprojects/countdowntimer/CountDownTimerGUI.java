package gvprojects.countdowntimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a Graphical front end for the Count Down Timer
 * 
 * @author Justin rohr
 * @version 1/23/13
 */

public class CountDownTimerGUI extends JPanel {

	// Instance Variables
	private static final long serialVersionUID = 1L;
	static JPanel button;
	private JLabel label;
	private JButton start, stop, reset;
	private JFrame frame;
	private Timer timer;
	private JMenuItem new_Timer, quit;
	private ButtonListener b;
	CountDownTimer time;
	private ActionListener t;

	/**
	 * Initializes and sets the components of the GUI
	 */
	public CountDownTimerGUI() {
		// Instantiates variables
		time = new CountDownTimer();
		label = new JLabel("Timer: " + time);

		// Creates the button panel and sets preferences
		button = new JPanel();
		button.setPreferredSize(new Dimension(250, 100));
		button.setBackground(Color.black);
		button.setBorder(BorderFactory.createMatteBorder(5, 2, 2, 2,
				Color.magenta));

		// Creates the frame and adds to it
		frame = new JFrame("Timer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(button);
		frame.setTitle("Justin's Timer");
		frame.setVisible(true);
		frame.pack();

		// Initializes the listeners
		t = new Listener();
		b = new ButtonListener();

		// Initialize the buttons
		start = new JButton("Start");
		stop = new JButton("Stop");
		reset = new JButton("Reset");

		// Set button Colors
		start.setBackground(Color.green);
		stop.setBackground(Color.red);
		reset.setBackground(Color.cyan);
		label.setBackground(Color.black);
		label.setForeground(Color.red);

		// Add a listeners
		timer = new Timer(1000, t);
		start.addActionListener(b);
		stop.addActionListener(b);
		reset.addActionListener(b);

		// Add the Buttons to the Panel
		button.add(start);
		button.add(stop);
		button.add(reset);
		button.add(label);

		// Sets up the menus on the frame
		setupMenus(frame);
	}

	/**
	 * Implements the timer actions by decreasing the time and updating the
	 * display
	 */
	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (time.totalSeconds() > 0)
				time.dec();
			else
				timer.stop();
			time.toString();
			label.setText("Timer: " + time);
		}

	}

	/**
	 * Sets up the file menu with a new timer and quit
	 * 
	 * @param f
	 *            the frame to be used
	 */
	public void setupMenus(JFrame f) {
		// Create the menu
		JMenuBar menu = new JMenuBar();
		f.setJMenuBar(menu);

		// Create and add the file menu
		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);

		// Create menu items
		new_Timer = new JMenuItem("New Timer");
		new_Timer.addActionListener(b);
		quit = new JMenuItem("Quit");
		quit.addActionListener(b);

		// Add menu items to the menu
		fileMenu.add(new_Timer);
		fileMenu.add(quit);
	}

	/**
	 * Implements the button actions via the GUI listener to re-act to the GUI
	 * buttons being pushed
	 * 
	 * @author rohrj
	 * 
	 */
	public class ButtonListener implements ActionListener {

		/**
		 * Responds the the user's input
		 */
		public void actionPerformed(ActionEvent e) {
			JComponent b = (JComponent) e.getSource();
			String input = null;

			// -------------------------Sets button
			// actions----------------------
			if (b == quit)
				System.exit(1);

			if (b == new_Timer) {
				input = JOptionPane
						.showInputDialog("Please enter a String time for the timer(i.e. 0:0):");
				time = new CountDownTimer(input);
				label.setText("Timer: " + time);
			}

			if (b == start) {
				if (time.totalSeconds() > 0) {
					timer.start();
					start.setEnabled(false);
					stop.setEnabled(true);
				}
			}

			if (b == stop) {
				timer.stop();
				start.setEnabled(true);
				stop.setEnabled(false);
			}

			if (b == reset) {
				time = new CountDownTimer();
				label.setText("Timer: " + time);
			}
		}
	}

	/**
	 * The main method that creates the GUI
	 * 
	 * @param args
	 *            array of a string for different arguments
	 */
	public static void main(String[] args) {
		new CountDownTimerGUI();
	}
}
