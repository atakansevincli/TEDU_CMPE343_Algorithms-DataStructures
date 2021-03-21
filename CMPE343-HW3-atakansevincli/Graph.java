import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Graph {

	// -----------------------------------------------------
	// Title: Graph
	// Author: Atakan Sevincli
	// Section: 1
	// Assignment: 3
	// Description: This class define Graph
	// -----------------------------------------------------

	
	private final int V;
	private int E;
	public Bag<Integer>[] adj;

	public Graph(BufferedReader br) throws IOException {
		// --------------------------------------------------------
		// Summary: Constructor an empty bag.
		// Precondition: int V, BufferedReader br
		// Postcondition: Initializes an empty graph with {@code V} vertices and 0
		// edges.
		// param V the number of vertices.
		// --------------------------------------------------------
		String[] VandE = br.readLine().split(" ");
		this.V = Integer.parseInt(VandE[0]);
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.E = Integer.parseInt(VandE[1]);
		;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {

			adj[v] = new Bag<Integer>(v + 1);
		}

	}

	ArrayList<String> nameOfCities = new ArrayList<String>();

	public Graph(int V, int E, BufferedReader br) throws IOException {
		// --------------------------------------------------------
		// Summary: Constructor an empty bag.
		// Precondition: int V, BufferedReader br
		// Postcondition: Initializes an empty graph with {@code V} vertices and 0
		// edges.
		// param V the number of vertices.
		// --------------------------------------------------------

		String line;
		String arr[];

		while ((line = br.readLine()) != null) {
			arr = line.split(" ");

			if (!nameOfCities.contains(arr[0])) {
				nameOfCities.add(arr[0]);
			}
			if (!nameOfCities.contains(arr[1])) {
				nameOfCities.add(arr[1]);
			}

		}

		this.V = V;
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.E = E;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>(nameOfCities.get(v), v + 1);
		}

	}

	private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
		// --------------------------------------------------------
		// Summary: This function is a variation of DFSUtil() in
		// Precondition: int i, boolean[] visited, boolean[] recStack
		// Postcondition: return boolean
		// --------------------------------------------------------

		// Mark the current node as visited and
		// part of recursion stack
		if (recStack[i])
			return true;

		if (visited[i])
			return false;

		visited[i] = true;

		recStack[i] = true;

		for (Integer c : adj[i])

			if (isCyclicUtil(c, visited, recStack)) {

				return true;
			}

		recStack[i] = false;

		return false;
	}

	public boolean isCyclic() {

		// --------------------------------------------------------
		// Summary: // Returns true if the graph contains a
		// cycle, else false.
		// This function is a variation of DFS() in
		// Precondition: int i, boolean[] visited, boolean[] recStack
		// Postcondition: return boolean
		// --------------------------------------------------------

		// Mark all the vertices as not visited and
		// not part of recursion stack
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		// Call the recursive helper function to
		// detect cycle in different DFS trees
		for (int i = 0; i < V; i++)
			if (isCyclicUtil(i, visited, recStack))
				return true;

		return false;
	}

	public void topologicalSort() {

		// --------------------------------------------------------
		// Summary: // Create a array to store
		// indegrees of all
		// vertices. Initialize all
		// indegrees as 0.

		// Precondition: there is no precondition
		// Postcondition: print topologicalsort
		// --------------------------------------------------------

		int indegree[] = new int[V];

		// Traverse adjacency lists
		// to fill indegrees of
		// vertices. This step takes
		// O(V+E) time

		System.out.println("****************");
		for (int i = 0; i < V; i++) {
			// ArrayList<Integer> temp = (ArrayList<Integer>)adj[i];

			for (int node : adj[i]) {
				indegree[node]++;
			}
		}

		// Create a queue and enqueue
		// all vertices with indegree 0
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}

		// Initialize count of visited vertices
		int cnt = 0;

		// Create a vector to store result
		// (A topological ordering of the vertices)
		Vector<Integer> topOrder = new Vector<Integer>();
		while (!q.isEmpty()) {
			// Extract front of queue
			// (or perform dequeue)
			// and add it to topological order
			int u = q.poll();
			topOrder.add(u);

			// Iterate through all its
			// neighbouring nodes
			// of dequeued node u and
			// decrease their in-degree
			// by 1
			for (int node : adj[u]) {
				// If in-degree becomes zero,
				// add it to queue
				if (--indegree[node] == 0)
					q.add(node);
			}
			cnt++;
		}

		// Check if there was a cycle
		if (cnt != V) {
			System.out.println("There exists a cycle in the graph");
			return;
		}

		// Print topological order
		for (int i : topOrder) {
			System.out.print(adj[i].ID + " ");
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

	public void ignoreEdge(int v, int w) {
		// --------------------------------------------------------
		// Summary:get Adds the undirected edge v-w to this graph..
		// Precondition: int v, int w.
		// Postcondition: add edge v-w to this graph.
		// --------------------------------------------------------

		validateVertex(v - 1);
		validateVertex(w - 1);
		adj[v - 1].remove();
		System.out.println(adj[v - 1].getName() + " --> " + adj[w - 1].getName());

	}

	public void addEdge(int v, int w) {
		// --------------------------------------------------------
		// Summary:get Adds the undirected edge v-w to this graph..
		// Precondition: int v, int w.
		// Postcondition: add edge v-w to this graph.
		// --------------------------------------------------------

		validateVertex(v - 1);
		validateVertex(w - 1);
		adj[v - 1].add(w - 1);

	}

}