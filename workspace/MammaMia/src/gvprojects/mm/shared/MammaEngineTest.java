package gvprojects.mm.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MammaEngineTest {
	Model engine;
	private final int NUM_PLAYERS = 3;
	private final int NUM_ROUNDS = 3;
	private final int NUM_COPIES = 5;

	@Before
	public void createEngine() {
		engine = new Model(NUM_PLAYERS, NUM_ROUNDS, NUM_COPIES);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badNumberPlayers() throws Throwable {
		engine = new Model(-3, 3, 14);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badNumberRounds() throws Throwable {
		engine = new Model(3, -1, 14);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badNumberOfCopies() throws Throwable {
		engine = new Model(3, 3, 2);
	}

	@Test
	public void testWinner() throws Throwable {
		for (int y = 0; y < NUM_ROUNDS; y++)
			for (int x = 0; x < NUM_PLAYERS; x++) {
				engine.skipPlayed();
				if (!(y == NUM_ROUNDS - 1 && x == NUM_ROUNDS - 1))
					engine.nextPlayer();
			}
		assertEquals(0, engine.determineWinner());
		engine.clearSort();
	}

	@Test
	public void testDrawRecipeCards() throws Throwable {
		engine.playCardsInHand(new boolean[] { false, false, false, false,
				false, false, true });
		engine.drawRecipeCards();
		assertEquals(6, engine.getPlayersRecipeCards(0).size());
		assertEquals(7, engine.getPlayersHand(0).size());
	}

	@Test
	public void testDrawToppingCards() throws Throwable {
		engine.playCardsInHand(new boolean[] { true, false, false, false,
				false, false, false });
		int supplySize = engine.getToppingCards().size();
		engine.drawToppingCards();
		assertEquals(supplySize - 1, engine.getToppingCards().size());
		assertEquals(7, engine.getPlayersRecipeCards(0).size());
		assertEquals(7, engine.getPlayersHand(0).size());
	}

	@Test(expected = IllegalStateException.class)
	public void testBadToppingDraw() throws Throwable {
		engine.getToppingCards().removeAllElements();
		engine.playCardsInHand(new boolean[] { true, false, false, false,
				false, false, false });
		engine.drawToppingCards();
	}

	@Test
	public void testCurrentPlayer() throws Throwable {
		assertEquals(engine.getCurrentPlayer(), 0);

		engine.playCardsInHand(new boolean[] { true, false, false, false,
				false, false, false });
		engine.drawToppingCards();
		engine.nextPlayer();
		assertEquals(engine.getCurrentPlayer(), 1);
	}

	@Test
	public void testDiscardPile() throws Throwable {
		assertEquals(0, engine.getPizzaStack().size());
		engine.playCardsInHand(new boolean[] { true, false, false, false,
				false, false, false });
		assertEquals(1, engine.getPizzaStack().size());

	}

	@Test
	public void testEndOfRound() throws Throwable {
		engine.getToppingCards().removeAllElements();
		for (int i = 0; i < this.NUM_PLAYERS; i++) {
			engine.playCardsInHand(new boolean[] { false, false, false, false,
					false, false, true });
			engine.drawRecipeCards();
			engine.nextPlayer();
		}
		assertTrue(engine.isEndOfRound());
	}

	@Test(expected = IllegalArgumentException.class)
	public void playNoCards() throws Throwable {
		engine.playCardsInHand(new boolean[] { false, false, false, false,
				false, false, false });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testplay2RecipeCards() throws Throwable {
		engine.getPlayersHand(0).set(1, new RecipeCard(null, null, null));
		engine.getPlayersHand(0).set(0, new RecipeCard(null, null, null));
		engine.playCardsInHand(new boolean[] { true, true, false, false, false,
				false, false });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlayDiftoppins() throws Throwable {
		engine.getPlayersHand(0).set(0, new ToppingCard(Topping.GREEN_PEPPER));
		engine.getPlayersHand(0).set(1, new ToppingCard(Topping.CHEESE));
		engine.playCardsInHand(new boolean[] { true, true, false, false, false,
				false, false });

	}

	private void makeEndGame() {
		engine.getToppingCards().removeAllElements();
		for (int i = 0; i < this.NUM_PLAYERS; i++) {
			engine.playCardsInHand(new boolean[] { true, true, false, false,
					false, false, false });
			engine.drawRecipeCards();
		}
	}

	@Test
	public void testSupplyStackCreation() throws Throwable {
		assertEquals(NUM_COPIES * 5 - (NUM_PLAYERS * 6), engine
				.getToppingCards().size());
	}

	@Test
	public void testEndOfGame() throws Throwable {
		assertFalse(engine.isEndOfGame());
	}

	@Test
	public void testPlayCards() throws Throwable {
		engine.playCardsInHand((new boolean[] { true, false, false, false,
				false, false, false }));
	}
}
