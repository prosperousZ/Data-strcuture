package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit with some simple tests to ensure the the spell checker class is
 * working properly.
 * 
 * @author Paul Carlson, Haoze Zhang
 *
 */
public class SpellCheckerTest {
	public ArrayList<String> words;
	public SpellChecker wordsDict;
	public SpellChecker largeDict;
	public SpellChecker helloDict;

	@Before
	public void makeWordList() {
		words = new ArrayList<String>();
		words.add("apple");
		words.add("banana");
		words.add("carrot");
		words.add("durian");

		largeDict = new SpellChecker(new File("dictionary.txt"));
		helloDict = new SpellChecker(new File("hello_world.txt"));
		wordsDict = new SpellChecker(words);
	}

	@Test
	public void testSpellCheckerConstructor() {
		SpellChecker dict = new SpellChecker();
		assertEquals(0, dict.getDictionary().size());
	}

	@Test
	public void testSpellCheckerListOfString() {
		wordsDict = new SpellChecker(words);
		assertEquals(4, wordsDict.getDictionary().size());
	}

	@Test
	public void testSpellCheckerFile() {
		largeDict = new SpellChecker(new File("dictionary.txt"));

		helloDict = new SpellChecker(new File("hello_world.txt"));
		assertEquals(7, helloDict.getDictionary().size());
	}

	@Test
	public void testAddToDictionary() {
		assertFalse(helloDict.getDictionary().contains("happy"));
		helloDict.addToDictionary("happy");
		assertTrue(helloDict.getDictionary().contains("happy"));
	}

	@Test
	public void testRemoveFromDictionary() {
		assertTrue(helloDict.getDictionary().contains("hello"));
		helloDict.removeFromDictionary("hello");
		assertFalse(helloDict.getDictionary().contains("hello"));
	}

	@Test
	public void testSpellCheck() {
		ArrayList<String> misspelled1 = (ArrayList<String>) largeDict.spellCheck(new File("hello_world.txt"));
		assertEquals(0, misspelled1.size());

		ArrayList<String> misspelled2 = (ArrayList<String>) helloDict.spellCheck(new File("hello_world.txt"));
		assertEquals(0, misspelled2.size());

		ArrayList<String> misspelled3 = (ArrayList<String>) wordsDict.spellCheck(new File("hello_world.txt"));
		assertEquals(7, misspelled3.size());
	}

}
