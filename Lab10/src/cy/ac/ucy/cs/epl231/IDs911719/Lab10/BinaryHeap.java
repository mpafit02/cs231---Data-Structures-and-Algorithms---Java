package cy.ac.ucy.cs.epl231.IDs911719.Lab10;

import java.util.Arrays;
import java.util.Scanner;

// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap. Note that all "matching" is based on the compareTo
 * method.
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	/**
	 * Construct the binary heap.
	 */
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Construct the binary heap.
	 * 
	 * @param capacity the capacity of the binary heap.
	 */
	public BinaryHeap(int capacity) {
		currentSize = 0;
		array = (AnyType[]) new Comparable[capacity + 1];
	}

	/**
	 * Construct the binary heap given an array of items.
	 */
	public BinaryHeap(AnyType[] items) {
		currentSize = items.length;
		array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

		int i = 1;
		for (AnyType item : items)
			array[i++] = item;
		buildHeap();
	}

	/**
	 * Insert into the priority queue, maintaining heap order. Duplicates are
	 * allowed.
	 * 
	 * @param x the item to insert.
	 */
	public void insert(AnyType x) {
		System.out.println("Inserting:" + x);
		if (currentSize == array.length - 1)
			enlargeArray(array.length * 2 + 1);

		// Percolate up stating from the last element of the array
		int hole = ++currentSize;
		for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;
		// printHeap();
	}

	private void enlargeArray(int newSize) {
		AnyType[] old = array;
		array = (AnyType[]) new Comparable[newSize];
		for (int i = 0; i < old.length; i++)
			array[i] = old[i];
	}

	/**
	 * Find the smallest item in the priority queue.
	 * 
	 * @return the smallest item, or throw an UnderflowException if empty.
	 * @throws UnderflowException
	 */
	public AnyType findMin() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();
		return array[1];
	}

	/**
	 * Remove the smallest item from the priority queue.
	 * 
	 * @return the smallest item, or throw an UnderflowException if empty.
	 * @throws UnderflowException
	 */
	public AnyType deleteMin() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();

		AnyType minItem = findMin();
		// Move the last element of the array in the position of the
		// deleted minimum element and then percolateDown that element
		array[1] = array[currentSize--];
		percolateDown(1);

		return minItem;
	}

	/**
	 * Establish heap order property from an arbitrary arrangement of items. Runs in
	 * linear time.
	 */
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	/**
	 * Test if the priority queue is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Make the priority queue logically empty.
	 */
	public void makeEmpty() {
		currentSize = 0;
	}

	private static final int DEFAULT_CAPACITY = 10;

	private int currentSize; // Number of elements in heap
	private AnyType[] array; // The heap array

	/**
	 * Internal method to percolate down in the heap.
	 * 
	 * @param hole the index at which the percolate begins.
	 */
	private void percolateDown(int hole) {
		int child;
		AnyType temp = array[hole];
		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child < currentSize && array[child + 1].compareTo(array[child]) < 0) {
				child++;
			}
			if (temp.compareTo(array[child]) >= 0) {
				array[hole] = array[child];
			} else {
				break;
			}
		}
		array[hole] = temp;
	}

	public void printHeap() {
		for (int i = 1; i <= currentSize; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
	}

	public void dump() {
		int height = log2(currentSize) + 1;

		for (int i = 1; i <= currentSize; i++) {
			int x = (int) array[i];
			int level = log2(i) + 1;
			int spaces = (height - level + 1) * 2;
			if (i % 2 == 1 && i != 1)
				System.out.print(stringOfSize(spaces, '-'));
			else
				System.out.print(stringOfSize(spaces, ' '));
			System.out.print(x);
			if ((int) Math.pow(2, level) - 1 == i)
				System.out.println("");
		}
		System.out.println();
	}

	private String stringOfSize(int size, char ch) {
		char[] a = new char[size];
		Arrays.fill(a, ch);
		return new String(a);
	}

	// log with base 2
	private int log2(int x) {
		return (int) (Math.log(x) / Math.log(2)); // = log(x) with base 10 / log(2) with base 10
	}

	// ==================================================================
	void MaxHeapify(int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int largest = i;
		if (l <= currentSize && array[l].compareTo(array[largest]) > 0) {
			largest = l;
		} 
		if (r <= currentSize && array[r].compareTo(array[largest]) > 0) {
			largest = r;
		}
		if (largest != i) {
			// Swap
			AnyType temp = array[i];
			array[i] = array[largest];
			array[largest] = temp;
			MaxHeapify(largest);
		}
	}

	void convertToMaxHeap() {
		for (int i = currentSize / 2; i > 0; --i) {
			MaxHeapify(i);
		}
	}

	// ==================================================================
	void MinHeapify(int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int largest = i;
		if (l <= currentSize && array[l].compareTo(array[largest]) < 0) {
			largest = l;
		} 
		if (r <= currentSize && array[r].compareTo(array[largest]) < 0) {
			largest = r;
		}
		if (largest != i) {
			// Swap
			AnyType temp = array[i];
			array[i] = array[largest];
			array[largest] = temp;
			MinHeapify(largest);
		}
	}

	void convertToMinHeap() {
		for (int i = currentSize / 2; i > 0; --i) {
			MinHeapify(i);
		}
	}

	// ==================================================================
	// Test program
	public static void main(String[] args) {
		BinaryHeap<Integer> h = new BinaryHeap<Integer>();
		int n = 1;
		try {
			h.insert(10);
			h.insert(9);
			h.insert(8);
			h.insert(7);
			h.insert(6);
			h.insert(5);
			h.insert(4);
			h.insert(3);
			h.insert(2);
			h.insert(1);
			h.insert(0);
			h.printHeap();
			h.dump();
			h.deleteMin();
			h.printHeap();
			h.dump();
			h.convertToMaxHeap();
			h.printHeap();
			h.dump();
			h.convertToMinHeap();
			h.printHeap();
			h.dump();
//			Scanner input = new Scanner(System.in);
//			System.out.print("\nEnter value to Add (0:exit): ");
//			while (n > 0) {
//				n = input.nextInt();
//				h.insert(n);
//				System.out.print("Enter value to Add: ");
//			}
		} catch (UnderflowException e) {
			e.printStackTrace();
		}
	}
}

class UnderflowException extends Exception {
	UnderflowException() {
		super("UnderflowException");
	}
}
