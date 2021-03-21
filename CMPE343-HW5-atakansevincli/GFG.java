import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class GFG {

	// -----------------------------------------------------
	// Title: GFG
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define GFG class
	// -----------------------------------------------------

	public void part2() throws IOException {
		// --------------------------------------------------------
		// Summary: output contain the 8 following steps of the dancing
		// Precondition: There is no precondition
		// Postcondition: output contain the 8 following steps of the dancing, followed
		// by three dots “…” as follows:
		// --------------------------------------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input2.txt")));
		int sizeLoop = Integer.parseInt(br.readLine());
		for (int i = 0; i < sizeLoop; i++) {
			String str, longestRW;
			str = br.readLine();
			LongestRepeatWord lrw = new LongestRepeatWord(str);
			longestRW = lrw.longestRepeatedSubstring();
			String repeatText = "";
			while (true) {
				if (repeatText.length() < 8) {
					if (repeatText.length() == 0) {
						repeatText = longestRW.substring((str.length()) % (longestRW.length()));
					} else {
						repeatText += longestRW;
					}
				} else {
					repeatText = repeatText.substring(0, 8);
					repeatText += "...";
					break;
				}
			}
			System.out.println(repeatText);
		}
	}

}