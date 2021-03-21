import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	
	//-----------------------------------------------------
		// Title: Test
		// Author: atakan sevinçli
		// Section: 1
		// Assignment: 1
		// Description: This class is test class
		//-----------------------------------------------------
	
	public static SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>(1999);

	public static int findminindex(String[] arr) {
		// --------------------------------------------------------
				// Summary: return min value of key's index of given String array
				// Precondition: String arr.
				// Postcondition: return index.
				// --------------------------------------------------------
		String min = arr[0];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {

			if (st.get(arr[i]) < st.get(min)) {
				min = arr[i];
				index = i;
			}

		}

		return index;
	}

	public static boolean contain(String[] arr, String string) {
		// --------------------------------------------------------
				// Summary: return true if given string is in given arrays
				// Precondition: String arr , String string.
				// Postcondition: return boolean.
				// --------------------------------------------------------
		for (int i = 0; i < arr.length; i++) {
			if (string.equalsIgnoreCase(arr[i])) {
				return true;
			}

		}
		return false;
	}

	public static void sort(String arr[]) {
		// --------------------------------------------------------
				// Summary: call put bests method and sort them by insertion sort and print them descending order
				// Precondition: String arr.
				// Postcondition: print descending order.
				// --------------------------------------------------------
		for (int i = 0; i < arr.length; i++) {
			for (String string : st.keys()) {
				arr[i] = string;
			}
		}

		for (String string : st.keys()) {
			if (st.get(string) > st.get(arr[findminindex(arr)]) && !contain(arr, string)) {
				arr[findminindex(arr)] = string;
			}
		}

		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			String key = arr[i];
			int j = i - 1;

			/*
			 * insertion sort
			 */
			while (j >= 0 && st.get(arr[j]) > st.get(key)) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.println(arr[i] + " " + st.get(arr[i]));
		}

		for (int i = 0; i < arr.length; i++) {
			st.delete("arr" + i);
		}
	}

	public static void main(String[] args) throws Exception {

		String[] arr = new String[10];
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/twitter.txt"))); //read file
		String str;
		int count = 0;

		String regex = "#[a-zA-Z-0-9-â-€-\\p{IsLatin}\\p{Punct}&&[^#]]+";
		Pattern matcher = Pattern.compile(regex);
		String regex2 = "[\\p{Punct}]";

		while ((str = br.readLine()) != null) {

			Matcher m = matcher.matcher(str.toLowerCase());

			while (m.find()) {

				String tag = m.group(0);
				tag = tag.toLowerCase();
				Pattern pattern2 = Pattern.compile(regex2);
				Matcher matcher2 = pattern2.matcher(tag.substring(1));

				boolean matchFound = matcher2.find();
//check hashtags
				if (!matchFound) {
					if (st.contains(tag)) {
						//put them and overwrite onto hashtable
						st.put(tag, st.get(tag) + 1);
					}

					else
						//put them onto hashtable first time
						st.put(tag, 1);
				}

			}
		}

		sort(arr); // sort best 10.

		System.out.println("Average number of probes : "+st.calculateProbe()); // print average number of probes

	}

}
