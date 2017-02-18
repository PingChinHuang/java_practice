class SortAlgorithm {
	private static SortAlgorithm instance = null;

	private int[] mSeries = null;
	private boolean mbIsSorted = false;
	private int mSortType = mSORT_TYPE_QUICKSORT;

	public static final int mSORT_TYPE_QUICKSORT = 0;
	public static final int mSORT_TYPE_MERGESORT = 1;
	public static final int mSORT_TYPE_BUBBLESORT = 2;
	public static final int mSORT_TYPE_SELECTIONSORT = 3;
	public static final int mSORT_TYPE_INSERTIONSORT = 4;

	private SortAlgorithm(int type) {
		mSortType = type;
	}

	private SortAlgorithm() {
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
		if (mSeries == null || mSeries.length <= 1) {
			mbIsSorted = true;
			return;
		}

		if (left >= right) {
			mbIsSorted = true;
			return;
		}

		int pivotIdx = left;
		int pivot = mSeries[pivotIdx];
		int i = left, j = right;
		while (i <= j) {
			while (mSeries[i] < pivot && i <= right) i++;
			while (mSeries[j] > pivot && j > pivotIdx) j--;
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

	private void BubbleSort() {
		for (int i = 0; i < mSeries.length; i++) {
			for (int j = 0; j < mSeries.length - i - 1; j++) {
				if (mSeries[j] > mSeries[j + 1])
					Swap(j, j + 1);
			}
		}
		mbIsSorted = true;
	}

	private void SelectionSort() {
		for (int i = 0; i < mSeries.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < mSeries.length; j++) {
				if ( mSeries[minIdx] > mSeries[j]) minIdx = j;
			}

			Swap(minIdx, i);
		}
		mbIsSorted = true;
	}

	private int[] Merge(int[] left, int[] right) {
		if (left == null || left.length < 1) return right;
		if (right == null || right.length < 1) return left;

		int[] sorted = new int[left.length + right.length];
		int leftIndex = 0, rightIndex = 0;
		for (int i = 0; i < left.length + right.length; i++) {
			if (leftIndex >= left.length) {
				sorted[i] = right[rightIndex++];
				System.out.println("[1]sorted[i] = " + sorted[i]);
			} else if (rightIndex >= right.length) {
				sorted[i] = left[leftIndex++];
				System.out.println("[2]sorted[i] = " + sorted[i]);
			} else if (left[leftIndex] >= right[rightIndex]) {
				sorted[i] = right[rightIndex++];

				System.out.println("[3]sorted[i] = " + sorted[i]);
			} else {
				sorted[i] = left[leftIndex++];
				System.out.println("[4]sorted[i] = " + sorted[i]);
			}
		}

		return sorted;
	}

	private int[] MergeSort(int[] series) {
		if (series == null || series.length <= 1) return series;

		int middle = series.length / 2;
		int[] leftSeries = new int[middle - 1 + 1]; // 0 ~ middle - 1
		int[] rightSeries = new int[series.length - middle]; // middle ~ mSeries.length - 1

		System.out.println("leftSeries Length=" + leftSeries.length  + " rightSeries Len=" + rightSeries.length);
		for (int i = 0; i < middle; i ++) {
			System.out.println("[left]series[i] = " + series[i]);
			leftSeries[i] = series[i];
		}
		for (int i = middle; i < series.length; i++) {
			System.out.println("[right]series[i] = " + series[i]);
			rightSeries[i - middle] = series[i];
		}

		leftSeries = MergeSort(leftSeries);
		rightSeries = MergeSort(rightSeries);
		return Merge(leftSeries, rightSeries);
	}

	private void MergeSort() {
		mSeries = MergeSort(mSeries);
		mbIsSorted = true;
	}

	private void InsertionSort() {
		for (int i = 1; i < mSeries.length; i++) {
			int tmp = mSeries[i];
			int j = i - 1;
			for (; j >= 0 && tmp < mSeries[j]; j--) {
				mSeries[j + 1] = mSeries[j];
			}
			mSeries[j + 1] = tmp;
		}
	}

	public boolean IsSorted() {
		return mbIsSorted;
	}

	public int GetSortType() {
		return mSortType;
	}

	public void SetSortType(int type) {
		mSortType = type;
	}

	public int[] Sort(int[] series) {
		if (series == null || series.length <= 1) {
			mbIsSorted = true;
			return series;
		}

		mSeries = series;
		mbIsSorted = false;

		switch (mSortType) {
		case mSORT_TYPE_QUICKSORT:
			QuickSort(0, mSeries.length - 1);
			break;
		case mSORT_TYPE_BUBBLESORT:
			BubbleSort();
			break;
		case mSORT_TYPE_SELECTIONSORT:
			SelectionSort();
			break;
		case mSORT_TYPE_MERGESORT:
			MergeSort();
			break;
		case mSORT_TYPE_INSERTIONSORT:
			InsertionSort();
			break;
		default:
			break;
		}
		return mSeries;
	}

	public static SortAlgorithm CreateInstance(int type) {
		if (instance == null)
			instance = new SortAlgorithm(type);
		else
			instance.SetSortType(type);

		return instance;
	}

	public static SortAlgorithm CreateInstance() {
		if (instance == null)
			instance = new SortAlgorithm();
		else
			instance.SetSortType(mSORT_TYPE_QUICKSORT);

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
