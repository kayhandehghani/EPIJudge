package epi;

import java.util.Arrays;

public class MyGreedy {

	public static void main(String[] args) {
		testArray(new int[] { 0 }, true);
		testArray(new int[] { 0, 1 }, false);
		testArray(new int[] { 1, 1 }, true);
		testArray(new int[] { 1, 0 }, true);
		testArray(new int[] { 2, 3, 1, 1, 4 }, true);
		testArray(new int[] { 3, 2, 1, 0, 4 }, false);
		testArray(new int[] { 4, 3, 0, 0, 1, 0, 4 }, false);
		testArray(new int[] { 4, 3, 0, 0, 1, 1, 4 }, true);
		testArray(new int[] { 4, 3, 0, 0, 2, 0, 4 }, true);
	}

	public static void testArray(int[] arr, boolean expected) {
		System.out.println(String.format("Array to test %s", Arrays.toString(arr)));
		boolean result = canJump(arr);
		System.out.println(String.format("\tExpected %b, returned %b XYZ", expected, result));
		assert (expected == result);
	}

	public static boolean canJump(int[] nums) {
		if (nums == null || nums.length <= 1)
			return true;
		return canJumRecursive(nums, nums.length - 1, 0);
	}

	private static boolean canJumRecursive(int[] nums, int endIndex, int neededJump) {
		if (endIndex == 0 && nums[0] > 0)
			return true;
		if (nums[endIndex] >= neededJump)
			return canJumRecursive(nums, endIndex - 1, 1);
		while (endIndex >= 0 && nums[endIndex] < neededJump) {
			endIndex--;
			neededJump++;
		}
		if (endIndex < 0)
			return false;
		return canJumRecursive(nums, endIndex, neededJump);
	}
}
