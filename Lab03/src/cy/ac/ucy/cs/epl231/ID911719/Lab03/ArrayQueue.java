package cy.ac.ucy.cs.epl231.ID911719.Lab03;

import cy.ac.ucy.cs.epl231.ID911719.Lab03.ArrayQueue;
import cy.ac.ucy.cs.epl231.ID911719.Lab03.Queue;
import cy.ac.ucy.cs.epl231.ID911719.Lab03.QueueEmptyException;
import cy.ac.ucy.cs.epl231.ID911719.Lab03.QueueFullException;

public class ArrayQueue<E> implements Queue<E> {
	// Υλοποίηση της Java διασύνδεσης Queue (από το Σχήμα 2.4) με πίνακα
	// σταθερού μήκους
	// Μεταβλητές στιγμιοτύπου
	public static final int MAXSIZE = 10000; // Το προκαθορισμένο μέγιστο μήκος
												// ουράς
	private E[] T; // Ο γενικευμένος πίνακας T όπου θα κρατούνται τα στοιχεία
					// της ουράς
	private int f = 0; // Δείκτης στον κόμβο κορυφής της ουράς
	private int sz = 0; // Το τρέχον μήκος της ουράς

	// Κατασκευαστές
	public ArrayQueue() { // Αρχικοποίηση ουράς με προκαθορισμένο μέγιστο μήκος
		this(MAXSIZE);

	}

	public ArrayQueue(int MaxSize) { // Αρχικοποίηση ουράς με δεδομένο μέγιστο
										// μήκος
		T = (E[]) new Object[MaxSize];
	}

	// Μέθοδοι
	public int size() { // Επιστρέφει το τρέχον μήκος της ουράς
		return sz;
	}

	public boolean isEmpty() { // Επιστρέφει true αν και μόνο αν η ουρά είναι
								// κενή
		return (sz == 0);
	}

	public void enqueue(E obj) // Εισαγωγή νέου αντικειμένου στην ουρά
			throws QueueFullException {
		if (sz == T.length)
			throw new QueueFullException("Γέμισε η ουρά");
		int avail = (f + sz) % T.length;
		T[avail] = obj;
		sz++;
	}

	public E front() // Επιστροφή αντικειμένου από την ουρά
			throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Η  ουρά είναι κενή");
		return T[f];
	}

	public E dequeue() // Επιστροφή και διαγραφή αντικειμένου από την ουρά
			throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Η  ουρά είναι κενή");
		E answer = T[f];
		T[f] = null;
		f = (f + 1) % T.length;
		sz--;
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Παράδειγμα Χρήσης της στοίβας
		Queue<Integer> Q = new ArrayQueue<>(); // Περιέχει: ()
		try {
			Q.enqueue(5); // Περιέχει: (5)
			Q.enqueue(3); // Περιέχει: (5, 3)
			System.out.println(Q.size()); // Περιέχει: (5, 3) Δίνει 2
			System.out.println(Q.dequeue()); // Περιέχει: (3) Εξάγει 5
			System.out.println(Q.isEmpty()); // Περιέχει: (3) Δίνει false
			System.out.println(Q.dequeue()); // Περιέχει: () Εξάγει 3
			System.out.println(Q.isEmpty()); // Περιέχει: () Εξάγει true
			// System.out.println(Q.dequeue()); // Περιέχει: () Exception
			Q.enqueue(7); // Περιέχει: (7)
			Q.enqueue(9); // Περιέχει: (7, 9)
			System.out.println(Q.front()); // Περιέχει: (7, 9) Δίνει 7
			Q.enqueue(4); // Περιέχει: (7, 9, 4)
			System.out.println(Q.size()); // Περιέχει: (7, 9, 4) Εξάγει 3
			System.out.println(Q.dequeue()); // Περιέχει: (9, 4) Εξάγει 7
			Q.enqueue(6); // Περιέχει: (9, 4 6)
			Q.enqueue(8); // Περιέχει: (9, 4, 6, 8)
			System.out.println(Q.dequeue()); // Περιέχει: (4, 6, 8) Εξάγει 9
		} catch (QueueEmptyException e) {
			System.out.println(e);
		} catch (QueueFullException ee) {
			System.out.println(ee);
		}
	}

}
