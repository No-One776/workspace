package gvprojects.superttt.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SuperTicTacToeView {
	JFrame frame;
	JMenuBar menu;
	JMenuItem quit, reset;
	JMenu fileMenu;
	JPanel board1, board2;
//Buttons,layout managers, frames etc
	
	public SuperTicTacToeView(){
		frame = new JFrame("Super Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board1);
		frame.add(board2);
		frame.setTitle("Justin's Super Tic Tac Toe");
		frame.setVisible(true);
		frame.pack();
	
	
		// Create the menu
		menu = new JMenuBar();
		frame.setJMenuBar(menu);

		// Create and add the file menu
		fileMenu = new JMenu("File");
		menu.add(fileMenu);

		// Create menu items
		reset = new JMenuItem("");
		quit = new JMenuItem("Quit");
		
		// Add menu items to the menu
		fileMenu.add(reset);
		fileMenu.add(quit);
	}
}