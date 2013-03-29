package gvprojects.mm.shared;

import gvprojects.mm.model.IToppingCard;

/**
 * Topping Card class to make a card with the specified topping as the
 * ingredient for part of a pizza recipe.
 * 
 * @author rohrj
 * @version Mar 29, 2013
 */
public class ToppingCard implements IToppingCard {

	private Topping topping;

	/**
	 * Constructor that takes a topping as a parameter to make a card with the
	 * specified topping
	 * 
	 * @param p
	 *            the topping of the card
	 */
	public ToppingCard(Topping p) {
		topping = p;
	}

	/**
	 * Returns the topping of this card
	 * 
	 * @return the topping of the card
	 */
	public Topping getTopping() {
		return topping;
	}
}
