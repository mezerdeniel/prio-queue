/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.exceptions;

/**
 * Exception class for dealing with invalid Locators
 *
 */
public class InvalidComparatorException extends RuntimeException {
	/**
	 * Parameterless Constructor
	 */
	public InvalidComparatorException() { }

	/**
	 * Constructor that accepts a message
	 * @param message the exception message
	 */
	public InvalidComparatorException(String message) {
		super(message);
	}
}
