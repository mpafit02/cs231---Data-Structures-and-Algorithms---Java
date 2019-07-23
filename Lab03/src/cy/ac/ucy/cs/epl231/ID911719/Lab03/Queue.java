package cy.ac.ucy.cs.epl231.ID911719.Lab03;

public interface Queue<E> {
	// Μέθοδοι προσβασης
	public int size(); 				// Αντιστοιχεί στην πράξη Length(Q)
	public boolean isEmpty(); 		// Αντιστοιχεί στην πράξη IsEmptyQueue(Q)
	public   E front()  		// Αντιστοιχεί στην πράξη Front(Q)
	throws QueueEmptyException; 	// Μήνυμα λάθους αν η ουρά είναι κενή
	// Μέθοδοι ανανέωσης
	public void enqueue (E  obj) 	// Αντιστοιχεί στην πράξη Enqueue(x, Q)
	throws QueueFullException;
	public   Object dequeue() 		// Αντιστοιχεί στην πράξη Dequeue(Q)
	throws QueueEmptyException; 	// Μήνυμα λάθους αν η ουρά είναι κενή
	}
