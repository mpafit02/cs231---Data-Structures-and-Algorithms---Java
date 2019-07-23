package cy.ac.ucy.cs.epl231.IDs911719.Lab07;

public interface Queue<E> {
	// �?έθοδοι π�?�?σβασης
	public int size(); 				// Αντιστοιχεί στην π�?άξη Length(Q)
	public boolean isEmpty(); 		// Αντιστοιχεί στην π�?άξη IsEmptyQueue(Q)
	public   E front()  		// Αντιστοιχεί στην π�?άξη Front(Q)
	throws QueueEmptyException; 	// �?ήνυμα λάθους αν η ου�?ά είναι κενή
	// �?έθοδοι ανανέωσης
	public void enqueue (E  obj) 	// Αντιστοιχεί στην π�?άξη Enqueue(x, Q)
	throws QueueFullException;
	public   E dequeue() 		// Αντιστοιχεί στην π�?άξη Dequeue(Q)
	throws QueueEmptyException; 	// �?ήνυμα λάθους αν η ου�?ά είναι κενή
	}
