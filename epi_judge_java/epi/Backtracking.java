package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Backtracking {
	private static List<List<Integer>> perms;
	private static List<Integer> currentPerm;

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> powerSet = new ArrayList<>();
		backtracking(0, nums, new ArrayList<>(), powerSet);
		return powerSet;
	}

	public static void backtracking(int start, int[] nums,
			List<Integer> subset, List<List<Integer>> powerSet) {
		powerSet.add(new ArrayList<>(subset));

		for (int i = start; i < nums.length; i++) {
			subset.add(nums[i]);
			backtracking(i + 1, nums, subset, powerSet);
			subset.remove(subset.size() - 1);
		}
	}

	public static List<List<Integer>> permute(int[] nums) {
		perms = new ArrayList<>();
		currentPerm = new ArrayList<>();
		backtrack(nums);
		return perms;
	}

	private static void backtrack(int[] nums) {
		if (currentPerm.size() == nums.length) {
			perms.add(new ArrayList<>(currentPerm));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (!currentPerm.contains(nums[i])) {
					currentPerm.add(nums[i]);
					backtrack(nums);
					currentPerm.remove(currentPerm.size() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		//		subsets(new int[] {1, 2, 3});
		//		permute(new int[] {1, 2, 3});
		Set<List<Integer>> s = new HashSet<>();
		List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<Integer> l2 = new ArrayList<>(Arrays.asList(2, 1, 3));
		List<Integer> l3 = new ArrayList<>(Arrays.asList(3, 2, 1));
		s.add(l1);
		s.add(l2);
		s.add(l3);
		System.out.println(s);

	}

}
