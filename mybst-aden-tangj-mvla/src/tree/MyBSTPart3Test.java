package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class MyBSTPart3Test {
	MyBST<Integer> bst;
	private boolean status = false;
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
	void test_levelorder() {
		initTestBST();
		System.out.println("\nTesting levelorder traversal:");
		bst.levelorder();
		String results = bst.getStrOrder();
		System.out.println("   strOrder = "+results);
		assertTrue("20,10,30,5,15,25,42,7,12,28,36,44,".equals(results));
		
	}
	
	@Test
	@Order(2)
	void test_noremove() {
		initTestBST();
		int size = bst.getSize();

		System.out.println("\nTesting failed removal (item does not exist in tree):");
		System.out.println("   Delete node containing 27:");		
		status = bst.remove(27);
		assertFalse(status);
		assertTrue(bst.getSize() == (size));
		System.out.println("   Delete node containing 1:");		
		status = bst.remove(1);
		assertFalse(status);
		assertTrue(bst.getSize() == (size));	
	}

	@Test
	@Order(3)
	void test_deleteleaf() {
		initTestBST();
		int size = bst.getSize();
		String results = "";
		System.out.println("\nTesting delete of leaf node:");
		
		System.out.println("   Delete node containing 7:");		
		status = bst.remove(7);
		assertTrue(status);
		size --;
		assertTrue(bst.getSize() == (size));
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,10,5,15,12,30,25,28,42,36,44,".equals(results));
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,10,12,15,20,25,28,30,36,42,44,".equals(results));
		System.out.println("   Delete node containing 28:");		
		status = bst.remove(28);
		assertTrue(status);
		size--;
		assertTrue(bst.getSize() == (size));		
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,10,5,15,12,30,25,42,36,44,".equals(results));
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,10,12,15,20,25,30,36,42,44,".equals(results));
	}

	@Test
	@Order(4)
	void test_singlechild() {
		initTestBST();
		int size = bst.getSize();
		
		String results = "";
		System.out.println("\nTesting delete of node with single child:");
		System.out.println("   Delete node containing 25:");		
		status = bst.remove(25);
		assertTrue(status);
		size --;
		assertTrue(bst.getSize() == (size));		
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("preorder: "+results);
		assertTrue("20,10,5,7,15,12,30,28,42,36,44,".equals(results));
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println(" inorder: "+results);
		assertTrue("5,7,10,12,15,20,28,30,36,42,44,".equals(results));
		System.out.println("   Delete node containing 15:");		
		status = bst.remove(15);
		assertTrue(status);
		size--;
		assertTrue(bst.getSize() == (size));		
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("preorder: "+results);
		assertTrue("20,10,5,7,12,30,28,42,36,44,".equals(results));
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println(" inorder: "+results);
		assertTrue("5,7,10,12,20,28,30,36,42,44,".equals(results));
	}

	@Test
	@Order(5)
	void test_twochildren_wBSTInit() {
		initTestBST();
		int size = bst.getSize() -1;
		String results = "";
		System.out.println("\nTesting delete from dual children - reinitialize after each removal:");
		System.out.println("   Delete node containing 10:");		
		status = bst.remove(10);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,12,5,7,15,30,25,28,42,36,44,".equals(results));
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,12,15,20,25,28,30,36,42,44,".equals(results));
		initTestBST();
		assertTrue(bst.getSize() == (size+1));
		System.out.println("   Delete node containing 42:");		
		status = bst.remove(42);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,10,5,7,15,12,30,25,28,44,36,".equals(results));		
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,10,12,15,20,25,28,30,36,44,".equals(results));		
		initTestBST();
		assertTrue(bst.getSize() == (size+1));
		System.out.println("   Delete node containing 30:");		
		status = bst.remove(30);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,10,5,7,15,12,36,25,28,42,44,".equals(results));		
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,10,12,15,20,25,28,36,42,44,".equals(results));		
		initTestBST();
		assertTrue(bst.getSize() == (size+1));
		System.out.println("   Delete node containing 20 (root):");		
		status = bst.remove(20);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		assertTrue("25,10,5,7,15,12,30,28,42,36,44,".equals(results));	
		System.out.println("   preorder: "+results);
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,10,12,15,25,28,30,36,42,44,".equals(results));	
	}

	@Test
	@Order(6)
	void test_twochildren_woBSTInit() {
		initTestBST();
		int size = bst.getSize()-1;
		String results = "";
		System.out.println("\nTesting delete from dual children - reinsert deleted node:");
		System.out.println("   Delete node containing 20:");		
		status = bst.remove(10);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,12,5,7,15,30,25,28,42,36,44,".equals(results));	
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,12,15,20,25,28,30,36,42,44,".equals(results));	
		bst.insert(10);
		assertTrue(bst.getSize() == (size+1));
		System.out.println("   Delete node containing 42:");		
		status = bst.remove(42);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,12,5,7,10,15,30,25,28,44,36,".equals(results));	
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,10,12,15,20,25,28,30,36,44,".equals(results));	
		bst.insert(42);
		assertTrue(bst.getSize() == (size+1));
		System.out.println("   Delete node containing 30:");		
		status = bst.remove(30);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("20,12,5,7,10,15,36,25,28,44,42,".equals(results));	
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,10,12,15,20,25,28,36,42,44,".equals(results));	
		bst.insert(30);
		assertTrue(bst.getSize() == (size+1));
		System.out.println("   Delete node containing 20 (root):");			
		status = bst.remove(20);
		assertTrue(status);
		assertTrue(bst.getSize() == size);
		bst.preorder();
		results = bst.getStrOrder();
		System.out.println("   preorder: "+results);
		assertTrue("25,12,5,7,10,15,36,28,30,44,42,".equals(results));	
		bst.inorder();
		results = bst.getStrOrder();
		System.out.println("    inorder: "+results);
		assertTrue("5,7,10,12,15,25,28,30,36,42,44,".equals(results));	
	}

	@Test
	@Order(7)
	void test_postOrderDelete() {
		String[] exp = new String[] {"5,12,15,10,28,25,36,44,42,30,20,",
				"12,15,10,28,25,36,44,42,30,20,",
				"15,10,28,25,36,44,42,30,20,",
				"10,28,25,36,44,42,30,20,",
				"28,25,36,44,42,30,20,",
				"25,36,44,42,30,20,",
				"36,44,42,30,20,",
				"44,42,30,20,",
				"42,30,20,",
				"30,20,",
				"20,",
				""};
		initTestBST();
		int size = bst.getSize();
		bst.postorder();
		System.out.println("\nTesting deletion of entire bst using postorder:");
		String results = bst.getStrOrder();
		String[] elements = results.split(",");
		for (int i = 0; i < elements.length; i++) {
			System.out.println("   Deleting node containing: "+elements[i]);
			status = bst.remove(Integer.parseInt(elements[i]));
			assertTrue(status);
			size --;
			assertTrue(bst.getSize() == size);
			bst.postorder();
			results = bst.getStrOrder();
			System.out.println("   strOrder = "+results);
			assertTrue(results.equals(exp[i]));
			if (size == 0)
				break;
		}
	}

	@Test
	@Order(8)
	void test_inOrderDelete() {
		String[] exp = new String[] {"7,10,12,15,20,25,28,30,36,42,44,",
				"10,12,15,20,25,28,30,36,42,44,",
				"12,15,20,25,28,30,36,42,44,",
				"15,20,25,28,30,36,42,44,",
				"20,25,28,30,36,42,44,",
				"25,28,30,36,42,44,",
				"28,30,36,42,44,",
				"30,36,42,44,",
				"36,42,44,",
				"42,44,",
				"44,",
				""};
		initTestBST();
		int size = bst.getSize();
		bst.inorder();
		System.out.println("\nTesting deletion of entire bst using inorder:");
		String results = bst.getStrOrder();
		String[] elements = results.split(",");
		for (int i = 0; i < elements.length; i++) {
			System.out.println("   Deleting node containing: "+elements[i]);
			status = bst.remove(Integer.parseInt(elements[i]));
			assertTrue(status);			
			size --;
			assertTrue(bst.getSize() == size);
			bst.inorder();
			results = bst.getStrOrder();
			System.out.println("   strOrder = "+results);
			assertTrue(results.equals(exp[i]));
			if (size == 0)
				break;
		}
	}

	@Test
	@Order(9)
	void test_preOrderDelete() {
		String[] exp = new String[] {"25,10,5,7,15,12,30,28,42,36,44,",
				"25,12,5,7,15,30,28,42,36,44,",
				"25,12,7,15,30,28,42,36,44,",
				"25,12,15,30,28,42,36,44,",
				"25,12,30,28,42,36,44,",
				"25,30,28,42,36,44,",
				"25,36,28,42,44,",
				"36,28,42,44,",
				"36,42,44,",
				"36,44,",
				"44,",
				""};
		initTestBST();
		int size = bst.getSize();
		bst.preorder();
		System.out.println("\nTesting deletion of entire bst using preorder:");
		String results = bst.getStrOrder();
		String[] elements = results.split(",");
		for (int i = 0; i < elements.length; i++) {
			System.out.println("   Deleting node containing: "+elements[i]);
			status = bst.remove(Integer.parseInt(elements[i]));
			size --;
			assertTrue(bst.getSize() == size);
			bst.preorder();
			results = bst.getStrOrder();
			System.out.println("   strOrder = "+results);
			assertTrue(results.equals(exp[i]));
			if (size == 0)
				break;
		}
	}

	@Test
	@Order(10)
	void test_levelOrderDelete() {
		String[] exp = new String[] {"25,10,30,5,15,28,42,7,12,36,44,",
				"25,12,30,5,15,28,42,7,36,44,",
				"25,12,36,5,15,28,42,7,44,",
				"25,12,36,7,15,28,42,44,",
				"25,12,36,7,28,42,44,",
				"28,12,36,7,42,44,",
				"28,12,36,7,44,",
				"28,12,36,44,",
				"28,36,44,",
				"36,44,",
				"44,",
				""};


		initTestBST();
		int size = bst.getSize();
		bst.levelorder();
		System.out.println("\nTesting deletion of entire bst using levelorder:");
		String results = bst.getStrOrder();
		String[] elements = results.split(",");
		for (int i = 0; i < elements.length; i++) {
			System.out.println("   Deleting node containing: "+elements[i]);
			status = bst.remove(Integer.parseInt(elements[i]));
			assertTrue(status);
			size --;
			assertTrue(bst.getSize() == size);
			bst.levelorder();
			results = bst.getStrOrder();
			System.out.println("   strOrder = "+results);
			assertTrue(results.equals(exp[i]));
			if (size == 0)
				break;
		}
	}
	
}
