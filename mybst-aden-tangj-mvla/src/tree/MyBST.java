package tree;

import java.util.LinkedList;
import java.util.Queue;

// TODO: Auto-generated Javadoc
/**
 * The Class MyBST.
 *
 * @param <E> the element type
 */
public class MyBST<E extends Comparable<E>> {
	
	/** The root of the BST. */
	private BSTNode<E> root;
	
	/** The size of the BST. */
	int size;
	
	/** The str order. */
	String strOrder;

	/**
	 * Instantiates a new MyBST .
	 */
	public MyBST() {
		size = 0;
		root =null;
	}
	
	// Part 1 - code and validate the insert and search methods
	
	/**
	 * Gets the root.
	 *
	 * @return the root node of the Binary Search Tree
	 */
	public BSTNode<E> getRoot() {
		return root;
	}

	
	/**
	 * Gets the size of the Binary Search Tree.
	 *
	 * @return the size of the Binary Search Tree
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Insert.
	 *
	 * @param e the element to insert into the BST
	 * @return true, if successful; false if e already exists in the BST
	 */
	public boolean insert(E e) {
		if (root == null) {
			root = new BSTNode<>(e,null);
			size++;
			return true;
		}
		BSTNode<E> node = root;
		while ((e.compareTo(node.getData()) < 0 && node.hasLeft())||
				(e.compareTo(node.getData()) > 0 && node.hasRight())) {
			if (e.compareTo(node.getData()) < 0) node = node.getLeftChild();
			else if (e.compareTo(node.getData()) > 0) node = node.getRightChild();
		}
		if (e.compareTo(node.getData()) < 0) node.addLeftChild(e);
		else if (e.compareTo(node.getData()) > 0) node.addRightChild(e);
		else return false;
		size++;
		return true;
	}
	
	/**
	 * Search the BST.
	 *
	 * @param e the element to search for
	 * @return true, if the element was found in the list...
	 */
	public boolean search(E e) {
		if (root == null) return false;
		BSTNode<E> node = root;
		while ((e.compareTo(node.getData()) < 0 && node.hasLeft())||
				(e.compareTo(node.getData()) > 0 && node.hasRight())) {
			if (e.compareTo(node.getData()) < 0) node = node.getLeftChild();
			else if (e.compareTo(node.getData()) > 0) node = node.getRightChild();
		}
		return node.getData().equals(e);
	}
	

	// Part 2: Pre/Post/In order traversals
	
	/**
	 * Debug method to dump the results of a traversal as a string
	 *
	 * @return the str order
	 */
	public String getStrOrder() {
		return(strOrder);
	}
	
	/**
	 * Preorder - traverse the BST using the preorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public void preorder() {
		strOrder = "";
		preorder(root);
	}
	
	/**
	 * Preorder traversal - process node, then left then right.
	 * Update strOrder with node.getData() when processed
	 *
	 * @param node the node
	 */
	private void preorder(BSTNode<E> node) {
		if (node != null) {
			strOrder += node.getData() + ",";
			if (node.hasLeft()) preorder(node.getLeftChild());
			if (node.hasRight()) preorder(node.getRightChild());
		}
	}

	/**
	 * Inorder - traverse the BST using the inorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public void inorder() {
		strOrder = "";
		inorder(root);
	}
	
	/**
	 * Inorder traversal - process left, node, then right
	 * Update strOrder with node.getData() when processed
	 *
	 * @param node the node being traversed
	 */
	private void inorder(BSTNode<E> node) {
		if (node != null) {
			if (node.hasLeft()) inorder(node.getLeftChild());
			strOrder += node.getData() + ",";
			if (node.hasRight()) inorder(node.getRightChild());
		}
	}
	
	/**
	 * Postorder - traverse the BST using the postorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public void postorder() {
		strOrder = "";
		postorder(root);
	}
	
	/**
	 * Postorder traversal - process left, then right then node.
	 * Update strOrder with node.getData() when processed
	 *
	 * @param node the node being traversed
	 */
	private void postorder(BSTNode<E> node) {
		if (node != null) {
			if (node.hasLeft()) postorder(node.getLeftChild());
			if (node.hasRight()) postorder(node.getRightChild());
			strOrder += node.getData() + ",";
		}
	}
	
	// Part 3: Level order Traversal and node removal

	/**
	 * Levelorder. Processes the nodes of a binary tree by level, starting at the root. 
	 * Note that this is not recursive. Update strOrder when a node is removed from the
	 * queue.
	 */
	public void levelorder() {
		strOrder = "";
		Queue <BSTNode <E>>levelQ = new LinkedList<>();
		levelQ.add(root);
		while (!levelQ.isEmpty()) {
			BSTNode <E> node = levelQ.poll();
			if (node != null) {
				strOrder += node.getData() + ",";
				if (node.hasLeft()) levelQ.add(node.getLeftChild());
				if (node.hasRight()) levelQ.add(node.getRightChild());
			}
		}
	}
	
	/**
	 * Returns the BSTNode whose data contains the given element.
	 *
	 * @param e the element to be matched
	 * @return the matching BSTNode if element was found; null otherwise.
	 */
	private BSTNode<E> getMatchingNode(E e) {
		if (root == null) return null;
		BSTNode<E> node = root;
		while ((e.compareTo(node.getData()) < 0 && node.hasLeft())||
				(e.compareTo(node.getData()) > 0 && node.hasRight())) {
			if (e.compareTo(node.getData()) < 0) node = node.getLeftChild();
			else if (e.compareTo(node.getData()) > 0) node = node.getRightChild();
		}
		if (node.getData().equals(e)) return node;
		return null;
	}

	/**
	 * Connect to parent node to the child node in both directions.
	 * Must handle the case where the parent is null - connect to root
	 * Must handle the case where the child is null and NOT attempt to
	 * set the parent of the child!
	 *
	 * @param left the element value used to determine if connecting child to
	 *          left or right branch of the parent
	 * @param parent the parent
	 * @param child the child
	 */
	private void connectToParent(boolean left, BSTNode<E> parent, BSTNode<E> child) {
		if (parent == null) {
			if (child != null) child.setParent(null);
			root = child;
		}
		else if (child == null) {
			if (left) parent.setLeftChild(null);
			else parent.setRightChild(null);
		}
		else if (left) parent.setLeftChild(child);
		else parent.setRightChild(child);
	}
	
	/**
	 * Finds left-most node in the right child of the specified node.
	 *
	 * @param node the node
	 * @return the BST node
	 */
	private BSTNode<E> findLeftMostNode(BSTNode<E> node) {
		while (node.hasLeft()) node = node.getLeftChild();
		return node;
	}

	/**
	 * Remove the BST node that contains the supplied element
	 *
	 * @param e the element to be searched for in the BST
	 * @return true if the element was found and deleted; false otherwise
	 */
	public boolean remove(E e) {
		boolean left = false;
		BSTNode<E> node = getMatchingNode(e);
		if (node == null) return false;
		if (node.getParent() != null) left = node.getParent().getData().compareTo(node.getData()) > 0;
		boolean hasLeft = node.hasLeft();
		boolean hasRight = node.hasRight();
		if (!hasLeft && !hasRight) connectToParent(left,node.getParent(),null);
		else if (!hasLeft) connectToParent(left,node.getParent(),node.getRightChild());
		else if (!hasRight) connectToParent(left,node.getParent(),node.getLeftChild());
		else {
			BSTNode<E> lmNode = findLeftMostNode(node.getRightChild());
			if (lmNode.equals(node.getRightChild())) connectToParent(left,lmNode.getParent(),lmNode.getRightChild());
			else connectToParent(true,lmNode.getParent(),lmNode.getRightChild());
			connectToParent(left,node.getParent(),lmNode);
			connectToParent(false, lmNode,node.getRightChild());
			connectToParent(true,lmNode,node.getLeftChild());
		}
		size--;
		return true;
	}
}



