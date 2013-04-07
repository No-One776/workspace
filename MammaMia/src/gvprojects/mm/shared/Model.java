package gvprojects.mm.shared;

import gvprojects.mm.model.ICard;
import gvprojects.mm.model.IRecipeCard;
import gvprojects.mm.model.IToppingCard;
import gvprojects.mm.model.MammaMia;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A game engine for the Mammia Mia Card game.
 * 
 * @author rohrj
 * @version Mar 29, 2013
 */
public class Model implements MammaMia {

	private int player, round;
	private Stack<IToppingCard> supply;
	private ArrayList<Stack> sort;
	private Stack<ICard> discard;
	private Stack<IRecipeCard> recipe;
	private Topping[] pToppings = { Topping.BLACK_OLIVE, Topping.CHEESE,
			Topping.GREEN_PEPPER, Topping.HAM, Topping.SAUSAGE, Topping.ANY};

	/**
	 * TODO:Make a player class with Topping, Stack<ICard>'s in your Hand
	 */
	public Model() {
		player = 0;
		round = 0;
		supply = new Stack<IToppingCard>();
		recipe = new Stack<IRecipeCard>();
		Stack<IToppingCard> sorting;
		sort = new ArrayList<Stack>();
		for (int x = 0; x < 6; x++) {
			sorting = new Stack<IToppingCard>();
			sort.add(sorting);
		}
	}

	private void sortCard(IToppingCard card) {
		switch(card.getTopping()){
		case GREEN_PEPPER:
			sort.get(0).push(card);
			break;
		case BLACK_OLIVE:
			sort.get(1).push(card);
			break;
		case HAM:
			sort.get(2).push(card);
			break;
		case CHEESE:
			sort.get(3).push(card);
			break;
		case SAUSAGE:
			sort.get(4).push(card);
			break;
		case ANY:
			sort.get(5).push(card);
			break;
		}
		//Sort the discard pile
	}

	/**
	 * TODO:Engine Stuff --Modifier and Type Method and Description--
	 * 
	 * int determineWinner() Determine the integer playerid of the winner.
	 * 
	 * void drawRecipeCards() The current player draws cards from her own recipe
	 * pile to keep her hands full (when possible).
	 * 
	 * void drawToppingCards() The current player draws cards from the topping
	 * pile to keep her hands full (when possible).
	 * 
	 * int getCurrentPlayer() Return the integer playerid associated with the
	 * current player
	 * 
	 * java.util.Stack<ICard> getPizzaStack() get the cards in the pizza stack
	 * 
	 * java.util.List<ICard> getPlayersHand(int playerId) Get the current cards
	 * held by a particular player
	 * 
	 * java.util.Stack<IRecipeCard> getPlayersRecipeCards(int playerId) Get the
	 * recipe cards owned by a particula player
	 * 
	 * java.util.Stack<IToppingCard> getToppingCards() get the cards in the
	 * topping pile
	 * 
	 * void playCardsInHand(boolean[] selected) The current player plays the
	 * selected card(s) from her hand to the pizza stack.
	 * 
	 * java.util.List<IRecipeCard>[] scorePizzaStack() Check the pizza stack for
	 * completed orders.
	 */

	/**
	 * 
	 */
	public int determineWinner() {

		return 0;
	}

	/**
	 * 
	 */
	public void drawRecipeCards() throws IllegalStateException {

	}

	/**
	 * 
	 */
	public void drawToppingCards() throws IllegalStateException {

	}

	/**
	 * 
	 */
	public int getCurrentPlayer() {
		return player;
	}

	/**
	 * 
	 */
	public Stack<ICard> getPizzaStack() {
		return discard;
	}

	/**
	 * 
	 */
	public List<ICard> getPlayersHand(int arg0) throws IllegalArgumentException {
		return null;
	}

	/**
	 * 
	 */
	public Stack<IRecipeCard> getPlayersRecipeCards(int arg0)
			throws IllegalArgumentException {

		return null;
	}

	/**
	 * 
	 */
	public Stack<IToppingCard> getToppingCards() {
		return supply;
	}

	/**
	 * 
	 */
	public boolean isEndOfGame() {
		return false;
	}

	/**
	 * 
	 */
	public boolean isEndOfRound() {
		//return round < 3 ? false : true;
		return false;
	}

	/**
	 * 
	 */
	public void playCardsInHand(boolean[] arg0)
			throws IllegalArgumentException, IllegalStateException {

	}

	/**
	 * 
	 */
	public List<IRecipeCard>[] scorePizzaStack() throws IllegalStateException {
		//sortCard();
		return null;
	}

}
