public class BruteForce {

	// -----------------------------------------------------
	// Title: BruteForce
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define BruteForce class
	// -----------------------------------------------------

	public static int search1(String pat, String txt) {
		// --------------------------------------------------------
		// Summary: return offset of first match or n if no match
		// Precondition: String pat, String txt
		// Postcondition: return offset of first match or n if no match
		// --------------------------------------------------------

		int m = pat.length();
		int n = txt.length();

		for (int i = 0; i <= n - m; i++) {
			int j;
			for (j = 0; j < m; j++) {
				if (txt.charAt(i + j) != pat.charAt(j))
					break;
			}
			if (j == m)
				return i; // found at offset i
		}
		return n; // not found
	}

}