package cy.ac.ucy.cs.epl231.IDs911719.homework2;

import java.io.Serializable;

public class Node implements Serializable {
	private static final long serialVersionUID = 1L;
	Word value;
	Node left;
	Node right;

	public Node(Word value) {
		this.value = value;
		right = null;
		left = null;
	}
}