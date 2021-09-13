import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

	/**
	 * This method assumes the input LinkedList is already sorted in non-descending
	 * order (i.e.,such that each element is greater than or equal to the one that
	 * is before it, and inserts the input int value into the correct location of
	 * the list. Note that the method does not return anything, but rather modifies
	 * the input LinkedList as a side effect. If the input LinkedList is null, this
	 * method should simply terminate.
	 * 
	 * @param list
	 * @param value
	 */
	public static void insertSorted(LinkedList<Integer> list, int value) {
		if (list != null) {
			if (list.isEmpty()) {
				list.add(value);
			} else if (list.get(list.size() - 1) < value) {
				list.add(value);
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) >= value) {
						list.add(i, value);
						break;
					}
				}
			}
		}
	}

	/**
	 * This method removes all instances of the N largest values in the LinkedList.
	 * Because the values are Strings, you will need to use the String class’
	 * compareTo method to find the largest elements; see the Java API for help with
	 * that method. If the input LinkedList is null or if N is non-positive, this
	 * method should simply return without any modifications to the input
	 * LinkedList. Keep in mind that if any of the N largest values appear more than
	 * once in the LinkedList, this method should return remove all instances, so it
	 * may remove more than N elements overall. The other elements in the LinkedList
	 * should not be modified and their order must not be changed.
	 * 
	 * @param list
	 * @param N
	 */
	public static void removeMaximumValues(LinkedList<String> list, int N) {
		if (list != null && N > 0 && !list.isEmpty()) {
			if (list.size() <= N) {
				list.removeAll(list);
			} else {
				LinkedList<String> myLinkedList = new LinkedList<String>();
				for (int i = 0; i < N; i++) {
					myLinkedList.add(findMax(list));
					list.removeAll(myLinkedList);
				}
			}
		}
	}

	private static String findMax(LinkedList<String> list) {
		String max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (max.compareTo(list.get(i)) < 0) {
				max = list.get(i);
			}
		}
		return max;
	}

	/**
	 * This method determines whether any part of the first LinkedList contains all
	 * elements of the second in the same order with no other elements in the
	 * sequence, i.e. it should return true if the second LinkedList is a
	 * subsequence of the first, and false if it is not. The method should return
	 * false if either input is null or empty.
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
		if (one == null || two == null || one.isEmpty() || two.isEmpty()) {
			return false;
		} else {
			int pt1 = 0;
			int pt2 = 0;
			while (pt2 < two.size() && pt1 < one.size()) {
				if (one.get(pt1) == two.get(pt2)) {
					pt1 += 1;
					pt2 += 1;
				}
				else {
					if (pt2 != 0) {
						pt1 = pt1 - pt2 + 1;
						pt2 = 0;
					}
					else {
						pt1 += 1;
					}
				}
			}
			if (pt2 == two.size()) {
				return true;
			}
			return false;
		}
	}	

	public static void main(String[] args) {
		// LinkedList<String> myLinkedList = new LinkedList<String>();
		// myLinkedList.add("aa");
		// myLinkedList.add("cc");
		// myLinkedList.add("bb");
		// myLinkedList.add("dd");
		// myLinkedList.add("cc");
		// myLinkedList.add("ff");
		// myLinkedList.add("gg");
		// myLinkedList.add("aa");
		// System.out.println(myLinkedList);
		// removeMaximumValues(myLinkedList, 4);
		// System.out.println(myLinkedList);
		LinkedList<Integer> one = new LinkedList<Integer>();
		LinkedList<Integer> two = new LinkedList<Integer>();
		one.add(1);
		one.add(1);
		one.add(1);
		one.add(2);
		one.add(3);
		one.add(1);

		two.add(1);
		two.add(2);
		two.add(3);
		System.out.println(containsSubsequence(one, two));

	}

}
