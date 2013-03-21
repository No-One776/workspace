package gvprojects.chess.model;

/**
 * King class that extends ChessPiece to make a King with the proper rules/moves
 * the King can make
 * 
 * @author rohrj
 * @version March 15, 2013
 */
public class King extends ChessPiece {

	/**
	 * Constructor that makes a king with the specified player
	 * 
	 * @param p
	 *            the player of the piece
	 */
	public King(Player p) {
		super(p, "King");
	}

	/**
	 * Method that specifies if the move is valid for a king on the board
	 * 
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		return super.isValidMove(m, p) && checkDistance(m, 1);
	}

}
