package gvprojects.chess.model;

/**
 * Pawn class that extends ChessPiece to make a pawn with the proper
 * rules/moves the pawn can make
 * 
 * @author rohrj
 * @version March 15, 2013
 */
public class Pawn extends ChessPiece {

	private boolean firstMove = true;

	/**
	 * Constructor that makes a Pawn chess piece with the specified player
	 * 
	 * @param p
	 *            the player of the pawn
	 */
	public Pawn(Player p) {
		super(p, "Pawn");
	}

	/**
	 * Method to check whether the move on the board is valid for a pawn
	 * 
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		// Old Tests
		if (super.isValidMove(m, p) == false)
			return false;
		// Checks to make sure move distance is ok
		else if (firstMove == true && checkDistance(m, 2) == false
				|| firstMove == false && checkDistance(m, 1) == false)
			return false;
		else if (m.fromColumn != m.toColumn && m.fromRow != m.toRow) {
			// If moving diagonal check that the spot is another player
			if (p[m.toRow][m.toColumn] != null
					&& (m.toRow - m.fromRow == 1 || m.fromRow - m.toRow == 1)
					&& p[m.toRow][m.toColumn].player() == this.player()
					|| p[m.toRow][m.toColumn] == null)
				return false;
		} else if (m.fromColumn != m.toColumn)
			return false;
		// Check the path is clear
		else if (firstMove == true && checkRandC(m, p) == false)
			return false;
		else if (!(m.fromColumn != m.toColumn && m.fromRow != m.toRow)
				&& p[m.toRow][m.toColumn] != null)
			return false;
		// White Cannot move backwards
		else if (this.player() == Player.WHITE && this.name() == "Pawn") {
			if (m.toRow > m.fromRow)
				return false;
			// Black cannot move backwards
		} else if (this.player() == Player.BLACK && this.name() == "Pawn")
			if (m.toRow < m.fromRow)
				return false;
		firstMove = false;
		return true;
	}

}
