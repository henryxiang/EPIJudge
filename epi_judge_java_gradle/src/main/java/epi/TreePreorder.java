package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreePreorder {
  @EpiTest(testDataFile = "tree_preorder.tsv")
  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    List<Integer> results = new ArrayList<>();
    stack.push(tree);
    while (!stack.empty()) {
      BinaryTreeNode<Integer> t = stack.pop();
      if (t != null) {
        results.add(t.data);
        stack.push(t.right);
        stack.push(t.left);
      }
    }
    return results;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
