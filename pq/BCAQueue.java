package pq;

import java.util.NoSuchElementException;

/**
 * BCAQueue is an interface for generic queue-like data structures.
 */
public interface BCAQueue<T> {

    /**
     * Adds a new element to the the queue.
     */
    void enqueue(T o);

    /**
     * Removes the next item from the queue and returns it.
     *
     * @throws NoSuchElementException if the queue is empty.
     */
    T dequeue();

    /**
     * Returns the next item from the queue without popping it.
     * If no item, returns null
     */
    T peek();

    /**
     * Returns whether the queue is empty or not.
     */
    boolean isEmpty();

    /**
     * Returns the number of items currently in the queue.
     */
    int size();
}
