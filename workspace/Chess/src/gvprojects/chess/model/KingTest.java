package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KingTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new King(p);
	}

	private IChessPiece king = make(Player.WHITE);

	@Test
	public void canMoveAnywhereInOneSpace() throws Throwable {
		board[1][1] = king;
		assertTrue("King Test 1", king.isValidMove(new Move(1, 1, 2, 2), board));
		assertTrue("King Test 1-2",
				king.isValidMove(new Move(1, 1, 1, 2), board));
		assertTrue("King Test 1-3",
				king.isValidMove(new Move(1, 1, 0, 0), board));
		assertTrue("King Test 1-4",
				king.isValidMove(new Move(1, 1, 1, 0), board));
	}

	@Test
	public void cannotMoveAnywherePastOneSpace() throws Throwable {
		board[1][1] = king;
		assertFalse("King Test 2",
				king.isValidMove(new Move(1, 1, 3, 3), board));
		assertFalse("King Test 2-2",
				king.isValidMove(new Move(1, 1, 3, 1), board));
		assertFalse("King Test 2-3",
				king.isValidMove(new Move(1, 1, 0, 3), board));
		assertFalse("King Test 2-4",
				king.isValidMove(new Move(1, 1, 7, 7), board));
	}

	@Test
	public void cannotMoveOntoOwnPlayer() throws Throwable {
		board[1][1] = king;
		board[1][2] = king;
		assertFalse("King Test 3",
				king.isValidMove(new Move(1, 1, 1, 2), board));
	}
	
	@Test
	public void canCapture() throws Throwable {
		board[1][1] = king;
		board[1][2] = make(Player.BLACK);
		assertTrue("King Test 3",
				king.isValidMove(new Move(1, 1, 1, 2), board));
	}
}
