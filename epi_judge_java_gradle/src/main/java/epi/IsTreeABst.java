package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return isInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isInRange(BinaryTreeNode<Integer> tree, int min, int max) {
    if (tree == null) return true;
    return tree.data >= min && tree.data <= max &&
            isInRange(tree.left, min, tree.data) &&
            isInRange(tree.right, tree.data, max);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
