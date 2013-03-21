package gvprojects.countdowntimer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This project models a Count Down Timer that can get input and accurately
 * count down from a set time and throws exceptions when necessary. 
 * 
 * @Author Justin Rohr
 * @version 1/24/13 Winter 2013
 */

public class CountDownTimer {
	// Instance variables
	private int hours, minutes, seconds;
	private static int count = 0;

	/**
	 * Static method to return the total seconds of the given parameter of time
	 * in hours, minutes, and seconds
	 * 
	 * @param hours
	 *            the number of hours to pass to the timer
	 * @param minutes
	 *            the number of minutes to pass to the timer
	 * @param seconds
	 *            the number of seconds to pass to the timer
	 * @return the total seconds of the timer
	 */
	public static int totalSeconds(int hours, int minutes, int seconds) {
		if (hours < 0 || seconds < 0 || minutes < 0)
			throw new IllegalArgumentException("Invalid Time");

		return (hours * 60) * 60 + minutes * 60 + seconds;
	}

	/**
	 * Makes a CountDownTimer with specified seconds, minutes, and hours by
	 * passing the parameter values to the timer variables. It also checks to
	 * validate the time variables and adds to the count of timers.
	 * 
	 * @param hours
	 *            the number of hours to be passed to the timer
	 * @param minutes
	 *            the number of minutes to be passed to the timer
	 * @param seconds
	 *            the number of seconds to be passed to the timer
	 */
	public CountDownTimer(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;

		validate(this.hours, this.minutes, this.seconds);

		count++;
	}

	/**
	 * Makes a CountDownTimer with specified seconds and minutes by passing the
	 * parameter values to the other CountDownTimer constructor to handle the
	 * input.
	 * 
	 * @param minutes
	 *            the number of minutes to be passed to the timer
	 * @param seconds
	 *            the number of seconds to be passed to the timer
	 */
	public CountDownTimer(int minutes, int seconds) {
		this(0, minutes, seconds);
	}

	/**
	 * Makes a CountDownTimer with specified seconds by passing the parameter
	 * value to the other CountDownTimer constructor to handle the input.
	 * 
	 * @param seconds
	 *            the number of seconds to be passed to the timer
	 */
	public CountDownTimer(int seconds) {
		this(0, 0, seconds);
	}

	/**
	 * This CountDownTimer constructor resets the timer variables to zero and
	 * adds to the count of timers made.
	 */
	public CountDownTimer() {
		hours = 0;
		minutes = 0;
		seconds = 0;

		count++;
	}

	/**
	 * This constructor takes the parameter and passes its values to this timer.
	 * It then checks to see if the times are valid and then adds to the count
	 * of timers.
	 * 
	 * @param other
	 *            CountDownTimer that is passed through to this one.
	 */
	public CountDownTimer(CountDownTimer other) {
		this.hours = other.getHours();
		this.minutes = other.getMinutes();
		this.seconds = other.getSeconds();

		validate(this.hours, this.minutes, this.seconds);
		count++;
	}

	/**
	 * Constructor that takes the String startTime and turns it into a timer by
	 * splitting the string. The method also checks to see if the timer of #: is
	 * passed and puts it as # minutes if so. It also checks for a start time
	 * that passes only colons and no integer data. It also checks and throws an
	 * exception if the string is to long of a time.
	 * 
	 * After passing those exceptions, it passes the array of parts to the timer
	 * field variables by using parseInt on the string. It then checks the
	 * validity of the timer variables and after adds to the count of timers.
	 * 
	 * @param startTime
	 *            the string time being passed into the constructor to set the
	 *            timer's time
	 */
	public CountDownTimer(String startTime) {
		String[] parts = startTime.split(":");

		// Checks to validate a timer ending in a : to pass an error
		int j, k = 0;
		for (j = 0; j < startTime.length(); j++) {
			if (startTime.charAt(j) == ':')
				k++;
		}
		if (k > 0 && parts.length == 1)
			throw new IllegalArgumentException("Invalid Input");

		// Checks for a startTime string with no data values and passes a error
		if (startTime == ":::" || startTime == "::" || startTime == ":"
				|| startTime == null)
			throw new IllegalArgumentException("Invalid Input");

		// Checks for a string input that is too long
		if (parts.length >= 4) {
			throw new IllegalArgumentException(
					"Invalid Time String is too long");
		}

		// Takes the Array parts after validation and passes it through to timer
		// values
		if (parts.length == 3) {
			hours = Integer.parseInt(parts[0]);
			minutes = Integer.parseInt(parts[1]);
			seconds = Integer.parseInt(parts[2]);
		} else if (parts.length == 2) {
			minutes = Integer.parseInt(parts[0]);
			seconds = Integer.parseInt(parts[1]);
		} else if (parts.length == 1)
			seconds = Integer.parseInt(parts[0]);
		else {
			hours = 0;
			minutes = 0;
			seconds = 0;
		}

		// Validates the timer values
		validate(this.hours, this.minutes, this.seconds);

		count++;
	}

	private static void validate(int h, int m, int s) {
		if (h < 0 || s < 0 || m < 0)
			throw new IllegalArgumentException("Invalid Time");
		if (m > 59 || s > 59)
			throw new IllegalArgumentException("Invalid Time");
	}

	/**
	 * Returns the total seconds of the timer by passing this timers field
	 * variables to the static int totalSeconds method
	 * 
	 * @return the total seconds of this timer
	 */
	public int totalSeconds() {
		return totalSeconds(this.hours, this.minutes, this.seconds);
	}

	/**
	 * Tests to see if the other timer object is equal to this timer by
	 * comparing each field. It checks to see if the specified object is another
	 * instance of CountDownTimer
	 * 
	 * @param other
	 *            the object being passed to a CountDownTimer to be compared
	 * @return returns true if the two timers are the same or false if otherwise
	 *         not
	 * 
	 */
	public boolean equals(Object other) {
		// Checks that other is a form of CountDownTimer
		if (!(other instanceof CountDownTimer))
			return false;

		// Passes the object to a count down timer variable
		CountDownTimer other1 = new CountDownTimer(other.toString());

		// Checks the two timers for equality
		if (this.hours == other1.getHours()
				&& this.minutes == other1.getMinutes()
				&& this.seconds == other1.getSeconds())
			return true;
		else
			return false;
	}

	/**
	 * Compares this timer to another given CountDownTimer being specified in
	 * the parameter
	 * 
	 * @param other
	 *            The CountDownTimer being compared too this
	 * @return if this CountDownTimer is the same as the other
	 */

	public int compareTo(CountDownTimer other) {
		return (this.totalSeconds() - other.totalSeconds());
	}

	/**
	 * Helper method that rolls over hours and minutes to seconds when more
	 * seconds are needed and there are minutes and hours left to decrement into
	 * seconds.
	 * 
	 * @param seconds
	 *            the amount of seconds needed to be subtracted
	 */
	private void rolloverTime(int seconds) {
		// Checks to see if there are enough seconds to be subtracted and any
		// minutes to roll down if there is not enough it decrements an hour to
		// use for minutes and seconds
		if (this.seconds < seconds && minutes == 0 && hours != 0) {
			this.hours--;
			this.minutes += 59;
			this.seconds += 60;
		}

		// Checks to see if there are enough seconds to subtract and if not,
		// rolls over a minute into seconds
		if (this.seconds < seconds && minutes != 0) {
			minutes--;
			this.seconds += 60;
		}

		subtract(seconds);
	}

	/**
	 * Subtracts the given parameter of seconds from the timer. The method
	 * checks to see if the seconds to be subtracted are greater than the
	 * timer's total seconds and throws an exception if so. It then checks to
	 * verify the number of seconds to subtract is not less then zero (negative)
	 * and throws an exception if it is.
	 * 
	 * Then if the seconds to be subtracted is greater than the seconds on the
	 * timer it calls the rolloverTime method to roll time over if possible.
	 * After it subtracts the given seconds from the timer.
	 * 
	 * @param seconds
	 *            the number of seconds to be subtracted from the timer
	 */
	public void subtract(int seconds) {
		// Checks to see if the subtract amount is larger than the timer
		if (seconds > this.totalSeconds())
			throw new IllegalArgumentException(
					"Cannot Subtract more than the timer");

		// Checks if seconds is negative
		if (seconds < 0)
			throw new IllegalArgumentException("Invalid Time");
		else if (this.seconds < seconds)
			rolloverTime(seconds);
		else
			this.seconds -= seconds;
	}

	/**
	 * Decreases the timers seconds by one using the subtract method
	 */
	public void dec() {
		subtract(1);
	}

	/**
	 * Saves the current CountDownTimer timer to the specified filename using
	 * the timers toString method to create the line the load method uses to get
	 * the timer
	 * 
	 * @param filename
	 *            the name of the file to save
	 * @throws IOException
	 *             throws an IOException if it fails
	 */
	public void save(String filename) throws IOException {
		FileWriter writer = new FileWriter(filename);
		BufferedWriter out = new BufferedWriter(writer);
		out.write(this.toString());
		out.close();
	}

	/**
	 * Loads the specified timer by scanning the output of the save method and
	 * using the String Constructor to pass it to this timer.
	 * 
	 * @param filename
	 *            name of the file to be loaded
	 * @throws FileNotFoundException
	 *             throws an exception if the file cannot be found
	 */
	public void load(String filename) throws FileNotFoundException {
		Scanner filescan = new Scanner(new File(filename));
		CountDownTimer time = new CountDownTimer(filescan.next());
		filescan.close();
		this.hours = time.getHours();
		this.minutes = time.getMinutes();
		this.seconds = time.getSeconds();
	}

	/**
	 * This method takes and returns the current count of timers made.
	 * 
	 * @return the number count of timers created
	 */
	public static int getNumberCreated() {
		return count;
	}

	/**
	 * Returns the formatted string for the timer and adds 0's where necessary
	 * to keep a nice format of "0:00:00"
	 * 
	 * @return the correctly formatted string of the timer's time
	 */
	public String toString() {
		String helper = hours + ":";

		// Checks and adds an extra 0 if minutes is less than 9
		if (minutes > 9)
			helper += minutes + ":";
		else if (minutes == 0)
			helper += "00:";
		else
			helper += "0" + minutes + ":";

		// Checks and adds an extra 0 if seconds is less than 9
		if (seconds > 9)
			helper += seconds;
		else if (seconds == 0)
			helper += "00";
		else
			helper += "0" + seconds;

		return helper;
	}

	/**
	 * Gets the number of hours and returns them.
	 * 
	 * @return the number of hours on this timer
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Gets the number of minutes and returns them.
	 * 
	 * @return the number of minutes on this timer
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Gets the number of seconds and returns them.
	 * 
	 * @return the number of seconds on this timer
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * Main method that runs tests for the CountDownTimer to check for correct
	 * implementation.
	 * 
	 * @param args
	 *            the array args for the method to use
	 */
	public static void main(String[] args) {
		PassFailTestRunner.run();
	}
}