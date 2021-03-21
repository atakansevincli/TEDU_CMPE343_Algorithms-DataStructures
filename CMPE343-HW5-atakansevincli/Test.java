import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	// -----------------------------------------------------
	// Title: Test
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 5
	// Description: This class define Test class
	// -----------------------------------------------------

	public static void main(String[] args) throws IOException {

		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input1.txt")));
		int inputCount = 1;
		while ((line = br.readLine()) != null) {

			line = line.replaceAll("\\s+", " ");
			LongestRepeatedSubstring ls = new LongestRepeatedSubstring();

			String repeatedWord = ls.lrs(line);
			System.out.println("Input" + inputCount + ": " + line + "\n" + repeatedWord.length() + "\nPattern: "
					+ repeatedWord + "\n");

			long startTime = System.nanoTime(); // start Time
			BruteForce bf = new BruteForce();
			int offsetBF = bf.search1(repeatedWord, line);
			long endTime = System.nanoTime(); // end Time
			long totalTime = endTime - startTime; // calculate the differences between start and end Time.
			System.out.println("Brute Force  " + totalTime + " nanosecond");

			long startTime2 = System.nanoTime(); // start Time
			BoyerMoore boyermoore1 = new BoyerMoore(repeatedWord);
			int offsetBM = boyermoore1.search(line);
			long endTime2 = System.nanoTime(); // end Time
			long totalTime2 = endTime2 - startTime2; // calculate the differences between start and end Time.
			System.out.println("Boyer Moore  " + totalTime2 + " nanosecond");

			long startTime3 = System.nanoTime(); // start Time
			KMPplus kmp = new KMPplus(repeatedWord);
			int offsetKMP = kmp.search(line);
			long endTime3 = System.nanoTime(); // end Time
			long totalTime3 = endTime3 - startTime3; // calculate the differences between start and end Time.
			System.out.println("Knuth-Morris " + totalTime3 + " nanosecond");
			System.out.println("-------------------------------------------------- \n");

			inputCount++;
		}

		System.out.println("PART2");
		GFG gfg = new GFG();
		gfg.part2();

	}
}
