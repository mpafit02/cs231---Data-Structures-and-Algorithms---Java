package cy.ac.ucy.cs.epl231.IDs911719.Lab09;

import java.util.concurrent.TimeUnit;

public class Sudoku_Backtracking {
	private static final int SIZE = 9;
	private static int[][] matrix1 = { { 6, 5, 0, 8, 7, 3, 0, 9, 0 }, { 0, 0, 3, 2, 5, 0, 0, 0, 8 },
			{ 9, 8, 0, 1, 0, 4, 3, 5, 7 }, { 1, 0, 5, 0, 0, 0, 0, 0, 0 }, { 4, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 5, 0, 3 }, { 5, 7, 8, 3, 0, 1, 0, 2, 6 }, { 2, 0, 0, 0, 4, 8, 9, 0, 0 },
			{ 0, 9, 0, 6, 2, 5, 0, 8, 1 } };
	private static int[][] matrix2 = { { 0, 0, 0, 2, 6, 0, 7, 0, 1 }, { 6, 8, 0, 0, 7, 0, 0, 9, 0 },
			{ 1, 9, 0, 0, 0, 4, 5, 0, 0 }, { 8, 2, 0, 1, 0, 0, 0, 4, 0 }, { 0, 0, 4, 6, 0, 2, 9, 0, 0 },
			{ 0, 5, 0, 0, 0, 3, 0, 2, 8 }, { 0, 0, 9, 3, 0, 0, 0, 7, 4 }, { 0, 4, 0, 0, 5, 0, 0, 3, 6 },
			{ 7, 0, 3, 0, 1, 8, 0, 0, 0 } };

	private static void printSudoku(int matrix[][]) {
		// for (int i = 0; i < 10; i++) System.out.print('\b');
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (Throwable ee) {
		}
		System.out.println(" -----------------------");
		for (int i = 0; i < SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < SIZE; j++) {
				System.out.print(matrix[i][j] + " ");
				if (j % 3 == 2)
					System.out.print("| ");
			}
			System.out.println(" ");
			if (i % 3 == 2)
				System.out.println(" -----------------------");
		}
	}

	// Check if all cells are assigned or not
	// if there is any unassigned cell
	// change the values of row and col accordingly
	private static int[] findUnassignedCell(int matrix[][]) {
		int numunassign = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				// if a cell is unassigned
				if (matrix[i][j] == 0) {
					int[] a = { 1, i, j };
					return a;
				}
			}
		}
		int[] a = { numunassign, -1, -1 };
		return a;
	}

	// Check if we can put a value in a the cell
	private static boolean isSafe(int matrix[][], int n, int r, int c) {
		// checking in row
		for (int i = 0; i < SIZE; i++) {
			// there is a cell with same value
			if (matrix[r][i] == n)
				return false;
		}
		// checking column
		for (int i = 0; i < SIZE; i++) {
			// there is a cell with the value equal to i
			if (matrix[i][c] == n)
				return false;
		}
		// checking sub matrix
		int row_start = (r / 3) * 3;
		int col_start = (c / 3) * 3;
		for (int i = row_start; i < row_start + 3; i++) {
			for (int j = col_start; j < col_start + 3; j++) {
				if (matrix[i][j] == n)
					return false;
			}
		}
		return true;
	}

	// Solve Sudoku using backtracking
	private static boolean solveSudoku(int matrix[][]) {
		int row = 0;
		int col = 0;
		// Look for an Unassigned Cell
		int[] a = findUnassignedCell(matrix);
		// if all cells are assigned then the Sudoku is already solved
		if (a[0] == 0)
			return true;
		// Else get the cell row and col
		row = a[1];
		col = a[2];
		// Check Numbers from 1 to SIZE
		for (int i = 1; i <= SIZE; i++) {
			/*** ADD YOUR CODE HERE ***/
			if (isSafe(matrix, i, row, col)) {
				matrix[row][col] = i;
				printSudoku(matrix);
				if (solveSudoku(matrix)) {
					return true;
				}
				matrix[row][col] = 0;
			}
		}
		return false;// If you can not assign a value you have to go back and try again
	}

	public static void main(String[] args) {
		printSudoku(matrix2);
		if (solveSudoku(matrix2)) {
			System.out.println("Solution:");
			printSudoku(matrix2);
		} else
			System.out.println("No solution");
	}
}
