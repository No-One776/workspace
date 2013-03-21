package gvprojects.chess.model;

public class ChessPiece implements IChessPiece {
	private Player player;
	private String name;

	public ChessPiece(Player p, String str) {
		player = p;
		name = str;
	}

	public boolean isValidMove(Move m, IChessPiece[][] p) {
		if (m.fromRow == m.toRow || m.toColumn == m.fromColumn)
			return false;
		else if (p[m.toRow][m.toColumn].player() == this.player())
			return false;
		else if (p[m.fromRow][m.fromColumn].equals(null))
			throw new IllegalArgumentException("Piece is not there");
		else
			return true;
	}

	public String name() {
		return name;
	}

	public Player player() {
		return player;
	}
}
