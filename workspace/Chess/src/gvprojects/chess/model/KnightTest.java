package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KnightTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new Knight(p);
	}

	@Override
	protected Move getValidMove(int row, int col) {
		return new Move(row, col, row + 2, col + 1);
	}

	private IChessPiece knight = make(Player.BLACK);

	@Test
	public void canMoveL() throws Throwable {
		board[1][1] = knight;
		assertTrue("knight Test 1",
				knight.isValidMove(getValidMove(1, 1), board));
		assertTrue("knight Test 1-2",
				knight.isValidMove(new Move(1, 1, 3, 0), board));
		board[4][4] = knight;
		assertTrue("knight Test 1-3",
				knight.isValidMove(new Move(4, 4, 2, 3), board));
		assertTrue("knight Test 1-4",
				knight.isValidMove(new Move(4, 4, 5, 2), board));
	}

	@Test
	public void cannotMoveNonL() throws Throwable {
		board[1][1] = knight;
		assertFalse("knight Test 2",
				knight.isValidMove(new Move(1, 1, 4, 0), board));
		assertFalse("knight Test 2-2",
				knight.isValidMove(new Move(1, 1, 6, 7), board));
		assertFalse("knight Test 2-3",
				knight.isValidMove(new Move(1, 1, 7, 0), board));
		assertFalse("knight Test 2-4",
				knight.isValidMove(new Move(1, 1, 4, 3), board));
	}

	@Test
	public void cannotMoveOverandCapture() throws Throwable {
		board[1][1] = knight;
		board[1][2] = make();
		board[1][3] = make();
		board[2][3] = make();
		assertTrue("knight Test 3",
				knight.isValidMove(new Move(1, 1, 2, 3), board));
	}

	@Test
	public void cannotCaptureOwnPlayer() throws Throwable {
		board[1][1] = knight;
		board[2][4] = knight;
		assertFalse("knight Test 4",
				knight.isValidMove(new Move(1, 1, 2, 4), board));
	}
}
