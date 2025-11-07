import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

public class GenericQueue<E> {
	private final int MAX_QUEUE_SIZE;
	private LinkedList<E> queue = new LinkedList<>();
	private int size;

	public GenericQueue() {		
		MAX_QUEUE_SIZE = 20;
		queue = new LinkedList<>();
	}

	public GenericQueue(int qSize) {		
		MAX_QUEUE_SIZE = qSize;
		queue = new LinkedList<>();
	}

	/*
	 * Implement the following methods:
	 * 1) boolean isEmpty()
	 * 2) int size()
	 * 3) boolean add(E o)
	 * 4) boolean offer(E o)
	 * 5) E remove()
	 * 6) E poll()
	 * 7) E element()
	 * 8) E peek()
	 */
	
	/**
	 * Checks if the queue is empty.
	 *
	 * @return true if empty
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}
	
	/**
	 * gets size of queue.
	 *
	 * @return size of queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds the element o to the queue
	 *
	 * @param o the object being added
	 * @return true, if successful, throws an IllegalStateException() if not
	 */
	public boolean add(E o) {
		try {
			if (size >= MAX_QUEUE_SIZE) {
				throw new IllegalStateException();
			}
			size++;
			return queue.add(o);
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Attempts to add element o to the queue
	 *
	 * @param o the object being added
	 * @return true, if successful, false if not
	 */
	public boolean offer(E o) {
		if (size >= MAX_QUEUE_SIZE) {
			return false;
		}
		size++;
		return queue.add(o);
	}
	
	/**
	 * Removes the element at the head of the queue
	 *
	 * @return the element at the head, throws a NoSuchElementException if the size is 0
	 */
	public E remove() {
		try {
			if (size == 0) {
				throw new NoSuchElementException();
			}
			size--;
			return queue.remove();
		} catch (Exception e) {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Attempts to remove the element at the head of queue
	 *
	 * @return the element at the head, null if size is 0
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}
		size--;
		return queue.remove();
	}
	
	/**
	 * Gets the element at head of queue but doesn't remove
	 *
	 * @return the element at head, throws a NoSuchElementException if there is no element
	 */
	public E element() {
		try {
			if (size == 0) {
				throw new NoSuchElementException();
			}
			return queue.element();
		} catch (Exception e) {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Peeks the element at head of queue
	 *
	 * @return the element at head, null if no element
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}
		return queue.element();
	}

        // Do NOT TOUCH THIS!!
	@Override
	public String toString() {
		String str = "queue: [";
		ListIterator<E> list = queue.listIterator(0);
		if (list != null) {
			while (list.hasNext()) {
				str += list.next();
				if (list.hasNext()) str += ",";
			}
		}
		str += "]";
		return str;
	}

}
