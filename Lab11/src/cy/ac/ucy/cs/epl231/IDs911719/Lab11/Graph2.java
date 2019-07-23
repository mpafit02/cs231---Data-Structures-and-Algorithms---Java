package cy.ac.ucy.cs.epl231.IDs911719.Lab11;

import java.util.ArrayList;

/*************************************************************************
 *  Compilation:  javac Graph.java        
 *  Execution:    java Graph input.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges 
 *  0: 6 2 1 5 
 *  1: 0 
 *  2: 0 
 *  3: 5 4 
 *  4: 5 6 3 
 *  5: 3 4 0 
 *  6: 0 4 
 *  7: 8 
 *  8: 7 
 *  9: 11 10 12 
 *  10: 9 
 *  11: 9 12 
 *  12: 11 9 
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges 
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15 
 *  1: 220 203 200 194 189 164 150 130 107 72 
 *  2: 141 110 108 86 79 51 42 18 14 
 *  ...
 *  
 *************************************************************************/

/**
 * The Graph class represents an undirected graph of vertices named 0 through
 * V-1. It supports the following operations: add an edge to the graph, iterate
 * over all of the neighbors adjacent to a vertex. Parallel edges and self-loops
 * are permitted.
 */
public class Graph2 {
	private final int V;
	private int E;
	private double weights[][];

	/**
	 * Create an empty graph with V vertices.
	 * 
	 * @throws java.lang.IllegalArgumentException
	 *             if V < 0
	 */
	public Graph2(int V) {
		if (V <= 0)
			throw new IllegalArgumentException("Number of vertices must be > 1");
		this.V = V;
		this.E = 0;
		weights = new double[V][V];
	}

	/**
	 * Return the number of vertices in the graph.
	 */
	public int V() {
		return V;
	}

	/**
	 * Return the number of edges in the graph.
	 */
	public int E() {
		return E;
	}

	/**
	 * Add the undirected edge v-w to graph.
	 * 
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless both 0 <= v < V and 0 <= w < V
	 */
	public void addDirectedEdge(int v, int w, double weight) {
		if (weight != 0) {
			if (v < 0 || v >= V)
				throw new IndexOutOfBoundsException();
			if (w < 0 || w >= V)
				throw new IndexOutOfBoundsException();
			if (weights[v][w] == 0)
				E++;
			weights[v][w] = weight;
		}
	}

	/**
	 * Add the undirected edge v-w to graph.
	 * 
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless both 0 <= v < V and 0 <= w < V
	 */
	public void addBidirectionalEdge(int v, int w, double weight) {
		addDirectedEdge(v, w, weight);
		addDirectedEdge(w, v, weight);
	}

	/**
	 * Add the undirected edge v-w to graph.
	 * 
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless both 0 <= v < V and 0 <= w < V
	 */
	public void removeEdge(int v, int w) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V)
			throw new IndexOutOfBoundsException();
		if (weights[v][w] != 0)
			E--;
		weights[v][w] = 0;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
				if (weights[i][j] != 0)
					result += ((char) (i + 65)) + "-" + ((char) (j + 65)) + ":"
							+ weights[i][j] + "\n";
		result += " \n\t";
		for (int i = 0; i < V; i++)
			result += ((char) (i + 65))+ "\t";
		result += " \n";
		for (int i = 0; i < V; i++){
			result += ((char) (i + 65))+ "\t";
			for (int j = 0; j < V; j++){
				result += weights[i][j] + "\t";
			}
			result += " \n";
		}
		return result;
	}

	private class PrimEdge {
		int v1;
		int v2;
		double weight;

		PrimEdge(int v1, int v2, double weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		public String toString() {
			return ((char) (v1 + 65)) + "-" + ((char) (v2 + 65)) + ":" + weight;
		}
	}

	public void prim() {

		//Î’Î¬Ï�Î¿Ï‚Â ÎµÎ½ÏŒÏ‚Â Î“Î” ÎµÎ¯Î½Î±Î¹Â Ï„Î¿Â Î¬Î¸Ï�Î¿Î¹ÏƒÎ¼Î±Â Ï„Ï‰Î½Â Î²Î±Ï�ÏŽÎ½Â ÏŒÎ»Ï‰Î½Â Ï„Ï‰Î½Â Î±ÎºÎ¼ÏŽÎ½Â Ï„Î¿Ï…
		double weight = 0;
		// ÎšÎ¿Ï�Ï…Ï†Î­Ï‚ Ï€Î¿Ï… Ï€Ï�Î¿ÏƒÏ„Î­Î¸Î·ÎºÎ±Î½ ÏƒÏ„Î¿ Î´Î­Î½Î´Ï�Î¿ (Î‘Ï�Ï‡Î¹ÎºÎ¬ ÏŒÎ»Î± â€œ0â€�)
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Â«Î Î¹Î¿ ÎšÎ¿Î½Ï„Î¹Î½ÏŒÏ‚ Î“ÎµÎ¯Ï„Î¿Î½Î±Ï‚Â» Î³Î¹Î± ÎºÎ¬Î¸Îµ i: Î‘Ï�Ï‡Î¹ÎºÎ¬ ÎºÎ±Î½Î­Î½Î±Ï‚
		int closest[] = new int[this.V];
		for (int i = 0; i < V; i++)
			closest[i] = -1;

		// Î‘Ï€ÏŒÏƒÏ„Î±ÏƒÎ· Î±Ï€ÏŒ Â«ÎšÎ¿Î½Ï„Î¹Î½. Î“ÎµÎ¯Ï„Î¿Î½Î±Â» Î³Î¹Î± ÎºÎ¬Î¸Îµ i: Î‘Ï�Ï‡Î¹ÎºÎ¬ Î¬Ï€ÎµÎ¹Ï�Î¿
		double distance[] = new double[this.V];
		for (int i = 0; i < V; i++)
			distance[i] = Double.MAX_VALUE; // Double.MAX_VALUE = 1.7976931348623157E308

		// Î¤Î¿ Î•Î“Î” Ï€Î¿Ï… Î¸Î­Î»Î¿Ï…Î¼Îµ Î½Î± ÎºÏ„Î¯ÏƒÎ¿Ï…Î¼Îµ (Ï€ÎµÏ�Î¹Î­Ï‡ÎµÎ¹ Î±ÎºÎ¼Î­Ï‚ (v1,v2,weight))
		ArrayList<PrimEdge> tree = new ArrayList<PrimEdge>();

		// EÏ€Î¹Î»Î¿Î³Î® v=0 ÏƒÎ±Î½ Î±Ï�Ï‡Î¹ÎºÎ® ÎºÎ¿Ï�Ï…Ï†Î®
		int v = 0;
		visited[v] = true; // Î¤ÏŽÏ�Î± Ï„Î¿ v Î±Î½Î®ÎºÎµÎ¹ ÏƒÏ„Î¿ Î´Î­Î½Î´Ï�Î¿

		// Î³Î¹Î± ÎºÎ¬Î¸Îµ ÎºÎ¿Ï�Ï…Ï†Î® v 
		/** ADD YOUR CODE HERE **/
		
		System.out.println("Î¤ÎµÎ»Î¹ÎºÏŒ Î•Î»Î¬Ï‡Î¹ÏƒÏ„Î¿Â Î“ÎµÎ½Î½Î·Ï„Î¿Ï�Î¹ÎºÏŒÂ Î”Î­Î½Î´Ï�Î¿ (Î•Î“Î”) Î¼Îµ Î²Î¬Ï�Î¿Ï‚ " + weight);
		for (int i = 0; i < tree.size(); i++)
			System.out.println(tree.get(i));

	}

	private int minVertex(boolean visited[], double distance[]) {
		int min = 0;
		double minimum = Double.MAX_VALUE;
		for (int i = 0; i < V; i++) {
			if (visited[i] == true)
				continue; // skip nodes already in MST
			if (distance[i] < minimum){
				min = i;
				minimum = distance[i];
			}
		}
		return min; // Return the minimum among all distances
	}

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		//Î•Î“Î”: Î Î±Ï�Î¬Î´ÎµÎ¹Î³Î¼Î± Î•ÎºÏ„Î­Î»ÎµÏƒÎ·Ï‚ Ï„Î¿Ï… Î±Î»Î³ÏŒÏ�Î¹Î¸Î¼Î¿Ï… Prim( Î•Ï�Î³Î±ÏƒÏ„Î®Ï�Î¹Î¿ Î® Î£ÎµÎ». 10-11)
		Graph2 g = new Graph2(6);
		g.addBidirectionalEdge(0, 1, 7);
		g.addBidirectionalEdge(0, 3, 9);
		g.addBidirectionalEdge(1, 2, 5);
		g.addBidirectionalEdge(1, 4, 1);
		g.addBidirectionalEdge(1, 5, 2);
		g.addBidirectionalEdge(2, 5, 6);
		g.addBidirectionalEdge(3, 4, 1);
		g.addBidirectionalEdge(4, 5, 2);
		// ----------------------------------------------
		// g.addBidirectionalEdge(0, 1, 15);
		// g.addBidirectionalEdge(0, 3, 16);
		// g.addBidirectionalEdge(0, 2, 18);
		// g.addBidirectionalEdge(0, 4, 33);
		// g.addBidirectionalEdge(1, 2, 14);
		// g.addBidirectionalEdge(1, 3, 11);
		// g.addBidirectionalEdge(1, 4, 17);
		// g.addBidirectionalEdge(4, 3, 14);
		// g.addBidirectionalEdge(3, 2, 19);
		// g.addBidirectionalEdge(4, 2, 18);
		//------------------------------------
		//Î•Î“Î”: Î‘ÏƒÎºÎ®ÏƒÎµÎ¹Ï‚ â€“ Î•ÎºÏ„ÎµÎ»Î­ÏƒÏ„Îµ Ï„Î¿Î½ Î±Î»Î³ÏŒÏ�Î¹Î¸Î¼Î¿ Prim( Page 34)
//		Graph g = new Graph(9);
//		g.addBidirectionalEdge(0, 1, 3);
//		g.addBidirectionalEdge(0, 4, 4);
//		g.addBidirectionalEdge(0, 3, 4);
//		
//		g.addBidirectionalEdge(1, 2, 10);
//		g.addBidirectionalEdge(1, 4, 2);
//		
//		g.addBidirectionalEdge(2, 5, 6);
//		
//		g.addBidirectionalEdge(3, 4, 5);
//		
//		g.addBidirectionalEdge(4, 5, 11);
//		g.addBidirectionalEdge(4, 6, 2);
//		
//		g.addBidirectionalEdge(5, 7, 1);
//		g.addBidirectionalEdge(5, 8, 3);
//		
//		g.addBidirectionalEdge(6, 7, 4);
//		g.addBidirectionalEdge(7, 8, 9);
		

		System.out.println(g);

		g.prim();

	}

}
