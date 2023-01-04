package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.experimental.theories.Theories;

/**
 * @author Haoze Zhang 
 * u0904009 
 * I do not have partner this time, i will finish this assignment by my own
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	// The size is the size of the list
	int size;
	// The head of the list
	Node head;
	// The tail of the list
	Node tail;

	/**
	 * Include a zero-parameter constructor, public DoublyLinkedList(). This is
	 * known as the 'default constructor'. Do not use Java's LinkedList class.
	 */
	// Constructor
	public DoublyLinkedList() {
		size = 0;
	}

	/**
	 * This method is the special case when user add to the empty list
	 * @param element
	 */
	private void addOnlyItem(E element) {
		Node item = new Node();
		item.elementInNode = element;
		head = item;
		tail = item;
		size++;
	}

	/**
	 * Inserts the specified element at the beginning of the list. 
	 * O(1) for a doubly-linked list.
	 * @param the element that user want to to add to the first of the list
	 */
	@Override
	public void addFirst(E element) {
		// Add to empty list
		if (size == 0) {
			addOnlyItem(element);
			return;
		}
		// Create a new node
		Node newHead = new Node();
		// Make this node to new head
		head.prev = newHead;
		newHead.next = head;
		// Add the element to the new head
		newHead.elementInNode = element;
		head = newHead;
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list. 
	 * O(1) for doubly-linked list.
	 * @param the element that user want to to add to the last of the list
	 */
	@Override
	public void addLast(E o) {
		// Add to empty list
		if (size == 0) {
			addOnlyItem(o);
			return;
		}
		// Create a new node
		Node newTail = new Node();
		// Make this node to new tail
		tail.next = newTail;
		newTail.prev = tail;
		// Add the element to the new tail
		newTail.elementInNode = o;
		tail = newTail;
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 * @param index means the position that user want to add, the element is what user want to add
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		// If the index is out of bounds of the list
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
		// When the index equals to size of the list, which means the index to
		// add the element to the end of list
		if (index == size) {
			addLast(element);
			return;
		}
		// When the index equals to 0, which means the index to add the element
		// to the first of list
		if (index == 0 && index < size) {
			addFirst(element);
			return;
		}
		// Create a node which is pointed to head
		Node h = head;
		h.next = head.next;
		// Create a node which is pointed to tail
		Node t = tail;
		t.prev = tail.prev;
		// Find the elements from head
		for (int j = 1; j < index; j++) {
			h = h.next;
		}
		// Find the elements from tail
		for (int i = size - 1; i > index; i--) {
			t = t.prev;
		}
		// Create the new Node
		Node node = new Node();
		// Connect to the previous element of the new node
		node.prev = h;
		// Connect to the next element of the new node
		node.next = t;
		node.prev.next = node;
		node.next.prev = node;
		t.prev = node;
		h.next = node;
		node = t.prev;
		node = h.next;
		// Add the element to the node
		node.elementInNode = element;
		size++;
	}

	/**
	 * Get the first element in the list
	 * Throws NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
	 * @return The first element in the list
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		// If the list is empty, throw exception
		if (size == 0) {
			throw new NoSuchElementException(
					"There is no elements in the list which means the list is empty");
		}
		// Get the first element
		return head.elementInNode;
	}

	/**
	 * Get the last element in the list
	 * Throws NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
	 * @return Returns the last element in the list. 
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		// If the list is empty, throw exception
		if (size == 0) {
			throw new NoSuchElementException(
					"There is no elements in the list which means the list is empty");
		}
		// Get the last element
		return tail.elementInNode;
	}

	/**
	 * Get the element in the index of node
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >=size()) O(N) for a doubly-linked list.
	 * @param the element of specified position that user want to 
	 * @return the element of the index node
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// If the list is empty, and the index is out of bounds, throw exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
					"There is no elements in the list which means the list is empty");
		}
		// Search from head
		Node h = head;
		for (int j = 0; j < index; j++) {
			h = h.next;
		}
		// Return the element in the node
		return h.elementInNode;
	}

	/**
	 * This method for only when the size is one, when user call remove method
	 */
	private void removeOnlyItem() {
		Node empty = new Node();
		head = empty;
		tail = empty;
		size--;
	}

	/**
	 * Removes first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a
	 * doubly-linked list.
	 * @return the first element
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		// If the list is empty, throw exception
		if (size == 0) {
			throw new NoSuchElementException(
					"There is no elements in the list which means the list is empty");
		}
		// If there is only one element in the list,
		if (size == 1) {
			Node firstElement = head;
			removeOnlyItem();
			return firstElement.elementInNode;
		}
		Node first = head;
		// change old head.next to new head
		head = head.next;
		// old head change to null
		head.prev = null;
		size--;
		return first.elementInNode;
	}

	/**
	 * Removes the last element from the list. 
	 * Throws NoSuchElementException if the list is empty. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @return the last element in the list
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		// If the list is empty, throw exception
		if (size == 0) {
			throw new NoSuchElementException(
					"There is no elements in the list which means the list is empty");
		}
		// If there is only one element in the list,
		if (size == 1) {
			Node lastElement = tail;
			removeOnlyItem();
			return lastElement.elementInNode;
		}
		Node last = tail;
		// change old tail.prev to new tail
		tail = tail.prev;
		// old tail change to null
		tail.next = null;
		size--;
		return last.elementInNode;
	}

	/**
	 * Removes the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size()) O(N) for a doubly-linked list.
	 * @param The element of specified position that user want to remove
	 * @return the element in the specified index
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// If the list is empty, throw exception or the index is out of bounds
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
		// When remove the last element in the list
		if (index == size - 1) {
			return removeLast();
		}
		// When remove the first element in the list
		if (index == 0 && size != 1) {
			return removeFirst();
		}
		// Create a new head
		Node newHead = head;
		newHead.next = head.next;
		// Create a new tail
		Node newTail = tail;
		newTail.prev = tail.prev;
		// Go through every node to the previous of the index of the element
		// from head
		for (int j = 0; j < index; j++) {
			newHead = newHead.next;
		}
		// Go through every node to the next of the index of the element from
		// tail
		for (int i = size - 1; i > index + 1; i--) {
			newTail = newTail.prev;
		}
		// Connect the previous node of the element which required to remove to
		// the next node of the element
		Node he = newHead.prev;
		he.next = newTail;
		newTail.prev = he;
		E element = newHead.elementInNode;
		// When user call remove method, size - 1
		size--;
		return element;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 * @param the element that user are looking for
	 * @return the index of the element when first occurrence
	 */
	//The method do not require throw any exception, so I make if the list is empty, user call the method.
	//The method will return -1, which means the element is not found
	@Override
	public int indexOf(E element) {
		if(size == 0){
			return -1;
		}
		// Search from head
		Node h = head;
		// If there is only 1 element in the list
		if (element.equals(h.elementInNode)) {
			return 0;
		}
		// Go through each elements in the list, find which node's element is
		// the same as the element user search for
		for (int i = 1; i < size; i++) {
			h = h.next;
			if (element.equals(h.elementInNode)) {
				// Return that index
				return i;
			}
		}
		// If the element is not contained in the list, return -1
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. 
	 * O(N) for a doubly-linked list.
	 * @param the element that user want to get when the position is the last occurrence
	 * @return the index of the element when last occurrence
	 */
	
	//The method do not require throw any exception, so I make if the list is empty, user call the method.
	//The method will return -1, which means the element is not found
	@Override
	public int lastIndexOf(E element) {
		if(size == 0){
			return -1;
		}
		// Search from head
		Node h = head;
		int bestSoFar = 0;
		// If there is only 1 element in the list
		if (element.equals(h.elementInNode)) {
			return 0;
		}
		// Go through each elements in the list, find which node's element is
		// the same as the element user search for
		for (int i = 1; i < size; i++) {
			h = h.next;
			if (element.equals(h.elementInNode)) {
				bestSoFar = i;
			}
		}
		// If the element is not contained in the list
		if (bestSoFar == 0) {
			return -1;
		}
		// Return the index
		return bestSoFar;
	}

	/**
	 * Returns the number of elements in this list. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements. 
	 * O(1) for a doubly-linked list.
	 * @return if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes all of the elements from this list. 
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		size = 0;
		// Change head and tail to null
		head = new Node();
		tail = new Node();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence (from first to last element). 
	 * O(N) for a doubly-linked list.
	 * @return change the list to array
	 */
	@Override
	public Object[] toArray() {
		// Change from the head
		Node h = head;
		// Create the array which length is the same as the list
		Object[] arr = new Object[size];
		// If the list is empty
		if (size == 0) {
			return arr;
		}
		// Add head into the element of array
		arr[0] = h.elementInNode;
		// Add each elements
		for (int i = 1; i < arr.length; i++) {
			h = h.next;
			arr[i] = h.elementInNode;
		}
		return arr;
	}

	/**
	 * Iterator
	 * 
	 * @return the iterator that user create
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			// From head
			Node current = head;
			// Control if the next() method was called
			int index = -1;
			// Control if the next() method was called
			boolean hasCalledNext = false;

			/**
			 * Returns true if the iteration has more elements. 
			 * (In other words, returns true if next() would return an element rather than throwing an exception.)
			 * @return true if there is next element in the list, false otherwise
			 */
			@Override
			public boolean hasNext() {
				if (index < size - 1) {
					return true;
				}
				return false;
			}

			/**
			 * the next element in the iteration
			 * @return the next element when user call next() method
			 */
			@Override
			public E next() {
				// If the iterator has coming the last node, and user called the
				// next method again, return throw exception
				if (index >= size - 1 && size > 1) {
					throw new NoSuchElementException("Out of bounds");
				}
				// If the list is empty, throw exception
				if (size == 0) {
					throw new NoSuchElementException("The list is empty");
				}
				// If there is only one elements in the list
				if (index == -1) {
					index++;
					hasCalledNext = true;
					return current.elementInNode;
				}
				// Otherwise
				current = current.next;
				hasCalledNext = true;
				index++;
				return current.elementInNode;
			}

			/**
			 * Removes from the underlying collection the last element returned by this iterator (optional operation). 
			 * This method can be called only once per call to next(). 
			 * The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
			 */
			@Override
			public void remove() {
				if (index <= -1 || hasCalledNext == false) {
					throw new IllegalStateException(
							"Have not called next() yet");
				}
				// if head, special case
				if (current == head) {
					current = current.next;
					if (current != null) { // edge case of one element
						current.prev = null;
					}
					head = current;
				} 
				//If tail, special case
				else if (current == tail) {
					current = current.prev;
					if (current != null) { // edge case of one element
						current.next = null;
					}
					head = current;
				} 
				//Otherwise
				else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
					current = current.prev;
				}
				index--;
				size--;
				hasCalledNext = false;
			}
		};
	}
	//The node class
	class Node {
		E elementInNode;
		Node prev, next;

	}
}
