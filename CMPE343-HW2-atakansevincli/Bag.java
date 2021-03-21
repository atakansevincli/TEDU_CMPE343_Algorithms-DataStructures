import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

	// -----------------------------------------------------
	// Title:Bag<Item>
	// Author: Atakan Sevinçli
	// Section: 1
	// Assignment: 2
	// Description: This class define Bag Object
	// -----------------------------------------------------

	private Node<Item> first;
	private int n;
	private String name;
	private int x, y; // cordinates

	private static class Node<Item> {

		private Item item;
		private Node<Item> next;
	}

	public Bag(String name, int x, int y) {
		// --------------------------------------------------------
		// Summary: Constructor an empty bag.
		// Precondition: String name, int x, int y
		// Postcondition: Constructor of an empty bag.
		// --------------------------------------------------------
		this.name = name;
		this.x = x;
		this.y = y;
		first = null;
		n = 0;
	}

	public Bag() {
		// --------------------------------------------------------
		// Summary: Initializes an empty bag.
		// Precondition: String name, int x, int y
		// Postcondition: Initializes of an empty bag.
		// --------------------------------------------------------
		first = null;
		n = 0;
	}

	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: check is empty bag.
		// Precondition: There is no precondition.
		// Postcondition: return true if its empty.
		// --------------------------------------------------------
		return first == null;
	}

	public int size() {
		// --------------------------------------------------------
		// Summary: The number of items in this bag..
		// Precondition: There is no precondition.
		// Postcondition: Returns the number of items in this bag..
		// --------------------------------------------------------
		return n;
	}

	public int getX() {
		// --------------------------------------------------------
		// Summary: get X cordinates of a bag
		// Precondition: There is no precondition.
		// Postcondition: Returns x cordinate of a bag..
		// --------------------------------------------------------
		return x;
	}

	public String getName() {
		// --------------------------------------------------------
		// Summary: get Name cordinates of a bag
		// Precondition: There is no precondition.
		// Postcondition: Returns Name cordinate of a bag..
		// --------------------------------------------------------
		return name;
	}

	public int getY() {
		// --------------------------------------------------------
		// Summary: get y cordinates of a bag
		// Precondition: There is no precondition.
		// Postcondition: Returns y cordinate of a bag..
		// --------------------------------------------------------
		return y;
	}

	public void add(Item item) {
		// --------------------------------------------------------
		// Summary: Adds the item to this bag
		// Precondition: Item item.
		// Postcondition: Adds the item to this bag..
		// --------------------------------------------------------
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}

	public Iterator<Item> iterator() {
		// --------------------------------------------------------
		// Summary: Iterator
		// Precondition: Item item.
		// Postcondition: Returns an iterator that iterates over the items in this bag
		// in arbitrary order.
		// --------------------------------------------------------
		return new LinkedIterator(first);
	}

	private class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			// return true if it has next.
			return current != null;
		}

		public void remove() {
			// an iterator, doesn't implement remove() since it's optional
			throw new UnsupportedOperationException();
		}

		public Item next() {
			// return item's next
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}