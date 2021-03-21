import java.util.Arrays;

public class SuffixArray {
	
	// -----------------------------------------------------
	// Title: SuffixArray
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define SuffixArray class
	// -----------------------------------------------------
	
	
	private Suffix[] suffixes;

	public SuffixArray(String text) {

		// --------------------------------------------------------
		// Summary: Initializes a suffix array for the given {@code text} string
		// Precondition: String pat, String txt
		// Postcondition: Initializes a suffix array for the given {@code text} string
		// --------------------------------------------------------

		int n = text.length();
		this.suffixes = new Suffix[n];
		for (int i = 0; i < n; i++)
			suffixes[i] = new Suffix(text, i);
		Arrays.sort(suffixes);
	}

	private static class Suffix implements Comparable<Suffix> {
		private final String text;
		private final int index;

		private Suffix(String text, int index) {
			this.text = text;
			this.index = index;
		}

		private int length() {
			return text.length() - index;
		}

		private char charAt(int i) {
			return text.charAt(index + i);
		}

		public int compareTo(Suffix that) {
			if (this == that)
				return 0; // optimization
			int n = Math.min(this.length(), that.length());
			for (int i = 0; i < n; i++) {
				if (this.charAt(i) < that.charAt(i))
					return -1;
				if (this.charAt(i) > that.charAt(i))
					return +1;
			}
			return this.length() - that.length();
		}

		public String toString() {
			return text.substring(index);
		}
	}

	public int length() {

		// --------------------------------------------------------
		// Summary: Returns the length of the input string.
		// Precondition: There is no precondition.
		// Postcondition: Returns the length of the input string.
		// --------------------------------------------------------

		return suffixes.length;
	}

	public int index(int i) {

		// --------------------------------------------------------
		// Summary: Returns the index into the original string of the i th smallest
		// suffix.
		// Precondition: int i
		// Postcondition: Returns the index into the original string of the i th
		// smallest suffix.
		// --------------------------------------------------------

		if (i < 0 || i >= suffixes.length)
			throw new IllegalArgumentException();
		return suffixes[i].index;
	}

	public int lcp(int i) {

		// --------------------------------------------------------
		// Summary: Returns the length of the longest common prefix of the i th smallest
		// suffix and the i-1st smallest suffix.
		// Precondition: int i
		// Postcondition: Returns the length of the longest common prefix of the i th
		// smallest suffix and the i-1st smallest suffix.
		// --------------------------------------------------------

		if (i < 1 || i >= suffixes.length)
			throw new IllegalArgumentException();
		return lcpSuffix(suffixes[i], suffixes[i - 1]);
	}

	private static int lcpSuffix(Suffix s, Suffix t) {
		// --------------------------------------------------------
		// Summary: Returns longest common prefix of s and t
		// Precondition: Suffix s, Suffix t
		// Postcondition: Returns longest common prefix of s and t
		// --------------------------------------------------------
		int n = Math.min(s.length(), t.length());
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) != t.charAt(i))
				return i;
		}
		return n;
	}

	public String select(int i) {
		// --------------------------------------------------------
		// Summary: Returns the ith smallest suffix as a string
		// Precondition: int i
		// Postcondition: Returns the ith smallest suffix as a string
		// --------------------------------------------------------

		if (i < 0 || i >= suffixes.length)
			throw new IllegalArgumentException();
		return suffixes[i].toString();
	}

	public int rank(String query) {

		// --------------------------------------------------------
		// Summary: Returns the number of suffixes strictly less than the {@code query}
		// string.
		// Precondition: String query
		// Postcondition: Returns the number of suffixes strictly less than the {@code
		// query} string.
		// --------------------------------------------------------

		int lo = 0, hi = suffixes.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = compare(query, suffixes[mid]);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	private static int compare(String query, Suffix suffix) {

		// --------------------------------------------------------
		// Summary: compare query string to suffix
		// Precondition: String query, Suffix suffix
		// Postcondition: compare query string to suffix
		// --------------------------------------------------------

		int n = Math.min(query.length(), suffix.length());
		for (int i = 0; i < n; i++) {
			if (query.charAt(i) < suffix.charAt(i))
				return -1;
			if (query.charAt(i) > suffix.charAt(i))
				return +1;
		}
		return query.length() - suffix.length();
	}
}