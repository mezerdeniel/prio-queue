/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.queue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
import java.util.stream.Stream;
import ch.bfh.basics.Locator;

/**
 * This test class tests up and down-heap operations.
 * The inputs are arrays of integers [1,2,3,4] up to [1,2,3,4,5,6,7].
 * The tests consist of all permutations of those elements.
 * So this tests all heaps of height 3 containing integers 1-4 up to 1-7.
 */
//CHECKSTYLE:OFF MagicNumber
public class UpAndDownHeapTest {
	private static final Comparator<Integer> INT_COMPARATOR = Comparator.naturalOrder();

	@ParameterizedTest
	//check all permutations of 1...4
	@MethodSource("createAll4Tuples")
	void test4TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i : a) {
			pq.insert(i, i);
		}
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up/downheap?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up/downheap?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up/downheap?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...5
	@MethodSource("createAll5Tuples")
	void test5TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i : a) {
			pq.insert(i, i);
		}
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...6
	@MethodSource("createAll6Tuples")
	void test6TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i : a) {
			pq.insert(i, i);
		}
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(6, pq.removeMin(), "6th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...7
	@MethodSource("createAll7Tuples")
	void test7TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		for (int i : a) {
			pq.insert(i, i);
		}
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(6, pq.removeMin(), "6th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertEquals(7, pq.removeMin(), "7th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...4
	@MethodSource("createAll4Tuples")
	void testConstructorWith4TuplesOfInts(int[] a) {
		List<Integer> ints = new ArrayList<>(4);
		for (int elem: a) ints.add(elem);
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, ints, ints);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heapv?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...5
	@MethodSource("createAll5Tuples")
	void testConstructorWith5TuplesOfInts(int[] a) {
		List<Integer> ints = new ArrayList<>(5);
		for (int elem: a) ints.add(elem);
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, ints, ints);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heapv?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...6
	@MethodSource("createAll6Tuples")
	void testConstructorWith6TuplesOfInts(int[] a) {
		List<Integer> ints = new ArrayList<>(6);
		for (int elem: a) ints.add(elem);
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, ints, ints);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap?v");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap? or bottom-up heap-construction");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap? or bottom-up heap-construction");
		assertEquals(6, pq.removeMin(), "6th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap? or bottom-up heap-construction");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...7
	@MethodSource("createAll7Tuples")
	void testConstructorWith7TuplesOfInts(int[] a) {
		List<Integer> ints = new ArrayList<>(7);
		for (int elem: a) ints.add(elem);
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, ints, ints);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heapv?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(6, pq.removeMin(), "6th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(7, pq.removeMin(), "7th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertTrue(pq.isEmpty());
	}



	@ParameterizedTest
	//check all permutations of 1...4
	@MethodSource("createAll4Tuples")
	void testSetComparator4TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR.reversed());
		for (int i : a) {
			pq.insert(i, i);
		}
		pq.setComparator(INT_COMPARATOR);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...5
	@MethodSource("createAll5Tuples")
	void testSetComparator5TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR.reversed());
		for (int i : a) {
			pq.insert(i, i);
		}
		pq.setComparator(INT_COMPARATOR);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...6
	@MethodSource("createAll6Tuples")
	void testSetComparator6TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR.reversed());
		for (int i : a) {
			pq.insert(i, i);
		}
		pq.setComparator(INT_COMPARATOR);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(6, pq.removeMin(), "6th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertTrue(pq.isEmpty());
	}

	@ParameterizedTest
	//check all permutations of 1...7
	@MethodSource("createAll7Tuples")
	void testSetComparator7TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR.reversed());
		for (int i : a) {
			pq.insert(i, i);
		}
		pq.setComparator(INT_COMPARATOR);
		assertEquals(1, pq.removeMin(), "1st RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(2, pq.removeMin(), "2nd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(3, pq.removeMin(), "3rd RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(4, pq.removeMin(), "4th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(5, pq.removeMin(), "5th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(6, pq.removeMin(), "6th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertEquals(7, pq.removeMin(), "7th RemoveMin gives wrong result for input "
				+ Arrays.toString(a) + " Problem with up-/down-heap or bottom-up heap-construction?");
		assertTrue(pq.isEmpty());
	}


	@ParameterizedTest
	//check all permutations of 1...4
	@MethodSource("createAll4Tuples")
	void testRemove4TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		List<Locator> locs = new LinkedList<>();
		for (int i : a) {
			locs.add(pq.insert(i, i));
		}
		int index = 0;
		for (Locator loc : locs) {
			assertEquals(a[index], pq.remove(loc));
			index++;
		}
	}


	@ParameterizedTest
	//check all permutations of 1...5
	@MethodSource("createAll5Tuples")
	void testRemove5TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		List<Locator> locs = new LinkedList<>();
		for (int i : a) {
			locs.add(pq.insert(i, i));
		}
		int index = 0;
		for (Locator loc : locs) {
			assertEquals(a[index], pq.remove(loc));
			index++;
		}
	}



	@ParameterizedTest
	//check all permutations of 1...6
	@MethodSource("createAll6Tuples")
	void testRemove6TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		List<Locator> locs = new LinkedList<>();
		for (int i : a) {
			locs.add(pq.insert(i, i));
		}
		int index = 0;
		for (Locator loc : locs) {
			assertEquals(a[index], pq.remove(loc));
			index++;
		}
	}



	@ParameterizedTest
	//check all permutations of 1...7
	@MethodSource("createAll7Tuples")
	void testRemove7TuplesOfInts(int[] a) {
		PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
		List<Locator> locs = new LinkedList<>();
		for (int i : a) {
			locs.add(pq.insert(i, i));
		}
		int index = 0;
		for (Locator loc : locs) {
			assertEquals(a[index], pq.remove(loc));
			index++;
		}
	}



	// helper methods


	private static Stream<int[]> createAll4Tuples() { return createAllNTuples(4); }

	private static Stream<int[]> createAll5Tuples() {
		return createAllNTuples(5);
	}

	private static Stream<int[]> createAll6Tuples() {
		return createAllNTuples(6);
	}

	private static Stream<int[]> createAll7Tuples() {
		return createAllNTuples(7);
	}

	private static Stream<int[]> createAllNTuples(int n) {
		int[] numbers = new int[n];
		Arrays.setAll(numbers, i -> i + 1);
		return Stream.iterate(numbers, Objects::nonNull, UpAndDownHeapTest::computeNextPermutation);
	}

	private static int[] computeNextPermutation(int[] a ) {
		int first = getFirst(a);
		if (first == -1) return null; // no greater permutation
		int swap = a.length - 1;
		while (a[first] >= a[swap])
			swap--;
		swap(a, first++, swap);
		swap = a.length - 1;
		while (first < swap)
			swap(a, first++, swap--);
		return a;
	}

	private static int getFirst(int[] a) {
		for (int i = a.length - 2; i >= 0; --i)
			if (a[i] <= a[i + 1])
				return i;
		return -1;
	}
	private static void swap(int[] a, int i, int j ) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}




}