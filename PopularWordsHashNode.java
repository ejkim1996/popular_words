package proj5;
/**
 * This class stores key and value information to be used to create
 * arrays/ArrayLists from hashtable/hashmap for sorting.
 * @author EJ Kim
 */

public class PopularWordsHashNode implements Comparable<PopularWordsHashNode>{
	//declare variables
	private String key;
	private Integer value;
	
	//getters
	public String getKey() {
		return key;
	}

	public Integer getValue() {
		return value;
	}

	//default constructor
	public PopularWordsHashNode() { }
	
	//constructor with key and value parameters
	public PopularWordsHashNode(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * default compareTo method for this class
	 * compares keys if they are different
	 * and compares the values if keys are the same
	 * @return an int based on which element is bigger
	 */
	@Override
	public int compareTo(PopularWordsHashNode arg0) {
		if (key.equals(arg0)) {
			return key.compareToIgnoreCase(arg0.getKey());
		}
		else {
			return value.compareTo(arg0.getValue());
		}
	}
}
