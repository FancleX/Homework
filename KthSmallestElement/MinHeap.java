

/**
 * A Heap implementation class
 * 
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {
	
	CompareInt[] heap;
	int size;

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
		if (size == heap.length - 1) throw new IllegalArgumentException();
		heap[++size] = val;
		// System.err.println(val.val);
		swim(size);
	}

	private void swim(int k) {
		while (k > 1 && heap[k].compareTo(heap[k / 2]) < 0) {
			// System.err.printf("swap val1 = %d, val2 = %d\n", heap[k].val, heap[k / 2].val);
			Sorting.swap(heap, k, k / 2);
			k = k / 2;
		}
	}

	private void sink(CompareInt[] heap, int k) {
		while (2 * k <= size) {
			int smallest = heap[2 * k].compareTo(heap[2 * k + 1]) < 0 ? 2 * k : 2 * k + 1;
			if (heap[k].compareTo(heap[smallest]) < 0) break;
			Sorting.swap(heap, k, smallest);
			k = smallest;
			// System.err.println("smallest = " + smallest);
		}
	}

	
	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {
		CompareInt min = heap[1];
		heap[1] = heap[size];
		size--;
		sink(heap, 1);
		return min;
	}
}
