package de.hawh.ld.fixedsizequeue;

/**
 * An Interface to be used for a specialized Queue in a lab task.
 * @author Bernd Kahlbrandt
 *
 */
public interface Queue<E> {
	/**
	 * Default capacity to be used by a fixed sized Queue, akna.&nbsp;
	 * input restricted Queue.
	 */
	static int DEFAULT_CAPACITY = 42;
	/**
	 * Inserts an element into the Queue.
	 * @param element The element to be inserted
	 */
	void enqueue(E element);
	/**
	 * Removes the first (oldest) element in the Queue and returns it.
	 * @return The first element.
	 */
	void dequeue();
	/**
	 * Returns the first element of the Queue but leaves it in the Queue.
	 * @return The first element.
	 */
	E peek();	
	/**
	 * Checks if the Queue is empty.
	 * @return <b>true</b> if the Queue is empty, <b>false</b> otherwise.
	 */
	boolean isEmpty();
	/**
	 * Checks if the Queue is full.
	 * @return <b>true</b> if the Queue is full, <b>false</b> otherwise.
	 */
	boolean isFull();
}
