package cy.ac.ucy.cs.epl231.IDs911719.homework1;

import cy.ac.ucy.cs.epl231.IDs911719.homework1.ArrayQueue;
import cy.ac.ucy.cs.epl231.IDs911719.homework1.Queue;
import cy.ac.ucy.cs.epl231.IDs911719.homework1.QueueEmptyException;
import cy.ac.ucy.cs.epl231.IDs911719.homework1.QueueFullException;

public class ArrayQueue<E> implements Queue<E> {
	// Variables
	public static final int MAXSIZE = 10000; // Maxsize of the queue
	private E[] T; // T array where the content of the queue will be
	private int f = 0; // Show the top of the queue
	private int sz = 0; // Current length of the queue
	private int waitingTime = 0;
	private int clock = 0;
	private int queueID = 0;

	// Constructors
	public ArrayQueue() { // Initizlize queue to mmaximum size
		this(MAXSIZE);

	}

	public ArrayQueue(int MaxSize) { // Initialize the queue with specific length
		T = (E[]) new Object[MaxSize];
	}

	// Methods
	public int size() { // Return the current size of the queue
		return sz;
	}

	public boolean isEmpty() { // Return true if the queue is empty
		return (sz == 0);
	}

	public int getQueueID() { // Get queue's unique ID
		return queueID;
	}

	public int getClock() { // Get the clock of the Queue
		return clock;
	}

	public int getWaitingTime() { // Get the waiting time for the queue
		return waitingTime;
	}

	public void setQueueID(int queueID) { // Set queue's unique ID
		this.queueID = queueID;
	}
	
	public void setWaitingTime(int waitingTime) { // Set the waiting time for the queue
		this.waitingTime = waitingTime;
	}

	public void increaseClock() { // Increase the clock for this Queue
		this.clock++;
	}

	public void resetClock() { // Reset the clock for the Queue
		this.clock = 0;
	}

	public boolean isReady() { // Check if the Queue is ready so I can get an object
		if (clock >= waitingTime) {
			return true;
		} else {
			return false;
		}
	}

	public void enqueue(E obj) // Add object E in the Queue
			throws QueueFullException {
		if (sz == T.length)
			throw new QueueFullException("Queue is Full!");
		int avail = (f + sz) % T.length;
		T[avail] = obj;
		sz++;
	}

	public E front() // Return object E from thw Queue
			throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Η  ου�?ά είναι κενή");
		return T[f];
	}

	public E dequeue() // Return and delete object E from the Queue
			throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Η  ου�?ά είναι κενή");
		E answer = T[f];
		T[f] = null;
		f = (f + 1) % T.length;
		sz--;
		return answer;
	}

}
