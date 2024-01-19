/*
 * Project and Training 2 - FS23, Computer Science, Berner Fachhochschule
 */

package ch.bfh.queue;

import ch.bfh.basics.Locator;
import ch.bfh.exceptions.EmptyPriorityQueueException;
import ch.bfh.exceptions.InvalidLocatorException;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;


/**
 * Implementation of a heap-based priority queue {@link PriorityQueue}
 * @param <K> The type of the keys in the priority queue
 * @param <E> The type of the elements in the priority queue
 */
public class HeapBasedPriorityQueue<K, E> implements PriorityQueue<K, E> {

    private static final int INIT_SIZE = 16; // Initial size of the array
    private int capacity = INIT_SIZE;
    private int size = 0;
    private Node<K, E>[] nodes;
    private Comparator<K> comparator;

    /**
     * Creates a new priority queue with the given comparator.
     * @param comparator the comparator to be used
     * @throws NullPointerException if the comparator is null
     */
    public HeapBasedPriorityQueue(Comparator<K> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator cannot be null");
        }
        this.comparator = comparator;
        this.nodes = createArray(INIT_SIZE);
        this.capacity = 0;
    }

    /**
     * Creates a Node<K, E> array with the size given by the parameter.
     * @param sizeValue an int value with the size, the array should be.
     * @return a Node array with the wanted size.
     */
    @SuppressWarnings("unchecked")
    // This helper method suppresses the unchecked warning when creating a new array of Node<K, E>[]
    private Node<K, E>[] createArray(int sizeValue) {
        return (Node<K, E>[]) new Node[sizeValue];
    }

    /**
     * Creates a new priority queue with the given comparator and the given keys and elements.
     * @param comparator the comparator to be used
     * @param keys the keys to be used
     * @param elements the elements to be used
     */
    public HeapBasedPriorityQueue(Comparator<K> comparator, List<K> keys, List<E> elements) {
        if (comparator == null) {
            throw new NullPointerException("Comparator cannot be null");
        }
        if (keys.size() != elements.size()) {
            throw new IllegalArgumentException("Keys and elements must have the same size");
        }
        this.comparator = comparator;
        this.nodes = createArray(keys.size());
        this.capacity = keys.size();

        for (int i = 0; i < keys.size(); i++) {
            nodes[i] = new Node<>(keys.get(i), null);
            nodes[i].setElement(elements.get(i));
        }

        buildHeap();
    }

    /**
     * Method that builds the heap structure by using the shift down method on every element inside the PQ.
     */
    private void buildHeap() { // Build heap
        for (int i = capacity / 2 - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    /**
     * Method to get the index of the parent node.
     * @param i an int value with the index of the child.
     * @return an int value representing the parent index.
     */
    private int getParent(int i) { // Get index of parent
        return (i - 1) / 2;
    }

    /**
     * Method to get the index of the leftChild node.
     * @param i an int value with the index of the parent.
     * @return an int value representing the leftChild index.
     */
    private int getLeftChild(int i) { // Get index of left child
        return 2 * i + 1;
    }

    /**
     * Method to get the index of the rightChild node.
     * @param i an int value with the index of the parent.
     * @return an int value representing the rightChild index.
     */
    private int getRightChild(int i) { // Get index of right child
        return 2 * i + 2;
    }

    /**
     * This Method swaps two Nodes in the heap.
     * @param i an int representing the index of the first Node.
     * @param j an int representing the index of the second Node.
     */
    private void swap(int i, int j) { // Swap two elements
        Node<K, E> temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;

        nodes[i].setIndex(i);
        nodes[j].setIndex(j);
    }

    /**
     * Helper method to sift up an element to its correct position, when the key is smaller than its parent.
     * @param i an int value with the index of the to be checked element.
     */
    // Helper method to "bubble" up an element to its correct position
    private void bubbleUp(int i) {
        while (i != 0 && comparator.compare(nodes[i].getKey(), nodes[getParent(i)].getKey()) < 0) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    /**
     * Helper method to sift up an element to its correct position, when the key is greater than its childes.
     * Checks every leftChild and rightChild, of a smaller Key exists in its childes.
     * Does this as long as smaller keys exist in one or the other childes.
     *
     * @param i an int value with the index of the to be checked element.
     */
    // Helper method to "bubble" down an element to its correct position
    private void bubbleDown(int i) {
        int minChild;
        while (getLeftChild(i) < capacity) {
            minChild = getLeftChild(i);
            if (getRightChild(i) < capacity && comparator.compare(nodes[getRightChild(i)].getKey(),
                    nodes[getLeftChild(i)].getKey()) < 0) {
                minChild = getRightChild(i);
            }
            if (comparator.compare(nodes[minChild].getKey(), nodes[i].getKey()) < 0) {
                swap(i, minChild);
                i = minChild;
            } else {
                break;
            }
        }
    }

    /**
     * Returns the hashCode for the heap.
     * @return an int value representing the hashCode of the priority queue
     */
    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < capacity; i++) {
            result = 31 * result + (nodes[i] != null ? nodes[i].hashCode() : 0);
        }
        return result;
    }

    /**
     * Compares this priority queue with another object for equality.
     * By looking of these equals the object.
     * The object is the same class as this or the object equals null.
     * Or if something in the array is equal to the object.
     *
     * @param object the object to be compared for equality with this priority queue
     * @return true if the specified object is equal to this priority queue
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        HeapBasedPriorityQueue<?, ?> queue = (HeapBasedPriorityQueue<?, ?>) object;
        if (capacity != queue.capacity) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (nodes[i] == null) {
                if (queue.nodes[i] != null) {
                    return false;
                }
            } else if (!nodes[i].equals(queue.nodes[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the current size of the priority queue
     * @return size of the elements in the priority queue as an int
     */
    @Override
    public int size() {
        return capacity;
    }

    /**
     * Checks if the priority queue is empty.
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    /**
     * Sets back the priority queue to its original empty form.
     */
    @Override
    public void clear() {
        capacity = 0;
        nodes = createArray(INIT_SIZE);
        size = 0;
    }

    /**
     * Inserts a new Node to the priority queue.
     * If the queue has reached its capacity, the queue will be replaced by a new queue with double the size.
     * Then the Element will be inserted and put to its correct position.
     * The size will be increased.
     *
     * @param key     The key of the new tuple
     * @param element The element of the new tuple
     * @return the Locator of the inserted element
     * @throws IllegalArgumentException if the comparator does not allow null as a key
     */

    @Override
    public Locator insert(K key, E element) {
        //Needs to call on itself to check if null is a valid key
        //noinspection EqualsWithItself
        if (comparator.compare(key, key) != 0) {
            throw new IllegalArgumentException("Key is not comparable");
        }
        if (capacity == nodes.length) {
            Node<K, E>[] newNode = createArray(nodes.length * 2);
            System.arraycopy(nodes, 0, newNode, 0, capacity);
            nodes = newNode;
        }

        Node<K, E> node = new Node<>(key, element);
        node.setIndex(capacity);
        nodes[capacity] = node;
        bubbleUp(capacity);
        capacity++;
        size++;

        return node;
    }

    /**
     * Removes the minimum element from the priority queue and decreases the queues size.
     *
     * @return the minimum element in the priority queue
     * @throws EmptyPriorityQueueException if the priority queue is empty
     */
    @Override
    public E removeMin() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Queue is empty");
        }
        Node<K, E> minEle = nodes[0];
        nodes[0] = nodes[capacity - 1];
        nodes[0].setIndex(0);
        nodes[capacity - 1] = null;
        capacity--;
        bubbleDown(0);
        size++;
        return minEle.getElement();
    }

    /**
     * Returns the Key object of the min Node.
     * @return the key object associated with the min Node
     * @throws EmptyPriorityQueueException if the priority queue is empty
     */
    @Override
    public K minKey() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Queue is empty");
        }
        return nodes[0].getKey();
    }


    /**
     * Returns the Element object of the min Node.
     * @return the Element object associated with the min Node
     * @throws EmptyPriorityQueueException if the priority queue is empty
     */
    @Override
    public E minElement() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Queue is empty");
        }
        return nodes[0].getElement();
    }

    /**
     * Replaces the current Key of a Node with the newly given one.
     * By setting the new one and put the Node to its new position.
     *
     * @return the old Key object associated with the locator
     * @throws InvalidLocatorException if the locator is invalid
     */
    @Override
    public K replaceKey(Locator locator, K key) throws InvalidLocatorException {
        Node<K, E> node = validate(locator);
        K previousKey = node.getKey();
        node.setKey(key);

        if (comparator.compare(key, previousKey) < 0) {
            bubbleUp(node.getIndex());
        } else if (comparator.compare(key, previousKey) > 0) {
            bubbleDown(node.getIndex());
        }
        size++;
        return previousKey;
    }

    /**
     * Replaces the current Element of the Locator with the newly given one.
     *
     *
     * @return the old element associated with the locator
     * @throws InvalidLocatorException if the locator is invalid
     */
    @Override
    public E replaceElement(Locator locator, E element) throws InvalidLocatorException {
        Node<K, E> node = validate(locator);
        E previousElement = node.getElement();
        node.setElement(element);
        size++;
        return previousElement;
    }

    /**
     * Removes the Node corresponding to the given locator.
     * If the Locator was the last Node it is simply removed, if not, the priority queue is sorted.
     *
     * @return the old element that was removed form the priority queue
     * @throws InvalidLocatorException if the locator is invalid
     */
    @Override
    public E remove(Locator locator) throws InvalidLocatorException {
        Node<K, E> node = validate(locator);
        int index = node.getIndex();
        if (index == capacity - 1) {
            capacity--;
        } else {
            swap(index, capacity - 1);
            capacity--;
            Node<K, E> swappedNode = nodes[index];
            bubbleUp(swappedNode.getIndex());
            bubbleDown(swappedNode.getIndex());
        }
        size++;
        return node.getElement();
    }

    /**
     * Returns the Key object of the locator.
     *
     * @return the key associated with the locator
     * @throws InvalidLocatorException if the locator is invalid
     */
    @Override
    public K key(Locator locator) throws InvalidLocatorException {
        Node<K, E> node = validate(locator);
        return node.getKey();
    }

    /**
     * Returns the Element object of the locator.
     *
     * @return the element associated with the locator
     * @throws InvalidLocatorException if the locator is invalid
     */
    @Override
    public E element(Locator locator) throws InvalidLocatorException {
        Node<K, E> node = validate(locator);
        return node.getElement();
    }

    /**
     * Method returns the comparator, which is used to sort the priority queue.
     *
     * @return comparator of the priority queue.
     */
    @Override
    public Comparator<K> getComparator() {
        return comparator;
    }

    /**
     * Replaces the old comparator with a new one.
     * The Heap is build anew according to the new comparator.
     *
     * @param newComparator A new comparator, with which the priority queue should now be sorted.
     * @return the old comparator
     * @throws NullPointerException if the comparator is null
     */
    @Override
    public Comparator<K> setComparator(Comparator<K> newComparator) throws NullPointerException {
        if (newComparator == null) {
            throw new NullPointerException("Comparator cannot be null");
        }
        Comparator<K> previousComparator = this.comparator;
        this.comparator = newComparator;
        buildHeap();
        return previousComparator;
    }

    /**
     * Inner class of Iterators of the key.
     *
     * @throws ConcurrentModificationException if the priority queue is modified while iterating
     * @throws NoSuchElementException if there are no more elements to iterate over
     * @return Iterator over the keys of the elements in the priority queue
     */
    @Override
    public Iterator<K> keys() {
        return new Iterator<>() {
            private int indexNow = 0; // current index of the iterator

            private final int presentModificationCount = size; // current modification count of the priority queue
            private final Comparator<K> c = getComparator();

            /**
             * Checks if the current Iterator has a next Iterator.
             *
             * @return true, if the Iterator has a next Iterator, otherwise returns false.
             */
            @Override
            public boolean hasNext() {
                if (presentModificationCount != size || c != getComparator()) {
                    return false;
                }
                return indexNow < capacity;
            }

            /**
             * Returns the next key of the next Iterator.
             * It checks, if the priority queue was modified.
             * Checks if the Iterator has a following Iterator
             * And then Returns the Key object if all the tests pass.
             *
             * @return Key object of the Iterator.
             * @throws ConcurrentModificationException if the priority queue was modified.
             * @throws NoSuchElementException if the Iterator does not have a next Iterator.
             */
            @Override
            public K next() {
                if (presentModificationCount != size || c != getComparator()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = nodes[indexNow].getKey();
                indexNow++;
                return key;
            }
        };
    }

    /**
     * Inner class of Iterators of the elements.
     *
     * @throws ConcurrentModificationException if the priority queue is modified while iterating
     * @throws NoSuchElementException if there are no more elements to iterate over
     * @return Iterator over the elements in the priority queue
     */
    @Override
    public Iterator<E> elements() {
        return new Iterator<>() {
            private int indexNow = 0; // current index of the iterator

            private final int presentModificationCount = size; // current modification count of the priority queue

            private final Comparator<K> c = getComparator();

            /**
             * Checks if the current Iterator has a next Iterator.
             *
             * @return true, if the Iterator has a next element, otherwise returns false.
             */
            @Override
            public boolean hasNext() {
                if (presentModificationCount != size || c != getComparator()) {
                    return false;
                }
                return indexNow < capacity;
            }

            /**
             * Returns the next element of the next Iterator.
             * First checks, if the priority queue was modified.
             * Then checks if the Iterator has a following Iterator
             * And then Returns the Key object if all tests pass.
             *
             * @return Element object of the Iterator.
             * @throws ConcurrentModificationException if the priority queue was modified.
             * @throws NoSuchElementException if the Iterator does not have a next Iterator.
             */
            @Override
            public E next() {
                if (presentModificationCount != size || c != getComparator()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = nodes[indexNow].getElement();
                indexNow++;
                return element;
            }
        };
    }
    /**
     * Validates the given locator and returns the corresponding node.
     *
     * @param locator the locator to validate
     * @return the validated node
     * @throws InvalidLocatorException if the locator is invalid
     */
    //helper method that validates the locator and returns the corresponding node
    private Node<K, E> validate(Locator locator) throws InvalidLocatorException {
        if (!(locator instanceof Node)) {
            throw new InvalidLocatorException("Invalid locator");
        }
        Node<K, E> node = toNode(locator);
        if (node.getIndex() >= capacity || nodes[node.getIndex()] != node) {
            throw new InvalidLocatorException("Invalid locator");
        }
        return node;
    }

    /**
     * helper method, that casts the Locator to a Node.
     *
     * @param locator the Locator that needs to be cast.
     * @return Node of the locator.
     */
    @SuppressWarnings("unchecked")
    //Helper method  that Unchecked cast: Node<K, E> zu Locator
    private Node<K, E> toNode(Locator locator) {
        return (Node<K, E>) locator;
    }


    /**
     * Represents a node in the priority queue.
     *
     * @param <K> the type of the key
     * @param <E> the type of the element
     */
    //inner class of the Node
    private static class Node<K, E> implements Locator {
        private K key;
        private E element;
        private int index;

        /**
         * Creates a new Node with the given key and element objects.
         *
         * @param key the Key object ob the new Node.
         * @param element the Element object of the new Node.
         */
        Node(K key, E element) {
            this.key = key;
            this.element = element;
        }

        /**
         * Returns the Key object of the Node.
         *
         * @return key object
         */
        private K getKey() {
            return key;
        }

        /**
         * returns the element of the Node.
         *
         * @return element object
         */
        private E getElement() {
            return element;
        }

        /**
         * returns the index of the Node.
         *
         * @return int value as the index.
         */
        private int getIndex() {
            return index;
        }

        /**
         * Setter method, that sets the new key object.
         *
         * @param key the new key object the Node should have.
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Setter method, that sets the new element object.
         *
         * @param element the new element object the Node should have.
         */
        private void setElement(E element) {
            this.element = element;
        }

        /**
         * Setter method, that sets the new int index.
         *
         * @param index the new int index the Node should have.
         */
        private void setIndex(int index) {
            this.index = index;
        }
    }
}
