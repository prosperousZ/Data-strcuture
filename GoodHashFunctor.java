package assignment10;
/**
 * 
 * @author Nicholas Fuller & Haoze Zhang
 *
 */
public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int h = 0;
		for (int i = 0; i <  item.length() ; i++) {
		    h = 31 * h + item.charAt(i);
		}
		if(h<0){
			return -h;
		}
		return h;
	}
}
