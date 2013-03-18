package gvprojects.chess.model;

public class Rook extends ChessPiece {

	public Rook(Player p) {
		super(p, "Rook");
	}

	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		if (m.toRow != m.fromRow && m.toColumn != m.fromColumn)
			return false;
		if (super.isValidMove(m, p) == false)
			return false;
		return checkRandC(m, p);
	}
}