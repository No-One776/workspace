/**
 * 
 */
package gvprojects.chess.model;

/**
 * @author rohrj
 * 
 */
public class Bishop extends ChessPiece {

	public Bishop(Player p) {
		super(p, "Bishop");
	}

	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		if (super.isValidMove(m, p) == false)
			return false;
		return checkDiag(m, p);
//		else if (checkDiag(m, p) == false)
//			return false;
//		return true;
	}
}
