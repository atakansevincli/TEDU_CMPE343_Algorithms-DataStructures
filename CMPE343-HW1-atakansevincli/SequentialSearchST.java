import java.util.ArrayList;


public class SequentialSearchST<Key, Value> {

	// -----------------------------------------------------
	// Title: SeparateChainingHashST<Key, Value>
	// Author: atakan sevinçli
	// Section: 1
	// Assignment: 1
	// Description: This class define SequentialSearchST
	// -----------------------------------------------------

	private int n; // number of key-value pairs
	private Node first; // the linked list of key-value pairs

	// a helper linked list data type
	private class Node {
		private Key key;
		private Value val;
		private Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public int size() {
		// --------------------------------------------------------
		// Summary: Returns the number of key-value pairs in this symbol table.
		// Precondition: There is no precondition.
		// Postcondition: return n;
		// --------------------------------------------------------
		return n;
	}

	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: Returns true if this symbol table is empty..
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return size() == 0;
	}

	public boolean contains(Key key) {
		// --------------------------------------------------------
		// Summary: Returns true if this symbol table contains the specified key.
		// Precondition: Key key.
		// Postcondition: return true if it contains.
		// --------------------------------------------------------
		return get(key) != null;
	}

	public Value get(Key key) {
		// --------------------------------------------------------
		// Summary: Returns the value associated with the specified key in this symbol
		// table.
		// Precondition: Key key.
		// Postcondition: the value associated with key in the symbol table
		// --------------------------------------------------------
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}

	public void put(Key key, Value val) {
		// --------------------------------------------------------
		// Summary: Inserts the specified key-value pair into the symbol table,
		// overwriting the
		// old value with the new value if the symbol table already contains the
		// specified key.
		// Precondition: Key key , Value val.
		// Postcondition: calculate the hash in put it onto hashtable
		// --------------------------------------------------------
		if (val == null) {
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
		n++;
	}

	public void delete(Key key) {
		// --------------------------------------------------------
		// Summary: Removes the key and associated value from the symbol table (if the
		// key is in
		// the symbol table).
		// Precondition: Key key.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		first = delete(first, key);
	}

	public Iterable<Key> keys() {
		// --------------------------------------------------------
		// Summary: return keys in symbol table as an Iterable
		// Precondition: There is no precondition.
		// Postcondition: return Iterable.
		// --------------------------------------------------------
		ArrayList<Key> arraylist = new ArrayList<Key>();
		for (Node x = first; x != null; x = x.next)
			arraylist.add(x.key);
		return arraylist;
	}

	private Node delete(Node x, Key key) {
		// --------------------------------------------------------
		// Summary: delete key in linked list beginning at Node x
		// Precondition: Node x ,Key key.
		// Postcondition: return deleted.
		// --------------------------------------------------------
		if (x == null)
			return null;
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}
}