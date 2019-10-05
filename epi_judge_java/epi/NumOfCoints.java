package epi;

import java.util.Arrays;

public class NumOfCoints {

	public static int minNumOfCoinsOn1(int[] coins, int val) {
		if (coins == null || coins.length == 0 || val < 1) return 0;

		int[] cache = new int[val + 1];
		cache[0] = 0;

		int count = 0;
		for (int i = 1; i <= val; i++) cache[i] = Integer.MAX_VALUE;

		for (int i = 1; i <= val; i++) {
			for (int j = 0; j < coins.length; j++) {
				count++;
				if (coins[j] <= i) {
					if (cache[i - coins[j]] != Integer.MAX_VALUE) {
						cache[i] = Math.min(cache[i - coins[j]] + 1, cache[i]);
					}
				}
			}
		}

		System.out.println("Number of operations: " + count);
		return cache[val];
	}

	public static int minNumOfCoinsOn2(int[] coins, int val) {
		if (coins == null || coins.length == 0 || val < 1) return 0;

		int result = Integer.MAX_VALUE;

		int current_val;
		int current_result;

		int count = 0;

		for (int i = 0; i < coins.length; i++) {
			current_val = val;
			current_result = 0;
			for (int j = i; j < coins.length; j++) {
				count++;
				current_result += current_val / coins[j];
				current_val = current_val % coins[j];
				if (current_val == 0) break;
			}

			result = Math.min(result, current_result);
		}

		System.out.println("Number of operations: " + count);
		return result;
	}

	private static void test(int[] coins, int val, int expected) {
		int result = minNumOfCoinsOn1(coins, val);
		//		int result = minNumOfCoinsOn2(coins, val);
		System.out.println(String.format("Testing: %s with val:%d !\n\tExpected %d, got %d",
				Arrays.toString(coins), val, expected, result));
		assert(result == expected);
	}

	public static void main(String[] args) {
		test(new int[] {9, 6, 5, 1}, 11, 2);
		test(new int[] {9, 6, 5, 1}, 9, 1);
		test(new int[] {9, 6, 5, 1}, 6, 1);
		test(new int[] {9, 6, 5, 1}, -6, 0);
		test(new int[] {}, 6, 0);
		test(new int[] {10, 6, 5, 1}, 66, 7);
		test(new int[] {25, 10, 1}, 30, 3);
		test(new int[] {259, 66, 44, 42, 41, 37, 35, 33, 32, 21, 10, 1}, 30078, 118);
		test(new int[] {33, 32, 21, 10, 1}, 30078, 912);

		System.out.println("\n\nPassed all tests!");
	}

}
