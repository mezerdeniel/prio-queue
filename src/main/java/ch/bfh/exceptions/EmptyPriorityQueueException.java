/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.exceptions;

/**
 * Exception class for dealing with empty PriorityQueues
 *
 */

public class EmptyPriorityQueueException extends RuntimeException {
	/**
	 * Parameterless Constructor
	 */
	public EmptyPriorityQueueException() { }

	/**
	 * Constructor that accepts a message
	 * @param message the exception message
	 */
	public EmptyPriorityQueueException(String message) {
		super(message);
	}
}
