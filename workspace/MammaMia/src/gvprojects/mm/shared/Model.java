/**
 * 
 */
package gvprojects.mm.shared;

import gvprojects.mm.model.ICard;
import gvprojects.mm.model.IRecipeCard;
import gvprojects.mm.model.IToppingCard;
import gvprojects.mm.model.MammaMia;

import java.util.List;
import java.util.Stack;

/**
 * @author rohrj
 * @version Mar 29, 2013
 */
public class Model implements MammaMia{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int determineWinner() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void drawRecipeCards() throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawToppingCards() throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentPlayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Stack<ICard> getPizzaStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICard> getPlayersHand(int arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stack<IRecipeCard> getPlayersRecipeCards(int arg0)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stack<IToppingCard> getToppingCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEndOfGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEndOfRound() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void playCardsInHand(boolean[] arg0)
			throws IllegalArgumentException, IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IRecipeCard>[] scorePizzaStack() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

}
