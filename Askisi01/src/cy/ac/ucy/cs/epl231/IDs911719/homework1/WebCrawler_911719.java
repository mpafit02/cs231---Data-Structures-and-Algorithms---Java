package cy.ac.ucy.cs.epl231.IDs911719.homework1;

import cy.ac.ucy.cs.epl231.IDs911719.homework1.ArrayQueue;
import cy.ac.ucy.cs.epl231.IDs911719.homework1.QueueEmptyException;
import cy.ac.ucy.cs.epl231.IDs911719.homework1.QueueFullException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebCrawler_911719 {

	static int counter = 0; // Counter for the manyUrls list
	static int clock = 0;
	static int duration = 50; // Clock cycles in order to finish
	static int N = 30; // Amount of URLs I read each time
	static int F = 10; // Amount of Front Queues
	static int B = 20; // Amount of Back Queues
	static int[] URLCounter;
// Detailed
	static boolean printDetailed = true;
	static boolean printQueues = false;
	static boolean printSimple = false;
	
//// Brief
//	static boolean printDetailed = false;
//	static boolean printQueues = false;
//	static boolean printSimple = true;

//// Fully Detailed
//	static boolean printDetailed = true;
//	static boolean printQueues = true;
//	static boolean printSimple = false;
	public static void main(String[] args) {
		// Create the two arrays with the 15 queues inside
		ArrayQueue<Url_911719>[] frontQueues = new ArrayQueue[F]; // Front Queues
		ArrayQueue<Url_911719>[] backQueues = new ArrayQueue[B + F]; // Back Queues
		ArrayQueue<ArrayQueue<Url_911719>> D = new ArrayQueue(B + F); // Queue to find the order for the back Queues
		ArrayList<String> top20Domains = new ArrayList<String>(20); // Top Domains array list
		ArrayList<String> manyURLs = new ArrayList<String>(500); // Many URLs array list
		Url_911719 urlToPass = null; // URL to pass from back Queue to front Queue
		URLCounter = new int[F];
		int[] sequence = new int[F]; // Used to find how to take URLs from back queues
		String createFQueues = ""; // Used for output
		String fQueues = ""; // Used for output
		String bQueues = ""; // Used for output
		int movedToF = 0; // Used for output
		int movedToB = 0; // Used for output
		resetURLCounter();

		// Create the Queues
		for (int i = 0; i < frontQueues.length; i++) {
			frontQueues[i] = new ArrayQueue<Url_911719>(duration * 500);
		}
		for (int i = 0; i < backQueues.length; i++) {
			backQueues[i] = new ArrayQueue<Url_911719>(duration * 500);
		}

		// Read URLs from file
		File fileTop20Urls = new File("src\\Top20Domains.txt");
		File fileManyUrls = new File("src\\manyURLs.txt");
		try {
			// Read URLs from top domains
			Scanner scan = new Scanner(fileTop20Urls);
			while (scan.hasNextLine()) {
				int num = Integer.parseInt(scan.next()) - 1;
				String link = scan.next();
				top20Domains.add(link);
				Url_911719 url = new Url_911719(link, 0); // Create a new URL
				try {
					backQueues[num].enqueue(url);
				} catch (QueueFullException e) {
					e.printStackTrace();
				}
			}
			scan.close();
			// Read URLs from many URLs
			scan = new Scanner(fileManyUrls);
			while (scan.hasNextLine()) {
				manyURLs.add(scan.nextLine()); // Put URLs in manyURLs array list
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Sequence to transfer URLs from front queues to back queues
		for (int i = 1; i <= F; i++) {
			sequence[i - 1] = F / i / 2 + 1;
		}

		// Create D Queue
		for (int i = 0; i < F + B; i++) {
			try {
				backQueues[i].setQueueID(i);
				D.enqueue(backQueues[i]);
			} catch (QueueFullException e) {
				e.printStackTrace();
			}
		}

		// Generate random waiting time for each back queue
		for (int i = 0; i < F + B; i++) {
			backQueues[i].setWaitingTime((int) (Math.random() * F * 2 + 2));
		}
		// Output
		if (printSimple) {
			System.out.print("|Clock| Add to F | Move to B | Src - Dest | Output URL Link ");
		}
		// Loop until we clock == duration
		while (clock != duration) {
			movedToF = 0;
			movedToB = 0;
			resetURLCounter();
			if(printDetailed) {
				System.out.println("\nClock: " + clock);
				System.out.println("--------------------------------------------------------------------");
			}
			// Add URL from Back Queue into the Front Queue
			if (urlToPass != null) {
				try {
					frontQueues[urlToPass.getPriority()].enqueue(urlToPass); // Put the URL back in Front Queue
					movedToF++; // Count how many URLs have moved to Front
				} catch (QueueFullException e) {
					e.printStackTrace();
				}
				if (printQueues) {
					try {
						System.out.println("Front Queue " + urlToPass.getPriority() + ":");
						for (int j = 0; j < frontQueues[urlToPass.getPriority()].size(); j++) {
							Url_911719 url2 = frontQueues[urlToPass.getPriority()].dequeue();
							System.out.println("\t" + url2.getLink());
							try {
								frontQueues[urlToPass.getPriority()].enqueue(url2);
							} catch (QueueFullException e) {
								e.printStackTrace();
							}
						}
						System.out.println("--------------------------------------------------------------------");
					} catch (QueueEmptyException e) {
						continue;
					}
					System.out.println();
				}
				urlToPass = null;
			}
			// Reading URLs from many URLs and put them in Front Queues
			if (counter < manyURLs.size()) {
				for (int i = 0; i < N; i++) {
					if (counter < manyURLs.size()) {
						createURL(manyURLs, counter, frontQueues);
						movedToF++;
						counter++;
					}
				}
			}
			// Output
			if (printDetailed) {
				createFQueues = printFrontQueuesStatus(frontQueues);
			}
			// Transfer URLs from Front Queues to Back Queues
			for (int i = 0; i < F; i++) {
				for (int j = 0; j < sequence[i]; j++) {
					try {
						if (!frontQueues[i].isEmpty()) {
							Url_911719 url = frontQueues[i].dequeue();
							// Find if it exists in one of top 20 Domains
							boolean found = false;
							int pos = 0;
							for (int k = 0; k < top20Domains.size(); k++) {
								if (top20Domains.get(k).equals(url.getDomain())) {
									pos = k;
									found = true;
								}
							}
							if (found) {
								try {
									backQueues[pos].enqueue(url);
								} catch (QueueFullException e) {
									e.printStackTrace();
								}
							} else {
								try {
									// Calculate the position for the new URL
									backQueues[B + url.getPriority()].enqueue(url);
								} catch (QueueFullException e) {
									e.printStackTrace();
								}
							}
							movedToB++;
						}
					} catch (QueueEmptyException e) {
						continue;
					}
				}
			}
			// Output
			if (printDetailed) {
				fQueues = printFrontQueuesStatus(frontQueues);
				bQueues = printBackQueuesStatus(backQueues);
				System.out.println("Front Queues before transfer to Back Queues: " + createFQueues);
				System.out.println("--------------------------------------------------------------------");
				System.out.println("Current URL status in Front Queues:          " + fQueues);
				System.out.println("--------------------------------------------------------------------");
				System.out.println("Current URL status in Back Queues:           " + bQueues);
				System.out.println("--------------------------------------------------------------------");
			}
			if (printSimple) {
				System.out.printf("\n| %3d |    %2d    |     %2d    |", clock, movedToF, movedToB);
			}
			// Find who is ready from the Back Queues
			boolean found = false;
			int tries = 0;
			while (!found && (tries < (F + B))) {
				try {
					ArrayQueue<Url_911719> tempQueue = D.dequeue();
					// In case the back queue is ready
					if (tempQueue.isReady()) {
						// Output
						if (printQueues) {
							if (!printDetailed) {
								System.out.println("\nClock: " + clock);
								System.out.println(
										"--------------------------------------------------------------------");
							}
							try {
								System.out.println("\nBack Queue " + tempQueue.getQueueID() + ":");
								for (int j = 0; j < backQueues[tempQueue.getQueueID()].size(); j++) {
									Url_911719 url1 = backQueues[tempQueue.getQueueID()].dequeue();
									System.out.println("\t" + url1.getLink());
									try {
										backQueues[tempQueue.getQueueID()].enqueue(url1);
									} catch (QueueFullException e) {
										e.printStackTrace();
									}
								}
							} catch (QueueEmptyException e) {
								continue;
							}
							System.out.println();
						}
						// Search for a ready queue to take a URL
						try {
							// Get the URL from the Back Queue
							urlToPass = backQueues[tempQueue.getQueueID()].dequeue();
							// Output
							if (printDetailed) {
								System.out.println("B" + tempQueue.getQueueID() + ": " + urlToPass.getLink()
										+ "  Ready Time:" + tempQueue.getWaitingTime() + "  Waiting Time:"
										+ tempQueue.getClock() + "  Destinition: F" + urlToPass.getPriority() + "\n");
								System.out.println("====================================================================");
							}
							if (printSimple) {
								System.out.printf(" B%-2d -> F%-2d | %s", tempQueue.getQueueID(),
										urlToPass.getPriority(), urlToPass.getLink());
							}
							tempQueue.resetClock(); // Reset Clock
							found = true; // If a ready Queue is found
						} catch (QueueEmptyException e) {
							continue;
						}
					}
					try {
						D.enqueue(tempQueue); // Put temporary Queue back in D Queue
					} catch (QueueFullException e) {
						e.printStackTrace();
					}
				} catch (QueueEmptyException e) {
					continue;
				}
				tries++; // Count the tries before finding a ready Queue
			}
			if (!found) {
				urlToPass = null;
			}
			// Output
			if (printSimple) {
				if (!found) {
					System.out.print("    --      |     --");
				}
			}
			// Increase the clock for every Back Queue
			for (int i = 0; i < F + B; i++) {
				try {
					ArrayQueue<Url_911719> tempQueue = D.dequeue();
					tempQueue.increaseClock();
					try {
						D.enqueue(tempQueue);
					} catch (QueueFullException e) {
						e.printStackTrace();
					}
				} catch (QueueEmptyException e) {
					continue;
				}
			}
			clock++;
		}
	}

	// Methods
	// Calculate a number for priority
	public static int prioritizer() {
		// Priority of a URL (0 - (F - 1))
		return (int) (Math.random() * F);
	}

	// Create URLs - Using Url_911719 class
	public static void createURL(ArrayList<String> manyURLs, int counter, ArrayQueue<Url_911719>[] frontQueues) {
		int priority = prioritizer();
		Url_911719 url = new Url_911719(manyURLs.get(counter), priority); // Create a new URL
		try {
			frontQueues[priority].enqueue(url);
			URLCounter[priority]++;
		} catch (QueueFullException e) {
			e.printStackTrace();
		}
	}

	public static void resetURLCounter() {
		for (int i = 0; i < F; i++) {
			URLCounter[i] = 0;
		}
	}

	public static String printURLCounter() {
		String str = "";
		for (int i = 0; i < F; i++) {
			if (URLCounter[i] == 0) {
				str += "F" + i + ":- ";
			} else {
				str += "F" + i + ":" + URLCounter[i] + " ";
			}
		}
		return str;
	}

	public static String printFrontQueuesStatus(ArrayQueue<Url_911719>[] frontQueues) {
		String str = "";
		for (int i = 0; i < F; i++) {
			if (frontQueues[i].size() == 0) {
				str += "F" + i + ":- ";
			} else {
				str += "F" + i + ":" + frontQueues[i].size() + " ";
			}
		}
		return str;
	}

	public static String printBackQueuesStatus(ArrayQueue<Url_911719>[] backQueues) {
		String str = "";
		for (int i = 0; i < B + F; i++) {
			if (backQueues[i].size() == 0) {
				str += "B" + i + ":- ";
			} else {
				str += "B" + i + ":" + backQueues[i].size() + " ";
			}
		}
		return str;
	}
}
