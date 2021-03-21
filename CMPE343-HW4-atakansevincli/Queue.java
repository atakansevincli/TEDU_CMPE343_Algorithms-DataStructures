import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	// -----------------------------------------------------
	// Title:Queue<Item>
	// Author: Atakan Sevincli
	// Section: 1
	// Assignment: 4
	// Description: This class define Queue<Item> Object
	// -----------------------------------------------------
	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue
	private int n; // number of elements on queue

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	public Queue() {
		// --------------------------------------------------------
		// Summary: Initializes an empty queue.
		// Precondition: There is no precondition
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		first = null;
		last = null;
		n = 0;
	}

	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: Returns true if this queue is empty.
		// Precondition: There is no precondition
		// Postcondition: Return boolean
		// --------------------------------------------------------
		return first == null;
	}

	public int size() {
		// --------------------------------------------------------
		// Summary: Returns the number of items in this queue.
		// Precondition: There is no precondition
		// Postcondition: Return size
		// --------------------------------------------------------
		return n;
	}

	public Item peek() {
		// --------------------------------------------------------
		// Summary: Returns the item least recently added to this queue.
		// Precondition: There is no precondition
		// Postcondition: NoSuchElementException if this queue is empty
		// --------------------------------------------------------
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
	}

	public void enqueue(Item item) {
		// --------------------------------------------------------
		// Summary: Adds the item to this queue.
		// Precondition: Item item
		// Postcondition: There is no post condition
		// --------------------------------------------------------
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		n++;
	}

	public Item dequeue() {
		// --------------------------------------------------------
		// Summary: Removes and returns the item on this queue that was least recently
		// added.
		// Precondition: There is no precondition.
		// Postcondition: Removes and returns the item on this queue that was least
		// recently added.
		// --------------------------------------------------------
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	public String toString() {
		// --------------------------------------------------------
		// Summary: Returns a string representation of this queue.
		// Precondition: There is no precondition.
		// Postcondition: Returns a string representation of this queue.
		// --------------------------------------------------------
		StringBuilder s = new StringBuilder();
		for (Item item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}

	public Iterator<Item> iterator() {
		// --------------------------------------------------------
		// Summary: Returns an iterator that iterates over the items in this queue in
		// FIFO order.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return new LinkedIterator(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

}