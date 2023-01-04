package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Paul Carlson, Haoze Zhang
 *
 */
public class BinarySearchTreeTest1 {

	public static int[] balancedIntArray = new int[] { 12, 7, 20, 4, 10, 17, 25, 2, 6, 9, 11, 15, 19, 23, 28, 1, 3, 5,
			8, 14, 16, 18, 22, 24, 27, 30, 0, 13, 21, 26, 29 };
	public static int[] leftIntArray = new int[] { 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2,
			1 };
	public static int[] rightIntArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
			20 };

	public static ArrayList<String> animalList;

	public static BinarySearchTree<Integer> balanced;
	public static BinarySearchTree<Integer> left;
	public static BinarySearchTree<Integer> right;
	public static BinarySearchTree<String> animals;

	@Before
	public void makeTrees() {
		animalList = new ArrayList<String>();
		animalList.add("monkey");
		animalList.add("horse");
		animalList.add("reindeer");
		animalList.add("iguana");
		animalList.add("bird");
		animalList.add("cat");
		animalList.add("aardvark");
		animalList.add("ape");
		animalList.add("llama");
		animalList.add("owl");
		animalList.add("snake");
		animalList.add("hampster");
		animalList.add("zebra");
		animalList.add("walrus");

		balanced = new BinarySearchTree<Integer>();
		for (int i : balancedIntArray) {
			balanced.add(i);
		}

		right = new BinarySearchTree<Integer>();
		for (int i : rightIntArray) {
			right.add(i);
		}

		left = new BinarySearchTree<Integer>();
		for (int i : leftIntArray) {
			left.add(i);
		}

		animals = new BinarySearchTree<String>();
		animals.addAll(animalList);
	}

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
	public void testMakingATree() {
		BinarySearchTree<Integer> balanced = new BinarySearchTree<Integer>();
		for (int i : balancedIntArray) {
			balanced.add(i);
		}
	}

	/**
	 * Create some BinarySearchTrees and make dot files from them Not the same
	 * kind of test, it will always pass, but lets us visually check the output
	 * of writeDot()
	 */
	@Test
	public void testWriteDotForIntegers() {
		balanced.writeDot("BalancedTree.dot");

		right.writeDot("RightHeavyTree.dot");

		left.writeDot("LeftHeavyTree.dot");
	}

	@Test
	public void testWriteDotForStrings() {
		// Make the trees
		animals.writeDot("AnimalTree.dot");
	}

	@Test
	public void testAddIntegerToEmptyTree() {
		BinarySearchTree<Integer> newTree = new BinarySearchTree<Integer>();
		assertEquals(null, newTree.getRoot());
		assertTrue(newTree.add(100));
		assertEquals(100, (int) newTree.getRoot().data);
	}

	@Test
	public void testAddNewIntegerToBalancedTree() {
		assertTrue(balanced.add(100));
		assertEquals(100, (int) balanced.last());
		assertEquals(32, balanced.size());

		assertTrue(balanced.add(-2));
		assertEquals(-2, (int) balanced.first());
		assertEquals(33, balanced.size());

		assertTrue(balanced.add(39));
		assertEquals(100, (int) balanced.last());
		assertEquals(-2, (int) balanced.first());
		assertEquals(34, balanced.size());
	}

	@Test
	public void testAddExistingAnimalToAnimalTree() {
		assertFalse(animals.add("owl"));
		assertEquals(14, animals.size());
	}

	@Test(expected = NullPointerException.class)
	public void testAddException() {
		balanced.add(null);
	}

	@Test
	public void testAddAllFromBalancedIntegerArrayList() {
		ArrayList<Integer> balancedIntList = new ArrayList<Integer>();
		for (int i : balancedIntArray) {
			balancedIntList.add(i);
		}

		BinarySearchTree<Integer> balanced = new BinarySearchTree<Integer>();
		balanced.addAll(balancedIntList);
		assertEquals(12, (int) balanced.getRoot().data);
		assertEquals(0, (int) balanced.first());
		assertEquals(30, (int) balanced.last());
	}

	@Test
	public void testAddAllWhenAddingToExistingList() {
		ArrayList<Integer> localBalanced = new ArrayList<Integer>();
		for (int i : balancedIntArray) {
			localBalanced.add(i);
		}

		ArrayList<Integer> localLeft = new ArrayList<Integer>();
		for (int i : leftIntArray) {
			localLeft.add(i);
		}
		assertEquals(20, (int) right.last());

		assertTrue(right.addAll(localBalanced));
		assertEquals(30, (int) right.last());
		assertEquals(31, right.size());

		assertFalse(right.addAll(localLeft));
	}

	@Test
	public void testAddAllRepeatedElements() {
		ArrayList<Integer> local = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			local.add(10);
		}
		assertFalse(balanced.addAll(local));
		assertEquals(31, balanced.size());
	}

	@Test
	public void testAddAllFromAnimalArrayList() {
		BinarySearchTree<String> animals = new BinarySearchTree<String>();
		animals.addAll(animalList);

		assertEquals("monkey", animals.getRoot().data);
		assertEquals("aardvark", animals.first());
		assertEquals("zebra", animals.last());
	}

	@Test(expected = NullPointerException.class)
	public void testAddAllException() {
		ArrayList<Integer> nullList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			nullList.add(null);
		}

		balanced.addAll(nullList);
	}

	@Test
	public void testContainsForFilledList() {
		assertTrue(balanced.contains(20));
		assertTrue(balanced.contains(0));

		assertFalse(balanced.contains(-30));

		balanced.add(-30);
		assertTrue(balanced.contains(-30));
	}

	@Test
	public void testContainsForEmptyList() {
		BinarySearchTree<Integer> newTree = new BinarySearchTree<Integer>();
		assertFalse(newTree.contains(20));
		assertFalse(newTree.contains(0));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsException() {
		balanced.contains(null);
	}

	@Test
	public void testContainsAll() {
		ArrayList<Integer> localRight = new ArrayList<Integer>();
		for (int i : rightIntArray) {
			localRight.add(i);
		}
		ArrayList<Integer> localBalanced = new ArrayList<Integer>();
		for (int i : balancedIntArray) {
			localBalanced.add(i);
		}
		assertTrue(left.containsAll(localRight));
		assertFalse(left.containsAll(localBalanced));
	}

	@Test
	public void testContainsAllRepeatedElements() {
		ArrayList<Integer> local = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			local.add(10);
		}
		assertTrue(left.containsAll(local));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsAllException() {
		ArrayList<Integer> nullList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			nullList.add(null);
		}
		balanced.containsAll(nullList);
	}

	@Test
	public void testClear() {
		balanced.clear();
		assertEquals(0, balanced.size());
		assertEquals(null, balanced.getRoot());
		balanced.clear();

		animals.clear();
		assertEquals(0, animals.size());
		assertEquals(null, animals.getRoot());
	}

	@Test
	public void testFirst() {
		assertEquals("aardvark", animals.first());
		assertEquals(1, (int) left.first());
		assertEquals(1, (int) right.first());
		assertEquals(0, (int) balanced.first());

		animals.remove("aardvark");
		assertEquals("ape", animals.first());
	}

	@Test(expected = NoSuchElementException.class)
	public void testFirstException() {
		BinarySearchTree<Integer> nullTree = new BinarySearchTree<Integer>();
		nullTree.first();
	}

	@Test
	public void testIsEmpty() {
		assertFalse(balanced.isEmpty());
		assertFalse(animals.isEmpty());

		BinarySearchTree<Integer> nullTree = new BinarySearchTree<Integer>();
		assertTrue(nullTree.isEmpty());

		animals.clear();
		assertTrue(animals.isEmpty());
	}

	@Test
	public void testLast() {
		assertEquals("zebra", animals.last());
		assertEquals(20, (int) left.last());
		assertEquals(20, (int) right.last());
		assertEquals(30, (int) balanced.last());

		animals.remove("zebra");
		assertEquals("walrus", animals.last());

		left.remove(20);
		assertEquals(19, (int) left.last());

		balanced.remove(12);
		assertEquals(30, (int) balanced.last());
	}

	@Test(expected = NoSuchElementException.class)
	public void testLastException() {
		BinarySearchTree<Integer> nullTree = new BinarySearchTree<Integer>();
		nullTree.last();
	}

	@Test
	public void testRemove() {
		left.remove(20);
		assertEquals(19, (int) left.getRoot().data);
		assertFalse(left.contains(20));
		assertEquals(19, left.size());

		balanced.remove(12);
		assertEquals(13, (int) balanced.getRoot().data);
		assertFalse(balanced.contains(12));
		assertEquals(30, balanced.size());

		assertTrue(animals.remove("zebra"));
		assertEquals("walrus", animals.last());
		assertFalse(animals.contains("zebra"));
		assertEquals(13, animals.size());
	}

	@Test
	public void testRemoveSameElement() {
		assertTrue(animals.remove("monkey"));
		assertEquals("owl", animals.getRoot().data);
		assertFalse(animals.contains("monkey"));
		assertEquals(13, animals.size());

		assertFalse(animals.remove("monkey"));
		assertEquals(13, animals.size());
	}

	@Test
	public void testOnlyOneElementToRemove() {
		BinarySearchTree<String> tempTree = new BinarySearchTree<String>();
		tempTree.add("hello");

		assertTrue(tempTree.remove("hello"));
		assertEquals(null, tempTree.getRoot());
		assertEquals(0, tempTree.size());
		assertFalse(tempTree.contains("hello"));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveException() {
		right.remove(null);
	}

	@Test
	public void testRemoveAll() {
		ArrayList<Integer> localRight = new ArrayList<Integer>();
		for (int i : rightIntArray) {
			localRight.add(i);
		}

		assertTrue(left.removeAll(localRight));
		assertEquals(0, left.size());
		assertEquals(null, left.getRoot());
		assertTrue(left.isEmpty());
		for (int i = 1; i < 21; i++) {
			assertFalse(left.contains(i));
		}

		assertTrue(balanced.removeAll(localRight));
		balanced.writeDot("messy.dot");
		assertEquals(11, balanced.size());
		assertFalse(balanced.isEmpty());

		for (int j : localRight) {
			assertFalse(balanced.contains(j));
		}
		for (int k = 21; k <= 30; k++) {

			assertTrue(balanced.contains(k));
		}

	}

	@Test
	public void testSize() {
		assertEquals(31, balanced.size());
		balanced.add(50);
		assertEquals(32, balanced.size());

		assertEquals(20, left.size());
		left.remove(12);
		assertEquals(19, left.size());

		assertEquals(14, animals.size());
		animals.clear();
		assertEquals(0, animals.size());
	}

	@Test
	public void testToArrayList() {
		ArrayList<Integer> balancedResult = balanced.toArrayList();
		for (int i = 0; i < balancedResult.size(); i++) {
			assertEquals(i, (int) balancedResult.get(i));
		}

		ArrayList<Integer> leftResult = left.toArrayList();
		for (int i = 0; i < leftResult.size(); i++) {
			assertEquals(i + 1, (int) leftResult.get(i));
		}

		ArrayList<Integer> rightResult = right.toArrayList();
		for (int i = 0; i < rightResult.size(); i++) {
			assertEquals(i + 1, (int) rightResult.get(i));
		}
	}

}
