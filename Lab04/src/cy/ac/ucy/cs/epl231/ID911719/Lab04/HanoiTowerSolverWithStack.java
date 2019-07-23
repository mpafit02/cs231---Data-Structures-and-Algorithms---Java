package cy.ac.ucy.cs.epl231.ID911719.Lab04;

import java.util.Stack;

public class HanoiTowerSolverWithStack {
	// Function to implement legal movement between two poles
	private int currentBiggestDisk = 0;

	void moveDisksBetweenTwoPoles(Stack<Integer> src, Stack<Integer> dest, String s, String d) {
		int pole1TopDisk = (!src.empty()) ? (int) src.pop() : Integer.MIN_VALUE;
		int pole2TopDisk = (!dest.empty()) ? (int) dest.pop() : Integer.MIN_VALUE;
		// When pole 1 is empty move Disk from Dest. to Src.
		if (pole1TopDisk == Integer.MIN_VALUE) {
			src.push(pole2TopDisk);
			moveDisk(d, s, pole2TopDisk);
		}
		// When pole2 pole is empty move Disk from Src. to Dest.
		else if (pole2TopDisk == Integer.MIN_VALUE) {
			dest.push(pole1TopDisk);
			moveDisk(s, d, pole1TopDisk);
		}
		// When top disk of pole1 > top disk of pole2 move back pole1TopDisk and
		// move Disk from Dest to Src.
		else if (pole1TopDisk > pole2TopDisk) {
			src.push(pole1TopDisk);
			src.push(pole2TopDisk);
			moveDisk(d, s, pole2TopDisk);
		}
		// When top disk of pole1 < top disk of pole2 move back pole2TopDisk and
		// move Disk from Src to Dest.
		else {
			dest.push(pole2TopDisk);
			dest.push(pole1TopDisk);
			moveDisk(s, d, pole1TopDisk);
		}
	}

	// Function to show the movement of disks
	void moveDisk(String fromPeg, String toPeg, int disk) {
		if (disk > currentBiggestDisk) {
			currentBiggestDisk = disk;
			System.out.println("Move the disk [" + disk + "] from " + fromPeg + " to " + toPeg);
		} else
			System.out.println("Move the disk  " + disk + "  from " + fromPeg + " to " + toPeg);
	}

	// Function to implement TOH puzzle
	void tohIterative(int num_of_disks, Stack<Integer> src, Stack<Integer> aux, Stack<Integer> dest) {
		int i, total_num_of_moves;
		String s = "Src", d = "Dest", a = "Aux";

		// If number of disks is even, then interchange
		// destination pole and auxiliary pole
		if (num_of_disks % 2 == 0) {
			String temp = d;
			d = a;
			a = temp;
		}
		// Calculate the Number of Moves
		total_num_of_moves = (int) (Math.pow(2, num_of_disks) - 1);
		System.out
				.println("Algorthm move n-1 Disks From Src to Aux - move n to Dest - move n-1 disks from Auc to Dest");
		// Push all the Disked into src stack.
		// Larger disks are pushed first
		for (i = num_of_disks; i >= 1; i--)
			src.push(i);
		// ***********************************************//
		// Start of the Algorithm....
		for (i = 1; i <= total_num_of_moves; i++) {
			System.out.print("Move:" + i + ((i <= 9) ? " " : "") + "->[" + i % 3 + "]");
			if (i % 3 == 1) {
				moveDisksBetweenTwoPoles(src, dest, s, d);
			} else if (i % 3 == 2) {
				moveDisksBetweenTwoPoles(src, aux, s, a);
			} else if (i % 3 == 0) {
				moveDisksBetweenTwoPoles(aux, dest, a, d);
			}
		}
	}

	// Driver Program to test above functions
	public static void main(String[] args) {

		// Input: number of disks
		int num_of_disks = 5;

		HanoiTowerSolverWithStack hanoiTowerSolver = new HanoiTowerSolverWithStack();

		// Create three stacks of size 'num_of_disks'
		// to hold the disks
		Stack<Integer> src = new Stack<>();
		Stack<Integer> dest = new Stack<Integer>();
		Stack<Integer> aux = new Stack<Integer>();

		hanoiTowerSolver.tohIterative(num_of_disks, src, aux, dest);
	}
}
