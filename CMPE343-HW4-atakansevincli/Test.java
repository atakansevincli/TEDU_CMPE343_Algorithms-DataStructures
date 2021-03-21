import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Test {
	// -----------------------------------------------------
		// Title:Test
		// Author: Atakan Sevincli
		// Section: 1
		// Assignment: 4
		// Description: This class define Test class
		// -----------------------------------------------------

	static ArrayList<Integer[]> visit2 = new ArrayList<Integer[]>();

	static void permute(java.util.List<Integer> arr, int k) {
		for (int i = k; i < arr.size(); i++) {
			java.util.Collections.swap(arr, i, k);
			permute(arr, k + 1);
			java.util.Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {
			arr.add(0, 0);
			arr.add(arr.size(), 0);

			Integer integerArray[] = java.util.Arrays.asList(arr.toArray()).toArray(new Integer[0]);

			arr.remove(0);

			arr.remove(arr.size() - 1);

			visit2.add(integerArray);

		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/hw4-part1-input-example.txt")));
		String[] VandE = br.readLine().replaceAll("\\s+", " ").split(" ");

		EdgeWeightedGraph graphQ1 = new EdgeWeightedGraph(Integer.parseInt(VandE[0]), Integer.parseInt(VandE[1]));

		for (int i = 0; i < graphQ1.E(); i++) {
			String[] ABC = br.readLine().replaceAll("\\s+", " ").split(" ");
			int Ai = Integer.parseInt(ABC[0]) - 1;
			int Bi = Integer.parseInt(ABC[1]) - 1;
			int Ci = Integer.parseInt(ABC[2]);
			// System.out.println(Ai);
			Edge e = new Edge(Ai, Bi, Ci);
			graphQ1.addEdge(e);
		}

		// System.out.println(graph);

		KruskalMST mst = new KruskalMST(graphQ1);
		System.out.printf("Part1:\n%d\n", (int) mst.weight());

		System.out.println(mst.print()+"\n");

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("src/hw4-part2-input-example.txt")));

		int V = Integer.parseInt(br2.readLine());
		int E = Integer.parseInt(br2.readLine());

		EdgeWeightedGraph graphQ2 = new EdgeWeightedGraph(V, E);

		for (int i = 0; i < E; i++) {
			String[] ABC = br2.readLine().replaceAll("\\s+", " ").split(" ");
			int Ai = Integer.parseInt(ABC[0]);
			int Bi = Integer.parseInt(ABC[1]);
			int Ci = Integer.parseInt(ABC[2]);
			// System.out.println(Ai);
			Edge e = new Edge(Ai, Bi, Ci);
			graphQ2.addEdge(e);
		}

		DijkstraUndirectedSP sp = new DijkstraUndirectedSP(graphQ2, 0);
		int numberofStores = Integer.parseInt(br2.readLine());

		ArrayList<Integer> visit = new ArrayList<Integer>();

		for (int i = 0; i < numberofStores; i++) {
			visit.add(Integer.parseInt(br2.readLine()));
		}

		
		permute(visit, 0);
		DijkstraUndirectedSP sp2;
		int check = 0;
		int min = 0;

		for (int i = 0; i < visit2.size(); i++) {
			for (int j = 0; j < visit2.get(i).length - 1; j++) {

				sp2 = new DijkstraUndirectedSP(graphQ2, visit2.get(i)[j]);
				if (sp2.hasPathTo(visit2.get(i)[j + 1])) {

					check += sp2.distTo(visit2.get(i)[j + 1]);

				}
			}

			if (i != 0) {
				if (min > check) {
					min = check;
				}

			} else
				min = check;

			check = 0;
		}
		System.out.println("Part2: \n" + min);

	}

}
