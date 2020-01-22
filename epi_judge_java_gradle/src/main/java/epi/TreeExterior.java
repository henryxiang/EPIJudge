package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class TreeExterior {

  public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    List<BinaryTreeNode<Integer>> list = new LinkedList<>();
    if (tree != null) {
      list.add(tree);
      collectLeftTreeExterior(tree.left, true, list);
      collectRightTreeExterior(tree.right, true, list);
    }
    return list;
  }

  private static void collectLeftTreeExterior(
          BinaryTreeNode<Integer> tree, boolean isEdge, List<BinaryTreeNode<Integer>> list) {
    if (tree == null) return;
    if (isEdge || isLeaf(tree)) {
      list.add(tree);
    }
    collectLeftTreeExterior(tree.left, isEdge && tree.left != null, list);
    collectLeftTreeExterior(tree.right, isEdge && tree.left == null, list);
  }

  private static void collectRightTreeExterior(
          BinaryTreeNode<Integer> tree, boolean isEdge, List<BinaryTreeNode<Integer>> list) {
    if (tree == null) return;
    collectRightTreeExterior(tree.left, isEdge && tree.right == null, list);
    collectRightTreeExterior(tree.right, isEdge && tree.right != null, list);
    if (isLeaf(tree) || isEdge) {
      list.add(tree);
    }
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> tree) {
    return (tree != null && tree.left == null && tree.right == null);
  }

  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
