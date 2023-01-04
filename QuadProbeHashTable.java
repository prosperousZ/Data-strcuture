package assignment10;

import java.util.Collection;

/**
 * 
 * @author Nicholas Fuller & Haoze Zhang
 *
 */
public class QuadProbeHashTable implements Set<String> {

	private String[] table;
	private HashFunctor functor;
	private int size;
	private int capacity;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		this.capacity = capacity;
		// To create the array of prime number
		table = new String[nextPrime(capacity)];
		this.functor = functor;
		size = 0;
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
		if (contains(item)) {
			return false;
		}
		rehash();
		int hashed = functor.hash(item);
		// If the table does not need to grow and the there is nothing on
		// the spot
		if (table[hashed % table.length] == null) {
			table[hashed % table.length] = item;
			size++;
			return true;
		}
		// If the table does not need to grow and there is something on the spot
		// that has to step forward which use the quadratic probing
		else if (table[hashed % table.length] != null) {
			// Use the quadratic probe
			int quad = 0;
			while ((hashed + (quad * quad)) % table.length < table.length
					&& table[(hashed + (quad * quad)) % table.length] != null) {
				quad++;
			}
			table[(hashed + (quad * quad)) % table.length] = item;
			size++;
			return true;
		}
		return false;
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
		//if the collection is empty, return false
		if(items.size() == 0){
			return false;
		}
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
	@Override
	public void clear() {
		// To create the array of prime number
		table = new String[nextPrime(capacity)];
		size = 0;
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
		int hashed = functor.hash(item);
		int quad = 0;
		boolean isItem = false;
		if (table[hashed % table.length] != null
				&& table[hashed % table.length].equals(item)) {
			return true;
		} else if (table[hashed % table.length] != null) {
			// Use the quadratic probe
			while ((hashed + (quad * quad)) % table.length < table.length - 1 && table[(hashed + (quad * quad)) % table.length] != null) {
				isItem = table[(hashed + (quad * quad)) % table.length] != null
						&& table[(hashed + (quad * quad)) % table.length]
								.equals(item);
				if (isItem) {
					return true;
				}
				quad++;
			}
		}
		return false;
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
		//If the collection is empty, return false
		if(items.size() == 0){
			return false;
		}
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
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	private void rehash() {
		// If the table has to grow
		if (table.length / 2 <= size) {
			// Create the temp table
			String[] tempTable = table;
			table = new String[nextPrime(table.length)];
			size = 0;
			for (int i = 0; i < tempTable.length; i++) {
				if (tempTable[i] != null) {
					add(tempTable[i]);
				}
			}
		}
	}

	/**
	 * To decide if the number if prime number
	 * 
	 * @param n
	 *            - the number we input
	 * @return true if the number is prime
	 */
	// checks whether an int is prime or not.
	private boolean isPrime(int n) {
		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	
	}

	/**
	 * To find the next prime number
	 * 
	 * @param n
	 *            - the number we input
	 * @return - the number prime number
	 */
	private int nextPrime(int n) {
		if(isPrime(n)){
			n++;
		}
		while (isPrime(n) == false) {
			n++;
		}
		return n;

	}
}
