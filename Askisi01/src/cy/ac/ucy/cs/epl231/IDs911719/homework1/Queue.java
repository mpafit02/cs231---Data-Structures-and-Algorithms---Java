package cy.ac.ucy.cs.epl231.IDs911719.homework1;

public interface Queue<E> {
	// Μέθοδοι π�?οσβασης
	public int size(); 				// Αντιστοιχεί στην π�?άξη Length(Q)
	public boolean isEmpty(); 		// Αντιστοιχεί στην π�?άξη IsEmptyQueue(Q)
	public   E front()  		// Αντιστοιχεί στην π�?άξη Front(Q)
	throws QueueEmptyException; 	// Μήνυμα λάθους αν η ου�?ά είναι κενή
	// Μέθοδοι ανανέωσης
	public void enqueue (E  obj) 	// Αντιστοιχεί στην π�?άξη Enqueue(x, Q)
	throws QueueFullException;
	public   Object dequeue() 		// Αντιστοιχεί στην π�?άξη Dequeue(Q)
	throws QueueEmptyException; 	// Μήνυμα λάθους αν η ου�?ά είναι κενή
	}
