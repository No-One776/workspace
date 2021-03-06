/**
 * 
 */
package gvprojects.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Justin Rohr
 * 
 */
public class EngineTest {

	private Model engine = new Model();
	Move m = new Move(1, 1, 3, 1);

	@Test
	public void makesBoard() throws Throwable {
		engine = new Model();
	}

	@Test
	public void constructorSetsRowsColumnsCorrectly() throws Throwable {
		assertEquals(8, engine.numColumns());
		assertEquals(8, engine.numRows());
	}

	@Test
	public void playerIntiallyReturnsWhite() throws Throwable {
		assertEquals(Player.WHITE, engine.currentPlayer());
	}

	@Test
	public void gameSwitchesPlayers() throws Throwable {
		engine.move(new Move(7, 7, 6, 7));
		assertEquals(Player.BLACK, engine.currentPlayer());
	}

	@Test
	public void gameMovesPawn() throws Throwable {
		engine.move(new Move(6, 1, 4, 1));
		assertEquals("Pawn", engine.pieceAt(4, 1).name());
	}

	@Test(expected = NullPointerException.class)
	public void gamecannotMovePawnThreeSpots() throws Exception {
		engine.move(new Move(6, 1, 3, 1));
		assertEquals("Pawn", engine.pieceAt(6, 1).name());
	}

	@Test
	public void knightMove() throws Throwable {
		engine.move(new Move(7, 1, 4, 2));
		assertEquals("Knight", engine.pieceAt(4, 2).name());
	}

	@Test
	public void invalidKnightMove() throws Throwable {
		engine.isValidMove(new Move(7, 1, 4, 4));
		assertEquals(null, engine.pieceAt(4, 4));
		assertEquals("Knight", engine.pieceAt(7, 1).name());
	}

	@Test
	public void checkGame() throws Throwable {

		// Moving pieces around without validation to check my inCheck method
		engine.move(new Move(7, 4, 1, 5));
		assertTrue("Check test", engine.inCheck(Player.BLACK));
		engine.move(new Move(0, 5, 5, 5));
		engine.move(new Move(1, 5, 4, 5));
		assertFalse("Check test 1", engine.inCheck(Player.BLACK));
		engine.move(new Move(1, 3, 5, 1));
		assertFalse("Check test 2", engine.inCheck(Player.BLACK));
		assertFalse("Check test 3", engine.inCheck(Player.WHITE));
		engine.move(new Move(6, 4, 4, 4));
		assertTrue("Check test 4", engine.inCheck(Player.WHITE));
	}

	@Test
	public void checkMate() throws Throwable {
		engine.setCheckCount(11);
		assertTrue("CheckMate", engine.isComplete());
	}

}
