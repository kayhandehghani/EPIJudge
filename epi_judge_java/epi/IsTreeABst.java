package epi;

import java.util.LinkedList;
import java.util.Queue;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {
	@EpiTest(testDataFile = "is_tree_a_bst.tsv")

	public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
		// return isTreeABST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return isTreeABSTUsingQueue(tree);
	}

	/**
	 * Using recursive with O(n) time and space complexity O(n)
	 */
	@SuppressWarnings("unused")
	private static boolean isTreeABST(BinaryTreeNode<Integer> tree, int min, int max) {
		if (tree == null) {
			return true;
		} else if (tree.data < min || tree.data > max) {
			return false;
		}
		return isTreeABST(tree.left, min, tree.data) && isTreeABST(tree.right, tree.data, max);
	}

	/**
	 * Using recursive with O(n) time and space complexity O(1)
	 */
	private static boolean isTreeABSTUsingQueue(BinaryTreeNode<Integer> tree) {
		Queue<ElementWithMinMax> q = new LinkedList<>();
		q.add(new ElementWithMinMax(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
		while (!q.isEmpty()) {
			ElementWithMinMax el = q.poll();
			if (el.node == null) {
				continue;
			}
			if (el.node.data > el.max || el.node.data < el.min) {
				return false;
			}
			q.add(new ElementWithMinMax(el.node.left, el.min, el.node.data));
			q.add(new ElementWithMinMax(el.node.right, el.node.data, el.max));
		}
		return true;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IsTreeABst.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}

class ElementWithMinMax {
	BinaryTreeNode<Integer> node;
	Integer max;
	Integer min;

	public ElementWithMinMax(BinaryTreeNode<Integer> node, Integer min, Integer max) {
		this.node = node;
		this.min = min;
		this.max = max;
	}
}
