package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return sumRootToLeaf(tree, 0);
  }

  private static int sumRootToLeaf(BinaryTreeNode<Integer> tree, int partial) {
    if (tree == null) return 0;
    partial = partial*2 + tree.data;
    if (tree.left == null && tree.right == null) return partial;
    return sumRootToLeaf(tree.left, partial) + sumRootToLeaf(tree.right, partial);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
