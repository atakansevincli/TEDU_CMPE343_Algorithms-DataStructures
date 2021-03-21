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
	// Assignment: 2
	// Description: This class define Test Class
	// -----------------------------------------------------
	public static void main(String[] args) throws NumberFormatException, IOException {

		// Read txt input
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/hw2.txt"))); // read file

		// Create Graph object
		Graph G = new Graph(Integer.parseInt(br.readLine()), br);
		
		String[] str;
		String[] end = { "-1", "-1" };

		// it is to stop reading when adding edges -1,-1 in input.
		while (!Arrays.deepEquals(str = br.readLine().split(" "), end)) {
			try {
				G.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

		PrintMaze maze = new PrintMaze(G); // print graph.
		

		
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, G.firstPosition());
		bfs.findPath(); //print shortest path between first position and last position

	}
}
