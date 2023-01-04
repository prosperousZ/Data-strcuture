package assignment10;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
/**
 * 
 * @author  Nicholas Fuller & Haoze Zhang
 *
 */
public class ChainingHashTableTest {
	
	@Test
	public void testChainingHashTable() {
		ChainingHashTable table =new ChainingHashTable(10, new GoodHashFunctor());
		assertEquals(0, table.size());
	}

	@Test
	public void testAdd() {
		ChainingHashTable table =new ChainingHashTable(10, new GoodHashFunctor());
		
		//Test if the item is added correct
		assertTrue(table.add("a"));
		assertTrue(table.add("b"));
		assertTrue(table.add("c"));
		assertTrue(table.add("aa"));
		assertTrue(table.add("asd"));
		assertTrue(table.add("cc"));
		assertEquals(6, table.size());
		
		//Test if duplicate item is added in the table
		assertFalse(table.add("a"));
		assertEquals(6, table.size());
		assertFalse(table.add("asd"));
		assertEquals(6, table.size());
	}

	@Test
	public void testAddAll() {
		//Test a group of string
		ArrayList<String> stringList= new ArrayList<String>();
		stringList = new ArrayList<String>();
		stringList.add("cat");
		stringList.add("supercat");
		stringList.add("dog");
		stringList.add("dawg");
		stringList.add("dogg");
		stringList.add("superdog");
		stringList.add("doggy");
		stringList.add("kitty");
		stringList.add("kitten");
		stringList.add("abc");
		stringList.add("xyz");
		stringList.add("pdq");
		stringList.add("yay");
		stringList.add("yay");
		stringList.add("pay");
		stringList.add("day");
		stringList.add("jay");
		stringList.add("may");
		ChainingHashTable table = new ChainingHashTable(4, new GoodHashFunctor());
		assertTrue(table.addAll(stringList));
		
		//Test if there is duplicate in the stringList, will the addAll method add the duplicate?
		assertEquals(17, table.size());
		assertTrue(table.containsAll(stringList));
		assertTrue(table.contains("cat"));
		assertTrue(table.contains("supercat"));
		assertTrue(table.contains("dog"));
		assertTrue(table.contains("dawg"));
		assertTrue(table.contains("dogg"));
		assertTrue(table.contains("superdog"));
		assertTrue(table.contains("doggy"));
		assertTrue(table.contains("kitty"));
		assertTrue(table.contains("kitten"));
		assertTrue(table.contains("abc"));
		assertTrue(table.contains("xyz"));
		assertTrue(table.contains("yay"));
		assertTrue(table.contains("pay"));
		assertTrue(table.contains("day"));
		assertTrue(table.contains("jay"));
		assertTrue(table.contains("may"));
		assertFalse(table.contains("a"));
		assertTrue(table.add("a"));
		stringList.add("a");
		assertTrue(table.containsAll(stringList));
		
		//Test if only add one item to call addAll method
		ChainingHashTable table2 = new ChainingHashTable(4, new GoodHashFunctor());
		ArrayList<String> list= new ArrayList<String>();
		list.add("b");
		assertTrue(table2.addAll(list));
		assertEquals(1, table2.size());
		assertTrue(table2.contains("b"));
		assertTrue(table2.containsAll(list));
		
		//Test if nothing in the table, will the table still can call the addAll method
		ChainingHashTable table3 = new ChainingHashTable(4, new GoodHashFunctor());
		ArrayList<String> list2= new ArrayList<String>();
		assertEquals(0, table3.size());
		assertTrue(table3.addAll(list2));
		assertEquals(0, table3.size());
		assertTrue(table3.containsAll(list2));
	}

	@Test
	public void testClear() {
		ChainingHashTable table = new ChainingHashTable(4, new GoodHashFunctor());
		table.add("cat");
		table.add("supercat");
		table.add("dog");
		table.add("dawg");
		table.add("dogg");
		table.add("superdog");
		table.add("doggy");
		table.add("kitty");
		table.add("kitten");
		table.add("abc");
		table.add("xyz");
		table.add("pdq");
		table.add("yay");
		table.add("yay");
		table.add("pay");
		table.add("day");
		table.add("jay");
		assertEquals(16, table.size());
		table.clear();
		
		//Test the size after calling the clear method
		assertEquals(0, table.size());
		
		//Test if there is any item in the table
		assertFalse(table.contains("pay"));
		assertFalse(table.contains("cat"));
	}

	@Test
	public void testContains() {
		ChainingHashTable table = new ChainingHashTable(4, new GoodHashFunctor());
		assertFalse(table.contains("a"));
		assertEquals(0, table.size());
		table.add("cat");
		table.add("supercat");
		table.add("dog");
		table.add("dawg");
		table.add("dogg");
		table.add("superdog");
		table.add("doggy");
		table.add("kitty");
		table.add("kitten");
		table.add("abc");
		table.add("xyz");
		table.add("pdq");
		table.add("yay");
		table.add("yay");
		table.add("pay");
		table.add("day");
		table.add("jay");
		assertFalse(table.contains("a"));
		assertTrue(table.contains("xyz"));
		assertFalse(table.contains(""));
		assertTrue(table.contains("yay"));
	}

	@Test
	public void testContainsAll() {
		ArrayList<String> stringList= new ArrayList<String>();
		stringList = new ArrayList<String>();
		stringList.add("cat");
		stringList.add("supercat");
		stringList.add("dog");
		stringList.add("dawg");
		stringList.add("dogg");
		stringList.add("superdog");
		stringList.add("doggy");
		stringList.add("kitty");
		stringList.add("kitten");
		stringList.add("abc");
		stringList.add("xyz");
		stringList.add("pdq");
		stringList.add("yay");
		stringList.add("yay");
		stringList.add("pay");
		stringList.add("day");
		stringList.add("jay");
		stringList.add("may");
		ChainingHashTable table = new ChainingHashTable(4, new GoodHashFunctor());
		assertTrue(table.addAll(stringList));
		assertEquals(17, table.size());
		assertTrue(table.containsAll(stringList));
		table.add("asd");
		assertTrue(table.containsAll(stringList));
		assertEquals(18, table.size());
		assertTrue(table.add("a"));
		stringList.add("a");
		assertTrue(table.containsAll(stringList));
		stringList.add("asdf");
		assertFalse(table.containsAll(stringList));
		
	}

	@Test
	public void testIsEmpty() {
		ChainingHashTable table = new ChainingHashTable(4, new GoodHashFunctor());
		assertTrue(table.isEmpty());
	}

	@Test
	public void testSize() {
		//Test when create an new ChainingHashTable
		ChainingHashTable table = new ChainingHashTable(4, new GoodHashFunctor());
		assertEquals(0, table.size());
		table.add("cat");
		table.add("supercat");
		table.add("dog");
		table.add("dawg");
		table.add("dogg");
		table.add("superdog");
		table.add("doggy");
		table.add("kitty");
		table.add("kitten");
		table.add("abc");
		table.add("xyz");
		table.add("pdq");
		table.add("yay");
		table.add("yay");
		table.add("pay");
		table.add("day");
		table.add("jay");
		assertEquals(16, table.size());
		table.add("a");
		assertEquals(17, table.size());
		
		//Test if add the duplicate
		table.add("a");
		assertEquals(17, table.size());
	}

}
