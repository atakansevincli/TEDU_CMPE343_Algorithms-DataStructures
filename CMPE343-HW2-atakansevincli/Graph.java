import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

	// -----------------------------------------------------
	// Title: Graph
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 2
	// Description: This class define Graph
	// -----------------------------------------------------

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	public Bag<Integer>[] adj;

	public Graph(int V, BufferedReader br) throws IOException {
		// --------------------------------------------------------
		// Summary: Constructor an empty bag.
		// Precondition: int V, BufferedReader br
		// Postcondition: Initializes an empty graph with {@code V} vertices and 0
		// edges.
		// param V the number of vertices.
		// --------------------------------------------------------
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];

		for (int v = 0; v < V; v++) {
			String str = br.readLine();
			String[] splitStrings = str.split(" ");
			adj[v] = new Bag<Integer>(splitStrings[0], Integer.parseInt(splitStrings[1]),
					Integer.parseInt(splitStrings[2]));
		}
	}

	public int V() {
		// --------------------------------------------------------
		// Summary: The number of vertices in this graph...
		// Precondition: There is no precondition.
		// Postcondition: Returns the number of vertices in this graph...
		// --------------------------------------------------------
		return V;
	}

	public int E() {
		// --------------------------------------------------------
		// Summary:The number of edges in this graph.
		// Precondition: There is no precondition.
		// Postcondition: Returns the number of edges in this graph.
		// --------------------------------------------------------
		return E;
	}

	public int firstPosition() {
		// --------------------------------------------------------
		// Summary:get firstPosition of graph.
		// Precondition: There is no precondition.
		// Postcondition: Returns firstposition of graph.
		// --------------------------------------------------------
		return 0;
	}

	public int lastPosition() {
		// --------------------------------------------------------
		// Summary:get lastPosition of graph.
		// Precondition: There is no precondition.
		// Postcondition: Returns lastPosition of graph.
		// --------------------------------------------------------
		return V() - 1;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public void addEdge(int v, int w) {
		// --------------------------------------------------------
		// Summary:get Adds the undirected edge v-w to this graph..
		// Precondition: int v, int w.
		// Postcondition: add edge v-w to this graph.
		// --------------------------------------------------------
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	

}