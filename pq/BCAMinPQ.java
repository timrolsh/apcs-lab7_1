package pq;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BCAMinPQ<E extends Comparable<E>> implements BCAQueue<E> {

    /* Stores items in heap starting at index 1 */
    public ArrayList<E> heap = new ArrayList<>();

    public BCAMinPQ() {
        /* dummy element in index 0, making actual elements start at index 1 */
        heap.add(null);
    }

    /**
     * Return index of the left child of element at index i.
     */
    private int leftChildOf(int i) {
        return 2 * i;

    }

    /**
     * Return index of the right child of element at index i.
     */
    private int rightChildOf(int i) {
        return 2 * i + 1;
    }

    /**
     * Return index of the parent of element at index i.
     */
    private int parentOf(int i) {
        return i / 2;
    }

    /**
     * Swap the elements at indexes i1 and i2.
     */
    private void swap(int i1, int i2) {
        E temp = heap.get(i1);
        heap.set(i1, heap.get(i2));
        heap.set(i2, temp);
    }

    /**
     * Push up the element at index i (swapping with bigger parent)
     * until it is at the appropriate level. (parent is smaller)
     * This will fix the heap property if the element of index i
     * is the only element out of place.
     */
    private void pushUp(int i) {
        // checks that i is not root already and that its parent is greater than itself
        while ((i != 1) && (heap.get(parentOf(i)).compareTo(heap.get(i))) > 0) {
            swap(i, parentOf(i));
            i /= 2;
        }
    }

    /**
     * Adds a new element to the the queue.
     */
    public void enqueue(E o) {
        heap.add(o);
        pushUp(heap.size() - 1);
    }

    /* Try BCAMinPQTest now! You should pass 0.1 to 0.6 */

    /**
     * Returns the next item from the queue without popping it.
     * If no item, returns null
     */
    public E peek() {
        return heap.get(1);
    }

    /* Try BCAMinPQTest now! You should pass 1a */

    /**
     * Push down the element at index i (swapping with its smallest child)
     * until it is at the appropriate level. (children are both bigger)
     * This will fix the heap property if the element of index i
     * is the only element out of place.
     */
    private void pushDown(int i) {
        while (((rightChildOf(i) < heap.size()) || (leftChildOf(i) < heap.size()))) {
            // left not null right null
            if ((rightChildOf(i) >= heap.size()) && (heap.get(leftChildOf(i)).compareTo(heap.get(i)) < 0)) {
                int temp = leftChildOf(i);
                swap(i, leftChildOf(i));
                i = temp;
            }
            // both not null
            else {

                int temp;
                if (heap.get(rightChildOf(i)).compareTo(heap.get(leftChildOf(i))) < 0) {
                    temp = rightChildOf(i);
                    swap(i, rightChildOf(i));
                } else {
                    temp = leftChildOf(i);
                    swap(i, leftChildOf(i));

                }
                i = temp;
            }
            // element is in the right spot
            if ((leftChildOf(i) >= heap.size() || (heap.get(leftChildOf(i)).compareTo(heap.get(i)) > 0)) && (rightChildOf(i) >= heap.size() || (heap.get(rightChildOf(i)).compareTo(heap.get(i)) > 0))) {
                break;
            }
        }
    }

    /**
     * Removes the smallest item from the queue and returns it.
     *
     * @throws NoSuchElementException if the queue is empty.
     */
    public E dequeue() {

        if (isEmpty()) throw new NoSuchElementException("MinPQ is empty");

        swap(1, heap.size() - 1);
        E dequeue = heap.remove(heap.size() - 1);
        pushDown(1);
        return dequeue;
    }

    /*
     * Try BCAMinPQTest now! You should pass the remaining tests 1b through 8, and
     * HeapSort
     */

    /**
     * Returns whether the queue is empty or not.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of items currently in the queue.
     */
    public int size() {
        return heap.size() - 1; // The arraylist has an extra element at position 0, hence the subtraction of 1.
    }

    /**
     * Return item at index i
     */
    public E get(int i) {
        return heap.get(i);
    }
}
