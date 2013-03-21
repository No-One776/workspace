package gvprojects.superttt.model;

import gvprojects.sttt.model.CellState;
import gvprojects.sttt.model.GameStatus;
import gvprojects.sttt.model.ISuperTicTacToeModel;

/**
 * 
 * The model for the SuperTicTacToe Game to run off of. It will play a game of
 * STTT and will detect wrap around win in both columns and rows. It throws
 * exceptions when needed.
 * 
 * @author Justin Rohr
 * @version 2/13/13 Winter 2013
 */

public class Model implements ISuperTicTacToeModel {
	CellState[][] board;
	GameStatus state;
	private static int playerturn;
	private static int win_length;

	/**
	 * Constructor that takes the row, column, and win size parameters for the
	 * game and checks their validity. Then it creates a game given those
	 * parameters
	 * 
	 * @param r
	 *            the number of rows on the board
	 * @param c
	 *            the number of columns on the board
	 * @param win_length
	 *            the win length for the game
	 */
	public Model(int r, int c, int win_length) {
		// Validates the columns and rows
		checkRowsandCols(r, c);

		// Creates the new board and resets it all to empty
		board = new CellState[r][c];
		reset();

		// Mak private JButton[][] button;es sure the win length is not less
		// than zero or greater than the
		// board
		if (win_length > r || win_length > c || win_length < 1)
			throw new IllegalArgumentException(
					"Win length can't be longer than the board or zero!");

		this.win_length = win_length;
		playerturn = 0;
	}

	/**
	 * Method that undoes the specified cell to empty and returns the playerturn
	 * to the previous player
	 * 
	 * @param r
	 *            the row for the cell to be undone
	 * @param c
	 *            the column for the cell to be undone
	 */
	public void undoCell(int r, int c) {
		// Validates the columns and rows
		checkRowsandCols(r, c);
		// Sets the spot to empty
		board[r][c] = CellState.EMPTY;
		// Minuses from the playerturn to keep it on the right player
		playerturn--;
	}

	/**
	 * Method for checking the cellstatus of the given spot on the game board
	 * 
	 * @param r
	 *            the row to check
	 * @param c
	 *            the column to check
	 * @return returns the CellState of the given board
	 */
	public CellState cellStatus(int r, int c) {
		// Validates the columns and rows
		checkRowsandCols(r, c);
		return board[r][c];
	}

	/**
	 * Method to return the length of the columns in this game board
	 * 
	 * @return the length of the columns
	 */
	public int numColumns() {
		return board[0].length;
	}

	/**
	 * Method to return the length of the rows in this game board
	 * 
	 * @return the length of the rows
	 */
	public int numRows() {
		return board.length;
	}

	/**
	 * Resets the entire board to CellState.EMPTY
	 */
	public void reset() {
		// Runs through the board and sets it empty
		for (int r = 0; r < board.length; r++)
			for (int j = 0; j < board[r].length; j++)
				board[r][j] = CellState.EMPTY;
		playerturn = 0;
		state = GameStatus.IN_PROGRESS;
	}

	/**
	 * Validates the Rows and Columns and throws a RunTimeError if the Cell is
	 * already filled. Otherwise, it checks the playerturn and fills the Cell
	 * with the players CellState and adds to the playerturn
	 */
	public void select(int r, int c) {
		// Validates the rows and columns
		checkRowsandCols(r, c);

		// Throws an exception if the spot is filled
		if ((board[r][c] != CellState.EMPTY) == true)
			throw new theRohrException("That Spot is filled!");

		// Sets the board state and the row and column to the player
		if (playerturn % 2 == 0)
			board[r][c] = CellState.X;
		else
			board[r][c] = CellState.O;

		playerturn++;
	}

	private void checkRowsandCols(int r, int c) {
		if (r < 0 || r > 50 || c < 0 || c > 50)
			throw new IllegalArgumentException("Invalid Input");
	}

	/**
	 * Method that checks the Game Status of the game by checking to see if
	 * anyone has won, tied, or if the game is still in progress
	 * 
	 * @return the status of the game
	 */
	public GameStatus status() {
		return checkForWin(board, win_length);
	}

	/**
	 * Checks for win on the game board, first checks to see if the board is
	 * null. Then checks to see who won by calling a private static helper
	 * method that returns the status of the current game board
	 * 
	 * @param game_board
	 *            the game board to be passed and checked
	 * @param win_length
	 *            the length of the win to be checked
	 * @return the current status of the game
	 */
	public static GameStatus checkForWin(CellState[][] game_board,
			int win_length) {
		// Checks the board to see if its null
		int Null = 0;
		for (int r = 0; r < game_board.length; r++)
			for (int c = 0; c < game_board[r].length; c++) {
				if (game_board[r][c] != CellState.EMPTY)
					Null++;
			}
		if (Null == 0)
			return null;

		// Checks to see who won by using a private helper method
		return otherCheckForWin(game_board, win_length);

	}

	// Private Helper method that returns the status of the game board
	private static GameStatus otherCheckForWin(CellState[][] board, int i) {

		/*
		 * Checks the columns for wrap around win by making a temp array of
		 * Cellstate that fills the temp array with 2 of the column
		 */
		CellState temp[];
		for (int row = 0; row < board.length; row++) {
			temp = new CellState[board[0].length * 2];
			int b = 0;
			for (int a = 0; a < temp.length; a++) {
				temp[a] = board[row][b];
				if (b == board[0].length - 1)
					b = -1;
				b++;
			}

			// Checks the temp array for the win length 'i' in a column
			// (includes
			// wrap-around)
			int check = 0, checkO = 0;
			for (int a = 0; a < temp.length; a++) {
				if (temp[a] == CellState.X) {
					check++;
					checkO = 0;
				} else if (temp[a] == CellState.O) {
					checkO++;
					check = 0;
				} else {
					check = 0;
					checkO = 0;
				}
				if (check == i)
					return GameStatus.X_WON;
				else if (checkO == i)
					return GameStatus.O_WON;
			}
		}

		/*
		 * Checks the rows for wrap around win by making a temp array of
		 * Cellstate that fills temp with two of the row
		 */
		for (int c = 0; c < board[0].length; c++) {
			temp = new CellState[board[0].length * 2];
			int b = 0;
			for (int a = 0; a < temp.length; a++) {
				temp[a] = board[b][c];
				if (b == board.length - 1)
					b = -1;
				b++;
			}

			// Checks the temp array for the win length 'i' in a row (includes
			// wrap-around)
			int check = 0, checkO = 0;
			for (int a = 0; a < temp.length; a++) {
				if (temp[a] == CellState.X) {
					check++;
					checkO = 0;
				} else if (temp[a] == CellState.O) {
					checkO++;
					check = 0;
				} else {
					check = 0;
					checkO = 0;
				}
				if (check == i)
					return GameStatus.X_WON;
				else if (checkO == i)
					return GameStatus.O_WON;
			}
		}

		// Checks to see if the game is still in progress and returns if so
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board[r].length; c++)
				if (board[r][c].equals(CellState.EMPTY))
					return GameStatus.IN_PROGRESS;

		// If none of the above are true, it returns the tie game
		return GameStatus.CATS;
	}
}
