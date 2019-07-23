package cy.ac.ucy.cs.epl231.IDs911719.homework2;

import java.io.Serializable;
import java.util.ArrayList;

public class SkipList implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> print = new ArrayList<>();
	private DocNode head;
	private DocNode tail;
	private int size = 0;

	public int size() {
		return size;
	}

	public SkipList() {
		head = null;
		size = 0;
	}

	public void add(int x) {
		DocNode temp = new DocNode(x);
		print.add(x);
		if (size == 0) {
			head = temp;
			tail = temp;
		} else {
			tail.next = temp;
			tail = temp;
		}
		size++;
	}

	public int indexOf(int id) {
		int index = 0;
		DocNode temp = head;
		while (temp != null && id != temp.value) {
			temp = temp.next;
			index++;
		}
		return index;
	}

	public boolean contains(int id) {
		DocNode temp = head;
		while (temp != null) {
			if (temp.value == id) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	public int get(int i) {
		int count = 0;
		DocNode temp = head;
		while (temp != null) {
			if (i == count) {
				return temp.value;
			}
			temp = temp.next;
			count++;
		}
		return -1;
	}

	public DocNode getNode(int i) {
		int count = 0;
		DocNode temp = head;
		while (temp != null) {
			if (i == count) {
				return temp;
			}
			temp = temp.next;
			count++;
		}
		return null;
	}

	public void print() {
		System.out.print("\n[");
		DocNode node = head;
		for (int i = 0; i < size - 1; i++) {
			if (i % 100 == 0 && i != 0) {
				System.out.print("\n ");
			}
			System.out.print(node.value + ", ");
			node = node.next;
		}
		System.out.print(node.value + "]");
	}

	public void setSkipPointers() {
		DocNode temp = head;
		DocNode lastSkip = null;
		int count = 0;
		while (temp != null) {
			if (count == 0) {
				lastSkip = temp;
			} else if (count % (int) Math.sqrt(size) == 0) {
				lastSkip.skip = temp;
				lastSkip = temp;
			}
			temp = temp.next;
			count++;
		}
	}

	public void printSkipPointers() {
		System.out.print("\n[");
		DocNode node = head;
		int i = 0;
		while (node != null) {
			if (node.skip != null) {
				if (i % 10 == 0 && i != 0) {
					System.out.print("\n ");
				}
				System.out.print("("+node.value + " -> " + node.skip.value + "), ");
				i++;
			}
			node = node.next;
		}
		System.out.print("]");
	}
}
