import java.util.*;
public class Main {
    public static void main(String[] args) {
		int[][] test = new int[][] {{5,1,1}, {2,4,7}, {2,4,5}, {5,6,3}};
		// int[][] test = new int[][] {{1,2}, {3,4}};
		List<GreedyDynamicAlgorithms.Direction> res = GreedyDynamicAlgorithms.optimalGridPath(test);
		for (GreedyDynamicAlgorithms.Direction dir : res) {
            System.out.println(dir);
        }
		

	}

}

