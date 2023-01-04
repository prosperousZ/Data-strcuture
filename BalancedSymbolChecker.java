package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Michael Sullivan, Haoze Zhang
 */
public class BalancedSymbolChecker {
	/**
	 * @param String of filename
	 * @return A message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.)
	 * @Throws FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		//If the file does not exist, throw exception
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		//Create the stack
		LinkedListStack<Character> charStack = new LinkedListStack<Character>();
		//The scanner
		Scanner scanner = new Scanner(file);
		// create a lineNum variable to keep track of for file testing
		int lineNum = 0;
		//While the scanner has the next line
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineNum++;
			for (int i = 0; i < line.length(); i++) {
				if (i < line.length() -1 &&line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
					break;
					// skips to next line which is outside the comment
				}
				if (i < line.length() -1 && line.charAt(i) == '/' && line.charAt(i + 1) == '*' ) {
					// Checks for closed comment block
					boolean isClosed = false;
					while (scanner.hasNextLine()) {
						if (line.contains("*/")) {
							isClosed = true;
							break;
						}
						line = scanner.nextLine();
						lineNum++;
					}
					if (!isClosed) {
						scanner.close();
						return unfinishedComment();
					}
					break;
				}
				// skips string literal symbols
				if (i < line.length() -1 &&line.charAt(i) == '"') {
					for (int l = i + 1; l < line.length(); l++) {
						if (line.charAt(l) == '"') {
							//In order to check if there are " in the String
							for(int j = l + 1; j < line.length(); j ++){
								if(line.charAt(j) != '"'){
									break;
								}
							}
							i = l;
						}
					}
				}
				// skips character literal symbols
				if (i < line.length() -1 &&line.charAt(i) == '\'') {
					for (int l = i + 1; l < line.length(); l++) {
						if (line.charAt(l) == '\'') {
							i = l;
							break;
						}
					}
				}
				// put opening symbols onto our stack
				if (line.charAt(i) == '(' || line.charAt(i) == '['|| line.charAt(i) == '{') {
					charStack.push(line.charAt(i));
				} 
				//Check all the symbols
				else if (line.charAt(i) == ')' || line.charAt(i) == ']'|| line.charAt(i) == '}') {
					if (charStack.isEmpty()) {
						scanner.close();
						return unmatchedSymbol(lineNum, i + 1, line.charAt(i), ' ');
					}
					//Check ()
					if (line.charAt(i) == ')' && charStack.peek() == '(') {
						charStack.pop();
					} 
					//Check []
					else if (charStack.peek() == '[' && line.charAt(i) == ']') {
						charStack.pop();
					} 
					//Check {}
					else if (charStack.peek() == '{' && line.charAt(i) == '}') {
						charStack.pop();
					} 
					//If does not match, return the message
					else {
						scanner.close();
						return unmatchedSymbol(lineNum, i + 1, line.charAt(i), expectedChar(charStack.peek()));
					}
				}
			}
		}
		//If the stack is empty, file should be matched
		if (charStack.isEmpty()) {
			scanner.close();
			return allSymbolsMatch();
		}
		else {
			scanner.close();
			return unmatchedSymbolAtEOF(expectedChar(charStack.peek()));
		}
	}

	/**
	 * Help method, to find the expected symbol
	 * @param symbol
	 * @return the character that user expect
	 */
	private Character expectedChar(char symbol) {
		if (symbol == '(') {
			return ')';
		}
		if (symbol == '[') {
			return ']';
		}
		if (symbol == '{')
			return '}';
		return ' ';
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber,
			char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column "
				+ colNumber + ". Expected " + symbolExpected + ", but read "
				+ symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected "
				+ symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}
