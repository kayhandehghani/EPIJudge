package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimalUtilization {

	public static int[][] findOptimalUtilization(int[][] a, int[][] b, int target) {
		return brute(a, b, target);
	}

	private static int[][] brute(int[][] a, int[][] b, int target) {
		List<int[]> result = new ArrayList<>();

		Arrays.sort(a, (p1, p2) -> p1[1] - p2[1]);
		Arrays.sort(b, (p1, p2) -> p2[1] - p1[1]);

		int aIndex = 0, bIndex = 0, maxSum = Integer.MIN_VALUE;

		while (aIndex < a.length && bIndex < b.length) {
			int sum = a[aIndex][1] + b[bIndex][1];

			if (sum > target) {
				bIndex++;
				continue;
			}

			if (sum >= maxSum) {
				if (sum > maxSum) {
					maxSum = sum;
					result = new ArrayList<>();
				}

				result.add(new int[] {a[aIndex][0], b[bIndex][0]});

				int bIndexRest = bIndex + 1;

				while (bIndexRest < b.length && b[bIndex][1] == b[bIndexRest][1]) {
					result.add(new int[] {a[aIndex][0], b[bIndexRest][0]});
					bIndexRest++;
				}
			}

			aIndex++;
		}

		System.out.println(Arrays.deepToString(a));
		System.out.println(Arrays.deepToString(b));

		int[][] resultArr = new int[result.size()][];
		for (int i = 0; i < result.size(); i++) resultArr[i] = result.get(i);
		return resultArr;
	}

	public static void main(String[] args) {
		test(new int[][]{{1, 8}, {2, 15}, {3, 9}},
				new int[][]{{1, 8}, {2, 11}, {3, 12}},
				20,
				new int[][]{{1, 3}, {3, 2}});
		test(new int[][]{{1, 8}, {2, 7}, {3, 14}},
				new int[][]{{1, 5}, {2, 10}, {3, 14}},
				20,
				new int[][]{{3, 1}});

		System.out.println("Done");
	}

	private static void test(int[][] a, int[][] b, int target, int[][] expected) {
		assert(Arrays.deepEquals(expected, findOptimalUtilization(a, b, target)));
	}

}
