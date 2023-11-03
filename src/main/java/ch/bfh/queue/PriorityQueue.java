/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.

package ch.bfh.queue;

import java.util.Comparator;
import java.util.Iterator;

import ch.bfh.basics.BasicCollection;
import ch.bfh.basics.Locator;
import ch.bfh.exceptions.EmptyPriorityQueueException;
import ch.bfh.exceptions.InvalidLocatorException;
import ch.bfh.exceptions.InvalidComparatorException;

/**
 * @param <K> The type of the keys in the priority queue
 * @param <E> The type of the elements in the priority queue
 * <p>
 * This interface specifies a priority queue. The order of the priority queue is
 * determined by the comparator (an instance of java.util.Comparator is then
 * specified during instantiation of the priority queue).
 * </p><p>
 * An implementation of this interface must provide (at least) two
 * constructors:
 * <ul>
 * <li> {@literal HeapBasedPriorityQueue(Comparator<K> comparator)} a constructor with a
 * comparator as a single parameter which generates a priority queue where the
 * keys are ordered according to the comparator (that is of type
 * java.util.Comparator). </li>
 * <li> {@literal HeapBasedPriorityQueue(List<K> keys, List<E> elements, Comparator<K> comparator)}
 *  a constructor with three arguments (a
 * list of keys, a corresponding list of elements and a comparator) which
 * generates a priority queue where the keys are ordered according to the
 * comparator (that is of type java.util.Comparator).</li>
 * </ul>
 *
 *
 */
public interface PriorityQueue<K, E> extends BasicCollection<E> {

	/**
	 * Inserts a new tuple (key, element) into the priority queue.
	 *
	 * @param key     The key of the new tuple
	 * @param element The element of the new tuple
	 * @return The locator where the element is stored.
	 */
	Locator insert(K key, E element);

	/**
	 * This method deletes the tuple that has the smallest key in the priority
	 * queue. If several such tuples exist, one of them is deleted. The method
	 * returns the element that belongs to the deleted tuple.
	 *
	 * @return The element which belongs to the tuple being deleted.
	 * @throws EmptyPriorityQueueException This exception is thrown if the priority
	 *                                     queue is empty.
	 */
	E removeMin() throws EmptyPriorityQueueException;

	/**
	 * @return The key which belongs to the tuple with the smallest key in the
	 *         priority queue. If several keys satisfy this condition, one of these
	 *         keys is returned.
	 * @throws EmptyPriorityQueueException This exception is thrown if the priority
	 *                                     queue is empty.
	 */
	K minKey() throws EmptyPriorityQueueException;

	/**
	 * @return The element which belongs to the tuple with the smallest key in the
	 *         priority queue. If several keys satisfy this condition, one of the
	 *         corresponding elements is returned.
	 * @throws EmptyPriorityQueueException This exception is thrown if the priority
	 *                                     queue is empty.
	 */
	E minElement() throws EmptyPriorityQueueException;

	/**
	 * This method replaces the key at some locator in the priority queue.
	 *
	 * @param locator a locator denoting the place where the key is changed.
	 * @param key     the new key
	 * @return The key at the locator which has been replaced.
	 * @throws InvalidLocatorException if the locator does not belong to the actual
	 *                                 priority queue.
	 * @throws NullPointerException if the key is null and the comparator does not allow null values.
	 */
	K replaceKey(Locator locator, K key) throws InvalidLocatorException, NullPointerException;

	/**
	 * This method replaces the element at some locator in the priority queue.
	 *
	 * @param locator a locator denoting the place where the element is changed.
	 * @param element the new element
	 * @return The element at the locator which has been replaced.
	 * @throws InvalidLocatorException if the locator does not belong to the actual
	 *                                 priorityqueue.
	 */
	E replaceElement(Locator locator, E element) throws InvalidLocatorException;

	/**
	 * This method removes the pair at the given locator in the priority queue.
	 *
	 * @param locator the locator which is to be removed.
	 * @return The value at locator which has been removed.
	 * @throws InvalidLocatorException if the locator does not belong to the actual
	 *                                 priorityqueue.
	 */
	E remove(Locator locator) throws InvalidLocatorException;

	/**
	 * @param locator a locator
	 * @return The key at the given locator
	 * @throws InvalidLocatorException if the locator does not belong to the actual
	 *                                 priority queue.
	 */
	K key(Locator locator) throws InvalidLocatorException;

	/**
	 * @param locator a locator
	 * @return The element at the given locator
	 * @throws InvalidLocatorException if the locator does not belong to the actual
	 *                                 priority queue.
	 */
	E element(Locator locator) throws InvalidLocatorException;

	/**
	 * @return The comparator used by this priority queue
	 */
	Comparator<K> getComparator();

	/**
	 * Set a new comparator to be used for ordering of the priority queue.
	 * Setting a new comparator implies a
	 * restructuring of the elements contained with respect to the new comparator.
	 *
	 * @param comparator A new comparator
	 * @return The previously used comparator
	 * @throws NullPointerException if the comparator is null.
	 * @throws InvalidComparatorException if the old comparator does allow null keys
	 * and the new one does not allow null keys.
	 */
	Comparator<K> setComparator(Comparator<K> comparator) throws NullPointerException, InvalidComparatorException;

	/**
	 * This method returns an iterator over all keys used in this priority queue.
	 * Note that this iterator must be fail-fast, i.e. a call to next
	 * must throw a ConcurrentModificationException (and hasNext must return false)
	 * if the underlying priority queue has been changed after the creation of this
	 * iterator, hence if any of the methods insert, removeMin, replaceKey,
	 * replaceElement, remove, setComparator or clear has been called.
	 * If a key occurs more than once in the priority queue, it must be
	 * returned by this iterator as many times as it occurs in the priority queue.
	 *
	 * @return an iterator over all keys actually in the priority queue.
	 */
	Iterator<K> keys();

	/**
	 * This method returns an iterator over all elements used in this priority queue.
	 * Note that this iterator must be fail-fast, i.e. a call to next
	 * must throw a ConcurrentModificationException (and hasNext must return false)
	 * if the underlying priority queue has been changed after the creation of this
	 * iterator, hence if any of the methods insert, removeMin, replaceKey,
	 * replaceElement, remove, setComparator or clear has been called.
	 * If an element occurs more than once in the priority queue, it must be
	 * returned by this iterator as many times as it occurs in the priority queue.
	 *
	 * @return an iterator over all elements actually in the priority queue.
	 */
	Iterator<E> elements();
}
