package cy.ac.ucy.cs.epl231.IDs911719.homework3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateGraph {

	/**
	 * Create Graph
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Read manyUrls
			ArrayList<String> urls = new ArrayList<>();
			InputStream input;
			input = new FileInputStream("manyUrls.txt");
			Scanner scan = new Scanner(input);
			while (scan.hasNext()) {
				urls.add(scan.next());
			}
			scan.close();
			// Create Graph
			Graph G = new Graph(urls);

			// System.out.println(G); // Print Graph

			// Write object to file
			try {
				System.out.println("Creating new Object file...");
				FileOutputStream fileOut = new FileOutputStream("..\\PageRank\\");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(G);
				objectOut.close();
				System.out.println("The Object was succesfully written to a file");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
