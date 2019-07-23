package cy.ac.ucy.cs.epl231.IDs911719.Lab09;

import java.util.Stack;

public class SortingStack<T extends Comparable<T>> {
	// *** Sorting Stack Using Recursion ***//
	// Algorithm:
	// sortStack(stack S)
	// if stack is not empty:
	// temp = pop(S);
	// sortStack(S);
	// sortedInsert(S, temp);
	//
	// sortedInsert(Stack S, element)
	// if stack is empty OR element > top element
	// S.push(elem)
	// else
	// temp = S.pop()
	// sortedInsert(S, element)
	// S.push(temp)

	// Recursive Method to insert an item x in sorted way
	void sortedInsert(Stack<T> s, T x) {
		System.out.print(s);
		System.out.println(": sortedInsert:x=" + x);
		/*** ADD YOUR CODE HERE ***/
		T temp;
		if (s.isEmpty() || (x.compareTo(s.peek()) > 0)) {
			s.push(x);
		} else {
			temp = s.pop();
			sortedInsert(s, x);
			s.push(temp);
		}
	}

	void sortStack(Stack<T> s) {
		/*** ADD YOUR CODE HERE ***/
		T temp;
		if (!s.isEmpty()) {
			temp = s.pop();
			sortStack(s);
			sortedInsert(s, temp);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortingStack<Integer> ss = new SortingStack<>();
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.push(9);
		stack.push(8);
		stack.push(7);
		stack.push(6);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);

		System.out.println(stack);
		ss.sortStack(stack);
		System.out.println(stack);
	}

}
