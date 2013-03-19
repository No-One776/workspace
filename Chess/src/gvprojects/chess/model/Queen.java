package gvprojects.chess.model;

/**
 * Queen class that extends ChessPiece to make a queen with the proper
 * rules/moves the queen can make
 * 
 * @author rohrj
 * @version March 15, 2013
 */
public class Queen extends ChessPiece {

	/**
	 * Constructor that creates a Queen chess piece with the specified player
	 * 
	 * @param p
	 *            the player the queen belongs to
	 */
	public Queen(Player p) {
		super(p, "Queen");
	}

	@Override
	/**
	 * Method that returns whether the given move is valid on the board
	 * @return if the move is valid true or not
	 */
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		if (super.isValidMove(m, p) == false)
			return false;
		else if (checkRandC(m, p) == false && checkDiag(m, p) == false)
			return false;
		else if (m.fromColumn != m.toColumn && m.fromRow != m.toRow
				&& checkDiag(m, p) == false)
			return false;
		return true;
	}
}
