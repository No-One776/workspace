package gvprojects.chess.model;

public class Rook extends ChessPiece {

	public Rook(Player p) {
		super(p, "Rook");
	}

	@Override
	public boolean isValidMove(Move m, IChessPiece[][] p) {
		if (m.fromRow == m.toRow || m.toColumn == m.fromColumn)
			return false;
		else if (p[m.toRow][m.toColumn].player() == this.player())
			return false;
		else if (p[m.fromRow][m.fromColumn].equals(null))
			throw new IllegalArgumentException("Piece is not there");
		else if (m.fromRow != m.toRow && m.fromColumn != m.toColumn)
			return false;
		else
			return true;
	}
}