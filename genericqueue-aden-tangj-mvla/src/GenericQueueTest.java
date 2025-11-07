import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;


// TODO: Auto-generated Javadoc
/**
 * The Class GenericQueueTest tests the implementation of the GenericQueue
 *
 * @author Scott
 */
@TestMethodOrder(OrderAnnotation.class)
class GenericQueueTest {
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 *
	 * @throws Exception the exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @param testInfo the new up
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp(TestInfo testInfo) throws Exception {
		System.out.println("Executing "+testInfo.getDisplayName());
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test empty queue. Checks that an EmptyQueue has a size of zero,
	 * isEmpty() returns true, and attempts to access using element(),
	 * remove(), poll() or pop() behave correctly.
	 */
	@Test
	@Order(1)
	void test_EmptyQueue() {
		GenericQueue<Integer> q = new GenericQueue<>();
		
		assertTrue(q.size() == 0);
		assertTrue(q.isEmpty());
		assertThrows(NoSuchElementException.class,() -> q.element());
		assertThrows(NoSuchElementException.class,() -> q.remove());
		
		assertNull(q.peek());
		assertNull(q.poll());
	}

	/**
	 * Test add queue. Add items to the queue, make sure that isEmpty() is false,
	 * and that the size increases by 1 for each addition. Check that the data is
	 * ordered properly using the q.toString();
	 */
	@Test
	@Order(2)
	void test_AddQueue() {
		GenericQueue<String> q = new GenericQueue<>();
		String data = "This is a test of the emergency queue system";
		String[] dary = data.split("\\s+");
		int i = 1;
		for (String d : dary) {
			assertTrue(q.add(d));
			assertFalse(q.isEmpty());
			assertTrue(q.size() == i);
			i++;
		}
		String match = "queue: ["+String.join(",",dary)+"]";
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == dary.length);
	}

	/**
	 * Test offer queue. Offer items to the queue, make sure that isEmpty() is false,
	 * and that the size increases by 1 for each addition. Check that the data is
	 * ordered properly using the q.toString();
	 */
	@Test
	@Order(3)
	void test_OfferQueue() {
		GenericQueue<String> q = new GenericQueue<>();
		String data = "This is a test of the emergency queue system";
		String[] dary = data.split("\\s+");
		int i = 1;
		for (String d : dary) {
			assertTrue(q.offer(d));
			assertFalse(q.isEmpty());
			assertTrue(q.size() == i);
			i++;
		}
		String match = "queue: ["+String.join(",",dary)+"]";
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == dary.length);
	}

	/**
	 * Test add full queue. Fill the queue using add(), and then check that 
	 * the data order matches. Try to add another element, and ensure that
	 * the response is appropriate. Check that the queue remains unchanged
	 */
	@Test
	@Order(4)
	void test_AddFullQueue() {
		GenericQueue<Integer> q = new GenericQueue<>();
		int[] data = new int[20];
		Arrays.setAll(data,i->20-i); // initialize array to the numbers 20 to 1
		for (int d : data) {
			assertTrue(q.add(d));
			assertFalse(q.isEmpty());
		}
		
		String dataStr = String.join(",",Arrays.toString(data));
		dataStr = dataStr.replaceAll("\\s+","");
		String match = "queue: "+dataStr;
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == data.length);
		
		assertThrows(IllegalStateException.class,() -> q.add(-1));
		// make sure that the queue was not changed....
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == data.length);
	}

	/**
	 * Test offer full queue. Fill the quque using offer(), and then check that 
	 * the data order matches. Try to add another element, and ensure that
	 * the response is appropriate. Check that the queue remains unchanged
	 */
	@Test
	@Order(5)
	void test_OfferFullQueue() {
		GenericQueue<Integer> q = new GenericQueue<>();
		int[] data = new int[20];
		Arrays.setAll(data,i->20-i); // initialize array to the numbers 20 to 1
		for (int d : data) {
			assertTrue(q.add(d));
			assertFalse(q.isEmpty());
		}
		
		String dataStr = String.join(",",Arrays.toString(data));
		dataStr = dataStr.replaceAll("\\s+","");
		String match = "queue: "+dataStr;
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == data.length);
		assertFalse(q.offer(-1));
		// make sure that the queue was not changed....
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == data.length);
		
	}

	/**
	 * Test the non-default Constructor (use a size different than 20). 
	 * Model this after test_OfferFullQueue with a size of your choosing.
	 * Fill the queue using offer(), and then check that
	 * the data order matches. Try to add another element, and ensure that
	 * the response is appropriate. Check that the queue remains unchanged.
	 */
	@Test
	@Order(6)
	void test_nonDefaultConstructor() {
		GenericQueue<Integer> q = new GenericQueue<>(10);
		int[] data = new int[10];
		Arrays.setAll(data,i->10-i); // initialize array to the numbers 10 to 1
		for (int d : data) {
			assertTrue(q.add(d));
			assertFalse(q.isEmpty());
		}
		
		String dataStr = String.join(",",Arrays.toString(data));
		dataStr = dataStr.replaceAll("\\s+","");
		String match = "queue: "+dataStr;
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == data.length);
		assertFalse(q.offer(-1));
		// make sure that the queue was not changed....
		assertTrue(match.equals(q.toString()));
		assertTrue(q.size() == data.length);
	}

	/**
	 * Test remove queue. Fill the queue, then remove() one item at a time, 
	 * validating that the item removed is the head, and that the size of the 
	 * queue decreases by 1 each time. Ensure that the queue is empty after all
	 * items have been removed
	 */
	@Test
	@Order(7)
	void test_RemoveQueue() {
		GenericQueue<String> q = new GenericQueue<>();
		Integer[] data = new Integer[20];
		Arrays.setAll(data,i->20-i); // initialize array to the numbers 20 to 1
		
		for (Integer d : data) {
			assertTrue(q.add(d.toString()));
			assertFalse(q.isEmpty());
		}
		
		for (int i = 0; i < data.length;i++) {
			assertTrue(data[i].toString().equals(q.remove()));
			assertTrue((data.length - (i+1)) == q.size());
		}
		assertTrue(q.isEmpty());		
	}

	/**
	 * Test poll queue.
	 * Test poll queue. Fill the queue, then poll() one item at a time, 
	 * validating that the item removed is the head, and that the size of the 
	 * queue decreases by 1 each time. Ensure that the queue is empty after all
	 * items have been removed
	 */
	@Test
	@Order(8)
	void test_PollQueue() {
		GenericQueue<String> q = new GenericQueue<>();
		Integer[] data = new Integer[20];
		Arrays.setAll(data,i->20-i); // initialize array to the numbers 20 to 1
		
		for (Integer d : data) {
			assertTrue(q.add(d.toString()));
			assertFalse(q.isEmpty());
		}
		
		for (int i = 0; i < data.length;i++) {
			assertTrue(data[i].toString().equals(q.poll()));
			assertTrue((data.length - (i+1)) == q.size());
		}
		assertTrue(q.isEmpty());		
	}

	/**
	 * Test element queue. Fill the array based on an integer array. In a loop,
	 * check that element() returns the element at the head of the queue without 
	 * removing it, then remove the item at the head of the queue, and check that 
	 * element() returns the next item. Ensure that the queue is not empty and has a
	 * size of 1.
	 */
	@Test
	@Order(9)
	void test_ElementQueue() {
		GenericQueue<String> q = new GenericQueue<>();
		Integer[] data = new Integer[20];
		Arrays.setAll(data,i->20-i); // initialize array to the numbers 20 to 1
		
		for (Integer d : data) {
			assertTrue(q.add(d.toString()));
			assertFalse(q.isEmpty());
		}
		
		for (int i = 1; i < data.length;i++) {
			assertFalse(data[i].toString().equals(q.element()));  // this should be the head of the queue...
			assertTrue(data[i-1].toString().equals(q.element()));  // this should be the head of the queue...
			assertTrue((data.length - (i-1)) == q.size());
			q.remove();   // remove the item from the head of queue 
			assertTrue(data[i].toString().equals(q.element()));  // this should be the head of the queue...
			assertTrue((data.length - i) == q.size());
		}
		assertFalse(q.isEmpty());
		assertTrue(q.size() == 1);
	}

	/**
	 * Test peek queue. Fill the array based on an integer array. In a loop,
	 * check that peek() returns the element at the head of the queue without 
	 * removing it, then remove the item at the head of the queue, and check that 
	 * peek() returns the next item. Ensure that the queue is not empty and has a
	 * size of 1.
	 */
	@Test
	@Order(10)
	void test_PeekQueue() {
		GenericQueue<String> q = new GenericQueue<>();
		Integer[] data = new Integer[20];
		Arrays.setAll(data,i->20-i); // initialize array to the numbers 20 to 1
		
		for (Integer d : data) {
			assertTrue(q.add(d.toString()));
			assertFalse(q.isEmpty());
		}
		
		for (int i = 1; i < data.length;i++) {
			assertFalse(data[i].toString().equals(q.peek()));  // this should be the head of the queue...
			assertTrue(data[i-1].toString().equals(q.peek()));  // this should be the head of the queue...
			assertTrue((data.length - (i-1)) == q.size());
			q.remove();   // remove the item from the head of queue 
			assertTrue(data[i].toString().equals(q.peek()));  // this should be the head of the queue...
			assertTrue((data.length - i) == q.size());
		}
		assertFalse(q.isEmpty());
		assertTrue(q.size() == 1);
	}

}
