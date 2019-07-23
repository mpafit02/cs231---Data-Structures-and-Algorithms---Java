package cy.ac.ucy.cs.epl231.IDs911719.Lab09;

import java.util.Scanner;

public class TowersOfHanoi {
	private static long number_of_steps = 0;

	public static void main(String[] args) {
		// Create a Scanner
		Scanner input = new Scanner(System.in);
		System.out.print

		("Enter number of disks: ");
		int n = input.nextInt();
		// Find the solution recursively
		number_of_steps = 0;
		System.out.println("The moves are:");
		moveDisks(n, 'A', 'B', 'C');
		System.out.println("Number of Steps: " + number_of_steps);
	}

	/**
	 * The method for finding the solution to move n disks from fromTower to toTower
	 * with auxTower
	 */
	public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
		System.out.println("moveDisks(" + "n:" + n + " fromTower:" + fromTower + " toTower:" + toTower + " auxTower:"
				+ auxTower + ")");
		if (n == 1) { // stopping condition
			System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower);
		} else {
			moveDisks(n - 1, fromTower, auxTower, toTower);
			System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower);
			moveDisks(n - 1, auxTower, toTower, fromTower);
		}
		number_of_steps++;
	}
}
