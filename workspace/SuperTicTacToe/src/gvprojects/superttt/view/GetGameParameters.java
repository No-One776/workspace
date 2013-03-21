package gvprojects.superttt.view;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GetGameParameters {
	JFrame f;
	JPanel p;

	private int size;
	private int winlength;
	private Component frame;

	public GetGameParameters() {
		JOptionPane
				.showMessageDialog(
						null,
						"Welcome to Super Tic Tac Toe! \n"
								+ "You can win by either regular or wrapping around the board.\n"
								+ "Next you will be asked for board size & win length.");

		try {
			Object[] possibilities = { 4, 5, 6 };
			size = (int) JOptionPane.showInputDialog(frame,
					"Pick from the board sizes:\n", "Board Size",
					JOptionPane.PLAIN_MESSAGE, null, possibilities, "--by--");
		} catch (NullPointerException e) {
			System.exit(0);
		}
		try {
			if (size == 4) {
				Object[] possibilities1 = { 3, 4 };
				winlength = (int) JOptionPane.showInputDialog(frame,
						"Pick from the win length:\n", "Win Size",
						JOptionPane.PLAIN_MESSAGE, null, possibilities1, "");
			} else if (size == 5) {
				Object[] possibilities1 = { 3, 4, 5 };
				winlength = (int) JOptionPane.showInputDialog(frame,
						"Pick from the win length:\n", "Win Size",
						JOptionPane.PLAIN_MESSAGE, null, possibilities1, "");
			} else {
				Object[] possibilities1 = { 3, 4, 5, 6 };
				winlength = (int) JOptionPane.showInputDialog(frame,
						"Pick from the win length:\n", "Win Size",
						JOptionPane.PLAIN_MESSAGE, null, possibilities1, "");
			}
		} catch (NullPointerException e) {
			System.exit(0);
		}
	}

	public int getBoardSize() {
		return size;
	}

	public int getWinLength() {
		return winlength;
	}
}
