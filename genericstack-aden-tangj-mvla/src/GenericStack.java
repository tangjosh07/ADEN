import java.util.ArrayList;
import java.util.EmptyStackException;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericStack.
 *
 * @param <E> the element type
 */

/**
 * @author Your name here
 *
 */
public class GenericStack<E> {

	/** Using an ArrayList as the data structure for the stack */
	private ArrayList<E> stack;
	
	/**
	 * Instantiates a new generic stack.
	 */
	public GenericStack() {
		stack = new ArrayList<E>();
	}
	/**
	 * You need to implement the following functions
	 * a) empty() - returns true if the element is empty
	 * b) size() - returns the size of the Stack
	 * c) peek() - returns the object that is at the top of the stack. Must throw
	 *             appropriate exception if attempt to peek empty stack
	 * d) pop() - gets the object at the top of stack, then removes it from 
	 *            the stack and returns the object. Must throw appropriate exception if
	 *            attempt to pop from empty stack.
	 * e) push(o) - adds the object to the top of stack/
	 * 
	 */
	
	/**
	 * 	 * To string
	 * 	 	 *
	 * 	 	 	 * @return the string
	 * 	 	 	 	 */
	
	/**
	 * Checks if stack is empty
	 *
	 * @return true if stack is emtpy, false if not
	 */
	public boolean empty() {
		return stack.isEmpty();
	}
	
	/**
	 * Gets size of the stack
	 *
	 * @return the integer size of the stack
	 */
	public int size() {
		return stack.size();
	}
	
	/**
	 * Returns object at the top of the stack
	 *
	 * @return the object at the top
	 */
	public E peek() {
		if (empty())
			throw new EmptyStackException();
		else
			return stack.get(stack.size()-1);
	}
	
	/**
	 * Removes and returns the item at the top of the stack
	 *
	 * @return the item at the top of the stack
	 */
	public E pop() {
		if (empty())
			throw new EmptyStackException();
		else
			return stack.remove(stack.size()-1);
	}
	
	/**
	 * Adds item to the top of the stack
	 *
	 * @param o is the object being added to the top
	 */
	public void push(E o) {
		stack.add(o);
	}
	
	@Override
   	public String toString() {
	   return("stack: "+stack.toString());
	}
}
