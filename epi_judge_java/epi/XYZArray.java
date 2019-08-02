package epi;

import java.util.Arrays;

public class XYZArray {

	public static void main(String[] args) {
		int[] arr = new int[] {-2, 2, 0, 2};
		testArray(arr, true);

		arr = new int[] {3, -1, 2, 0};
		testArray(arr, false);

		arr = new int[] {3, -1, 2, 0, 6};
		testArray(arr, true);

		arr = new int[] {-1, 2, 6};
		testArray(arr, true);

		arr = new int[] {-1, -2, 6};
		testArray(arr, false);

		arr = new int[] {-1, -2};
		testArray(arr, false);

		arr = new int[] {};
		testArray(arr, false);

		testArray(null, false);
		System.out.println("Passed all tests!");
	}

	public static void testArray(int[] arr, boolean expected) {
		System.out.println(String.format("Array to test %s", Arrays.toString(arr)));
		boolean result = isArrayXYXFast(arr);
		//		boolean result = isArrayXYXNaive(arr);
		System.out.println(String.format("\tExpected %b, returned %b XYZ", expected, result));
		assert (expected == result);
	}


	// time complexity is O(n), space complexity is O(n)
	public static boolean isArrayXYXFast(int[] arr) {
		if (arr == null || arr.length < 3) {
			return false;
		}

		int[] mins = new int[arr.length];
		int[] maxs = new int[arr.length];

		mins[0] = arr[0];

		for (int i = 1; i < arr.length; i++) {
			mins[i] = Math.min(mins[i - 1], arr[i]);
		}

		maxs[arr.length - 1] = arr[arr.length - 1];

		for (int i = arr.length - 2; i >= 0; i--) {
			maxs[i] = Math.max(maxs[i + 1], arr[i]);
		}

		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] > mins[i -1] && arr[i] < maxs[i + 1]) {
				return true;
			}
		}

		return false;
	}

	// time complexity is O(n3), space complexity is O(1)
	public static boolean isArrayXYXNaive(int[] arr) {

		if (arr == null || arr.length < 3) {
			return false;
		}

		for (int i = 0; i < arr.length - 2; i++) {
			for (int j = i + 1; j < arr.length - 1; j++) {
				for (int k = j + 1; k < arr.length; k++) {
					int x = arr[i];
					int y = arr[j];
					int z = arr[k];

					if (x < y && y < z) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
