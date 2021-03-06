package gvprojects.chess.model;

/**
 * Parent Chess Piece class that implements IChessPiece Interface that contains
 * general methods and helpers for checking isValidMove with rows, columns, and
 * diagonal.
 * 
 * @author rohrj
 * @version March 10, 2013
 */
public class ChessPiece implements IChessPiece {
	private Player player;
	private String name;
	private Move move;
	private IChessPiece[][] board;

	/**
	 * Constructor that takes player and a string as the name for the piece.
	 * 
	 * @param p
	 *            the player black or white
	 * @param str
	 *            the piece name
	 */
	public ChessPiece(Player p, String str) {
		player = p;
		name = str;
	}

	/**
	 * Method that checks the move for correct values that are on the board and
	 * a valid piece on the given board. Throws an exception if the move is off
	 * the board or the piece is not yours.
	 * 
	 * @param m
	 *            the move to be checked for validity
	 * @param p
	 *            the IChessPiece board to be checked
	 * @return true or false if it is a valid move
	 */
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		// Checks that the move is on the board
		if (m.fromRow < 0 || m.fromRow > 7 || m.fromColumn > 7
				|| m.fromColumn < 0)
			throw new IllegalArgumentException("Invalid Move Spots");
		// Checks that you are not landing on your player and if the piece is
		// you are moving is yours
		else if (p[m.fromRow][m.fromColumn] != this
				|| p[m.fromRow][m.fromColumn].player() != this.player)
			throw new IllegalArgumentException(
					"Piece is not there or is not yours");
		// Checks if the move is going to the same spot
		else if (m.fromRow == m.toRow && m.toColumn == m.fromColumn)
			return false;
		// Checks if the final spot is empty, if not, it checks to make sure it
		// is not your own piece you are taking
		else if (p[m.toRow][m.toColumn] != null
				&& p[m.fromRow][m.fromColumn] != null
				&& p[m.toRow][m.toColumn].player() == p[m.fromRow][m.fromColumn]
						.player())
			return false;
		else
			return true;
	}

	/**
	 * Returns the name of the chess piece
	 * 
	 * @return the pieces name
	 */
	public String name() {
		return name;
	}

	/**
	 * Returns the player owner of the chess piece
	 * 
	 * @return the chess piece's player
	 */
	public Player player() {
		return player;
	}

	/**
	 * Helper method that checks rows or columns are clear; depending on where
	 * the piece is supposed to me moving
	 * 
	 * @param m
	 *            the move to check
	 * @param p
	 *            the board to check on
	 * @return true if the move is valid, false otherwise
	 */
	protected boolean checkRandC(Move m, IChessPiece[][] p) {
		return ((m.fromRow != m.toRow && checkRow(m, p)) || (m.toColumn != m.fromColumn && checkCol(
				m, p)));
	}

	// Helper method that checks if the row is clear
	private boolean checkRow(Move m, IChessPiece[][] p) {
		// Checks from your position to the spot before your move to be empty
		if (m.fromRow < m.toRow) {
			for (int x = m.fromRow + 1; x < m.toRow; x++)
				if (p[x][m.fromColumn] != null)
					return false;
		} else
			for (int x = m.fromRow - 1; x > m.toRow; x--)
				if (p[x][m.fromColumn] != null)
					return false;

		return true;
	}

	// Private helper method to check that the columns are clear
	private boolean checkCol(Move m, IChessPiece[][] p) {
		// Checks from your position to the spot before your final spot to be
		// empty of pieces
		if (m.fromColumn < m.toColumn) {
			for (int y = m.fromColumn + 1; y < m.toColumn; y++)
				if (p[m.fromRow][y] != null)
					return false;
		} else
			for (int y = m.fromColumn - 1; y > m.toColumn; y--)
				if (p[m.fromRow][y] != null)
					return false;

		return true;
	}

	/**
	 * Checks the distance of the move against the specified length and returns
	 * true if it is within bounds otherwise returns false.
	 * 
	 * @param m
	 *            the move to check
	 * @param i
	 *            the distance to check
	 * @return true or false if the move is within the distance
	 */
	protected boolean checkDistance(Move m, int i) {
		if (m.fromColumn != m.toColumn
				&& (m.toColumn - m.fromColumn > i || m.fromColumn - m.toColumn > i)
				|| m.fromRow != m.toRow
				&& (m.toRow - m.fromRow > i || m.fromRow - m.toRow > i))
			return false;
		else
			return true;
	}

	/**
	 * Checks the diagonal move on the chess piece to see if there is a piece in
	 * the way where it is going and if the ending spot is in diagonal to the
	 * start.
	 * 
	 * @param m
	 *            the move to check
	 * @param p
	 *            the IChessPiece board to check on
	 * @return true if the move is valid, false otherwise.
	 */
	protected boolean checkDiag(Move m, IChessPiece[][] p) {
		// Sets some variables for the recursive method to access
		move = m;
		board = p;
		int a = 0, b = 0;

		// Hands the recursive method the directions to move for both row and
		// column depending on the move position
		if (m.fromRow - m.toRow > 0 && m.fromColumn - m.toColumn > 0) {
			a = -1;
			b = -1;
		} else if (m.fromRow - m.toRow > 0 && m.fromColumn - m.toColumn < 0) {
			a = -1;
			b = 1;
		} else if (m.fromRow - m.toRow < 0 && m.fromColumn - m.toColumn < 0) {
			a = 1;
			b = 1;
		} else if (m.fromRow - m.toRow < 0 && m.fromColumn - m.toColumn > 0) {
			a = 1;
			b = -1;
		}
		return checkDiagHelp(m.fromRow + a, m.fromColumn + b, a, b);
	}

	// Recursive helper method that takes the start positions + the direction
	// already and then checks if its on the board, at the end position, the
	// board is empty on its way, and otherwise calls itself
	private boolean checkDiagHelp(int x, int y, int a, int b) {
		if (x == move.toRow && y == move.toColumn)
			return true;
		else if (x < 0 || x > 7 || y < 0 || y > 7)
			return false;
		else if (board[x][y] != null)
			return false;
		else
			return checkDiagHelp(x + a, y + b, a, b);
	}
}
