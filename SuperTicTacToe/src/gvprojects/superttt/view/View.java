package gvprojects.superttt.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View {
	private JFrame frame;
	private JMenuBar menu;
	private JMenuItem quit, reset;
	private JMenu fileMenu;
	private JPanel board, functions, main;
	private JButton quitb, resetb, undo;
	private JButton[][] buttons;
	private int size;
	private Icon oIcon, xIcon, empty;

	public View(int s) {
		size = s;

		// Loads Icons to use
		oIcon = loadIcon("o.png");
		xIcon = loadIcon("x.jpg");
		empty = loadIcon("empty.jpg");

		// Game Buttons
		buttons = new JButton[size][size];

		// JButtons
		undo = new JButton("Undo");
		quitb = new JButton("Quit");
		resetb = new JButton("Reset");

		// Functions Panel
		functions = new JPanel();
		functions.setLayout(new FlowLayout());
		functions.add(resetb);
		functions.add(undo);
		functions.add(quitb);

		// Board Panel
		board = new JPanel();
		board.setLayout(new GridLayout(size, size));

		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++) {
				buttons[r][c] = new JButton(empty);
				board.add(buttons[r][c], r, c);
			}
		board.revalidate();
		frame = new JFrame();
		main = new JPanel();
		main.setLayout(new BorderLayout());

		main.add(functions, BorderLayout.CENTER);
		main.add(board, BorderLayout.PAGE_START);
		frame = new JFrame("Super Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(main);
		setUpMenus();
		frame.setTitle("Justin's Super Tic Tac Toe");
		frame.setVisible(true);
		frame.pack();

		quitb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void resetBoard() {
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++) {
				buttons[r][c].setIcon(empty);
				buttons[r][c].setEnabled(true);
			}
		undo.setEnabled(true);
	}

	private static ImageIcon loadIcon(String name) {
		java.net.URL imgURL = View.class.getResource(name);
		if (imgURL == null) {
			throw new RuntimeException("Icon resource not found.");
		}
		return new ImageIcon(imgURL);
	}

	private void setUpMenus() {
		// Create menu items
		reset = new JMenuItem("Reset");
		quit = new JMenuItem("End Game");

		// Create the menu
		menu = new JMenuBar();

		// Create and add the file menu
		fileMenu = new JMenu("File");
		menu.add(fileMenu);

		// Add menu items to the menu
		fileMenu.add(reset);
		fileMenu.add(quit);

		frame.setJMenuBar(menu);
	}

	public void addResetButtonListener(ActionListener resetButtonListener) {
		resetb.addActionListener(resetButtonListener);
		reset.addActionListener(resetButtonListener);
	}

	public void addButtonsListeners(int r, int c, ActionListener ButtonListener) {
		buttons[r][c].addActionListener(ButtonListener);
	}

	public void displayMessage(String string) {
		JOptionPane.showMessageDialog(null, string);
	}

	public void endGame() {
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++) {
				buttons[r][c].setEnabled(false);
			}

		undo.setEnabled(false);
	}

	public void addUndoButtonListener(ActionListener undoButtonListener) {
		undo.addActionListener(undoButtonListener);
	}

	public void setXIcon(int r, int c) {
		buttons[r][c].setIcon(xIcon);
	}

	public void setOIcon(int r, int c) {
		buttons[r][c].setIcon(oIcon);
	}

	public void setEmpty(int temprow, int tempcol) {
		buttons[temprow][tempcol].setIcon(empty);
	}

	public void disableUndo() {
		undo.setEnabled(false);
	}

	public void enableUndo() {
		undo.setEnabled(true);
	}
}