package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class MyBSTPart2Test {
	MyBST<Integer> bst;
	
	private void initTestBST() {
		bst = new MyBST<>();
		bst.insert(20);
		bst.insert(10);
		bst.insert(5);
		bst.insert(15);
		bst.insert(7);
		bst.insert(12);
		bst.insert(30);
		bst.insert(25);
		bst.insert(28);
		bst.insert(42);
		bst.insert(36);
		bst.insert(44);
	
	}
	@Test
	@Order(1)
	void test_preorder() {
		initTestBST();
		System.out.println("\nTesting preorder traversal:");
		bst.preorder();
		String results = bst.getStrOrder();
		System.out.println("   strOrder = "+results);
		assertTrue("20,10,5,7,15,12,30,25,28,42,36,44,".equals(results));
		
	}

	@Test
	@Order(2)
	void test_inorder() {
		initTestBST();
		System.out.println("\nTesting inorder traversal:");
		bst.inorder();
		String results = bst.getStrOrder();
		System.out.println("   strOrder = "+results);
		assertTrue("5,7,10,12,15,20,25,28,30,36,42,44,".equals(results));
	}

	@Test
	@Order(3)
	void test_postorder() {
		initTestBST();
		System.out.println("\nTesting postorder traversal:");
		bst.postorder();
		String results = bst.getStrOrder();
		System.out.println("   strOrder = "+results);
		assertTrue("7,5,12,15,10,28,25,36,44,42,30,20,".equals(results));

	}
}
