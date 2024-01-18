/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

package ch.bfh.queue;

import ch.bfh.basics.Locator;
import ch.bfh.exceptions.EmptyPriorityQueueException;
import ch.bfh.exceptions.InvalidComparatorException;
import ch.bfh.exceptions.InvalidLocatorException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of a heap-based priority queue {@link PriorityQueue}
 *
 * @param <K> The type of the keys in the priority queue
 * @param <E> The type of the elements in the priority queue
 */
public class HeapBasedPriorityQueue<K, E> implements PriorityQueue<K, E> {

    private static final int INITIAL_SIZE = 16;
    private int capacity = INITIAL_SIZE;
    private int size = 0;
    private Node[] nodes = new Node[INITIAL_SIZE];
    private final Comparator<K> comparator;

    public HeapBasedPriorityQueue(Comparator<K> comparator){
        this.comparator = comparator;
    }
    public HeapBasedPriorityQueue(Comparator<K> comparator, List<K> keys, List<E> elements){
        this.comparator = comparator;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

    }

    @Override
    public Locator insert(K key, E element) {
        if(size == capacity){
            resize(capacity * 2);
        }
        Node<K,E> node = new Node(key,element);
        nodes[size] = node;
        int i = size;
        size++;
        while(i > 0){
            Node<K,E> parent = nodes[getParent(i)];
            if(comparator.compare(parent.key, node.key) < 0) {
               break;
            }
            swap(i, getParent(i));
            i = getParent(i);
        }
        return node;
    }

    @Override
    public E removeMin() throws EmptyPriorityQueueException {
        if(size == 0){
            throw new EmptyPriorityQueueException();
        }
        Node<K,E> node = nodes[0];
        swap(0, size - 1);
        nodes[size - 1] = null;
        size--;
        bubbleDown(0);
        shrinkIfNeeded();
        return node.element;
    }

    @Override
    public K minKey() throws EmptyPriorityQueueException {
        if(size == 0){
            throw new EmptyPriorityQueueException();
        }
        Node<K,E> node = nodes[0];
        return node.key;
    }

    @Override
    public E minElement() throws EmptyPriorityQueueException {
        if(size == 0){
            throw new EmptyPriorityQueueException();
        }
        Node<K,E> node = nodes[0];
        return node.element;
    }

    @Override
    public K replaceKey(Locator locator, K key) throws InvalidLocatorException, NullPointerException {
        return null;
    }

    @Override
    public E replaceElement(Locator locator, E element) throws InvalidLocatorException {
        if(locator instanceof HeapBasedPriorityQueue.Node){
            Node<K,E> node = (Node) locator;
            E temp = node.element;
            node.element = element;
            return temp;
        }
        throw new InvalidLocatorException();
    }

    @Override
    public E remove(Locator locator) throws InvalidLocatorException {
        return null;
    }

    @Override
    public K key(Locator locator) throws InvalidLocatorException {
        if(locator instanceof HeapBasedPriorityQueue.Node){
            Node <K,E> node = (Node<K, E>) locator;
            return node.key;
        }else {
            throw new InvalidLocatorException("No key.");
        }
    }

    @Override
    public E element(Locator locator) throws InvalidLocatorException {
        if(locator instanceof HeapBasedPriorityQueue.Node){
            Node <K,E> node = (Node<K, E>) locator;
            return node.element;
        }else {
            throw new InvalidLocatorException("No element.");
        }
    }

    @Override
    public Comparator<K> getComparator() {
        return comparator;
    }

    @Override
    public Comparator<K> setComparator(Comparator<K> comparator) throws NullPointerException, InvalidComparatorException {

        return null;
    }

    @Override
    public Iterator<K> keys() {
        return null ;
    }

    @Override
    public Iterator<E> elements() {
        return null;
    }
    private void swap(int i, int j){
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }
    private int getRightChild(int i){
        return 2 * i + 2;
    }
    private int getLeftChild(int i) {
        return 2 * i + 1;
    }
    private int getParent(int i) {
        return (i - 1)/2;
    }
    private void shrinkIfNeeded(){
        if(size <= capacity/4 && capacity/4 > INITIAL_SIZE){
            resize(capacity/2);
        }
    }
    private void resize(int newCapacity){
        Node[] newNodes = new Node[newCapacity];
        for (int i = 0; i < size; i++) {
            newNodes[i] = nodes[i];
        }
        capacity = newCapacity;
        nodes = newNodes;
    }
    private void bubbleDown(int i){
        int l = getLeftChild(i);
        int r = getRightChild(i);
        int x = i;
        Node<K,E> node = nodes[i];
        if(l < size){
            Node<K,E> left = nodes[l];
            if(comparator.compare(left.key, node.key) < 0){
                x = l;
                node = nodes[x];
            }
        }
        if(r < size){
            Node<K,E> right = nodes[r];
            if(comparator.compare(right.key, node.key) < 0){
                x = r;
            }
        }
        if(i != x){
            swap(i, x);
            bubbleDown(x);
        }
    }
    private class Node<K, E> implements Locator {
        private final K key;
        private E element;

        public Node(K key, E element) {
            this.key = key;
            this.element = element;
        }
    }
}
