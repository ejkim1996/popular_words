package proj5;

/**
 * This class provides static methods as tools to remove special characters
 * and invalid word connectors
 * @author EJ Kim
 */

public class GetWords {
	//default constructor
	private GetWords() { }
	
	/**
	 * Remove special characters, repeated word connectors, digits from a whole line
	 * @param inputString line to remove special characters
	 * @return String with special characters removed
	 */
	public static String removeSpecialCharacters(String inputString) {
		//replace all characters that are not a-z, -, _, or ' with a space,
		//and then replace all repeated word connectors with a space
		inputString = inputString.replaceAll("[^a-z-_']", " ");
		inputString = inputString.replaceAll("-{2,}|_{2,}|'{2,}", " ");
		inputString = inputString.replaceAll("-_'|-'_|_-'|_'-|'_-|'-_|-_|-'|_-|_'|'-|'_", " ");
		
		//replace multiple spaces with one space
		inputString = inputString.replaceAll(" {2,}", " ");
		
		//remove single spaces from beginning and end of the line
		if (inputString.length() == 1) {
			if (inputString.charAt(0) == ' ') {
				inputString = inputString.substring(1, inputString.length());
			}
		}
		else if (inputString.length() != 0) {
			if (inputString.charAt(0) == ' ') {
				inputString = inputString.substring(1, inputString.length());
			}
			if (inputString.charAt(inputString.length() - 1) == ' ') {
				inputString = inputString.substring(0, inputString.length() - 1);
			}
		}
		return inputString;
	}
	
	/**
	 * Remove invalid word connectors from words.
	 * @param inputString the word to have invalid word connectors removed
	 * @return String with invalid word connectors removed
	 */
	public static String cleanWord(String inputString) {
		//while inputString still has letters
		while (inputString.length() != 0) {
			//if first character ', remove it
			if (inputString.charAt(0) == '\'') {
				inputString = inputString.substring(1, inputString.length());
				continue;
			}
			//if last character is ', remove it
			if (inputString.charAt(inputString.length() - 1) == '\'') {
				inputString = inputString.substring(0, inputString.length() - 1);
				continue;
			}
			//if first character is _, remove it
			if (inputString.charAt(0) == '_') {
				inputString = inputString.substring(1, inputString.length());
				continue;
			}
			//if last character is _, remove it
			if (inputString.charAt(inputString.length() - 1) == '_') {
				inputString = inputString.substring(0, inputString.length() - 1);
				continue;
			}
			//if first character is -, remove it
			if (inputString.charAt(0) == '-') {
				inputString = inputString.substring(1, inputString.length());
				continue;
			}
			//if last character is -, remove it
			if (inputString.charAt(inputString.length() - 1) == '-') {
				inputString = inputString.substring(0, inputString.length() - 1);
				continue;
			}
			break;
		}
		return inputString;
	}
}
