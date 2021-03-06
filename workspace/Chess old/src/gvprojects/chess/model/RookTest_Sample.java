package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Sample tests for the {@code Rook} class.
 *
 * Notice that this class extends ChessPieceTest.  This means that running all the tests in
 * this class also runs all the tests in ChessPieceTest.  Running the ChessPieceTest tests is important
 * because you want to make sure when overriding methods, you don't break any of the behavior specified
 * by the parent class.
 *
 * @author Zachary Kurmas
 */

public class RookTest_Sample extends ChessPieceTest {


   // This method overrides make(Player p) in the ChessPieceTest class
   // Thus, when running these tests, calling make() generates a Rook object
   // whereas calling make when running ChessPiece Tests generates a ChessPiece object.
   @Override
   protected IChessPiece make(Player p) {
      return new Rook(p);
   }

   // As noted in ChessPieceTest:  Many tests require a valid move.  The set of valid moves is different for
   // each chess piece.  This method generates a move that is valid from the given row and column
   @Override
   protected Move getValidMove(int row, int col) {
      int newRow = row + 1;
      if (newRow >= board.length) {
         newRow = row - 1;
      }
      return new Move(row, col, newRow, col);
   }

   // Verify that a rook can move across a row
   @Test
   public void canMoveInRow() throws Exception {
      board[1][1] = piece;
      assertTrue("Rook Test 1", piece.isValidMove(new Move(1, 1, 1, 6), board));
   }

   // Verify that a rook can move up and down a column
   @Test
   public void canMoveInColumn() throws Throwable {
      board[1][1] = piece;
      assertTrue("Rook Test 2", piece.isValidMove(new Move(1, 1, 6, 1), board));
   }

   // Verify that a rook cannot move diagonally
   @Test
   public void cannotMoveDiagonal() throws Throwable {
      board[1][1] = piece;
      assertFalse("Rook Test 3", piece.isValidMove(new Move(1, 1, 3, 3), board));
   }

   // Verify that a rook cannot jump over other pieces.
   @Test
   public void rowMustBeClear1() throws Throwable {
      board[2][2] = piece;
      board[3][2] = make();
      assertFalse("Rook Test 4", piece.isValidMove(new Move(2, 2, 4, 2), board));
   }

}
