package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    if (tree == null) return Collections.emptyList();
    List<List<Integer>> results = new ArrayList<>();
    List<BinaryTreeNode<Integer>> currentLevel = new ArrayList<>();
    currentLevel.add(tree);
    while (!currentLevel.isEmpty()) {
      List<Integer> levelData = new ArrayList<>();
      List<BinaryTreeNode<Integer>> nextLevel = new ArrayList<>();
      for (BinaryTreeNode<Integer> n : currentLevel) {
        levelData.add(n.data);
        if (n.left != null) nextLevel.add(n.left);
        if (n.right != null) nextLevel.add(n.right);
      }
      results.add(levelData);
      currentLevel = nextLevel;
    }
    return results;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
