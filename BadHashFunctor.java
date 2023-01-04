package assignment10;
/**
 * 
 * @author Nicholas Fuller & Haoze Zhang
 *
 */
public class BadHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		return item.length();
	}
}
