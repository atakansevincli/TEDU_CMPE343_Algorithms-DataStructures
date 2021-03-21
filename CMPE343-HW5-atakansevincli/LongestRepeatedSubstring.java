public class LongestRepeatedSubstring {

	// -----------------------------------------------------
	// Title: LongestRepeatedSubstring
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define LongestRepeatedSubstring class
	// -----------------------------------------------------

	
	public LongestRepeatedSubstring() {
		// --------------------------------------------------------------
		// Summary: Do not instantiate.
		// Precondition:There is no precondition
		// Postcondition: Do not instantiate.
		// --------------------------------------------------------------
	}

	public static String lrs(String text) {

		// --------------------------------------------------------------
		// Summary: Returns the longest repeated substring of the specified string.
		// Precondition: String text
		// Postcondition: Returns the longest repeated substring of the specified
		// string.
		// --------------------------------------------------------------

		int n = text.length();
		SuffixArray sa = new SuffixArray(text);
		String lrs = "";
		for (int i = 1; i < n; i++) {
			int length = sa.lcp(i);
			if (length > lrs.length()) {
				// lrs = sa.select(i).substring(0, length);
				lrs = text.substring(sa.index(i), sa.index(i) + length);
			}
		}
		return lrs;
	}

}
