/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

//If the input is null or an empty TreeMap, the data structure returned by the method should be empty
//You can assume that all movie titles in the input TreeMap consist of lowercase letters.
public class MovieRatingsProcessor {

	/**
	 * Return a List of movie titles in alphabetical order.
	 * @param movieRatings
	 * @return
	 */
	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		if (movieRatings == null || movieRatings.isEmpty()) return new ArrayList<String>();
		return new ArrayList<String>(movieRatings.keySet());
	}

	/**
	 * Given an input int rating, return a List of movie titles in alphabetical order, 
	   where all movies in the List do not have any ratings less than or equal to rating 
	   (hint: the PriorityQueue is a min-heap, meaning that the smallest rating is at the front of the queue!)
	 * @param movieRatings
	 * @param rating
	 * @return
	 */
	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		List<String> myStr = new ArrayList<String>();
		if (movieRatings == null || movieRatings.isEmpty()) return myStr;
		for (String title : movieRatings.keySet()) {
			if (movieRatings.get(title).peek() > rating) {
				myStr.add(title);
			}
		}
		return myStr;
	}
	
	/**
	 * Given an input int rating, 
	   modify the TreeMap object that was passed as an argument so that you remove all ratings in the PriorityQueue 
	   that are below (but not equal to) rating for every movie in the Map. 
	   If all ratings are removed from a movie’s PriorityQueue, then remove the mapping from the TreeMap. Additionally, 
	   this method should return a new TreeMap that maps a movie title to the number of ratings that were removed from its corresponding PriorityQueue; 
	   the TreeMap that is returned should only contain movies that had ratings removed from its PriorityQueue.
	 * @param movieRatings
	 * @param rating
	 * @return
	 */
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		TreeMap<String, Integer> newTreeMap = new TreeMap<String, Integer>();
		if (movieRatings == null || movieRatings.isEmpty()) return newTreeMap;
		
		// throw java.util.concurrentmodificationexception
		// for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
		// 	for (Integer ratings : entry.getValue()) {
		// 		if (ratings < rating) {
		// 			entry.getValue().remove(ratings);
		// 			if (!newTreeMap.containsKey(entry.getKey())) {
		// 				newTreeMap.put(entry.getKey(), 1);
		// 			} else {
		// 				newTreeMap.put(entry.getKey(), newTreeMap.get(entry.getKey()) + 1);
		// 			}
		// 		}
		// 	}

		Iterator<Map.Entry<String, PriorityQueue<Integer>>> entry = movieRatings.entrySet().iterator();
		while (entry.hasNext()) {
			Map.Entry<String, PriorityQueue<Integer>> elem = entry.next();
			Iterator<Integer> ratings = elem.getValue().iterator();
			while (ratings.hasNext()) {
				if (ratings.next() < rating) {
					ratings.remove();
					if (!newTreeMap.containsKey(elem.getKey())) {
						newTreeMap.put(elem.getKey(), 1);
					} else {
						newTreeMap.put(elem.getKey(), newTreeMap.get(elem.getKey()) + 1);
					}
				}
			}
			if (elem.getValue().isEmpty()) {
				entry.remove();
			}
		}
		return newTreeMap;
	}
}
