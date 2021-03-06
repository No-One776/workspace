package gvprojects.chess.view;

import gvprojects.chess.model.Model;
import gvprojects.chess.model.Player;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * View that prints and displays all the information including the board and
 * pieces along with who's player turn it is. It also gets and takes the player
 * input for moving bases.
 * 
 * @author Justin Rohr
 * @version March 16, 2013
 */
public class View {

	Scanner reader = new Scanner(System.in);
	PrintStream out = new PrintStream(System.out);
	private final int bWidth = 41;
	private Model model;

	/**
	 * Constructor that takes a model to access the engines general information
	 * for updating the views board
	 * 
	 * @param e
	 *            the engine for the view to use
	 */
	public View(Model e) {
		model = e;
	}

	/**
	 * Prints the given string message to the players
	 * 
	 * @param str
	 *            the message string to be printed out
	 */
	public void print(String str) {
		out.println("\n" + str);
	}

	/**
	 * Prints the nicely formated board with row and column numbering and the
	 * player pieces at positions
	 */
	public void printBoard() {
		// Print the top Column numbers
		out.println(line(8));
		// Prints the whole board
		for (int x = 0; x < model.numRows(); x++) {
			out.println("  " + printStars(bWidth));
			out.print((char) ('A' + x) + " ");
			for (int y = 0; y < model.numColumns(); y++)
				out.print(boardRow(x, y));
			out.println("|");
		}
		out.println("  " + printStars(bWidth));
	}

	// Recursive helper method to print the line numbers
	private String line(int l) {
		return l == 0 ? "     " : line(l - 1) + l + "    ";
	}

	// Recursive method to print the number in stars
	private String printStars(int w) {
		return w == 1 ? "*" : "*" + printStars(w - 1);
	}

	// Helper method that prints the board rows of pieces
	private String boardRow(int row, int col) {
		String str = "| ";
		String name = " ";
		Player p = null;
		// Try catch block to find pieces or null (none occupied) board spots
		try {
			name = model.pieceAt(row, col).name();
			p = model.pieceAt(row, col).player();
		} catch (NullPointerException e) {
			name = " ";
		}
		// Concatenates to the string whether its a white or black piece
		if (p == Player.WHITE)
			str += "W" + pName(name);
		else if (p == Player.BLACK)
			str += "B" + pName(name);
		else
			str += "   ";

		return str;
	}

	// Helper method to give the string name for a piece
	private String pName(String name) {
		String str = "";
		if (name == "Rook")
			str += "R ";
		else if (name == "Bishop")
			str += "B ";
		else if (name == "King")
			str += "K ";
		else if (name == "Knight")
			str += "Kn";
		else if (name == "Pawn")
			str += "P ";
		else if (name == "Queen")
			str += "Q ";
		return str;
	}

	/**
	 * Method for the presenter to get a move from the player to move a Chess
	 * Piece
	 * 
	 * @return the string value of the move from the player
	 */
	public String getMove() {
		out.println(model.currentPlayer() + ", Please enter a Move: ");
		String a = reader.next();
		return a;
	}

}
