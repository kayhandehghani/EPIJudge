package epi;

import java.util.Arrays;

public class MyBitQuestions {

	/**
	 * Question: Every number has been repeated 4 times except one, which is
	 * repeated once Return that unique number using O(n) time complexity and O(1)
	 * space
	 */
	public static int getRepeatedOnce(int[] nums) {
		if (nums == null) {
			throw new IllegalArgumentException("Don't pass a null dude!");
		}

		int seenOnce = 0, seenTwice = 0, seenThreeTimes = 0;

		for (int n : nums) {
			seenOnce = ~seenTwice & ~seenThreeTimes & (seenOnce ^ n);
			seenTwice = ~seenThreeTimes & ~seenOnce & (seenTwice ^ n);
			seenThreeTimes = ~seenOnce & ~seenTwice & (seenThreeTimes ^ n);
		}

		return seenOnce;
	}

	public static void main(String[] args) {
		test(new int[] {1, 2, 1, 1, 1}, 2);
		test(new int[] {1, 0, 26, 0, 1, 1, 0, 1, 0}, 26);
		test(new int[] {3, 3, 3, 3, 8}, 8);
		test(new int[] {3, 3, 3, 3, 0}, 0);
		test(new int[] {Integer.MAX_VALUE-1, Integer.MAX_VALUE-1, Integer.MAX_VALUE-1, Integer.MAX_VALUE-1, 5}, 5);
		test(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 5, 5, 5, 77}, 77);

		System.out.println("Passed all tests!");
	}

	public static void test(int[] nums, int expected) {
		int result = getRepeatedOnce(nums);
		System.out.println(String.format("input: %s, expected: %d, result: %d", Arrays.toString(nums), expected, result));
		assert(expected == result);
	}

}
