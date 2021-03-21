
public class PrintMaze {
	// -----------------------------------------------------
	// Title: PrintMaze
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 2
	// Description: This class print Graph
	// -----------------------------------------------------

	public PrintMaze(Graph G) {
		// --------------------------------------------------------
		// Summary: Constructor an empty bag.
		// Precondition: Graph G.
		// Postcondition: Returns a string representation of this graph
		// --------------------------------------------------------

		for (int v = 0; v < G.V(); v++) {

			for (int w : G.adj[v]) {
				if (w >= v) {
					System.out.println(G.adj[v].getName() + " --> " + G.adj[w].getName());

				}

			}
		}
		System.out.println();

	}

}
