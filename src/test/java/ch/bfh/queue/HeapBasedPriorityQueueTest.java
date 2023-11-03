/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.queue;

import ch.bfh.basics.Locator;
import ch.bfh.exceptions.EmptyPriorityQueueException;
import ch.bfh.exceptions.InvalidComparatorException;
import ch.bfh.exceptions.InvalidLocatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests various methods of the priority queue.
 */
//CHECKSTYLE:OFF MagicNumber
public class HeapBasedPriorityQueueTest {
    private static final Comparator<Integer> INT_COMPARATOR = Comparator.naturalOrder();
    private static final Comparator<String> STRING_COMPARATOR = Comparator.naturalOrder();

    @Test
    void testEmptyHeapSizeWithComparator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertEquals(0, pq.size());
    }

    @Test
    void testEmptyHeapWithComparator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertTrue(pq.isEmpty());
    }

    @Test
    void testOneElementIsEmptyAfterInsert() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq.insert(1, "1");
        assertFalse(pq.isEmpty());
    }

    @Test
    void testOneElementSizeAfterInsert() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq.insert(1, "1");
        assertEquals(1, pq.size());
    }

    @ParameterizedTest
    @MethodSource("createInts")
    void testOneElementMinElementAfterInsert(Integer i) {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        String element = i.toString();
        pq.insert(i, element);
        assertEquals(element, pq.minElement());
    }

    @ParameterizedTest
    @EmptySource
    @MethodSource("createStrings")
    void testOneElementMinElementAfterInsert(String i) {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert(i, i);
        assertEquals(i, pq.minElement());
    }

    @ParameterizedTest
    @MethodSource("createInts")
    void testOneElementMinKeyAfterInsert(Integer i) {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        String element = i.toString();
        pq.insert(i, element);
        assertEquals(i, pq.minKey());
    }

    @ParameterizedTest
    @EmptySource
    @MethodSource("createStrings")
    void testOneElementMinKeyAfterInsert(String i) {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert(i, i);
        assertEquals(i, pq.minKey());
    }

    @ParameterizedTest
    @MethodSource("createInts")
    void testOneElementRemoveMinAfterInsert(Integer i) {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        String element = i.toString();
        pq.insert(i, element);
        assertEquals(element, pq.removeMin());
    }

    @ParameterizedTest
    @EmptySource
    @MethodSource("createStrings")
    void testOneElementRemoveMinAfterInsert(String i) {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert(i, i);
        assertEquals(i, pq.removeMin());
    }

    @Test
    void testOneElementSizeAfterInsertAndRemoveMin() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        String element = "1";
        pq.insert(1, element);
        pq.removeMin();
        assertAll(() -> assertEquals(0, pq.size()), () -> assertTrue(pq.isEmpty()));
    }

    @Test
    void testTwoElementsSizeAfterTwoInserts() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        assertAll(() -> assertEquals(2, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsSizeAfterTwoInserts_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        assertAll(() -> assertEquals(2, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsMinElementAfterTwoInserts() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        assertEquals(element1, pq.minElement());
    }

    @Test
    void testTwoElementsMinElementAfterTwoInserts_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        assertEquals(element1, pq.minElement());
    }

    @Test
    void testTwoElementsMinKeyAfterTwoInserts() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        assertEquals(i1, pq.minKey());
    }

    @Test
    void testTwoElementsMinKeyAfterTwoInserts_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        assertEquals(i1, pq.minKey());
    }

    @Test
    void testTwoElementsSizeAfterMinElement() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.minElement();
        assertAll(() -> assertEquals(2, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsSizeAfterMinElement_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.minElement();
        assertAll(() -> assertEquals(2, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsRemoveMinAfterTwoInserts() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testTwoElementsRemoveMinAfterTwoInserts_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testTwoElementsSizeAfterRemoveMind() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.removeMin();
        assertAll(() -> assertEquals(1, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsSizeAfterRemoveMind_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.removeMin();
        assertAll(() -> assertEquals(1, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsMinElementAfterRemoveMin() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.removeMin();
        assertEquals(element2, pq.minElement());
    }

    @Test
    void testTwoElementsMinElementAfterRemoveMin_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.removeMin();
        assertEquals(element2, pq.minElement());
    }

    @Test
    void testTwoElementsMinKeyAfterRemoveMin() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.removeMin();
        assertEquals(i2, pq.minKey());
    }

    @Test
    void testTwoElementsMinKeyAfterRemoveMin_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.removeMin();
        assertEquals(i2, pq.minKey());
    }

    @Test
    void testTwoElementsSizeAfterRemoveMinAndMinElement() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.removeMin();
        pq.minElement();
        assertAll(() -> assertEquals(1, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsSizeAfterRemoveMinAndMinElement_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.removeMin();
        pq.minElement();
        assertAll(() -> assertEquals(1, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testTwoElementsRemoveTwice() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.removeMin();
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testTwoElementsRemoveTwice_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.removeMin();
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testTwoElementsSizeAfterTwoRemoveMin() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.removeMin();
        pq.removeMin();
        assertAll(() -> assertEquals(0, pq.size()), () -> assertTrue(pq.isEmpty()));
    }

    @Test
    void testTwoElementsSizeAfterTwoRemoveMin_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.removeMin();
        pq.removeMin();
        assertAll(() -> assertEquals(0, pq.size()), () -> assertTrue(pq.isEmpty()));
    }

    @Test
    void testSeveralElementsSizeAfterInsert() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int size = 10000;
        Random r = new Random(10);
        for (int element = 1; element <= size; element++) {
            pq.insert(r.nextInt(), String.valueOf(element));
        }
        assertAll(() -> assertEquals(size, pq.size()), () -> assertFalse(pq.isEmpty()));
    }

    @Test
    void testSeveralElementsInsertAndRemoveAll() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int size = 10000;
        Random r = new Random(20);
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int element = r.nextInt();
            list.add(element);
            pq.insert(element, String.valueOf(element));
        }
        list.sort(INT_COMPARATOR);
        for (int i = 0; i < size; i++) {
            assertEquals(list.get(i), pq.minKey());
            assertEquals(String.valueOf(list.get(i)), pq.removeMin());
        }
    }

    @Test
    void testSeveralElementsSizeInsertAndRemoveAll() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int size = 10000;
        Random r = new Random(3);
        for (int i = 0; i < size; i++) {
            int element = r.nextInt();
            pq.insert(element, String.valueOf(element));
        }
        for (int i = 0; i < size; i++) {
            pq.removeMin();
        }
        assertAll(() -> assertEquals(0, pq.size()), () -> assertTrue(pq.isEmpty()));
    }

    @Test
    void testExceptionMinElement() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertThrows(EmptyPriorityQueueException.class, pq::minElement);
    }

    @Test
    void testExceptionMinKey() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertThrows(EmptyPriorityQueueException.class, pq::minKey);
    }

    @Test
    void testExceptionRemoveMin() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertThrows(EmptyPriorityQueueException.class, pq::removeMin);
    }

    @Test
    void continueAfterExceptions() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertThrows(EmptyPriorityQueueException.class, pq::minElement);
        assertThrows(EmptyPriorityQueueException.class, pq::removeMin);
        Integer i = 100;
        String e = "some string";
        pq.insert(i, e);
        assertEquals(e, pq.minElement());
        assertEquals(i, pq.minKey());
        assertEquals(e, pq.removeMin());
        assertThrows(EmptyPriorityQueueException.class, pq::minElement);
        assertThrows(EmptyPriorityQueueException.class, pq::removeMin);
    }

    @Test
    void testComparableClass() {
        // Helper class implementing Comparable interface
        class ComparableClass implements Comparable<ComparableClass> {
            @Override
            public int compareTo(ComparableClass o) {

                return 0; // Implementation without ordering, just for testing below.
            }
        }
        Comparator<ComparableClass> c = Comparator.naturalOrder();

        PriorityQueue<ComparableClass, String> pq = new HeapBasedPriorityQueue<>(c);
        pq.insert(new ComparableClass(), "A");
        pq.insert(new ComparableClass(), "B");
        pq.insert(new ComparableClass(), "C");
        assertEquals(pq.size(), 3);
    }

    @Test
    void testTwoElementsChangeComparator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        Comparator<Integer> c = Comparator.naturalOrder();
        pq.setComparator(c);
        assertEquals(element1, pq.removeMin());
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testTwoElementsChangeComparatorReverse() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        Comparator<Integer> c = Comparator.naturalOrder();
        pq.setComparator(c.reversed());
        assertEquals(element2, pq.removeMin());
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testTwoElementsDefaultComparator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR.reversed());
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i2, element2);
        pq.insert(i1, element1);
        pq.setComparator(INT_COMPARATOR);
        assertEquals(element1, pq.removeMin());
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testReturnOfSetComparator() {
        Comparator<Integer> tmpComp = INT_COMPARATOR.reversed();
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(tmpComp);
        assertEquals(tmpComp, pq.setComparator(INT_COMPARATOR), "the return value of setComparator must be the old comparator");
    }

    @Test
    void testComparatorAfterException() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertThrows(NullPointerException.class, () -> pq.setComparator(null));
        assertEquals(INT_COMPARATOR, pq.setComparator(INT_COMPARATOR.reversed()), "the return value of setComparator must be the old comparator being replaced");
    }

    @Test
    void testReplaceElementWithOneElement1() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        String element2 = "2";
        Locator locator = pq.insert(i1, element1);
        assertEquals(element1, pq.replaceElement(locator, element2), "the return value of replaceElement must be the old element being replaced");
    }

    @Test
    void testReplaceElementWithOneElement2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        String element2 = "2";
        Locator locator = pq.insert(i1, element1);
        pq.replaceElement(locator, element2);
        assertEquals(element2, pq.removeMin());
    }


    @Test
    void testReplaceElementWithTwoElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        String element3 = "0";
        Locator locator = pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.replaceElement(locator, element3);
        assertEquals(element3, pq.removeMin());
    }

    @Test
    void testReplaceElementsWithTwoElements_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        pq.insert(i1, element1);
        Locator locator = pq.insert(i2, element2);
        pq.replaceElement(locator, element3);
        pq.removeMin();
        assertEquals(element3, pq.removeMin());
    }

    @Test
    void testReplaceElementsWithTwoElements_3() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        pq.insert(i1, element1);
        Locator locator = pq.insert(i2, element2);
        assertEquals(element2, pq.replaceElement(locator, element3), "the return value of replaceElement must be the old element being replaced");
    }

    @Test
    void testReplaceElementWithThreeElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        String element4 = "4";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        Locator locator = pq.insert(i3, element3);
        pq.replaceElement(locator, element4);
        pq.removeMin();
        pq.removeMin();
        assertEquals(element4, pq.removeMin());
    }

    @Test
    void testReplaceElementWithThreeElements_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        String element4 = "4";
        pq.insert(i1, element1);
        Locator locator = pq.insert(i2, element2);
        pq.insert(i3, element3);
        pq.replaceElement(locator, element4);
        pq.removeMin();
        assertEquals(element4, pq.removeMin());
    }

    @Test
    void testReplaceElementWithThreeElements_3() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        String element4 = "4";
        Locator locator = pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.insert(i3, element3);
        pq.replaceElement(locator, element4);
        assertEquals(element4, pq.removeMin());
    }

    @Test
    void testReplaceKeyWithOneElement() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        pq.replaceKey(locator, i2);
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testReplaceKeyWithTwoElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        Locator locator = pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.replaceKey(locator, i3);
        pq.removeMin();
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testReplaceKeyWithTwoElements_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 0;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        Locator locator = pq.insert(i2, element2);
        pq.replaceKey(locator, i3);
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testReplaceKeyWithThreeElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 4;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        Locator locator = pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.insert(i3, element3);
        pq.replaceKey(locator, i4);
        pq.removeMin();
        pq.removeMin();
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testReplaceKeyWithThreeElements_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 4;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        pq.insert(i1, element1);
        Locator locator = pq.insert(i2, element2);
        pq.insert(i3, element3);
        pq.replaceKey(locator, i4);
        pq.removeMin();
        assertEquals(element3, pq.removeMin());
    }

    @Test
    void testReplaceKeyWithThreeElements_3() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 0;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        Locator locator = pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.insert(i3, element3);
        pq.replaceKey(locator, i4);
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testReplaceKeyThreeTimes() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 3;
        Integer i2 = 4;
        Integer i3 = 5;
        Integer i4 = 2;
        Integer i5 = 1;
        Integer i6 = 0;
        String element1 = "A";
        String element2 = "B";
        String element3 = "C";
        pq.insert(i1, element1);
        Locator l2 = pq.insert(i2, element2);
        Locator l3 = pq.insert(i3, element3);
        pq.replaceKey(l3, i4);
        pq.replaceKey(l2, i5);
        pq.replaceKey(l3, i6);
        assertEquals(element3, pq.removeMin());
        assertEquals(element2, pq.removeMin());
        assertEquals(element1, pq.removeMin());
        // If you do not agree with this result, do the example with paper and pencil!
    }

    @Test
    void testReplaceKeyTwice() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 3;
        Integer i2 = 4;
        Integer i3 = 2;
        Integer i4 = 1;
        String element1 = "A";
        String element2 = "B";
        Locator l1 = pq.insert(i1, element1);
        Locator l2 = pq.insert(i2, element2);
        pq.replaceKey(l2, i3);
        pq.replaceKey(l1, i4);
        assertEquals(element1, pq.removeMin());
        assertEquals(element2, pq.removeMin());
        // If you do not agree with this result, do the example with paper and pencil!
    }

    @Test
    void testRemoveWithOneElement() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        pq.remove(locator);
        assertTrue(pq.isEmpty());
    }

    @Test
    void testRemoveWithTwoElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        Locator locator = pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.remove(locator);
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testRemoveWithTwoElements_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        String element1 = "1";
        String element2 = "2";
        pq.insert(i1, element1);
        Locator locator = pq.insert(i2, element2);
        pq.remove(locator);
        assertEquals(element1, pq.removeMin());
    }

    @Test
    void testRemoveWithThreeElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        Locator locator = pq.insert(i3, element3);
        assertEquals(element3, pq.remove(locator), "the return value of remove must be the element being removed");
    }

    @Test
    void testRemoveWithThreeElements_1() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        pq.insert(i1, element1);
        pq.insert(i2, element2);
        Locator locator = pq.insert(i3, element3);
        pq.remove(locator);
        pq.removeMin();
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testRemoveWithThreeElements_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        pq.insert(i1, element1);
        Locator locator = pq.insert(i2, element2);
        pq.insert(i3, element3);
        pq.remove(locator);
        pq.removeMin();
        assertEquals(element3, pq.removeMin());
    }

    @Test
    void testRemoveWithThreeElements_3() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        Locator locator = pq.insert(i1, element1);
        pq.insert(i2, element2);
        pq.insert(i3, element3);
        pq.remove(locator);
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testKeyWithOneElement() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        assertEquals(i1, pq.key(locator));
    }

    @Test
    void testElementWithOneElement() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        assertEquals(element1, pq.element(locator));
    }

    @Test
    void testElementWithRemovedLocator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        pq.removeMin();
        assertThrows(InvalidLocatorException.class, () -> pq.element(locator), "element must throw an exception if the locator is invalid");
    }

    @Test
    void testElementWithRemovedLocator_1() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        pq.remove(locator);
        assertThrows(InvalidLocatorException.class, () -> pq.element(locator), "element must throw an exception if the locator is invalid");
    }

    @Test
    void testKeyWithRemovedLocator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        pq.removeMin();
        assertThrows(InvalidLocatorException.class, () -> pq.key(locator), "key must throw an exception if the locator is invalid");
    }

    @Test
    void testKeyWithRemovedLocator_2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq.insert(i1, element1);
        pq.remove(locator);
        assertThrows(InvalidLocatorException.class, () -> pq.key(locator), "key must throw an exception if the locator is invalid");
    }

    @Test
    void testKeyWithLocatorFromOtherPQ() {
        PriorityQueue<Integer, String> pq1 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        PriorityQueue<Integer, String> pq2 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq1.insert(i1, element1);
        pq2.insert(0, "0");
        assertThrows(InvalidLocatorException.class, () -> pq2.key(locator), "key must throw an exception if the locator is invalid in the current pq");
    }

    @Test
    void testElementWithLocatorFromOtherPQ() {
        PriorityQueue<Integer, String> pq1 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        PriorityQueue<Integer, String> pq2 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq1.insert(i1, element1);
        pq2.insert(0, "0");
        assertThrows(InvalidLocatorException.class, () -> pq2.element(locator), "element must throw an exception if the locator is invalid in the current pq");
    }

    @Test
    void testRemoveWithLocatorFromOtherPQ() {
        PriorityQueue<Integer, String> pq1 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        PriorityQueue<Integer, String> pq2 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq1.insert(i1, element1);
        pq2.insert(0, "0");
        assertThrows(InvalidLocatorException.class, () -> pq2.remove(locator), "remove must throw an exception if the locator is invalid in the current pq");
    }

    @Test
    void testReplaceWithLocatorFromOtherPQ() {
        PriorityQueue<Integer, String> pq1 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        PriorityQueue<Integer, String> pq2 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        Locator locator = pq1.insert(i1, element1);
        pq2.insert(0, "0");
        assertThrows(InvalidLocatorException.class, () -> pq2.replaceElement(locator, "3"), "replaceElement must throw an exception if the locator is invalid in the current pq");
    }

    static class FakeLocator implements Locator {
    }

    @Test
    void testRemoveWithFakeLocator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        pq.insert(i1, element1);
        Locator locator = new FakeLocator();
        assertThrows(InvalidLocatorException.class, () -> pq.remove(locator), "remove must throw an exception if the locator is invalid");
    }

    @Test
    void testElementWithFakeLocator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        pq.insert(i1, element1);
        Locator locator = new FakeLocator();
        assertThrows(InvalidLocatorException.class, () -> pq.element(locator), "element must throw an exception if the locator is invalid");
    }

    @Test
    void testKeyWithFakeLocator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        pq.insert(i1, element1);
        Locator locator = new FakeLocator();
        assertThrows(InvalidLocatorException.class, () -> pq.key(locator), "key must throw an exception if the locator is invalid");
    }

    @Test
    void testReplaceKeyWithFakeLocator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        pq.insert(i1, element1);
        Locator locator = new FakeLocator();
        assertThrows(InvalidLocatorException.class, () -> pq.replaceKey(locator, 2), "replaceKey must throw an exception if the locator is invalid");
    }

    @Test
    void testReplaceElementWithFakeLocator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer i1 = 1;
        String element1 = "1";
        pq.insert(i1, element1);
        Locator locator = new FakeLocator();
        assertThrows(InvalidLocatorException.class, () -> pq.replaceElement(locator, "2"), "replaceElement must throw an exception if the locator is invalid");
    }


    @Test
    void test_null() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        assertThrows(NullPointerException.class, () -> pq.insert(null, null), "insert must throw an exception if the key is null and the comparator does not permit null");
    }

    @Test
    void test_nullComparator() {
        assertThrows(NullPointerException.class, () -> new HeapBasedPriorityQueue<>(null), "constructor must throw an exception if the comparator is null");
    }

    @Test
    void test_nullComparatorWithKeysAndElements() {
        List<String> elements = List.of("X", "B", "K");
        List<Integer> keys = List.of(3, 1, 2);
        assertThrows(NullPointerException.class, () -> new HeapBasedPriorityQueue<>(null, keys, elements), "constructor must throw an exception if the comparator is null");
    }

    @Test
    void testConstructorWithLists() {
        List<String> elements = List.of("X", "B", "K");
        List<Integer> keys = List.of(3, 1, 2);
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements);
        assertEquals(3, pq.size());
    }

    @Test
    void testConstructorWithLists2() {
        List<String> elements = List.of("X", "B", "K");
        List<Integer> keys = List.of(3, 1, 2);
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements);
        assertAll(() -> assertEquals(elements.get(1), pq.minElement()), () -> assertEquals(keys.get(1), pq.minKey()));
    }

    @Test
    void testConstructorWithLists3() {
        List<String> elements = List.of("X", "B", "K");
        List<Integer> keys = List.of(3, 1, 2);
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements);
        pq.removeMin();
        assertAll(() -> assertEquals(elements.get(2), pq.minElement()), () -> assertEquals(keys.get(2), pq.minKey()));
    }

    @Test
    void testConstructorWithLists4() {
        List<String> elements = List.of("X", "B", "K");
        List<Integer> keys = List.of(3, 1, 2);
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements);
        pq.removeMin();
        pq.removeMin();
        assertAll(() -> assertEquals(elements.get(0), pq.minElement()), () -> assertEquals(keys.get(0), pq.minKey()));
    }

    @Test
    void testConstructorWithLists5() {
        List<String> elements = List.of("K", "B", "X");
        List<Integer> keys = List.of(2, 1, 3);
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements);
        assertAll(() -> assertEquals(elements.get(1), pq.minElement()), () -> assertEquals(keys.get(1), pq.minKey()));
    }

    @Test
    void testConstructorWithEmptyLists() {
        List<String> elements = List.of();
        List<Integer> keys = List.of();
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements);
        assertTrue(pq.isEmpty());
    }

    @Test
    void testConstructorWithWitMoreElementsThanKeys() {
        List<String> elements = List.of("1", "2");
        List<Integer> keys = List.of();
        assertThrows(RuntimeException.class, () -> new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements));
    }

    @Test
    void testConstructorWithWitMoreKeysThanElements() {
        List<String> elements = List.of();
        List<Integer> keys = List.of(3, 2, 1);
        assertThrows(RuntimeException.class, () -> new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, elements));
    }

    @Test
    void testSetComparatorWithNUll() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq.insert(1, "A");
        pq.insert(2, "B");
        assertThrows(NullPointerException.class, () -> pq.setComparator(null));
    }

    @Test
    void testNullElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq.insert(3, null); // null as element is allowed
        pq.insert(1, null);
        pq.insert(2, null);
        assertNull(pq.removeMin());
        assertNull(pq.removeMin());
        assertNull(pq.removeMin());
    }

    @Test
    void testLocatorAfterMove1() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer key = 1;
        String element = "element";
        Locator locator = pq.insert(key, element);
        pq.insert(0, "test");
        assertEquals(key, pq.key(locator), "Locator must still point to the correct tuple");
        assertEquals(element, pq.element(locator), "Locator must still point to the correct tuple");
    }

    @Test
    void testLocatorAfterMove2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer key = 1;
        String element = "element";
        Locator locator = pq.insert(key, element);
        pq.insert(2, "test");
        assertEquals(key, pq.key(locator), "Locator must still point to the correct tuple");
        assertEquals(element, pq.element(locator), "Locator must still point to the correct tuple");
    }

    @Test
    void testLocatorAfterReplaceKey1() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer key = 1;
        String element = "element";
        Locator locator = pq.insert(key, element);
        Integer newKey = 0;
        pq.replaceKey(locator, newKey);
        pq.insert(2, "test");
        assertEquals(newKey, pq.key(locator), "change of key must be visible in locator");
        assertEquals(element, pq.element(locator), "Locator must still point to the correct tuple");
    }

    @Test
    void testLocatorAfterReplaceKey2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer key = 1;
        String element = "element";
        Locator locator = pq.insert(key, element);
        Integer newKey = 2;
        pq.replaceKey(locator, newKey);
        pq.insert(0, "test");
        assertEquals(newKey, pq.key(locator), "change of key must be visible in locator");
        assertEquals(element, pq.element(locator), "Locator must still point to the correct tuple");
    }

    @Test
    void testLocatorAfterReplaceElement1() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer key = 1;
        String element = "element";
        Locator locator = pq.insert(key, element);
        String newElement = "new element";
        pq.replaceElement(locator, newElement);
        pq.insert(2, "test");
        assertEquals(key, pq.key(locator), "Locator must still point to the correct tuple");
        assertEquals(newElement, pq.element(locator), "change of element must be visible in locator");
    }

    @Test
    void testLocatorAfterReplaceElement2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer key = 1;
        String element = "element";
        Locator locator = pq.insert(key, element);
        String newElement = "new element";
        pq.replaceElement(locator, newElement);
        pq.insert(0, "test");
        assertEquals(key, pq.key(locator), "Locator must still point to the correct tuple");
        assertEquals(newElement, pq.element(locator), "change of element must be visible in locator");
    }

    @Test
    void testSameKeyWithDifferentElement() {
        int value = 100;
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Integer key = 1;
        String element = "element";
        Random r = new Random(value);
        List<String> strings = new LinkedList<>();
        for (int i = 0; i < value; i++) {
            String s = element + r.nextInt();
            strings.add(s);
            pq.insert(key, s);
        }
        while (!pq.isEmpty()) {
            String s = pq.removeMin();
            assertTrue(strings.remove(s));
        }
        assertTrue(strings.isEmpty());
    }

    @Test
    void testWithCombinedComparator() {
        class Triple {
            final String first;
            final String second;
            final String third;

            Triple(String first, String second, String third) {
                this.first = first;
                this.second = second;
                this.third = third;
            }

            String first() {
                return first;
            }

            String second() {
                return second;
            }

            String third() {
                return third;
            }
        }

        PriorityQueue<Triple, String> pq = new HeapBasedPriorityQueue<>(
                Comparator.comparing(Triple::first).thenComparing(Triple::second).thenComparing(Triple::third));
        pq.insert(new Triple("1", "2", "3"), "123");
        pq.insert(new Triple("1", "3", "4"), "134");
        pq.insert(new Triple("1", "2", "2"), "122");

        assertEquals("122", pq.removeMin());
        assertEquals("123", pq.removeMin());
        assertEquals("134", pq.removeMin());
    }

    @Test
    void testChangeComparator() {
        PriorityQueue<String, String> pq1 = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        PriorityQueue<String, String> pq2 = new HeapBasedPriorityQueue<>(STRING_COMPARATOR.reversed());
        createStrings().forEach(s -> pq1.insert(s, s));
        createStrings().forEach(s -> pq2.insert(s, s));
        pq1.insert("", "");
        pq2.insert("", "");
        pq2.setComparator(STRING_COMPARATOR);
        assertEquals(pq1.size(), pq2.size());
        while (!pq1.isEmpty() && !pq2.isEmpty()) {
            assertEquals(pq1.minKey(), pq2.minKey());
            assertEquals(pq1.removeMin(), pq2.removeMin());
        }
    }

    @Test
    void testNullComparatorMustNotChangePQ() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key1 = 1;
        int key2 = 2;
        int key3 = 0;
        String element1 = "1";
        String element2 = "2";
        String element3 = "0";
        pq.insert(key1, element1);
        pq.insert(key2, element2);
        pq.insert(key3, element3);
        assertThrows(NullPointerException.class, () -> pq.setComparator(null));
        // This exception must not change the priority queue
        assertEquals(key3, pq.minKey());
        assertEquals(element3, pq.removeMin());
        assertEquals(key1, pq.minKey());
        assertEquals(element1, pq.removeMin());
        assertEquals(key2, pq.minKey());
        assertEquals(element2, pq.removeMin());
    }

    @Test
    void testInsertRemoveInsert() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        pq.insert("2", "2");
        pq.insert("0", "0");
        pq.removeMin();
        pq.removeMin();
        pq.removeMin();
        pq.insert("1", "1");
        pq.insert("2", "2");
        pq.insert("0", "0");
        assertEquals(3, pq.size());
    }

    @Test
    void testKeyWithInvalidLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        Locator invalid = new Locator() {
        };
        assertThrows(InvalidLocatorException.class, () -> pq.key(invalid), "key must throw an exception if locator is invalid");
    }

    @Test
    void testElementWithInvalidLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        Locator invalid = new Locator() {
        };
        assertThrows(InvalidLocatorException.class, () -> pq.element(invalid), "element must throw an exception if locator is invalid");
    }

    @Test
    void testRemoveElementWithInvalidLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        Locator invalid = new Locator() {
        };
        assertThrows(InvalidLocatorException.class, () -> pq.remove(invalid), "remove must throw an exception if locator is invalid");
    }

    @Test
    void testReplaceElementWithInvalidLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        Locator invalid = new Locator() {
        };
        assertThrows(InvalidLocatorException.class, () -> pq.replaceElement(invalid, "2"), "replaceElement must throw an exception if locator is invalid");
    }

    @Test
    void testReplaceKeyWithInvalidLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        Locator invalid = new Locator() {
        };
        assertThrows(InvalidLocatorException.class, () -> pq.replaceKey(invalid, "2"), "replaceKey must throw an exception if locator is invalid");
    }

    @Test
    void testReplaceKeyWithNullLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        assertThrows(InvalidLocatorException.class, () -> pq.replaceKey(null, "2"), "replaceKey must throw an exception if locator is null");
    }

    @Test
    void testReplaceKeyWithNullKey() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator l = pq.insert("1", "1");
        assertThrows(NullPointerException.class, () -> pq.replaceKey(l, null), "replaceKey must throw an exception if key is null and comparator does not allow null");
    }

    @Test
    void testReplaceElementWithNullLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        assertThrows(InvalidLocatorException.class, () -> pq.replaceElement(null, "2"), "replaceElement must throw an exception if locator is null");
    }

    @Test
    void testElementWithNullLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        assertThrows(InvalidLocatorException.class, () -> pq.element(null), "element must throw an exception if locator is null");
    }

    @Test
    void testKeyWithNullLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        assertThrows(InvalidLocatorException.class, () -> pq.key(null), "key must throw an exception if locator is null");
    }

    @Test
    void testRemoveElementWithNullLocator() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("1", "1");
        assertThrows(InvalidLocatorException.class, () -> pq.remove(null), "remove must throw an exception if locator is null");
    }

    @Test
    void testLocatorAfterSettingNewComparator() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key = 1;
        String string = "s1";
        Locator locator = pq.insert(key, string);
        pq.setComparator(INT_COMPARATOR.reversed());
        pq.remove(locator);
        assertTrue(pq.isEmpty());
    }

    @Test
    void testLocatorAfterSettingNewComparator2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key1 = 1;
        int key2 = 2;
        String string1 = "s1";
        String string2 = "s2";
        Locator locator1 = pq.insert(key1, string1);
        pq.insert(key2, string2);
        pq.setComparator(INT_COMPARATOR.reversed());
        pq.remove(locator1);
        assertEquals(1, pq.size());
    }

    @Test
    void testLocatorAfterSettingNewComparator3() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key1 = 1;
        int key2 = 2;
        String string1 = "s1";
        String string2 = "s2";
        Locator locator1 = pq.insert(key1, string1);
        Locator locator2 = pq.insert(key2, string2);
        pq.setComparator(INT_COMPARATOR.reversed());
        assertEquals(pq.element(locator1), string1, "Locator must still point to the correct tuple with new comparator");
        assertEquals(pq.key(locator1), key1, "Locator must still point to the correct tuple with new comparator");
        assertEquals(pq.element(locator2), string2, "Locator must still point to the correct tuple with new comparator");
        assertEquals(pq.key(locator2), key2, "Locator must still point to the correct tuple with new comparator");
    }

    @Test
    void testSameKeys1() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key = 1;
        String element = "elem";
        Locator locator = pq.insert(key, element);
        pq.insert(key, "other");
        pq.insert(key, "else");
        assertEquals(element, pq.element(locator), "Locator must point to the correct tuple");
        assertEquals(key, pq.key(locator), "Locator must point to the correct tuple");
    }

    @Test
    void testSameKeys2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key = 1;
        String element = "elem";
        pq.insert(key, "other");
        Locator locator = pq.insert(key, element);
        pq.insert(key, "else");
        assertEquals(element, pq.element(locator), "Locator must point to the correct tuple");
        assertEquals(key, pq.key(locator), "Locator must point to the correct tuple");
    }

    @Test
    void testSameKeys3() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key = 1;
        String element = "elem";
        pq.insert(key, "other");
        pq.insert(key, "else");
        Locator locator = pq.insert(key, element);
        assertEquals(element, pq.element(locator), "Locator must point to the correct tuple");
        assertEquals(key, pq.key(locator), "Locator must point to the correct tuple");
    }

    @Test
    void testSameKeys4() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key = 1;
        String element = "elem";
        Locator locator = pq.insert(key, element);
        pq.insert(key, "other");
        pq.insert(key, "else");
        String result = pq.remove(locator);
        assertEquals(element, result, "Locator must point to the correct tuple");
    }

    @Test
    void testSameKeys5() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key = 1;
        String element = "elem";
        pq.insert(key, "other");
        Locator locator = pq.insert(key, element);
        pq.insert(key, "else");
        String result = pq.remove(locator);
        assertEquals(element, result, "Locator must point to the correct tuple");
    }

    @Test
    void testSameKeys6() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        int key = 1;
        String element = "elem";
        pq.insert(key, "other");
        pq.insert(key, "else");
        Locator locator = pq.insert(key, element);
        String result = pq.remove(locator);
        assertEquals(element, result, "Locator must point to the correct tuple");
    }

    @Test
    void testWithComparatorAllowingNullAsKey() {
        // This is a comparator for String objects that sorts 'null' before any other String.
        Comparator<String> nullStringComp = Comparator.nullsFirst(STRING_COMPARATOR);
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(nullStringComp);
        pq.insert(null, "1");
        pq.insert("1", "2");
        pq.insert(null, "0");
        assertEquals(3, pq.size(), "If the comparator allows null value, then null can be used also as key");
    }

    @Test
    void testWithComparatorNotAllowingNullAsKey() {
        PriorityQueue<String, String> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        assertThrows(NullPointerException.class, () -> pq.insert(null, "1"),
                "If the comparator does not allow null values, then an exception must be thrown");
    }

    @Test
    void testConstructorWithListsAndComparatorAllowingNullKeys() {
        // This is a comparator for String objects that sorts 'null' before any other String.
        Comparator<String> nullStringComp = Comparator.nullsFirst(STRING_COMPARATOR);
        List<String> keys = new ArrayList<>();
        keys.add("1");
        keys.add(null);
        keys.add("2");
        List<Integer> elements = List.of(3, 1, 2);
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(nullStringComp, keys, elements);
        assertEquals(3, pq.size(), "If the comparator allows null value, then null can be used also as key");
    }

    @Test
    void testConstructorWithListsAndComparatorNotAllowingNullKeys() {
        List<String> keys = new ArrayList<>();
        keys.add("1");
        keys.add(null);
        keys.add("2");
        List<Integer> elements = List.of(3, 1, 2);
        assertThrows(NullPointerException.class, () -> new HeapBasedPriorityQueue<>(STRING_COMPARATOR, keys, elements),
                "If the comparator does not allow null values, then an exception must be thrown");
    }

    @Test
    void testClearOnEmptyPQ() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.clear();
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
    }

    @Test
    void testClearOnNonEmptyPQ() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("A", 1);
        pq.insert("B", 2);
        pq.clear();
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
    }

    @Test
    void testTwoTimesClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("A", 1);
        pq.insert("B", 2);
        pq.clear();
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
        pq.clear();
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
    }

    @Test
    void testInsertAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("A", 1);
        pq.insert("B", 2);
        pq.clear();
        pq.insert("C", 3);
        pq.insert("D", 4);
        assertFalse(pq.isEmpty());
        assertEquals(2, pq.size());
    }

    @Test
    void testClearDoesNotDeleteComparator() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Comparator<String> comp = pq.getComparator();
        pq.insert("A", 1);
        pq.clear();
        assertEquals(comp, pq.getComparator(), "Clear must not delete the comparator");
    }

    @Test
    void testLocatorIsInvalidAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test", 1);
        pq.clear();
        assertThrows(InvalidLocatorException.class, () -> pq.key(loc),
                "Old locator is invalid after clear");
    }

    @Test
    void testLocatorIsInvalidAfterClear2() {
        int num = 1000;
        PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        for (int i = 0; i < num; i++) {
            pq.insert(i, i);
        }
        Locator loc = pq.insert(num, num);
        pq.clear();
        assertThrows(InvalidLocatorException.class, () -> pq.key(loc),
                "Old locator is invalid after clear");
    }

    @Test
    void testLocatorIsInvalidAfterClear3() {
        int num = 1000;
        PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        List<Locator> locators = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            locators.add(pq.insert(i, i));
        }
        pq.clear();
        for (Locator l : locators) {
            assertThrows(InvalidLocatorException.class, () -> pq.key(l),
                    "Old locator is invalid after clear");
        }
    }


    @Test
    void testTwoTimesSize() {
        PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq.insert(1, 1);
        pq.insert(2, 2);
        assertEquals(2, pq.size());
        assertEquals(2, pq.size(), "size does not change the state of the pq");
    }


    @Test
    void testTwoTimesMinKey() {
        PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq.insert(1, 1);
        pq.insert(2, 2);
        Integer i1 = pq.minKey();
        Integer i2 = pq.minKey();
        assertEquals(i1, i2);
        assertEquals(2, pq.size(), "minKey does not change the state of the pq");
    }

    @Test
    void testTwoTimesMinElement() {
        PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq.insert(1, 1);
        pq.insert(2, 2);
        Integer i1 = pq.minElement();
        Integer i2 = pq.minElement();
        assertEquals(i1, i2);
        assertEquals(2, pq.size(), "minElement does not change the state of the pq");
    }

    @Test
    void testTwoTimesKey() {
        PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Locator l = pq.insert(1, 1);
        Integer i1 = pq.key(l);
        Integer i2 = pq.key(l);
        assertEquals(i1, i2);
        assertEquals(1, pq.size(), "key does not change the state of the pq");
    }

    @Test
    void testTwoTimesElement() {
        PriorityQueue<Integer, Integer> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Locator l = pq.insert(1, 1);
        Integer i1 = pq.element(l);
        Integer i2 = pq.element(l);
        assertEquals(i1, i2);
        assertEquals(1, pq.size(), "element does not change the state of the pq");
    }

    @Test
    void testMinElementAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test", 123);
        pq.clear();
        assertThrows(EmptyPriorityQueueException.class, pq::minElement);
    }

    @Test
    void testMinKeyAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test", 123);
        pq.clear();
        assertThrows(EmptyPriorityQueueException.class, pq::minKey);
    }

    @Test
    void testReplaceKeyAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test", 123);
        pq.clear();
        assertThrows(InvalidLocatorException.class, () -> pq.replaceKey(loc, "new"), "replaceKey must throw an exception if the locator is invalid");
    }

    @Test
    void testReplaceElementAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test", 123);
        pq.clear();
        assertThrows(InvalidLocatorException.class, () -> pq.replaceElement(loc, 0), "replaceElement must throw an exception if the locator is invalid");
    }

    @Test
    void testRemoveAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test", 123);
        pq.clear();
        assertThrows(InvalidLocatorException.class, () -> pq.remove(loc), "remove must throw an exception if the locator is invalid");
    }

    @Test
    void testKeyAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test", 123);
        pq.clear();
        assertThrows(InvalidLocatorException.class, () -> pq.key(loc), "key must throw an exception if the locator is invalid");
    }

    @Test
    void testElementAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test", 123);
        pq.clear();
        assertThrows(InvalidLocatorException.class, () -> pq.element(loc), "element must throw an exception if the locator is invalid");
    }

    @Test
    void testSetComparatorAfterClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test", 123);
        pq.clear();
        pq.setComparator(pq.getComparator().reversed());
        assertEquals(STRING_COMPARATOR.reversed(), pq.getComparator());
    }

    @Test
    void testPriorityQueueOfPriorityQueues() {
        PriorityQueue<Integer, PriorityQueue<Integer, Integer>> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        PriorityQueue<Integer, Integer> pq1 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq1.insert(1, 10);
        pq1.insert(2, 20);
        pq.insert(100, pq1);
        PriorityQueue<Integer, Integer> pq2 = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        pq2.insert(3, 30);
        pq2.insert(4, 40);
        pq.insert(200, pq2);

        assertEquals(100, pq.minKey());
        assertEquals(1, pq.minElement().minKey());
        assertEquals(10, pq.minElement().minElement());
        pq.minElement().removeMin();
        assertEquals(2, pq.minElement().minKey());
        assertEquals(20, pq.minElement().minElement());

        pq.removeMin();

        assertEquals(200, pq.minKey());
        assertEquals(3, pq.minElement().minKey());
        assertEquals(30, pq.minElement().minElement());
        pq.minElement().removeMin();
        assertEquals(4, pq.minElement().minKey());
        assertEquals(40, pq.minElement().minElement());
    }

    @Test
    void testKeysOnEmptyPriorityQueue() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        assertFalse(pq.keys().hasNext());
        assertThrows(NoSuchElementException.class, () -> pq.keys().next());
    }

    @Test
    void testElementsOnEmptyPriorityQueue() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        assertFalse(pq.elements().hasNext());
        assertThrows(NoSuchElementException.class, () -> pq.elements().next());
    }

    @Test
    void testKeysWithOneElement() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        String key = "test";
        int element = 123;
        pq.insert(key, element);
        Iterator<String> keys = pq.keys();
        assertTrue(keys.hasNext());
        assertEquals(key, keys.next());
        assertFalse(keys.hasNext());
        assertThrows(NoSuchElementException.class, keys::next);
    }

    @Test
    void testElementsWithOneElement() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        String key = "test";
        int element = 123;
        pq.insert(key, element);
        Iterator<Integer> elements = pq.elements();
        assertTrue(elements.hasNext());
        assertEquals(element, elements.next());
        assertFalse(elements.hasNext());
        assertThrows(NoSuchElementException.class, elements::next);
    }


    @Test
    void testKeysWithSeveral() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Set<String> keys = new TreeSet<>(Arrays.asList("test1", "test2", "test3", "test4", "test5"));
        Set<Integer> elements = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<String> keyIter = pq.keys();
        for (int i = 0; i < keys.size(); i++) {
            assertTrue(keyIter.hasNext());
            assertTrue(keys.contains(keyIter.next()));
        }
        assertFalse(keyIter.hasNext());
    }

    @Test
    void testElementsWithSeveral() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Set<String> keys = new TreeSet<>(Arrays.asList("test1", "test2", "test3", "test4", "test5"));
        Set<Integer> elements = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<Integer> elementIter = pq.elements();
        for (int i = 0; i < elements.size(); i++) {
            assertTrue(elementIter.hasNext());
            assertTrue(elements.contains(elementIter.next()));
        }
        assertFalse(elementIter.hasNext());
    }

    @Test
    void testKeysWithSeveralEqualKeys() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        List<String> keys = Arrays.asList("test", "test", "test", "test", "test");
        Set<Integer> elements = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<String> keyIter = pq.keys();
        for (int i = 0; i < keys.size(); i++) {
            assertTrue(keyIter.hasNext());
            assertTrue(keys.contains(keyIter.next()));
        }
        assertFalse(keyIter.hasNext());
    }

    @Test
    void testElementsWithSeveralEqualKeys() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        List<String> keys = Arrays.asList("test", "test", "test", "test", "test");
        Set<Integer> elements = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<Integer> elementIter = pq.elements();
        for (int i = 0; i < elements.size(); i++) {
            assertTrue(elementIter.hasNext());
            assertTrue(elements.contains(elementIter.next()));
        }
        assertFalse(elementIter.hasNext());
    }

    @Test
    void testKeysWithSeveralEqualElements() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        List<String> keys = Arrays.asList("test1", "test2", "test3", "test4", "test5");
        List<Integer> elements = Arrays.asList(1, 1, 1, 1, 1);
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<String> keyIter = pq.keys();
        for (int i = 0; i < keys.size(); i++) {
            assertTrue(keyIter.hasNext());
            assertTrue(keys.contains(keyIter.next()));
        }
        assertFalse(keyIter.hasNext());
    }

    @Test
    void testElementsWithSeveralEqualElements() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        List<String> keys = Arrays.asList("test1", "test2", "test3", "test4", "test5");
        List<Integer> elements = Arrays.asList(1, 1, 1, 1, 1);
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<Integer> elementIter = pq.elements();
        for (int i = 0; i < elements.size(); i++) {
            assertTrue(elementIter.hasNext());
            assertTrue(elements.contains(elementIter.next()));
        }
        assertFalse(elementIter.hasNext());
    }

    @Test
    void testKeysWithSeveralEqualKeysAndElements() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        List<String> keys = Arrays.asList("test", "test", "test", "test", "test");
        List<Integer> elements = Arrays.asList(1, 1, 1, 1, 1);
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<String> keyIter = pq.keys();
        for (int i = 0; i < keys.size(); i++) {
            assertTrue(keyIter.hasNext());
            assertTrue(keys.contains(keyIter.next()));
        }
        assertFalse(keyIter.hasNext());
    }

    @Test
    void testElementsWithSeveralEqualKeysAndElements() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        List<String> keys = Arrays.asList("test", "test", "test", "test", "test");
        List<Integer> elements = Arrays.asList(1, 1, 1, 1, 1);
        Iterator<String> keysIterator = keys.iterator();
        Iterator<Integer> elementsIterator = elements.iterator();
        while (keysIterator.hasNext() && elementsIterator.hasNext()) {
            pq.insert(keysIterator.next(), elementsIterator.next());
        }
        Iterator<Integer> elementIter = pq.elements();
        for (int i = 0; i < elements.size(); i++) {
            assertTrue(elementIter.hasNext());
            assertTrue(elements.contains(elementIter.next()));
        }
        assertFalse(elementIter.hasNext());
    }

    @Test
    void testEndingOfKeysWithInsert() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.insert("test2", 2);
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithInsert() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.insert("test2", 2);
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithRemoveMin() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.removeMin();
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithRemoveMin() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.removeMin();
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithRemove() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.remove(loc);
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithRemove() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.remove(loc);
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithReplaceKey() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.replaceKey(loc, "test2");
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithReplaceKey() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.replaceKey(loc, "test2");
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithReplaceElement() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.replaceElement(loc, 2);
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithReplaceElement() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        Locator loc = pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.replaceElement(loc, 2);
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithSetComparator() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.setComparator(STRING_COMPARATOR.reversed());
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithSetComparator2() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.setComparator(STRING_COMPARATOR.reversed());
        pq.setComparator(STRING_COMPARATOR.reversed());
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithSetComparator() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.setComparator(STRING_COMPARATOR.reversed());
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithSetComparator2() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.setComparator(STRING_COMPARATOR.reversed());
        pq.setComparator(STRING_COMPARATOR.reversed());
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.clear();
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfKeysWithClear2() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<String> keys = pq.keys();
        pq.clear();
        pq.insert("test2", 2);
        assertFalse(keys.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithClear() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.clear();
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }

    @Test
    void testEndingOfElementsWithClear2() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<Integer> elements = pq.elements();
        pq.clear();
        pq.insert("test2", 2);
        assertFalse(elements.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
    }


    @Test
    void testContinuingKeys() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<String> keys1 = pq.keys();
        pq.insert("test2", 2);
        Iterator<String> keys2 = pq.keys();
        assertFalse(keys1.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, keys1::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
        assertTrue(keys2.hasNext(), "Iterator generated after last change can continue");
        assertEquals("test1", keys2.next(), "Iterator generated after last change can continue");
    }

    @Test
    void testContinuingElements() {
        PriorityQueue<String, Integer> pq = new HeapBasedPriorityQueue<>(STRING_COMPARATOR);
        pq.insert("test1", 1);
        Iterator<Integer> elements1 = pq.elements();
        pq.insert("test2", 2);
        Iterator<Integer> elements2 = pq.elements();
        assertFalse(elements1.hasNext(), "Iterator should be ended after modification of the priority queue");
        assertThrows(ConcurrentModificationException.class, elements1::next, "Iterator should throw ConcurrentModificationException after modification of the priority queue");
        assertTrue(elements2.hasNext(), "Iterator generated after last change can continue");
        assertEquals(1, elements2.next(), "Iterator generated after last change can continue");
    }

    @Test
    void testComparator() {
        class TestClass {
        }
        class ComparatorForTestClass implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                if (o1 == null || o2 == null) {
                    throw new NullPointerException("comparator does not allow null values");
                }
                return 0;
            }
        }

        PriorityQueue<TestClass, String> pq = new HeapBasedPriorityQueue<>(new ComparatorForTestClass());
        pq.insert(new TestClass(), "A");
        pq.insert(new TestClass(), "B");
        pq.insert(new TestClass(), "C");
        assertThrows(NullPointerException.class, () -> pq.insert(null, "Test"), "insert must throw nullPointerException if comparator does not allow null values");
        assertEquals(pq.size(), 3);
    }

    @Test
    void testComparator2() {
        class TestClass {
        }
        class ComparatorForTestClass implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                return 0;
            }
        }

        PriorityQueue<TestClass, String> pq = new HeapBasedPriorityQueue<>(new ComparatorForTestClass());
        pq.insert(new TestClass(), "A");
        pq.insert(new TestClass(), "B");
        pq.insert(new TestClass(), "C");
        assertDoesNotThrow(() -> pq.insert(null, "Test"), "insert must not throw nullPointerException if comparator does allow null values");
        assertEquals(pq.size(), 4);
    }

    @Test
    void testRemoveThreeElements() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Locator loc1 = pq.insert(1, "A");
        Locator loc2 = pq.insert(2, "B");
        Locator loc3 = pq.insert(3, "C");
        assertEquals("A", pq.remove(loc1));
        assertEquals("B", pq.remove(loc2));
        assertEquals("C", pq.remove(loc3));
    }

    @Test
    void testRemoveThreeElements2() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Locator loc1 = pq.insert(1, "A");
        Locator loc2 = pq.insert(2, "B");
        Locator loc3 = pq.insert(3, "C");
        assertEquals("C", pq.remove(loc3));
        assertEquals("B", pq.remove(loc2));
        assertEquals("A", pq.remove(loc1));
    }

    @Test
    void testRemoveThreeElements3() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Locator loc3 = pq.insert(3, "C");
        Locator loc2 = pq.insert(2, "B");
        Locator loc1 = pq.insert(1, "A");
        assertEquals("A", pq.remove(loc1));
        assertEquals("B", pq.remove(loc2));
        assertEquals("C", pq.remove(loc3));
    }

    @Test
    void testRemoveThreeElements4() {
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(INT_COMPARATOR);
        Locator loc3 = pq.insert(3, "C");
        Locator loc2 = pq.insert(2, "B");
        Locator loc1 = pq.insert(1, "A");
        assertEquals("C", pq.remove(loc3));
        assertEquals("B", pq.remove(loc2));
        assertEquals("A", pq.remove(loc1));
    }

    @Test
    void testSetComparatorWithComparatorNotAllowingNullKeys() {
        final Comparator<Integer> comparatorWithoutNull = INT_COMPARATOR;
        final Comparator<Integer> comparatorWithNull = Comparator.nullsFirst(comparatorWithoutNull);
        PriorityQueue<Integer, String> pq = new HeapBasedPriorityQueue<>(comparatorWithNull);
        pq.insert(null, "A");
        pq.insert(2, "B");
        assertThrows(InvalidComparatorException.class, () -> pq.setComparator(comparatorWithoutNull),
                "When the old comparator allows null keys, then the new one must also allow null keys");
        assertSame(comparatorWithNull, pq.getComparator(), "The comparator was not changed");
    }

    @Test
    void testComparatorSetterWithAndWithoutNull() {
        class TestClass {
        }
        class ComparatorWithNull implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                return 0;
            }
        }

        class ComparatorWithoutNull implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                if (o1 == null || o2 == null) throw new NullPointerException();
                return 0;
            }
        }

        PriorityQueue<TestClass, String> pq = new HeapBasedPriorityQueue<>(new ComparatorWithNull());
        pq.insert(new TestClass(), "A");
        pq.insert(new TestClass(), "B");
        pq.insert(new TestClass(), "C");
        assertThrows(InvalidComparatorException.class, () -> pq.setComparator(new ComparatorWithoutNull()));
    }

    @Test
    void testComparatorSetterWithAndWithoutNull2() {
        class TestClass {
        }
        class ComparatorWithNull implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                return 0;
            }
        }

        class ComparatorWithoutNull implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                if (o1 == null || o2 == null) throw new NullPointerException();
                return 0;
            }
        }

        PriorityQueue<TestClass, String> pq = new HeapBasedPriorityQueue<>(new ComparatorWithoutNull());
        pq.insert(new TestClass(), "A");
        pq.insert(new TestClass(), "B");
        pq.insert(new TestClass(), "C");
        assertDoesNotThrow(() -> pq.setComparator(new ComparatorWithNull()));
    }

    @Test
    void testComparatorSetterWithAndWithoutNull3() {
        class TestClass {
        }
        class ComparatorWithoutNull implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                if (o1 == null || o2 == null) throw new NullPointerException();
                return 0;
            }
        }

        PriorityQueue<TestClass, String> pq = new HeapBasedPriorityQueue<>(new ComparatorWithoutNull());
        pq.insert(new TestClass(), "A");
        pq.insert(new TestClass(), "B");
        pq.insert(new TestClass(), "C");
        assertDoesNotThrow(() -> pq.setComparator(new ComparatorWithoutNull()));
    }

    @Test
    void testComparatorSetterWithAndWithoutNull4() {
        class TestClass {
        }
        class ComparatorWithNull implements Comparator<TestClass> {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                return 0;
            }
        }

        PriorityQueue<TestClass, String> pq = new HeapBasedPriorityQueue<>(new ComparatorWithNull());
        pq.insert(new TestClass(), "A");
        pq.insert(new TestClass(), "B");
        pq.insert(new TestClass(), "C");
        assertDoesNotThrow(() -> pq.setComparator(new ComparatorWithNull()));
    }

    @Test
    void testConstructorWithNull() {
        List<Integer> keys = new ArrayList<>(Arrays.asList(10, 20, 30, null, 50));
        List<String> values = new ArrayList<>(Arrays.asList("10", "20", "30", "null", "50"));
        assertThrows(NullPointerException.class, () -> new HeapBasedPriorityQueue<>(INT_COMPARATOR, keys, values), "the comparator does not allow null values");
    }

    @Test
    void testConstructorWithNull2() {
        Comparator<Integer> comp = Comparator.nullsFirst(INT_COMPARATOR);
        List<Integer> keys = new ArrayList<>(Arrays.asList(10, 20, 30, null, 50));
        List<String> values = new ArrayList<>(Arrays.asList("10", "20", "30", "null", "50"));
        assertDoesNotThrow(() -> new HeapBasedPriorityQueue<>(comp, keys, values), "the comparator does allow null values");
    }



    // helper methods

    private static Stream<String> createStrings() {
        return Stream.of("A", "AA", "AAA", "TESTESTESTESTST", "\u0074\u0065\u0073\u0074", "\t", "\n",
                "Μῆνιν ἄειδε, θεά, Πηληϊάδεω Ἀχιλῆος", "ℕ ⊆ ℕ₀ ⊂ ℤ ⊂ ℚ ⊂ ℝ ⊂ ℂ", "", "\"");
    }

    private static Stream<Integer> createInts() {
        return Stream.of(0, 1, 2, 100, Integer.MAX_VALUE, -1, -2, -100, Integer.MIN_VALUE, 1001, -1001);
    }


}