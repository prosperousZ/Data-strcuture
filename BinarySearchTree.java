package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * 
 * @author Paul Carlson, Haoze Zhang
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
	/*
	 * I take the private off, I want to use root in the test case
	 */
	private BinaryNode root;
	private int size;

	public BinarySearchTree() {
		root = null;
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
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean add(T item) {
		if (item == null) {
			throw new NullPointerException("the item is null");
		}

		// Is the item in the tree already?
		if (contains(item) == true) {
			return false;
		}
		// If there is no root add the item there
		if (root == null) {
			root = new BinaryNode(item);
			size++;
			return true;
		}

		// No, so add the node at the correct location in the tree!
		BinaryNode newNode = new BinaryNode(item);
		addNode(item, root, newNode);
		size++;
		return true;
	}

	/**
	 * A helper method to add a node to a binary. Recursively calls itself until
	 * the correct location is found.
	 * 
	 * @param item
	 *            -- the data we will store in the new node we are creating in
	 *            the tree
	 * @param currentNode
	 *            -- the node the we are currently looking
	 */
	private void addNode(T item, BinaryNode currentNode, BinaryNode newNode) {
		// If item goes to the right and there is no right node, add item to the
		// right
		if (item.compareTo(currentNode.data) > 0 && currentNode.right == null) {
			currentNode.right = newNode;
			return;
		}
		// If item goes to the left and there is no left node, add item to the
		// left
		else if (item.compareTo(currentNode.data) < 0 && currentNode.left == null) {
			currentNode.left = newNode;
			return;
		}
		// Keep looking for a place to put the new node
		else {
			if (item.compareTo(currentNode.data) > 0) {
				addNode(item, currentNode.right, newNode);
			} else {
				addNode(item, currentNode.left, newNode);
			}
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
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean addAll(Collection<? extends T> items) {
		int startSize = size; // Store the starting size of the tree
		// Loop through each item in the collection and try adding it to the
		// tree
		for (T element : items) {
			if (element == null) {
				throw new NullPointerException("There is item is null");
			} else {
				add(element);
			}
		}
		// Did we add anything to the tree?
		return size > startSize;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		root = null;
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
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean contains(T item) {
		BinaryNode newRoot = root;
		// If the item is null
		if (item == null) {
			throw new NullPointerException("The item is null");
		}
		// Otherwise look through the tree for the item
		while (newRoot != null) {
			// We found it!
			if (newRoot.data.compareTo(item) == 0) {
				return true;
			}
			if (newRoot.left != null && item.compareTo(newRoot.data) < 0) {
				// Look left
				newRoot = newRoot.left;
			} else if (newRoot.right != null && item.compareTo(newRoot.data) > 0) {
				// Look right
				newRoot = newRoot.right;
			} else
				// There's nowhere to go from here
				newRoot = null;
		}
		// We didn't find it :(
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
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean containsAll(Collection<? extends T> items) {
		// Loop through each item in the collection and check if it is in the
		// tree
		for (T element : items) {
			if (element == null) {
				throw new NullPointerException("There is item which is null");
			} else {
				if (!contains(element)) {
					return false;
				}
			}
		}
		// All the items were there so return true!
		return true;
	}

	/**
	 * Returns the first (i.e., smallest) item in this tree.
	 * 
	 * @throws NoSuchElementException
	 *             if the tree is empty
	 */
	@Override
	public T first() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("the set is empty");
		}
		return getLeftmostNode(root).data;
	}

	/**
	 * A helper method to search for the "first" or smallest item in the tree
	 * 
	 * @param currentNode
	 *            -- the node we are currently looking at
	 * @return the smallest item in the tree
	 */
	private BinaryNode getLeftmostNode(BinaryNode currentNode) {
		if (currentNode.left == null) {
			return currentNode;
		}
		return getLeftmostNode(currentNode.left);
	}

	/**
	 * Returns true if this tree contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the last (i.e., largest) item in this tree.
	 * 
	 * @throws NoSuchElementException
	 *             if the tree is empty
	 */
	@Override
	public T last() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("the set is empty");
		}
		return getRightmostNode(root).data;
	}

	/**
	 * A helper method to search for the "last" or largest item in the tree
	 * 
	 * @param currentNode
	 *            -- the node we are currently looking at
	 * @return the smallest item in the tree
	 */
	private BinaryNode getRightmostNode(BinaryNode currentNode) {
		if (currentNode.right == null) {
			return currentNode;
		}
		return getRightmostNode(currentNode.right);
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *            - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually removed); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean remove(T item) {
		// If the item is null throw an exception
		if (item == null) {
			throw new NullPointerException("the item is null");
		}
		// If the tree is empty or the item is not in the tree return false
		if (size == 0) {
			return false;
		}
		// Go ahead and remove the item
		// Case of only root and removing root
		if (size == 1 && item.compareTo(root.data) == 0) {
			clear();
			return true;
		}

		BinaryNode parentNode = findParent(item);
		// Search for the parent node whose child we want to remove
		if (parentNode == null) {
			return false;
		}

		// Get the number of children for the node you are removing
		int numChildren;

		if (parentNode.equals(root) && parentNode.data.compareTo(item) == 0) {
			numChildren = parentNode.numberOfChildren();
		} else if (parentNode.left != null && parentNode.left.data.compareTo(item) == 0) {
			numChildren = parentNode.left.numberOfChildren();
		} else {
			numChildren = parentNode.right.numberOfChildren();
		}

		// remove the node
		switch (numChildren) {
		case 0:
			// Case of 0 children
			removeNoChild(item, parentNode);
			size--;
			break;
		case 1:
			// Case of 1 child
			removeOneChild(item, parentNode);
			size--;
			break;
		case 2:
			// Case of 2 children
			removeTwoChild(item, parentNode);
			break;
		}
		return true;
	}

	/**
	 * A method to look for the parent of a node that we want to remove from the
	 * tree. Returns the parent node if the item is in the tree and false
	 * otherwise.
	 * 
	 * @param item
	 *            -- the item we want to remove from the tree
	 * @return the parent node of the item we want to remove, null if the item
	 *         is not in the list.
	 */
	private BinaryNode findParent(T item) {
		BinaryNode newRoot = root;
		if (newRoot.data.compareTo(item) == 0) {
			return newRoot;
		}
		// Look through the tree for the item until we find it or run into a
		// leaf node
		while (newRoot != null) {
			// We found it!
			if (newRoot.right != null && newRoot.right.data.compareTo(item) == 0) {
				return newRoot;
			} else if (newRoot.left != null && newRoot.left.data.compareTo(item) == 0) {
				return newRoot;
			}

			// We need to keep looking
			// If there is a null node in the next place to look, item is not in
			// the tree
			if ((newRoot.left == null && newRoot.right == null)
					|| (newRoot.left == null && item.compareTo(newRoot.data) < 0)
					|| (newRoot.right == null && item.compareTo(newRoot.data) > 0)) {
				return null;
			}

			else if (newRoot.left != null && item.compareTo(newRoot.data) < 0) {
				// Look left
				newRoot = newRoot.left;

			} else if (newRoot.right != null && item.compareTo(newRoot.data) > 0) {
				// Look right
				newRoot = newRoot.right;

			} else {
				// Something wrong happened but we don't want to loop
				// forever so exit loop
				newRoot = null;
			}

		}
		return null;
	}

	/**
	 * Remove a node that has no children
	 * 
	 * @param item
	 *            - the item to be removed from the tree
	 */
	private void removeNoChild(T item, BinaryNode parentNode) {
		// If the node being removed is to the left of the parentNode
		if (parentNode.left.data.compareTo(item) == 0) {
			parentNode.left = null;
			// If the node being removed is to the right of the parentNode
		} else if (parentNode.right.data.compareTo(item) == 0) {
			parentNode.right = null;
		}
	}

	/**
	 * Remove a node that has one child
	 * 
	 * @param item
	 *            -- the item to be removed from the tree
	 */
	private void removeOneChild(T item, BinaryNode parentNode) {
		// If the root node is being removed
		if (parentNode.equals(root) && parentNode.data.compareTo(item) == 0) {
			if (root.left != null) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		// If the node being removed is on the left
		else if (parentNode.left != null && parentNode.left.data.compareTo(item) == 0) {
			if (parentNode.left.left != null) {
				parentNode.left = parentNode.left.left;
			} else {
				parentNode.left = parentNode.left.right;
			}
		}
		// If the node being removed is on the right
		else if (parentNode.right != null && parentNode.right.data.compareTo(item) == 0) {
			if (parentNode.right.right != null) {
				parentNode.right = parentNode.right.right;
			} else {
				parentNode.right = parentNode.right.left;
			}
		}
	}

	/**
	 * Remove a node that had two children
	 * 
	 * @param item
	 *            -- the item to be removed from the tree
	 */
	private void removeTwoChild(T item, BinaryNode parentNode) {
		// Get the node being removed
		BinaryNode currentNode;
		if (parentNode.equals(root) && parentNode.data.compareTo(item) == 0) {
			currentNode = root;
		} else if (parentNode.left.data.compareTo(item) == 0) {
			currentNode = parentNode.left;
		} else {
			currentNode = parentNode.right;
		}

		// Get the smallest value to the right of the node
		T tempItem = getLeftmostNode(currentNode.right).data;
		// Remove that node
		// BinaryNode tempParent = findParent(tempItem);
		// removeNoChild(tempItem, tempParent);
		// swap in the new value for the node.
		remove(tempItem);
		currentNode.data = tempItem;
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items
	 *            - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually removed);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean removeAll(Collection<? extends T> items) {
		int startSize = this.size; // Store the starting size of the tree
		// Loop through each item in the collection and try removing it to the
		// tree
		int i = 0;
		for (T element : items) {
			if (element == null) {
				throw new NullPointerException("There is item is null");
			} else {
				remove(element);
				writeDot("remove_" + i);
				i++;
			}
		}
		// Did we remove anything from the tree?
		return size < startSize;
	}

	/**
	 * Returns the number of items in this tree. Not to be confused with the
	 * tree height!
	 * 
	 * @return the size of the tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the root node of the tree
	 * 
	 * @return the root node of this tree
	 */
	public BinaryNode getRoot() {
		return root;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 * 
	 * @return an ArrayList of the elements in the tree in sorted order
	 */
	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> arr = new ArrayList<T>();
		//The case when the binary search tree is empty
		if(size == 0){
			return arr;
		}
		addToArray(arr, root);
		return arr;
	}

	/**
	 * Add elements to the provided array list. recursively calls itself until
	 * we reach a leaf.
	 * 
	 * @param arr
	 *            -- the ArrayList being created from the tree
	 * @param currentNode
	 *            -- the node we are looking at right now
	 */
	private void addToArray(ArrayList<T> arr, BinaryNode currentNode) {
		// If we are at a leaf, add the value at that node to the list
		if (currentNode.left == null && currentNode.right == null) {
			arr.add(currentNode.data);
		}
		// Otherwise, look down the left and right branches from the node
		else {
			if (currentNode.left != null) { // Look left
				addToArray(arr, currentNode.left);
			}
			// add the value at this node to the list
			arr.add(currentNode.data);
			if (currentNode.right != null) { // Look right
				addToArray(arr, currentNode.right);
			}
		}
	}

	/**
	 * Creates a dot file representation of the binary search tree to be viewed
	 * using graphViz. Implementation copied from code provided in BST.java
	 * 
	 * @param filename
	 *            -- the name the file will be save as.
	 */
	public void writeDot(String filename) {
		try {
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));

			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");

			if (root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * A recursive method for writing this tree to a dot file.
	 * 
	 * @param n
	 *            -- the current binary node
	 * @param output
	 *            -- the PrintWriter creating the dot file
	 * @throws Exception
	 */
	private void writeDotRecursive(BinaryNode n, PrintWriter output) throws Exception {
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if (n.left != null) {
			// write the left subtree
			writeDotRecursive(n.left, output);

			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D");
		}
		if (n.right != null) {
			// write the left subtree
			writeDotRecursive(n.right, output);

			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D");
		}

	}

	/**
	 * An inner class to create the nodes of the BinarySearchTree, each node
	 * stores an data and a reference to it's "left" and "right" children
	 * 
	 * @author Paul Carlson, Haoze Zhang
	 *
	 */
	class BinaryNode {
		T data;
		BinaryNode left;
		BinaryNode right;

		/**
		 * Create a new node with children.
		 * 
		 * @param data
		 *            -- whatever data you want to store in the node
		 * @param left
		 *            -- the left hand child
		 * @param right
		 *            -- the right hand child
		 */
		public BinaryNode(T data, BinaryNode left, BinaryNode right) {
			this.data = data;
			this.right = right;
			this.left = left;
		}

		/**
		 * Create a new node with no children.
		 * 
		 * @param data
		 *            -- whatever information you want to store in the node.
		 */
		public BinaryNode(T data) {
			this(data, null, null);
		}

		/**
		 * Returns the number of direct children of this node
		 * 
		 * @return 0 if left and right are null, 1 if left or right are null, 2
		 *         if neither are null
		 */
		public int numberOfChildren() {
			if (left == null && right == null) {
				return 0;
			} else if (left == null || right == null) {
				return 1;
			} else {
				return 2;
			}
		}

	}

}
