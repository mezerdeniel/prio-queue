/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.queue;

import java.util.Comparator;
import java.util.Random;

import ch.bfh.basics.Locator;

//CHECKSTYLE:OFF MagicNumber
/**
 * Simple example
 */
public class PriorityQueueUseExample {

	/**
	 * Small examples of how to use a PriorityQueue.
	 *
	 * @param args (ignored)
	 */
	public static void main(String[] args) {
		Comparator<Integer> integerComparator = Comparator.naturalOrder();

		// PriorityQueue with integers and natural order of integer
		PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(integerComparator);

		pq.insert(5, "five");
		pq.insert(3, "three");
		pq.insert(1, "one");
		pq.insert(2, "two");
		pq.insert(4, "four");

		System.out.println("Size is " + pq.size());
		while (!pq.isEmpty()) {
			System.out.println("Removed Element = " + pq.removeMin());
		}
		System.out.println("Size is " + pq.size());

		// PriorityQueue with integers and reversed natural order of integer
		pq = new HeapBasedPriorityQueue<>(integerComparator.reversed());

		pq.insert(5, "five");
		pq.insert(3, "three");
		pq.insert(1, "one");
		pq.insert(2, "two");
		pq.insert(4, "four");

		System.out.println("\nSize is " + pq.size());
		while (!pq.isEmpty()) {
			System.out.println("Removed Element = " + pq.removeMin());
		}
		System.out.println("Size is " + pq.size());


		pq = new HeapBasedPriorityQueue<>(integerComparator);
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			pq.insert(r.nextInt(), "" + i);
		}
		System.out.println("\nSize is " + pq.size());
		while (!pq.isEmpty()) {
			System.out.println("Removed Element = " + pq.minKey() + " / " + pq.removeMin());
		}




		// PriorityQueue using positions to remove and replace
		pq = new HeapBasedPriorityQueue<>(integerComparator);
		pq.insert(2, "two");
		pq.insert(6, "six");
		Locator p1 = pq.insert(4, "four");
		Locator p2 = pq.insert(1, "one");
		pq.insert(5, "five");
		Locator p3 = pq.insert(3, "three");
		System.out.println("\nSize is " + pq.size());
		System.out.println("Remove p1: " + pq.remove(p1));
		System.out.println("Replace at p2, old element was: " + pq.replaceElement(p2, "0000"));
		System.out.println("Replace at p3, old key was: " + pq.replaceKey(p3, -10));

		System.out.println("\nSize is " + pq.size());
		while (!pq.isEmpty()) {
			System.out.println("Removed Element = " + pq.minKey() + " / " + pq.removeMin());
		}

		pq = new HeapBasedPriorityQueue<>(integerComparator);

		pq.insert(5, "five");
		pq.insert(3, "three");
		pq.insert(1, "one");
		pq.insert(2, "two");
		pq.insert(4, "four");
		pq.setComparator(integerComparator.reversed());

		System.out.println("\nSize is " + pq.size());
		while (!pq.isEmpty()) {
			System.out.println("Removed Element = " + pq.removeMin());
		}
		System.out.println("Size is " + pq.size());
	}
}
