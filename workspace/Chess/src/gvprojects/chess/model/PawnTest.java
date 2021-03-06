package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the Pawn Chess Piece
 * 
 * @author rohrj
 * 
 */
public class PawnTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new Pawn(p);
	}

	@Override
	protected Move getValidMove(int row, int col) {
		return new Move(row, col, row - 1, col);
	}

	private IChessPiece black = make(Player.BLACK);
	private IChessPiece white = make(Player.WHITE);

	@Test
	public void canMoveTwoSpotsFirstMove() throws Throwable {
		board[1][1] = black;
		assertTrue("Pawn Test 1",
				black.isValidMove(new Move(1, 1, 3, 1), board));
	}

	@Test
	public void cannotMoveTwoSpotsSecondMove() throws Throwable {
		board[1][1] = black;
		black.isValidMove(new Move(1, 1, 3, 1), board);
		board[3][1] = board[1][1];
		assertFalse("Pawn Test 2",
				black.isValidMove(new Move(3, 1, 6, 1), board));
	}

	@Test
	public void blackCanCaptureDiagonalPiece() throws Throwable {
		board[1][1] = black;
		board[2][2] = white;
		assertTrue("Pawn Test 3",
				black.isValidMove(new Move(1, 1, 2, 2), board));
	}

	@Test
	public void cannotMoveOverPiece() throws Throwable {
		board[1][1] = black;
		board[2][1] = white;
		assertFalse("Pawn Test 4",
				black.isValidMove(new Move(1, 1, 3, 1), board));
	}

	@Test
	public void cannotMoveSideways() throws Throwable {
		board[1][1] = black;
		assertFalse("Pawn Test 5",
				black.isValidMove(new Move(1, 1, 1, 2), board));
		assertFalse("Pawn Test 5-2",
				black.isValidMove(new Move(1, 1, 1, 0), board));
	}

	@Test
	public void cannotMoveBackwards() throws Throwable {
		board[4][1] = black;
		assertFalse("Pawn Test 6",
				black.isValidMove(new Move(4, 1, 3, 1), board));
		board[1][3] = white;
		assertFalse("Pawn Test 6-2",
				white.isValidMove(new Move(1, 3, 2, 3), board));
	}

	@Test
	public void whitePawnCanMoveForwards() throws Throwable {
		board[6][1] = white;
		assertTrue("Pawn Test 7",
				white.isValidMove(new Move(6, 1, 4, 1), board));
	}

	@Test
	public void cannotMoveDiagonal() throws Throwable {
		board[1][1] = black;
		assertFalse("Pawn Test 9",
				black.isValidMove(new Move(1, 1, 2, 2), board));
		assertFalse("Pawn Test 9-2",
				black.isValidMove(new Move(1, 1, 3, 3), board));
	}

	// Override because getValidMove moves forward 1 and a pawn can only
	// capture going diagonal and not forward
	@Override
	@Test
	public void canCapture() throws Throwable {
		Move move = new Move(1, 1, 2, 2);
		board[move.toRow][move.toColumn] = make(piece.player().next());
		board[move.fromRow][move.fromColumn] = piece;
		assertTrue("ChessPiece Test 4", piece.isValidMove(move, board));
	}
}
