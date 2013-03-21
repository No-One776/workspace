package gvprojects.countdowntimer;

/**
 * Tests the methods of CountDownTimer for accuracy and throwing exceptions
 * @author Justin Rohr
 * @version 1/17/13 Winter 2013
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class CountDownTimerTest {

	//  Tests the totalSeconds method for accuracy
	@Test
	public void totalSecondsCombinesAllThreeValues1() throws Throwable {
		assertEquals(3661, CountDownTimer.totalSeconds(1, 1, 1));
	}

	@Test
	public void totalSecondsCombinesAllThreeValues2() throws Throwable {
		assertEquals(0, CountDownTimer.totalSeconds(0, 0, 0));
	}

	@Test
	public void totalSecondsCombinesAllThreeValues3() throws Throwable {
		assertEquals(11259, CountDownTimer.totalSeconds(3, 5, 159));
	}

	@Test
	public void totalSecondsCombinesAllThreeValues4() throws Throwable {
		assertEquals(4805, CountDownTimer.totalSeconds(0, 80, 5));
	}

	// Tests the default Constructor to set variables to zero
	@Test
	public void defaultConstructorSetsTimeToZero() throws Throwable {
		CountDownTimer timer = new CountDownTimer();
		assertEquals(0, timer.totalSeconds());
	}

	// Tests that the three parameter constructor sets the time correctly
	@Test
	public void threeParameterConstructorCorrectlySetsTime() throws Throwable {
		CountDownTimer timer = new CountDownTimer(1, 2, 3);
		assertEquals(3723, timer.totalSeconds());
	}

	// Tests the two parameter constructor in setting the right time
	@Test
	public void twoParameterConstructorCorrectlySetsTime() throws Throwable {
		CountDownTimer time = new CountDownTimer(5, 3);
		assertEquals(303, time.totalSeconds());
	}

	// Tests the one parameter Constructor for setting the right time
	@Test
	public void oneParameterConstructorCorrectlySetsTime() throws Throwable {
		CountDownTimer time = new CountDownTimer(3);
		assertEquals(3, time.totalSeconds());
	}

	// Tests the constructor that copies a timers values
	@Test
	public void copyConstructor1() throws Throwable {
		CountDownTimer timer1 = new CountDownTimer(2, 3, 4);
		CountDownTimer timer2 = new CountDownTimer(timer1);
		assertEquals(7384, timer2.totalSeconds());
	}

	@Test
	public void copyConstructor2() throws Throwable {
		CountDownTimer timer3 = new CountDownTimer(0, 55, 59);
		CountDownTimer timer4 = new CountDownTimer(timer3);
		assertEquals(3359, timer4.totalSeconds());
	}

	// Tests the String constructor for accuracy
	@Test
	public void stringConstructor1() throws Throwable {
		CountDownTimer timer1 = new CountDownTimer("11:12:13");
		assertEquals(40333, timer1.totalSeconds());
	}

	@Test
	public void stringConstructor2() throws Throwable {
		CountDownTimer timer2 = new CountDownTimer("12:13");
		assertEquals(733, timer2.totalSeconds());
	}

	@Test
	public void stringConstructor3() throws Throwable {
		CountDownTimer timer3 = new CountDownTimer("13");
		assertEquals(13, timer3.totalSeconds());
	}

	@Test
	public void stringConstructor4() throws Throwable {
		CountDownTimer timer4 = new CountDownTimer("0:11:13");
		assertEquals(673, timer4.totalSeconds());
	}

	@Test
	public void stringConstructor5() throws Throwable {
		CountDownTimer timer5 = new CountDownTimer("0:11:03");
		assertEquals(663, timer5.totalSeconds());
	}

	@Test
	public void stringConstructor6() throws Throwable {
		CountDownTimer timer6 = new CountDownTimer("1:1:3");
		assertEquals(3663, timer6.totalSeconds());
	}

	@Test
	public void stringConstructor7() throws Throwable {
		CountDownTimer timer7 = new CountDownTimer("1:0:1");
		assertEquals(3601, timer7.totalSeconds());
	}

	@Test
	public void stringConstructor8() throws Throwable {
		CountDownTimer timer8 = new CountDownTimer("1:0:3");
		assertEquals(3603, timer8.totalSeconds());
	}

	// Tests the equals to method for correct detection
	@Test
	public void equalsDetectsEquality1() throws Throwable {
		CountDownTimer timer1 = new CountDownTimer(2, 3, 4);
		CountDownTimer timer2 = new CountDownTimer("2:03:04");
		assertTrue(timer1.equals(timer2));
	}

	@Test
	public void equalsDetectsEquality2() throws Throwable {
		CountDownTimer timer3 = new CountDownTimer(2, 3, 4);
		CountDownTimer timer4 = new CountDownTimer("1:03:04");
		assertFalse(timer3.equals(timer4));
	}

	@Test
	public void equalsDetectsEquality3() throws Throwable {
		CountDownTimer timer5 = new CountDownTimer(0, 4);
		CountDownTimer timer6 = new CountDownTimer("3:00");
		assertFalse(timer5.equals(timer6));
	}

	@Test
	public void equalsDetectsEquality4() throws Throwable {
		CountDownTimer timer7 = new CountDownTimer(4);
		CountDownTimer timer8 = new CountDownTimer("04");
		assertTrue(timer7.equals(timer8));
	}

	@Test
	public void equalsDetectsEquality5() throws Throwable {
		CountDownTimer timer9 = new CountDownTimer(2, 4);
		CountDownTimer timer10 = new CountDownTimer("1:02:04");
		assertFalse(timer9.equals(timer10));
	}

	@Test
	public void equalsDetectsEquality6() throws Throwable {
		CountDownTimer timer = new CountDownTimer(4);
		CountDownTimer timer1 = new CountDownTimer();
		assertFalse(timer.equals(timer1));
	}

	// Test the compare to method for correct comparison
	@Test
	public void compareToDetectsEquality1() throws Throwable {
		CountDownTimer timer1 = new CountDownTimer(2, 3, 4);
		CountDownTimer timer2 = new CountDownTimer("2:03:04");
		assertTrue(timer1.equals(timer2));
		assertEquals(0, timer2.compareTo(timer1));
	}

	@Test
	public void compareToDetectsEquality2() throws Throwable {
		CountDownTimer timer3 = new CountDownTimer(13, 4);
		CountDownTimer timer4 = new CountDownTimer("13:04");
		assertTrue(timer3.equals(timer4));
		assertEquals(0, timer4.compareTo(timer3));
	}

	@Test
	public void compareToDetectsEquality3() throws Throwable {
		CountDownTimer timer5 = new CountDownTimer(4);
		CountDownTimer timer6 = new CountDownTimer("4");
		assertTrue(timer5.equals(timer6));
		assertEquals(0, timer6.compareTo(timer5));
	}

	@Test
	public void compareToDetectsEquality4() throws Throwable {
		CountDownTimer timer7 = new CountDownTimer(2, 13, 4);
		CountDownTimer timer8 = new CountDownTimer("2:13:04");
		assertTrue(timer7.equals(timer8));
		assertEquals(0, timer8.compareTo(timer7));
	}

	@Test
	public void compareToDetectsEquality5() throws Throwable {
		CountDownTimer timer9 = new CountDownTimer(2, 3, 14);
		CountDownTimer timer10 = new CountDownTimer("2:03:13");
		assertFalse(timer10.equals(timer9));
		assertEquals(1, timer9.compareTo(timer10));
	}

	@Test
	public void compareToDetectsEquality6() throws Throwable {
		CountDownTimer timer11 = new CountDownTimer(1, 3, 4);
		CountDownTimer timer12 = new CountDownTimer("2:03:04");
		assertFalse(timer11.equals(timer12));
		assertEquals(3600, timer12.compareTo(timer11));
	}

	@Test
	public void compareToDetectsEquality7() throws Throwable {
		CountDownTimer timer13 = new CountDownTimer(3, 55);
		CountDownTimer timer14 = new CountDownTimer("3:55");
		assertTrue(timer13.equals(timer14));
		assertEquals(0, timer14.compareTo(timer13));
	}

	@Test
	public void compareToDetectsEquality8() throws Throwable {
		CountDownTimer timer15 = new CountDownTimer(12, 44, 4);
		CountDownTimer timer16 = new CountDownTimer("12:44:04");
		assertTrue(timer15.equals(timer16));
		assertEquals(0, timer16.compareTo(timer15));
	}

	@Test
	public void compareToDetectsEquality9() throws Throwable {
		CountDownTimer timer17 = new CountDownTimer(22, 3, 24);
		CountDownTimer timer18 = new CountDownTimer("22:03:26");
		assertFalse(timer17.equals(timer18));
		assertEquals(2, timer18.compareTo(timer17));
	}

	@Test
	public void compareToDetectsEquality10() throws Throwable {
		CountDownTimer timer19 = new CountDownTimer(0, 3, 6);
		CountDownTimer timer20 = new CountDownTimer("0:03:04");
		assertFalse(timer19.equals(timer20));
		assertEquals(-2, timer20.compareTo(timer19));
	}

	@Test
	public void compareToDetectsEquality11() throws Throwable {
		CountDownTimer timer21 = new CountDownTimer(2, 0, 4);
		CountDownTimer timer22 = new CountDownTimer("2:00:04");
		assertTrue(timer21.equals(timer22));
		assertEquals(0, timer22.compareTo(timer21));
	}

	@Test
	public void compareToDetectsEquality12() throws Throwable {
		CountDownTimer timer24 = new CountDownTimer(2, 04, 0);
		CountDownTimer timer23 = new CountDownTimer("2:04:1");
		assertFalse(timer24.equals(timer23));
		assertEquals(1, timer23.compareTo(timer24));
	}

	// Tests the Subtract method for accuracy and correctness
	@Test
	public void subtractHandlesSmallSeconds1() throws Throwable {
		CountDownTimer timer = new CountDownTimer(55);
		timer.subtract(25);
		assertEquals(30, timer.totalSeconds());
	}

	@Test
	public void subtractHandlesSmallSeconds2() throws Throwable {
		CountDownTimer timer2 = new CountDownTimer(2, 55);
		timer2.subtract(58);
		assertEquals(117, timer2.totalSeconds());
	}

	@Test
	public void subtractHandlesSmallSeconds3() throws Throwable {
		CountDownTimer timer3 = new CountDownTimer(1, 1, 5);
		timer3.subtract(25);
		assertEquals(3640, timer3.totalSeconds());
		assertEquals(0, timer3.getMinutes());
		timer3.subtract(60);
		assertEquals(0, timer3.getHours());
		assertEquals(3580, timer3.totalSeconds());
	}

	// Tests the decrement method for correct decrementing
	@Test
	public void decrementCorrectlySubtractsSeconds() throws Throwable {
		CountDownTimer timer = new CountDownTimer(1, 1);
		timer.dec();
		assertEquals(60, timer.totalSeconds());
		timer.dec();
		assertEquals(59, timer.totalSeconds());
	}

	// Tests the to String method for correct string format
	@Test
	public void toStringAddsLeading0toSeconds() throws Throwable {
		CountDownTimer timer = new CountDownTimer(0, 0, 3);
		assertEquals("0:00:03", timer.toString());
	}

	@Test
	public void toStringAddsLeading0toSeconds1() throws Throwable {
		CountDownTimer timer2 = new CountDownTimer(0, 4, 3);
		assertEquals("0:04:03", timer2.toString());
	}

	@Test
	public void toStringAddsLeading0toSeconds2() throws Throwable {
		CountDownTimer timer3 = new CountDownTimer(1, 11, 13);
		assertEquals("1:11:13", timer3.toString());
	}

	@Test
	public void toStringAddsLeading0toSeconds3() throws Throwable {
		CountDownTimer timer4 = new CountDownTimer(0, 11, 13);
		assertEquals("0:11:13", timer4.toString());
	}

	@Test
	public void toStringAddsLeading0toSeconds4() throws Throwable {
		CountDownTimer timer5 = new CountDownTimer(1, 5, 23);
		assertEquals("1:05:23", timer5.toString());
	}

	@Test
	public void toStringAddsLeading0toSeconds5() throws Throwable {
		CountDownTimer timer6 = new CountDownTimer(0, 0, 0);
		assertEquals("0:00:00", timer6.toString());
	}

	// Tests the load and save methods to ensure accuracy and correct exception
	// throwing
	@Test
	public void testLoadSave1() throws IOException {
		CountDownTimer c1 = new CountDownTimer(10, 10, 10);
		c1.save("timer1.txt");
		CountDownTimer c2 = new CountDownTimer();
		c2.load("timer1.txt");
		assertTrue(c1.equals(c2));
	}

	@Test
	public void testLoadSave2() throws IOException {
		CountDownTimer c3 = new CountDownTimer(1, 11);
		c3.save("timer2.txt");
		CountDownTimer c4 = new CountDownTimer();
		c4.load("timer2.txt");
		assertTrue(c3.equals(c4));
	}

	// Tests the number of CountDownTimer's created method for accuracy
	@Test
	public void testNumberCreated_1() throws IOException {
		int count = CountDownTimer.getNumberCreated();
		new CountDownTimer(1, 1, 1);
		new CountDownTimer(5, 5);
		new CountDownTimer(5);
		new CountDownTimer();
		assertTrue(CountDownTimer.getNumberCreated() == count + 4);
	}

	// Tests the total Seconds method to throw an exception when seconds is
	// negative
	@Test(expected = IllegalArgumentException.class)
	public void totalSecondsShouldThrowExceptionIfSecondsNegative() {
		CountDownTimer.totalSeconds(1, 2, -5);
		CountDownTimer.totalSeconds(1, -2, 5);
		CountDownTimer.totalSeconds(-1, 2, 5);
		CountDownTimer.totalSeconds(1, -2, -5);
		CountDownTimer.totalSeconds(-1, 2, -5);
		CountDownTimer.totalSeconds(-1, -2, -5);
		CountDownTimer.totalSeconds(-1, -2, 5);
	}

	// Tests all the constructors to throw an exception when seconds is
	// negative
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfSecondsNegative1() {
		new CountDownTimer(1, -2, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfSecondsNegative2() {
		new CountDownTimer(1, 2, -5);

	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfSecondsNegative3() {
		new CountDownTimer(-1, 2, 5);

	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfSecondsNegative4() {
		new CountDownTimer(-2, 5);

	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfSecondsNegative5() {
		new CountDownTimer(2, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfSecondsNegative6() {
		new CountDownTimer(-5);
	}

	// Tests the string constructor for throwing an exception with it is passed
	// an invalid string
	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error1() {
		new CountDownTimer("1:2:3:4");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error2() {
		new CountDownTimer("1:-1:1:");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error3() {
		new CountDownTimer("0:1:03:1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error4() {
		new CountDownTimer(":2;");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error5() {
		new CountDownTimer("2/4");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error6() {
		new CountDownTimer("2;");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error7() {
		new CountDownTimer(":4:-1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error8() {
		new CountDownTimer("11:2::99");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error9() {
		new CountDownTimer("a:b");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error10() {
		new CountDownTimer(":::");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error11() {
		new CountDownTimer(":");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error12() {
		new CountDownTimer("::");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringConstructor_error13() {
		new CountDownTimer("3:");
	}

	// Tests that decrement throws an exception if seconds is negative
	@Test(expected = IllegalArgumentException.class)
	public void decShouldThrowExceptionIfSecondsNegative1() {
		CountDownTimer time = new CountDownTimer(1);
		time.dec();
		time.dec();
	}

	@Test(expected = IllegalArgumentException.class)
	public void decShouldThrowExceptionIfSecondsNegative2() {
		CountDownTimer timer = new CountDownTimer(0);
		timer.dec();
	}

	@Test(expected = IllegalArgumentException.class)
	public void decShouldThrowExceptionIfSecondsNegative3() {
		CountDownTimer time = new CountDownTimer(1, 1);
		for (int n = 68; n > 0; n--) {
			time.dec();
		}
	}

	// Tests that subtract throws an exception if seconds is negative
	@Test(expected = IllegalArgumentException.class)
	public void subtractShouldThrowExceptionIfSecondsNegative1() {
		CountDownTimer time = new CountDownTimer(55);
		time.subtract(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void subtractShouldThrowExceptionIfSecondsNegative2() {
		CountDownTimer timer = new CountDownTimer(55);
		timer.subtract(-15);
	}
}
