package cy.ac.ucy.cs.epl231.IDs911719.homework3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PageRank_911719 {
	// Dumping Factor
	public static final float d = 0.85f;
	public static final float siblingFactor = 0.1f;
	public static final float siblingFactor2 = 0.2f;
	public static final float siblingFactor3 = 0.3f;
	public static final float spamFactor = 0.3f;
	public static int detailed = -1;
	public static int page = -1;

	public static void main(String[] args) {
		Graph G = null;
		// Read Graph_911719
		System.out.println("Hello, This is the Page Rank Algorithm!\n");
		try { // Load the Binary tree from the file
			System.out.println("Reading Object file...");
			FileInputStream fileIn = new FileInputStream("..\\PageRank\\");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object obj = objectIn.readObject();
			objectIn.close();
			G = (Graph) obj;
			System.out.println("Finish!");
			if (G == null) {
				System.out.println("Error: I can't read the Graph_911719");
				return;
			}
			// System.out.println(G); //Print Graph_911719
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nI am ready!");
		System.out.println("Please select a page from number 0 to " + (G.getV() - 1));
		System.out.print("> ");
		Scanner scan = new Scanner(System.in);
		page = scan.nextInt();
		while (page < 0 || page > 491) {
			System.out.println("Wrong Input");
			System.out.println("Please select a page from number 0 to " + (G.getV() - 1));
			System.out.print("> ");
			page = scan.nextInt();
		}
		System.out.println("Detailed Level (0 = Brief / 1 = Detailed)");
		System.out.print("> ");
		detailed = scan.nextInt();
		while (detailed != 0 && detailed != 1) {
			System.out.println("Wrong Input");
			System.out.println("Detailed Level (0 = Brief / 1 = Detailed)");
			System.out.print("> ");
			detailed = scan.nextInt();
		}
		scan.close();
		// Read Page Number from the User;
		// Print Detailed Graph_911719
//		for (int v = 0; v < G.getV(); v++) {
//			System.out.println(v + " : " + G.getUrls().get(v));
//			System.out.print("--" + G.getG()[v].size() + "--" + (float)1/G.getG()[v].size() + ":\t");
//			for (String w : G.getG()[v]) {
//				System.out.print("(" + G.getUrls().indexOf(w) + ")" + w + " ");
//			}
//			System.out.println();
//		}

		// Calculate the C array
		int C[] = new int[G.getV()];
		for (int v = 0; v < G.getV(); v++) {
			C[v] = G.getG()[v].size();
		}

		// Creating transition matrix
		float mat[][] = new float[G.getV()][G.getV()];
		for (int v = 0; v < G.getV(); v++) {
			for (String w : G.getG()[v]) {
				mat[G.getUrls().indexOf(w)][v] = (float) 1 / G.getG()[v].size();
			}
		}
//		int count = 0;
//		for (int i = 0; i < G.getV(); i++) {
//			for (int j = 0; j < G.getV(); j++) {
//				if (mat[i][j] != 0) {
//					count++;
//				}
//			}
//		}
//		System.out.printf("The matrix is full at %.2f percent.\n", ((float) count /
//		(G.getV() * G.getV())) * 100);
		// Testing creation of mat array
//		float sum[] = new float[G.getV()];
//		for (int j= 0; j < G.getV();j++) {
//			sum[j] = 0;
//			for (int i = 0; i < G.getV(); i++) {
//				sum[j] += mat[i][j];
//			}
//		}
//		for (int i = 0; i < G.getV(); i++) {
//			System.out.println(sum[i] + " ");
//		}

		System.out.println("Page Rank for page " + G.getUrls().get(page));
		System.out.println("\nMethod A: ");
		// Calculate Page Rank A method
		float PR_A[] = calculatePageRankA(G.getV(), mat, C);

		System.out.println("\n----------------------------------");
		System.out.println("\nMethod B: ");
		// Find the best number to detect spams
		// Calculate the P matrix, how many they pointing at me
		int P[] = new int[G.getV()];
		for (int i = 0; i < G.getV(); i++) {
			P[i] = 0;
		}
		for (int i = 0; i < G.getV(); i++) {
			for (int j = 0; j < G.getV(); j++) {
				if (mat[i][j] != 0) {
					P[i]++;
				}
			}
		}
		ArrayList<Integer> spamList = new ArrayList<>();
		for (int i = 0; i < G.getV(); i++) {
			// Less than or equal of spamFactor * size
			if (P[i] <= G.getN() * spamFactor) {
				spamList.add(i);
//				System.out.println("Page " + i + " (" + G.getUrls().get(i) + ") is a spam with " + P[i]
//						+ " pages pointing to it.");
			}
		}
		if (spamList.contains(page)) {
			System.out.println("\nSpam Page\n");
		} else {
			System.out.println("\nNot a Spam Page\n");
		}
		// Calculate Page Rank B method
		float PR_B[] = calculatePageRankB(G.getV(), mat, C, spamList);

		if (detailed == 1) {
			System.out.println();
			for (int i : spamList) {
				System.out.println("Page " + i + " (" + G.getUrls().get(i) + ") is a spam with PR = " + PR_B[i]);
			}
		}
		// Calculate Page Rank C method
		System.out.println("\n----------------------------------");
		System.out.println("\nMethod C:\n");
		float PR_C[] = calculatePageRankC(G.getV(), mat, C, G, siblingFactor);
		// Testing Sibling Factor
//		float PR_C2[] = calculatePageRankC(G.getV(), mat, C, G, siblingFactor2);
//		float PR_C3[] = calculatePageRankC(G.getV(), mat, C, G, siblingFactor3);

		int Url_A[] = new int[G.getV()];
		int Url_B[] = new int[G.getV()];
		int Url_C[] = new int[G.getV()];
		int Url_C2[] = new int[G.getV()];
		int Url_C3[] = new int[G.getV()];
		for (int i = 0; i < G.getV(); i++) {
			Url_A[i] = i + 1;
			Url_B[i] = i + 1;
			Url_C[i] = i + 1;
			Url_C2[i] = i + 1;
			Url_C3[i] = i + 1;
		}
		bubbleSort(PR_A, Url_A);
		bubbleSort(PR_B, Url_B);
		bubbleSort(PR_C, Url_C);
		// Testing sibling factor
//		bubbleSort(PR_C2, Url_C2);
//		bubbleSort(PR_C3, Url_C3);
		// Print Page ranks for method A, B and C
//		for (int i = 0; i < G.getV(); i++) {
//			if (spamList.contains(Url_B[i])) {
//				System.out.print("(Spam) ");
//			}
//			System.out.printf(
//					"PR_A(p%3d): %.3f   PR_B(p%3d): %.3f   PR_C[0.1](p%3d): %.3f   PR_C[0.2](p%3d): %.3f   PR_C[0.3](p%3d): %.3f\n",
//					Url_A[i], PR_A[i], Url_B[i], PR_B[i], Url_C[i], PR_C[i], Url_C2[i], PR_C2[i], Url_C3[i],
//					PR_C3[i]);
//		}
	}

	public static float[] calculatePageRankA(int n, float mat[][], int C[]) {
		// Page Rank array
		float PR[] = new float[n];
		float PR_old[] = new float[n];
		int iterations = 0;
		// Initialize PageRank
		for (int i = 0; i < n; i++) {
//			PR[i] = (float) (1.0 / n);
//			PR_old[i] = (float) (1.0 / n);
			PR[i] = (float) (1.0);
			PR_old[i] = (float) (1.0);
		}
		boolean finish = false;
		if (detailed == 0) {
			System.out.println("\nManual: (Iteration)PageRank[Page] Value..\n");
		}
		boolean printDots = false;
		// Finish when we reach our precision
		while (!finish) {
			finish = true;
			if (detailed == 1) {
				if (Math.abs(PR_old[page] - PR[page]) >= 0.0001 || iterations == 0) {
					printDots = false;
					System.out.printf("Iteration %2d PR[%d] = %.4f\n", iterations, page, PR[page]);
				} else if (!printDots) {
					System.out.println(".\n.");
					printDots = true;
				}
			} else {
				if (Math.abs(PR_old[page] - PR[page]) >= 0.0001 || iterations == 0) {
					System.out.printf("(%d)%.4f..", iterations, PR[page]);
				}
			}
			// Copy Page Rank to the old one
			for (int i = 0; i < n; i++) {
				PR_old[i] = PR[i];
			}
			// Calculate the new Page Rank
			for (int i = 0; i < n; i++) {
				float sum = 0;
				for (int j = 0; j < n; j++) {
					if (mat[i][j] != 0) {
						sum += PR_old[j] / (float) C[j];
					}
				}
				// New Page Rank for the i page
				PR[i] = 1 - d + d * sum;
				// Accuracy 4 decimal digits
				if (Math.abs(PR_old[i] - PR[i]) > 0.00009) {
					finish = false;
				}
				// System.out.println(i + ": " + PR_old[i] + " - " + PR[i] + " " +
				// Math.abs(PR_old[i] - PR[i]));
				// System.out.println("PR[" + i + "] = " + PR[i]);
			}
			iterations++;
		}
		if (detailed == 1) {
			System.out.printf("Iteration %2d PR[%d] = %.4f\n", iterations, page, PR[page]);
			System.out.print("Contributors: ");
			for (int i = 0; i < n; i++) {
				if (mat[page][i] != 0) {
					System.out.printf("PR[%d], ", i);
				}
			}
			System.out.println();
		} else {
			System.out.printf("(%d)%.4f..END\n", iterations, PR[page]);
		}
		System.out.println("\nIterations for Method A = " + iterations);
		return PR;
	}

	public static float[] calculatePageRankB(int n, float mat[][], int C[], ArrayList<Integer> spamList) {
		// Page Rank array
		float PR[] = new float[n];
		float PR_old[] = new float[n];
		// Initialize PageRank
		for (int i = 0; i < n; i++) {
//			PR[i] = (float) (1.0 / n);
//			PR_old[i] = (float) (1.0 / n);
			PR[i] = (float) (1.0);
			PR_old[i] = (float) (1.0);
		}
		int iterations = 0;
		boolean printDots = false;
		boolean finish = false;
		// Finish when we reach our precision
		while (!finish) {
			finish = true;
			if (detailed == 1) {
				if (Math.abs(PR_old[page] - PR[page]) >= 0.0001 || iterations == 0) {
					printDots = false;
					System.out.printf("Iteration %2d PR[%d] = %.4f\n", iterations, page, PR[page]);
				} else if (!printDots) {
					System.out.println(".\n.");
					printDots = true;
				}
			} else {
				if (Math.abs(PR_old[page] - PR[page]) >= 0.0001 || iterations == 0) {
					System.out.printf("(%d)%.4f..", iterations, PR[page]);
				}
			}
			// Copy Page Rank to the old one
			for (int i = 0; i < n; i++) {
				PR_old[i] = PR[i];
			}
			// Calculate the new Page Rank
			for (int i = 0; i < n; i++) {
				float sum = 0;
				for (int j = 0; j < n; j++) {
					if (mat[i][j] != 0 && !spamList.contains(j) && PR_old[j] > 0.4) {
						sum += PR_old[j] / (float) C[j];
					}
				}
				// New Page Rank for the i page
				PR[i] = 1 - d + d * sum;
				// Accuracy 4 decimal digits
				if (Math.abs(PR_old[i] - PR[i]) > 0.00009) {
					finish = false;
				}
				// System.out.println(i + ": " + PR_old[i] + " - " + PR[i] + " " +
				// Math.abs(PR_old[i] - PR[i]));
				// System.out.println("PR[" + i + "] = " + PR[i]);
			}
			iterations++;
		}
		if (detailed == 1) {
			System.out.printf("Iteration %2d PR[%d] = %.4f\n", iterations, page, PR[page]);
			System.out.print("Contributors: ");
			for (int i = 0; i < n; i++) {
				if (mat[page][i] != 0) {
					System.out.printf("PR[%d], ", i);
				}
			}
			System.out.println();
		} else {
			System.out.printf("(%d)%.4f..END\n", iterations, PR[page]);
		}
		System.out.println("\nIterations for Method B = " + iterations);
		for (int i = 0; i < n; i++) {
			if (PR[i] <= 0.4 && !spamList.contains(i)) {
				spamList.add(i);
			}
		}
		return PR;
	}

	public static float[] calculatePageRankC(int n, float mat[][], int C[], Graph G, float siblingFactor) {
		// Page Rank array
		float PR[] = new float[n];
		float PR_old[] = new float[n];
		// Initialize PageRank
		for (int i = 0; i < n; i++) {
//		PR[i] = (float) (1.0 / n);
//		PR_old[i] = (float) (1.0 / n);
			PR[i] = (float) (1.0);
			PR_old[i] = (float) (1.0);
		}
		int iterations = 0;
		boolean printDots = false;
		boolean finish = false;
		// Finish when we reach our precision
		while (!finish) {
			finish = true;
			if (detailed == 1) {
				if (Math.abs(PR_old[page] - PR[page]) >= 0.0001 || iterations == 0) {
					printDots = false;
					System.out.printf("Iteration %2d PR[%d] = %.4f\n", iterations, page, PR[page]);
				} else if (!printDots) {
					System.out.println(".\n.");
					printDots = true;
				}
			} else {
				if (Math.abs(PR_old[page] - PR[page]) >= 0.0001 || iterations == 0) {
					System.out.printf("(%d)%.4f..", iterations, PR[page]);
				}
			}
			// Copy Page Rank to the old one
			for (int i = 0; i < n; i++) {
				PR_old[i] = PR[i];
			}
			// Calculate the new Page Rank
			for (int i = 0; i < n; i++) {
				float sum = 0;
				for (int j = 0; j < n; j++) {
					if (mat[i][j] != 0 && i != j) {
						if (G.getUrls().get(i).split("/")[0].equals(G.getUrls().get(j).split("/")[0])) {
							sum += siblingFactor * PR_old[j] / (float) C[j];
						} else {
							sum += PR_old[j] / (float) C[j];
						}
					}
				}
				// New Page Rank for the i page
				PR[i] = 1 - d + d * sum;
				// Accuracy 4 decimal digits
				if (Math.abs(PR_old[i] - PR[i]) > 0.00009) {
					finish = false;
				}
				// System.out.println(i + ": " + PR_old[i] + " - " + PR[i] + " " +
				// Math.abs(PR_old[i] - PR[i]));
				// System.out.println("PR[" + i + "] = " + PR[i]);
			}
			iterations++;
		}
		if (detailed == 1) {
			System.out.printf("Iteration %2d PR[%d] = %.4f\n", iterations, page, PR[page]);
			System.out.print("Contributors: ");
			for (int i = 0; i < n; i++) {
				if (mat[page][i] != 0) {
					System.out.printf("PR[%d], ", i);
				}
			}
			System.out.println();
		} else {
			System.out.printf("(%d)%.4f..END\n", iterations, PR[page]);
		}
		System.out.println("\nIterations for Method C = " + iterations);
		return PR;
	}

	static void bubbleSort(float[] arr, int url[]) {
		int n = arr.length;
		float temp = 0;
		int temp1 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) {
					// swap elements
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
					temp1 = url[j - 1];
					url[j - 1] = url[j];
					url[j] = temp1;
				}

			}
		}

	}
}
