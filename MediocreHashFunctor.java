package assignment10;
/**
 * 
 * @author Nicholas Fuller & Haoze Zhang
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int toReturn = 0;
		for(int i = 0; i < item.length(); i ++){
			toReturn += item.charAt(i);
		}
		return toReturn;
	}
}
