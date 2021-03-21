import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test {

	// -----------------------------------------------------
	// Title: Test
	// Author: Atakan Sevincli
	// Section: 1
	// Assignment: 3
	// Description: This class define Test Class
	// -----------------------------------------------------
	public static void main(String[] args) throws NumberFormatException, IOException {

		/////////////////////////////////// PART1
		/////////////////////////////////// ///////////////////////////////////////////////////////
		
		
		// Read txt input for PART1
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input1.txt")));
		BufferedReader add = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input1.txt")));

		
		Graph G1 = new Graph(Integer.parseInt(br1.readLine()), Integer.parseInt(br1.readLine()), br1);

		String line;
		String arr[];
		add.readLine(); 
		add.readLine(); 

		while ((line = add.readLine()) != null) {
			arr = line.split(" ");
			
			G1.addEdge(G1.nameOfCities.indexOf(arr[0]) + 1, G1.nameOfCities.indexOf(arr[1]) + 1);

			if (G1.isCyclic())
				G1.ignoreEdge(G1.nameOfCities.indexOf(arr[0]) + 1, G1.nameOfCities.indexOf(arr[1]) + 1);

		}

		System.out.println("0 0");

		/////////////////////////////////// PART2
		/////////////////////////////////// ///////////////////////////////////////////////////////

		// Read txt input for PART2
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input2.txt"))); // read
																														// file

		// Create Graph object
		Graph G2 = new Graph(br2);

		String[] str;
		String[] end = { "0", "0" };

		// it is to stop reading when adding edges 0,0 in input.
		while (!Arrays.deepEquals(str = br2.readLine().split(" "), end)) {
			try {
				G2.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		System.out.println("\nTopological Sort");
		G2.topologicalSort();

	}
}
