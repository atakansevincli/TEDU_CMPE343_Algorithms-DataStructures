import java.util.*;

public class BreadthFirstPaths {

	// -----------------------------------------------------
	// Title: BreadthFirstPaths
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 2
	// Description: This class define BreadthFirstPaths
	// -----------------------------------------------------

	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; // marked[v] = is there an s-v path
	private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
	private int[] distTo; // distTo[v] = number of edges shortest s-v path
	public Graph G;

	public BreadthFirstPaths(Graph G, int s) {
		// --------------------------------------------------------
		// Summary: Constructor BreadthFirstPaths.
		// Precondition: SGraph G, int s
		// Postcondition: Constructor BreadthFirstPaths.
		// --------------------------------------------------------

		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		this.G = G;
		bfs(G, s);

	}

	private void bfs(Graph G, int s) {
		// --------------------------------------------------------
				// Summary: breadth-first search from a single source.
				// Precondition: Graph G, int s
				// Postcondition: breadth-first search from a single source.
				// --------------------------------------------------------
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		distTo[s] = 0;
		marked[s] = true;
		q.add(s);

		while (!q.isEmpty()) {
			int v = q.remove();
			for (int w : G.adj[v]) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.add(w);
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		// --------------------------------------------------------
		// Summary: Is there a path between the source vertex
		// Precondition: int v.
		// Postcondition: Returns true if is there a path...
		// --------------------------------------------------------

		return marked[v];
	}

	public int distTo(int v) {
		// --------------------------------------------------------
		// Summary: Returns the number of edges in a shortest path between the source
		// vertex
		// Precondition: int v.
		// Postcondition: Returns true if is there a path...
		// --------------------------------------------------------

		return distTo[v];
	}

	public Iterable<Integer> pathTo(int v) {
		// --------------------------------------------------------
		// Summary: Returns a shortest path between the source vertex.
		// Precondition: int v.
		// Postcondition:Returns a shortest path between the source vertex...
		// --------------------------------------------------------

		if (!hasPathTo(v))
			return null;
		LinkedList<Integer> path = new LinkedList<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

	public void findPath() {
		// --------------------------------------------------------
		// Summary: Print a shortest path between the source vertex...
		// Precondition: Graph G.
		// Postcondition:Print a shortest path between the source vertex...
		// --------------------------------------------------------
		for (int x : this.pathTo(G.lastPosition())) {
			if (x != G.lastPosition())
				System.out.print(G.adj[x].getName() + "->");
			else
				System.out.print(G.adj[x].getName());
		}
	}

}