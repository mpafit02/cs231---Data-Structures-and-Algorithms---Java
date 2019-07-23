package cy.ac.ucy.cs.epl231.IDs911719.Lab06;

/**
 * A class that contains several sorting routines, implemented as static
 * methods. Arrays are rearranged with smallest item first, using compareTo.
 * 
 */
public final class Sort {
	/**
	 * Simple insertion sort.
	 * 
	 * @param a an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
		int j;
		for (int i = 1; i < a.length; i++) {
			AnyType temp = a[i];
			for (j = i; j > 0 && temp.compareTo(a[j - 1]) < 0; j--) {
				a[j] = a[j - 1];
			}
			a[j] = temp;
		}
		/** Add Your Code HERE **/
	}

	/**
	 * Shellsort, using Shell's (poor) increments.
	 * 
	 * @param a an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void shellsort(AnyType[] a) {
		int j;
		// https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html
		/** Add Your Code HERE **/
	}

	/**
	 * Internal method for heapsort.
	 * 
	 * @param i the index of an item in the heap.
	 * @return the index of the left child.
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Internal method for heapsort that is used in deleteMax and buildHeap.
	 * 
	 * @param a an array of Comparable items.
	 * @index i the position from which to percolate down.
	 * @int n the logical size of the binary heap.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int n) {
		int child;
		AnyType tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
				child++;
			if (tmp.compareTo(a[child]) < 0)
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}

	/**
	 * Standard heapsort.
	 * 
	 * @param a an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void heapsort(AnyType[] a) {
		for (int i = a.length / 2 - 1; i >= 0; i--) /* buildHeap */
			percDown(a, i, a.length);
		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i); /* deleteMax */
			percDown(a, 0, i);
		}
	}

	/**
	 * Mergesort algorithm.
	 * 
	 * @param a an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
		AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];

		mergeSort(a, tmpArray, 0, a.length - 1);
	}

	/**
	 * Internal method that makes recursive calls.
	 * 
	 * @param a        an array of Comparable items.
	 * @param tmpArray an array to place the merged result.
	 * @param left     the left-most index of the subarray.
	 * @param right    the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray,
			int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * 
	 * @param a        an array of Comparable items.
	 * @param tmpArray an array to place the merged result.
	 * @param leftPos  the left-most index of the subarray.
	 * @param rightPos the index of the start of the second half.
	 * @param rightEnd the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] tmpArray,
			int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos].compareTo(a[rightPos]) <= 0)
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos++];

		while (leftPos <= leftEnd) // Copy rest of first half
			tmpArray[tmpPos++] = a[leftPos++];

		while (rightPos <= rightEnd) // Copy rest of right half
			tmpArray[tmpPos++] = a[rightPos++];

		// Copy tmpArray back
		for (int i = 0; i < numElements; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}

	/**
	 * Quicksort algorithm.
	 * 
	 * @param a an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static final int CUTOFF = 3;

	/**
	 * Method to swap to elements in an array.
	 * 
	 * @param a      an array of objects.
	 * @param index1 the index of the first object.
	 * @param index2 the index of the second object.
	 */
	public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
		AnyType tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	/**
	 * Return median of left, center, and right. Order these and hide the pivot.
	 */
	private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0)
			swapReferences(a, left, center);
		if (a[right].compareTo(a[left]) < 0)
			swapReferences(a, left, right);
		if (a[right].compareTo(a[center]) < 0)
			swapReferences(a, center, right);

		// Place pivot at position right - 1
		swapReferences(a, center, right - 1);
		return a[right - 1];
	}

	/**
	 * Internal quicksort method that makes recursive calls. Uses median-of-three
	 * partitioning and a cutoff of 10.
	 * 
	 * @param a     an array of Comparable items.
	 * @param left  the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int left, int right) {
		if (left + CUTOFF <= right) {
			AnyType pivot = median3(a, left, right);

			// Begin partitioning
			int i = left, j = right - 1;
//			for (;;) {
//				while (a[++i].compareTo(pivot) < 0) {
//
//				}
//				while (a[--j].compareTo(pivot) > 0) {
//
//				}
//				if (i < j) {
//					//quicksort(a, i, j);
//				}
//			}
			swapReferences(a, i, right - 1); // Restore pivot

			/** Add Your Code HERE **/ // Sort small elements
			/** Add Your Code HERE **/ // Sort large elements
		} else // Do an insertion sort on the subarray
			insertionSort(a, left, right);
	}

	/**
	 * Internal insertion sort routine for subarrays that is used by quicksort.
	 * 
	 * @param a     an array of Comparable items.
	 * @param left  the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
		for (int p = left + 1; p <= right; p++) {
			AnyType tmp = a[p];
			int j;

			for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	/**
	 * Quick selection algorithm. Places the kth smallest item in a[k-1].
	 * 
	 * @param a an array of Comparable items.
	 * @param k the desired rank (1 is minimum) in the entire array.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int k) {
		quickSelect(a, 0, a.length - 1, k);
	}

	/**
	 * Internal selection method that makes recursive calls. Uses median-of-three
	 * partitioning and a cutoff of 10. Places the kth smallest item in a[k-1].
	 * 
	 * @param a     an array of Comparable items.
	 * @param left  the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 * @param k     the desired index (1 is minimum) in the entire array.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int left, int right,
			int k) {
		if (left + CUTOFF <= right) {
			AnyType pivot = median3(a, left, right);

			// Begin partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}

			swapReferences(a, i, right - 1); // Restore pivot

			if (k <= i)
				quickSelect(a, left, i - 1, k);
			else if (k > i + 1)
				quickSelect(a, i + 1, right, k);
		} else // Do an insertion sort on the subarray
			insertionSort(a, left, right);
	}

	private static void checkSort(Integer[] a) {
		for (int i = 0; i < a.length; i++)
			if (a[i] != i)
				System.out.println("Error at " + i);
		System.out.println("Finished checksort");
	}

	/***************************************************************/
	public static <AnyType extends Comparable<? super AnyType>> void oddEvenMergeSort(AnyType[] a) {
		oddEvenMergeSort(a, 0, a.length - 1);
	}

	/**
	 * Internal method that makes recursive calls.
	 * 
	 * @param a        an array of Comparable items.
	 * @param tmpArray an array to place the merged result.
	 * @param left     the left-most index of the subarray.
	 * @param right    the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void oddEvenMergeSort(AnyType[] a, int left,
			int right) {
		if (left < right) {
			int center = (left + right) / 2;
			/** Add Your Code HERE **/
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * 
	 * @param a        an array of Comparable items.
	 * @param tmpArray an array to place the merged result.
	 * @param leftPos  the left-most index of the subarray.
	 * @param n        the size of the subarray.
	 * @param r        is the distance of the elements to be compared.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void oddEvenMerge(AnyType[] a, int leftPos, int n,
			int r) {
		int m = r * 2;
		// System.out.print("oddEvenMerge(a,"+leftPos+","+n+","+r+")");
		// for (int i = leftPos; i < leftPos+n && i<a.length; i++)
		// System.out.print(a[i]+" ");
		// System.out.println();
		if (m < n) {
			oddEvenMerge(a, leftPos, n, m); // even subsequence
			oddEvenMerge(a, leftPos + r, n, m); // odd subsequence
			System.out.println("oddEvenCompare->From:" + leftPos + " To:" + (leftPos + n) + " Step:" + r);
			for (int i = leftPos; i + r <= leftPos + n; i += r) {
				oddEvenCompare(a, i, i + r);
			}
		} else {
			System.out.println("oddEvenCompare->From:" + leftPos + " To:" + (leftPos + r) + " Step:" + 1);
			oddEvenCompare(a, leftPos, leftPos + r);
		}
	}

	private static <AnyType extends Comparable<? super AnyType>> void oddEvenCompare(AnyType[] a, int i, int j) {
		if (a[i].compareTo(a[j]) > 0)
			swapReferences(a, i, j);
	}

	// Needs to be power of 2 for oddEvenMergeSort
	private static final int NUM_ITEMS = 100; // 8192=1024*8

	public static void main(String[] args) {
		Integer[] a = new Integer[NUM_ITEMS];
		long start;
		for (int i = 0; i < a.length; i++)
			a[i] = i;

		Random.permute(a);
		start = System.currentTimeMillis();
		Sort.insertionSort(a);
		System.out.println("Sort.insertionSort(a):[" + (System.currentTimeMillis() - start) + "mSec]");
		checkSort(a);

		Random.permute(a);
		start = System.currentTimeMillis();
		Sort.heapsort(a);
		System.out.println("Sort.heapsort(a):[" + (System.currentTimeMillis() - start) + "mSec]");
		checkSort(a);

		Random.permute(a);
		start = System.currentTimeMillis();
		Sort.shellsort(a);
		System.out.println("Sort.shellsort(a):[" + (System.currentTimeMillis() - start) + "mSec]");
		checkSort(a);

		Random.permute(a);
		start = System.currentTimeMillis();
		Sort.mergeSort(a);
		System.out.println("Sort.mergeSort(a):[" + (System.currentTimeMillis() - start) + "mSec]");
		checkSort(a);

		Random.permute(a);
		start = System.currentTimeMillis();
		Sort.quicksort(a);
		System.out.println("Sort.quicksort(a):[" + (System.currentTimeMillis() - start) + "mSec]");
		checkSort(a);

		Random.permute(a);
		start = System.currentTimeMillis();
		Sort.oddEvenMergeSort(a);
		System.out.println("Sort.oddEvenMergeSort(a):[" + (System.currentTimeMillis() - start) + "mSec]");
		checkSort(a);
	}
}
