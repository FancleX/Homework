# HomeWork
 In this assignment you will implement methods that process movie ratings, using the java.util.TreeMap and java.util.PriorityQueue classes from the Java Collections API.

In completing this assignment, you will:

Become familiar with the methods in the java.util.TreeMap and java.util.PriorityQueue classes
Apply what you have learned about how trees and heaps store data
Demonstrate that you can use trees and heaps to solve data-related problems
Getting Started

Download UserMovieRating.java, which defines a class representing a single rating of a movie and contains two attributes: movie (the title of the movie) and userRating (an anonymous user rating of the movie). You should not change this implementation for your solution.

Also download the MovieRatingsParser.java and MovieRatingsProcessor.java files, which contain the unimplemented methods for the code that you will write in this assignment.

Debugging/Error Note:

If you run into errors/bugs/don't understand the output that Codio is giving you, please post in the Discussion Forum and a TA will assist you!  Please do NOT email Codio as they will not review any errors you are getting.

Activity Part 1. Convert List to Map

In MovieRatingsParser.java, implement the parseMovieRatings method, which takes a List of UserMovieRatings as input and creates a TreeMap data structure from it. The TreeMap is a mapping from the movie’s title to a PriorityQueue of all its associated ratings.

Keep in mind that the same movie title may appear numerous times in the input List, and multiple instances may even have the same rating.

A distinct movie title should only appear once as a key in the TreeMap, however, and be mapped to a PriorityQueue (min-heap) of all the ratings for that movie.

Your implementation of parseMovieRatings should also adhere to the following:

If the input List is null or empty, parseMovieRatings should return an empty TreeMap
If the input List contains any null UserMovieRatings object, or a UserMovieRatings object whose movie title is null or an empty string, or a UserMovieRatings object whose rating is negative, parseMovieRatings should ignore that UserMovieRatings object
The movie titles should be considered case-insensitive, i.e. if two UserMovieRatings objects have the same title that differ only in case (upper or lower), they should be considered the same movie. The movie titles stored in the TreeMap must use lowercase letters.
Please do not change the signature of the parseMovieRatings method and please do not modify UserMovieRatings.java. Also, please do not create any additional .java files for your solution. If you need additional classes, you can define them in MovieRatingsParser.java. Last, please be sure that your MovieRatingsParser class is in the default package, i.e. there is no “package” declaration at the top of the source code.

Activity Part 2. Using the Map

Now implement the following methods in MovieRatingsProcessor.java. All methods take as input a TreeMap that maps movie titles to a PriorityQueue of their ratings, in addition to any inputs specified:

getAlphabeticalMovies: return a List of movie titles in alphabetical order
getAlphabeticalMoviesAboveRating: given an input int rating, return a List of movie titles in alphabetical order, where all movies in the List do not have any ratings less than or equal to rating (hint: the PriorityQueue is a min-heap, meaning that the smallest rating is at the front of the queue!)
removeAllRatingsBelow: given an input int rating, modify the TreeMap object that was passed as an argument so that you remove all ratings in the PriorityQueue that are below (but not equal to) rating for every movie in the Map. If all ratings are removed from a movie’s PriorityQueue, then remove the mapping from the TreeMap. Additionally, this method should return a new TreeMap that maps a movie title to the number of ratings that were removed from its corresponding PriorityQueue; the TreeMap that is returned should only contain movies that had ratings removed from its PriorityQueue.
In all cases, the methods should also adhere to the following:

If the input is null or an empty TreeMap, the data structure returned by the method should be empty
You can assume that all movie titles in the input TreeMap consist of lowercase letters.

Please do not change the signatures of the three methods and please do not modify UserMovieRatings.java. Also, please do not create any additional .java files for your solution. If you need additional classes, you can define them in MovieRatingsProcessor.java. Last, please be sure that your MovieRatingsProcessor class is in the default package, i.e. there is no “package” declaration at the top of the source code.

Helpful Hints

Documentation about the methods in the TreeMap and PriorityQueue classes is available at:

https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html
Refer to this documentation if you need help understanding the methods that are available to you.

Keep in mind that the keys in the TreeMap are stored in a TreeSet, which is a binary search tree, so be sure to take advantage of its structure.

Also keep in mind that although the PriorityQueue class implements a min-heap, you do not have access to the underlying structure and the “top” or “root” of the heap. However, this class implements the java.util.Queue interface, and the front/head of the queue is guaranteed to be the smallest element.
