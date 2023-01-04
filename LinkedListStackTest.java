package assignment07;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * Test case
 * @author Michael Sullivan, Haoze Zhang
 */
public class LinkedListStackTest {

	//Test for create a linkedListStack
	@Test
	public void testLinkedstackStack() {
		LinkedListStack<Integer> stack1 = new LinkedListStack<Integer>();
		assertTrue(stack1.isEmpty());
		// stack should be empty
		assertEquals(0, stack1.size());
		LinkedListStack<String> stack2 = new LinkedListStack<String>();
		assertTrue(stack2.isEmpty());
		// stack should be empty
		assertEquals(0, stack2.size());
	}

	//Test clear method, if the clear method was called, will clear all the elements
	@Test
	public void testClear() {
		//Test smaller group of numbers
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		//Add a elements
		stack.push(1);
		//Test the size of stack
		assertEquals(1, stack.size());
		assertEquals(1, (int)stack.peek());
		//Call clear method
		stack.clear();
		assertEquals(0, stack.size());
		//Test larger group of number
		LinkedListStack<Integer> stack2 = new LinkedListStack<Integer>();
		for(int i = 0; i < 100; i ++){
			stack2.push(i);
		}
		assertEquals(100, stack2.size());
		assertEquals(99, (int)stack2.peek());
		stack2.clear();
		assertEquals(0, stack2.size());
		//Test if the stack2 is empty
		try {
			stack2.peek();
			fail("the stack is empty");
		} catch (NoSuchElementException e) {
			assertEquals(0, stack2.size());
		}
	}

	//Test the isEmpty method, 
	@Test
	public void testIsEmpty() {
		//Test if the stack is empty when create a new stack
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
		//Test if there are elements in the stack, will the method false
		LinkedListStack<Integer> stack2 = new LinkedListStack<Integer>();
		for(int i = 0; i < 100; i ++){
			stack2.push(i);
		}
		assertFalse(stack2.isEmpty());
	}

	//Test peek method
	@Test
	public void testPeek() {
		//Test if the method return the first element in the stack without removing element
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		//Test if the stack is empty
		try {
			stack.peek();
			fail("There is no elements in the stack");
		} catch (NoSuchElementException e) {
			assertEquals(0, stack.size());
		}
		stack.push(1);
		stack.push(2);
		Object element = stack.peek();
		assertEquals(2, element);
		stack.push(10000);
		element = stack.peek();
		assertEquals(10000, element);
		assertEquals(3, stack.size());
	}

	//Test pop() method
	@Test
	public void testPop() {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		//Test if the method is empty
		try {
			stack.pop();
			fail("There is no elements in the stack");
		} catch (NoSuchElementException e) {
			assertEquals(0, stack.size());
		}
		//Test there is only one element in the stack
		stack.push(1);
		assertEquals(1,(int)stack.pop());
		assertEquals(0, stack.size());
		//Test there are more elements in the stack
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertEquals(3, stack.size());
		//Test if return the first element in the stack
		assertEquals(3, (int)stack.peek());
		stack.pop();
		assertEquals(2, stack.size());
		assertEquals(2, (int)stack.peek());
	}

	//Test push() method
	@Test
	public void testPush() {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		//Test larger group of numbers
		for(int i = 0; i < 100 ; i ++){
			stack.push(i *2 );
		}
		assertEquals(100, stack.size());
		assertEquals(198,(int)stack.peek());
		assertEquals(198,(int)stack.pop());
		assertEquals(99, stack.size());
		assertEquals(196, (int) stack.peek());
		//Test there is only one element in the stack
		LinkedListStack<Integer> stack2 = new LinkedListStack<Integer>();
		stack2.push(100);
		assertEquals(1, stack2.size());
		assertEquals(100,(int)stack2.peek());
		assertEquals(100,(int)stack2.pop());
		assertEquals(0, stack2.size());
	}

	//Test the size of the stack
	@Test
	public void testSize() {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		assertEquals(0, stack.size());
		stack.push(1);
		assertEquals(1, stack.size());
	}

}
