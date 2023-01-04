package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is
 * implemented as a min heap. The min heap is implemented implicitly as an
 * array.
 * 
 * @author Haoze Zhang & Nicholas Fuller
 */
public class PriorityQueue<AnyType>
{
	//Size
	private int currentSize;
	//The back array
	private AnyType[] array;
	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according to their
	 * natural ordering (i.e., AnyType is expected to be Comparable) AnyType is
	 * not forced to be Comparable.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue()
	{
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10];
	}

	/**
	 * Construct an empty priority queue with a specified comparator. Orders
	 * elements according to the input Comparator (i.e., AnyType need not be
	 * Comparable).
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(Comparator<? super AnyType> c)
	{
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10];
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size()
	{
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear()
	{
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException
	{
		if (currentSize == 0)
		{
			throw new NoSuchElementException();
		}
		return array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException
	{
		// if the heap is empty, throw a NoSuchElementException
		if (currentSize == 0)
		{
			throw new NoSuchElementException();
		}
		// store the minimum item so that it may be returned at the end
		AnyType toReturn = array[0];
		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		// update size
		currentSize--;
		// percolate the item at minIndex down the tree until heap order is
		// restored
		percolateDown();
		// return the minimum item that was stored
		return toReturn;
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x
	 *            -- the item to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void add(AnyType x)
	{
		// if the array is full, double its capacity
		if (currentSize == array.length)
		{
			AnyType[] tmp = (AnyType[]) new Object[currentSize * 2];
			for (int i = 0; i < array.length; i++)
			{
				tmp[i] = array[i];
			}
			array = tmp;
		}

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;
		// update size
		currentSize++;
		// percolate the new item up the levels of the tree until heap order is
		// restored
		percolateUp();
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename)
	{
		try
		{
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for (int i = 0; i < currentSize; i++)
			{
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i]
						+ "|<f2> \"]");
				if (((i * 2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1)
							+ ":f1");
				if (((i * 2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2)
							+ ":f1");
			}
			out.println("}");
			out.close();
		} catch (IOException e)
		{
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by
	 * the user at construction time, or Comparable, if no Comparator was
	 * provided.
	 */
	@SuppressWarnings("unchecked")
	private int compare(AnyType lhs, AnyType rhs)
	{
		if (cmp == null)
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe
																		// to
																		// ignore
																		// warning
		// We won't test your code on non-Comparable types if we didn't supply a
		// Comparator
		return cmp.compare(lhs, rhs);
	}
	// LEAVE IN for grading purposes
	public Object[] toArray()
	{
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}
	/**
	 * Percolate down
	 */
	private void percolateDown()
	{
		int i = 0;
		while (i < (currentSize - 1))
		{
			AnyType parent = array[i];
			// check if there are two children
			if (leftChildIndex(i) < currentSize
					&& rightChildIndex(i) < currentSize)
			{
				AnyType leftChild = array[leftChildIndex(i)];
				AnyType rightChild = array[rightChildIndex(i)];
				if (compare(leftChild, rightChild) > 0)
				{
					if (compare(parent, rightChild) > 0)
					{
						swapWithParent(rightChildIndex(i));
						i = rightChildIndex(i);
					} else
					{
						return;
					}
				} else if (compare(leftChild, rightChild) < 0)
				{
					if (compare(parent, leftChild) > 0)
					{
						swapWithParent(leftChildIndex(i));
						i = leftChildIndex(i);
					} else
					{
						return;
					}
				}
				
				else
				{
					if (compare(parent, rightChild) > 0)
					{
						swapWithParent(rightChildIndex(i));
						i = rightChildIndex(i);
					} else
					{
						return;
					}
				}
			}
			// check if just one child on the right
			else if (rightChildIndex(i) < currentSize)
			{
				AnyType rightChild = array[rightChildIndex(i)];
				if (compare(parent, rightChild) > 0)
				{
					swapWithParent(rightChildIndex(i));
					i = rightChildIndex(i);
				} else
				{
					return;
				}
			}
			// check if just one child on the left
			else if (leftChildIndex(i) < currentSize)
			{
				AnyType leftChild = array[leftChildIndex(i)];
				if (compare(parent, leftChild) > 0)
				{
					swapWithParent(leftChildIndex(i));
					i = leftChildIndex(i);
				} else
				{
					return;
				}
			}
			else{
				return;
			}
		}
	}
	/**
	 * Percolate up
	 */
	private void percolateUp()
	{
		int i = currentSize - 1;
		while (i > 0)
		{
			AnyType child = array[i];
			AnyType parent = array[parentIndex(i)];
			if (compare(parent, child) > 0)
			{
				swapWithParent(i);
				i = parentIndex(i);
			} else
			{
				return;
			}
		}
	}
	/**
	 * Get the left child index
	 * @param index - The partner
	 * @return child index
	 */
	private int leftChildIndex(int index)
	{
		return (index * 2) + 1;
	}
	/**
	 * Get the right child index
	 * @param index - The partner
	 * @return child index
	 */
	private int rightChildIndex(int index)
	{
		return (index * 2) + 2;
	}
	/**
	 * Get the partner index
	 * @param index - The child index
	 * @return the partner index
	 */
	private int parentIndex(int index)
	{
		return (index - 1) / 2;
	}
	/**
	 * Help method to swap with the partner
	 * @param index - the child index
	 */
	private void swapWithParent(int index)
	{
		AnyType temp = array[index];
		array[index] = array[parentIndex(index)];
		array[parentIndex(index)] = temp;
	}
}
