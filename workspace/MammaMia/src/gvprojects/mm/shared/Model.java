package gvprojects.mm.shared;

import gvprojects.mm.model.ICard;
import gvprojects.mm.model.IRecipeCard;
import gvprojects.mm.model.IToppingCard;
import gvprojects.mm.model.MammaMia;

import java.util.List;
import java.util.Stack;

/**
 * A game engine for the Mammia Mia Card game.
 * 
 * @author rohrj
 * @version Mar 29, 2013
 */
public class Model implements MammaMia {

	private int player;

	/**
	 * 
	 */
	public Model() {
		player = 0;
	}

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

		return null;
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

		return null;
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
		return null;
	}

}
