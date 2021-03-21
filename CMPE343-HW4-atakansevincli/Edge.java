public class Edge implements Comparable<Edge> {
	// -----------------------------------------------------
			// Title: Edge
			// Author: Atakan Sevincli
			// Section: 1
			// Assignment: 4
			// Description: This class define Edge class
			// -----------------------------------------------------

	private final int v;
	private final int w;
	private final int weight;

	public Edge(int v, int w, int weight) {
		// --------------------------------------------------------
		// Summary: Initializes an edge between vertices {@code v} and {@code w} of
		// the given.
		// Precondition: int v, int w, int weight
		// Postcondition: throws IllegalArgumentException.
		// --------------------------------------------------------
		if (v < 0)
			throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if (w < 0)
			throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if (Double.isNaN(weight))
			throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int weight() {
		// --------------------------------------------------------
		// Summary: Returns the weight of this edge
		// Precondition: There is no precondition
		// Postcondition: Returns the weight of this edge.
		// --------------------------------------------------------
		return weight;
	}

	public int either() {
		// --------------------------------------------------------
		// Summary:Returns either endpoint of this edge.
		// Precondition: There is no precondition
		// Postcondition: Returns either endpoint of this edge.
		// --------------------------------------------------------
		return v;
	}

	public int other(int vertex) {
		// --------------------------------------------------------
		// Summary: Returns the endpoint of this edge that is different from the given
		// vertex.
		// Precondition: int vertex
		// Postcondition: Returns the endpoint of this edge that is different from the
		// given vertex.
		// --------------------------------------------------------
		if (vertex == v)
			return w;
		else if (vertex == w)
			return v;
		else
			throw new IllegalArgumentException("Illegal endpoint");
	}

	@Override
	public int compareTo(Edge that) {
		// --------------------------------------------------------
		// Summary: Compares two edges by weight
		// Precondition: Edge that
		// Postcondition: Compares two edges by weight
		// --------------------------------------------------------
		return Integer.compare(this.weight, that.weight);
	}

	public String toString() {
		// --------------------------------------------------------
		// Summary: Returns a string representation of this edge.
		// Precondition: Edge that
		// Postcondition: Returns a string representation of this edge.
		// --------------------------------------------------------
		return String.format("%d-%d", v + 1, w + 1);
	}

}