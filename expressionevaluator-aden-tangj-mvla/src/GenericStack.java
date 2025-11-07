import java.util.ArrayList;
import java.util.EmptyStackException;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericStack. Implements a generic software stack for any element
 * IMPORTANT: You ned to replace with YOUR GenericStack Implementation!!!
 *
 *
 * @param <E> the element type
 */
public class GenericStack<E>  {
	
	/** The stack.  The stack will be built on a generic ArrayList, but will only
	 *  expose stack methods push, pop, peek, isEmpty and getSize.
	 */
	private ArrayList<E> stack;
	
	/**
	 * Instantiates a new generic stack. The stack is empty at the beginning
	 */
	public GenericStack() {
		stack = new ArrayList<>();
	}
	
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
