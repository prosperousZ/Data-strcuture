package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;
/**
 * BalancedSymbolChecker test case
 * @author Michael Sullivan, Haoze Zhang
 *
 */
public class BalancedSymbolCheckerTest {

	@Test
	public void checkFileTest() throws FileNotFoundException {
		BalancedSymbolChecker testChecker = new BalancedSymbolChecker();
		try {
			testChecker.checkFile("class");
			fail("The file does not exist");
		} catch (FileNotFoundException e) {
		}
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				testChecker.checkFile("Class1.java"));
		assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
				testChecker.checkFile("Class2.java"));
		assertEquals("No errors found. All symbols match.",
				testChecker.checkFile("Class3.java"));
		assertEquals("ERROR: File ended before closing comment.",
				testChecker.checkFile("Class4.java"));
		assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
				testChecker.checkFile("Class5.java"));
		assertEquals("No errors found. All symbols match.",
				testChecker.checkFile("Class6.java"));
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",
				testChecker.checkFile("Class7.java"));
		assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",
				testChecker.checkFile("Class8.java"));
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",
				testChecker.checkFile("Class9.java"));
		assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",
				testChecker.checkFile("Class10.java"));
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",
				testChecker.checkFile("Class11.java"));
		assertEquals("No errors found. All symbols match.",
				testChecker.checkFile("Class12.java"));
		assertEquals("No errors found. All symbols match.",
				testChecker.checkFile("Class13.java"));
		assertEquals("No errors found. All symbols match.",
				testChecker.checkFile("Class14.java"));
	}
}
