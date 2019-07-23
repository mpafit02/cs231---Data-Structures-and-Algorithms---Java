package cy.ac.ucy.cs.epl231.IDs911719.Lab07;

import cy.ac.ucy.cs.epl231.IDs911719.Lab07.ArrayQueue;
import cy.ac.ucy.cs.epl231.IDs911719.Lab07.Queue;
import cy.ac.ucy.cs.epl231.IDs911719.Lab07.QueueEmptyException;
import cy.ac.ucy.cs.epl231.IDs911719.Lab07.QueueFullException;

public class ArrayQueue<E> implements Queue<E> {
	// Î¥Î»Î¿Ï€Î¿Î¯Î·ÏƒÎ· Ï„Î·Ï‚ Java Î´Î¹Î±ÏƒÏ�Î½Î´ÎµÏƒÎ·Ï‚ Queue (Î±Ï€ÏŒ Ï„Î¿ Î£Ï‡Î®Î¼Î± 2.4) Î¼Îµ Ï€Î¯Î½Î±ÎºÎ±
	// ÏƒÏ„Î±Î¸ÎµÏ�Î¿Ï� Î¼Î®ÎºÎ¿Ï…Ï‚
	// ÎœÎµÏ„Î±Î²Î»Î·Ï„Î­Ï‚ ÏƒÏ„Î¹Î³Î¼Î¹Î¿Ï„Ï�Ï€Î¿Ï…
	public static final int MAXSIZE = 10000; // Î¤Î¿ Ï€Ï�Î¿ÎºÎ±Î¸Î¿Ï�Î¹ÏƒÎ¼Î­Î½Î¿ Î¼Î­Î³Î¹ÏƒÏ„Î¿ Î¼Î®ÎºÎ¿Ï‚
												// Î¿Ï…Ï�Î¬Ï‚
	private E[] T; // ÎŸ Î³ÎµÎ½Î¹ÎºÎµÏ…Î¼Î­Î½Î¿Ï‚ Ï€Î¯Î½Î±ÎºÎ±Ï‚ T ÏŒÏ€Î¿Ï… Î¸Î± ÎºÏ�Î±Ï„Î¿Ï�Î½Ï„Î±Î¹ Ï„Î± ÏƒÏ„Î¿Î¹Ï‡ÎµÎ¯Î±
					// Ï„Î·Ï‚ Î¿Ï…Ï�Î¬Ï‚
	private int f = 0; // Î”ÎµÎ¯ÎºÏ„Î·Ï‚ ÏƒÏ„Î¿Î½ ÎºÏŒÎ¼Î²Î¿ ÎºÎ¿Ï�Ï…Ï†Î®Ï‚ Ï„Î·Ï‚ Î¿Ï…Ï�Î¬Ï‚
	private int sz = 0; // Î¤Î¿ Ï„Ï�Î­Ï‡Î¿Î½ Î¼Î®ÎºÎ¿Ï‚ Ï„Î·Ï‚ Î¿Ï…Ï�Î¬Ï‚

	// ÎšÎ±Ï„Î±ÏƒÎºÎµÏ…Î±ÏƒÏ„Î­Ï‚
	public ArrayQueue() { // Î‘Ï�Ï‡Î¹ÎºÎ¿Ï€Î¿Î¯Î·ÏƒÎ· Î¿Ï…Ï�Î¬Ï‚ Î¼Îµ Ï€Ï�Î¿ÎºÎ±Î¸Î¿Ï�Î¹ÏƒÎ¼Î­Î½Î¿ Î¼Î­Î³Î¹ÏƒÏ„Î¿ Î¼Î®ÎºÎ¿Ï‚
		this(MAXSIZE);

	}

	public ArrayQueue(int MaxSize) { // Î‘Ï�Ï‡Î¹ÎºÎ¿Ï€Î¿Î¯Î·ÏƒÎ· Î¿Ï…Ï�Î¬Ï‚ Î¼Îµ Î´ÎµÎ´Î¿Î¼Î­Î½Î¿ Î¼Î­Î³Î¹ÏƒÏ„Î¿
										// Î¼Î®ÎºÎ¿Ï‚
		T = (E[]) new Object[MaxSize];
	}

	// ÎœÎ­Î¸Î¿Î´Î¿Î¹
	public int size() { // Î•Ï€Î¹ÏƒÏ„Ï�Î­Ï†ÎµÎ¹ Ï„Î¿ Ï„Ï�Î­Ï‡Î¿Î½ Î¼Î®ÎºÎ¿Ï‚ Ï„Î·Ï‚ Î¿Ï…Ï�Î¬Ï‚
		return sz;
	}

	public boolean isEmpty() { // Î•Ï€Î¹ÏƒÏ„Ï�Î­Ï†ÎµÎ¹ true Î±Î½ ÎºÎ±Î¹ Î¼ÏŒÎ½Î¿ Î±Î½ Î· Î¿Ï…Ï�Î¬ ÎµÎ¯Î½Î±Î¹
								// ÎºÎµÎ½Î®
		return (sz == 0);
	}

	public void enqueue(E obj) // Î•Î¹ÏƒÎ±Î³Ï‰Î³Î® Î½Î­Î¿Ï… Î±Î½Ï„Î¹ÎºÎµÎ¹Î¼Î­Î½Î¿Ï… ÏƒÏ„Î·Î½ Î¿Ï…Ï�Î¬
			throws QueueFullException {
		if (sz == T.length)
			throw new QueueFullException("Î“Î­Î¼Î¹ÏƒÎµ Î· Î¿Ï…Ï�Î¬");
		int avail = (f + sz) % T.length;
		T[avail] = obj;
		sz++;
	}

	public E front() // Î•Ï€Î¹ÏƒÏ„Ï�Î¿Ï†Î® Î±Î½Ï„Î¹ÎºÎµÎ¹Î¼Î­Î½Î¿Ï… Î±Ï€ÏŒ Ï„Î·Î½ Î¿Ï…Ï�Î¬
			throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Î—  Î¿Ï…Ï�Î¬ ÎµÎ¯Î½Î±Î¹ ÎºÎµÎ½Î®");
		return T[f];
	}

	public E dequeue() // Î•Ï€Î¹ÏƒÏ„Ï�Î¿Ï†Î® ÎºÎ±Î¹ Î´Î¹Î±Î³Ï�Î±Ï†Î® Î±Î½Ï„Î¹ÎºÎµÎ¹Î¼Î­Î½Î¿Ï… Î±Ï€ÏŒ Ï„Î·Î½ Î¿Ï…Ï�Î¬
			throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Î—  Î¿Ï…Ï�Î¬ ÎµÎ¯Î½Î±Î¹ ÎºÎµÎ½Î®");
		E answer = T[f];
		T[f] = null;
		f = (f + 1) % T.length;
		sz--;
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Î Î±Ï�Î¬Î´ÎµÎ¹Î³Î¼Î± Î§Ï�Î®ÏƒÎ·Ï‚ Ï„Î·Ï‚ ÏƒÏ„Î¿Î¯Î²Î±Ï‚
		Queue<Integer> Q = new ArrayQueue<>(); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: ()
		try {
			Q.enqueue(5); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (5)
			Q.enqueue(3); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (5, 3)
			System.out.println(Q.size()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (5, 3) Î”Î¯Î½ÎµÎ¹ 2
			System.out.println(Q.dequeue()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (3) Î•Î¾Î¬Î³ÎµÎ¹ 5
			System.out.println(Q.isEmpty()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (3) Î”Î¯Î½ÎµÎ¹ false
			System.out.println(Q.dequeue()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: () Î•Î¾Î¬Î³ÎµÎ¹ 3
			System.out.println(Q.isEmpty()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: () Î•Î¾Î¬Î³ÎµÎ¹ true
			// System.out.println(Q.dequeue()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: () Exception
			Q.enqueue(7); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (7)
			Q.enqueue(9); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (7, 9)
			System.out.println(Q.front()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (7, 9) Î”Î¯Î½ÎµÎ¹ 7
			Q.enqueue(4); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (7, 9, 4)
			System.out.println(Q.size()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (7, 9, 4) Î•Î¾Î¬Î³ÎµÎ¹ 3
			System.out.println(Q.dequeue()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (9, 4) Î•Î¾Î¬Î³ÎµÎ¹ 7
			Q.enqueue(6); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (9, 4 6)
			Q.enqueue(8); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (9, 4, 6, 8)
			System.out.println(Q.dequeue()); // Î ÎµÏ�Î¹Î­Ï‡ÎµÎ¹: (4, 6, 8) Î•Î¾Î¬Î³ÎµÎ¹ 9
		} catch (QueueEmptyException e) {
			System.out.println(e);
		} catch (QueueFullException ee) {
			System.out.println(ee);
		}
	}

}
