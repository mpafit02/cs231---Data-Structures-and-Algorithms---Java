package cy.ac.ucy.cs.epl231.IDs911719.homework3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Graph implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int N = 30;
	private final int V;
	private int E;
	private Set<String>[] adj;
	private ArrayList<String> urls;

	/**
	 * Creates an empty graph with V vertices
	 */
	public Graph(int V) {
		if (V <= 0)
			throw new IllegalArgumentException("Number of vertices must be > 1");
		this.V = V;
		this.E = 0;
		adj = new Set[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new TreeSet<String>();
		}
	}

	/**
	 * Creates a graph from an array of urls
	 */
	public Graph(ArrayList<String> urls) {
		this(urls.size()); // The number of Nodes from the File
		this.urls = urls;
		int rand, e;// edjes for eache verticies
		ArrayList<Integer> edges;
		Random random = new Random();
		for (int i = 0; i < V; i++) {
			edges = new ArrayList<>();
			e = random.nextInt(N) + 1; // +1 because we don't want 0 links
			for (int j = 0; j < e; j++) {
				rand = random.nextInt(V);
				while (edges.contains(rand)) {
					rand = random.nextInt(V);
				}
				edges.add(rand);
				addEdge(i, urls.get(rand));
			}
			E += e;
		}
	}

	/**
	 * Return the number of vertices in the graph.
	 */
	public int getV() {
		return V;
	}

	/**
	 * Return the number of edges in the graph.
	 */
	public int getE() {
		return E;
	}
	/**
	 * Return the number maximum edges per vertices
	 */
	public int getN() {
		return N;
	}
	
	/**
	 * Return the graph
	 */
	public Set<String>[] getG() {
		return adj;
	}

	/**
	 * Return Urls
	 */
	public ArrayList<String> getUrls() {
		return urls;
	}

	/**
	 * Add the undirected edge v-w to graph.
	 * 
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w
	 *         < V
	 */
	public void addEdge(int v, String w) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		E++;
		adj[v].add(w);
		// adj[w].add(v);
	}

	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		String s = "";
		s += V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (String w : adj[v]) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}

}
