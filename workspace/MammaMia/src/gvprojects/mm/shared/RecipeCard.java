package gvprojects.mm.shared;

import gvprojects.mm.model.IRecipeCard;
import gvprojects.mm.view.MyCard;

/**
 * Recipe Card that is used to make a players needed Recipe for the card by
 * taking the string of ingredients, a topping that specifies who the owner of
 * the card is, and a MyCard for the image of this recipe.
 * 
 * @author rohrj
 * @version Mar 29, 2013
 */
public class RecipeCard implements IRecipeCard {

	private String recipe;
	private Topping topping;
	private MyCard card;

	/**
	 * Creates a Recipe card with the specified string of ingredients and the
	 * topping which specifies the owner
	 * 
	 * @param s
	 *            the String of Ingredients
	 * @param p
	 *            the "owner" Topping of the card
	 */
	public RecipeCard(String s, Topping p, MyCard card) {
		recipe = s;
		topping = p;
		this.card = card;
	}

	/**
	 * Returns the MyCard associated with this recipe card
	 * 
	 * @return the MyCard of this recipe
	 */
	public MyCard getCard() {
		return card;
	}

	/**
	 * Method to return who owns the Recipe card
	 * 
	 * @return the topping "owner" of the card
	 */
	public Topping getOwner() {
		return topping;
	}

	/**
	 * Method that returns the string of toppings required for the recipe card
	 * 
	 * @return the string of toppings for the recipe
	 */
	public String getToppings() {
		return recipe;
	}
}
