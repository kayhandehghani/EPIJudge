package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstKey {
	@EpiTest(testDataFile = "search_first_key.tsv")

	public static int searchFirstOfK(List<Integer> A, int k) {
		// return naiveWay(A, k);
		return fastWay(A, k);
	}

	// O(log n) in worst case
	private static int fastWay(List<Integer> A, int k) {
		if (A == null) {
			return -1;
		}

		int high = A.size() - 1;
		int low = 0;
		int firstIndex = -1;

		while (low <= high) {
			int mid = (low + high) >>> 1;

			int curr = A.get(mid);

			if (k == curr) {
				firstIndex = mid;
				high = mid - 1;
			} else if (k < curr) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return firstIndex;
	}

	// O(n) in worst case
	@SuppressWarnings("unused")
	private static int naiveWay(List<Integer> A, int k) {
		if (A == null) {
			return -1;
		}

		int high = A.size() - 1;
		int low = 0;

		while (low <= high) {
			int mid = (high + low) >>> 1;

			int curr = A.get(mid);

			if (k == curr) {
				while (curr == k && --mid >= 0) {
					curr = A.get(mid);
				}
				return curr != k ? mid + 1 : 0;
			} else if (k < curr) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SearchFirstKey.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
