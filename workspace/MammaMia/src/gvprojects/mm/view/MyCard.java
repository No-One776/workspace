/**
 * 
 */
package gvprojects.mm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * This holds buffered images and a background image that creates a card that
 * looks like a card from a deck.
 * 
 * @author rohrj
 * @version Apr 12, 2013
 */
public class MyCard extends JPanel {

	private BufferedImage front, back, current;
	private Dimension dim;

	/**
	 * Constructor that makes a MyCard from the specified images, sets the
	 * current display image to front, and sets the dimension of the card.
	 * 
	 * @param frontImg
	 *            the front image to use for the card
	 * @param backImg
	 *            the back image to use for the card
	 */
	public MyCard(BufferedImage frontImg, BufferedImage backImg) {
		front = frontImg;
		back = backImg;
		/* make the card to face up by default */
		current = front;
		dim = new Dimension(front.getWidth(), front.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(current, 0, 0, /* coordinate of the upper-left corner */
				Color.WHITE, /* background color */
				null /* no image observer */);
	}

	/**
	 * The image to display is set as the front
	 */
	public void faceUp() {
		current = front;
	}

	/**
	 * The image to display for the card is the back
	 */
	public void faceDown() {
		current = back;
	}

	/**
	 * Returns the dimension of the card
	 * 
	 * @return the dimension of this card
	 */
	@Override
	public Dimension getMinimumSize() {
		return dim;
	}

	/**
	 * Returns the preferred size of this card
	 * 
	 * @return the preferred dimension
	 */
	@Override
	public Dimension getPreferredSize() {
		return dim;
	}
}
