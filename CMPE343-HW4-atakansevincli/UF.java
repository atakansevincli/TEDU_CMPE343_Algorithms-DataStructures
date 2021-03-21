public class UF {
	// -----------------------------------------------------
		// Title:UF
		// Author: Atakan Sevincli
		// Section: 1
		// Assignment: 4
		// Description: This class define UF Object
		// -----------------------------------------------------

	private int[] parent; // parent[i] = parent of i
	private byte[] rank; // rank[i] = rank of subtree rooted at i (never more than 31)
	private int count; // number of components

	public UF(int n) {
		// --------------------------------------------------------
		// Summary: Initializes an empty union-find data structure with
		// Precondition: int n
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		if (n < 0)
			throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int p) {
		// --------------------------------------------------------
		// Summary: Returns the canonical element of the set containing element
		// Precondition: int p
		// Postcondition: Returns the canonical element of the set containing element.
		// --------------------------------------------------------
		validate(p);
		while (p != parent[p]) {
			parent[p] = parent[parent[p]]; // path compression by halving
			p = parent[p];
		}
		return p;
	}

	public int count() {
		// --------------------------------------------------------
		// Summary: Returns the number of sets.
		// Precondition: There is no post condition
		// Postcondition: Returns the number of sets.
		// --------------------------------------------------------

		return count;
	}


	@Deprecated
	public boolean connected(int p, int q) {
		// --------------------------------------------------------
				// Summary: Returns true if the two elements are in the same set.
				// Precondition: int p, int q
				// Postcondition: Returns boolean
				// --------------------------------------------------------
		return find(p) == find(q);
	}

	public void union(int p, int q) {
		
		// --------------------------------------------------------
		// Summary: Merges the set containing element {@code p} with the the set containing
		//element {@code q}.
		// Precondition: int p, int q
		// Postcondition: There is no postcondition
		// --------------------------------------------------------
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;

		// make root of smaller rank point to root of larger rank
		if (rank[rootP] < rank[rootQ])
			parent[rootP] = rootQ;
		else if (rank[rootP] > rank[rootQ])
			parent[rootQ] = rootP;
		else {
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
		count--;
	}

	
	private void validate(int p) {
		// --------------------------------------------------------
				// Summary:validate that p is a valid index
				// Precondition: int p
				// Postcondition: throw IllegalArgumentException
				// --------------------------------------------------------
		int n = parent.length;
		if (p < 0 || p >= n) {
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
		}
	}

}