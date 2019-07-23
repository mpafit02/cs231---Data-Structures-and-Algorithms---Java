package cy.ac.ucy.cs.epl231.IDs911719.homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CreateSearchEngine_911719 {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		// Read Data
		File f = null;
		String[] paths;
		Scanner sc;
		String content;
		Node node;
		Word word;
		int wordCounter = 0;
		Scanner scan = new Scanner(System.in);
		// C:\Users\mario\eclipse-workspace-epl231\Askisi02\InputFiles\
		System.out.println("Give me the path of the folder with the files: ");
		String pathFolder = scan.nextLine();
		// Create new file
		f = new File(pathFolder);
		// Array of files and directory
		paths = f.list();
		scan.close();
		try {
			System.out.println("Creating the binary tree...");
			// For each name in the path array
			for (String path : paths) {
				sc = new Scanner(new File(pathFolder + path));
				wordCounter = 0;
				while (sc.hasNext()) {
					content = sc.next().toUpperCase().trim();
					node = bt.search(content);
					if (node != null) {
						word = node.value;
						word.updateDocID(bt.getFileCounter(), wordCounter);
					} else {
						word = new Word(content, bt.getFileCounter(), wordCounter);
						bt.add(word);
					}
					++wordCounter;
				}
				bt.increaseFileCounter();
			}
			System.out.println("Binary tree has been created!");
			// bt.print(); 

			// Write object to file
			try {
				System.out.println("Creating new Object file...");
				FileOutputStream fileOut = new FileOutputStream("..\\BinaryTree\\");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(bt);
				objectOut.close();
				System.out.println("The Object was succesfully written to a file");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
