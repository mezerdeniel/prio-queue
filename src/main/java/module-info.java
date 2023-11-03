/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */


// You are not allowed to change this file.


/**
 * Module priorityqueue exports and opens all types
 */
module priorityqueue {
	exports ch.bfh.queue;
	exports ch.bfh.basics;
	exports ch.bfh.exceptions;
	
	opens ch.bfh.queue;
	opens ch.bfh.basics;
	opens ch.bfh.exceptions;
}