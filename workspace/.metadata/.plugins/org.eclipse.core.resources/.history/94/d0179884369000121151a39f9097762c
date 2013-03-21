package gvprojects.chess.model;

/**
 * 
 * @author rohrj
 * 
 */
public class Knight extends ChessPiece {

	public Knight(Player p) {
		super(p, "Knight");
	}

	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		if (super.isValidMove(m, p) == false)
			return false;
		else if (knightMove(m, p))
			return true;
		else
		return knightMove(m, p);
	}

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
