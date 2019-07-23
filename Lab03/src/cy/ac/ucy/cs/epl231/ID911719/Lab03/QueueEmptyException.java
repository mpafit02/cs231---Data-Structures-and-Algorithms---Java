package cy.ac.ucy.cs.epl231.ID911719.Lab03;

public class QueueEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	QueueEmptyException(String err){
		super(err);
	}
}
