package gvprojects.Test.practicecode;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Draws several different kinds of fractals.
 * 
 * @author Zachary Kurmas
 */
// Created 3/8/13 at 8:58 AM
// (C) Zachary Kurmas 2013

public class Fractal {

	private JFrame frame;
	private JPanel panel;

	public Fractal() {

		// panel = new TreePanel();
		panel = new KochSnowflake();

		frame = new JFrame("Koch Snowflake");
		frame.setContentPane(panel);

		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Fractal();
	}

}
