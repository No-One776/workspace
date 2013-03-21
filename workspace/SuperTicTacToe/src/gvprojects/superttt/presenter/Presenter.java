package gvprojects.superttt.presenter;

import gvprojects.sttt.model.CellState;
import gvprojects.sttt.model.GameStatus;
import gvprojects.superttt.model.Model;
import gvprojects.superttt.model.theRohrException;
import gvprojects.superttt.view.GetGameParameters;
import gvprojects.superttt.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Presenter {

	private static int size, winlength, undo;
	private Model engine;
	private View view;
	private int[] tempcol, temprow;

	public Presenter(Model e, View v) {
		undo = 0;
		tempcol = new int[size * size +1];
		temprow = new int[size * size +1];
		engine = e;
		view = v;
		view.addUndoButtonListener(new UndoButtonListener());
		view.addResetButtonListener(new ResetButtonListener());
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				view.addButtonsListeners(r, c, new ButtonListener(r, c));
	}

	class ResetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			engine.reset();
			view.resetBoard();
			undo = 0;
		}
	}

	class UndoButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			undo--;
			if (undo < 0) {
				undo++;
				view.disableUndo();
			} else {
				engine.undoCell(temprow[undo], tempcol[undo]);
				view.setEmpty(temprow[undo], tempcol[undo]);
			}
		}
	}

	class ButtonListener implements ActionListener {

		int row = 0, column = 0;

		public ButtonListener(int r, int c) {
			row = r;
			column = c;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				engine.select(row, column);
			} catch (theRohrException e1) {
				view.displayMessage("That Spot Is Taken!");
			}

			setTemp(row, column);

			CellState state = engine.cellStatus(row, column);

			if (state.equals(CellState.X))
				view.setXIcon(row, column);
			else if (state.equals(CellState.O))
				view.setOIcon(row, column);

			GameStatus status = engine.status();

			if (!(status.equals(GameStatus.IN_PROGRESS))) {
				if (status.equals(GameStatus.X_WON))
					view.displayMessage("X Wins!");
				else if (status.equals(GameStatus.O_WON))
					view.displayMessage("O Wins!");
				else
					view.displayMessage("Tie Game!");

				view.endGame();
			}
		}
	}

	public static void main(String[] args) {
		GetGameParameters game = new GetGameParameters();
		size = game.getBoardSize();
		winlength = game.getWinLength();

		Model engine = new Model(size, size, winlength);
		View view = new View(size);
		new Presenter(engine, view);
	}

	private void setTemp(int r, int c) {
		if (!(undo < 0)) {
			temprow[undo] = r;
			tempcol[undo] = c;
			view.enableUndo();
			undo++;
		}
	}
}
