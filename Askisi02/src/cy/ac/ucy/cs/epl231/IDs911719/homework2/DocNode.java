package cy.ac.ucy.cs.epl231.IDs911719.homework2;

import java.io.Serializable;

public class DocNode implements Serializable {
	private static final long serialVersionUID = 1L;
	int value;
	DocNode next;
	DocNode skip;

	public DocNode(int value) {
		this.value = value;
		next = null;
		skip = null;
	}
}
