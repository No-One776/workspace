package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueenTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new Queen(p);
	}

	@Override
	protected Move getValidMove(int row, int col) {
		return new Move(row, col, row + 1, col + 1);
	}

	private IChessPiece queen = make(Player.WHITE);

	// Verify that a Queen can move across a row
	@Test
	public void canMoveInRow() throws Exception {
		board[1][1] = queen;
		assertTrue("Queen Test 1",
				queen.isValidMove(new Move(1, 1, 1, 6), board));
	}

	// Verify that a Queen can move up and down a column
	@Test
	public void canMoveInColumn() throws Throwable {
		board[1][1] = queen;
		assertTrue("Queen Test 2",
				queen.isValidMove(new Move(1, 1, 6, 1), board));
	}

	// Verify that a Queen cannot jump over otherqueens.
	@Test
	public void rowMustBeClear1() throws Throwable {
		board[2][2] = queen;
		board[3][2] = make();
		assertFalse("Queen Test 4",
				queen.isValidMove(new Move(2, 2, 4, 2), board));
	}

	@Test
	public void mustBeClearBackwards() throws Throwable {
		board[3][2] = queen;
		board[2][2] = make();
		board[3][3] = make();
		assertFalse("Queen Test 4",
				queen.isValidMove(new Move(3, 2, 1, 2), board));
		assertFalse("Queen Test 4-2",
				queen.isValidMove(new Move(3, 2, 3, 4), board));
	}

	@Test
	public void colMustBeClear1() throws Throwable {
		board[2][2] = queen;
		board[2][3] = make();
		assertFalse("Queen Test 4",
				queen.isValidMove(new Move(2, 2, 2, 4), board));
	}
	
	@Test
	public void canMoveDiagonal() throws Throwable {
		board[1][1] =queen;
		assertTrue("queen Test 1",
				queen.isValidMove(new Move(1, 1, 2, 0), board));
		assertTrue("queen Test 1-2",
				queen.isValidMove(new Move(1, 1, 7, 7), board));
		assertTrue("queen Test 1-3",
				queen.isValidMove(new Move(1, 1, 0, 0), board));
		assertTrue("queen Test 1-4",
				queen.isValidMove(new Move(1, 1, 0, 2), board));
	}

	@Test
	public void cannotMoveOverPieceDiagonal() throws Throwable {
		board[1][1] = queen;
		board[2][2] = make();
		assertFalse("queen Test 3",
				queen.isValidMove(new Move(1, 1, 3, 3), board));
	}
	
	@Test
	public void cannotMoveOverPiece() throws Throwable {
		board[1][1] = queen;
		board[1][2] = make();
		assertFalse("queen Test 4",
				queen.isValidMove(new Move(1, 1, 1, 3), board));
	}
	
	@Test
	public void cannotMoveHorizontDiagonal() throws Throwable {
		board[1][1] = queen;
		assertFalse("queen Test 5",
				queen.isValidMove(new Move(1, 1, 7, 0), board));
	}
	
}
