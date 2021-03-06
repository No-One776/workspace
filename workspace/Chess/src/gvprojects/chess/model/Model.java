/**
 * Engine model that implements the ChessModel Interface and 
 * can be used by a MVP to run a complete chess game where two Players 
 * can compete against each other. Does not have an AI.
 */
package gvprojects.chess.model;

/**
 * @author Justin Rohr
 * @version March 14, 2013
 */
public class Model implements IChessModel {

	private IChessPiece[][] board;
	private final int rows = 8, cols = 8;
	private Player player;
	private IChessPiece takenPiece;
	private int checkCount = 0;;

	/**
	 * Constructor that creates a chess board, fills it with pieces, and sets
	 * the starting player.
	 */
	public Model() {
		board = new IChessPiece[8][8];
		reset();
		player = Player.WHITE;
	}

	// Reset method to clear the board and then places the pieces for a new game
	private void reset() {
		// Clears the board of all pieces
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < cols; y++)
				board[x][y] = null;

		// Makes the Rooks
		board[0][0] = new Rook(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[7][0] = new Rook(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);

		// Makes the Knights
		board[0][1] = new Knight(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[7][1] = new Knight(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);

		// Makes the bishops
		board[0][2] = new Bishop(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);

		// Makes the King and Queen
		board[0][4] = new King(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[7][3] = new King(Player.WHITE);
		board[7][4] = new Queen(Player.WHITE);

		// Makes the Pawns
		for (int y = 0; y < cols; y++) {
			board[1][y] = new Pawn(Player.BLACK);
			board[6][y] = new Pawn(Player.WHITE);
		}
	}

	/**
	 * Returns the current player who is moving
	 * 
	 * @return the current player
	 */
	public Player currentPlayer() {
		return player;
	}

	/**
	 * Checks to see it the specified player is in check by first finding the
	 * Players king and then passing that to a helper method to search
	 * 
	 * @return true or false whether the players king is in check
	 */
	public boolean inCheck(Player p) {
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < cols; y++)
				if (board[x][y] != null && board[x][y].player() == p
						&& board[x][y].name() == "King")
					return check(p, x, y);
		return false;
	}

	// Check (if empty spot) Bishop L, Diagonals(Bishop/Queen), straight
	// (Rook,Queen), Pawn Diagonal
	private boolean check(Player p, int r, int c) {
		// -------------First checks for knights---------------
		if (checkKnight(p, r, c))
			return true;
		// Checks that the king is not in check in any direction
		int a = 1;
		int b = 0;

		// Check down
		if (checkSide(p, r, c, a, b))
			return true;

		// Diagonal Checks Down and Right
		if (checkDiag(p, r, c, a, b + 1))
			return true;

		// -------------------------------------------------
		a = 0;
		b = 1;
		// Check right
		if (checkSide(p, r, c, a, b))
			return true;

		// Diagonal Check Back and Right
		if (checkDiag(p, r, c, a - 1, b))
			return true;

		// -------------------------------------------------
		a = -1;
		b = 0;
		// Check above
		if (checkSide(p, r, c, a, b))
			return true;

		// Diagonal Check Back and Up
		if (checkDiag(p, r, c, a, b - 1))
			return true;

		// -------------------------------------------------
		a = 0;
		b = -1;
		// Check left
		if (checkSide(p, r, c, a, b))
			return true;

		// Diagonal Check Down and Left
		if (checkDiag(p, r, c, a + 1, b))
			return true;

		// ----------------End of Horizontal/Vertical

		// // Check for the Pawn to take King
		// if (validateMove(r - 1, c - 1) && board[r - 1][c - 1] != null
		// && board[r - 1][c - 1].player() != p
		// && board[r - 1][c - 1].name() == "Pawn")
		// return true;

		return false;
	}

	// Helper method for seeing if king is in check horizontally or vertically
	private boolean checkDiag(Player p, int r, int c, int a, int b) {
		boolean taken = false;
		int first = 0;
		// While the move is valid and a spot between the king !not taken
		while (validateMove(r + a, c + b) && !taken) {
			// Check to see if the board is null
			if (board[r + a][c + b] != null) {
				if (board[r + a][c + b].player() != p
						&& (board[r + a][c + b].name() == "Queen"
								|| board[r + a][c + b].name() == "Bishop" || first == 0
								&& board[r + a][c + b].name() == "Pawn"))
					return true;
				taken = true;
			}
			first++;
			// Increments the moves
			if (a >= 1)
				a++;
			else if (a <= -1)
				a--;
			if (b >= 1)
				b++;
			else if (b <= -1)
				b--;
		}
		return false;
	}

	// Helper method for seeing if king is in check horizontally or vertically
	private boolean checkSide(Player p, int r, int c, int a, int b) {
		boolean taken = false;
		// While the move is valid and a spot between the king !not taken
		while (validateMove(r + a, c + b) && !taken) {
			// Check to see if the board is null
			if (board[r + a][c + b] != null) {
				if (board[r + a][c + b].player() != p
						&& (board[r + a][c + b].name() == "Queen" || board[r
								+ a][c + b].name() == "Rook"))
					return true;
				taken = true;
				System.out.println("Taken by: " + board[r + a][c + b].name());
			}
			// Increments the moves
			if (a >= 1)
				a++;
			else if (a <= -1)
				a--;
			if (b >= 1)
				b++;
			else if (b <= -1)
				b--;

		}
		return false;
	}

	// Checks the positions around a king where a knight is able to attack him
	private boolean checkKnight(Player p, int r, int c) {
		String k = "Knight";
		// Checks below to the left and right
		if (validateMove(r + 2, c + 1) && board[r + 2][c + 1] != null
				&& board[r + 2][c + 1].player() != p
				&& board[r + 2][c + 1].name() == k)
			return true;
		else if (validateMove(r + 2, c - 1) && board[r + 2][c - 1] != null
				&& board[r + 2][c - 1].player() != p
				&& board[r + 2][c - 1].name() == k)
			return true;
		// Checks above to the left and right
		else if (validateMove(r - 2, c + 1) && board[r - 2][c + 1] != null
				&& board[r - 2][c + 1].player() != p
				&& board[r - 2][c + 1].name() == k)
			return true;
		else if (validateMove(r - 2, c - 1) && board[r - 2][c - 1] != null
				&& board[r - 2][c - 1].player() != p
				&& board[r - 2][c - 1].name() == k)
			return true;
		// Check to the left
		else if (validateMove(r + 1, c - 2) && board[r + 1][c - 2] != null
				&& board[r + 1][c - 2].player() != p
				&& board[r + 1][c - 2].name() == k)
			return true;
		else if (validateMove(r - 1, c - 2) && board[r - 1][c - 2] != null
				&& board[r - 1][c - 2].player() != p
				&& board[r - 1][c - 2].name() == k)
			return true;
		// Check to the right
		else if (validateMove(r + 1, c + 2) && board[r + 1][c + 2] != null
				&& board[r + 1][c + 2].player() != p
				&& board[r + 1][c + 2].name() == k)
			return true;
		else if (validateMove(r - 1, c + 2) && board[r - 1][c + 2] != null
				&& board[r - 1][c + 2].player() != p
				&& board[r - 1][c + 2].name() == k)
			return true;
		else
			return false;
	}

	// Validates if the position is on the board
	private boolean validateMove(int x, int y) {
		return x < 8 && x > -1 && y > -1 && y < 8;
	}

	/**
	 * Method to return whether the game is complete or not
	 * 
	 * @return true if game is done, false if otherwise
	 */
	public boolean isComplete() {
		int count = 0;
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < cols; y++)
				if (board[x][y] != null && board[x][y].name() == "King")
					count++;
		return checkCount > 10 ? true : count != 2;
		// if (checkCount > 10)
		// return true;
		// if (count == 2)
		// return false;
		// else
		// return true;

	}

	/**
	 * Checks the specified move to see if it is valid or not for the piece
	 * 
	 * @return true if the move is valid or false otherwise
	 */
	public boolean isValidMove(Move m) {
		return pieceAt(m.fromRow, m.fromColumn).isValidMove(m, board);
	}

	/**
	 * Moves the chess piece at the specified spot to the specified location.
	 * Before doing so the presenter should call isValidMove first.
	 */
	public void move(Move m) {
		// If the piece at the start spot is this player
		if (board[m.fromRow][m.fromColumn].player() == player) {
			takenPiece = board[m.toRow][m.toColumn];
			// Move the piece and make the old spot empty
			board[m.toRow][m.toColumn] = board[m.fromRow][m.fromColumn];
			board[m.fromRow][m.fromColumn] = null;

			player = player == Player.WHITE ? Player.BLACK : Player.WHITE;
		}
		// else //If not throw an exception
		// throw new IllegalArgumentException("Not your piece");
	}

	/**
	 * Returns the number of Columns on the board
	 * 
	 * @return the number of columns
	 */
	public int numColumns() {
		return cols;
	}

	/**
	 * Returns the number of rows on the board
	 * 
	 * @return the number of rows
	 */
	public int numRows() {
		return rows;
	}

	/**
	 * Method that returns the ChessPiece at the specified postion
	 * 
	 * @param x
	 *            the row to check
	 * @param y
	 *            the column to check
	 * @return the piece on the board
	 */
	public IChessPiece pieceAt(int x, int y) {
		return board[x][y];
	}

	/**
	 * Undoes the last move for when a player moves to a position equating to
	 * him being in check
	 * 
	 * @param m
	 *            the move to undo
	 * @param p
	 *            the player to move
	 */
	public void undoLastMove(Move m, Player p) {
		if (board[m.toRow][m.toColumn] != null
				&& board[m.toRow][m.toColumn].player() == p) {
			// Move the piece and make the old spot empty
			board[m.fromRow][m.fromColumn] = board[m.toRow][m.toColumn];
			if (takenPiece != null)
				board[m.toRow][m.toColumn] = takenPiece;
			else
				board[m.toRow][m.toColumn] = null;
			player = player == Player.WHITE ? Player.BLACK : Player.WHITE;
		}

	}

	/**
	 * Sets the number of times check has been hit
	 * 
	 * @param checkCount
	 *            the number of checks done
	 */
	public void setCheckCount(int checkCount) {
		this.checkCount = checkCount;
	}

}
