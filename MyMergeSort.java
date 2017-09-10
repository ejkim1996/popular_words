package proj5;
/**
 * This class provides a mergesort implementation for an PopularWordsHashNode
 * ArrayList based on the Comparator inputted.
 * @author EJ Kim
 */

import java.util.ArrayList;
import java.util.Comparator;

public class MyMergeSort {
	//default constructor
	private MyMergeSort() { }
	
	/**
	 * wrapper method for the mergesort algorithm
	 * @param wordsList the ArrayList of PopularWordsHashNodes to be sorted
	 * @param c Comparator to base the sort on
	 */
	public static void mergeSort(ArrayList<PopularWordsHashNode> wordsList, Comparator<PopularWordsHashNode> c) {
		mergeSortRec(wordsList, 0, wordsList.size() - 1, c);
	}
	
	/**
	 * private method for the mergesort algorithm
	 * divides the list into partitions and then merges them
	 * @param wordsList the ArrayList of PopularWordsHashNodes to be sorted
	 * @param begin int of the first index of the partition
	 * @param end int of the last index of the partition
	 * @param c Comparator to base the sort on
	 */
	private static void mergeSortRec(ArrayList<PopularWordsHashNode> wordsList, int begin, int end, Comparator<PopularWordsHashNode> c) {
		//base case
		if (begin == end) {
			return;
		}
		//partition the ArrayList into two and call function recursively
		int mid = (begin + end)/2;
		mergeSortRec(wordsList, begin, mid, c);
		mergeSortRec(wordsList, mid+1, end, c);
		//merge the two partitions
		merge(wordsList, begin, mid, mid+1, end, c);
	}
	
	/**
	 * the merge method to be used in the mergesort algorithm
	 * @param wordsList the ArrayList of PopularWordsHashNodes to be sorted
	 * @param leftFirst int of the first index of the left partition
	 * @param leftLast int of the last index of the left partition
	 * @param rightFirst int of the first index of the right partition
	 * @param rightLast int of the last index of the right partition
	 * @param c Comparator to base the sort on
	 */
	private static void merge(ArrayList<PopularWordsHashNode> wordsList, int leftFirst, int leftLast, int rightFirst, int rightLast, Comparator<PopularWordsHashNode> c) {
		ArrayList<PopularWordsHashNode> tempList = new ArrayList<>();
		int indexLeft = leftFirst;
		int indexRight = rightFirst;
		
		//merge the two partitions and store values into tempList based on compare value
		while (indexLeft <= leftLast && indexRight <= rightLast) {
			if (c.compare(wordsList.get(indexLeft), wordsList.get(indexRight)) < 0) {
				tempList.add(wordsList.get(indexLeft));
				indexLeft++;
			}
			else {
				tempList.add(wordsList.get(indexRight));
				indexRight++;
			}
		}
		
		//copy over the remaining values to tempList
		for (int i = indexLeft; i <= leftLast; i++) {
			tempList.add(wordsList.get(i));
		}
		for (int i = indexRight; i <= rightLast; i++) {
			tempList.add(wordsList.get(i));
		}
		
		//save tempList values into wordsList on correct indices
		for (int i = leftFirst, j = 0; i <= rightLast; i++, j++) {
			wordsList.set(i, tempList.get(j));
		}
	}
}

/**
 * Comparator for PopularWordsHashNode objects that compares them by key values.
 * @author EJ Kim
 */
class CompareWordsByName implements Comparator<PopularWordsHashNode> { 
	@Override
	public int compare(PopularWordsHashNode o1, PopularWordsHashNode o2) {
		return o1.getKey().compareTo(o2.getKey());  
	}
}

/**
 * Comparator for PopularWordsHashNode objects that compares them by value information.
 * Smaller value returns a negative int.
 * @author EJ Kim
 */
class CompareWordsByScarcity implements Comparator<PopularWordsHashNode> { 
	@Override
	public int compare(PopularWordsHashNode o1, PopularWordsHashNode o2) { 
		int retValue = Integer.compare(o1.getValue(), o2.getValue());
		if (retValue == 0) {
			return o1.getKey().compareTo(o2.getKey());
		}
		else {
			return retValue;
		}
	}
}

/**
 * Comparator for PopularWordsHashNode objects that compares them by int values.
 * Bigger value returns a negative int.
 * @author EJ Kim
 */
class CompareWordsByFrequency implements Comparator<PopularWordsHashNode> { 
	@Override
	public int compare(PopularWordsHashNode o1, PopularWordsHashNode o2) {
		int retValue = Integer.compare(o2.getValue(), o1.getValue());
		if (retValue == 0) {
			return o1.getKey().compareTo(o2.getKey());
		}
		else {
			return retValue;
		}
	}
}