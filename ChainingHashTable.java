package assignment10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Nicholas Fuller & Haoze Zhang
 *
 */
public class ChainingHashTable implements Set<String> {

	private LinkedList<String>[] table;
	private int capacity;
	private int size;
	private HashFunctor functor;
	private final int largeLoad = 5;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		//Initial capacity and functor
		table = (LinkedList<String>[]) new LinkedList[capacity];
		size = 0;
		this.capacity = capacity;
		this.functor = functor;
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 */
	@Override
	public boolean add(String item) {
		//If there are duplicates, return false
		if (contains(item)) {
			return false;
		}
		//Decide if the table has to rehash
		rehash();
		int hashed = functor.hash(item);
		if (table[hashed % table.length] == null) {
			table[hashed % table.length] = new LinkedList<String>();
			table[hashed % table.length].add(item);
			size++;
			return true;
		} else {
			table[hashed % table.length].add(item);
			size++;
			return true;
		}
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		//If the items is empty, user can still call addAll method
		if(items.size() == 0){
			return false;
		}
		//If items is not empty
		for (String s : items) {
			if (changed == false) {
				changed = add(s);
			} else {
				add(s);
			}
		}
		return changed;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		table = (LinkedList<String>[]) new LinkedList[capacity];
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		//If the item is containing in the table
		int hashed = functor.hash(item);
		if (table[hashed % table.length] == null) {
			return false;
		} else {
			return table[hashed % table.length].contains(item);
		}
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 */

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean changed = true;
		for (String s : items) {
			if(!contains(s)){
				return false;
			}
		}
		return changed;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	/**
	 * the number of items in this set.
	 * @Returns - the size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * rehash
	 * Helper method
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		// When to rehash
		if (lambdaC() >= largeLoad) {
			// make new arraylist to store all linkedlist item
			ArrayList<LinkedList<String>> tempList = new ArrayList<LinkedList<String>>();
			// Copy to the new linkedlist
			for (LinkedList<String> s : table) {
				tempList.add(s);
			}
			// Grow
			capacity *= 2;
			table = (LinkedList<String>[]) new LinkedList[capacity];
			size = 0;
			// Copy all of the string to the linkedlist
			for (LinkedList<String> s : tempList) {
				for (int i = 0; i < s.size(); i++) {
					add(s.get(i));
				}
			}
		}
	}

	/**
	 * The method calculates the load factor of the table
	 * Helper method
	 * @return - the load factor of the table
	 */
	private int lambdaC() {
		int avg = 0;
		int numOfLists = 0;
		for (LinkedList<String> k : table) {
			if (k != null) {
				avg += k.size();
				numOfLists++;
			}
		}
		if(numOfLists == 0){
			return 0;
		}
		return avg / numOfLists;

	}

}
