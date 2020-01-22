package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeFromPreorderInorder {
  private static Map<Integer, Integer> iMap;
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
  public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(
          List<Integer> preorder, List<Integer> inorder) {
    // TODO - you fill in here.
    iMap = new HashMap<>();
    for (int i = 0; i < inorder.size(); i++) {
      iMap.put(inorder.get(i), i);
    }
    if (preorder == null) return null;
    return getRoot(preorder, 0, preorder.size()-1, 0, inorder.size()-1);
  }

  private static BinaryTreeNode<Integer> getRoot(
          List<Integer> preorder, int pStart, int pEnd,
          int iStart, int iEnd) {
    if (pStart > pEnd) return null;
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(pStart));
    int i = iMap.get(preorder.get(pStart));
    int nLeft = i - iStart, nRight = iEnd - i;
    root.left = getRoot(preorder, pStart+1, pStart+nLeft, iStart, i-1);
    root.right = getRoot(preorder, pStart+nLeft+1, pEnd, i+1, iEnd);
    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
