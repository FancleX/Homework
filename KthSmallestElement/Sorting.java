public class Sorting {
	
	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
	public static void mergeSort(CompareInt[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static void merge(CompareInt[] arr, int left, int mid, int right) {
		// get sizes of subarrarys to be merged
		int sub1 = mid - left + 1;
		int sub2 = right - mid;

		// create temporay subarrays
		CompareInt[] lt = new CompareInt[sub1];
		CompareInt[] rt = new CompareInt[sub2];
		
		// copy data to temp arrays
		for (int i = 0; i < sub1; i++) {
			lt[i] = arr[left + i];
		}
		for (int j = 0; j < sub2; j++) {
			rt[j] = arr[mid + 1 + j];
		}

		// merge the temp arrays
		// initial indexes of first and second subarrays
		int i = 0, j = 0;

		// initial indexes of merged subarrays
		int k = left;
		while (i < sub1 && j < sub2){
			if (lt[i].compareTo(rt[j]) <= 0) {
				arr[k] = lt[i];
				i++;
			} else {
				arr[k] = rt[j];
				j++;
			}
			k++;
		}

		// copy the rest elements of lt
		while (i < sub1) {
			arr[k] = lt[i];
			k++;
			i++;
		}

		// copy the rest elements of rt
		while (j < sub2) {
			arr[k] = rt[j];
			k++;
			j++;
		}
	}

	private static void sort(CompareInt[] arr, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			// sort left
			sort(arr, left, mid);
			// sort right
			sort(arr, mid + 1, right);
			// merge the sorted halves
			merge(arr, left, mid, right);
		}

	}

	
	/**
	 * Implement the quickSelect
	 * 
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
		quickSort(arr, 0, arr.length - 1);	
		// for (int i = 0; i < arr.length; i++) {
		// 	System.err.print(arr[i].val + ',');
		// }
		// System.err.println();
		return arr[k - 1];
	}

	public static void swap(CompareInt[] arr, int i, int j) {
		CompareInt temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static int partition(CompareInt[] arr, int low, int high) {
		CompareInt pivot = arr[high];
		// System.err.println(pivot.val);
		// index of the element that's smaller than pivot got so far
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j].compareTo(pivot) < 0) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return i + 1;
	}

	private static void quickSort(CompareInt[] arr, int low, int high) {
		if (low < high) {
			// partition index
			int pi = partition(arr, low, high);
			// sort left
			quickSort(arr, low, pi - 1);
			// sort right
			quickSort(arr, pi + 1, high);
		}
	}


	public static void main(String[] args) {
		CompareInt[] arr = MyTests.convert(new int[] {1,4,7,8,5,2,3,0});
		// mergeSort(arr);
		// for (int i = 0; i < arr.length; i++) {
		// 	System.err.print(arr[i].val + ',');
		// }
		// System.err.println();
		System.err.println(quickSelect(3, arr).val);
	}


}
