/**
 * 
 */
package gvprojects.mm.shared;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A set of tests for the MammaMia Game Engine that thuroughly test the engines
 * methods for playing the game
 * 
 * @author rohrj
 * @version Mar 29, 2013
 */
public class EngineTest {
	Model engine = new Model();

	@Test
	public void testPlayer() {
		assertEquals(0, engine.getCurrentPlayer());
	}

}
