package gvprojects.chess.model;

/**
 * Knight class that extends ChessPiece to make a knight with the proper
 * rules/moves the Knight can make
 * 
 * @author rohrj
 * @version March 15, 2013
 */
public class Knight extends ChessPiece {

	/**
	 * Constructor that makes a knight with the specified player
	 * 
	 * @param p
	 *            the player of the piece
	 */
	public Knight(Player p) {
		super(p, "Knight");
	}

	/**
	 * Method that specifies if the move is valid on the given board
	 * 
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		return super.isValidMove(m, p) && (knightMove(m, p));
	}

	// Helper method that specifies what moves are valid for a knight
	private boolean knightMove(Move m, IChessPiece[][] p) {
		if (m.fromRow + 2 == m.toRow
				&& (m.fromColumn + 1 == m.toColumn || m.fromColumn - 1 == m.toColumn))
			return true;
		else if (m.fromRow - 2 == m.toRow
				&& (m.fromColumn + 1 == m.toColumn || m.fromColumn - 1 == m.toColumn))
			return true;
		else if (m.fromColumn + 2 == m.toColumn
				&& (m.fromRow + 1 == m.toRow || m.fromRow - 1 == m.toRow))
			return true;
		else if (m.fromColumn - 2 == m.toColumn
				&& (m.fromRow + 1 == m.toRow || m.fromRow - 1 == m.toRow))
			return true;
		return false;
	}
}
