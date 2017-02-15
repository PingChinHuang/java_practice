class SortAlgorithm {
	private static SortAlgorithm instance = null;

	private int[] mSeries = null;
	private boolean mbIsSorted = false;
	private int mSortType = mSORT_TYPE_QUICKSORT;
	public static final int mSORT_TYPE_QUICKSORT = 0;
	public static final int mSORT_TYPE_MERGESORT = 1;
	public static final int mSORT_TYPE_BUBBLESORT = 2;
	public static final int mSORT_TYPE_SELECTIONSORT = 3;

	private SortAlgorithm(int type) {
		mSortType = type;
	}

	private void Swap(int i, int j) {
		int temp = mSeries[i];
		mSeries[i] = mSeries[j];
		mSeries[j] = temp;

		//mSeries[i] = mSeries[i] ^ mSeries[j];
		//mSeries[j] = mSeries[j] ^ mSeries[i];
		//mSeries[i] = mSeries[i] ^ mSeries[j];
		//
		//mSeries[i] = mSeries[i] + mSeries[j];
		//mSeries[j] = mSeries[i] - mSeries[j];
		//mSeries[i] = mSeries[i] - mSeries[j];
	}

	private void QuickSort(int left, int right) {
		if (mSeries == null || mSeries.length <= 1) return;

		if (left >= right) return;

		int pivotIdx = left;
		int pivot = mSeries[pivotIdx];
		int i = left, j = right;
		while (i <= j) {
			while (mSeries[i] < pivot) i++;
			while (mSeries[j] > pivot) j--;
			if (i <= j) {
				Swap(i, j);
				i++;
				j--;
			}
		} 
		
		if (left < j) QuickSort(left, j);
		if (right > i) QuickSort(i, right);
		mbIsSorted = true;
	}

	public boolean IsSorted() {
		return mbIsSorted;
	}

	public int[] Sort(int[] series) {
		if (series == null || series.length <= 1) return series;

		mSeries = series;

		switch (mSortType) {
		case mSORT_TYPE_QUICKSORT:
			QuickSort(0, mSeries.length - 1);
			break;
		default:
			break;
		}
		return mSeries;
	}

	public static SortAlgorithm CreateInstance(int type) {
		if (instance == null)
			instance = new SortAlgorithm(type);

		return instance;
	}
}

public class Sort {
	public static void main(String[] argv) {
		int[] unsortedSeries = null;
		try {
			unsortedSeries = new int[Integer.valueOf(argv[0])];
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Didn't give series length. Assign default value 10.");
			unsortedSeries = new int[10];
		}

		System.out.println("Original: ");
		for (int i = 0; i < unsortedSeries.length; i++) {
			unsortedSeries[i] = (int)(Math.random() * 50) + 1;
			System.out.print(unsortedSeries[i] + " ");
		}
		System.out.println("");

		SortAlgorithm sortAlgo = SortAlgorithm.CreateInstance(SortAlgorithm.mSORT_TYPE_QUICKSORT); 
		int[] sortedSeries = sortAlgo.Sort(unsortedSeries);
		System.out.println("Sorted: ");
		for (int i = 0; i < sortedSeries.length; i++) {
			System.out.print(sortedSeries[i] + " ");
		}
		System.out.println("");
	}
}
