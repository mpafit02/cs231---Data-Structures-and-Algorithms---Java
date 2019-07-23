package cy.ac.ucy.cs.epl231.ID911719.Lab03;

import java.util.Random;

public class FastFoodSimulator {
	public static void main(String[] args) {
		ArrayQueue<Customer> myQueue = new ArrayQueue<>(100);
		int time = 0;
		int SimulationTime = 10;
		int arrivalPropability = 50;
		final int SERVICE_DELAY = 5;
		int remainingTime = 0;
		Customer c = null;
		Random rnd = new Random(System.currentTimeMillis());
		try {
			while (time <= SimulationTime) {
				int i = rnd.nextInt(100);
				System.out.print(time + ":\t|");
				if (i < arrivalPropability) {
					// System.out.println("Add value "+i+" into Queue");
					System.out.print(" \t" + time + "\t|");
					myQueue.enqueue(new Customer(time));
				} else {
					System.out.print("\t--\t|");
				}
				if (remainingTime == 0 && !myQueue.isEmpty()) {
					c = myQueue.dequeue();
					System.out.print(" \t" + c + "\t");
					System.out.print("|" + "\t" + (time - c.getTimeOfArrival()) + "\t");
					remainingTime = SERVICE_DELAY;
				} else {
					System.out.print("\t" + c + "(" + (time - c.getTimeOfArrival()) + ")\t");
				}
				remainingTime = (remainingTime > 0) ? --remainingTime : 0;
				time++;
				System.out.println();
			}
		} catch (QueueFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
