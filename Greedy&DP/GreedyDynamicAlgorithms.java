
import java.util.*;

public class GreedyDynamicAlgorithms {

	/**
	 * Goal: find the smallest number of red intervals to select, such that
	 * every blue interval overlaps with at least one of the selected red intervals.
	 * Output this number
	 * 
	 * @param red - the list of red intervals
	 * @param blue - the list blue intervals
	 * @return
	 */
	public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
		Interval.sortByStartTime(blue);
		Interval.sortByFinishTime(red);
		int i, j, count;
		i = j = count = 0;

		while (i < blue.size() && j < red.size()) {
			if (!isIntersected(red.get(j), blue.get(i))) {
				j++;
				continue;
			}

			// if is intersected, loop to find the largest interval that overlaps the case
			while (j < red.size() && isIntersected(red.get(j), blue.get(i))) {
				j++;
			}
			count++;

			// go over the blue interval that the red interval overlaps
			while (i < blue.size() && isIntersected(red.get(j - 1), blue.get(i))) {
				i++;
				// if all of the blue intervals are intersected return the count
				if (i == blue.size()) {
					return count;
				}
			}
		}

		// if some blue interval is not intersected return -1
		return i < blue.size() ? -1 : count;
	}
	
	/**
	 * return true if red interval can intersect with blue interval
	 * @param Red
	 * @param Blue
	 * @return
	 */
	public static boolean isIntersected(Interval Red, Interval Blue) {
		if (Blue.start < Red.start) {
			return Blue.finish >= Red.start;
		} else {
			return Red.finish >= Blue.start;
		}
	}

	/**
	 * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return
	 */
	public static List<Direction> optimalGridPath(int[][] grid) {
		List<Direction> dirs = new ArrayList<Direction>();
		if (grid == null || grid.length == 0) {
			return dirs;
		}

		int row = grid.length; // row = 4
		int col = grid[0].length; // col = 3
		directionArrayList[][] directionMatrix= new directionArrayList[row][col];
		int[][] memo = new int[row][col];
		memo[0][0] = grid[0][0];

		// initialize colume
		for (int i = 1; i < row; i++) {
			memo[i][0] = memo[i - 1][0] + grid[i][0];
			if (i == 1) {
				directionMatrix[i][0] = new directionArrayList();
				directionMatrix[i][0].add(Direction.DOWN);
			} else {
			// down
			directionMatrix[i][0] = new directionArrayList();
			directionMatrix[i][0].addAll(directionMatrix[i - 1][0]);
			directionMatrix[i][0].add(Direction.DOWN);
			}
		}
		// initialize row
		for (int j = 1; j < col; j++) {
			memo[0][j] = memo[0][j - 1] + grid[0][j];
			if (j == 1) {
				directionMatrix[0][j] = new directionArrayList();
				directionMatrix[0][j].add(Direction.RIGHT);
			} else {
			// right
			directionMatrix[0][j] = new directionArrayList();
			directionMatrix[0][j].addAll(directionMatrix[0][j - 1]);
			directionMatrix[0][j].add(Direction.RIGHT);
			}
		}

		// // // check row
		// for (int i = 1; i < row; i++){
		// 	System.out.println(directionMatrix[i][0].getList());
		// }

		// // check col
		// for (int j = 1; j < col; j++){
		// 	System.out.println(directionMatrix[0][j].getList());
		// }

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (memo[i - 1][j] > memo[i][j - 1]) {
					memo[i][j] = memo[i][j - 1] + grid[i][j];
					//right
					directionMatrix[i][j] = new directionArrayList();
					directionMatrix[i][j].addAll(directionMatrix[i][j - 1]);
					// System.out.println(directionMatrix[i][j - 1].getList());
					directionMatrix[i][j].add(Direction.RIGHT);	
				} else {
					memo[i][j] = memo[i - 1][j] + grid[i][j];
					//down
					directionMatrix[i][j] = new directionArrayList();
					directionMatrix[i][j].addAll(directionMatrix[i - 1][j]);
					// System.out.println(directionMatrix[i - 1][j].getList());
					directionMatrix[i][j].add(Direction.DOWN);
				}
				// System.out.println(i);
				// System.out.println(j);
				// System.out.println(directionMatrix[i][j].getList());
				// Print2DArray(memo);
				// System.out.println();
			}
		}
		// Print2DArray(directionMatrix);
		dirs = directionMatrix[row - 1][col - 1].getList();
		return dirs;
	}

	/**
	 * an arraylist datatype to store the directions
	 */
	public static class directionArrayList {
		ArrayList<Direction> d;
		directionArrayList() {
			this.d = new ArrayList<Direction>();
		}

		/**
		 * add a direction to directionArrayList
		 * @param dir Direction
		 */
		public void add(Direction dir) {
			d.add(dir);
		}

		/**
		 * add a group of directions to directionArrayList
		 * @param dA
		 */
		public void addAll(directionArrayList dA) {
			for (Direction i : dA.getList()) {
				d.add(i);
			}
		}

		/**
		 * get the directions from a directionArrayList
		 * @return a list contains Directions
		 */
		public ArrayList<Direction> getList() {
			return d;
		}

		/**
		 * get size of the directionArrayList
		 * @return size
		 */
		public int getSize() {
			return d.size();
		}
	}
	
	/**
	 * print 2D int array
	 * @param array
	 */
	public static void Print2DArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * print 2D directionArrayList where contains the optimal paths (don't include the boundary)
	 * @param array
	 */
	public static void Print2DArray(directionArrayList[][] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = 1; j < array[0].length; j++) {
				System.out.println(array[i][j].getList());
			}
			System.out.println();
		}
	}
	

	/**
	 * A simple Direction enum
	 * directions can be either DOWN or RIGHT
	 * You will output a list of these in the grid-path problem
	 */
	public static enum Direction {
		DOWN, RIGHT
	}

	/**
	 * A private Interval class to help with the interval question
	 */
	public static class Interval {
		
		int start;
		int finish;
		
		public Interval(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
		
		/**
		 * sorts a list of intervals by start time, you are free to use this on the first question
		 */
		public static void sortByStartTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.start - i2.start;
				}
			});
		}
		
		/**
		 * sorts a list of intervals by finish time, you are free to use this on the first question
		 */
		public static void sortByFinishTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.finish - i2.finish;
				}
			});
		}
	}	
}


