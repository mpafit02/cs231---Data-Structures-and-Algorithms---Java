package cy.ac.ucy.cs.epl231.IDs911719.Lab05;

public class SingleList<E extends Comparable<? super E>> {

	private class ListNode<E> {
		private E obj;
		private ListNode<E> next;

		ListNode(E obj, ListNode<E> next) {
			this.obj = obj;
			this.next = next;
		}

		public E getElement() {
			return this.obj;
		}

		public String toString() {
			return this.obj.toString();
		}

	}

	private ListNode<E> head;
	private int size;

	public SingleList() {
		this.head = null;
		size = 0;
	}

	public void makeEmpty() {
		this.head = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int size() {
		return this.size;
	}

	public void insert(E obj) {
		ListNode<E> newNode = new ListNode<E>(obj, head);
		head = newNode;
		size += 1;
	}

	public void insertLast(E obj) {
		ListNode<E> newNode = new ListNode<E>(obj, null);
		ListNode<E> temp = this.head;
		if (head == null) {
			head = newNode;
		} else {
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
		}
		size += 1;
	}

	public void insertSorted(E obj) {
		System.out.println("Inserting " + obj);
		ListNode<E> newNode = new ListNode<E>(obj, head);
		ListNode<E> temp = this.head;
		if (isEmpty()) {
			this.head = newNode;
		} else if (temp.getElement().compareTo(obj) > 0) {
			newNode.next = temp;
			this.head = newNode;
		} else {
			while (temp != null) {
				if ((temp.getElement().compareTo(obj) < 0) || (temp.next == null)) {
					break;
				}
				temp = temp.next;
			}
			newNode.next = temp.next;
			temp.next = newNode;
		}
		size++;
	}

	public void delete(E obj) {
		if (!isEmpty()) {
			ListNode<E> tmp = this.head;
			ListNode<E> prev = tmp;
			// if first node is to be deleted
			if (tmp.getElement().equals(obj)) {
				this.head = this.head.next;
				size -= 1;
			} else {
				while (tmp != null) {
					if (tmp.getElement().equals(obj)) {
						prev.next = tmp.next;
						size -= 1;
						break;
					}
					prev = tmp;
					tmp = tmp.next;
				}
			}
		}
	}

	public ListNode<E> findNode(E obj) {
		/*** ADD YOUR CODE HERE ***/
		return null;
	}

	public ListNode<E> getFrontNode() {
		return this.head;
	}

	public boolean existsNode(E obj) {
		/*** ADD YOUR CODE HERE ***/
		return false;
	}

	public void print() {
		ListNode<E> tmp = this.head;
		for (int i = this.size - 1; i >= 0; i--) {
			System.out.print(tmp.getElement() + " ");
			tmp = tmp.next;
		}
		System.out.println("");
	}

	public String toString() {
		String s = new String();
		ListNode<E> tmp = this.head;
		for (int i = this.size - 1; i >= 0; i--) {
			s += tmp.getElement() + " ";
			tmp = tmp.next;
		}
		return s;
	}

	ListNode<E> findMergePoint1(SingleList<E> list2) {
		ListNode<E> mergePoint = null;
		ListNode<E> temp1 = this.head;
		ListNode<E> temp2 = list2.head;
		while (temp1.next != null) {
			while (temp2.next != null) {
				if (temp1.getElement() == temp2.getElement()) {
					mergePoint = temp1;
					boolean found = true;
					while (temp1.next != null) {
						if (temp1.next.getElement() != temp2.next.getElement()) {
							found = false;
						}
						temp1 = temp1.next;
						temp2 = temp2.next;
					}
					if (!found) {
						mergePoint = null;
					}
				}
				temp2 = temp2.next;
			}
			temp1 = temp1.next;
		}
		return mergePoint;
	}

	ListNode<E> findMergePoint2(SingleList<E> list2) {
		ListNode<E> mergePoint = null;

		return mergePoint;
	}

	public static void main(String[] args) {

		SingleList<Integer> list = new SingleList<Integer>();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insertLast(5);
		list.print();

		list.delete(3);
		list.print();
		// *************************************
		SingleList<Integer> sorted_list = new SingleList<Integer>();
		sorted_list.insertSorted(3);
		sorted_list.print();
		sorted_list.insertSorted(1);
		sorted_list.print();
		sorted_list.insertSorted(4);
		sorted_list.print();
		sorted_list.insertSorted(5);
		sorted_list.print();
		sorted_list.insertSorted(2);
		sorted_list.print();
		sorted_list.delete(1);
		sorted_list.print();

		// *************************************
		SingleList<Integer> list1 = new SingleList<>();
		SingleList<Integer> list2 = new SingleList<>();

		list1.insertLast(3);
		list1.insertLast(6);
		list1.insertLast(9);
		list2.insertLast(10);
		list1.insertLast(15);
		list2.insertLast(15);
		list1.insertLast(30);
		list2.insertLast(30);
		list1.insertLast(100);
		list2.insertLast(100);
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list1.findMergePoint1(list2));
		System.out.println(list1.findMergePoint2(list2));

	}
}
