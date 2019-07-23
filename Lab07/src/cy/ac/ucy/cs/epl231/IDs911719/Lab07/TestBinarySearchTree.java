package cy.ac.ucy.cs.epl231.IDs911719.Lab07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Random;

public class TestBinarySearchTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<Integer> testTree = new BinarySearchTree<>();
		Random r = new Random();

		int insertedV[] = new int[10];
		LinkedList<Integer> toCompareWith = new LinkedList<Integer>();
		// First Let's Do the Lab Example.
		testTree.insert(2);
		testTree.insert(1);
		testTree.insert(4);
		testTree.insert(3);
		testTree.insert(5);
		System.out.println("Tree height is: " + testTree.getHeight());
		System.out.println("Tree holds " + testTree.getSize() + " integers");
		testTree.preOrderTraversal();
		testTree.postOrderTraversal();
		testTree.inOrderTraversal();
		testTree.PrintTree();
		testTree.EulerTour();
		testTree.printLevelOrder();
		BinarySearchTree<String> testTreeABC = new BinarySearchTree<>();
		testTreeABC.insert("ABC");
		testTreeABC.insert("ABA");
		testTreeABC.insert("ABD");
		testTreeABC.insert("AAA");
		testTreeABC.insert("ABB");
		testTreeABC.PrintTree();
		testTreeABC.EulerTour();
		testTreeABC.printLevelOrder();
		System.out.println("Mul recursive: " + testTree.mul());
		System.out.println("Find 1 element: " + testTree.findi(1));
		System.out.println("Find 2 element: " + testTree.findi(2));
		System.out.println("Find 3 element: " + testTree.findi(3));
		System.out.println("Find 6 element: " + testTree.findi(6));
		try {
			System.out.println("Mul not recursive: " + testTree.mul2());
		} catch (QueueEmptyException | QueueFullException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Now let's do the Lab Exercise
		testTree = new BinarySearchTree<>();
		// *** ADD YOUR CODE HERE ***//

		System.out.println("Tree height is: " + testTree.getHeight());
		System.out.println("Tree holds " + testTree.getSize() + " integers");
		System.out.println("Tree values are in the range of [" + testTree.getMin() + ", " + testTree.getMax() + "]");

		System.out.println("Searching test");

		System.out.println("\t\tList\tTree\t(ms)");
		for (int i = 0; i < insertedV.length; i++) {
			int value = insertedV[i];
			long start1 = System.currentTimeMillis();

			if (!toCompareWith.contains(value)) {
				System.out.println("Error, " + value + " is not in the list");
			}

			long start2 = System.currentTimeMillis();
			if (!testTree.lookup(value)) {
				System.out.println("Error, " + value + " is not in the tree");
			}

			long end = System.currentTimeMillis();

			System.out.println(i + ":" + value + "\t" + (start2 - start1) + "\t" + (end - start2));
		}

		/*********
		 * Example Output *********************
		 * 
		 * Tree height is: 51 Tree holds 1000000 integers Tree values are in the range
		 * of [-2147483027, 2147479909] Searching test List Tree (ms) 0:635477680 0 0
		 * 1:508147331 3 0 2:1323424275 1 0 3:1218304360 1 0 4:-661096310 2 0
		 * 5:1109684098 2 0 6:-572626345 3 0 7:-1648424686 4 0 8:-164831302 4 0
		 * 9:-1408338345 5 0
		 ************************************************/

		// Write the Binary Tree to a file and read it back.
		try {
			FileOutputStream fos = new FileOutputStream("c:\\Temp\\t.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(testTree);
			System.out.println("Tree was written into file t.tmp");
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		// Read back the Binary Tree and perform some operations
		try {
			FileInputStream fis = new FileInputStream("c:\\Temp\\t.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			BinarySearchTree<Integer> bt = (BinarySearchTree) ois.readObject();
			ois.close();
			System.out.println("Tree was read from file t.tmp");
			System.out.println("Tree height is: " + bt.getHeight());
			System.out.println("Tree holds " + bt.getSize() + " integers");
			System.out.println("Tree values are in the range of [" + bt.getMin() + ", " + bt.getMax() + "]");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ee) {
			ee.printStackTrace();
		} catch (ClassNotFoundException eee) {
			eee.printStackTrace();
		}
	}

}
