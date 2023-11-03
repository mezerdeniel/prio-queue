/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.queue;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import ch.bfh.basics.Locator;

/**
 * This class make some very simple time tests.
 * Note that fulfilling these tests does not imply that
 * the implementation satisfies the complexity requirements.
 */
//CHECKSTYLE:OFF MagicNumber
class TimeTests {
	private static final int num = 100000;
	private static final Comparator<Integer> INT_COMPARATOR = Comparator.naturalOrder();

	@Test
	void testInsertAscendingTimeWithComparator() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.insert(i, "" + i);
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasLessThanLinearTimeBehaviour(result, 5, num, "InsertAscendingTime:"))
			throw new RuntimeException("Method insertElement is too slow, not O(log(n))!");
	}

	@Test
	void testInsertDescendingTimeWithComparator() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i = num - 1; i >= 0; i--) {
			long start = System.currentTimeMillis();
			q.insert(i, "" + i);
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasLessThanLinearTimeBehaviour(result, 5, num, "InsertDescendingTime:"))
			throw new RuntimeException("Method insertElement is too slow, not O(log(n))!");
	}

	@Test
	void testRemoveMinTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i = 0; i < num + 10; i++) {
			q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.removeMin();
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasLessThanLinearTimeBehaviour(result, 5, num, "RemoveTime:"))
			throw new RuntimeException("Method removeMin is too slow, not O(1)!");
	}

	@Test
	void testRemoveElementAscendingTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		Locator[] locs = new Locator[num];
		for (int i = 0; i < num; i++) {
			locs[i] = q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.remove(locs[i]);
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasLessThanLinearTimeBehaviour(result, 5, num, "RemoveElementTime:"))
			throw new RuntimeException("Method removeElement is too slow, not O(1)!");
	}

	@Test
	void testRemoveElementDescendingTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		Locator[] locs = new Locator[num];
		for (int i = num - 1; i >= 0; i--) {
			locs[i] = q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.remove(locs[i]);
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasLessThanLinearTimeBehaviour(result, 5, num, "RemoveElementTime:"))
			throw new RuntimeException("Method removeElement is too slow, not O(1)!");
	}

	@Test
	void testMinKeyTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i = 0; i < num; i++) {
			q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.minKey();
			long stop = System.currentTimeMillis();
			q.removeMin();
			result[i] = stop - start;
		}
		if (!methodHasConstantTimeBehaviour(result, 5, num, "minKeyTime:"))
			throw new RuntimeException("Method minKey is too slow, not O(1)!");
	}

	@Test
	void testMinElementTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i = 0; i < num; i++) {
			q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.minElement();
			long stop = System.currentTimeMillis();
			q.removeMin();
			result[i] = stop - start;
		}
		if (!methodHasConstantTimeBehaviour(result, 5, num, "minElementTime:"))
			throw new RuntimeException("Method minElement is too slow, not O(1)!");
	}

	@Test
	void testKeyOfLocatorTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		Locator[] locs = new Locator[num];
		for (int i = 0; i < num; i++) {
			locs[i] = q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.key(locs[i]);
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasConstantTimeBehaviour(result, 5, num, "keyTime:"))
			throw new RuntimeException("Method key is too slow, not O(1)!");
	}

	@Test
	void testElementOfLocatorTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		Locator[] locs = new Locator[num];
		for (int i = 0; i < num; i++) {
			locs[i] = q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.element(locs[i]);
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasConstantTimeBehaviour(result, 5, num, "elementTime:"))
			throw new RuntimeException("Method key is too slow, not O(1)!");
	}

	@Test
	void testReplaceElementTime() {
		long[] result = new long[num];
		PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		Locator[] locs = new Locator[num];
		for (int i = 0; i < num; i++) {
			locs[i] = q.insert(i, "" + i);
		}
		for (int i = 0; i < num; i++) {
			long start = System.currentTimeMillis();
			q.replaceElement(locs[i], "test");
			long stop = System.currentTimeMillis();
			result[i] = stop - start;
		}
		if (!methodHasConstantTimeBehaviour(result, 5, num, "ReplaceElementTime:"))
			throw new RuntimeException("Method replaceElement is too slow, not O(1)!");
	}

	@Test
	void testSetComparatorTime() {
		int bottomUpHeapConstructionIsSlow = 0;
		int testRepetitions = 100;

		for (int t = 0; t < testRepetitions; t++) {
			PriorityQueue<Integer, String> q = new HeapBasedPriorityQueue<>(INT_COMPARATOR.reversed());
			// measure time for inserting all elements
			long start1 = System.currentTimeMillis();
			// worst case: elements in opposite order
			for (int i = 0; i < num; i++) {
				q.insert(i, "" + i);
			}
			long stop1 = System.currentTimeMillis();
			// measure time for reversing the comparator
			long start2 = System.currentTimeMillis();
			q.setComparator(INT_COMPARATOR);
			long stop2 = System.currentTimeMillis();

			// test if reversing is too slow (must be faster using bottom-up heap construction!)
			if ((stop2 - start2) >= (stop1 - start1)) {
				bottomUpHeapConstructionIsSlow++;
			}
		}
		 //System.out.println("Num of slows: " + bottomUpHeapConstructionIsSlow);
		if (bottomUpHeapConstructionIsSlow > testRepetitions * acceptablePercentageFaultsForBottomUpHeap) {
			throw new RuntimeException("Method setComparator is too slow, not O(n)!");
		}
	}

	// helper methods

	// Maximum absolute factor for linear term in a regression of a constant function
	private final double constantFunctionMaxLinearTerm = 0.1;
	
	// Maximum absolute factor for linear term in a regression of a log. function
	private final double logFunctionMaxLinearTerm = 1.0;
	
	// Maximum absolute factor for linear term in a regression of a log. function
	private final double acceptablePercentageFaultsForBottomUpHeap = 0.1;

	private boolean methodHasConstantTimeBehaviour(long[] arr, int start, int end, String headerString) {
		Doublepair p = computeRegression(arr, start, end);
		// System.out.println(headerString + " param1=" + p.param1 + " param2=" + p.param2);
		return Math.abs(p.param1) <= constantFunctionMaxLinearTerm;
		// this is true if the measurements suggest that the method has a constant time behaviour
	}

	private boolean methodHasLessThanLinearTimeBehaviour(long[] arr, int start, int end, String headerString) {
		Doublepair p = computeRegression(arr, start, end);
		// System.out.println(headerString + " param1=" + p.param1 + " param2=" + p.param2);
		return Math.abs(p.param1) <= logFunctionMaxLinearTerm;
		// this is true if the measurements suggest that the method has a constant time behaviour
	}

	private class Doublepair {
		double param1;
		@SuppressWarnings("unused")
		double param2;

		Doublepair(double param1, double param2) {
			this.param1 = param1;
			this.param2 = param2;
		}
	}

	// Computes a linear regression using the given data points
	private Doublepair computeRegression(long[] arr, int start, int end) {
		if (start < 0 || end < 0 || start >= arr.length || end > arr.length || end <= start)
			throw new RuntimeException(
					"arguments inconsistent start=" + start + " end=" + end + " arr.size=" + arr.length);
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum += arr[i];
		}
		double meanY = ((double) sum) / ((double) (end - start));
		double meanX = 0.5 * start + 0.5 * (end - 1);
		double sumxy = 0.0;
		double sumxx = 0.0;
		for (int i = start; i < end; i++) {
			sumxy += (i - meanX) * (arr[i] - meanY);
			sumxx += (i - meanX) * (i - meanX);
		}
		double param1 = sumxy / sumxx;
		double param2 = meanY - param1 * meanX;
		return new Doublepair(param1, param2);

	}

}
