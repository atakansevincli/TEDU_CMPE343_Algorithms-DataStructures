
public class LongestRepeatWord {

	// -----------------------------------------------------
	// Title: LongestRepeatWord
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define LongestRepeatWord class
	// -----------------------------------------------------

	private String text;

	public String getText() {
		// --------------------------------------------------------------
		// Summary: Return String text
		// Precondition: There is no precondition.
		// Postcondition: Return String text
		// --------------------------------------------------------------

		return text;
	}

	public void setText(String text) {

		// --------------------------------------------------------------
		// Summary: Set String text
		// Precondition: String text
		// Postcondition: Set String text
		// --------------------------------------------------------------

		this.text = text;
	}

	public LongestRepeatWord(String text) {
		// --------------------------------------------------------------
		// Summary: Set String text
		// Precondition: String text
		// Postcondition: Set String text
		// --------------------------------------------------------------
		this.text = text;
	}

	public String longestRepeatedSubstring() {

		// --------------------------------------------------------------
		// Summary: Find the longest repeated sequance in text.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------------

		int n = this.text.length();
		int LCSRe[][] = new int[n + 1][n + 1];

		String res = ""; // To store result
		int res_length = 0; // To store length of result

		// building table in bottom-up manner
		int i, index = 0;
		for (i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				// (j-i) > LCSRe[i-1][j-1] to remove
				// overlapping
				if (this.text.charAt(i - 1) == this.text.charAt(j - 1) && LCSRe[i - 1][j - 1] < (j - i)) {
					LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1;

					// updating maximum length of the
					// substring and updating the finishing
					// index of the suffix
					if (LCSRe[i][j] > res_length) {
						res_length = LCSRe[i][j];
						index = Math.max(i, index);
					}
				} else {
					LCSRe[i][j] = 0;
				}
			}
		}

		// If we have non-empty result, then insert all
		// characters from first character to last
		// character of String
		if (res_length > 0) {
			for (i = index - res_length + 1; i <= index; i++) {
				res += this.text.charAt(i - 1);
			}
		}

		return res;
	}
}
