package epi;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class MaxSumSubarray {
	@EpiTest(testDataFile = "max_sum_subarray.tsv")

	public static int findMaximumSubarray(List<Integer> A) {
		int minSum = 0, sumToHere = 0, maxSum = 0;

		for (int a : A) {
			sumToHere += a;
			minSum = Math.min(minSum, sumToHere);
			maxSum = Math.max(maxSum, sumToHere - minSum);
		}

		return maxSum;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "MaxSumSubarray.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
