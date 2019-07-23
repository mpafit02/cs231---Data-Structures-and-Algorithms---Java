package cy.ac.ucy.cs.epl231.IDs911719.homework2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SearchEngine_911719 {
	private static Scanner scan;
//C:\Users\mario\Documents\University\3rd semester\EPL 231\Ergasia\Ergasia 2\InputFiles\HW2inputsNew\StarWars.txt
	public static void main(String[] args) {
		try { // Load the Binary tree from the file
			System.out.println("Reading Object file...");
			FileInputStream fileIn = new FileInputStream("..\\BinaryTree\\");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object obj = objectIn.readObject();
			objectIn.close();
			BinaryTree bt = (BinaryTree) obj;
			bt.setSkipPointerBST();
			System.out.println("\nFinish!");
			start(bt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void start(BinaryTree bt) {
		scan = new Scanner(System.in);
		String content, path;
		int option = 0;
		String sentence[];
		while (true) {
			System.out.println();
			System.out.println("Choose an option:");
			System.out.println("----------------------------------------------------------------");
			System.out.println("(1)	Search Word");
			System.out.println("(2)	Search OR/AND");
			System.out.println("(3)	Search Wild-Card");
			System.out.println("(4)	Search Phrase");
			System.out.println("(5)	Add DocID");
			System.out.println("(9)	Exit");
			System.out.print("> ");
			try {
				option = Integer.parseInt(scan.nextLine());
				System.out.println();
				if (option == 1) {
					// Read a word from the user
					System.out.print("Search: ");
					content = scan.nextLine().toUpperCase();
					sentence = content.split("\\s+");
					content = sentence[0];
					Node node = bt.search(content);
					if (node == null) {
						System.out.println("Word: " + content + "\nNot found!");
					} else {
						System.out.println("Word: " + node.value.getContent());
						System.out.print("Appears in Files: ");
						node.value.getDocID().print();
						System.out.println();
						System.out.println();
						System.out.print("Skip pointers: ");
						node.value.getDocID().printSkipPointers();
						System.out.println();
						System.out.println();
						System.out.println("Positions in files: ");
						for (int i = 0; i < node.value.getPositions().size(); i++) {
							System.out.println(
									"File " + node.value.getDocID().get(i) + ": " + node.value.getPositions().get(i));
						}
						System.out.println();
					}
				} else if (option == 2) {
					// Search or and
					System.out.print("Search: ");
					content = scan.nextLine().toUpperCase();
					sentence = content.split(" ");
					// Validations
					if ((sentence.length != 3 && sentence.length != 5) || !correctFormat(sentence)
							|| !hasUniqueKey(sentence)) {
						System.out.println("Wrong format!");
						System.out.println("Correct format: word1 AND/OR word2 AND/OR word3...");
					} else {
						Node node;
						ArrayList<Node> nodes = new ArrayList<Node>();
						ArrayList<Integer> similar = new ArrayList<Integer>();
						ArrayList<Integer> temp = new ArrayList<Integer>();
						String words = "";
						// Find the nodes
						for (int i = 0; i < sentence.length; i++) {
							if (i % 2 == 0) {
								words += sentence[i] + " ";
								node = bt.search(sentence[i]);
								if (node == null) {
									System.out.println("Sorry. They don't have similar files");
									break;
								}
								nodes.add(node);
							}
						}
						// Check the nodes
						if (sentence[1].equals("AND")) { // Case is AND
							DocNode skipNode;
							for (int i = 0; i < nodes.size() - 1; i++) {
								SkipList doc1 = nodes.get(i).value.getDocID();
								SkipList doc2 = nodes.get(i + 1).value.getDocID();
								if (i == 0) {
									System.out.println("Word: " + nodes.get(i).value.getContent());
									System.out.print("Appears in Files: ");
									doc1.print();
									System.out.println();
								}
								System.out.println();
								System.out.println("Word: " + nodes.get(i + 1).value.getContent());
								System.out.print("Appears in Files: ");
								doc2.print();
								System.out.println();
								int pos1 = 0;
								int pos2 = 0;
								System.out.println();
								while (pos1 < doc1.size() && pos2 < doc2.size()) {
									if (doc1.get(pos1) == doc2.get(pos2)) {
										if (i == 0) {
											similar.add(doc1.get(pos1));
										} else {
											if (similar.contains(doc1.get(pos1))) {
												temp.add(doc1.get(pos1));
											}
										}
										pos1++;
										pos2++;
									} else if (doc1.get(pos1) < doc2.get(pos2)) {
										skipNode = doc1.getNode(pos1).skip;
										if ((skipNode != null) && (skipNode.value <= doc2.get(pos2))) {
											while ((skipNode != null) && (doc1.get(skipNode.value) <= doc2.get(pos2))) {
												System.out.println("2nd DocID: " + doc2.get(pos2) + "\tSkip 1st DocID from " + doc1.get(pos1) + " to " + doc1.get(skipNode.value));
												pos1 = skipNode.value;
												skipNode = skipNode.skip;
											}
										} else {
											pos1++;
										}
									} else {
										skipNode = doc2.getNode(pos2).skip;
										if ((skipNode != null) && (skipNode.value <= doc1.get(pos1))) {
											while ((skipNode != null) && (doc2.get(skipNode.value) <= doc1.get(pos1))) {
												System.out.println("1st DocID: " + doc1.get(pos1) + "\tSkip 2nd DocID from " + doc2.get(pos2) + " to " + doc2.get(skipNode.value));
												pos2 = skipNode.value;
												skipNode = skipNode.skip;
											}
										} else {
											pos2++;
										}
									}
								}
							}
							if (nodes.size() == 3) {
								similar.clear();
								similar.addAll(temp);
								temp.clear();
							}
						} else { // Case is OR
							for (int i = 0; i < nodes.size(); i++) {
								if (nodes.get(i) == null) {
									System.out.println("Word: " + content + "\nNot found!");
								} else {
									for (int j = 0; j < nodes.get(i).value.getDocID().size(); j++) {
										if (!similar.contains(nodes.get(i).value.getDocID().get(j))) {
											similar.add(nodes.get(i).value.getDocID().get(j));
										}
									}
									System.out.println();
									System.out.println("Word: " + nodes.get(i).value.getContent());
									System.out.print("Appears in Files: ");
									nodes.get(i).value.getDocID().print();
									System.out.println();
								}
							}
						}
						Collections.sort(similar);
						if (!similar.isEmpty()) {
							System.out.println("\nWords: " + words + "\nAppears in Files: ");
							System.out.print("[");
							int i = 0;
							for (; i < similar.size() - 1; i++) {
								if (i % 100 == 0 && i != 0) {
									System.out.print("\n ");
								}
								System.out.print(similar.get(i) + ", ");
							}
							System.out.println(similar.get(i) + "]\n");
						} else {
							System.out.println("\nThey don't have similar files");
						}
					}
				} else if (option == 3) {
					// Search wild card
					System.out.print("Search: ");
					content = scan.nextLine().toUpperCase();
					sentence = content.split("\\s+");
					content = sentence[0];
					if (content.length() < 2 || content.charAt(content.length() - 1) != '*') {
						System.out.println("Wrong format!");
					} else {
						content = content.substring(0, content.length() - 1);
						System.out.println("Wild-Card: " + content + "*\n");
						ArrayList<Node> nodeList = bt.searchWildCard(content);
						if (nodeList.isEmpty()) {
							System.out.println("Nothing found!");
						} else {
							System.out.println("I found those:\n");
							for (int j = 0; j < nodeList.size(); j++) {
								System.out.println(nodeList.get(j).value.getContent());
							}
							System.out.println();
						}
					}
				} else if (option == 4) {
					// Search phrase
					System.out.print("Search: ");
					content = scan.nextLine().toUpperCase();
					sentence = content.split("\\s+");
					if (sentence.length <= 1) {
						System.out.println("Wrong format!");
					} else {
						bt.searchPhrase(bt, sentence);
					}
				} else if (option == 5) {
					// Read a DocID file
					System.out.print("Please give me the path of your file:\n> ");
					path = scan.nextLine();
					int wordCounter = 0;
					Scanner file;
					Node node;
					Word word;
					try {
						// For each name in the path array
						file = new Scanner(new File(path));
						while (file.hasNext()) {
							content = file.next().toUpperCase().trim();
							node = bt.search(content);
							if (node != null) {
								word = node.value;
								word.updateDocID(bt.getFileCounter(), wordCounter);
							} else {
								word = new Word(content, bt.getFileCounter(), wordCounter);
								bt.add(word);
							}
							wordCounter++;
						}
						wordCounter = 0;
						bt.increaseFileCounter();
						// bt.print();
						System.out.println("Success! You have updated your Binary Tree!");
						// Write new object to file
						try {
							System.out.println("\nCreating new Object file...");
							FileOutputStream fileOut = new FileOutputStream("..\\BinaryTree_Updated\\");
							ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
							objectOut.writeObject(bt);
							objectOut.close();
							System.out.println("\nFinish!");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (FileNotFoundException e2) {
						System.out.println("Wrong file input!");
					}
				} else if (option == 9) {
					System.out.println("Thank you! Bye!");
					return;
				} else {
					System.out.println("Wrong input!");
				}
			} catch (

			NumberFormatException e) {
				System.out.println("Wrong input!");
			}
		}

	}

	public static boolean correctFormat(String[] arr) {
		boolean correctFormat = true;
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 != 0 && !arr[i].equals("AND") && !arr[i].equals("OR")) {
				correctFormat = false;
			}
		}
		return correctFormat;
	}

	public static boolean hasUniqueKey(String[] arr) {
		boolean hasUniqueKey = true;
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 != 0 && !arr[i].equals(arr[1])) {
				hasUniqueKey = false;
			}
		}
		return hasUniqueKey;
	}
}
