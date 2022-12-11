import java.util.Random;

public class sorter2 {
	/**
	 * Random generator
	 */
	Random random = new Random();

	/**
	 * Number of comparisons for last operation
	 */
	private int compareCount;

	/**
	 * Insertion sort implementation
	 *
	 * @param list
	 *            List containing elements to sort
	 * @return Number of comparisons
	 */
	public int insertionSort(Comparable list[]) {
		compareCount = 0;
		for (int i = 0; i < list.length; i++) {
			Comparable currentKey = list[i];
			int j = i;

			for (; j > 0 && increaseCC()
					&& list[j - 1].compareTo(currentKey) > 0;) {
				list[j] = list[j - 1];
				j = j - 1;
			}
			list[j] = currentKey;
		}
		return compareCount;
	}

	/**
	 * Increases comparison counter
	 *
	 * @return True
	 */
	private boolean increaseCC() {
		compareCount++;
		return true;
	}

	/**
	 * Executes quisearch
	 *
	 * @param list
	 *            List of objects to compare
	 * @param randomize
	 *            True => randomized quicksort
	 * @return Number of comparisons
	 */
	public int quickSort(Comparable[] list, boolean randomize) {
		compareCount = 0;
		recursiveQuickSort(list, 0, list.length - 1, randomize);
		return compareCount;
	}

	/**
	 * Recursive quicksort (heart of the quicksort implementation)
	 *
	 *
	 * @param list
	 *            List of objects to compare
	 * @param first
	 *            First pos
	 * @param last
	 *            Last pos
	 * @param randomize
	 *            True => randomized quicksort
	 */
	protected void recursiveQuickSort(Comparable[] list, int first, int last,
			boolean randomize) {
		if (first < last) {
			int p = partition(list, first, last, randomize);
			recursiveQuickSort(list, first, p - 1, randomize);
			recursiveQuickSort(list, p + 1, last, randomize);
		}
	}

	/**
	 * Part of recursive quicksearch implementation
	 *
	 * @param list
	 *            List of objects to compare
	 * @param first
	 *            First pos
	 * @param last
	 *            Last pos
	 * @param randomize
	 *            True => randomized quicksort
	 * @return New pivot
	 */
	protected int partition(Comparable[] list, int first, int last,
			boolean randomize) {
		if (randomize) {
			int p = first + random.nextInt(last - first + 1);
			swap(list, first, p);
		}
		int p = first;
		for (int n = p + 1; n <= last; n++) {
			if (increaseCC() && list[n].compareTo(list[p]) < 0) {
				swap(list, n, p + 1);
				swap(list, p, p + 1);
				p++;
			}
		}
		return p;
	}

	/**
	 * Swaps index1 to index2 in list
	 *
	 * @param list
	 *            List to sort
	 * @param index1
	 *            Pos 1 to put at position 2
	 * @param index2
	 *            Pos 2 to put at position 1
	 */
	protected void swap(Comparable[] list, int index1, int index2) {
		Comparable temp = list[index1];
		list[index1] = list[index2];
		list[index2] = temp;
	}

	/**
	 * Reheaps the heap ... muhaha
	 *
	 * @param arr
	 *            Array to sort
	 * @param k
	 *            Something
	 * @param l
	 *            Something
	 */
	protected void reheap(Comparable[] arr, int k, int l) {
		if (2 * k + 1 >= l)
			return;
		int maxson;
		if (2 * k + 1 == l - 1)
			maxson = 2 * k + 1;
		else {
			if (increaseCC() && arr[2 * k + 1].compareTo(arr[2 * k + 2]) > 0)
				maxson = 2 * k + 1;
			else
				maxson = 2 * k + 2;
		}

		if (increaseCC() && arr[k].compareTo(arr[maxson]) >= 0)
			return;
		swap(arr, k, maxson);
		reheap(arr, maxson, l);
	}

	/**
	 * Builds the heap
	 *
	 * @param arr
	 *            Arr to sort
	 * @param n
	 *            Length of arr
	 */
	protected void buildheap(Comparable[] arr, int n) {
		for (int j = (n / 2) - 1; j >= 0; j--)
			reheap(arr, j, n);
	}

	/**
	 * Heapsport implementation
	 *
	 * @param arr
	 *            array to be sorted
	 * @param n
	 *            Length of the array
	 * @return Number of comparisons
	 */
	public int heapsort(Comparable[] arr, int n) {
		if (n <= 1)
			return -1;
		compareCount = 0;
		buildheap(arr, n);
		int heapsize = n;
		while (heapsize >= 2) {
			swap(arr, 0, heapsize - 1);
			heapsize--;
			reheap(arr, 0, heapsize);
		}
		return compareCount;
	}

	/**
	 * Main method
	 *
	 * @param args
	 *            Console arguments
	 */
	public static void main(String[] args) {
		sorter2 sorter = new sorter2();

		Comparable[][] td = genTestData();
		int cmpSk = sorter.insertionSort(td[0]);
		int cmpEk = sorter.insertionSort(td[1]);
		System.out.println("Needed comparison for insertion sort/simple key: "
				+ cmpSk);
		System.out
				.println("Needed comparison for insertion sort/extended key: "
						+ cmpEk);

		td = genTestData();
		cmpSk = sorter.quickSort(td[0], true);
		cmpEk = sorter.quickSort(td[1], true);
		System.out.println("Needed comparison for quicksort sort/simple key: "
				+ cmpSk);
		System.out
				.println("Needed comparison for quicksort sort/extended key: "
						+ cmpEk);

		td = genTestData();
		cmpSk = sorter.heapsort(td[0], td[0].length);
		cmpEk = sorter.heapsort(td[1], td[1].length);
		System.out.println("Needed comparison for heapsort sort/simple key: "
				+ cmpSk);
		System.out
				.println("Needed comparison for heapsort sort/extended key: "
						+ cmpEk);
	}

	protected static Comparable[][] genTestData() {
		Comparable skArr[] = new Comparable[100];
		Comparable ekArr[] = new Comparable[100];

		for (int i = 0; (i < skArr.length) && (i < ekArr.length); i++) {
			int val = i % 2;
			skArr[i] = new SimpleKey(new Integer(val));
			ekArr[i] = new ExtendedKey(new Integer(val), new Integer(i));
		}

		return new Comparable[][] { skArr, ekArr };
	}
}
