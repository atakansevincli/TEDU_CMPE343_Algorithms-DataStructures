public class KMPplus {

	// -----------------------------------------------------
	// Title: KMPplus
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define KMPplus class
	// -----------------------------------------------------

	private String pattern;
	private int[] next;

	// create Knuth-Morris-Pratt NFA from pattern
	public KMPplus(String pattern) {

		// --------------------------------------------------------
		// Summary: Initializes an KMPplus.
		// Precondition: String pattern
		// Postcondition: Initializes of an KMPplus.
		// --------------------------------------------------------

		this.pattern = pattern;
		int m = pattern.length();
		next = new int[m];
		int j = -1;
		for (int i = 0; i < m; i++) {
			if (i == 0)
				next[i] = -1;
			else if (pattern.charAt(i) != pattern.charAt(j))
				next[i] = j;
			else
				next[i] = next[j];
			while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = next[j];
			}
			j++;
		}

	}

	public int search(String text) {

		// --------------------------------------------------------
		// Summary:return offset of first occurrence of text in pattern (or n if no
		// match) simulate the NFA to find match
		// Precondition: String txt
		// Postcondition: return offset of first occurrence of text in pattern (or n if
		// no match) simulate the NFA to find match
		// --------------------------------------------------------

		int m = pattern.length();
		int n = text.length();
		int i, j;
		for (i = 0, j = 0; i < n && j < m; i++) {
			while (j >= 0 && text.charAt(i) != pattern.charAt(j))
				j = next[j];
			j++;
		}
		if (j == m)
			return i - m;
		return n;
	}

}
