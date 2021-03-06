package gvprojects.chess.model;

/**
 * A Rook Class that extends IChessPiece to make a Rook that overrides the
 * isValidMove to implement Rook rules
 * 
 * @author rohrj
 * @version March 18, 2013
 */
public class Rook extends ChessPiece {

	/**
	 * Constructor that takes the player parameter and creates a rook with the
	 * parent class
	 * 
	 * @param p
	 *            the player the piece belongs to
	 */
	public Rook(Player p) {
		super(p, "Rook");
	}

	/**
	 * Makes it so the Rook can only do what a Rook does
	 * 
	 * @return true or false if the move is valid
	 */
	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		if (m.toRow != m.fromRow && m.toColumn != m.fromColumn)
			return false;
		if (super.isValidMove(m, p) == false)
			return false;
		return checkRandC(m, p);
	}
}