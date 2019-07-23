package cy.ac.ucy.cs.epl231.IDs911719.Lab07;

import java.io.Serializable;

// BinaryTree.java
public class BinarySearchTree<E extends Comparable<E>> implements Serializable {

	private static final long serialVersionUID = 1L;

	// Root node pointer. Will be null for an empty tree.
	private Node<E> root;

	/*
	 * --Node-- The binary tree is built using this nested node class. Each node
	 * stores one data element, and has left and right sub-tree pointer which may be
	 * null. The node is a "dumb" nested class -- we just use it for storage; it
	 * does not have any methods.
	 */
	public static class Node<E extends Comparable<E>> implements Serializable {
		private static final long serialVersionUID = 1L;
		Node<E> left;
		Node<E> right;
		E data;

		Node(E newData) {
			left = null;
			right = null;
			data = newData;
		}

		public String toString() {
			return data.toString();
		}
	}

	/**
	 * Creates an empty binary tree -- a null root pointer.
	 */
	public void BinaryTree() {
		root = null;
	}

	/**
	 * Returns true if the given target is in the binary tree. Uses a recursive
	 * helper.
	 */
	public boolean lookup(E data) {
		return (lookup(root, data));
	}

	/**
	 * Recursive lookup -- given a node, recur down searching for the given data.
	 */
	private boolean lookup(Node<E> node, E data) {
		if (node == null) {
			return (false);
		}

		if (data.compareTo(node.data) == 0) {
			return (true);
		} else if (data.compareTo(node.data) < 0) {
			return (lookup(node.left, data));
		} else {
			return (lookup(node.right, data));
		}
	}

	/**
	 * Inserts the given data into the binary tree. Uses a recursive helper.
	 */
	public void insert(E data) {
		// *** ADD YOUR CODE HERE ***//
		root = insert(root, data);
	}

	/**
	 * Recursive insert -- given a node pointer, recur down and insert the given
	 * data into the tree. Returns the new node pointer (the standard way to
	 * communicate a changed pointer back to the caller).
	 */
	private Node<E> insert(Node<E> node, E data) {
		// *** ADD YOUR CODE HERE ***//
		if (node == null) {
			node = new Node<E>(data);
		} else {
			if (data.compareTo(node.data) < 0) {
				node.left = insert(node.left, data);
			} else {
				node.right = insert(node.right, data);
			}
		}
		return (node);
	}

	///////////////////////////////////////
	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node<E> n) {
		if (n != null && n.left == null && n.right == null)
			return 0;
		return n == null ? 0 : 1 + Math.max(getHeight(n.left), getHeight(n.right));
		// OR null returns -1
		// return n == null ? -1 : 1 + Math.max( getHeight(n.left), getHeight(n.right)
		// );
	}

	public int getSize() {
		return getSize(root);
	}

	private int getSize(Node<E> n) {
		return (n == null) ? 0 : 1 + getSize(n.left) + getSize(n.right);
	}

	public E getMin() {
		return root == null ? null : getMin(root);
	}

	private E getMin(Node<E> n) {
		return (n.left == null) ? n.data : getMin(n.left);
	}

	public E getMax() {
		return root == null ? null : getMax(root);
	}

	private E getMax(Node<E> n) {
		return (n.right == null) ? n.data : getMax(n.right);
	}

	void PrintTree() {
		BTreePrinter btp = new BTreePrinter();
		btp.printNode(this.root);
	}

	void preOrderTraversal() {
		// *** ADD YOUR CODE HERE ***//
		System.out.println("PreOrder:");
		preOrderTraversal(this.root);
		System.out.println();
	}

	void preOrderTraversal(Node<E> n) {
		// *** ADD YOUR CODE HERE ***//
		if (n != null) {
			System.out.println(n);
			preOrderTraversal(n.left);
			preOrderTraversal(n.right);
		}
	}

	void inOrderTraversal() {
		System.out.println("InOrder:");
		inOrderTraversal(this.root);
		System.out.println();
	}

	void inOrderTraversal(Node<E> n) {
		// *** ADD YOUR CODE HERE ***//
		if (n != null) {
			inOrderTraversal(n.left);
			System.out.println(n);
			inOrderTraversal(n.right);
		}
	}

	void postOrderTraversal() {
		// *** ADD YOUR CODE HERE ***//
		System.out.println("PostOrder:");
		postOrderTraversal(this.root);
		System.out.println();
	}

	void postOrderTraversal(Node<E> n) {
		// *** ADD YOUR CODE HERE ***//
		if (n != null) {
			postOrderTraversal(n.left);
			postOrderTraversal(n.right);
			System.out.println(n);
		}
	}

	void EulerTour() {
		// *** ADD YOUR CODE HERE ***//
		System.out.println("EulerTour:\n");
		EulerTour(this.root);
		System.out.println();
	}

	void EulerTour(Node<E> n) {
		// *** ADD YOUR CODE HERE ***//
		if (n != null) {
			System.out.println("PreVisit:\t" + n);
			EulerTour(n.left);
			System.out.println("InVisit:\t" + n);
			EulerTour(n.right);
			System.out.println("PostVisit:\t" + n);
		}
	}

	Node<E> findi(int i) {
		Node<E> n = root;
		Queue<Node<E>> q = new ArrayQueue<>(100);
		try {
			q.enqueue(root);
		} catch (QueueFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 1;
		while (!q.isEmpty()) {
			try {
				n = q.dequeue();
			} catch (QueueEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (count == i) {
				return n;
			}
			count++;
			if (n.left != null || n.right != null) {
				try {
					q.enqueue(n.left);
					q.enqueue(n.right);
				} catch (QueueFullException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	int mul2() throws QueueEmptyException, QueueFullException {
		Node<E> n = root;
		int result = 1;
		Queue<Node<E>> q = new ArrayQueue<>(100);
		q.enqueue(root);
		while (!q.isEmpty()) {
			n = q.dequeue();
			if (n.left == null && n.right == null) {
				result *= (int) n.data;
			} else {
				q.enqueue(n.left);
				q.enqueue(n.right);
			}
		}
		return result;
	}

	int mul() {
		return mul(this.root);
	}

	int mul(Node<E> n) {
		if (n == null)
			return 1;
		if (n.left == null && n.right == null)
			return (int) n.data;
		return mul(n.right) * mul(n.left);
	}

	/*
	 * Given a binary tree. Print its nodes in level order using array for
	 * implementing queue
	 */
	void printLevelOrder() {
		try {
			Queue<Node<E>> queue = new ArrayQueue<>(100);
			queue.enqueue(root);
			// *** ADD YOUR CODE HERE ***//
			while (!queue.isEmpty()) {
				Node<E> node = queue.dequeue();
				System.out.print(node + " ");
				if (node.left != null) {
					queue.enqueue(node.left);
				}
				if (node.right != null) {
					queue.enqueue(node.right);
				}
			}
			System.out.println();
			System.out.println();
		} catch (QueueFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}