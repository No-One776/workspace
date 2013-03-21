package gvprojects.superttt.model;

import static org.junit.Assert.assertEquals;
import gvprojects.sttt.model.CellState;
import gvprojects.sttt.model.GameStatus;

import org.junit.Test;

/**
 * * Tests for the SuperTicTacToeModel class for error handling and accuracy in
 * passing parameters and running through the SuperTicTacToe game.
 * 
 * @author Justin Rohr
 * @version February 1, 2013 
 */

public class SuperTicTacToeModel_Test {
	
	@Test
	public void engineDetectsWrapWin() throws Throwable {
		Model engine = new Model(10, 7, 3);
		engine.select(0, 0);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(1, 1);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(1, 0);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(0, 1);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(9, 0);
		assertEquals(GameStatus.X_WON, engine.status());
	}
	

	@Test
	public void constructorSetsNumRowsCorrectly() throws Throwable {
		Model engine = new Model(10, 3, 3);
		assertEquals(10, engine.numRows());
	}

	@Test
	public void constructorSetsNumRowsCorrectly1() throws Throwable {
		Model engine = new Model(15, 3, 3);
		assertEquals(15, engine.numRows());
	}

	@Test
	public void constructorSetsNumColumnsCorrectly() throws Throwable {
		Model engine = new Model(5, 10, 5);
		assertEquals(10, engine.numColumns());
	}

	@Test
	public void constructorSetsNumColumnsCorrectly1() throws Throwable {
		Model engine = new Model(3, 1, 1);
		assertEquals(1, engine.numColumns());
	}

	@Test
	public void constructorCreatesAnEmptyBoard() throws Throwable {
		Model engine = new Model(4, 3, 3);
		for (int r = 0; r < engine.numRows(); r++) {
			for (int c = 0; c < engine.numColumns(); c++) {
				assertEquals(CellState.EMPTY, engine.cellStatus(r, c));
			}
		}
	}

	@Test
	public void selectSetsCellX() throws Throwable {
		Model engine = new Model(10, 7, 5);
		engine.select(3, 4);
		assertEquals(CellState.X, engine.cellStatus(3, 4));
	}
	
	@Test
	public void selectSetsCellO() throws Throwable {
		Model engine = new Model(10, 7, 5);
		engine.select(2, 4);
		engine.select(3, 4);
		assertEquals(CellState.O, engine.cellStatus(3, 4));
	}

	@Test
	public void resetSetsToZero() {
		Model test = new Model(2, 2, 1);
		test.select(0, 0);
		test.reset();
		assertEquals(CellState.EMPTY, test.cellStatus(0, 0));
	}
	
	//Tests that both the engine detects win and displays correct status
	@Test
	public void engineDetectsWin() throws Throwable {
		Model engine = new Model(10, 7, 3);
		engine.select(0, 0);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(1, 0);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(0, 1);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(1, 1);
		assertEquals(GameStatus.IN_PROGRESS, engine.status());
		engine.select(0, 2);
		assertEquals(GameStatus.X_WON, engine.status());
	}

	// Helper Method for testing the game
	private CellState[][] buildGameBoard(String[] description) {
		CellState[][] answer = new CellState[description.length][description[0]
				.length()];
		for (int r = 0; r < description.length; r++) {
			for (int c = 0; c < description[r].length(); c++) {
				switch (description[r].charAt(c)) {
				case 'x':
					answer[r][c] = CellState.X;
					break;
				case 'o':
					answer[r][c] = CellState.O;
					break;
				case '.':
					answer[r][c] = CellState.EMPTY;
					break;
				default:
					throw new IllegalArgumentException(
							"Illegal format character");
				}
			}
		}
		return answer;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void emptyBoardReturnsNull() throws Throwable {
		String[] board_description = { ".....", ".....", "....." };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(null, Model.checkForWin(game_board, 4));
	}

	@Test
	public void detectsXWininBottomRow() throws Throwable {
		String[] board_description = { ".......", ".......", ".xxxx.." };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.X_WON,
				Model.checkForWin(game_board, 4));
	}

	@Test
	public void detectsOWininBottomRow() throws Throwable {
		String[] board_description = { ".......", ".......", ".oooo.." };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.O_WON,
				Model.checkForWin(game_board, 4));
	}

	@Test
	public void detectsXWinInColumn() throws Throwable {
		String[] board_description = { "x......", "x......", "x......" };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.X_WON,
				Model.checkForWin(game_board, 3));
	}

	@Test
	public void detectsOWinInColumn() throws Throwable {
		String[] board_description = { "o......", "o......", "o......" };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.O_WON,
				Model.checkForWin(game_board, 3));
	}

	@Test
	public void detectsXWinInEndColumn() throws Throwable {
		String[] board_description = { "......x", "......x", "......x" };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.X_WON,
				Model.checkForWin(game_board, 3));
	}

	@Test
	public void detectsOWinInEndColumn() throws Throwable {
		String[] board_description = { "......o", "......o", "......o" };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.O_WON,
				Model.checkForWin(game_board, 3));
	}
	
	@Test
	public void detectsWrapAroundWinRow() throws Throwable {
		String[] board_description = { ".......", "ooo...o", "......." };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.O_WON,
				Model.checkForWin(game_board, 4));
	}
	
	@Test
	public void detectsWrapAroundWinColumn() throws Throwable {
		String[] board_description = { ".o.....", ".o.....", ".......", ".o.....", ".o....." };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.O_WON,
				Model.checkForWin(game_board, 4));
	}

	@Test
	public void detectsTieGame() throws Throwable {
		String[] board_description = { "xoxoxox", "oxoxoxo", "xoxoxox" };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.CATS,
				Model.checkForWin(game_board, 4));
	}
	
	@Test
	public void detectsOWinEndGame() throws Throwable {
		String[] board_description = { "xoxoxox", "oxoxoxo", "ooooooo" };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.O_WON,
				Model.checkForWin(game_board, 4));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void doesNotDetectWinIfNotLongEnough() throws Throwable {
		String[] board_description = { "......", "......", ".xxxx." };
		CellState[][] game_board = buildGameBoard(board_description);
		assertEquals(GameStatus.IN_PROGRESS, Model.checkForWin(game_board, 5));
	}

	@SuppressWarnings("rawtypes")
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsExceptionIfPassedNegativeRow() {
		new Model(-1, 2, 5);
	}

	@SuppressWarnings("rawtypes")
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsExceptionIfPassedNegativeCol() {
		new Model(1, -1, 5);
	}

	@SuppressWarnings("rawtypes")
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsExceptionIfPassedNegativeWin() {
		new Model(4, 4, -1);
	}

	@SuppressWarnings("rawtypes")
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsExceptionIfPassedZeroRow() {
		new Model(0, 5, 2);
	}

	@SuppressWarnings("rawtypes")
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsExceptionIfPassedZeroCol() {
		new Model(3, 0, 1);
	}


//	@Test(expected = IllegalArgumentException.class)
//	public void constructorThrowsExceptionIfPassedNullRow() {
//		new SuperTicTacToeModel((Integer) null, 2, 1);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void constructorThrowsExceptionIfPassedNullCol() {
//		new SuperTicTacToeModel(1, (Integer) null, 1);
//	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsExceptionIfWinLengthIsLongerThanBoard() {
		new Model(1, 1, 5);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsExceptionOnTooLongWinLength() {
		new Model(1, 2, 5);
	}

	public void constructorThrowsExceptionIfBoardIsOversize() {
		new Model(50, 50, 35);
	}

	@Test(expected = theRohrException.class)
	public void cantPlaceOverExistingSpot() {
		Model engine = new Model(10, 7, 3);
		engine.select(1, 1);
		engine.select(1, 1);
	}

	@Test(expected = theRohrException.class)
	public void cantPlaceOverExistingSpot2() {
		Model engine = new Model(10, 7, 3);
		engine.select(2, 4);
		engine.select(2, 4);
	}

	@Test
	public void gameSwitchesPlayersO() {
		Model engine = new Model(10, 7, 3);
		engine.select(2, 4);
		engine.select(2, 5);
		assertEquals(CellState.O, engine.cellStatus(2, 5));
	}

	@Test
	public void gameSwitchesPlayersX() {
		Model engine = new Model(10, 7, 3);
		engine.select(2, 4);
		engine.select(2, 5);
		engine.select(3, 5);
		assertEquals(CellState.X, engine.cellStatus(3, 5));
	}

	@Test
	public void gameSwitchesPlayers2X() {
		Model engine = new Model(10, 7, 3);
		engine.select(2, 4);
		engine.select(2, 5);
		engine.select(3, 5);
		engine.select(2, 6);
		engine.select(2, 1);
		assertEquals(CellState.X, engine.cellStatus(2, 1));
	}

}
