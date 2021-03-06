/**
 * Presenter that has the main method to start the game and control the view and the engine to complete a full game of chess until complete
 */
package gvprojects.chess.presenter;

import gvprojects.chess.model.Model;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import gvprojects.chess.view.View;

/**
 * @author Justin Rohr
 * @version March 16, 2013
 */
public class Presenter {

	private Model engine;
	private View view;
	private int checkCount = 0;

	/**
	 * Constructor that creates the game engine and the view.Then it starts the
	 * game loop
	 * 
	 * @param e
	 *            the game engine to use
	 * @param v
	 *            the view to use
	 */
	public Presenter(Model e, View v) {
		engine = e;
		view = v;
		game();
	}

	// Method that loops through the game until complete
	private void game() {
		boolean move;
		Move m = new Move(0, 0, 0, 0);

		// Prints a message of the game and how to move
		view.print("Welcome to the Game of Chess! \nTo move enter in spots like A1:A2.");

		// Do while loop to run until the game finishes
		do {
			view.printBoard();
			// Do while loop to run until the player gives a correct move.
			do {
				// Gets the String to move from the user
				String str = view.getMove();
				// Statements to validate and convert the move
				if (str.length() == 5) {
					move = true;
					if (validateMove(str.charAt(0) - 65) == true)
						m.fromRow = (str.charAt(0) - 65);
					else
						move = false;
					if (validateMove(str.charAt(1) - 49) == true)
						m.fromColumn = str.charAt(1) - 49;
					else
						move = false;
					if (validateMove(str.charAt(3) - 65) == true)
						m.toRow = str.charAt(3) - 65;
					else
						move = false;
					if (validateMove(str.charAt(4) - 49) == true)
						m.toColumn = str.charAt(4) - 49;
					else
						move = false;
					// Sets the current player for checking inCheck
					Player p = engine.currentPlayer();
					// Moves
					if (move != false)
						move(m);
					// Checks to see if player went into check, if so moves the
					// player back and tells the player he cannot move and end
					// in check
					if (engine.inCheck(p)) {
						view.print("You are in check & cannot end a move in check "
								+ p);
						engine.undoLastMove(m, p);
						checkCount++;
						engine.setCheckCount(checkCount);
					} else
						checkCount = 0;
				} else {
					view.print("Invalid Move. Please try again");
					move = false;
				}
				// Checks to see if the player is in check
				boolean check = engine.inCheck(engine.currentPlayer());
				if (check) {
					view.print("You are in check " + engine.currentPlayer());
				}

			} while (move == false);
		} while (engine.isComplete() == false);

	}

	// Validates that a move is on the board from the string to int conversion
	private boolean validateMove(int i) {
		return i < 8 && i > -1;
	}

	/**
	 * Completes a players move by validating and moving the piece. If there is
	 * an error or the piece can't move it prints the problem to the player
	 * 
	 * @param m
	 *            the move to attempt to be completed
	 */
	public void move(Move m) {
		try {
			if (engine.isValidMove(m))
				engine.move(m);
			else
				view.print("Not a valid position to move to!");
		} catch (IllegalArgumentException e) {
			view.print("Invalid Move.Please try again.");
		}
	}

	/**
	 * Main method to create run a chess game
	 * 
	 * @param args
	 *            user specifications for the game --not used
	 */
	public static void main(String[] args) {
		Model e = new Model();
		View v = new View(e);

		new Presenter(e, v);
	}

}
