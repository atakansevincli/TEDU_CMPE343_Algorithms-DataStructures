public class EdgeWeightedGraph {
	// -----------------------------------------------------
		// Title: EdgeWeightedGraph
		// Author: Atakan Sevincli
		// Section: 1
		// Assignment: 4
		// Description: This class define EdgeWeightedGraph class
		// -----------------------------------------------------
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Edge>[] adj;

	public EdgeWeightedGraph(int V, int E) {
		// --------------------------------------------------------
		// Summary: Initialize EdgeWeightedGraph.
		// Precondition: int V,int E
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = E;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>(v + 1);
		}
	}

	public int V() {
		// --------------------------------------------------------
		// Summary: Returns the number of vertices in this edge-weighted graph.
		// Precondition: There is no precondition.
		// Postcondition: Returns the number of vertices in this edge-weighted graph.
		// --------------------------------------------------------
		return V;
	}

	public int E() {
		// --------------------------------------------------------
		// Summary: Returns the number of edges in this edge-weighted graph.
		// Precondition: There is no precondition.
		// Postcondition:Returns the number of edges in this edge-weighted graph.
		// --------------------------------------------------------
		return E;
	}

	public void addEdge(Edge e) {
		// --------------------------------------------------------
		// Summary: Adds the undirected edge {@code e} to this edge-weighted graph.
		// Precondition: Edge e
		// Postcondition:There is no postcondition.
		// --------------------------------------------------------
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}

	public Iterable<Edge> adj(int v) {
		// --------------------------------------------------------
		// Summary: Returns the edges incident on vertex
		// Precondition: int v
		// Postcondition: throws IllegalArgumentException unless {@code 0 <= v < V}
		// --------------------------------------------------------
		return adj[v];
	}

	public int degree(int v) {
		// --------------------------------------------------------
		// Summary: Returns the degree of vertex
		// Precondition: int v
		// Postcondition: throws IllegalArgumentException unless {@code 0 <= v < V}
		// --------------------------------------------------------
		return adj[v].size();
	}

	public Iterable<Edge> edges() {
		// --------------------------------------------------------
		// Summary: Returns all edges in this edge-weighted graph.
		// Precondition: There is no precondition.
		// Postcondition: Returns all edges in this edge-weighted graph.
		// --------------------------------------------------------
		Bag<Edge> list = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				// add only one copy of each self loop (self loops will be consecutive)
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}

	public String toString() {
		// --------------------------------------------------------
		// Summary: Returns all edges in this string.
		// Precondition: There is no precondition.
		// Postcondition: Return string
		// --------------------------------------------------------
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + 1 + ": ");
			for (Edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

}
