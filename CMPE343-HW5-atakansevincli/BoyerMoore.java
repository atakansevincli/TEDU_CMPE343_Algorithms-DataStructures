public class BoyerMoore {

	// -----------------------------------------------------
	// Title: BoyerMoore
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define BoyerMoore class
	// -----------------------------------------------------

	private final int R; // the radix
	private int[] right; // the bad-character skip array

	private char[] pattern; // store the pattern as a character array
	private String pat; // or as a string

	public BoyerMoore(String pat) {

		// --------------------------------------------------------
		// Summary: Initializes an BoyerMoore.
		// Precondition: String pat
		// Postcondition: Initializes of an BoyerMoore.
		// --------------------------------------------------------

		this.R = 256;
		this.pat = pat;

		right = new int[R];
		for (int c = 0; c < R; c++)
			right[c] = -1;
		for (int j = 0; j < pat.length(); j++)
			right[pat.charAt(j)] = j;
	}

	public int search(String txt) {

		// --------------------------------------------------------
		// Summary: Returns the index of the first occurrrence of the pattern string in
		// the text string
		// Precondition: String txt
		// Postcondition: Returns the index of the first occurrrence of the pattern
		// string in the text string
		// --------------------------------------------------------

		int m = pat.length();
		int n = txt.length();
		int skip;
		for (int i = 0; i <= n - m; i += skip) {
			skip = 0;
			for (int j = m - 1; j >= 0; j--) {
				if (pat.charAt(j) != txt.charAt(i + j)) {
					skip = Math.max(1, j - right[txt.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0)
				return i; // found
		}
		return n; // not found
	}

}