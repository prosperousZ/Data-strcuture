package assignment08;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * 
 * @author Paul Carlson, Haoze Zhang
 *
 */
public class BinarySearchTreeTest2 {

	// Test the constructor of binarySearchTree
	@Test
	public void testBinarySearchTree() {
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
		// The new BinarySearchTree should be empty
		assertTrue(tree1.isEmpty());
		// The size is also 0
		assertEquals(0, tree1.size());
		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		assertTrue(tree2.isEmpty());
		// linked tree1 should be empty
		assertEquals(0, tree2.size());
	}

	@Test
	public void testAdd() {
		// Create an empty tree1
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		// Test if meet following condition, will it throw exception
		try {
			tree1.add(null);
			fail("the item is null");
		} catch (NullPointerException e) {
			assertEquals(0, tree1.size());
		}
		// Test when the size is 0
		assertTrue(tree1.add("a"));
		assertEquals(1, tree1.size());
		assertEquals("a", (String) tree1.getRoot().data);
		assertEquals(null, tree1.getRoot().left);
		assertEquals(null, tree1.getRoot().right);
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add("a");
		ArrayList<String> to1 = tree1.toArrayList();
		 assertEquals(arr1, to1);
		// Test when there is only one node in the tree, will the data of node
		// will be largest or smallest
		assertEquals("a", tree1.first());
		assertEquals("a", tree1.last());

		// Test if add the same item in the tree, will it return false
		assertFalse(tree1.add("a"));
		assertEquals(1, tree1.size());
		assertEquals("a", (String) tree1.getRoot().data);
		assertEquals(null, tree1.getRoot().left);
		assertEquals(null, tree1.getRoot().right);
		ArrayList<String> arr2 = new ArrayList<String>();
		ArrayList<String> to2 = tree1.toArrayList();
		arr2.add("a");
		assertEquals(arr2, to2);
		// Test when there is only one node in the tree, will the data of node
		// will be largest or smallest
		assertEquals("a", tree1.first());
		assertEquals("a", tree1.last());

		// Test when the size is 1, use the add method add item
		assertTrue(tree1.add("b"));
		assertEquals(2, tree1.size());
		assertEquals("a", (String) tree1.getRoot().data);
		assertEquals(null, tree1.getRoot().left);
		assertEquals("b", tree1.getRoot().right.data);
		arr2.add("b");
		ArrayList<String> to3 = tree1.toArrayList();
		assertEquals(arr2, to3);
		assertEquals("a", tree1.first());
		assertEquals("b", tree1.last());

		// Test larger group of number
		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		assertTrue(tree2.add("g"));
		assertTrue(tree2.add("d"));
		assertTrue(tree2.add("y"));
		assertTrue(tree2.add("n"));
		assertTrue(tree2.add("m"));
		assertTrue(tree2.add("b"));
		assertTrue(tree2.add("v"));
		assertTrue(tree2.add("o"));
		assertTrue(tree2.add("w"));
		assertTrue(tree2.add("p"));
		assertTrue(tree2.add("q"));
		assertTrue(tree2.add("f"));
		assertTrue(tree2.add("a"));
		assertEquals(13, tree2.size());
		assertEquals("g", (String) tree2.getRoot().data);
		assertEquals("d", (String) tree2.getRoot().left.data);
		assertEquals("y", (String) tree2.getRoot().right.data);
		ArrayList<String> arr4 = new ArrayList<String>();
		ArrayList<String> to4 = tree2.toArrayList();
		arr4.add("a");
		arr4.add("b");
		arr4.add("d");
		arr4.add("f");
		arr4.add("g");
		arr4.add("m");
		arr4.add("n");
		arr4.add("o");
		arr4.add("p");
		arr4.add("q");
		arr4.add("v");
		arr4.add("w");
		arr4.add("y");
		assertEquals(arr4, to4);
		//When add new item
		ArrayList<String> arr5 = new ArrayList<String>();
		arr5.add("a");
		arr5.add("aaa");
		arr5.add("b");
		arr5.add("d");
		arr5.add("f");
		arr5.add("g");
		arr5.add("m");
		arr5.add("n");
		arr5.add("o");
		arr5.add("p");
		arr5.add("q");
		arr5.add("v");
		arr5.add("w");
		arr5.add("y");
		assertEquals("a", tree2.first());
		assertEquals("y", tree2.last());
		assertTrue(tree2.add("aaa"));
		assertEquals(14, tree2.size());
		ArrayList<String> to5 = tree2.toArrayList();
		assertEquals(arr5, to5);
	}

	@Test
	public void testAddAll() {

	}

	// Test if clear() method is called, will the set clear all the items
	@Test
	public void testClear() {
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		tree1.add("a");
		tree1.add("b");
		tree1.add("c");
		assertEquals(3, tree1.size());
		tree1.clear();
		assertEquals(0, tree1.size());
		assertEquals(null, tree1.getRoot());
		try {
			tree1.first();
			fail("the set is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, tree1.size());
		}
		try {
			tree1.last();
			fail("the set is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, tree1.size());
		}
		assertTrue(tree1.isEmpty());
		// Test larger group of number
		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		assertTrue(tree2.add("g"));
		assertTrue(tree2.add("d"));
		assertTrue(tree2.add("y"));
		assertTrue(tree2.add("n"));
		assertTrue(tree2.add("m"));
		assertTrue(tree2.add("b"));
		assertTrue(tree2.add("v"));
		assertTrue(tree2.add("o"));
		assertTrue(tree2.add("w"));
		assertTrue(tree2.add("p"));
		assertTrue(tree2.add("q"));
		assertTrue(tree2.add("f"));
		assertTrue(tree2.add("a"));
		assertEquals(13, tree2.size());
		tree2.clear();
		assertEquals(0, tree2.size());
		assertEquals(null, tree2.getRoot());
		try {
			tree2.first();
			fail("the set is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, tree2.size());
		}
		try {
			tree2.last();
			fail("the set is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, tree2.size());
		}
	}

	@Test
	public void testContains() {
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		try {
			tree1.contains(null);
			fail("The item is null");
		} catch (NullPointerException e) {
			assertEquals(0, tree1.size());
		}
		// Test when there is nothing in the set
		assertFalse(tree1.contains("a"));
		// Test when there is only one item in the set
		assertTrue(tree1.add("a"));
		assertFalse(tree1.contains("b"));
		assertTrue(tree1.contains("a"));

		// Test larger group of number
		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		assertTrue(tree2.add("g"));
		assertTrue(tree2.add("d"));
		assertTrue(tree2.add("y"));
		assertTrue(tree2.add("n"));
		assertTrue(tree2.add("m"));
		assertTrue(tree2.add("b"));
		assertTrue(tree2.add("v"));
		assertTrue(tree2.add("o"));
		assertTrue(tree2.add("w"));
		assertTrue(tree2.add("p"));
		assertTrue(tree2.add("q"));
		assertTrue(tree2.add("f"));
		assertTrue(tree2.add("a"));
		assertFalse(tree2.contains("aa"));
		assertTrue(tree2.contains("a"));
		assertTrue(tree2.contains("y"));
		assertFalse(tree2.contains("y a"));
		// Test if call add method is not successful, will the return the item
		assertTrue(tree2.add("qwer"));
		assertTrue(tree2.contains("qwer"));
		assertFalse(tree2.add("g"));
		assertTrue(tree2.contains("g"));
		assertEquals(14, tree2.size());
	}

	@Test
	public void testContainsAll() {
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
		for (int i = 1; i < 101; i++) {
			arr1.add(i);
		}
		for (int j = 1; j < 101; j++) {
			tree1.add(j);
		}
		// Test if they have excatly the same items
		assertTrue(tree1.containsAll(arr1));
		// Add things to specified set, but things are duplicate
		arr1.add(100);
		assertTrue(tree1.containsAll(arr1));
		// Add things that not duplicate
		arr1.add(10000);
		assertFalse(tree1.containsAll(arr1));

		ArrayList<String> arr2 = new ArrayList<String>();
		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		arr2.add("y");
		arr2.add("v");
		arr2.add("a");
		assertTrue(tree2.add("g"));
		assertTrue(tree2.add("d"));
		assertTrue(tree2.add("y"));
		assertTrue(tree2.add("n"));
		assertTrue(tree2.add("m"));
		assertTrue(tree2.add("b"));
		assertTrue(tree2.add("v"));
		assertTrue(tree2.add("o"));
		assertTrue(tree2.add("w"));
		assertTrue(tree2.add("p"));
		assertTrue(tree2.add("q"));
		assertTrue(tree2.add("f"));
		assertTrue(tree2.add("a"));
		assertTrue(tree2.containsAll(arr2));
		arr2.add(null);
		try {
			tree2.containsAll(arr2);
			fail("There is item which is null");
		} catch (NullPointerException e) {

		}
		// Test if there is only one item in the specified set
		ArrayList<String> arr3 = new ArrayList<String>();
		arr3.add("y");
		assertTrue(tree2.containsAll(arr3));
		arr3.add("c");
		assertFalse(tree2.containsAll(arr3));
	}

	@Test
	public void testFirst() {
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		try {
			tree1.first();
			fail("the set is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, tree1.size());
		}
		assertTrue(tree1.add("g"));
		assertTrue(tree1.add("d"));
		assertTrue(tree1.add("y"));
		assertTrue(tree1.add("n"));
		assertTrue(tree1.add("m"));
		assertTrue(tree1.add("b"));
		assertTrue(tree1.add("v"));
		assertTrue(tree1.add("o"));
		assertTrue(tree1.add("w"));
		assertTrue(tree1.add("p"));
		assertTrue(tree1.add("q"));
		assertTrue(tree1.add("f"));
		assertEquals("b", tree1.first());
		assertEquals(12, tree1.size());
		assertTrue(tree1.add("aaa"));
		assertEquals("aaa", tree1.first());
		assertEquals(13, tree1.size());
		assertTrue(tree1.add("z"));
		assertEquals(14, tree1.size());
		assertEquals("aaa", tree1.first());
	}

	@Test
	public void testIsEmpty() {
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		assertTrue(tree1.isEmpty());
		tree1.add("a");
		assertFalse(tree1.isEmpty());
		tree1.clear();
		assertTrue(tree1.isEmpty());
	}

	@Test
	public void testLast() {
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		try {
			tree1.last();
			fail("the set is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, tree1.size());
		}
		assertTrue(tree1.add("g"));
		assertTrue(tree1.add("d"));
		assertTrue(tree1.add("y"));
		assertTrue(tree1.add("n"));
		assertTrue(tree1.add("m"));
		assertTrue(tree1.add("b"));
		assertTrue(tree1.add("v"));
		assertTrue(tree1.add("o"));
		assertTrue(tree1.add("w"));
		assertTrue(tree1.add("p"));
		assertTrue(tree1.add("q"));
		assertTrue(tree1.add("f"));
		assertEquals("y", tree1.last());
		assertEquals(12, tree1.size());
		assertTrue(tree1.add("aaa"));
		assertEquals("y", tree1.last());
		assertEquals(13, tree1.size());
		assertTrue(tree1.add("z"));
		assertEquals(14, tree1.size());
		assertEquals("z", tree1.last());
	}

	@Test
	public void testRemove() {
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		//Test exception throw
		try {
			tree1.remove(null);
			fail("the item is null");
		} catch (NullPointerException e) {
			assertEquals(0, tree1.size());
		}
		assertFalse(tree1.remove("a"));
		assertEquals(0,tree1.size());
		assertTrue(tree1.add("a"));
		assertEquals(1,tree1.size());
		tree1.remove("a");
		assertEquals(0, tree1.size());
		//Test more elements in the list
		assertTrue(tree1.add("g"));
		assertTrue(tree1.add("d"));
		assertTrue(tree1.add("y"));
		assertTrue(tree1.add("n"));
		assertTrue(tree1.add("m"));
		assertTrue(tree1.add("b"));
		assertTrue(tree1.add("v"));
		assertTrue(tree1.add("o"));
		assertTrue(tree1.add("w"));
		assertTrue(tree1.add("p"));
		assertTrue(tree1.add("q"));
		assertTrue(tree1.add("f"));
		assertTrue(tree1.add("a"));
		ArrayList<String> arr1 = new ArrayList<String>();
		assertTrue(arr1.add("a"));
		assertTrue(arr1.add("b"));
		assertTrue(arr1.add("d"));
		assertTrue(arr1.add("f"));
		assertTrue(arr1.add("g"));
		assertTrue(arr1.add("m"));
		assertTrue(arr1.add("n"));
		assertTrue(arr1.add("o"));
		assertTrue(arr1.add("p"));
		assertTrue(arr1.add("q"));
		assertTrue(arr1.add("v"));
		assertTrue(arr1.add("w"));
		assertTrue(arr1.add("y"));
		ArrayList<String> to1 = tree1.toArrayList();
		assertEquals(arr1, to1);
		//Remove the node that has two children
		tree1.remove("g");
		assertEquals(12, tree1.size());
		assertEquals("m", tree1.getRoot().data);
		assertEquals("d", tree1.getRoot().left.data);
		assertEquals("y", tree1.getRoot().right.data);

	}

	@Test
	public void testRemoveAll() {
		
	}

	@Test
	public void testSize() {
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
		assertEquals(0,tree1.size());
		for (int j = 1; j < 101; j++) {
			tree1.add(j);
		}
		assertEquals(100,tree1.size());
		tree1.remove(50);
		assertEquals(99,tree1.size());
		
	}

	@Test
	public void testToArrayList() {
		
	}
}
