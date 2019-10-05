package epi;

import java.util.List;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;

public class BstFromSortedArray {

	public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> A) {
		return addNodesRecursively(0, A.size() - 1, A);
	}

	private static BstNode<Integer> addNodesRecursively(int l, int r, List<Integer> A) {
		if (l > r) {
			return null;
		}

		int m = l + ((r - l) / 2);

		return new BstNode<>(A.get(m), addNodesRecursively(l, m - 1, A), addNodesRecursively(m + 1, r, A));

	}

	@EpiTest(testDataFile = "bst_from_sorted_array.tsv")
	public static int buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor, List<Integer> A)
			throws Exception {
		BstNode<Integer> result = executor.run(() -> buildMinHeightBSTFromSortedArray(A));

		List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

		TestUtils.assertAllValuesPresent(A, inorder);
		BinaryTreeUtils.assertTreeIsBst(result);
		return BinaryTreeUtils.binaryTreeHeight(result);
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "BstFromSortedArray.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
