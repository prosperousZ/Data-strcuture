package assignment06;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;
/**
 * 
 * @author Haoze Zhang
 * u0904009
 * Test case of whole class
 */
	//Test the constructor
	public class DoublyLinkedListTest {
	@Test
	public void TestNewDoublyLinkedList() {
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<Integer>();
		assertTrue(list1.isEmpty());
		// linked list should be empty
		assertEquals(0, list1.size());
		DoublyLinkedList<String> list2 = new DoublyLinkedList<String>();
		assertTrue(list2.isEmpty());
		// linked list should be empty
		assertEquals(0, list2.size());
	}
	//Test addFirst method
	@Test
	public void testAddFirst() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		// Test if the list is empty, add the first elements, and the size should be 1
		// The head should be tail, the tail should be head
		list.addFirst("a");
		assertEquals(1, list.size());
		assertEquals("a", list.head.elementInNode);
		assertEquals("a", list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals(null, list.head.next);
		assertEquals(null, list.tail.prev);
		Object[] arr1 = { "a" };
		Object[] to1 = list.toArray();
		// Change list to array, and test when the list change to array, if this list is correct
		Assert.assertArrayEquals(arr1, to1);
		assertEquals(0, list.indexOf("a"));
		// Add the elements when the list is not empty
		list.addFirst("b");
		assertEquals(2, list.size());
		assertEquals("b", list.head.elementInNode);
		assertEquals("a", list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals("a", list.head.next.elementInNode);
		assertEquals("b", list.tail.prev.elementInNode);
		//Test when the list to array, will they have same elements
		Object[] arr2 = { "b", "a" };
		Object[] to2 = list.toArray();
		Assert.assertArrayEquals(arr2, to2);
		assertEquals(0, list.indexOf("b"));
		assertEquals(1, list.indexOf("a"));
		//Add the third elements
		list.addFirst("c");
		assertEquals(3, list.size());
		assertEquals("c", list.head.elementInNode);
		assertEquals("a", list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals("b", list.head.next.elementInNode);
		assertEquals("b", list.tail.prev.elementInNode);
		Object[] arr3 = { "c", "b", "a" };
		Object[] to3 = list.toArray();
		Assert.assertArrayEquals(to3, arr3);
		assertEquals(0, list.indexOf("c"));
		assertEquals(1, list.indexOf("b"));
		assertEquals(2, list.indexOf("a"));

		// Test when the input type is integer
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		list2.addFirst(1);
		assertEquals(1, list2.size());
		assertEquals(1, (int) list2.head.elementInNode);
		assertEquals(1, (int) list2.tail.elementInNode);
		assertEquals(null, list2.head.prev);
		assertEquals(null, list2.tail.next);
		assertEquals(null, list2.head.next);
		assertEquals(null, list2.tail.prev);
		Object[] arr4 = { 1 };
		Object[] to4 = list2.toArray();
		// list to array, and test when the list change to array, if this list is correct
		Assert.assertArrayEquals(arr4, to4);
		assertEquals(0, list2.indexOf(1));

		// Test larger group of elements
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list3.addFirst(i);
		}
		assertEquals(100, list3.size());
		assertEquals(99, (int) list3.head.elementInNode);
		assertEquals(0, (int) list3.tail.elementInNode);
		assertEquals(null, list3.head.prev);
		assertEquals(null, list3.tail.next);
		assertEquals(98, (int) list3.head.next.elementInNode);
		assertEquals(1, (int) list3.tail.prev.elementInNode);
		Object[] arr5 = new Object[100];
		for (int i = 0; i < 100; i++) {
			arr5[i] = 99 - i;
		}
		Object[] to5 = list3.toArray();
		// list to array, and test when the list change to array, if this list is correct
		Assert.assertArrayEquals(arr5, to5);
		assertEquals(49, list3.indexOf(50));
	}

	//Test addLast method
	@Test
	public void testAddLast() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		// Test if the list is empty, add the first elements, and the size should be 1
		// The head should be tail, the tail should be head
		list.addLast("a");
		assertEquals(1, list.size());
		assertEquals("a", list.head.elementInNode);
		assertEquals("a", list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals(null, list.head.next);
		assertEquals(null, list.tail.prev);
		Object[] arr1 = { "a" };
		Object[] to1 = list.toArray();
		// list to array, and test when the list change to array, if this list is correct
		Assert.assertArrayEquals(arr1, to1);
		assertEquals(0, list.indexOf("a"));

		// Add the elements when the list is not empty
		list.addLast("b");
		assertEquals(2, list.size());
		assertEquals("a", list.head.elementInNode);
		assertEquals("b", list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals("b", list.head.next.elementInNode);
		assertEquals("a", list.tail.prev.elementInNode);
		Object[] arr2 = { "a", "b" };
		Object[] to2 = list.toArray();
		Assert.assertArrayEquals(arr2, to2);
		assertEquals(0, list.indexOf("a"));
		assertEquals(1, list.indexOf("b"));
		
		// When the size is greater than 2
		list.addLast("c");
		assertEquals(3, list.size());
		assertEquals("a", list.head.elementInNode);
		assertEquals("c", list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals("b", list.head.next.elementInNode);
		assertEquals("b", list.tail.prev.elementInNode);
		Object[] arr3 = { "a", "b", "c" };
		Object[] to3 = list.toArray();
		Assert.assertArrayEquals(to3, arr3);
		assertEquals(0, list.indexOf("a"));
		assertEquals(1, list.indexOf("b"));
		assertEquals(2, list.indexOf("c"));

		// Test when the input type is integer
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		list2.addFirst(1);
		assertEquals(1, list2.size());
		assertEquals(1, (int) list2.head.elementInNode);
		assertEquals(1, (int) list2.tail.elementInNode);
		assertEquals(null, list2.head.prev);
		assertEquals(null, list2.tail.next);
		assertEquals(null, list2.head.next);
		assertEquals(null, list2.tail.prev);
		Object[] arr4 = { 1 };
		Object[] to4 = list2.toArray();
		// list to array, and test when the list change to array, if this list is correct
		Assert.assertArrayEquals(arr4, to4);
		assertEquals(0, list2.indexOf(1));

		// Test larger group of elements
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list3.addLast(i);
		}
		assertEquals(100, list3.size());
		assertEquals(0, (int) list3.head.elementInNode);
		assertEquals(99, (int) list3.tail.elementInNode);
		assertEquals(null, list3.head.prev);
		assertEquals(null, list3.tail.next);
		assertEquals(1, (int) list3.head.next.elementInNode);
		assertEquals(98, (int) list3.tail.prev.elementInNode);
		Object[] arr5 = new Object[100];
		for (int i = 0; i < 100; i++) {
			arr5[i] = i;
		}
		Object[] to5 = list3.toArray();
		// list to array, and test when the list change to array, if this list is correct
		Assert.assertArrayEquals(arr5, to5);
		assertEquals(50, list3.indexOf(50));

	}

	//Test add method
	@Test
	public void testAdd() {
		//Create an empty list
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		//Test if meet following condition, will it thorw exception
		//When the index is negative number
		try {
			list.add(-1, 1);
			fail("The index is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//Test when index is over bounds of list
		try {
			list.add(1, 1);
			fail("The index is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		// Test when the size is 0
		list.add(0, 1);
		assertEquals(1, list.size());
		assertEquals(1, (int) list.head.elementInNode);
		assertEquals(1, (int) list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		Object[] arr1 = { 1 };
		Object[] to1 = list.toArray();
		Assert.assertArrayEquals(arr1, to1);
		assertEquals(0, list.indexOf(1));
		
		// Test when the size is 1, use the add method add the first element
		list.add(0, 2);
		assertEquals(2, list.size());
		assertEquals(2, (int) list.head.elementInNode);
		assertEquals(1, (int) list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals(1, (int) list.head.next.elementInNode);
		assertEquals(2, (int) list.tail.prev.elementInNode);
		assertEquals(2, (int) list.head.next.prev.elementInNode);
		assertEquals(1, (int) list.tail.prev.next.elementInNode);
		Object[] arr2 = { 2, 1 };
		Object[] to2 = list.toArray();
		Assert.assertArrayEquals(arr2, to2);
		
		// Test if the index is equals to size
		list.add(2, 3);
		assertEquals(3, list.size());
		assertEquals(2, (int) list.head.elementInNode);
		assertEquals(3, (int) list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		assertEquals(1, (int) list.head.next.elementInNode);
		assertEquals(1, (int) list.tail.prev.elementInNode);
		assertEquals(2, (int) list.head.next.prev.elementInNode);
		assertEquals(3, (int) list.tail.prev.next.elementInNode);
		Object[] arr5 = { 2, 1, 3 };
		Object[] to5 = list.toArray();
		Assert.assertArrayEquals(arr5, to5);
		
		// Test when the size is 1, use the add method add the last element
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<Integer>();
		list3.add(0, 1);
		list3.add(1, 2);
		assertEquals(2, list3.size());
		assertEquals(1, (int) list3.head.elementInNode);
		assertEquals(2, (int) list3.tail.elementInNode);
		assertEquals(null, list3.head.prev);
		assertEquals(null, list3.tail.next);
		assertEquals(2, (int) list3.head.next.elementInNode);
		assertEquals(1, (int) list3.tail.prev.elementInNode);
		assertEquals(1, (int) list3.head.next.prev.elementInNode);
		assertEquals(2, (int) list3.tail.prev.next.elementInNode);
		Object[] arr3 = { 1, 2 };
		Object[] to3 = list3.toArray();
		Assert.assertArrayEquals(arr3, to3);

		// Test larger group of numbers
		DoublyLinkedList<Integer> list4 = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list4.add(i, i);
		}
		assertEquals(100, list4.size());
		list4.add(40, 10000);
		assertEquals(101, list4.size());
		assertEquals(0, (int) list4.head.elementInNode);
		assertEquals(99, (int) list4.tail.elementInNode);
		assertEquals(null, list4.head.prev);
		assertEquals(null, list4.tail.next);
		assertEquals(1, (int) list4.head.next.elementInNode);
		assertEquals(98, (int) list4.tail.prev.elementInNode);
		assertEquals(40, list4.indexOf(10000));
		Object[] arr4 = new Object[101];
		for (int i = 0; i < 40; i++) {
			arr4[i] = i;
		}
		arr4[40] = 10000;
		for (int j = 41; j < 101; j++) {
			arr4[j] = j - 1;
		}
		Object[] to4 = list4.toArray();
		// list to array, and test when the list change to array, if this list is correct
		Assert.assertArrayEquals(arr4, to4);
		assertEquals(51, list4.indexOf(50));
	}

	//Test getFirst method
	@Test
	public void testGetFirst() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		
		//Test if the list is empty, will the program throw exception
		try {
			list.getFirst();
			fail("There is no elements in the list which means the list is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, list.size());
		}
		
		//Test when there are two elements in the list, which means the size is 2
		list.add(0, 1);
		list.add(1, 2);
		Object element = list.getFirst();
		assertEquals(1, element);
		
		//Test if user add new elements, will the list still get the first element of the list
		list.add(0, 10000);
		element = list.getFirst();
		assertEquals(10000, element);
		assertEquals(3, list.size());
		
		//Test where is head and tail, are they at the correct position
		assertEquals(10000, (int) list.head.elementInNode);
		assertEquals(2, (int) list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		Object[] arr1 = { 10000, 1, 2 };
		Object[] to1 = list.toArray();
		Assert.assertArrayEquals(arr1, to1);
		assertEquals(0, list.indexOf(10000));
	}

	//Test getLast method
	@Test
	public void testGetLast() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		
		//Test if the list is empty, will the program throw exception
		try {
			list.getLast();
			fail("There is no elements in the list which means the list is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, list.size());
		}
		
		//Test when there are two elements in the list, which means the size is 2
		list.add(0, 1);
		list.add(1, 2);
		Object element = list.getLast();
		assertEquals(2, element);
		list.add(2, 10000);
		element = list.getLast();
		
		//Test if user add new elements, will the list still get the last element of the list
		assertEquals(10000, element);
		assertEquals(3, list.size());
		
		//Test the position of the head and tail
		assertEquals(1, (int) list.head.elementInNode);
		assertEquals(10000, (int) list.tail.elementInNode);
		assertEquals(null, list.head.prev);
		assertEquals(null, list.tail.next);
		Object[] arr1 = { 1, 2, 10000 };
		Object[] to1 = list.toArray();
		Assert.assertArrayEquals(arr1, to1);
		assertEquals(2, list.indexOf(10000));
	}

	//Test get method
	@Test
	public void testGet() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		//Test exception throw
		try {
			list.get(1);
			fail("There is no elements in the list which means the list is empty");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//Test larger group of elements
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list3.add(i, i);
		}
		assertEquals(100, list3.size());
		assertEquals(0, (int) list3.head.elementInNode);
		assertEquals(99, (int) list3.tail.elementInNode);
		assertEquals(null, list3.head.prev);
		assertEquals(null, list3.tail.next);
		assertEquals(1, (int) list3.head.next.elementInNode);
		assertEquals(98, (int) list3.tail.prev.elementInNode);
		//Test if user call get method, will get the correct position of element
		for (int i = 0; i < 100; i++) {
			assertEquals(i, (int) list3.get(i));
			assertEquals(0, (int) list3.get(0));
			assertEquals(99, (int) list3.get(99));
		}
		//Test if there is new elements added to the list, will the get method still get the correct element
		list3.add(30, 10000);
		assertEquals(10000, (int) list3.get(30));
	}

	//Test removeFirst method
	@Test
	public void testRemoveFirst() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		//Test exception throw
		try {
			list.removeFirst();
			fail("There is no elements in the list which means the list is empty");
		} catch (NoSuchElementException e) {
			//The size of list should still be 0 
			assertEquals(0, list.size());
		}
		//Test when there is only one elements, will the method still work correctly
		list.add(0, 1);
		list.removeFirst();
		assertEquals(null, list.head.elementInNode);
		assertEquals(null, list.tail.elementInNode);
		assertEquals(0, list.size());
		//If there are more elements in the list
		list.add(0, 1);
		list.add(1, 2);
		list.add(2, 3);
		Object[] arr2 = { 1, 2, 3 };
		Object[] to2 = list.toArray();
		Assert.assertArrayEquals(arr2, to2);
		//Test if user call removeFirst method, will the user return correct element
		assertEquals(1, (int)list.removeFirst());
		assertEquals(2, (int) list.head.elementInNode);
		assertEquals(3, (int) list.tail.elementInNode);
		assertEquals(0, (int) list.indexOf(2));
		assertEquals(1, (int) list.indexOf(3));
		//Test the size after the method was called
		assertEquals(2, list.size());
		Object[] arr = { 2, 3 };
		Object[] to = list.toArray();
		Assert.assertArrayEquals(arr, to);
	}

	//Test removeLast method
	@Test
	public void testRemoveLast() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		//Test exception throw
		try {
			list.removeLast();
			fail("There is no elements in the list which means the list is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, list.size());
		}
		//Test the condition when there is only one element the list
		list.add(0, 1);
		list.removeLast();
		//Test the element of head and tail
		assertEquals(null, list.head.elementInNode);
		assertEquals(null, list.tail.elementInNode);
		assertEquals(0, list.size());
		//Test if there are more elements in the list
		list.add(0, 1);
		list.add(1, 3);
		list.add(1, 2);
		Object[] arr2 = { 1, 2, 3 };
		Object[] to2 = list.toArray();
		Assert.assertArrayEquals(arr2, to2);
		//Test if the removeLast return the correct element in the list
		assertEquals((int)list.removeLast(), 3);
		assertEquals(1, (int) list.head.elementInNode);
		assertEquals(2, (int) list.tail.elementInNode);
		assertEquals(1, (int) list.indexOf(2));
		assertEquals(0, (int) list.indexOf(1));
		assertEquals(2, list.size());
		Object[] arr = { 1, 2 };
		Object[] to = list.toArray();
		Assert.assertArrayEquals(arr, to);
	}

	//Test remove method
	@Test
	public void testRemove() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		//Test exception throw
		try {
			list.remove(1);
			fail("The index is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//Test if there is only one elements, will the method still throw correct exception
		list.add(0, 1);
		try {
			list.remove(1);
			fail("The index is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		try {
			list.remove(-1);
			fail("The index is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		//When there is only element in the list, user call remove method.
		list.remove(0);
		assertEquals(null, list.head.elementInNode);
		assertEquals(null, list.tail.elementInNode);
		assertEquals(0, list.size());
		//Test more elements in the list
		list.add(0, 1);
		list.add(1, 2);
		list.add(2, 3);
		Object[] arr2 = { 1, 2, 3 };
		Object[] to2 = list.toArray();
		assertEquals(3, list.size());
		Assert.assertArrayEquals(arr2, to2);
		list.remove(1);
		assertEquals(1, (int) list.head.elementInNode);
		assertEquals(3, (int) list.tail.elementInNode);
		assertEquals(0, (int) list.indexOf(1));
		assertEquals(1, (int) list.indexOf(3));
		assertEquals(2, list.size());
		Object[] arr = { 1, 3 };
		Object[] to = list.toArray();
		Assert.assertArrayEquals(arr, to);
		// Test larger group of numbers
		DoublyLinkedList<Integer> list4 = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list4.add(i, i);
		}
		assertEquals(100, list4.size());
		list4.remove(40);
		assertEquals(99, list4.size());
		assertEquals(0, (int) list4.head.elementInNode);
		assertEquals(99, (int) list4.tail.elementInNode);
		assertEquals(null, list4.head.prev);
		assertEquals(null, list4.tail.next);
		assertEquals(1, (int) list4.head.next.elementInNode);
		assertEquals(98, (int) list4.tail.prev.elementInNode);
		assertEquals(39, list4.indexOf(39));
		assertEquals(40, list4.indexOf(41));
		Object[] arr4 = new Object[99];
		for (int i = 0; i < 40; i++) {
			arr4[i] = i;
		}
		arr4[40] = 41;
		for (int j = 41; j < 99; j++) {
			arr4[j] = j + 1;
		}
		Object[] to4 = list4.toArray();
		Assert.assertArrayEquals(arr4, to4);
	}

	//Test indexOf method which will return the first occurence in the list
	@Test
	public void testIndexOf() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		//Test larger group of elements
		for (int i = 0; i < 100; i++) {
			list.add(i, i * 2);
		}
		//Test will the method return correct elements
		assertEquals(0, list.indexOf(0));
		assertEquals(49, list.indexOf(98));
		assertEquals(-1, list.indexOf(10000));
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		//Test if there are duplicates in the list
		list2.add(0, 1);
		list2.add(1, 2);
		list2.add(2, 3);
		list2.add(3, 4);
		list2.add(4, 5);
		list2.add(5, 4);
		list2.add(6, 3);
		list2.add(7, 2);
		list2.add(8, 1);
		assertEquals(1, list2.indexOf(2));
		assertEquals(3, list2.indexOf(4));
		assertEquals(-1, list2.indexOf(-1));
		// Test if the size is only 1
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<Integer>();
		list3.add(0, 5);
		assertEquals(0, list3.lastIndexOf(5));
	}

	//Test lastIndexOf method
	@Test
	public void testLastIndexOf() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		//Test larger group of elements
		for (int i = 0; i < 100; i++) {
			list.add(i, i * 2);
		}
		//Test will the method get correct element
		assertEquals(0, list.lastIndexOf(0));
		assertEquals(49, list.lastIndexOf(98));
		assertEquals(-1, list.lastIndexOf(10000));
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		//Test if there are duplicates in the list
		list2.add(0, 1);
		list2.add(1, 2);
		list2.add(2, 3);
		list2.add(3, 4);
		list2.add(4, 5);
		list2.add(5, 4);
		list2.add(6, 3);
		list2.add(7, 2);
		list2.add(8, 1);
		assertEquals(7, list2.lastIndexOf(2));
		assertEquals(5, list2.lastIndexOf(4));
		assertEquals(-1, list2.lastIndexOf(-1));
		// Test if the size is only 1
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<Integer>();
		list3.add(0, 5);
		assertEquals(0, list3.lastIndexOf(5));
	}

	//Test the size of the list
	@Test
	public void testSize() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list.add(i, i * 2);
		}
		assertEquals(100, list.size());
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		assertEquals(0, list2.size());
	}

	//Test if the list is empty
	@Test
	public void testIsEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		assertTrue(list.isEmpty());
		list.add(0, 1);
		assertFalse(list.isEmpty());
		list.remove(0);
		assertTrue(list.isEmpty());
	}

	//Test when user call clear method, will the list become empty
	@Test
	public void testClear() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list.add(i, i * 2);
		}
		assertEquals(100, list.size());
		Object[] arr = new Object[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i * 2;
		}
		Object[] to = list.toArray();
		Assert.assertArrayEquals(arr, to);
		list.clear();
		assertEquals(0, list.size());
		// Test if there is anything in the array after user call clear method
		Object[] arr2 = {};
		Object[] to2 = list.toArray();
		Assert.assertArrayEquals(arr2, to2);
	}

	//Test list to array
	@Test
	public void testToArray() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list.add(i, i * 2);
		}
		assertEquals(100, list.size());
		Object[] arr = new Object[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i * 2;
		}
		Object[] to = list.toArray();
		Assert.assertArrayEquals(arr, to);
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		list2.add(0, 1);
		Object[] to2 = { 1 };
		Object[] arr2 = list2.toArray();
		Assert.assertArrayEquals(arr2, to2);
	}

	//Test iterator
	@Test
	public void testIterator() {
		// Test when the list is empty
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		Iterator<Integer> myIterator = list.iterator();
		try {
			myIterator.next();
			fail("The list is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, list.size());
		}
		assertFalse(myIterator.hasNext());

		// Test 10 elements in the list
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		// create list and fill list
		for (int i = 0; i < 10; i++) {
			list2.add(i, i);
		}
		//Test when user call hasNext method, will return the correct boolean expression
		Iterator<Integer> myIterator2 = list2.iterator();
		assertEquals(0, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(1, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(2, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(3, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(4, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(5, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(6, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(7, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(8, (int) myIterator2.next());
		assertTrue(myIterator2.hasNext());
		assertEquals(10, list2.size());
		assertEquals(9, (int) myIterator2.next());
		// When check to last element, iterator's hasNext method should return false;
		assertFalse(myIterator.hasNext());
		assertEquals(10, list2.size());

		// Test Out of bounds exception
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			list3.add(i, i);
		}
		Iterator<Integer> myIterator3 = list3.iterator();
		myIterator3.next();
		myIterator3.next();
		myIterator3.next();
		myIterator3.next();
		myIterator3.next();
		myIterator3.next();
		myIterator3.next();
		myIterator3.next();
		myIterator3.next();
		assertTrue(myIterator3.hasNext());
		myIterator3.next();
		// Test if use the next method when the iterator has already came to the last element
		try {
			myIterator3.next();
			fail("Out of bounds");
		} catch (NoSuchElementException e) {
			assertEquals(10, list3.size());
		}
		assertFalse(myIterator3.hasNext());

		// Test if there is only one element in the list
		DoublyLinkedList<Integer> list4 = new DoublyLinkedList<Integer>();
		list4.add(0, 1);
		Iterator<Integer> myIterator4 = list4.iterator();
		assertTrue(myIterator4.hasNext());
		assertEquals(1, (int) myIterator4.next());
		// Test if the list is empty, will throw exception
		DoublyLinkedList<Integer> list5 = new DoublyLinkedList<Integer>();
		Iterator<Integer> myIterator5 = list5.iterator();
		try {
			myIterator5.remove();
			fail("Have not called next() yet");
		} catch (IllegalStateException e) {
			assertEquals(0, list5.size());
		}
		// Test if there are elements in the list, but user has not called next
		// method yet
		DoublyLinkedList<Integer> list6 = new DoublyLinkedList<Integer>();
		list6.add(0, 1);
		Iterator<Integer> myIterator6 = list6.iterator();
		try {
			myIterator6.remove();
			fail("Have not called next() yet");
		} catch (IllegalStateException e) {
			assertEquals(1, list6.size());
		}
		myIterator6.next();
		myIterator6.remove();
		assertEquals(0, list6.size());
		Object[] to = {};
		Object[] arr = list6.toArray();
		Assert.assertArrayEquals(arr, to);

		// Test if the next method was called
		DoublyLinkedList<Integer> list7 = new DoublyLinkedList<Integer>();
		list7.add(0, 1);
		list7.add(1, 2);
		list7.add(2, 3);
		Iterator<Integer> myIterator7 = list7.iterator();
		myIterator7.next();
		myIterator7.next();
		myIterator7.remove();
		Object[] to2 = { 1, 3 };
		Object[] arr2 = list7.toArray();
		Assert.assertArrayEquals(arr2, to2);
	}
}
