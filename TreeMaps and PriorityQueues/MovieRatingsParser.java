/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	/**
	 * Keep in mind that the same movie title may appear numerous times in the input List, and multiple instances may even have the same rating.
	   A distinct movie title should only appear once as a key in the TreeMap, however, 
	   and be mapped to a PriorityQueue (min-heap) of all the ratings for that movie.
	 * If the input List is null or empty, parseMovieRatings should return an empty TreeMap
	 * If the input List contains any null UserMovieRatings object, 
	   or a UserMovieRatings object whose movie title is null or an empty string, 
	   or a UserMovieRatings object whose rating is negative, 
	   parseMovieRatings should ignore that UserMovieRatings object
	 * The movie titles should be considered case-insensitive, i.e. 
	   If two UserMovieRatings objects have the same title that differ only in case (upper or lower), 
	   they should be considered the same movie. The movie titles stored in the TreeMap must use lowercase letters.
	 * @param allUsersRatings
	 * @return
	 */
	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> myTreemMap = new TreeMap<String, PriorityQueue<Integer>>();
		if (allUsersRatings == null || allUsersRatings.isEmpty()) return myTreemMap;
		for (UserMovieRating rating : allUsersRatings) {
			if (!(rating == null || rating.getMovie() == null || rating.getMovie().isEmpty() || rating.getUserRating() < 0)) {
				if (!myTreemMap.containsKey(rating.getMovie().toLowerCase())) {
					PriorityQueue<Integer> myPriorityQueue = new PriorityQueue<Integer>();
					myPriorityQueue.add(rating.getUserRating());
					myTreemMap.put(rating.getMovie().toLowerCase(), myPriorityQueue);
				} else {
					myTreemMap.get(rating.getMovie().toLowerCase()).add(rating.getUserRating());
				}
			}
		}
		return myTreemMap;
	}
}
