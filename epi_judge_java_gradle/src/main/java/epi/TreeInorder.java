package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeInorder {
  @EpiTest(testDataFile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    List<Integer> results = new ArrayList<>();
    BinaryTreeNode<Integer> t = tree;
    while (t != null || !stack.empty()) {
      if (t != null) {
        stack.push(t);
        t = t.left;
      } else {
        t = stack.pop();
        results.add(t.data);
        t = t.right;
      }
    }
    return results;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
