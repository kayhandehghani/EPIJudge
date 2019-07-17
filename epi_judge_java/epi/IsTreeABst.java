package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
	  return isTreeABST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  private static boolean isTreeABST(BinaryTreeNode<Integer> tree, int min, int max) {
	  if (tree == null) {
		  return true;
	  } else if (tree.data < min || tree.data > max) {
		  return false;
	  }
	  return isTreeABST(tree.left, min, tree.data) && isTreeABST(tree.right, tree.data, max);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
