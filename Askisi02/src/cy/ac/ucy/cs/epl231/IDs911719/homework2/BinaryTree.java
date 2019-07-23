package cy.ac.ucy.cs.epl231.IDs911719.homework2;

import java.util.Set;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.ArrayList;

public class BinaryTree implements Serializable {
	// default serialVersion id
	private static final long serialVersionUID = 1L;
	private int fileCounter = 0;
	private Node root;
	private ArrayList<Node> nodeList = new ArrayList<>();

	// Get fileCounter
	public int getFileCounter() {
		return this.fileCounter;
	}

	// Increase fileCounter
	public void increaseFileCounter() {
		this.fileCounter++;
	}

	// Add a Node in the tree
	public void add(Word value) {
		root = addRecursive(root, value);
	}

	// Print the tree
	public void print() {
		printRecursive(root);
	}

	// Set Skip Pointers
	public void setSkipPointerBST() {
		setSkipPointerBST(root);
	}

	// Set Skip Pointers Recursive
	public void setSkipPointerBST(Node n) {
		if (n == null) {
			return;
		}
		n.value.getDocID().setSkipPointers();
		setSkipPointerBST(n.left);
		setSkipPointerBST(n.right);
	}

	// Search the tree
	public Node search(String word) {
		return searchRecursive(root, word);
	}

	// Search Wild Card
	public ArrayList<Node> searchWildCard(String word) {
		nodeList.clear();
		searchRecursiveWildCard(root, word);
		return nodeList;
	}

	// Recursive method to add the Node
	private Node addRecursive(Node current, Word value) {
		if (current == null) {
			return new Node(value);
		}
		if (value.getContent().compareTo(current.value.getContent()) < 0) {
			current.left = addRecursive(current.left, value);
		} else if (value.getContent().compareTo(current.value.getContent()) > 0) {
			current.right = addRecursive(current.right, value);
		} else {
			// value already exists so increase the counter of the word
			return current;
		}

		return current;
	}

	// Recursive method to print the tree
	private void printRecursive(Node current) {
		if (current == null) {
			// System.out.print("null");
			return;
		} else {
			System.out.print(current.value.toString());
			System.out.print("\n");
			// System.out.print("<- ");
			printRecursive(current.left);
			// System.out.print("-> ");
			printRecursive(current.right);
			// System.out.print("\n");

		}
	}

	// A utility function to search a given key in BST
	public Node searchRecursive(Node current, String word) {
		if (current == null || (current.value.getContent().compareTo(word) == 0))
			return current;

		if (current.value.getContent().compareTo(word) > 0)
			return searchRecursive(current.left, word);

		return searchRecursive(current.right, word);
	}

	// A utility function to search a given wild card in BST
	public void searchRecursiveWildCard(Node current, String word) {
		if (current == null) {
			return;
		}
		if (current.value.getContent().length() >= word.length()) {
			if (current.value.getContent().compareTo(word) == 0
					|| current.value.getContent().substring(0, word.length()).compareTo(word) == 0) {
				if (!nodeList.contains(current)) {
					nodeList.add(current);
				}
				searchRecursiveWildCard(current.left, word);
				searchRecursiveWildCard(current.right, word);
			}
		}

		if (current.value.getContent().compareTo(word) > 0) {
			searchRecursiveWildCard(current.left, word);
		} else if (current.value.getContent().compareTo(word) < 0) {
			searchRecursiveWildCard(current.right, word);
		}
	}

	public void searchPhrase(BinaryTree bt, String sentence[]) {
		ArrayList<Node> sentenceNodes = new ArrayList<>();
		ArrayList<Integer> temp;
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Node node;
		Word currentWord, nextWord;
		int fileCurrent, fileNext, currentPosition, nextPosition;
		// Find all the words
		for (int i = 0; i < sentence.length; i++) {
			node = bt.search(sentence[i]);
			if (node == null) {
				System.out.println("\nThis sentence doesn't exists!");
				System.out.println("\nNot Found: " + sentence[i]);
				return;
			} else {
				sentenceNodes.add(node);
			}
		}
		for (int i = 0; i < sentenceNodes.size() - 1; i++) {
			currentWord = sentenceNodes.get(i).value;
			nextWord = sentenceNodes.get(i + 1).value;
//			System.out.println("\nWord: " + currentWord.getContent() + " Next: " + nextWord.getContent());
//			System.out.println(nextWord.getDocID().toString());
			for (int j = 0; j < currentWord.getDocID().size(); j++) {
				fileCurrent = currentWord.getDocID().get(j);
				// System.out.print("Positions for file: " + fileCurrent + " :");
				for (int m = 0; m < nextWord.getDocID().size(); m++) {
					fileNext = nextWord.getDocID().get(m);
					// System.out.print("Positions for file: " + fileCurrent + " :");
					if (fileCurrent == fileNext) {
						for (int k = 0; k < currentWord.getPositions().get(j).size(); k++) {
							currentPosition = currentWord.getPositions().get(j).get(k);
							for (int l = 0; l < nextWord.getPositions().get(m).size(); l++) {
								nextPosition = nextWord.getPositions().get(m).get(l);
								if (currentPosition == (nextPosition - 1)) {
									if (i == 0) {
										if (!map.containsKey(fileCurrent)) {
											temp = new ArrayList<>();
											temp.add(currentPosition);
											temp.add(nextPosition);
											map.put(fileCurrent, temp);
										} else {
											temp = map.get(fileCurrent);
											temp.add(currentPosition);
											temp.add(nextPosition);
											map.replace(fileCurrent, temp);
										}
									} else {
										if (map.containsKey(fileCurrent)) {
											temp = map.get(fileCurrent);
											if (temp.contains(currentPosition)) {
												temp.add(nextPosition);
												map.replace(fileCurrent, temp);
											}
										}
									}
//									System.out.println(fileCurrent + ":\t" + currentPosition + ":\t"
//											+ currentWord.getContent() + " " + nextWord.getContent());
								}
							}
						}
					}
				}
			}
		}
		Set<Integer> keys = map.keySet();
		System.out.println("\nFiles that contains the Phrase:");
		int counter = 0;
		for (int key : keys) {
			boolean contains;
			temp = map.get(key);
			for (int i = 0; i < temp.size(); i++) {
				contains = true;
				for (int k = 1; k < sentence.length; k++) {
					contains = contains && temp.contains(temp.get(i) + k);
				}
				if (contains) {
					if (counter > 100) {
						System.out.println();
						counter = 0;
					}
					if (counter == 0) {
						System.out.print(key);
					} else {
						System.out.print(", " + key);
					}
					counter++;
					break;
				}
			}
		}
		System.out.println();
	}
}