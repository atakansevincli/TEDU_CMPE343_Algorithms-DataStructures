import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key> {
	// -----------------------------------------------------
	// Title: MinPQ<Key>
	// Author: Atakan Sevincli
	// Section: 1
	// Assignment: 4
	// Description: This class define MinPQ<Key> class
	// -----------------------------------------------------
	private Key[] pq; // store items at indices 1 to n
	private int n; // number of items on priority queue
	private Comparator<Key> comparator; // optional comparator

	public MinPQ(int initCapacity) {
		// --------------------------------------------------------
		// Summary: Initializes an empty priority queue with the given initial capacity.
		// Precondition: int initCapacity.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}

	public MinPQ() {
		// --------------------------------------------------------
		// Summary: Initializes an empty priority queue.
		// Precondition: There is no precondition
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		this(1);
	}

	public MinPQ(int initCapacity, Comparator<Key> comparator) {
		// --------------------------------------------------------
		// Summary: Initializes an empty priority queue with the given initial capacity,
		// Precondition: int initCapacity, Comparator<Key> comparator
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------

		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}

	public MinPQ(Comparator<Key> comparator) {
		// --------------------------------------------------------
		// Summary: Initializes an empty priority queue using the given comparator.
		// Precondition: Comparator<Key> comparator
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		this(1, comparator);
	}

	public MinPQ(Key[] keys) {
		// --------------------------------------------------------
		// Summary: Initializes a priority queue from the array of keys
		// Precondition: iKey[] keys
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		n = keys.length;
		pq = (Key[]) new Object[keys.length + 1];
		for (int i = 0; i < n; i++)
			pq[i + 1] = keys[i];
		for (int k = n / 2; k >= 1; k--)
			sink(k);
		assert isMinHeap();
	}

	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: Returns true if this priority queue is empty.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return n == 0;
	}

	public int size() {
		// --------------------------------------------------------
		// Summary: Returns the number of keys on this priority queue.
		// Precondition: There is no precondition.
		// Postcondition: Returns the number of keys on this priority queue.
		// --------------------------------------------------------
		return n;
	}

	public Key min() {
		// --------------------------------------------------------
		// Summary: Returns a smallest key on this priority queue.
		// Precondition: There is no precondition.
		// Postcondition: throws NoSuchElementException if this priority queue is empty
		// --------------------------------------------------------
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	private void resize(int capacity) {
		// --------------------------------------------------------
		// Summary: resize the underlying array to have the given capacity
		// Precondition: int capacity.
		// Postcondition: resize the underlying array to have the given capacity.
		// --------------------------------------------------------
		assert capacity > n;
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	public void insert(Key x) {
		// --------------------------------------------------------
		// Summary: Adds a new key to this priority queue.
		// Precondition: Key x
		// Postcondition: Adds a new key to this priority queue.
		// --------------------------------------------------------
		// double size of array if necessary
		if (n == pq.length - 1)
			resize(2 * pq.length);

		// add x, and percolate it up to maintain heap invariant
		pq[++n] = x;
		swim(n);
		assert isMinHeap();
	}

	public Key delMin() {
		// --------------------------------------------------------
		// Summary: Removes and returns a smallest key on this priority queue.
		// Precondition: There is no precondition.
		// Postcondition: a smallest key on this priority queue
		// --------------------------------------------------------
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		Key min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = null; // to avoid loitering and help with garbage collection
		if ((n > 0) && (n == (pq.length - 1) / 4))
			resize(pq.length / 2);
		assert isMinHeap();
		return min;
	}

	/***************************************************************************
	 * Helper functions to restore the heap invariant.
	 ***************************************************************************/

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	/***************************************************************************
	 * Helper functions for compares and swaps.
	 ***************************************************************************/
	private boolean greater(int i, int j) {
		if (comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
		} else {
			return comparator.compare(pq[i], pq[j]) > 0;
		}
	}

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// is pq[1..n] a min heap?
	private boolean isMinHeap() {
		for (int i = 1; i <= n; i++) {
			if (pq[i] == null)
				return false;
		}
		for (int i = n + 1; i < pq.length; i++) {
			if (pq[i] != null)
				return false;
		}
		if (pq[0] != null)
			return false;
		return isMinHeapOrdered(1);
	}

	// is subtree of pq[1..n] rooted at k a min heap?
	private boolean isMinHeapOrdered(int k) {
		if (k > n)
			return true;
		int left = 2 * k;
		int right = 2 * k + 1;
		if (left <= n && greater(k, left))
			return false;
		if (right <= n && greater(k, right))
			return false;
		return isMinHeapOrdered(left) && isMinHeapOrdered(right);
	}

	public Iterator<Key> iterator() {
		// --------------------------------------------------------
		// Summary: Returns an iterator that iterates over the keys on this priority
		// queue in
		// ascending order.
		// Precondition: There is no precondition.
		// Postcondition: return new HeapIterator();
		// --------------------------------------------------------

		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<Key> {
		// create a new pq
		private MinPQ<Key> copy;

		// add all items to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator() {
			if (comparator == null)
				copy = new MinPQ<Key>(size());
			else
				copy = new MinPQ<Key>(size(), comparator);
			for (int i = 1; i <= n; i++)
				copy.insert(pq[i]);
		}

		public boolean hasNext() {
			return !copy.isEmpty();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Key next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return copy.delMin();
		}
	}

}
