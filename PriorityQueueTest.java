package assignment11;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Haoze Zhang & Nicholas Fuller
 *
 */
public class PriorityQueueTest
{

	private PriorityQueue<Integer> intQueue;

	@Before
	public void setUp() throws Exception
	{
		intQueue = new PriorityQueue<Integer>();
		intQueue.add(5);
		intQueue.add(2);
		intQueue.add(7);
		intQueue.add(10);
		intQueue.add(8);
		intQueue.add(6);
		intQueue.add(3);
		intQueue.add(1);
	}

	@Test
	public void testPriorityQueue()
	{
		 intQueue.deleteMin();
		 intQueue.deleteMin();
		 intQueue.add(100);
		 intQueue.add(0);
		 intQueue.deleteMin();
		 intQueue.deleteMin();
		 intQueue.deleteMin();
		 intQueue.deleteMin();
		 intQueue.deleteMin();
		 intQueue.deleteMin();
		 intQueue.deleteMin();
	}

	@Test
	public void testSize()
	{
		assertEquals(8, intQueue.size());
		intQueue.deleteMin();
		assertEquals(7, intQueue.size());
		intQueue.deleteMin();
		assertEquals(6, intQueue.size());
		intQueue.add(4);
		assertEquals(7, intQueue.size());
	}

	@Test
	public void testClear()
	{
		intQueue.clear();
		assertArrayEquals(new PriorityQueue<Integer>().toArray(),
				intQueue.toArray());
	}

	@Test
	public void testFindMinAndDeleteMin()
	{
		assertEquals(new Integer(1), intQueue.findMin());
		intQueue.deleteMin();
		assertEquals(new Integer(2), intQueue.findMin());
		intQueue.deleteMin();
		assertEquals(new Integer(3), intQueue.findMin());
		intQueue.deleteMin();
		assertEquals(new Integer(5), intQueue.findMin());
		intQueue.deleteMin();
		assertEquals(new Integer(6), intQueue.findMin());
		intQueue.deleteMin();
		assertEquals(new Integer(7), intQueue.findMin());
		intQueue.deleteMin();
		assertEquals(new Integer(8), intQueue.findMin());
		intQueue.deleteMin();
		assertEquals(new Integer(10), intQueue.findMin());
		intQueue.deleteMin();
		try
		{
			intQueue.findMin();
		} catch (NoSuchElementException e)
		{

		}
	}

	@Test
	public void testAdd()
	{
		intQueue.add(-1);
		assertEquals(new Integer(-1), intQueue.deleteMin());
		intQueue.add(0);
		assertEquals(new Integer(0), intQueue.deleteMin());
		intQueue.add(100);
		intQueue.add(25);
		intQueue.add(50);
		intQueue.add(75);
		intQueue.add(25);
		intQueue.add(50);
		intQueue.deleteMin();
		intQueue.deleteMin();
		intQueue.deleteMin();
		intQueue.deleteMin();
		intQueue.deleteMin();
		intQueue.deleteMin();
		intQueue.deleteMin();
		intQueue.deleteMin();
		assertEquals(new Integer(25), intQueue.deleteMin());
		assertEquals(new Integer(25), intQueue.deleteMin());
		assertEquals(new Integer(50), intQueue.deleteMin());
		assertEquals(new Integer(50), intQueue.deleteMin());
		assertEquals(new Integer(75), intQueue.deleteMin());
		assertEquals(new Integer(100), intQueue.deleteMin());
	}

}
