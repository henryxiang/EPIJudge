package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.LinkedList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    // TODO - you fill in here.
    List<Integer> keys = new LinkedList<>();
    BinaryTree<Integer> curr = tree, prev = null, next;
    while (curr != null) {
      if (curr.parent == prev) {
        if (curr.left != null) {
          next = curr.left;
        } else {
          keys.add(curr.data);
          next = curr.right != null ? curr.right : curr.parent;
        }
      } else if (curr.left == prev) {
        keys.add(curr.data);
        next = curr.right != null ? curr.right : curr.parent;
      } else {
        next = curr.parent;
      }
      prev = curr;
      curr = next;
    }
    return keys;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
