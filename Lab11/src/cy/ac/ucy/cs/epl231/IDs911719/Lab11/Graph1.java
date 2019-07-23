package cy.ac.ucy.cs.epl231.IDs911719.Lab11;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
public class Graph1 {
	private final int V;
	private int E;
	private Set<Integer>[] adj;

	/**
	 * Create an empty graph with V vertices.
	 * 
	 * @throws java.lang.IllegalArgumentException if V < 0
	 */
	public Graph1(int V) {
		if (V <= 0)
			throw new IllegalArgumentException("Number of vertices must be > 1");
		this.V = V;
		this.E = 0;
		adj = new Set[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new TreeSet<Integer>();
		}
	}

	/**
	 * Create a random graph with V vertices and E edges. Expected running time is
	 * proportional to V + E.
	 * 
	 * @throws java.lang.IllegalArgumentException if either V < 0 or E < 0
	 */
	public Graph1(int V, int E) {
		this(V);
		if (E <= 0)
			throw new IllegalArgumentException("Number of edges must be > 0");
		for (int i = 0; i < E; i++) {
			int v = (int) (Math.random() * V);
			int w = (int) (Math.random() * V);
			addEdge(v, w);
		}
	}

	/**
	 * Create a digraph from input stream.
	 */
	public Graph1(Scanner in) {
		this(in.nextInt()); // The number of Nodes from the File
		int E = in.nextInt(); // The number of Edges from the File
		for (int i = 0; i < E; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			addEdge(v, w);
		}
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
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w
	 *         < V
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V)
			throw new IndexOutOfBoundsException();
		E++;
		adj[v].add(w);
		// adj[w].add(v);
	}

	/**
	 * Return the list of neighbors of vertex v as in Iterable.
	 * 
	 * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		return adj[v];
	}

	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		String s = "";
		s += V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (int w : adj[v]) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}

	private int findmin(int[] degrees, List<Integer> visited) {
		int min = 0;
		for (int x = 0; x < degrees.length; x++)
			min = (!visited.contains(x) && degrees[x] <= degrees[min]) ? x : min;
		return min;
	}

	public void topologicalSort() {
		int indegree[] = new int[V];
		// For All the Nodes Count the Number of incoming Edges
		for (int i = 0; i < adj.length; i++) {
			for (int to : adj[i]) {
				indegree[to]++;
			}
		}

		List<Integer> visited = new LinkedList<Integer>();
		System.out.println();
		while (true) {
			int nxt = findmin(indegree, visited);
			if (nxt == 0 && visited.contains(0)) {
				if (visited.size() < adj.length) {
					System.out.println("Circle");
				} else {
					System.out.println("Done");
				}
				break;
			}
			System.out.print(nxt + " ");
			for (int t : adj[nxt]) {
				indegree[t]--;
			}
			visited.add(nxt);
		}
		System.out.println();
		/** ADD YOUR CODE HERE **/
	}

	// prints BFS traversal from a given source s
	void BFS(int s) {
		// Mark all the vertices as not visited(By default set as false)
		boolean visited[] = new boolean[V];
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);
		while (!queue.isEmpty()) {
			s = queue.poll();
			System.out.print(s + " ");
			for (int t : adj[s]) {
				if (!visited[t]) {
					visited[t] = true;
					queue.add(t);
				}
			}
		}
	}

	// A function used by DFS
	void DFSr(int v, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");
		for (int t : adj[v]) {
			if (!visited[t]) {
				DFSr(t, visited);
			}
		}
		/** ADD YOUR CODE HERE **/
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	void DFS(int v) {
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[V];
		// Call the recursive helper function to print DFS traversal
		DFSr(v, visited);
	}

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		try {
			InputStream input;
			input = new FileInputStream("src/graph_13.txt");
			Graph1 G1 = new Graph1(new Scanner(input));
			System.out.println(G1);
			System.out.println();
			G1.topologicalSort();

			input = new FileInputStream("src/graph_BFS.txt");
			Graph1 G2 = new Graph1(new Scanner(input));
			System.out.println(G2);
			System.out.println();
			G2.BFS(0);
			System.out.println();
			G2.DFS(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
