package MyLinkedList;
import java.util.AbstractList;

/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team/Scott Murray
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	// declared as final due because the head and tail DO NOT CHANGE!!!
	final LLNode<E> head;
	final LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// Must construct head and tail pointers due to final declaration.
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		// TODO: Finish implementing this method
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add. 
	 * @return true if the add was successful (which it should always be)
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> node = new LLNode<>(element);
		tail.prev.next = node;
		node.next = tail;
		node.prev = tail.prev;
		tail.prev = node;
		size++;
		return true;
	}

	/**
	 * Returns the node at position specified by index.
	 * Challenge: Can you take advantage of the doubly-linked lists to
	 * improve performance? NOTE - this does not throw an exception as it REQUIRES
	 * that the index be between 0 and size()-1.
	 *
	 * @param index the index of the node to access.
	 * @return the node at the specified index
	 */
	private LLNode<E> getNodeAtIndex(int index) {
		// TODO: Implement this method
		LLNode<E> node;
		if (index < size/2) {
			node = head;
			for (int i = 0; i < index+1;i++) {
				node = node.next;
			}
		}
		else {
			node = tail;
			for (int i = size - 1; i > index-1; i--) {
				node = node.prev;
			}
		}
		return node;
	}

	/** Get the element at position index 
	 * 
	 * @param  index  the index of the node to access
	 * @return the data element associated with this node.
	 * @throws IndexOutOfBoundsException if the index is out of bounds. 
	 */
	public E get(int index) throws IndexOutOfBoundsException 
	{
		// TODO: Implement this method
		if (index < 0 || index > size-1) throw new IndexOutOfBoundsException();

		return getNodeAtIndex(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException 
	{
		// TODO: Implement this method
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		LLNode<E> node = getNodeAtIndex(index);
		LLNode<E> newNode = new LLNode<E>(element, node.prev,node);
		node.prev.next = newNode;
		node.prev = newNode;
		size++;
	}


	/** Return the size of the list
	 * 
	 *  @return size of the linked list 
	 */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed from the list
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException 
	{
		// TODO: Implement this method
		if (index < 0 || index > size-1) throw new IndexOutOfBoundsException();
		LLNode<E> node = getNodeAtIndex(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
		size--;
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException 
	{
		// TODO: Implement this method
		if (index < 0 || index > size-1) throw new IndexOutOfBoundsException();
		LLNode<E> node = getNodeAtIndex(index);
		E prev = node.data;
		node.data = element;
		return prev;
	}   
}

