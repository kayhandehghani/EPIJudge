package epi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class TwoSum {
	@EpiTest(testDataFile = "two_sum.tsv")

	public static boolean hasTwoSum(List<Integer> A, int t) {
		//		Collections.sort(A, Collections.reverseOrder()); // turning on reverse sort breaks approach one
		//		return approach2(A, t);
		return approach1(A, t);
	}

	// time: O(n), space: O(1) but only works with sorted array
	public static boolean approach2(List<Integer> A, int t) {
		int l = 0, h = A.size() - 1;

		while (l <= h) {
			if (A.get(l) + A.get(h) == t) {
				return true;
			} else if (A.get(l) + A.get(h) < t) {
				l++;
			} else {
				h--;
			}
		}

		return false;
	}

	// time: O(n), space: O(n) but works with unsorted array as well
	public static boolean approach1(List<Integer> A, int t) {
		Map<Integer, Integer> valueIndexMap = new HashMap<>();

		for (int i = 0; i < A.size(); i++) {
			int curr = A.get(i);
			if (t - curr == curr || valueIndexMap.containsKey(t - curr)) {
				return true;
			}
			valueIndexMap.put(curr, i);
		}

		return false;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "TwoSum.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
