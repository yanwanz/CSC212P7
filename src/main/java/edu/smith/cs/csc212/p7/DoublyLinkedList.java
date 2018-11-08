package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;


public class DoublyLinkedList<T> implements P6List<T>,Iterable<T> {
	private Node<T> start;
	private Node<T> end;

	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}


	@Override
	public T removeFront() {
		// O(1): Easy to do this because we keep track of the start node
		checkNotEmpty();
		
		T before = start.value;
		start = start.after;
		return before;
	}

	@Override
	public T removeBack() {
		// O(1): Easy to do this because we keep track of both the start node and the end node
		checkNotEmpty();
		
		//If only one element
		T removed = null;
		if (start.after == null) {
			removed = start.value;
			this.start = null;
		} else {
			// Two or more
			removed = end.value;
			end.before.after = null;
			this.end = end.before;
		}
		
		return removed;
		
	}

	@Override
	public T removeIndex(int index) {
		// O(n): In the worst case, we have to loop over the whole list
		// Empty list
		checkNotEmpty();
		
		
		//One element
		if (this.size() == 1) {
			if (index == 0) {
				return removeFront();
			}
		}else {
			//Remove first node
			if (index == 0) {
				return removeFront();
			}else if (index == this.size()-1) {
				//Remove last node
				return removeBack();
			} else {
				// Remove from the middle
				int at = 0;
				T removed = null;
				for (Node<T> current = start; current != null; current = current.after) {
					if (at == index) {
						removed = current.value;
						current.before.after = current.after;
						current.after.before = current.before;
						current = null;
						return removed;
					}
					at++;
				}
				
			}
			
		}
		
		
		// Index not found
		throw new BadIndexError();
		
	}

	@Override
	public void addFront(T item) {
		// O(1): Easy to do this because we keep track of both the start node and the end node
		
		Node<T> newNode = new Node<T>(item);
		newNode.after = start;
		newNode.before = null;
		
		//If not an empty list
		if (start != null) {
			start.before = newNode;
			//If there is only one element in the list
			if (this.size() == 1) {
				//Make sure we have the correct end
				this.end = start;
			}
		}
		
		this.start = newNode;
		
	}

	@Override
	public void addBack(T item) {
		// O(1): Easy to do this because we keep track of both the start node and the end node
		
		if (start == null) {
			addFront(item);
		}
		else {
			Node<T> back = new Node<T>(item);
			back.after = null;
			if (this.end == null) {
				back.before = start;
				start.after = back;
			} else {
				back.before = end;
				end.after = back;
			}
			
			this.end = back;
		}


	}

	@Override
	public void addIndex(T item, int index) {
		// O(n): In the worst case, have to loop over the whole list
		// First and last
		if (index == 0) {
			addFront(item);
		}else if (index == this.size()) {
			addBack(item);
		}else if (index < this.size() && index > 0) {
			//Add to the middle
			Node<T> added = new Node<T>(item);
			int at = 0;
			for (Node<T> current = start; current !=null; current=current.after) {
				if (at == index) {
					added.after = current;
					added.before = current.before;
					current.before.after = added;
					current.before = added;
					return;
				}
				at++;
			}
			
		}else {
			// Bad index ( <0 or >size )
			throw new BadIndexError();
		}
	}

	@Override
	public T getFront() {
		// O(1): Easy to do this because we keep track of the start node
		checkNotEmpty();
		return start.value;
	}

	@Override
	public T getBack() {
		// O(1): Easy to do this because we keep track of both the start node and the end node
		checkNotEmpty();
		if (this.end == null) {
			return start.value;
		}
		return end.value;
	}

	@Override
	public T getIndex(int index) {
		// O(n): In the worst case, have to loop over the whole list
		checkNotEmpty();
		// From SList.java in the test lab
		int at = 0;
		for (Node<T> current = start; current != null; current = current.after) {
			if (at == index) {
				return current.value;
			}
			at++;
		}
		// We couldn't find it, throw an exception!
		throw new BadIndexError();
	}

	@Override
	public int size() {
		// O(n): Have to loop over the whole list to keep track of the count of elements
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		// O(1): just check the start node and return a boolean
		if (this.start == null) {
			return true;
		}else {
			return false;
		}
	}

	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	public List<T> copyToList() {
        ArrayList<T> output = new ArrayList<T>();
        for (Node<T> n = this.start; n != null; n = n.after) {
                output.add(n.value);
        }
        return output;
}
	

	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}

	/**
	 * From the iterator implemented in Singly Linked List
	 * I made some changes to JJ's implementation
	 *
	 * @param <T>
	 */
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(DoublyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.after;
			return found;
		}
	}
	
	/**
	 * Implement iterator() so that {@code SinglyLinkedList} can be used in a for loop.
	 * @return an object that understands "next()" and "hasNext()".
	 */
	public Iterator<T> iterator() {
		return new Iter<>(this);
	}
	
}
