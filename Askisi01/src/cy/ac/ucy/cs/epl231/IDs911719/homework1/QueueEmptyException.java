package cy.ac.ucy.cs.epl231.IDs911719.homework1;

public class QueueEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	QueueEmptyException(String err){
		super(err);
	}
}
