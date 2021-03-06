package gvprojects.chess.model;

/**
 * Bishop class that extends ChessPiece to make a bishop with the proper
 * rules/moves the bishop can make
 * 
 * @author rohrj
 * @version March 15, 2013
 */
public class Bishop extends ChessPiece {

	/**
	 * Constructor that makes a bishop piece with the specified player
	 * 
	 * @param p
	 *            the player of piece
	 */
	public Bishop(Player p) {
		super(p, "Bishop");
	}

	/**
	 * Method that specifies whether the move is valid for the piece on the
	 * board
	 * 
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		return super.isValidMove(m, p) && checkDiag(m, p);
	}
}
