/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.exceptions;

/**
 * Exception class for dealing with invalid Locators
 *
 */
public class InvalidLocatorException extends RuntimeException {
	/**
	 * Parameterless Constructor
	 */
	public InvalidLocatorException() { }

	/**
	 * Constructor that accepts a message
	 * @param message the exception message
	 */
	public InvalidLocatorException(String message) {
		super(message);
	}
}
