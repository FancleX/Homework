import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.nio.file.Paths;
import java.util.HashMap;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/* 
	 * This method should take the name of the file to read and read it one line at a time, 
	   creating Sentence objects and putting them into the List. 
	   Note that the method returns a List containing Sentence objects.
	   Your code should ignore (and not create a Sentence object for) any line that is not well-formatted, 
	   which we assume to mean “starts with an int between -2 and 2 (inclusive), 
	   has a single whitespace, and then is followed by more text.”
	 * However, if the file cannot be opened for reading or if the input filename is null, this method should return an empty List.
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		List<Sentence> mySentences = new ArrayList<Sentence>();
		List<String> allLines = new ArrayList<String>();
		try {
			allLines = Files.readAllLines(Paths.get(filename));
		} catch (Exception e) {
			return mySentences;
		}
		for (String line : allLines) {
			try {
				int score = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				String text = line.substring(line.indexOf(" ") + 1);
				if (score >= -2 && score <= 2 && !text.isEmpty()) {
					mySentences.add(new Sentence(score, text));
				}
			} catch (Exception e) {
				continue;
			}
		}
		return mySentences;
	}
	
	/*
	 * This method should find all of the individual tokens/words in the text field of each Sentence in the List and create Word objects for each distinct word. 
	   The Word objects should keep track of the number of occurrences of that word in all Sentences, 
	   and the total cumulative score of all Sentences in which it appears. 
	   This method should then return a Set of those Word objects.
	 * If the input List of Sentences is null or is empty, the allWords method should return an empty Set.
	 * If a Sentence object in the input List is null, this method should ignore it and process the non-null Sentences.
	 * As you may have noticed in the data file we provided, some tokens start with punctuation and the first word of each sentence starts with a capital letter. 
	   In producing the Set of Words, your program should ignore any token that does not start with a letter. 
	   Also, this method should convert all strings to lowercase so that it is case-insensitive. 
	   Do not assume that the strings in the Sentence objects have already been converted to lowercase.
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		ArrayList<Word> myWord = new ArrayList<Word>();
		if (sentences != null) {
			for (Sentence sentence : sentences) {
				if (sentence != null) {
					String[] tokens = sentence.getText().toLowerCase().split(" ");
					for (String token : tokens) {
						if (Character.isLetter(token.charAt(0))) {
							Word word = new Word(token);
							word.increaseTotal(sentence.getScore());
							if (!myWord.contains(word)) {
								myWord.add(word);
							} else {
								myWord.get(myWord.indexOf(word)).increaseTotal(word.getTotal());
							}
						}
					}
				}	
			}
		}
		return new HashSet<Word>(myWord); 
	}
	
	/*
	 * This method should iterate over each Word in the input Set, 
	   use the Word’s calculateScore method to get the average sentiment score for that Word, 
	   and then place the text of the Word (as key) and calculated score (as value) in a Map.
	 * If the input Set of Words is null or is empty, the calculateScores method should return an empty Map.
	 * If a Word object in the input Set is null, this method should ignore it and process the non-null Words.
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
		Map<String, Double> myMap = new HashMap<String, Double>();
		if (!(words == null || words.isEmpty())) {
			for (Word word : words) {
				if (word != null) {
					myMap.put(word.getText(), word.calculateScore());
				}
			}
		}
		return myMap; 
	}
	
	/*
	 * This method should use the Map to calculate and return the average score of all the words in the input sentence.
	 * If a word in the sentence does not start with a letter, 
	   then you can ignore it, but if it starts with a letter and is not present in the Map, 
	   assign it a score of 0.
	 * You may assume that all words in the Map consist only of lowercase letters (since this would have been done in previous steps) 
	   but do not assume that all words in the input sentence consist only of lowercase letters; 
	   you should convert them to lowercase before checking whether they’re contained in the Map.
	 * If the input Map is null or empty, or if the input sentence is null or empty or does not contain any valid words, 
	   this method should return 0.
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		double score = 0;
		int times = 0;
		if (!(wordScores == null || wordScores.isEmpty() || sentence == null || sentence.isEmpty())) {
			String[] tokens = sentence.toLowerCase().split(" ");
			for (String token : tokens) {
				if (Character.isLetter(token.charAt(0))) {
					if (wordScores.containsKey(token)) {
						score += wordScores.get(token);
					}
					times += 1;
				}
			}
		}
		if (times != 0 ) return score / times;
		return 0;
	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
