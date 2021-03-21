import java.util.ArrayList;

public class SeparateChainingHashST<Key, Value> {

	// -----------------------------------------------------
	// Title: SeparateChainingHashST<Key, Value>
	// Author: atakan sevinçli
	// Section: 1
	// Assignment: 1
	// Description: This class define SeparateChainingHashST
	// -----------------------------------------------------
	
	
	private int n; // number of key-value pairs
	public int m; // hash table size
	private SequentialSearchST<Key, Value>[] st; // array of linked-list symbol tables
	public int numberofProbes = 0;

	public SeparateChainingHashST(int m) {

		// --------------------------------------------------------
		// Summary: determine the m (hash table size)
		// Precondition: int m
		// Postcondition: determine the hash table size and create m st objects.
		// --------------------------------------------------------
		this.m = m;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}

	private void resize(int chains) {

		// --------------------------------------------------------
		// Summary: resize the hash table to have the given number of chains
		// Precondition: int chains
		// Postcondition: rehashing all of the keys
		// --------------------------------------------------------

		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
	}

	public void delete(Key key) {

		// --------------------------------------------------------
		// Summary: Removes the specified key and its associated value from this symbol
		// table
		// Precondition: Key key
		// Postcondition: if the key is in this symbol table
		// --------------------------------------------------------
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		if (st[i].contains(key))
			n--;
		st[i].delete(key);

	}

	private int hash(Key key) {
		// --------------------------------------------------------
		// Summary: This method calculate hashcode and define index which index put the
		// hashtable.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		int hashcode = 0; // hash code of key.
		int length_key = ((String) key).length();
		for (int i = 1; i < length_key; i++) {
			hashcode += ((String) key).charAt(i);
		}

		return hashcode % m;
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

		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	public Value get(Key key) {
		// --------------------------------------------------------
		// Summary: Returns the value associated with the specified key in this symbol
		// table.
		// Precondition: Key key.
		// Postcondition: the value associated with key in the symbol table
		// --------------------------------------------------------
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
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
		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");

		// double table size if average length of list >= 10
		if (n >= 10 * m)
			resize(2 * m);

		int i = hash(key);
		if (!st[i].contains(key))
			n++;
		st[i].put(key, val);
	}

	public double calculateProbe() {
		// --------------------------------------------------------
		// Summary: Calculate the average number of probes.
		// Precondition: There is no precondition.
		// Postcondition: return average number of probes.
		// --------------------------------------------------------
		for (int i = 0; i < st.length; i++) {
			numberofProbes += (st[i].size() * (st[i].size() + 1)) / 2;
		}
		return (float) numberofProbes / (float) m;

	}

	public Iterable<Key> keys() {
		// --------------------------------------------------------
		// Summary: return keys in symbol table as an Iterable
		// Precondition: There is no precondition.
		// Postcondition: return Iterable.
		// --------------------------------------------------------
		ArrayList<Key> arraylist = new ArrayList<Key>();
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys())
				arraylist.add(key);
		}
		return arraylist;
	}

}