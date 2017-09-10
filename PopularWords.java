package proj5;
/**
 * This class stores the key and value information from the inputted text file
 * into a hashnode, and uses it to print out the words and counts based on the
 * sort category given by the second argument.
 * @author EJ Kim
 */

import java.io.*;
import java.util.*;

public class PopularWords {
	private static HashMap<String, Integer> wordMap = new HashMap<>();
	private static ArrayList<PopularWordsHashNode> wordList = new ArrayList<>();

	public static void main(String[] args) {
		int numOfWordsToPrint = -1;
		
		//check if arguments are correct
		if (args.length < 2) {
			System.err.println("You must have two or more arguments.");
			System.exit(1);
		}
		String fileName = args[0];
		File file = new File(fileName);
		if (!file.canRead()) {
			System.err.printf("Error: cannot read data from file " + fileName);
			System.exit(1);
		}
		if (!args[1].equalsIgnoreCase("name") && !args[1].equalsIgnoreCase("frequency") && !args[1].equalsIgnoreCase("scarcity")) {
			System.err.println("Second argument must be name, frequency, or scarcity");
			System.exit(1);
		}
		if (args.length > 2) {
			try {
				numOfWordsToPrint = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException e) { 
		        System.err.println("Third argument must be a positive integer.");
		        System.exit(1);
		    } catch(NullPointerException e) {
		    	System.err.println("Third argument must be a positive integer.");
		        System.exit(1);
		    }
			if (Integer.parseInt(args[2]) < 0) {
				System.err.println("Third argument must be a positive integer.");
		        System.exit(1);
			}
		}
		
		//populate hashmap with words from the input file
		populateHashMap(file);
		
		//populate the PopularWordsHashNode ArrayList with data from the hashmap
		for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			wordList.add(new PopularWordsHashNode(key, value));
		}
		
		//print words and counts based on sort category and number of words to print
		printOutput(wordList, args[1], numOfWordsToPrint);
	}
	
	/**
	 * Populates the static HashMap variable using the inputted file.
	 * @param file File object to populate HashMap with
	 */
	public static void populateHashMap(File file) {
		//set up scanner for file
		Scanner inputFile = null; 
		try {
			inputFile = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			System.err.printf("Error: file " + file.getName() + "not found.");
			System.exit(1);
		}
		
		
		while (inputFile.hasNextLine()) {
			/* save one line of the file to the String object input in lower case.
			 * remove special characters from the line
			 * move to next iteration if the line is empty
			 * separate the info using space and store them into a String array.
			 */
			String input = inputFile.nextLine();
			input = input.toLowerCase();
			input = GetWords.removeSpecialCharacters(input);
			if (input.length() == 0) {
				continue;
			}
			String[] data = input.split(" ");
			
			//for every word in the data array, get rid of invalid word connectors
			//and add it to the hashmap. increment the value by one if it is already
			//in the hashmap.
			for (int i = 0; i < data.length; i++) {
				String cleanedWord = GetWords.cleanWord(data[i]);
				if (cleanedWord.length() == 0) {
					continue;
				}
				if (wordMap.containsKey(cleanedWord)) {
					wordMap.put(cleanedWord, wordMap.get(cleanedWord) + 1);
				}
				else {
					wordMap.put(cleanedWord, 1);
				}
				
			}
		}
	}
	
	/**
	 * Print words and their counts based on the sortCategory given by the second argument and 
	 * the number of words to print given by the third argument
	 * @param wordList the PopularWordsHashNode ArrayList to sort and get data from
	 * @param sortCategory the sort priority
	 * @param numOfWordsToPrint how many words/counts to be printed
	 */
	public static void printOutput(ArrayList<PopularWordsHashNode> wordList, String sortCategory, int numOfWordsToPrint) {
		//print output by name
		if (sortCategory.equalsIgnoreCase("name")) {
			//mergesort by name
			MyMergeSort.mergeSort(wordList, new CompareWordsByName());
			//if numOfWordsToPrint wasn't given or bigger than wordList size, print everything
			if (numOfWordsToPrint == -1 || numOfWordsToPrint > wordList.size()) {
				for (int i = 0; i < wordList.size(); i++) {
					System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
				}
			}
			//else, print only the amount given by third argument
			else {
				for (int i = 0; i < numOfWordsToPrint; i++) {
					System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
				}
			}
		}
		//print output by frequency
		else if (sortCategory.equalsIgnoreCase("frequency")) {
			//mergesort by frequency
			MyMergeSort.mergeSort(wordList, new CompareWordsByFrequency());
			//if numOfWordsToPrint wasn't given or bigger than wordList size, print everything
			if (numOfWordsToPrint == -1 || numOfWordsToPrint > wordList.size()) {
				for (int i = 0; i < wordList.size(); i++) {
					System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
				}
			}
			//else, print only the amount given by third argument
			else {
				for (int i = 0; i < numOfWordsToPrint; i++) {
					System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
				}
			}
		}
		//print output by scarcity
		else {
			//mergesort by scarcity
			MyMergeSort.mergeSort(wordList, new CompareWordsByScarcity());
			//if numOfWordsToPrint wasn't given or bigger than wordList size, print everything
			if (numOfWordsToPrint == -1 || numOfWordsToPrint > wordList.size()) {
				for (int i = 0; i < wordList.size(); i++) {
					System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
				}
			}
			//else, print only the amount given by third argument
			else {
				for (int i = 0; i < numOfWordsToPrint; i++) {
					System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
				}
			}
		}
	}

}
