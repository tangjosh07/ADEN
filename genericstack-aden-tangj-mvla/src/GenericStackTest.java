import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.EmptyStackException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author Your name here
 *
 */
@TestMethodOrder(OrderAnnotation.class)
class GenericStackTest {
	private String[] reverse = {"z","y","x","w","v","u","t","s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a"};
	private String[] stuff = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	/**
	 * Tests proper behavior of empty() and size() methods when the stack actually is empty.
	 */
	@Test
	@Order(1)
	void test_EmptyStack() {
		System.out.println("Testing empty and size methods of empty stack");
		GenericStack<String> stack = new GenericStack<String>();
		assertTrue(stack.empty());
		assertEquals(0,stack.size());
		System.out.println("PASSED");
	}
	
	/**
	 * Test that stack exceptions are appropriately thrown by peek and pop methods.
	 */
	@Test
	@Order(2)
	void test_StackExceptions() {
		System.out.println("Testing stack exceptions properly thrown by peek and pop methods");
		GenericStack<String> stack = new GenericStack<String>();
		assertTrue(stack.empty());
		assertEquals(0,stack.size());
		assertThrows(EmptyStackException.class,()->stack.peek());
		assertThrows(EmptyStackException.class,()->stack.pop());
		System.out.println("PASSED");
	}
	
	/**
	 * Test push and peek methods. Must actually check:
	 * a) size(), empty() behave correctly
	 * b) data is correctly stored in LIFO order.
	 */
	@Test
	@Order(3)
	void test_PushPeek() {	
		System.out.println("Testing push and peek methods");
		GenericStack<String> stack = new GenericStack<String>();
		assertTrue(stack.empty());
		assertEquals(0,stack.size());
		for (String s: stuff) {
			stack.push(s);
			assertEquals(s,stack.peek());
		}
		assertFalse(stack.empty());
		assertEquals(stuff.length,stack.size());
		assertEquals("stack: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",stack.toString());
		System.out.println("PASSED");
	}

	/**
	 * Test pop - tests that 
	 * a)  data is retrieved from the stack in LIFO order, and
	 * b)  that size(), empty() behave as expected.
	 */
	@Test
	@Order(4)
	void test_Pop() {
		System.out.println("Testing Pop method");
		GenericStack<String> stack = new GenericStack<String>();
		assertTrue(stack.empty());
		assertEquals(0,stack.size());
		for (String s: stuff) {
			stack.push(s);
		}
		assertFalse(stack.empty());
		assertEquals(stuff.length,stack.size());
		assertEquals("stack: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",stack.toString());
		for (String s: reverse) {
			assertEquals(s,stack.pop());
		}
		assertTrue(stack.empty());
		assertEquals(0,stack.size());
		System.out.println("PASSED");
	}

}
