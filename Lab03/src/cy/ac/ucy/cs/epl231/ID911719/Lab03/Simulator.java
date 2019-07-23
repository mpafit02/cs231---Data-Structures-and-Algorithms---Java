package cy.ac.ucy.cs.epl231.ID911719.Lab03;

import java.util.Random;

public class Simulator {

	public static void main(String[] args) {
		ArrayQueue<Element> myQueue = new ArrayQueue<>(100);
		int time = 0;
		int SimulationTime = 100;
		int arrivalProbability = 50;
		Random rnd = new Random(System.currentTimeMillis());
		try {
			while (time <= SimulationTime) {
				int i = rnd.nextInt(100);
				if (i < arrivalProbability) {
					//System.out.println("Add value "+i+" into Queue");
					System.out.print(".");
					myQueue.enqueue(new Element(i, time));
				}
				time++;
			}
			System.out.print("\n");
			
			while (!myQueue.isEmpty())
				System.out.println(myQueue.dequeue());
		} catch (QueueFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
