package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class SuccessorInTree {

  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    // TODO - you fill in here.
    if (node == null) return null;
    if (node.right == null) return findMinParent(node);
    else return findMinChild(node.right);
  }

  private static BinaryTree<Integer> findMinChild(BinaryTree<Integer> node) {
    BinaryTree<Integer> min = node;
    while (min != null && min.parent != null && min.left != null) min = min.left;
    return min;
  }

  private static BinaryTree<Integer> findMinParent(BinaryTree<Integer> node) {
    BinaryTree<Integer> min = node;
    while (min.parent != null && min.parent.left != min) min = min.parent;
    return min.parent;
  }

  @EpiTest(testDataFile = "successor_in_tree.tsv")
  public static int findSuccessorWrapper(TimedExecutor executor,
                                         BinaryTree<Integer> tree, int nodeIdx)
      throws Exception {
    BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

    BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

    return result == null ? -1 : result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
