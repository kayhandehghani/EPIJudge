package epi;

import java.util.Stack;

public class BSTTraversal {

	public static void inOrder(BinaryTreeNode<Integer> tree) {
		// inorderRecursive(tree);
		inorderIterative(tree);
	}
	
	public static void postOrder(BinaryTreeNode<Integer> tree) {
		postOrderRecursive(tree);
		postOrderIterative(tree);
	}

	@SuppressWarnings("unused")
	private static void inorderRecursive(BinaryTreeNode<Integer> tree) {
		if (tree == null) {
			return;
		}
		inorderRecursive(tree.left);
		printNode(tree);
		inorderRecursive(tree.right);
	}

	@SuppressWarnings("unused")
	private static void inorderIterative(BinaryTreeNode<Integer> tree) {
		Stack<BinaryTreeNode<Integer>> s = new Stack<>();
		
		while (tree != null || !s.isEmpty()) {
			while (tree != null) {
				s.push(tree);
				tree = tree.left;
			}
			
			tree = s.pop();
			printNode(tree);
			tree = tree.right;
		}

	}
	
	@SuppressWarnings("unused")
	private static void postOrderRecursive(BinaryTreeNode<Integer> tree) {
		if (tree == null) {
			return;
		}
		
		postOrderRecursive(tree.left);
		postOrderRecursive(tree.right);
		printNode(tree);
	}
	
	@SuppressWarnings("unused")
	private static void postOrderIterative(BinaryTreeNode<Integer> tree) {
		Stack<BinaryTreeNode<Integer>> s = new Stack<>();
		
		while (tree != null || !s.isEmpty()) {
			while(tree != null) {
				s.push(tree);
				tree = tree.left;
			}
			
			tree = s.pop();
			printNode(tree);
		}
		
		
		
	}
	
	private static void printNode(BinaryTreeNode<Integer> node) {
		System.out.print(node.data + "  ");
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> t0 = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> t1 = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> t2 = new BinaryTreeNode<>(2);
		BinaryTreeNode<Integer> t3 = new BinaryTreeNode<>(3);
		BinaryTreeNode<Integer> t4 = new BinaryTreeNode<>(4);
		BinaryTreeNode<Integer> t5 = new BinaryTreeNode<>(5);
		BinaryTreeNode<Integer> t6 = new BinaryTreeNode<>(6);
		t4.left = t2;
		t4.right = t6;
		t6.left = t5;
		t2.right = t3;
		t2.left = t1;
		t1.left = t0;
		inOrder(t4);
		System.out.println();
		postOrder(t4);
		System.out.println();
		System.out.println(IsTreeABst.isBinaryTreeBST(t4) ? "Is a BST" : "Is not a BST");
	}
}
