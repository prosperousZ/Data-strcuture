package assignment07;

import assignment06.DoublyLinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author Michael Sullivan, Haoze Zhang
 */
public class LinkedListStack<E> {
	//Create a stack
	private DoublyLinkedList<E> stack;

	//Constructor
	public LinkedListStack() {
		stack = new DoublyLinkedList<E>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear() {
		stack.clear();
	}

	/**
	 * Returns true if the stack contains no elements.
	 */
	public boolean isEmpty() {
		if(stack.isEmpty() == true){
			return true;
		}
		return false;
	}

	/**
	 * Returns the item at the top of the stack without removing it from the
	 * stack. Throws NoSuchElementException if the stack is empty.
	 */
	public E peek() throws NoSuchElementException {
		if(stack.isEmpty() == true){
			throw new NoSuchElementException("the stack is empty");
		}
		return stack.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public E pop() throws NoSuchElementException {
		if(stack.isEmpty() == true){
			throw new NoSuchElementException("the stack is empty");
		}
		return stack.removeFirst();
	}

	/**
	 * Pushes the input item onto the top of the stack.
	 */
	public void push(E item) {
		stack.addFirst(item);
	}

	/**
	 * Returns the number of items in the stack.
	 */
	public int size() {
		return stack.size();
	}
}
